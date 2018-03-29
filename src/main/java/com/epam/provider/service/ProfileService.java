package com.epam.provider.service;

import com.epam.provider.dao.DAOManager;
import com.epam.provider.dao.DAOType;
import com.epam.provider.dao.impl.ProfilesDAO;
import com.epam.provider.dao.impl.UsersDAO;
import com.epam.provider.model.Profile;
import com.epam.provider.model.User;

import java.sql.SQLException;

/**
 * Created by HP on 29.03.2018.
 */
public class ProfileService extends Service{
    private ProfilesDAO profilesDAO;

    public ProfileService(DAOManager daoManager){
        profilesDAO = (ProfilesDAO) daoManager.getDAO(DAOType.PROFILES);
    }

    public Profile findUserProfile(User user) throws ServiceException {
        Profile profile;
        try {
            profile=profilesDAO.findEntityById(user.getIdProfiles());
        } catch (SQLException e) {
            throw new ServiceException();
        }
        return profile;
    }
}
