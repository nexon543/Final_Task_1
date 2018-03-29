package com.epam.provider.dao;

import com.epam.provider.dao.pool.ConnectionPool;

import java.sql.Connection;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by HP on 29.03.2018.
 */
public class DAOManager {
    private Map<DAOType, AbstractDAO> createdDao;
    private Connection connection;

    public DAOManager() {
        createdDao = new EnumMap<>(DAOType.class);
        connection = ConnectionPool.getInstance().getConnection();
    }

    public AbstractDAO getDAO(DAOType type) {
        AbstractDAO dao = createdDao.get(type);
        if (dao == null) {
            dao = DAOFactory.getDAO(type, connection);
        }
        return dao;
    }
}
