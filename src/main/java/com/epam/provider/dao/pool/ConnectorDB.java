package com.epam.provider.dao.pool;

import com.epam.provider.util.resource.DatabaseResourceManager;
import com.epam.provider.util.resource.ResourceConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by HP on 27.03.2018.
 */
public class ConnectorDB {

    private static String url;
    private static String user;
    private static String pass;

    static {
        url = DatabaseResourceManager.getProperty(ResourceConstants.DB_KEY_URL);
        user = DatabaseResourceManager.getProperty(ResourceConstants.DB_KEY_USER);
        pass = DatabaseResourceManager.getProperty(ResourceConstants.DB_KEY_PASS);
        try {
            Class.forName(DatabaseResourceManager.getProperty(ResourceConstants.DB_KEY_DRIVER_NAME));
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
