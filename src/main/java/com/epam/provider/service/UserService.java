package com.epam.provider.service;

import com.epam.provider.dao.DAOException;
import com.epam.provider.dao.factory.DaoFactory;
import com.epam.provider.dao.UserDao;
import com.epam.provider.model.User;

/**
 * Created by HP on 28.03.2018.
 */
public class UserService extends Service {

    private UserDao usersDAO = DaoFactory.getUserDao();

    public UserService() {
    }

    public User findUser(String login, String pass) throws ServiceException {;
        try {
            return usersDAO.findByLoginPass(login, pass);
        } catch (DAOException e) {
            throw new ServiceException(ServiceException.MESS_SEARCH_USER_ERROR);
        }

    }
}
