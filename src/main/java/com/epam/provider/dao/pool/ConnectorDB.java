package com.epam.provider.dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by HP on 27.03.2018.
 */
public class ConnectorDB {
    private  static final String PATH_TO_DATA="database";
    private static String URL_PROPERTY_KEY="url";
    private static String USER_PROPERTY_KEY="user";
    private static String PASSWORD_PROPERTY_KEY="pass";

    private static String url;
    private static String user;
    private static String pass;
    static {
        url="jdbc:mysql://localhost:3306/mydb";
        user="root";
        pass="root";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
