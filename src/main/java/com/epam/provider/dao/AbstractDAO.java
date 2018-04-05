package com.epam.provider.dao;

import com.epam.provider.dao.pool.ConnectionPool;
import com.epam.provider.model.Entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractDao<K, T extends Entity> implements CrudDao<K, T> {

    @Override
    public List<T> findAll() throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        List<T> entities = new ArrayList<>();
        try {
            ResultSet rs = executeSelectAll(connection);
            if (rs.next()) {
                T entity = getNewEntity(rs);
                entities.add(entity);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        }
        return entities;
    }

    @Override
    public T findById(K id) throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            ResultSet rs = executeSelectById(connection, id);
            rs.next();
            return getNewEntity(rs);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        }
    }

    @Override
    public void delete(K id) throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            executeDelete(connection, id);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        }
    }

    @Override
    public void create(T entity) throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            executeCreate(connection, entity);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        }
    }

   @Override
    public void update(T entity) throws DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            executeUpdate(connection, entity);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        }
    }

    protected abstract void executeDelete(Connection connection, K id) throws SQLException;

    protected abstract ResultSet executeSelectAll(Connection connection) throws SQLException;

    protected abstract ResultSet executeSelectById(Connection connection, K id) throws SQLException;

    protected abstract void executeUpdate(Connection connection, T entity) throws SQLException;

    protected abstract void executeCreate(Connection connection, T entity) throws SQLException;

    protected abstract T getNewEntity(ResultSet rs) throws SQLException;
}
