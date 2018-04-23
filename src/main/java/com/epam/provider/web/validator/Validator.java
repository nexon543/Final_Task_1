package com.epam.provider.web.validator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * The Class Validator.
 */
public class Validator {

    private static final Logger LOGGER = LogManager.getLogger(Validator.class);

    /**
     * Instantiates a new validator.
     */
    public Validator() {
    }

    /**
     * Checks if is valid.
     *
     * @param paramsMap the params map
     * @return true, if is valid
     */
    public static boolean isValid(Map<ParameterName, String> paramsMap) {
        Set<Map.Entry<ParameterName, String>> entrySet = paramsMap.entrySet();
        boolean isValid=true;
        for (Map.Entry entry: entrySet){
            ParameterName paramName = (ParameterName)entry.getKey();
            String value = (String)entry.getValue();
            String regexp = paramName.getRegexp();
            try {
                isValid = Pattern.matches(regexp, value);
                if (!isValid) {
                    return false;
                }
            } catch (IllegalArgumentException e) {
                LOGGER.error("Parameter is not field " + paramName + " value "+value);
            }
        }
        return isValid;
    }
}
