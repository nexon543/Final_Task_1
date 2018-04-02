package com.epam.provider.service;

import com.epam.provider.dao.DAOFactory;
import com.epam.provider.dao.DAOType;
import com.epam.provider.dao.impl.ProfilesDAO;
import com.epam.provider.model.Profile;
import com.epam.provider.model.User;

import java.sql.SQLException;

/**
 * Created by HP on 29.03.2018.
 */
public class ProfileService extends Service {
    ProfilesDAO profilesDAO = (ProfilesDAO) DAOFactory.getDAO(DAOType.PROFILES);

    public ProfileService() {
    }

    public Profile findUserProfile(User user) throws ServiceException {
        Profile profile;
        daoManager.openConnection();
        daoManager.setDAOConnection(profilesDAO);
        try {
            profile = profilesDAO.findEntityById(user.getIdProfiles());
        } catch (SQLException e) {
            throw new ServiceException(ServiceException.MESS_SEARCH_PROFILE_ERROR);
        } finally {
            daoManager.closeConnection();
        }
        return profile;
    }
}
