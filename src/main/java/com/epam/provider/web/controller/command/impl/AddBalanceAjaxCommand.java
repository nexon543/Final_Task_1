package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Field;
import com.epam.provider.model.Profile;
import com.epam.provider.service.ProfileService;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.impl.ProfileServiceImpl;
import com.epam.provider.util.RequestContent;
import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.util.resource.ResourceManager;
import com.epam.provider.web.controller.command.ActionType;
import com.epam.provider.web.controller.command.AjaxActionCommand;
import com.epam.provider.web.controller.command.Constants;
import com.epam.provider.web.validator.ValidationParameters;
import com.epam.provider.web.validator.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class AddBalanceAjaxCommand implements AjaxActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(AddBalanceAjaxCommand.class);
    private ProfileService profileService = new ProfileServiceImpl();

    @Override
    public JSONObject execute(HttpServletRequest req) {
        JSONObject result = new JSONObject();
        RequestContent.initSession(req);
        boolean isValid = Validator.isValid(RequestContent
                .getValuesForValidation(ValidationParameters.getParamSet(ActionType.ADD_BALANCE), req));
        if (isValid) {
            Double amount = Double.parseDouble(req.getParameter(Field.PROFILE_BALANCE.getName()));
            tryToAddBalanceAndSetResult(result, amount, Constants.DEFAULT_LANG);
        } else {
            result.put(Constants.ATTR_IS_SUCCESS, Constants.VALUE_IS_SUCCESS_NO);
            result.put(Constants.ATTR_ERROR_MESSAGE,
                    ResourceManager.getMessage(ResourceConstants.M_INCORRECT_VALUE, "en"));
        }
        return result;
    }

    private void tryToAddBalanceAndSetResult(JSONObject result, Double amount, String lang) {

        Profile profile = (Profile) RequestContent.getSessionAttribute(Constants.ATTR_SESSION_PROFILE);
        Integer profileId = Optional.ofNullable(profile).map((p) -> p.getProfileId()).orElse(null);
        try {
            if (profileService.addBalance(amount, profileId)) {
                profile.setBalance(profile.getBalance() + amount);
                result.put(Constants.ATTR_SUCCESS_MESSAGE,
                        ResourceManager.getMessage(ResourceConstants.M_SUCCESS_ADD_BALANCE, Constants.DEFAULT_LANG));
                result.put(Constants.ATTR_IS_SUCCESS, Constants.VALUE_IS_SUCCESS_YES);
            } else {
                result.put(Constants.ATTR_IS_SUCCESS, Constants.VALUE_IS_SUCCESS_NO);
                result.put(Constants.ATTR_ERROR_MESSAGE,
                        ResourceManager.getMessage(ResourceConstants.M_INCORRECT_VALUE, Constants.DEFAULT_LANG));
            }
        } catch (ServiceException e) {
            result.put(Constants.ATTR_IS_SUCCESS, Constants.VALUE_IS_SUCCESS_NO);
            result.put(Constants.ATTR_ERROR_MESSAGE,
                    ResourceManager.getMessage(ResourceConstants.M_FAILD, Constants.DEFAULT_LANG));
            LOGGER.log(Level.ERROR, e.getStackTrace());
        }
    }
}
