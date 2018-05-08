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

  /**
   * {@inheritDoc}
   */
  @Override
  public CommandResult execute(HttpServletRequest req) {
    CommandResult res = new CommandResult();
    RequestContent.init(req);
    String lang=RequestContent.getCurrentLang();
    Tariff tariff = RequestContent.getTariff(req);
    try {
      tariffService.createTariff(tariff);
      RequestContent.setMessage(Constants.ATTR_SUCCESS_MESSAGE,
              ResourceManager.getMessage(ResourceConstants.M_SUCCESS_CREATE_TARIFF,lang));
      res.setControllerRequest(ActionType.GET_TARIFFS);
    } catch (ServiceException e) {
      LOGGER.log(Level.ERROR, "can't add new tariff");
      res.setPage(ResourceManager.getPagePath(ResourceConstants.PAGE_NAME_ERROR));
    }
    return res;
  }
}
