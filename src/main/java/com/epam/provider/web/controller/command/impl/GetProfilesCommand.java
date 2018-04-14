package com.epam.provider.web.controller.command.impl;

import com.epam.provider.service.ProfileService;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.ServiceFactory;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class GetProfilesCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(GetProfilesCommand.class);
    ProfileService profileService = ServiceFactory.getProfileService();

    public GetProfilesCommand() {
    }

    @Override
    public CommandResult execute(HttpServletRequest req) {
        CommandResult result = new CommandResult(CommandResult.CommandResultState.FORWARD_ADMIN);
        // HttpSession session = req.getSession();
        try {
            req.setAttribute(Constants.PARAM_USERS, profileService.findAll());
            req.setAttribute(Constants.PARAM_ADMIN_STATUS, Constants.ADMIN_PAGE_STATUS_SHOW_USERS);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "can't execute 'get all users' command");
            result.setState(CommandResult.CommandResultState.REDIRECT_ERROR);
        }
        return result;
    }
}
