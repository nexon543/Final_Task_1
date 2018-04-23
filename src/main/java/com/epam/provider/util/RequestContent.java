package com.epam.provider.util;

import com.epam.provider.model.Profile;
import com.epam.provider.model.Tariff;
import com.epam.provider.web.controller.command.Constants;
import com.epam.provider.web.validator.ParameterName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.*;

/**
 * @author Gleb Aksenov
 */
public class RequestContent {

    public RequestContent() {
    }


    public static Profile getProfile(HttpServletRequest req) {
        Profile profile = new Profile();
        Date date = new Date(new java.util.Date().getTime());
        profile.setBalance(Double.parseDouble(req.getParameter(Constants.PARAM_BALANCE)));
        profile.setFirstName(req.getParameter(Constants.PARAM_FIRST_NAME));
        String paramIdTariff=req.getParameter(Constants.PARAM_TARIFFS_ID);
        profile.setIdTariffs(Integer.parseInt(paramIdTariff));
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

    public static String getCurrentUserRole(HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        Profile profile = (Profile) session.getAttribute(Constants.PARAM_USER);
        if (profile == null) {
            return null;
        }
        return profile.getRole();
    }

    public static Tariff getTariff(HttpServletRequest req) {
        Tariff newTariff = new Tariff();
        HttpSession session = req.getSession();
        String lang=(String)session.getAttribute(Constants.PARAM_LOCAL);
        String id = req.getParameter("id");
        if (id!=null && !"".equals(id)) {
            newTariff.setTariffId(Integer.parseInt(id));
        }
        newTariff.setName(req.getParameter(Constants.PARAM_TARIFF_NAME));
        newTariff.setPrice(Integer.parseInt(req.getParameter(Constants.PARAM_PRICE)));
        newTariff.setReceivingSpeed(Integer.parseInt(req.getParameter(Constants.PARAM_RECEIVING_SPEED)));
        newTariff.setTransferSpeed(Integer.parseInt(req.getParameter(Constants.PARAM_TRANSFER_SPEED)));
        newTariff.setDescription(req.getParameter(Constants.PARAM_DESCRIPTION));
        newTariff.setName(req.getParameter(Constants.PARAM_TARIFF_NAME));
        req.getParameter(Constants.PARAM_LOCAL);
        newTariff.setLang(lang);
        return newTariff;
    }

    public static Profile getSessionProfile(HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (session != null) {
            return (Profile) session.getAttribute(Constants.ATTR_SESSION_PROFILE);
        }
        return null;
    }

    public static EnumMap<ParameterName, String> getValuesForValidation(Set<ParameterName> parameterNames, HttpServletRequest req) {
        EnumMap<ParameterName, String> parameters=new EnumMap<>(ParameterName.class);
        for (ParameterName parameterName:parameterNames){
            String paramName=parameterName.getName();
            parameters.put (parameterName, req.getParameter(paramName));
        }
        return parameters;
    }

}
