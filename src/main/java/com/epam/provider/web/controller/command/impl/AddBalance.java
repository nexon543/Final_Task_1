package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Profile;
import com.epam.provider.service.ProfileService;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.impl.ProfileServiceImpl;
import com.epam.provider.util.SessionRequestContent;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;
import com.epam.provider.web.validator.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This command is changing balance of client when he wants to deposit money
 *
 * @author Gleb Aksenov
 * {@link ActionCommand}  invokes method execute()
 */
public class AddBalance implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(AddTariffCommand.class);
    private ProfileService profileService;

    public AddBalance() {
        profileService = new ProfileServiceImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(HttpServletRequest req) {
        CommandResult result = new CommandResult(CommandResult.CommandResultState.REDIRECT_LOGIN);
        HttpSession session = req.getSession();
        SessionRequestContent reqContent = new SessionRequestContent(req);
        Validator validator = new Validator();
        boolean isSuccess = true;//validator.isValid(reqContent.getRequestAttributes());
        String message;
        String messageKey;
        if (isSuccess) {
            Integer amount = Integer.parseInt(req.getParameter(Constants.PARAM_BALANCE));
            Profile Profile = (Profile) session.getAttribute(Constants.PARAM_USER);
            Profile profile = (Profile) session.getAttribute(Constants.PARAM_PROFILE);
            Integer profileId = Profile.getProfileId();
            try {
                profileService.addBalance(amount, profileId);
                profile.setBalance(profile.getBalance() + amount);
                session.setAttribute(Constants.PARAM_PROFILE, profile);
                result.appendToRedirectParam(Constants.PARAM_SUCCESS_MESSAGE, "money was successfully deposit");

            } catch (ServiceException e) {
                result.appendToRedirectParam(Constants.PARAM_ERROR_MESSAGE, "error on server while depositing");
                LOGGER.log(Level.ERROR, "error depositing money");
            }
        } else {
            result.appendToRedirectParam(Constants.PARAM_ERROR_MESSAGE, "incorrect value");
        }
        result.appendToRedirectParam(Constants.PARAM_IS_SUCCESS, String.valueOf(isSuccess));
        return result;
    }
}
