package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Profile;
import com.epam.provider.model.Tariff;
import com.epam.provider.service.ProfileService;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.ServiceFactory;
import com.epam.provider.service.TariffService;
import com.epam.provider.service.impl.TariffServiceImpl;
import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.util.resource.ResourceManager;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This command is used to verify the user
 *
 * @author Gleb Aksenov
 * {@link ActionCommand}  invokes method execute()
 */
public class LoginCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    private TariffService tariffService = new TariffServiceImpl();
    private ProfileService profileService;

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(HttpServletRequest req) {
        String login = req.getParameter(Constants.PARAM_LOGIN);
        String pass = req.getParameter(Constants.PARAM_PASSWORD);
        profileService = ServiceFactory.getProfileService();
        CommandResult commandResult = new CommandResult(CommandResult.CommandResultState.REDIRECT_LOGIN);

        try {
            Profile profile = profileService.findUser(login, pass);
            if (profile.getProfileId() != null) {
                setSessionForUser(profile, req);
            } else {
                req.setAttribute(Constants.PARAM_ERROR_MESSAGE, "invalid login or password");
                commandResult.setState(CommandResult.CommandResultState.REDIRECT_LOGIN);
            }
        } catch (ServiceException e) {
            commandResult.setState(CommandResult.CommandResultState.REDIRECT_ERROR);
            LOGGER.log(Level.ERROR, ResourceManager.getMessage(ResourceConstants.M_ERROR_LOGIN));
        }
        commandResult.setPage(commandResult.getPage() + "?wrongAction=redirect");
        return commandResult;
    }

    private void setSessionForUser(Profile profile, HttpServletRequest req) throws ServiceException {
        HttpSession session = req.getSession();
        Tariff tariff = tariffService.getTariffById(profile.getIdTariffs());
        session.setAttribute(Constants.PARAM_PROFILE, profile);
        session.setAttribute(Constants.PARAM_TARIFF_FOR_USER, tariff);
        LOGGER.log(Level.INFO, "user \"" + profile.getLogin() + "\" has loged in");
        profile.setPassword("");
        session.setAttribute(Constants.PARAM_USER, profile);
    }
}
