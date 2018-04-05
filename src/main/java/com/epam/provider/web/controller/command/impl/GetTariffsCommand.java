package com.epam.provider.web.controller.command.impl;

import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;
import com.epam.provider.model.Tariff;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.TariffService;
import com.epam.provider.util.resource.ConfigResourceManager;
import com.epam.provider.util.resource.ResourceConstants;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by HP on 30.03.2018.
 */
public class GetTariffsCommand implements ActionCommand {

    TariffService tariffService = new TariffService();
    Integer recordsNumber;
    Integer recordsPerPage=new Integer(Constants.VALUE_RECORDS_PERPAGE);
    Integer pagesNumber;
    Integer currentPage = new Integer(1);

    private static Logger logger = Logger.getLogger(GetTariffsCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest req) {
        CommandResult result = new CommandResult();
        try {
            countPages(req);
            setSessionValues(req.getSession());
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "error while counting tariff pages");
        }
        result.setPage(ConfigResourceManager.getPagePath(ResourceConstants.PAGE_NAME_TARIFF));
        result.setResponseType(CommandResult.ResponseType.FORWARD);
        return result;
    }

    private void countPages(HttpServletRequest req) throws ServiceException {
        String currPageStr = req.getParameter(Constants.PARAM_NAME_CURR_PAGE);
        if (currPageStr != null) {
            currentPage = new Integer(currPageStr);
        }
        recordsNumber = tariffService.getNumberOfRecords();
        String recPerPage= req.getParameter(Constants.PARAM_NAME_RECORDS_PER_PAGE);
        if (recPerPage != null) {
            recordsPerPage = new Integer(recPerPage);
        }

        pagesNumber = recordsNumber / recordsPerPage;
        if (recordsNumber % recordsPerPage != 0) {
            pagesNumber++;
        }
    }

    private void setSessionValues(HttpSession session) throws ServiceException {
        session.setAttribute(Constants.PARAM_NAME_CURR_PAGE, currentPage);
        session.setAttribute(Constants.PARAM_NAME_RECORDS_PER_PAGE, recordsPerPage);
        session.setAttribute(Constants.PARAM_NAME_NUMEBER_OF_PAGES, pagesNumber);

        Integer start = recordsPerPage * (currentPage - 1);
        Integer end = start + recordsPerPage > recordsNumber ? recordsNumber : start + recordsPerPage;
        List<Tariff> tariffs = tariffService.getTariffs(start, end);
        session.setAttribute(Constants.PARAM_NAME_TARIFFS, tariffs);
    }
}
