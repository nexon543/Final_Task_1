package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Profile;
import com.epam.provider.model.fields.ProfileField;
import com.epam.provider.service.ProfileService;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.ServiceFactory;
import com.epam.provider.util.RequestContent;
import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.util.resource.ResourceManager;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.ActionType;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;
import com.epam.provider.web.validator.ValidationParameters;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.EnumMap;

public class UpdateProfileCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(UpdateTariffCommand.class);
    private ProfileService profileService = ServiceFactory.getProfileService();


    /**
     * Updates profile if login is unique
     *
     * @param req is needed to get data and set session attributes
     * @return {@link CommandResult}
     */

    @Override
    public CommandResult execute(HttpServletRequest req) {

        Profile updatedProfile = RequestContent.getProfile(req);
        CommandResult res = new CommandResult();
        RequestContent.init(req);
        String lang=RequestContent.getCurrentLang();
        EnumMap<ValidationParameters,String> parametersForValidation=RequestContent
                .getValuesForValidation(ValidationParameters.getParamSet(ActionType.UPDATE_PROFILE),req);
        try{
                boolean isUpdated = profileService.updateUser(updatedProfile, parametersForValidation);
                if (isUpdated) {
                    RequestContent.setMessage(Constants.ATTR_SUCCESS_MESSAGE,
                            ResourceManager.getMessage(ResourceConstants.M_SUCCESS_UPDATE_PROFILE,lang));
                    res.setState(CommandResult.CommandResultState.CONTROLLER_GET_PROFILE);
                } else {
                    res.setState(CommandResult.CommandResultState.GET_UPDATE_PAGE);
                    RequestContent.setMessage(Constants.ATTR_ERROR_MESSAGE, ResourceManager.getMessage(ResourceConstants.M_PROFILE_EXISTS,lang));
                    res.appendParamToRedirect(Constants.PARAM_UPDATED_ENTITY, Constants.VALUE_UPDATED_ENTITY_PROFILE);
                    res.appendParamToRedirect(ProfileField.ID.getName(),
                            updatedProfile.getProfileId().toString());
                    LOGGER.log(Level.INFO, "attempt to update profile with invalid data");
                }
            } catch (ServiceException e) {
                res.appendParamToRedirect(Constants.ATTR_ERROR_MESSAGE, ResourceManager.getMessage(ResourceConstants.M_ERROR_UPDATE_PROFILE, lang));
                LOGGER.log(Level.ERROR, e.getStackTrace());
            }
        return res;
    }
}
