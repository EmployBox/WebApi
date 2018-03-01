package dataMapping.mappers;

import dataMapping.exceptions.DataMapperException;
import model.Local;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocalMapper extends AbstractMapper<Local>{
    private final String SELECT_QUERY = "SELECT Address, Country, District, ZIPCode FROM Local WHERE Address = ?";

    @Override
    protected String findByPKStatement() {
        return SELECT_QUERY;
    }

    @Override
    Local mapper(ResultSet rs) throws DataMapperException {
        try{
            String address = rs.getString("Address");
            String country = rs.getString("Country");
            String district = rs.getString("District");
            String zipCode = rs.getString("ZIPCode");

            Local local = Local.load(address, country, district, zipCode);
            getIdentityMap().put(local.getIdentityKey(), local);

            return local;
        } catch (SQLException e) {
            throw new DataMapperException(e.getMessage(), e);
        }
    }

    @Override
    public void insert(Local obj) {
        //TODO
    }

    @Override
    public void update(Local obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Local obj) {
        //TODO
    }
}