package com.epam.provider.service.impl;

import com.epam.provider.dao.DaoException;
import com.epam.provider.dao.GenericDao;
import com.epam.provider.dao.ProfileDao;
import com.epam.provider.dao.factory.DaoFactory;
import com.epam.provider.model.Payment;
import com.epam.provider.model.Profile;
import com.epam.provider.model.Tariff;
import com.epam.provider.service.ProfileService;
import com.epam.provider.service.ServiceException;
import com.epam.provider.web.controller.command.Constants;
import com.epam.provider.web.validator.ValidationParameters;
import com.epam.provider.web.validator.Validator;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


/**
 * @author Gleb Aksenov
 */
public class ProfileServiceImpl implements ProfileService {

  private static final Logger LOGGER = Logger.getLogger(ProfileServiceImpl.class);
  private ProfileDao profileDao = DaoFactory.getProfileDao();
  private GenericDao<Tariff> tariffDao = DaoFactory.getTariffDao();
  private GenericDao<Payment> transactionGenericDao = DaoFactory.getTransactionDao();

  public ProfileServiceImpl() {
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
  public void addBalance(Double amount, Integer profileId) throws ServiceException {
    boolean isValid = Validator.isValid(new HashMap<ValidationParameters, String>() {{
      put(ValidationParameters.BALANCE, amount.toString());
    }});
    if (isValid) {
      Date date = new Date(new java.util.Date().getTime());
      Payment payment = new Payment().setAmount(amount)
          .setDate(date)
          .setIdProfiles(profileId);
      try {
        transactionGenericDao.create(payment);
      } catch (DaoException e) {
        LOGGER.log(Level.ERROR, e.getMessage());
        throw new ServiceException("can't add balance", e);
      }
    }
  }

  @Override
  public Profile findUser(String login, String pass) throws ServiceException {
    try {
      return profileDao.findByLoginPass(login, pass);
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e.getMessage());
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
      LOGGER.log(Level.ERROR, e.getMessage());
      throw new ServiceException("can't find user by login", e);
    }
  }

  @Override
  public void createProfile(Profile profile) throws ServiceException {
    try {
      profileDao.create(profile);
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e.getMessage());
      throw new ServiceException("Error in creatong profile", e);
    }
  }

  @Override
  public List<Profile> findAll() throws ServiceException {
    try {
      return profileDao.findAll(null);
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e.getMessage());
      throw new ServiceException("Error finding all profiles", e);
    }
  }

  @Override
  public Profile getById(Integer id) throws ServiceException {
    try {
      return profileDao.findById(id, null);
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e.getMessage());
      throw new ServiceException("Error find by id", e);
    }
  }

  @Override
  public boolean updateUser(Profile profile, Map<ValidationParameters, String> parametersForValidation)
      throws ServiceException {
    if (Validator.isValid(parametersForValidation)) {
      try {
        Profile existedProfile = findUser(profile.getLogin());
        boolean isExistedProfileNull = !Optional.ofNullable(existedProfile.getProfileId())
            .isPresent();
        boolean isExistedEqualsToNew = false;
        if (!isExistedProfileNull) {
          isExistedEqualsToNew = existedProfile.getProfileId().equals(profile.getProfileId());
        }
        boolean isEnableToUpdate = isExistedProfileNull || isExistedEqualsToNew;
        if (isEnableToUpdate) {
          profileDao.update(profile);
        }
        return isEnableToUpdate;
      } catch (DaoException e) {
        LOGGER.log(Level.ERROR, e.getMessage());
        throw new ServiceException("Error updating profile", e);
      }
    }
    return false;
  }


  @Override
  public boolean updateUsersTariff(Integer profileId, Integer newTariffId) throws ServiceException {
    Profile profile = null;
    try {
      profile = profileDao.findById(profileId, null);
      Tariff tariff = tariffDao.findById(newTariffId, Constants.DEFAULT_LANG);
      if (profile.getBalance() >= tariff.getPrice()) {
        profileDao.updateTariff(profile.getProfileId(), newTariffId);
        return true;
      }
      return false;
    } catch (DaoException e) {
      throw new ServiceException("updating profile tariff error", e);
    }
  }

  @Override
  public void deleteProfile(Integer profileId) throws ServiceException {
    try {
      profileDao.delete(profileId);
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e.getMessage());
      throw new ServiceException("updating profile tariff error", e);
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
      LOGGER.log(Level.ERROR, e.getMessage());
      throw new ServiceException("can't find user by login", e);
    }
  }
}
