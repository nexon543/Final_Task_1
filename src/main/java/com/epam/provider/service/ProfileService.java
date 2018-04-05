package com.epam.provider.service;

import com.epam.provider.dao.DAOException;
import com.epam.provider.dao.ProfileDao;
import com.epam.provider.dao.factory.DaoFactory;
import com.epam.provider.model.Profile;
import com.epam.provider.model.User;

/**
 * Created by HP on 29.03.2018.
 */
public class ProfileService extends Service {
    ProfileDao profileDAO =  DaoFactory.getProfileDao();

    public ProfileService() {
    }

    public Profile findUserProfile(User user) throws ServiceException {
        Profile profile;
        try {
            profile = profileDAO.findById(user.getIdProfiles());
        } catch (DAOException e) {
            throw new ServiceException(ServiceException.MESS_SEARCH_PROFILE_ERROR);
        }
        return profile;
    }
}
