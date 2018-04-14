package com.epam.provider.dao.pool;

import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.util.resource.ResourceManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Gleb Akseonov
 */
public class ConnectorDB {

    private static String url;
    private static String user;
    private static String pass;

    static {
        url = ResourceManager.getDatabaseProperty(ResourceConstants.DB_KEY_URL);
        user = ResourceManager.getDatabaseProperty(ResourceConstants.DB_KEY_USER);
        pass = ResourceManager.getDatabaseProperty(ResourceConstants.DB_KEY_PASS);
        try {
            Class.forName(ResourceManager.getDatabaseProperty(ResourceConstants.DB_KEY_DRIVER_NAME));
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
