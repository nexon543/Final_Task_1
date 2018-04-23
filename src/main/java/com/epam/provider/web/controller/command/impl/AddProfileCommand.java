package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Profile;
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
import com.epam.provider.web.validator.ParameterName;
import com.epam.provider.web.validator.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddProfileCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(AddTariffCommand.class);

    ProfileService profileService = ServiceFactory.getProfileService();

    @Override
    public CommandResult execute(HttpServletRequest req) {

        CommandResult res = new CommandResult();
        boolean isValid = Validator.isValid(RequestContent.getValuesForValidation(ParameterName.getParamSet(ActionType.ADD_PROFILE), req));
        if (!isValid) {
            try {
                res.setState(CommandResult.CommandResultState.CONTROLLER_GET_PROFILE);
                Profile profile = RequestContent.getProfile(req);
                if (!profileService.isUserExists(profile.getLogin())) {
                    profileService.createProfile(profile);
                    res.appendParamToRedirect(Constants.PARAM_SUCCESS_MESSAGE, ResourceManager.getMessage(ResourceConstants.M_SUCCESS));
                } else {
                    res.appendParamToRedirect(Constants.PARAM_ERROR_MESSAGE, ResourceManager.getMessage(ResourceConstants.M_PROFILE_EXISTS));
                }

            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, ResourceManager.getMessage(ResourceConstants.M_FAILD));
                res.setState(CommandResult.CommandResultState.REDIRECT_ERROR);
            }
        } else {
            res.setState(CommandResult.CommandResultState.FORWARD_ADMIN);
            req.setAttribute(Constants.PARAM_ERROR_MESSAGE, ResourceManager.getMessage(ResourceConstants.M_INCORRECT_VALUE));
        }
        return res;
    }

}
