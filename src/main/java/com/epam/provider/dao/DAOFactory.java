package com.epam.provider.dao;

import com.epam.provider.dao.impl.ProfilesDAO;
import com.epam.provider.dao.impl.TrafficDAO;
import com.epam.provider.dao.impl.UsersDAO;

import java.sql.Connection;

/**
 * Created by HP on 29.03.2018.
 */
public class DAOFactory {
    public static AbstractDAO getDAO(DAOType type, Connection conn) {
        switch (type) {
            case PROFILES:
                return new ProfilesDAO(conn);
            case TRAFFIC:
                return new TrafficDAO(conn);
            case TRANSACTIONS:
                return new TrafficDAO(conn);
            case USERS:
                return new UsersDAO(conn);
        }
        return null;
    }
}
