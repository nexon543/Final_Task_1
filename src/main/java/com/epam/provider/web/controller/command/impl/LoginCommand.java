package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Profile;
import com.epam.provider.model.User;
import com.epam.provider.service.ProfileService;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.UserService;
import com.epam.provider.util.resource.ConfigResourceManager;
import com.epam.provider.util.resource.MessageResourceManager;
import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.ActionType;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class LoginCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    private UserService userService;
    private ProfileService profileService;

    @Override
    public CommandResult execute(HttpServletRequest req) {
        String page;
        String login = req.getParameter(Constants.PARAM_NAME_LOGIN);
        String pass = req.getParameter(Constants.PARAM_NAME_PASSWORD);
        userService = new UserService();
        profileService = new ProfileService();
        CommandResult.ResponseType respType = CommandResult.ResponseType.REDIRECT;
        try {
            User user = userService.findUser(login, pass);
            page = getPageForUser(user);
            setSessionForUser(user, req);
        } catch (Exception e) {
            page = ConfigResourceManager.getPagePath(ResourceConstants.PAGE_NAME_ERROR);
            LOGGER.log(Level.ERROR, MessageResourceManager.getProperty(ResourceConstants.MESSAGE_KEY_ERROR_LOGIN));
        }
        CommandResult commandResult = new CommandResult(respType, page);
        return commandResult;
    }

    private String getPageForUser(User user) {
        if (Constants.ROLE_NAME_ADMIN.equals(user.getRole())) {
            return ConfigResourceManager.getPagePath(ResourceConstants.PAGE_NAME_ADMIN);
        }
        if (Constants.ROLE_NAME_CLIENT.equals(user.getRole())) {
            return ConfigResourceManager.getPagePath(ResourceConstants.PAGE_NAME_CLIENT);
        }
        return ConfigResourceManager.getPagePath(ResourceConstants.PAGE_NAME_LOGIN);
    }

    private void setSessionForUser(User user, HttpServletRequest req) throws ServiceException {
        HttpSession session = req.getSession();
        Profile profile = profileService.findUserProfile(user);
        session.setAttribute(Constants.PARAM_NAME_PROFILE, profile);
        session.setAttribute(Constants.PARAM_NAME_USER, user);
    }
}
