package com.epam.provider.service;

import com.epam.provider.dao.DaoException;
import com.epam.provider.dao.factory.DaoFactory;
import com.epam.provider.dao.TariffDao;
import com.epam.provider.model.Profile;
import com.epam.provider.model.Tariff;

import java.util.List;


public class TariffService extends Service {
    private TariffDao tariffDAO = DaoFactory.getTariffDao();

    public TariffService() {
    }

    public void insert(Tariff tariff) throws ServiceException {
        try {
            tariffDAO.create(tariff);
        } catch (DaoException e) {
            throw new ServiceException("can't insert tariff");
        }
    }
    public Integer getNumberOfRecords() throws ServiceException {
        try {
            return tariffDAO.countRecords();
        } catch (DaoException e) {
            throw new ServiceException("can't count number of tariff records");
        }
    }

    public List<Tariff> getTariffs(Integer start, Integer end) throws ServiceException {
        try {
            return tariffDAO.findLimited(start, end);
        } catch (DaoException e) {
            throw new ServiceException("error selecting tariffs");
        }
    }

    public List<Tariff> getAllTariffs() throws DaoException {
        List<Tariff> tariffs = tariffDAO.findAll();
        return tariffs;
    }

    public Tariff getTariffForUser(Profile profile) throws ServiceException {
        Tariff tariffs = new Tariff();
        try {
            tariffDAO.findById(profile.getTariff());
        } catch (DaoException e) {
            throw new ServiceException("can't find tariff for profile");
        }
        return tariffs;
    }
}
