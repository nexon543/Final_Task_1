package com.epam.provider.dao.pool;

import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.util.resource.ResourceManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Gleb Akseonov
 */
public class ConnectorDB {

    private ConnectorDB() {
        throw new IllegalStateException("Utility class");
    }

    private static final Logger LOGGER = Logger.getLogger(ConnectorDB.class);
    private static String url;
    private static String user;
    private static String pass;

    static {
        url = ResourceManager.getDatabaseProperty(ResourceConstants.DB_KEY_URL);
        user = ResourceManager.getDatabaseProperty(ResourceConstants.DB_KEY_USER);
        pass = ResourceManager.getDatabaseProperty(ResourceConstants.DB_KEY_PASS);
        try {
            String driver=ResourceManager.getDatabaseProperty(ResourceConstants.DB_KEY_DRIVER_NAME);
            Class.forName(driver);
            LOGGER.log(Level.INFO, "initialized connection parameters: " + url + " " + user + " " + pass+" "+driver);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
