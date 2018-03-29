package com.epam.provider.util.resource;

import java.util.ResourceBundle;

/**
 * Created by HP on 28.03.2018.
 */
public class DatabaseResourceManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(ResourceConstants.RESOURCE_PATH_DATABASE);

    private DatabaseResourceManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
