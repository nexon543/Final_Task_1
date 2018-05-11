package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Field;
import com.epam.provider.model.Tariff;
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
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class UpdateTariffCommand implements ActionCommand {

  private static final Logger LOGGER = LogManager.getLogger(UpdateTariffCommand.class);
  private TariffService tariffService = ServiceFactory.getTariffService();

  @Override
  public CommandResult execute(HttpServletRequest req) {
    CommandResult res = new CommandResult();
    Tariff tariff = RequestContent.getTariff(req);
    RequestContent.initSession(req);
    String lang = RequestContent.getCurrentLang();
    try {
      tariffService.updateTariff(tariff);
      RequestContent.setMessage(Constants.ATTR_SUCCESS_MESSAGE,
          ResourceManager.getMessage(ResourceConstants.M_SUCCESS_UPDATE_TARIFF, lang));
      res.setControllerRequest(ActionType.GET_TARIFFS);
    } catch (ServiceException e) {
      res.appendParamToRedirect(Constants.PARAM_UPDATED_ENTITY,
          Constants.VALUE_UPDATED_ENTITY_PROFILE);
      res.appendParamToRedirect(Field.TARIFF_ID.getName(),
          tariff.getTariffId().toString());
      RequestContent.setMessage(Constants.ATTR_ERROR_MESSAGE,
          ResourceManager.getMessage(ResourceConstants.M_ERROR_UPDATE_TARIFF, lang));
      LOGGER.log(Level.ERROR, e.getMessage());
    }
    return res;
  }
}
