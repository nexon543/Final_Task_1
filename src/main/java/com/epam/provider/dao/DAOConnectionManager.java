package com.epam.provider.dao;

import com.epam.provider.dao.pool.ConnectionPool;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by HP on 29.03.2018.
 */
public class DAOConnectionManager {
    private static Logger logger = LogManager.getLogger(ConnectionPool.class);
    private Connection connection;

    public DAOConnectionManager() { }

    public void openConnection() {
        connection = ConnectionPool.getInstance().getConnection();
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "can't close connection");
        }
    }

    public AbstractDAO setDAOConnection(AbstractDAO dao) {
        if (connection == null) {
            openConnection();
        }
        dao.setConnection(connection);
        return dao;
    }
}
