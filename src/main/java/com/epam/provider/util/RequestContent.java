package com.epam.provider.util;

import com.epam.provider.dao.factory.entity.ProfileFactory;
import com.epam.provider.model.Profile;
import com.epam.provider.model.Tariff;
import com.epam.provider.model.fields.ProfileField;
import com.epam.provider.model.fields.TableFieldName;
import com.epam.provider.web.controller.command.Constants;
import com.epam.provider.web.validator.ValidationParameters;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Gleb Aksenov
 */
public class RequestContent {

  private static ThreadLocal<HttpSession> session = new ThreadLocal<>();

  private RequestContent() {
    throw new IllegalStateException("Utility class");
  }

  public static String getCurrentLang() {
    return (String) getSessionAttribute(Constants.PARAM_LOCAL);
  }

  public static void init(HttpServletRequest request) {
    session.set(request.getSession(true));
  }

  public static void setSessionAttribute(String sessionAttrKey, Object attrValue) {
    session.get().setAttribute(sessionAttrKey, attrValue);
  }

  public static Object getSessionAttribute(String key) {
    return session.get().getAttribute(key);
  }

  public static void setMessage(String messageKey, String messageContent) {
    setSessionAttribute(messageKey, messageContent);
    setSessionAttribute(Constants.PARAM_DISPLAY_MESSAGE, Constants.VALUE_DISPLAY_MESSAGE_YES);
  }

  public static Profile getProfile(HttpServletRequest req) {
    Map<TableFieldName, String> fieldKeyValue = getParametersFromRequest(
       new HashSet<>(Arrays.asList(ProfileField.values())), req);
    return (Profile) ProfileFactory.getInstance().getEntity(fieldKeyValue);
  }

  public static Map<TableFieldName, String> getParametersFromRequest(
      Set<TableFieldName> parameterKeys, HttpServletRequest req) {
    Map<TableFieldName, String> params = new HashMap<>();
    parameterKeys.forEach(k -> params.put(k, req.getParameter(k.getName())));
    return params;
  }

  public static String getCurrentUserRole() {
    return Optional.ofNullable(session.get().getAttribute(Constants.PARAM_USER)).map(o->((Profile)o).getRole()).orElseGet(null);
  }

  public static Tariff getTariff(HttpServletRequest req) {
    Tariff newTariff = new Tariff();
    return newTariff;
  }

  public static Profile getSessionProfile(HttpServletRequest req) {
    if (session != null) {
      return (Profile) session.get().getAttribute(Constants.ATTR_SESSION_PROFILE);
    }
    return null;
  }

  public static EnumMap<ValidationParameters, String> getValuesForValidation(
      Set<ValidationParameters> validationParameters, HttpServletRequest req) {
    EnumMap<ValidationParameters, String> parameters = new EnumMap<>(ValidationParameters.class);
    for (ValidationParameters validationParameter : validationParameters) {
      String paramName = validationParameter.getName();
      parameters.put(validationParameter, req.getParameter(paramName));
    }
    return parameters;
  }

}
