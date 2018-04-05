package com.epam.provider.dao;

import com.epam.provider.model.User;

/**
 * Created by HP on 28.03.2018.
 */
public interface UserDAO extends AbstractDAO<Integer, User> {
    User findProfileByLoginPass(String login, String pass) throws DAOException;
}
