package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Profile;
import com.epam.provider.model.Tariff;
import com.epam.provider.service.ProfileService;
import com.epam.provider.service.ServiceFactory;
import com.epam.provider.service.TariffService;
import com.epam.provider.util.RequestContent;
import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.util.resource.ResourceManager;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;
import javax.servlet.http.HttpServletRequest;

public class ChangeTariffCommand implements ActionCommand {

  private TariffService tariffService = ServiceFactory.getTariffService();
  private ProfileService profileService = ServiceFactory.getProfileService();
  @Override
  public CommandResult execute(HttpServletRequest req) {
    RequestContent.initSession(req);
    Profile profile = RequestContent.getSessionProfile();
    String lang=RequestContent.getCurrentLang();
    CommandResult res = new CommandResult(CommandResult.CommandResultState.REDIRECT_LOGIN);
    try {
      profile.setIdTariffs(Integer.parseInt(req.getParameter(Constants.PARAM_CHANGE_TARIFF)));
      if (profileService.updateUsersTariff(profile.getProfileId(), profile.getIdTariffs())) {
        Tariff tariff = tariffService.getTariffById(profile.getIdTariffs(), lang);
        RequestContent.setSessionAttribute(Constants.ATT_SESSION_PROFILE_TARIFF, tariff);
        RequestContent.setMessage(Constants.ATTR_SUCCESS_MESSAGE, ResourceManager.getMessage(ResourceConstants.M_SUCCESS_CHANGE_TARIFF,lang));
      }
    } catch (Exception e) {
      RequestContent.setMessage(Constants.ATTR_ERROR_MESSAGE,ResourceManager.getMessage(ResourceConstants.M_ERROR_CHANGE_TARIFF,lang));
    }
    return res;
  }


}
