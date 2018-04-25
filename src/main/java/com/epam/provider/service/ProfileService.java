package com.epam.provider.service;

import com.epam.provider.model.Profile;

import java.util.List;

/**
 * @author Gleb Aksenov
 */
public interface ProfileService {

    /**
     * Method changes balance of a user while doing a transaction
     *
     * @param amount    money to deposit
     * @param profileId id of client who pays
     * @throws ServiceException
     */
    void addBalance(Integer amount, Integer profileId) throws ServiceException;

    /**
     * Method selects user from database by login and pass. If no user was found the empty object is returned
     *
     * @param login user login
     * @param pass  user password
     * @return initialized or empty user object
     * @throws ServiceException
     */
    Profile findUser(String login, String pass) throws ServiceException;

    boolean isUserExists(String login) throws ServiceException;

    void createProfile(Profile profile) throws ServiceException;

    List<Profile> findAll() throws ServiceException;

    Profile getById(Integer id) throws ServiceException;

    void updateUser(Profile profile) throws ServiceException;

    boolean updateUsersTariff(Integer profileId, Integer newTariffId) throws ServiceException;

    void deleteProfile (Integer profileId)throws ServiceException;

    Profile findUser(String login)throws ServiceException;
}
