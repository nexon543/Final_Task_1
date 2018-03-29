package com.epam.provider.dao.impl;

import com.epam.provider.dao.AbstractDAO;
import com.epam.provider.model.Entity;
import com.epam.provider.model.Traffic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by HP on 26.03.2018.
 */
public class TrafficDAO extends AbstractDAO<Integer, Traffic>{
    private Integer id;

    public TrafficDAO (Connection conn){
        super(conn);
    }

    @Override
    public List<Traffic> findAll() throws SQLException {
        return null;
    }

    @Override
    public Traffic findEntityById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(Traffic entity) {
        return false;
    }

    @Override
    public boolean create(Traffic entity) {
        return false;
    }

    @Override
    public Traffic update(Traffic entity) {
        return null;
    }


}
