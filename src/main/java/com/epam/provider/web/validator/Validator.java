package com.epam.provider.web.validator;

import java.util.Map;
import java.util.regex.Pattern;


/**
 * The Class Validator.
 */
public class Validator {

    private Validator(){
        throw new IllegalStateException("Utility class");
    }

    /**
     * Checks if is valid.
     *
     * @param paramsMap the params map
     * @return true, if is valid
     */
    public static boolean isValid(Map<ParameterName, String> paramsMap) {
        return paramsMap.entrySet().stream().allMatch((entry) ->
                Pattern.matches((entry.getKey()).getRegexp(), entry.getValue()));
    }
}
