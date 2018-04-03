package com.epam.provider.controller.command.impl;

import com.epam.provider.controller.command.ActionCommand;
import com.epam.provider.controller.command.ActionType;
import com.epam.provider.controller.command.CommandResult;
import com.epam.provider.controller.command.Constants;
import com.epam.provider.model.Profile;
import com.epam.provider.model.User;
import com.epam.provider.service.ProfileService;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.UserService;
import com.epam.provider.util.resource.ConfigResourceManager;
import com.epam.provider.util.resource.MessageResourceManager;
import com.epam.provider.util.resource.ResourceConstants;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Created by HP on 27.03.2018.
 */
public class LoginCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger(LoginCommand.class);

    private UserService userService;
    private ProfileService profileService;

    @Override
    public CommandResult execute(HttpServletRequest req) {
        String page;
        String login = req.getParameter(Constants.PARAM_NAME_LOGIN);
        String pass = req.getParameter(Constants.PARAM_NAME_PASSWORD);
        userService = new UserService();
        profileService = new ProfileService();
        CommandResult.ResponseType respType= CommandResult.ResponseType.REDIRECT;
        try {
            User user = userService.findUser(login, pass);
            page = getPageForUser(user);
            if (userService.isUserValid(user)) {
                setSessionForUser(user, req);
            } else{
                req.setAttribute(Constants.PARAM_NAME_ERROR_LOGIN, "Invalid login or pass" );
                respType= CommandResult.ResponseType.FORWARD;
            }
        } catch (Exception e) {
            page = ConfigResourceManager.getPagePath(ConfigResourceManager.getPagePath(ResourceConstants.PAGE_NAME_ERROR));
            logger.log(Level.ERROR, MessageResourceManager.getProperty(ResourceConstants.MESSAGE_KEY_ERROR_LOGIN));
        }
        req.getRequestDispatcher("/Controller?command="+ ActionType.GET_TARIFFS);
        CommandResult commandResult = new CommandResult(respType, page);
        return commandResult;
    }

    private String getPageForUser(User user) {
        if (userService.validateLoginAdmin(user)) {
            return "/Controller?command=get_tariffs";
            //return ConfigResourceManager.getPagePath(ResourceConstants.PAGE_NAME_TARIFF);
        }
        if (userService.validateLoginUser(user)) {
            return ConfigResourceManager.getPagePath(ResourceConstants.PAGE_NAME_CLIENT);
        }
        return ConfigResourceManager.getPagePath(ResourceConstants.PAGE_NAME_LOGIN);
    }

    private void setSessionForUser(User user, HttpServletRequest req) throws ServiceException {
        HttpSession session = req.getSession();
        if (userService.isUserValid(user)) {
            Profile profile = profileService.findUserProfile(user);
            session.setAttribute(Constants.PARAM_NAME_PROFILE, profile);
            session.setAttribute(Constants.PARAM_NAME_USER, user);
        }
    }
}