package com.epam.provider.web.controller.command.impl;

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
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This command is adding new tariff entered by admin
 *
 * @author Gleb Aksenov {@link ActionCommand}  invokes method execute()
 */
public class AddTariffCommand implements ActionCommand {

  private static final Logger LOGGER = LogManager.getLogger(AddTariffCommand.class);
  private TariffService tariffService = ServiceFactory.getTariffService();

  public AddTariffCommand() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CommandResult execute(HttpServletRequest req) {
    CommandResult res = new CommandResult();

    res.setControllerRequest(ActionType.GET_TARIFFS);
    Tariff tariff = RequestContent.getTariff(req);
    try {
      tariffService.createTariff(tariff);
      res.appendParamToRedirect(Constants.PARAM_SUCCESS_MESSAGE,
          "tariff was successfully created");
    } catch (ServiceException e) {
      LOGGER.log(Level.ERROR, "can't add new tariff");
      res.setPage(ResourceManager.getPagePath(ResourceConstants.PAGE_NAME_ERROR));
    }
    req.getSession()
        .setAttribute(Constants.PARAM_DISPLAY_MESSAGE, Constants.VALUE_DISPLAY_MESSAGE_YES);
    return res;
  }
}
