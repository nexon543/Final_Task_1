package com.epam.provider.dao;


import com.epam.provider.dao.factory.DaoFactory;
import com.epam.provider.model.Profile;

/**
 * Interface User Dao
 *
 * @author Gleb Akseonov
 */
public interface ProfileDao extends GenericDao<Profile> {

  /**
   * Method selects user from database by login and pass. If no user was found the empty object is
   * returned
   *
   * @param login user login
   * @param pass user password
   * @return initialized or empty user object
   */
  Profile findByLoginPass(String login, String pass) throws DaoException;

  Profile finByLogin(String login) throws DaoException;

  void updateTariff(Integer idProfile, Integer newIdTariff) throws DaoException;

  void updateBalance(Double newBalance, Integer idProfile) throws DaoException;
}
