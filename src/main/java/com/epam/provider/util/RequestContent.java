package com.epam.provider.util;

import com.epam.provider.dao.factory.entity.ProfileFactory;
import com.epam.provider.dao.factory.entity.TariffFactory;
import com.epam.provider.model.Field;
import com.epam.provider.model.Profile;
import com.epam.provider.model.Tariff;
import com.epam.provider.web.controller.command.Constants;
import com.epam.provider.web.validator.ValidationParameters;

import java.io.Serializable;
import java.util.*;
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

  /**
   * Need to call this method before using other methods
   */
  public static void initSession(HttpServletRequest request) {
    session.set(request.getSession(true));
  }

  public static void setSessionAttribute(String sessionAttrKey, Serializable attrValue) {
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
    Map<Field, String> fieldKeyValue = getParametersFromRequest(
            Field.profileFields, req);
    return (Profile) ProfileFactory.getInstance().getEntity(fieldKeyValue);
  }

  public static Tariff getTariff(HttpServletRequest req) {
    Map<Field, String> fieldKeyValue = getParametersFromRequest(
            Field.tariffFields, req);
    return (Tariff) TariffFactory.getInstance().getEntity(fieldKeyValue);
  }


  public static Map<Field, String> getParametersFromRequest(
      Set<Field> parameterKeys, HttpServletRequest req) {
    Map<Field, String> params = new HashMap<>();
    parameterKeys.forEach(k -> params.put(k, req.getParameter(k.getName())));
    return params;
  }

  public static String getCurrentUserRole() {
    return Optional.ofNullable(session.get()).map(s->s.getAttribute(Constants.PARAM_USER)).
            map(o->((Profile)o).getRole()).orElse(null);
  }


  public static Profile getSessionProfile() {
      return Optional.ofNullable((Profile) session.get().getAttribute(Constants.ATTR_SESSION_PROFILE)).orElse(null);
  }

  public static Map<ValidationParameters, String> getValuesForValidation(
      Set<ValidationParameters> validationParameters, HttpServletRequest req) {
    EnumMap<ValidationParameters, String> parameters = new EnumMap<>(ValidationParameters.class);
    for (ValidationParameters validationParameter : validationParameters) {
      String paramName = validationParameter.getName();
      parameters.put(validationParameter, req.getParameter(paramName));
    }
    return parameters;
  }

}
