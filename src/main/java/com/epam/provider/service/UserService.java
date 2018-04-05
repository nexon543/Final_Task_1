package com.epam.provider.service;

import com.epam.provider.dao.DAOException;
import com.epam.provider.dao.factory.DAOFactory;
import com.epam.provider.dao.DAOType;
import com.epam.provider.dao.UserDAO;
import com.epam.provider.model.User;
import com.epam.provider.web.controller.command.Constants;

import java.sql.SQLException;

/**
 * Created by HP on 28.03.2018.
 */
public class UserService extends Service {

    private UserDAO usersDAO = (UserDAO) DAOFactory.getDAO(DAOType.USERS);

    public UserService() {
    }

    public User findUser(String login, String pass) throws ServiceException {;
        try {
            return usersDAO.findProfileByLoginPass(login, pass);
        } catch (DAOException e) {
            throw new ServiceException(ServiceException.MESS_SEARCH_USER_ERROR);
        }

    }
}
