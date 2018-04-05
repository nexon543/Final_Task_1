package com.epam.provider.service;

import com.epam.provider.dao.DAOException;
import com.epam.provider.dao.ProfileDAO;
import com.epam.provider.dao.factory.DAOFactory;
import com.epam.provider.dao.DAOType;
import com.epam.provider.model.Profile;
import com.epam.provider.model.User;

/**
 * Created by HP on 29.03.2018.
 */
public class ProfileService extends Service {
    ProfileDAO profileDAO = (ProfileDAO) DAOFactory.getDAO(DAOType.PROFILES);

    public ProfileService() {
    }

    public Profile findUserProfile(User user) throws ServiceException {
        Profile profile;
        try {
            profile = profileDAO.findEntityById(user.getIdProfiles());
        } catch (DAOException e) {
            throw new ServiceException(ServiceException.MESS_SEARCH_PROFILE_ERROR);
        }
        return profile;
    }
}
