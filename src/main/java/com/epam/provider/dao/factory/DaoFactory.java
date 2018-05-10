package com.epam.provider.dao.factory;

import com.epam.provider.dao.GenericDao;
import com.epam.provider.dao.ProfileDao;
import com.epam.provider.dao.impl.PaymentDaoImpl;
import com.epam.provider.dao.impl.ProfileDaoImpl;
import com.epam.provider.dao.impl.TariffDaoImpl;
import com.epam.provider.model.Payment;
import com.epam.provider.model.Tariff;

/**
 * @author Gleb Akseonov
 */
public class DaoFactory {

  private static ProfileDao profileDao = new ProfileDaoImpl();
  private static GenericDao<Tariff> tariffDao = new TariffDaoImpl();
  private static GenericDao<Payment> transactionsDao = new PaymentDaoImpl();
  private DaoFactory() {
    throw new IllegalStateException("Utility class");
  }

  public static ProfileDao getProfileDao() {
    return profileDao;
  }

  public static GenericDao<Tariff> getTariffDao() {
    return tariffDao;
  }

  public static GenericDao<Payment> getTransactionDao() {
    return transactionsDao;
  }
}
