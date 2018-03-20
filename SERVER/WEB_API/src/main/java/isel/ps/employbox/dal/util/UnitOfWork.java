package isel.ps.employbox.dal.util;

import isel.ps.employbox.dal.exceptions.ConcurrencyException;
import isel.ps.employbox.dal.model.DomainObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static isel.ps.employbox.dal.util.MapperRegistry.getMapper;

public class UnitOfWork {
    /**
     * Private for each transaction
     */
    private Connection connection = null;
    private final Logger log = LoggerFactory.getLogger(UnitOfWork.class);
    private final Supplier<Connection> connectionSupplier;
    private final List<DomainObject> newObjects = new ArrayList<>();
    private final List<DomainObject> clonedObjects = new ArrayList<>();
    private final List<DomainObject> dirtyObjects = new ArrayList<>();
    private final List<DomainObject> removedObjects = new ArrayList<>();

    private UnitOfWork(Supplier<Connection> connectionSupplier){
        this.connectionSupplier = connectionSupplier;
    }

    public Connection getConnection() {
        if(connection == null)
            connection = connectionSupplier.get();
        return connection;
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) { }
        connection = null;
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
        //getMapper(obj.getClass()).getIdentityMap().put(obj.getIdentityKey(), obj);
    }

    private static ThreadLocal<UnitOfWork> current = new ThreadLocal<>();

    /**
     * Each Thread will have its own UnitOfWork
     */
    public static void newCurrent(Supplier<Connection> supplier) {
        setCurrent(new UnitOfWork(supplier));
    }

    private static void setCurrent(UnitOfWork uow) {
        current.set(uow);
    }

    //TODO FIX: sometimes current.get() returns null,
    /**Possible reason: since we are using completableFuture we dont have the guarantee that the threadLocal of the working thread has the unit of work*/
    public static UnitOfWork getCurrent() {
        UnitOfWork ret = current.get();//test porpuses
        if(ret == null)
            ret = new UnitOfWork(ConnectionManager::getConnection2);
        return ret;
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
            //closeConnection();
            newObjects.clear();
            clonedObjects.clear();
            dirtyObjects.clear();
            removedObjects.clear();
        }
    }

    private void insertNew() {
        for (DomainObject obj : newObjects) {
            //getMapper(obj.getClass()).insert(obj);
        }
    }

    private void updateDirty() {
        /*dirtyObjects
                .stream()
                .filter(domainObject -> !removedObjects.contains(domainObject))
                .forEach(domainObject -> getMapper(domainObject.getClass()).update(domainObject));*/
    }

    private void deleteRemoved() {
        for (DomainObject obj : removedObjects) {
            //getMapper(obj.getClass()).delete(obj);
        }
    }

    /**
     * Removes the objects from the newObjects from the IdentityMap
     * Puts the objects in removedObjects into the IdentityMap
     * The objects in dirtyObjects need to go back as before
     */
    public void rollback() throws SQLException {
        connection.rollback();
        /*for (DomainObject obj : newObjects)
            MapperRegistry.getMapper(obj.getClass()).getIdentityMap().remove(obj.getIdentityKey());*/

        /*newObjects
                .stream()
                .filter(domainObject -> getMapper(domainObject.getClass()).getIdentityMap().containsKey(domainObject.getIdentityKey()))
                .forEach(domainObject -> getMapper(domainObject.getClass()).getIdentityMap().remove(domainObject.getIdentityKey(), domainObject));

        for(DomainObject obj : dirtyObjects){
            clonedObjects
                    .stream()
                    .filter(domainObject -> domainObject.getIdentityKey().equals(obj.getIdentityKey()))
                    .findFirst()
                    .ifPresent(
                            clone -> getMapper(obj.getClass()).getIdentityMap().put(clone.getIdentityKey(), clone)
                    );
        }
        removedObjects
                .stream()
                .filter(obj -> !dirtyObjects.contains(obj))
                .forEach(obj -> getMapper(obj.getClass()).getIdentityMap().put(obj.getIdentityKey(), obj));*/
    }
}
