package com.epam.provider.web.controller.command.impl;

import com.epam.provider.service.ProfileService;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.ServiceFactory;
import com.epam.provider.util.resource.ConfigResourceManager;
import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetProfilesCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(GetProfilesCommand.class);
    ProfileService profileService = ServiceFactory.getProfileService();

    @Override
    public CommandResult execute(HttpServletRequest req) {
        CommandResult result = new CommandResult(CommandResult.ResponseType.REDIRECT, ConfigResourceManager.getPagePath(ResourceConstants.PAGE_NAME_LOGIN_REDIRECT));
        HttpSession session = req.getSession();
        try {
            session.setAttribute(Constants.PARAM_USERS, profileService.findAll());
            session.setAttribute(Constants.PARAM_ADMIN_STATUS, Constants.ADMIN_PAGE_STATUS_SHOW_USERS);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "can't execute 'get all users' command");
            result.setState(CommandResult.CommandResultState.REDIRECT_ERROR);
        }
        return result;
    }
}
