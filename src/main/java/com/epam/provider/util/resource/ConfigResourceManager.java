package com.epam.provider.util.resource;

import java.util.ResourceBundle;

/**
 * Created by HP on 28.03.2018.
 */
public class ConfigResourceManager {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(ResourceConstants.RESOURCE_PATH_CONFIG);

    private ConfigResourceManager() {
    }

    public static String getPagePath(String pageName) {
        return getProperty("pages.path." + pageName);
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
