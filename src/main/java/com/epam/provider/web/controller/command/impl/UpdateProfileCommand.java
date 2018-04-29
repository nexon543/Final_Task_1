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

public class UpdateProfileCommand implements ActionCommand {

  private static final Logger LOGGER = LogManager.getLogger(UpdateTariffCommand.class);
  private ProfileService profileService = ServiceFactory.getProfileService();


  /**
   * Updates profile if login is unique
   *
   * @param req needed to get data and set session attributes
   * @return {@link CommandResult}
   */

  @Override
  public CommandResult execute(HttpServletRequest req) {
    Profile updatedProfile = RequestContent.getProfile(req);
    String currentUserRole = RequestContent.getCurrentUserRole(req);
    CommandResult res = new CommandResult(CommandResult.CommandResultState.CONTROLLER_GET_PROFILE);
    boolean isValid = Validator.isValid(RequestContent
        .getValuesForValidation(ParameterName.getParamSet(ActionType.UPDATE_PROFILE), req));
    if (isValid) {
      try {
        Profile existedProfile = profileService.findUser(updatedProfile.getLogin());

        if ((existedProfile.getProfileId() == null)
            || existedProfile.getProfileId() == updatedProfile.getProfileId()) {
          profileService.updateUser(updatedProfile);
          res.appendParamToRedirect(Constants.PARAM_SUCCESS_MESSAGE,
              getSuccessMessage(currentUserRole));
        } else {
          res.setState(CommandResult.CommandResultState.GET_UPDATE_PAGE);
          req.setAttribute(Constants.PARAM_SUCCESS_MESSAGE, "tariff was successfully updated");
          res.appendParamToRedirect(Constants.PARAM_ERROR_MESSAGE,
              ResourceManager.getMessage(ResourceConstants.M_PROFILE_EXISTS));
          res.appendParamToRedirect(Constants.PARAM_UPDATED_ENTITY, "profile");
          res.appendParamToRedirect(Constants.PARAM_PROFILE_ID,
              updatedProfile.getProfileId().toString());
        }
      } catch (ServiceException e) {
        res.appendParamToRedirect(Constants.PARAM_ERROR_MESSAGE, getErrorMessage(currentUserRole));
        LOGGER.log(Level.ERROR, e.getStackTrace());
      }

    }
    req.getSession()
        .setAttribute(Constants.PARAM_DISPLAY_MESSAGE, Constants.VALUE_DISPLAY_MESSAGE_YES);
    return res;
  }

  private String getSuccessMessage(String role) {
    if (Constants.ROLE_NAME_ADMIN.equals(role)) {
      return "user was successfully updated";
    } else {
      return "You have successfully changed the tariff!";
    }
  }

  private String getErrorMessage(String role) {
    if (Constants.ROLE_NAME_ADMIN.equals(role)) {
      return "user wasn't updated";
    } else {
      return "You didn't changed the tariff";
    }
  }
}
