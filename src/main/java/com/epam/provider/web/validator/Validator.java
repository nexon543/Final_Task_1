package com.epam.provider.web.validator;

import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;


/**
 * The Class Validator.
 */
public class Validator {

  private Validator() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Checks if is valid.
   *
   * @param paramsMap the params map
   * @return true, if is valid
   */
  public static boolean isValid(Map<ValidationParameters, String> paramsMap) {
    return paramsMap.entrySet().stream().allMatch((entry) ->
        Optional.ofNullable(entry.getValue())
            .map(v -> Pattern.matches((entry.getKey()).getRegexp(), v)).orElse(false));
  }
}
