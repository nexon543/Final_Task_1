package com.epam.provider.service;

import com.epam.provider.service.impl.ProfileServiceImpl;
import com.epam.provider.service.impl.TariffServiceImpl;

/**
 * @author Gleb Aksenov
 */
public abstract class ServiceFactory {

    private static ProfileService profileService = new ProfileServiceImpl();
    private static TariffService tariffService = new TariffServiceImpl();


    public static ProfileService getProfileService() {
        return profileService;
    }

    public static TariffService getTariffService() {
        return tariffService;
    }

}
