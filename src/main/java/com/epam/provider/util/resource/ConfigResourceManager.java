package com.epam.provider.util.resource;

import java.util.ResourceBundle;

/**
 * @author Gleb Aksenov
 */
public class ConfigResourceManager {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(ResourceConstants.RESOURCE_PATH_CONFIG);

    private ConfigResourceManager() {

    }

    public static String getPagePath(String pageName) {
        return getProperty(ResourceConstants.PAGE_PATH_PREFIX + pageName);
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
