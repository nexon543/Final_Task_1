package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Profile;
import com.epam.provider.model.Tariff;
import com.epam.provider.service.ProfileService;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.ServiceFactory;
import com.epam.provider.service.TariffService;
import com.epam.provider.util.RequestContent;
import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.util.resource.ResourceManager;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.ActionType;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;
import com.epam.provider.web.validator.ParameterName;
import com.epam.provider.web.validator.Validator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This command is used to verify the user
 *
 * @author Gleb Aksenov {@link ActionCommand}  invokes method execute()
 */
public class LoginCommand implements ActionCommand {

  private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

  private TariffService tariffService = ServiceFactory.getTariffService();
  private ProfileService profileService = ServiceFactory.getProfileService();
  ;

  /**
   * {@inheritDoc}
   */
  @Override
  public CommandResult execute(HttpServletRequest req) {
    String login = req.getParameter(Constants.PARAM_LOGIN);
    String pass = req.getParameter(Constants.PARAM_PASSWORD);
    RequestContent.init(req);
    String lang=RequestContent.getCurrentLang();
    CommandResult result = new CommandResult(CommandResult.CommandResultState.REDIRECT_LOGIN);
    boolean isValid = Validator.isValid(
        RequestContent.getValuesForValidation(ParameterName.getParamSet(ActionType.LOGIN), req));
    if (!isValid) {
      result.appendParamToRedirect(Constants.ATTR_ERROR_MESSAGE,
          ResourceManager.getMessage(ResourceConstants.M_ERROR_LOGIN, lang));
      return result;
    }
    try {
      Profile profile = profileService.findUser(login, pass);
      if (profile.getProfileId() != null) {
        setSessionForUser(profile, req);
      } else {
        req.setAttribute(Constants.ATTR_ERROR_MESSAGE,
            ResourceManager.getMessage(ResourceConstants.M_ERROR_LOGIN, lang));
        result.setState(CommandResult.CommandResultState.REDIRECT_LOGIN);
      }
    } catch (ServiceException e) {
      result.setState(CommandResult.CommandResultState.REDIRECT_ERROR);
      LOGGER.log(Level.ERROR, "Error querying profile by login password");
    }
    return result;
  }

  private void setSessionForUser(Profile profile, HttpServletRequest req) throws ServiceException {
    HttpSession session = req.getSession();
    String lang = (String) session.getAttribute(Constants.PARAM_LOCAL);
    Tariff tariff = tariffService.getTariffById(profile.getIdTariffs(), lang);
    session.setAttribute(Constants.ATTR_SESSION_PROFILE, profile);
    session.setAttribute(Constants.ATT_SESSION_PROFILE_TARIFF, tariff);
    LOGGER.log(Level.INFO, "user \"" + profile.getLogin() + "\" has loged in");
    profile.setPassword("");
    session.setAttribute(Constants.PARAM_USER, profile);
  }
}
