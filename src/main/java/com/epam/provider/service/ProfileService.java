package com.epam.provider.service;

import com.epam.provider.dao.DaoException;
import com.epam.provider.dao.ProfileDao;
import com.epam.provider.dao.factory.DaoFactory;
import com.epam.provider.model.Profile;
import com.epam.provider.model.User;


public class ProfileService extends Service {
    ProfileDao profileDAO =  DaoFactory.getProfileDao();

    public ProfileService() {
    }

    public Profile findUserProfile(User user) throws ServiceException {
        Profile profile;
        try {
            profile = profileDAO.findById(user.getIdProfiles());
        } catch (DaoException e) {
            throw new ServiceException(ServiceException.MESS_SEARCH_PROFILE_ERROR);
        }
        return profile;
    }
}
