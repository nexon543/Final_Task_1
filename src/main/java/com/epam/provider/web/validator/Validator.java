package com.epam.provider.web.validator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by HP on 04.04.2018.
 */
public class Validator {

    private static Logger logger = LogManager.getLogger(Validator.class);

    HashMap<ParameterName, String> errorMessages;

    public boolean isValid(HashMap<String, String> paramsMap) {

        Set<Map.Entry<String, String>> entrySet = paramsMap.entrySet();
        entrySet.forEach(entry -> {
            String current = entry.getKey();
            try {
                ParameterName field = ParameterName.valueOf(current.toUpperCase());
                String regexp = field.getRegexp();

            } catch (IllegalArgumentException e) {
                logger.info("Parameter is not field " + current + " value " + entry.getValue());
            }
        });
        return errorMessages.isEmpty() ? true : false;
    }

    private boolean validate (ParameterName parameter, String value){
        return true;
    }
}
