package com.epam.provider.dao.factory;

import com.epam.provider.dao.CrudDao;
import com.epam.provider.dao.ProfileDao;
import com.epam.provider.dao.TariffDao;
import com.epam.provider.dao.impl.ProfileDaoImpl;
import com.epam.provider.dao.impl.TariffDaoImpl;
import com.epam.provider.dao.impl.TransactionDaoImpl;
import com.epam.provider.model.Transaction;

/**
 * @author Gleb Akseonov
 */
public class DaoFactory {

    private static ProfileDao profileDao = new ProfileDaoImpl();
    private static TariffDao tariffDao = new TariffDaoImpl();
    private static CrudDao<Transaction> transactionsDao = new TransactionDaoImpl();

    public static ProfileDao getProfileDao() {
        return profileDao;
    }

    public static TariffDao getTariffDao() {
        return tariffDao;
    }

    public static CrudDao<Transaction> getTransactionDao() {
        return transactionsDao;
    }
}
