package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Tariff;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.ServiceFactory;
import com.epam.provider.service.TariffService;
import com.epam.provider.util.RequestContent;
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

  public UpdateTariffCommand() {
  }

  @Override
  public CommandResult execute(HttpServletRequest req) {
    CommandResult res = new CommandResult();
    res.setControllerRequest(ActionType.GET_TARIFFS);
    Tariff tariff = RequestContent.getTariff(req);
    try {
      tariffService.updateTariff(tariff);
      res.appendParamToRedirect(Constants.PARAM_SUCCESS_MESSAGE, "tariff was successfully updated");
    } catch (ServiceException e) {
      res.appendParamToRedirect(Constants.PARAM_ERROR_MESSAGE, "invalid login or password");
      LOGGER.log(Level.ERROR, e.getMessage());
    }
    req.setAttribute("updatableTariff", null);
    req.getSession()
        .setAttribute(Constants.PARAM_DISPLAY_MESSAGE, Constants.VALUE_DISPLAY_MESSAGE_YES);
    return res;
  }


}
