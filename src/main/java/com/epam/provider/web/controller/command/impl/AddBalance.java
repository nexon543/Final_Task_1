package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Profile;
import com.epam.provider.service.ProfileService;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.impl.ProfileServiceImpl;
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

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This command is changing balance of client when he wants to deposit money
 *
 * @author Gleb Aksenov {@link ActionCommand}  invokes method execute()
 */
public class AddBalance implements ActionCommand {

  private static final Logger LOGGER = LogManager.getLogger(AddTariffCommand.class);
  private ProfileService profileService = new ProfileServiceImpl();

  /**
   * {@inheritDoc}
   */
  @Override
  public CommandResult execute(HttpServletRequest req) {
    CommandResult result = new CommandResult(CommandResult.CommandResultState.REDIRECT_LOGIN);
    RequestContent.init(req);
    String lang=RequestContent.getCurrentLang();
    boolean isValid = Validator.isValid(RequestContent
        .getValuesForValidation(ParameterName.getParamSet(ActionType.ADD_BALANCE), req));
    if (isValid) {
      Integer amount = Integer.parseInt(req.getParameter(Constants.PARAM_BALANCE));
      Profile profile = (Profile) RequestContent.getSessionAttribute(Constants.ATTR_SESSION_PROFILE);
      Integer profileId = profile.getProfileId();
      try {
        profileService.addBalance(amount, profileId);
        profile.setBalance(profile.getBalance() + amount);
        RequestContent.setMessage(Constants.ATTR_SUCCESS_MESSAGE,
            ResourceManager.getMessage(ResourceConstants.M_SUCCESS_ADD_BALANCE,lang));
      } catch (ServiceException e) {
        RequestContent.setMessage(Constants.ATTR_ERROR_MESSAGE,
            ResourceManager.getMessage(ResourceConstants.M_FAILD,lang));
        LOGGER.log(Level.ERROR, e.getStackTrace());
      }
    } else {
      RequestContent.setMessage(Constants.ATTR_ERROR_MESSAGE,
          ResourceManager.getMessage(ResourceConstants.M_INCORRECT_VALUE, lang));
    }
    return result;
  }
}
