package com.epam.provider.service;

import com.epam.provider.controller.command.Constants;
import com.epam.provider.dao.DAOFactory;
import com.epam.provider.dao.DAOType;
import com.epam.provider.dao.impl.UsersDAO;
import com.epam.provider.model.User;

import java.sql.SQLException;

/**
 * Created by HP on 28.03.2018.
 */
public class UserService extends Service {

    private UsersDAO usersDAO = (UsersDAO) DAOFactory.getDAO(DAOType.USERS);

    public UserService() {
    }

    public User findUser(String login, String pass) throws ServiceException {
        User user;
        daoManager.openConnection();
        daoManager.setDAOConnection(usersDAO);
        try {
            user = usersDAO.findProfileByLoginPass(login, pass);
        } catch (SQLException e) {
            throw new ServiceException(ServiceException.MESS_SEARCH_USER_ERROR);
        }
        finally{
            daoManager.closeConnection();
        }
        return user;
    }

    public boolean validateLoginUser(User user) {
        boolean result = Constants.ROLE_NAME_CLIENT.equals(user.getRole());
        return result;
    }

    public boolean validateLoginAdmin(User user) {
        boolean result = Constants.ROLE_NAME_ADMIN.equals(user.getRole());
        return result;
    }

    public boolean isUserValid(User user) {
        return user.getIdUsers() != null;
    }
}
