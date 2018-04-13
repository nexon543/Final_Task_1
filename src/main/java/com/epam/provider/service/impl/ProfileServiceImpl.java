package com.epam.provider.service.impl;

import com.epam.provider.dao.CrudDao;
import com.epam.provider.dao.DaoException;
import com.epam.provider.dao.ProfileDao;
import com.epam.provider.dao.factory.DaoFactory;
import com.epam.provider.model.Profile;
import com.epam.provider.model.Transaction;
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
    private CrudDao<Transaction> transactionCrudDao = DaoFactory.getTransactionDao();

    public ProfileServiceImpl() {
        profileDao = DaoFactory.getProfileDao();
    }

    /**
     * {@inheritDoc}
     */
    public Profile findUserProfile(Integer id) throws ServiceException {
        Profile profile;
        try {
            profile = profileDao.findById(id);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException(ServiceException.MESS_SEARCH_PROFILE_ERROR);
        }
        return profile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBalance(Integer amount, Integer profileId) throws ServiceException {
        Date date = new Date(new java.util.Date().getTime());
        Transaction transaction = new Transaction().setAmount(amount)
                .setDate(date)
                .setIdProfiles(profileId);
        try {
            transactionCrudDao.create(transaction);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "can't add balance");
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Profile findUser(String login, String pass) throws ServiceException {
        try {
            return profileDao.findByLoginPass(login, pass);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "can't find user");
            throw new ServiceException(ServiceException.MESS_SEARCH_USER_ERROR);
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
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException("can't find user by login");
        }
    }

    @Override
    public void createProfile(Profile profile) throws ServiceException {
        try {
            profileDao.create(profile);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException("Error in creatong profile");
        }
    }

    @Override
    public List<Profile> findAll() throws ServiceException {
        try {
            return profileDao.findAll();
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException("Error finding all profiles");
        }
    }

    @Override
    public Profile getById(Integer id) throws ServiceException {
        try {
            return profileDao.findById(id);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException("Error find by id");
        }
    }
}
