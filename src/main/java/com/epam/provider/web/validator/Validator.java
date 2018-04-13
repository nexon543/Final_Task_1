package com.epam.provider.web.validator;

import com.epam.provider.web.controller.command.Constants;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
     * The errors.
     */
    private HashMap<String, String> errorMessages;

    /**
     * Instantiates a new validator.
     */
    public Validator() {
    }

    public static boolean validateAdmin(String role) {
        return Constants.ROLE_NAME_ADMIN.equals(role);
    }

    public static boolean validateClient(String role) {
        return Constants.ROLE_NAME_CLIENT.equals(role);
    }

    /**
     * Checks if is valid.
     *
     * @param paramsMap the params map
     * @return true, if is valid
     */
    public boolean isValid(HashMap<String, String> paramsMap) {
        errorMessages = new HashMap<>();
        Set<Map.Entry<String, String>> entrySet = paramsMap.entrySet();
        entrySet.forEach(entry -> {
            String current = entry.getKey();
            try {
                ParameterName field = ParameterName.valueOf(current.toUpperCase());
                String regexp = field.getRegexp();
                if (!validate(field, regexp)) {
                    errorMessages.put(field.getName(), "value is incorrect");
                }
            } catch (IllegalArgumentException e) {
                LOGGER.error("Parameter is not field " + current + " value " + entry.getValue());
            }
        });
        return errorMessages.isEmpty() ? true : false;
    }

    /**
     * Validate not required.
     *
     * @param parameter contains the name and regexp
     * @param value     the value
     * @return true, if successful
     */
    private boolean validate(ParameterName parameter, String value) {
        return Pattern.matches(parameter.getRegexp(), value);
    }
}
