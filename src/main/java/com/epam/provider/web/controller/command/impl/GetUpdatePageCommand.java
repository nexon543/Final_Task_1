package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Field;
import com.epam.provider.model.Profile;
import com.epam.provider.model.Tariff;


import com.epam.provider.service.ProfileService;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.ServiceFactory;
import com.epam.provider.service.TariffService;
import com.epam.provider.util.RequestContent;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class GetUpdatePageCommand implements ActionCommand {

  private static final Logger LOGGER = Logger.getLogger(GetTariffsCommand.class);
  private ProfileService profileService = ServiceFactory.getProfileService();
  private TariffService tariffService = ServiceFactory.getTariffService();

  @Override
  public CommandResult execute(HttpServletRequest req) {
    CommandResult result = new CommandResult();
    CommandResult.CommandResultState resultState;
    String entity = req.getParameter(Constants.PARAM_UPDATED_ENTITY);
    RequestContent.initSession(req);
    String lang=RequestContent.getCurrentLang();
    Optional<String> id;
    try {
      switch (entity) {
        case "tariff":
          Tariff tariff;
          id=Optional.ofNullable(req.getParameter(Field.TARIFF_ID.getName())).filter(s->!"".equals(s));
          if(id.isPresent()){
            tariff=tariffService.getTariffById(Integer.parseInt(id.get()), lang);
            resultState=CommandResult.CommandResultState.FORWARD_UPDATE_TARIFF;
          } else {
            tariff = new Tariff();
            resultState=CommandResult.CommandResultState.FORWARD_ADD_TARIFF;
          }
          result.setState(resultState);
          req.setAttribute(Constants.ATTR_UPDATABLE_TARIFF, tariff);
          req.setAttribute(Constants.ATTR_STATUS, Constants.ADMIN_STATUS_UPDATE_TARIFF);
          req.setAttribute(Constants.PARAM_CURRENT_PAGE_REQUEST_NAME, Constants.REQUEST_UPDATE_TARIFF);
          break;
        case "profile":
          Profile profile;
          id=Optional.ofNullable(req.getParameter(Field.PROFILE_ID.getName())).filter(s->!"".equals(s));
          if (id.isPresent()) {
            profile = profileService.getById(Integer.parseInt(id.get()));
            result.setState(CommandResult.CommandResultState.FORWARD_UPDATE_PROFILE);
          } else {
            profile = new Profile();
            result.setState(CommandResult.CommandResultState.FORWARD_ADD_PROFILE);
          }
          List<Tariff> tariffs = tariffService.getAllTariffs(lang);
          req.setAttribute(Constants.ATTR_ALL_TARIFFS, tariffs);
          req.setAttribute(Constants.ATTR_UPDATABLE_PROFILE, profile);
          req.setAttribute(Constants.PARAM_CURRENT_PAGE_REQUEST_NAME, Constants.REQUEST_UPDATE_PROFILE);
      }
    } catch (ServiceException e) {
      LOGGER.log(Level.ERROR, e.getStackTrace());
    }
    return result;
  }
}
