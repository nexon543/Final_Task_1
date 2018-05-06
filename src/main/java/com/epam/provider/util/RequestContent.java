package com.epam.provider.util;

import com.epam.provider.model.Profile;
import com.epam.provider.model.Tariff;
import com.epam.provider.web.controller.command.Constants;
import com.epam.provider.web.validator.ParameterName;
import java.sql.Date;
import java.util.EnumMap;
import java.util.Set;
import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Gleb Aksenov
 */
public class RequestContent {

  private static ThreadLocal<HttpSession> session=new ThreadLocal<>();


  public static String getCurrentLang(){
    return (String)getSessionAttribute(Constants.PARAM_LOCAL);
  }
  public static void init(HttpServletRequest req){
    session.set(req.getSession(true));
  }

  public static void setSessionAttribute(String sessionAttrKey, Object attrValue){
    session.get().setAttribute(sessionAttrKey, attrValue);
  }
  public static Object getSessionAttribute(String key){
    return session.get().getAttribute(key);
  }
  public static void setMessage(String messageKey, String messageContent){
    setSessionAttribute(messageKey, messageContent);
    setSessionAttribute(Constants.PARAM_DISPLAY_MESSAGE, Constants.VALUE_DISPLAY_MESSAGE_YES);
  }
  public static Profile getProfile(HttpServletRequest req) {
    Profile profile = new Profile();
    Date date = new Date(new java.util.Date().getTime());
    String balance = req.getParameter(Constants.PARAM_BALANCE);
    if (balance != null && balance != "") {
      profile.setBalance(Double.parseDouble(balance));
    }
    profile.setFirstName(req.getParameter(Constants.PARAM_FIRST_NAME));
    String paramIdTariff = req.getParameter(Constants.PARAM_TARIFFS_ID);
    if (paramIdTariff != null && paramIdTariff != "") {
      profile.setIdTariffs(Integer.parseInt(paramIdTariff));
    }
    profile.setPassport(req.getParameter(Constants.PARAM_PASSPORT));
    profile.setSecondName(req.getParameter(Constants.PARAM_SECOND_NAME));
    profile.setRegisterDate(date);
    profile.setLogin(req.getParameter(Constants.PARAM_LOGIN));
    profile.setPassword(req.getParameter(Constants.PARAM_PASSWORD));
    profile.setRole(req.getParameter(Constants.PARAM_ROLE));
    String profileId = req.getParameter(Constants.PARAM_PROFILE_ID);
    if (profileId != null && !"".equals(profileId)) {
      profile.setProfileId(Integer.parseInt(profileId));
    }
    return profile;
  }

  public static String getCurrentUserRole() {
    Profile profile = (Profile) session.get().getAttribute(Constants.PARAM_USER);
    if (profile == null) {
      return null;
    }
    return profile.getRole();
  }

  public static Tariff getTariff(HttpServletRequest req) {
    Tariff newTariff = new Tariff();
    String lang = (String) session.get().getAttribute(Constants.PARAM_LOCAL);
    String id = req.getParameter("id");
    if (id != null && !"".equals(id)) {
      newTariff.setTariffId(Integer.parseInt(id));
    }
    newTariff.setName(req.getParameter(Constants.PARAM_TARIFF_NAME));
    newTariff.setPrice(Integer.parseInt(req.getParameter(Constants.PARAM_PRICE)));
    newTariff
        .setReceivingSpeed(Integer.parseInt(req.getParameter(Constants.PARAM_RECEIVING_SPEED)));
    newTariff.setTransferSpeed(Integer.parseInt(req.getParameter(Constants.PARAM_TRANSFER_SPEED)));
    newTariff.setDescription(req.getParameter(Constants.PARAM_DESCRIPTION));
    newTariff.setName(req.getParameter(Constants.PARAM_TARIFF_NAME));
    req.getParameter(Constants.PARAM_LOCAL);
    newTariff.setLang(lang);
    return newTariff;
  }

  public static Profile getSessionProfile(HttpServletRequest req) {
    if (session != null) {
      return (Profile) session.get().getAttribute(Constants.ATTR_SESSION_PROFILE);
    }
    return null;
  }

  public static EnumMap<ParameterName, String> getValuesForValidation(
      Set<ParameterName> parameterNames, HttpServletRequest req) {
    EnumMap<ParameterName, String> parameters = new EnumMap<>(ParameterName.class);
    for (ParameterName parameterName : parameterNames) {
      String paramName = parameterName.getName();
      parameters.put(parameterName, req.getParameter(paramName));
    }
    return parameters;
  }

}
