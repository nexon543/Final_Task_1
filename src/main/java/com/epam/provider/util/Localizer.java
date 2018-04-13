package com.epam.provider.util;

import com.epam.provider.util.resource.ResourceConstants;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Gleb Aksenov
 */
public class Localizer {
    ResourceBundle bundle;

    public Localizer() {
    }

    private void setBundle(String locale) {
        if (locale == null) {
            bundle = ResourceBundle.getBundle(ResourceConstants.RESOURCE_PATH_LOCALIZATION);
        } else {
            Locale localeObj = new Locale(locale);
            bundle = ResourceBundle.getBundle(ResourceConstants.RESOURCE_PATH_LOCALIZATION, localeObj);
        }
    }

    public String getLocalizedText(String locale, String key) throws UnsupportedEncodingException {
        setBundle(locale);
        String value = bundle.getString(key);
        return value;
    }
}
