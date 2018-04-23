package com.epam.provider.service.impl;

import com.epam.provider.dao.GenericDao;
import com.epam.provider.dao.DaoException;
import com.epam.provider.dao.ProfileDao;
import com.epam.provider.dao.factory.DaoFactory;
import com.epam.provider.model.Profile;
import com.epam.provider.model.Payment;
import com.epam.provider.service.ProfileService;
import com.epam.provider.service.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.util.List;


/**
 * @author Gleb Aksenov
 */
public class ProfileServiceImpl implements ProfileService {
    private static final Logger LOGGER = Logger.getLogger(ProfileServiceImpl.class);
    private ProfileDao profileDao;
    private GenericDao<Payment> transactionGenericDao = DaoFactory.getTransactionDao();

    public ProfileServiceImpl() {
        profileDao = DaoFactory.getProfileDao();
    }

    /**
     * {@inheritDoc}
     */
    public Profile findById(Integer id) throws ServiceException {
        Profile profile;
        try {
            profile = profileDao.findById(id, null);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getStackTrace());
            throw new ServiceException("can't find profile for user", e);
        }
        return profile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBalance(Integer amount, Integer profileId) throws ServiceException {
        Date date = new Date(new java.util.Date().getTime());
        Payment payment = new Payment().setAmount(amount)
                .setDate(date)
                .setIdProfiles(profileId);
        try {
            transactionGenericDao.create(payment);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getStackTrace());
            throw new ServiceException("can't add balance",e);
        }
    }

    @Override
    public Profile findUser(String login, String pass) throws ServiceException {
        try {
            return profileDao.findByLoginPass(login, pass);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getStackTrace());
            throw new ServiceException("can't find user", e);
        }
    }

    @Override
    public boolean isUserExists(String login) throws ServiceException {
        if (login == null) {
            return false;
        }
        try {
            return profileDao.finByLogin(login).getProfileId() != null;
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getStackTrace());
            throw new ServiceException("can't find user by login",e);
        }
    }

    @Override
    public void createProfile(Profile profile) throws ServiceException {
        try {
            profileDao.create(profile);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getStackTrace());
            throw new ServiceException("Error in creatong profile",e);
        }
    }

    @Override
    public List<Profile> findAll() throws ServiceException {
        try {
            return profileDao.findAll(null);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getStackTrace());
            throw new ServiceException("Error finding all profiles",e);
        }
    }

    @Override
    public Profile getById(Integer id) throws ServiceException {
        try {
            return profileDao.findById(id, null);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getStackTrace());
            throw new ServiceException("Error find by id",e);
        }
    }

    @Override
    public void updateUser(Profile profile) throws ServiceException {
        try {
            profileDao.update(profile);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getStackTrace());
            throw new ServiceException("Error updating profile",e);
        }
    }

    @Override
    public void updateUsersTariff(Integer profileId, Integer newTariffId) throws ServiceException {
        try{
            profileDao.updateTariff(profileId, newTariffId);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getStackTrace());
            throw new ServiceException("updating profile tariff error",e);
        }
    }

    @Override
    public void deleteProfile(Integer profileId) throws ServiceException {
        try{
            profileDao.delete(profileId);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getStackTrace());
            throw new ServiceException("updating profile tariff error",e);
        }
    }

    @Override
    public Profile findUser(String login) throws ServiceException {
        if (login == null) {
            return new Profile();
        }
        try {
            return profileDao.finByLogin(login);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getStackTrace());
            throw new ServiceException("can't find user by login",e);
        }
    }
}
