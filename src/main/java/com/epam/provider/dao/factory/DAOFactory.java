package com.epam.provider.dao.factory;

import com.epam.provider.dao.*;
import com.epam.provider.dao.impl.*;

public abstract class DaoFactory {

    private final static ProfileDao profileDao = new ProfileDaoImpl();
    private final static UserDao userDao = new UserDaoImpl();
    private final static TariffDao tariffDao = new TariffDaoImpl();
    private final static TransactionDao transactionsDao = new TransactionDaoImpl();

    public static ProfileDao getProfileDao(){
        return profileDao;
    }

    public static UserDao getUserDao(){
        return userDao;
    }

    public static TariffDao getTariffDao(){
        return tariffDao;
    }

    public static TransactionDao getTransactionDao(){
        return transactionsDao;
    }
}
