package com.epam.provider.service;

import com.epam.provider.dao.DAOConnectionManager;
import com.epam.provider.dao.DAOException;
import com.epam.provider.dao.DAOFactory;
import com.epam.provider.dao.DAOType;
import com.epam.provider.dao.impl.TariffDAO;
import com.epam.provider.model.Profile;
import com.epam.provider.model.Tariff;

import java.util.List;

/**
 * Created by HP on 30.03.2018.
 */
public class TariffService extends Service {
    private TariffDAO tariffDAO = (TariffDAO) DAOFactory.getDAO(DAOType.TARIFF);

    public TariffService() {
    }

    public boolean insert(Tariff tariff) throws ServiceException {
        daoManager.openConnection();
        daoManager.setDAOConnection(tariffDAO);
        try {
            return tariffDAO.create(tariff);
        } catch (DAOException e) {
            throw new ServiceException("can't insert tariff");
        }
        finally{
            daoManager.closeConnection();
        }
    }
    public Integer getNumberOfRecords() throws ServiceException {
        daoManager.openConnection();
        daoManager.setDAOConnection(tariffDAO);
        try {
            return tariffDAO.countRecords();
        } catch (DAOException e) {
            throw new ServiceException("can't count number of tariff records");
        } finally {
            daoManager.closeConnection();
        }
    }

    public List<Tariff> getTariffs(Integer start, Integer end) throws ServiceException {
        daoManager.openConnection();
        try {
            return tariffDAO.findLimited(start, end);
        } catch (DAOException e) {
            throw new ServiceException("error selecting tariffs");
        }
        finally {
            daoManager.closeConnection();
        }
    }

    public List<Tariff> getAllTariffs() throws DAOException {
        List<Tariff> tariffs = tariffDAO.findAll();
        return tariffs;
    }

    public Tariff getTariffForUser(Profile profile) throws ServiceException {
        Tariff tariffs = new Tariff();
        tariffDAO.openConnection();
        try {
            tariffDAO.findEntityById(profile.getTariff());
        } catch (DAOException e) {
            throw new ServiceException("can't find tariff for profile");
        } finally {
            tariffDAO.closeConnection();
        }
        return tariffs;
    }
}
