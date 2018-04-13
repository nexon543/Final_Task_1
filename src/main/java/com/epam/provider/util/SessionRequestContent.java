package com.epam.provider.util;

import com.epam.provider.model.Profile;
import com.epam.provider.model.Tariff;
import com.epam.provider.web.controller.command.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * @author Gleb Aksenov
 */
public class SessionRequestContent {
    private HashMap<String, String> requestAttributes;
    private HashMap<String, String[]> requestParameters;
    private HttpSession session;
    public SessionRequestContent(HttpServletRequest req) {
        //extractValues(req);
        session = req.getSession();
    }

    public Profile getUser() {
        if (session != null) {
            return (Profile) session.getAttribute(Constants.PARAM_USER);
        }
        return null;
    }

    public Object getAttribute(String attributeName) {
        return requestAttributes.get(attributeName);
    }

    public String[] getParameter(String parameterName) {
        return requestParameters.get(parameterName);
    }

    public void extractValues(HttpServletRequest req) {
        requestAttributes = new HashMap<>();
        Enumeration<String> attributeNames = req.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attrName = attributeNames.nextElement();
            String attrValue = (String) req.getAttribute(attrName);
            requestAttributes.put(attrName, attrValue);
        }
        Enumeration<String> parameterNames = req.getParameterNames();
        requestParameters = (HashMap<String, String[]>) req.getParameterMap();
    }

    public void insertAttribute(HttpServletRequest req) {
        requestAttributes.forEach((k, v) -> req.setAttribute(k, v));
    }


    public HashMap<String, String> getRequestAttributes() {
        return requestAttributes;
    }

    public SessionRequestContent setRequestAttributes(HashMap<String, String> requestAttributes) {
        this.requestAttributes = requestAttributes;
        return this;
    }

    public HashMap<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public SessionRequestContent setRequestParameters(HashMap<String, String[]> requestParameters) {
        this.requestParameters = requestParameters;
        return this;
    }

    public static Profile getProfile(HttpServletRequest req) {
        Profile profile = new Profile();
        Date date = new Date(new java.util.Date().getTime());
        profile.setBalance(Double.parseDouble(req.getParameter("balance")));
        profile.setFirstName(req.getParameter("firstName"));
        profile.setIdTariffs(Integer.parseInt(req.getParameter("tariffId")));
        profile.setPassport(req.getParameter("passport"));
        profile.setSecondName(req.getParameter("secondName"));
        profile.setRegisterDate(date);
        profile.setLogin(req.getParameter("name"));
        profile.setPassword(req.getParameter("password"));
        profile.setRole(req.getParameter("role"));
        String profileId = req.getParameter("profileId");
        if (profileId != null && !"".equals(profileId)) {
            profile.setProfileId(Integer.parseInt(profileId));
        }
        return profile;
    }

    public static String getUserRole(HttpServletRequest req) {
        HttpSession sessdion = req.getSession();
        Profile profile = (Profile) sessdion.getAttribute(Constants.PARAM_USER);
        if (profile == null) {
            return "";
        }
        return profile.getRole();
    }

    public static Tariff getTariff(HttpServletRequest req) {
        Tariff newTariff = new Tariff();
        String id = req.getParameter("id");
        if (!"".equals(id)) {
            newTariff.setTariffId(Integer.parseInt(id));
        }
        newTariff.setPrice(Integer.parseInt(req.getParameter("price")));
        newTariff.setRecievingSpeed(Integer.parseInt(req.getParameter("recievingSpeed")));
        newTariff.setTransferSpeed(Integer.parseInt(req.getParameter("transferSpeed")));
        newTariff.setDescription(req.getParameter("description"));
        newTariff.setName(req.getParameter("name"));
        newTariff.setLang(req.getParameter("lang"));
        return newTariff;
    }
}
