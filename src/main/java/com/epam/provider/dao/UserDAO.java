package com.epam.provider.dao;

import com.epam.provider.model.User;


public interface UserDao extends CrudDao<Integer, User> {
    User findByLoginPass(String login, String pass) throws DaoException;
}
