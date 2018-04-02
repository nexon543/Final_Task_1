package com.epam.provider.dao;

import com.epam.provider.dao.impl.*;

/**
 * Created by HP on 29.03.2018.
 */
public class DAOFactory {
    private final static ProfilesDAO profilesDAO = new ProfilesDAO();
    private final static UsersDAO usersDAO = new UsersDAO();
    private final static TrafficDAO trafficDAO = new TrafficDAO();
    private final static TariffDAO tariffDAO = new TariffDAO();
    private final static TransactionsDAO transactionsDAO = new TransactionsDAO();

    public static AbstractDAO getDAO(DAOType type) {
        switch (type) {
            case PROFILES:
                return profilesDAO;
            case TRAFFIC:
                return trafficDAO;
            case TRANSACTIONS:
                return trafficDAO;
            case USERS:
                return usersDAO;
            case TARIFF:
                return tariffDAO;
        }
        return null;
    }
}
