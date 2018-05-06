package com.epam.provider.web.controller.command.impl;

import com.epam.provider.service.ServiceException;
import com.epam.provider.service.ServiceFactory;
import com.epam.provider.service.TariffService;
import com.epam.provider.util.RequestContent;
import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.util.resource.ResourceManager;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DeleteTariffCommand implements ActionCommand {

  private static final Logger LOGGER = LogManager.getLogger(DeleteTariffCommand.class);
  private TariffService tariffService = ServiceFactory.getTariffService();

  @Override
  public CommandResult execute(HttpServletRequest req) {
    CommandResult res = new CommandResult(CommandResult.CommandResultState.GET_TARIFFS);
    String idStr = req.getParameter(Constants.PARAM_DELET_ENTITY_ID);
    RequestContent.init(req);
    String lang=RequestContent.getCurrentLang();
    if (idStr != null) {
      Integer id = Integer.parseInt(idStr);
      try {
        tariffService.deleteTariff(id);
        if (tariffService.getTariffById(id, lang) == null) {
          RequestContent.setMessage(Constants.ATTR_SUCCESS_MESSAGE,
              ResourceManager.getMessage(ResourceConstants.M_SUCCESS_DELETE_TARIFF,lang));
        } else {
          RequestContent.setMessage(Constants.ATTR_ERROR_MESSAGE,
              ResourceManager.getMessage(ResourceConstants.M_ERROR_DELETE_TARIFF,lang));
        }
      } catch (ServiceException e) {
        LOGGER.log(Level.ERROR, e.getMessage());
      }
    }
    return res;
  }
}
