package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Tariff;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.TariffService;
import com.epam.provider.service.impl.TariffServiceImpl;
import com.epam.provider.util.RequestContent;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @author Gleb Akseonov
 */
public class GetTariffsCommand implements ActionCommand {

  private static final Logger LOGGER = Logger.getLogger(GetTariffsCommand.class);

  private TariffService tariffService = new TariffServiceImpl();
  private Integer recordsNumber;
  private Integer recordsPerPage = new Integer(Constants.VALUE_RECORDS_PERPAGE);
  private Integer pagesNumber;
  private Integer currentPage = new Integer(1);

  /**
   * {@inheritDoc}
   */
  @Override
  public CommandResult execute(HttpServletRequest req) {
    CommandResult res = new CommandResult(CommandResult.CommandResultState.FORWARD_TARIFF);
    try {
      countPages(req);
      setValues(req);
      req.setAttribute(Constants.PARAM_CURRENT_PAGE_REQUEST_NAME, "get_tariffs");
      return res;
    } catch (ServiceException e) {
      res.setState(CommandResult.CommandResultState.REDIRECT_ERROR);
      LOGGER.log(Level.ERROR, "error while counting tariff pages");
    }
    req.getSession()
        .setAttribute(Constants.PARAM_DISPLAY_MESSAGE, Constants.VALUE_DISPLAY_MESSAGE_NO);
    return res;
  }


  private void countPages(HttpServletRequest req) throws ServiceException {
    String currPageStr = req.getParameter(Constants.PARAM_CURR_PAGE);
    if (currPageStr != null) {
      currentPage = new Integer(currPageStr);
    }
    recordsNumber = tariffService.getNumberOfRecords();
    String recPerPage = req.getParameter(Constants.PARAM_RECORDS_PER_PAGE);
    if (recPerPage != null) {
      recordsPerPage = new Integer(recPerPage);
    }
    pagesNumber = recordsNumber / recordsPerPage;
    if (recordsNumber % recordsPerPage != 0) {
      pagesNumber++;
    }
    if (currentPage > pagesNumber) {
      currentPage = pagesNumber;
    }
  }

  private void setValues(HttpServletRequest req) throws ServiceException {
    req.setAttribute(Constants.PARAM_CURR_PAGE, currentPage);
    req.setAttribute(Constants.PARAM_RECORDS_PER_PAGE, recordsPerPage);
    req.setAttribute(Constants.PARAM_NUMEBER_OF_PAGES, pagesNumber);
    Integer start = recordsPerPage * (currentPage - 1);
    Integer end = start + recordsPerPage > recordsNumber ? recordsNumber : start + recordsPerPage;
    String lang = (String) req.getSession().getAttribute(Constants.PARAM_LOCAL);
    req.setAttribute(Constants.ATTR_STATUS, Constants.ADMIN_PAGE_STATUS_SHOW_TARIFF);
    List<Tariff> tariffs = tariffService.getTariffs(start, end,
        lang);
    req.setAttribute(Constants.PARAM_TARIFFS, tariffs);
  }
}
