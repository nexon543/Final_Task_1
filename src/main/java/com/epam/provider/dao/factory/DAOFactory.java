package com.epam.provider.dao.factory;

import com.epam.provider.dao.*;
import com.epam.provider.dao.impl.*;

/**
 * Created by HP on 29.03.2018.
 */
public abstract class DAOFactory {

    private final static ProfileDAO profilesDAO = new ProfileDAOImpl();
    private final static UserDAO usersDAO = new UserDAOImpl();
    private final static TariffDAO tariffDAO = new TariffDAOImpl();
    private final static TransactionDAO transactionsDAO = new TransactionDAOImpl();

    public static AbstractDAO getDAO(DAOType type) {
        switch (type) {
            case PROFILES:
                return profilesDAO;
            case TRANSACTIONS:
                return transactionsDAO;
            case USERS:
                return usersDAO;
            case TARIFF:
                return tariffDAO;
        }
        return null;
    }
}
