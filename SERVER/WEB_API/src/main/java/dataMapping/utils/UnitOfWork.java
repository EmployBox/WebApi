package dataMapping.utils;

import dataMapping.exceptions.ConcurrencyException;
import model.DomainObject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {
    /**
     * Private for each transaction
     */
    private final Connection connection;
    private final List<DomainObject> newObjects = new ArrayList<>();
    private final List<DomainObject> clonedObjects = new ArrayList<>();
    private final List<DomainObject> dirtyObjects = new ArrayList<>();
    private final List<DomainObject> removedObjects = new ArrayList<>();

    private UnitOfWork(Connection connection){
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * Adds the obj to the newObjects List and to the IdentityMap
     * @param obj
     */
    public void registerNew(DomainObject obj) {
        assert obj.getIdentityKey() != null;
        assert !dirtyObjects.contains(obj);
        assert !removedObjects.contains(obj);
        assert !newObjects.contains(obj);
        newObjects.add(obj);
    }

    public void registerClone(DomainObject obj) {
        assert obj.getIdentityKey()!= null;
        assert !removedObjects.contains(obj);
        if(!clonedObjects.contains(obj) && !newObjects.contains(obj))
            clonedObjects.add(obj);
    }

    /**
     * Tags the object to be updated on the DB
     * @param obj
     */
    public void registerDirty(DomainObject obj){
        assert obj.getIdentityKey()!= null;
        assert !removedObjects.contains(obj);
        if(!dirtyObjects.contains(obj) && !newObjects.contains(obj))
            dirtyObjects.add(obj);
    }

    /**
     * Removes the obj from newObjects and/or dirtyObjects and from the IdentityMap
     * @param obj
     */
    public void registerRemoved(DomainObject obj){
        assert obj.getIdentityKey()!= null;
        if(newObjects.remove(obj)) return;
        dirtyObjects.remove(obj);
        if(!removedObjects.contains(obj))
            removedObjects.add(obj);
    }

    /**
     * Adds the new obj to the IdentityMap
     * @param obj
     */
    public void registerClean(DomainObject obj){
        assert obj.getIdentityKey()!= null;
        MapperRegistry.getMapper(obj.getClass()).getIdentityMap().put(obj.getIdentityKey(), obj);
    }

    private static ThreadLocal<UnitOfWork> current = new ThreadLocal<>();
    private static final ConnectionManager manager = ConnectionManager.getConnectionManagerOfDefaultDB();

    /**
     * Each Thread will have its own UnitOfWork
     */
    public static void newCurrent() {
        setCurrent(new UnitOfWork(manager.getConnection()));
    }

    public static void setCurrent(UnitOfWork uow) {
        current.set(uow);
    }

    public static UnitOfWork getCurrent() {
        return current.get();
    }

    //TODO does it catch the concurrentyException?
    //TODO update IdentityMaps only after all transactions succeeded?
    public void commit() throws SQLException {
        try {
            insertNew();
            updateDirty();
            deleteRemoved();

            connection.commit();
        } catch (ConcurrencyException e) {
            rollback();
            throw e;
        }
        finally {
            try { connection.close(); } catch (SQLException e) { }
        }
    }

    private void insertNew() {
        for (DomainObject obj : newObjects) {
            MapperRegistry.getMapper(obj.getClass()).insert(obj);
        }
    }

    private void updateDirty() {
        dirtyObjects
                .stream()
                .filter(domainObject -> !removedObjects.contains(domainObject))
                .forEach(domainObject -> MapperRegistry.getMapper(domainObject.getClass()).update(domainObject));
    }

    private void deleteRemoved() {
        for (DomainObject obj : removedObjects) {
            MapperRegistry.getMapper(obj.getClass()).delete(obj);
        }
    }

    /**
     * Removes the objects from the newObjects from the IdentityMap
     * Puts the objects in removedObjects into the IdentityMap
     * The objects in dirtyObjects need to go back as before
     */
    private void rollback() throws SQLException {
        connection.rollback();
        /*for (DomainObject obj : newObjects)
            MapperRegistry.getMapper(obj.getClass()).getIdentityMap().remove(obj.getIdentityKey());*/

        newObjects
                .stream()
                .filter(domainObject -> MapperRegistry.getMapper(domainObject.getClass()).getIdentityMap().containsKey(domainObject.getIdentityKey()))
                .forEach(domainObject -> MapperRegistry.getMapper(domainObject.getClass()).getIdentityMap().remove(domainObject.getIdentityKey(), domainObject));

        for(DomainObject obj : dirtyObjects){
            clonedObjects
                    .stream()
                    .filter(domainObject -> domainObject.getIdentityKey().equals(obj.getIdentityKey()))
                    .findFirst()
                    .ifPresent(
                            clone -> MapperRegistry.getMapper(obj.getClass()).getIdentityMap().put(clone.getIdentityKey(), clone)
                    );
        }

        removedObjects
                .stream()
                .filter(obj -> !dirtyObjects.contains(obj))
                .forEach(obj -> MapperRegistry.getMapper(obj.getClass()).getIdentityMap().put(obj.getIdentityKey(), obj));
    }
}
