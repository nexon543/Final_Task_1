package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Profile;
import com.epam.provider.model.Tariff;
import com.epam.provider.service.ProfileService;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.ServiceFactory;
import com.epam.provider.service.TariffService;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class GetUpdatePageCommand implements ActionCommand {

  private static final Logger LOGGER = Logger.getLogger(GetTariffsCommand.class);
  private ProfileService profileService = ServiceFactory.getProfileService();
  private TariffService tariffService = ServiceFactory.getTariffService();

  @Override
  public CommandResult execute(HttpServletRequest req) {
    CommandResult result = new CommandResult();
    String entity = req.getParameter(Constants.PARAM_UPDATED_ENTITY);
    HttpSession session = req.getSession();
    String lang = (String) session.getAttribute(Constants.PARAM_LOCAL);
    String idParam;
    //Validator.isValid(idParam);
    try {
      switch (entity) {
        case "tariff":
          idParam = req.getParameter(Constants.PARAM_TARIFFS_ID);
          Tariff tariff;
          if (idParam != null) {
            Integer id = Integer.parseInt(idParam);
            tariff = tariffService.getTariffById(id, lang);
            result.setState(CommandResult.CommandResultState.FORWARD_UPDATE_TARIFF);
          } else {
            tariff = new Tariff();
            result.setState(CommandResult.CommandResultState.FORWARD_ADD_TARIFF);
          }
          req.setAttribute(Constants.ATTR_UPDATABLE_TARIFF, tariff);
          req.setAttribute(Constants.ATTR_STATUS, Constants.ADMIN_STATUS_UPDATE_TARIFF);
          req.setAttribute(Constants.PARAM_CURRENT_PAGE_REQUEST_NAME, "/UpdateTariffPage");
          break;
        case "profile":
          idParam = req.getParameter(Constants.PARAM_PROFILE_ID);
          Profile profile;
          if (idParam != null) {
            Integer id = Integer.parseInt(idParam);
            profile = profileService.getById(id);
            result.setState(CommandResult.CommandResultState.FORWARD_UPDATE_PROFILE);
          } else {
            profile = new Profile();
            result.setState(CommandResult.CommandResultState.FORWARD_ADD_PROFILE);
          }
          List<Tariff> tariffs = tariffService.getAllTariffs(lang);
          req.setAttribute(Constants.ATTR_ALL_TARIFFS, tariffs);
          req.setAttribute(Constants.ATTR_UPDATABLE_PROFILE, profile);
          req.setAttribute(Constants.PARAM_CURRENT_PAGE_REQUEST_NAME, "/UpdateProfile");
      }
    } catch (ServiceException e) {
      LOGGER.log(Level.ERROR, e.getStackTrace());
    }
    appendMessageIfExists(req, result);
    return result;
  }

  private void appendMessageIfExists(HttpServletRequest req, CommandResult res) {
    String message = req.getParameter(Constants.PARAM_ERROR_MESSAGE);
    if (message != null) {
      res.appendParamToRedirect(Constants.PARAM_ERROR_MESSAGE, message);
    }
    message = req.getParameter(Constants.PARAM_SUCCESS_MESSAGE);
    if (message != null) {
      res.appendParamToRedirect(Constants.PARAM_SUCCESS_MESSAGE, message);
    }
  }
}
