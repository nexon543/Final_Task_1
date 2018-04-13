package com.epam.provider.dao.impl;

import com.epam.provider.dao.CrudDao;
import com.epam.provider.dao.DaoException;
import com.epam.provider.dao.pool.ConnectionPool;
import com.epam.provider.dao.pool.ConnectionPoolException;
import com.epam.provider.model.Entity;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class Abstract DAO
 *
 * @author Gleb Aksenov
 */
public abstract class AbstractDao<T extends Entity> implements CrudDao<T> {

    private static final Logger LOGGER = LogManager.getLogger(AbstractDao.class);

    /**
     * {@inheritDoc}
     */

    @Override
    public List<T> findAll() throws DaoException {
        Connection connection = null;
        List<T> entities = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ResultSet rs = executeSelectAll(connection);
            while (rs.next()) {
                T entity = getNewEntity(rs);
                entities.add(entity);
            }
            rs.close();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
        }
        return entities;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public T findById(Integer id) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ResultSet rs = executeSelectById(connection, id);
            rs.next();
            T entity = getNewEntity(rs);
            return entity;
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new DaoException("can't find entity by id");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
                throw new DaoException("error in sql select user by id");
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Integer id) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            executeDelete(connection, id);
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new DaoException("can't delete record");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(T entity) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            executeCreate(connection, entity);
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(T entity) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            executeUpdate(connection, entity);
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
        }
    }

    /**
     * Does main work in delete method
     */
    protected abstract void executeDelete(Connection connection, Integer id) throws SQLException;

    /**
     * Does main work in select all method
     */
    protected abstract ResultSet executeSelectAll(Connection connection) throws SQLException;

    /**
     * Does main work in select by id method
     */
    protected abstract ResultSet executeSelectById(Connection connection, Integer id) throws SQLException;

    /**
     * Does main work in delete method
     */
    protected abstract void executeUpdate(Connection connection, T entity) throws SQLException;

    /**
     * Does main work in create method
     */
    protected abstract void executeCreate(Connection connection, T entity) throws SQLException;

    /**
     * Used in create select and find methods
     */
    protected abstract T getNewEntity(ResultSet rs) throws SQLException;
}
