package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Profile;
import com.epam.provider.service.ProfileService;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.ServiceFactory;
import com.epam.provider.util.SessionRequestContent;
import com.epam.provider.util.resource.ConfigResourceManager;
import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddProfileCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(AddTariffCommand.class);

    ProfileService profileService = ServiceFactory.getProfileService();

    @Override
    public CommandResult execute(HttpServletRequest req) {
        Profile profile = SessionRequestContent.getProfile(req);
        CommandResult res = new CommandResult();
        try {
            if (!profileService.isUserExists(profile.getLogin())) {
                profileService.createProfile(profile);
                req.setAttribute(Constants.PARAM_ERROR_MESSAGE, "User was succesfully added");
                res.setState(CommandResult.CommandResultState.FORWARD_ADMIN);
            } else {
                req.setAttribute(Constants.PARAM_ERROR_MESSAGE, "Such user already exists");
                res.setPage(ConfigResourceManager.getPagePath(ResourceConstants.PAGE_NAME_ADMIN));
                res.setResponseType(CommandResult.ResponseType.FORWARD);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "can't execute add user command");
            res.setState(CommandResult.CommandResultState.REDIRECT_ERROR);
        }
        return res;
    }

}
