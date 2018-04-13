package com.epam.provider.util.resource;

import java.util.ResourceBundle;

/**
 * @author Gleb Aksenov
 */
public class MessageResourceManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(ResourceConstants.RESOURCE_PATH_MESSAGE);

    private MessageResourceManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
