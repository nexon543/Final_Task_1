package com.epam.provider.service.impl;

import com.epam.provider.dao.DaoException;
import com.epam.provider.dao.GenericDao;
import com.epam.provider.dao.factory.DaoFactory;
import com.epam.provider.model.Tariff;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.TariffService;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * @author Gleb Aksenov
 */
public class TariffServiceImpl implements TariffService {

  private static final Logger LOGGER = LogManager.getLogger(TariffServiceImpl.class);
  private GenericDao<Tariff> tariffDAO = DaoFactory.getTariffDao();
  ;

  public TariffServiceImpl() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createTariff(Tariff tariff) throws ServiceException {
    try {
      tariffDAO.create(tariff);
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e.getMessage());
      throw new ServiceException("can't createTariff tariff");
    }
  }

  @Override
  public void updateTariff(Tariff tariff) throws ServiceException {
    try {
      tariffDAO.update(tariff);
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e.getMessage());
      throw new ServiceException("can't createTariff tariff");
    }
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public Integer getNumberOfRecords() throws ServiceException {
    try {
      return tariffDAO.countRecords();
    } catch (DaoException e) {
      throw new ServiceException("can't count number of tariff records");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Tariff> getTariffs(Integer start, Integer end, String lang) throws ServiceException {
    try {
      return tariffDAO.findLimited(start, end, lang);
    } catch (DaoException e) {
      throw new ServiceException("error selecting tariffs");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Tariff> getAllTariffs(String lang) throws ServiceException {
    try {
      return tariffDAO.findAll(lang);
    } catch (DaoException e) {
      throw new ServiceException(e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Tariff getTariffById(Integer id, String lang) throws ServiceException {
    try {
      return tariffDAO.findById(id, lang);
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e.getStackTrace());
      throw new ServiceException("can't find tariff by id");
    }
  }

  @Override
  public void deleteTariff(Integer id) throws ServiceException {
    try {
      tariffDAO.delete(id);
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e.getStackTrace());
      throw new ServiceException(("can't delete tariff with id=" + id));
    }
  }
}
