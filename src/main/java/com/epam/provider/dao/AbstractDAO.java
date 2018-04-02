package com.epam.provider.dao;

import com.epam.provider.dao.pool.ConnectionPool;
import com.epam.provider.model.Entity;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by HP on 26.03.2018.
 */
public abstract class AbstractDAO<K, T extends Entity> {
    private static Logger logger = Logger.getLogger(AbstractDAO.class);

    protected Connection connection;

    public AbstractDAO() {
    }

    public abstract List<T> findAll() throws SQLException, DAOException;

    public abstract T findEntityById(K id) throws SQLException, DAOException;

    public abstract boolean delete(K id);

    public abstract boolean delete(T entity);

    public abstract boolean create(T entity) throws DAOException;

    public abstract T update(T entity);

    public void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    public void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void openConnection() {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.ERROR, DAOException.MESS_CLOSE_CONECTION_ERROR);
        }
    }
}
