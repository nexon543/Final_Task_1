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
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Date;

public class AddProfileCommand implements ActionCommand {

  private static final Logger LOGGER = LogManager.getLogger(AddTariffCommand.class);
  private ProfileService profileService = ServiceFactory.getProfileService();

  public AddProfileCommand() {
  }

  @Override
  public CommandResult execute(HttpServletRequest req) {

    CommandResult res = new CommandResult();
    RequestContent.init(req);
    String lang = RequestContent.getCurrentLang();
    Profile profile = RequestContent.getProfile(req);
    boolean isValid = Validator.isValid(RequestContent
        .getValuesForValidation(ParameterName.getParamSet(ActionType.ADD_PROFILE), req));
    if (isValid) {
      try {
        res.setState(CommandResult.CommandResultState.CONTROLLER_GET_PROFILE);
        if (!profileService.isUserExists(profile.getLogin())) {
          Date date = new Date(new java.util.Date().getTime());
          profile.setRegisterDate(date);
          profileService.createProfile(profile);
          RequestContent.setMessage(Constants.ATTR_SUCCESS_MESSAGE,
              ResourceManager.getMessage(ResourceConstants.M_SUCCESS,lang));
        } else {
          res.setState(CommandResult.CommandResultState.FORWARD_ADD_PROFILE);
          RequestContent.setMessage(Constants.ATTR_ERROR_MESSAGE,
              ResourceManager.getMessage(ResourceConstants.M_PROFILE_EXISTS,lang));

          setUpdatableProfileWithTariff(profile, lang, req);
        }

      } catch (ServiceException e) {
        LOGGER.log(Level.ERROR, ResourceManager.getMessage(ResourceConstants.M_FAILD));
        res.setState(CommandResult.CommandResultState.REDIRECT_ERROR);
      }
    } else {
      res.setState(CommandResult.CommandResultState.FORWARD_ADD_PROFILE);
      try {
        setUpdatableProfileWithTariff(profile, lang, req);
      } catch (ServiceException e) {
        LOGGER.log(Level.ERROR, e.getStackTrace());
      }
      RequestContent.setMessage(Constants.ATTR_ERROR_MESSAGE,
          ResourceManager.getMessage(ResourceConstants.M_INCORRECT_VALUE,lang));
    }
    return res;
  }

  private void setUpdatableProfileWithTariff(Profile profile, String lang, HttpServletRequest req)
      throws ServiceException {
    req.setAttribute(Constants.ATTR_ALL_TARIFFS,
        ServiceFactory.getTariffService().getAllTariffs(lang));
    req.setAttribute(Constants.ATTR_CREATE_PROFILE_FORM_OBJECT, profile);
  }

}
