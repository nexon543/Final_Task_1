package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Tariff;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.TariffService;
import com.epam.provider.service.impl.TariffServiceImpl;
import com.epam.provider.util.RequestContent;
import com.epam.provider.web.controller.command.AjaxActionCommand;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class
GetTariffsAjaxCommand implements AjaxActionCommand {

    private static final Logger LOGGER = Logger.getLogger(GetTariffsCommand.class);

    private TariffService tariffService = new TariffServiceImpl();
    private Integer recordsNumber;
    private Integer recordsPerPage = Constants.VALUE_RECORDS_PERPAGE;
    private Integer pagesNumber;
    private Integer currentPage = Constants.VALUE_CURRENT_PAGE;

    @Override
    public JSONObject execute(HttpServletRequest req) {
        JSONObject tariffsResponse = new JSONObject();
        CommandResult res = new CommandResult(CommandResult.CommandResultState.FORWARD_TARIFF);
        RequestContent.init(req);
        String lang = RequestContent.getCurrentLang();
        try {
            countPages(req);
            setValues(tariffsResponse, lang);
            req.setAttribute(Constants.PARAM_CURRENT_PAGE_REQUEST_NAME, Constants.VALUE_CURRENT_PAGE_REQUEST_NAME_TARIFFS);
            return tariffsResponse;
        } catch (ServiceException e) {
            res.setState(CommandResult.CommandResultState.REDIRECT_ERROR);
            LOGGER.log(Level.ERROR, "error while counting tariff pages");
        }
        req.getSession()
                .setAttribute(Constants.PARAM_DISPLAY_MESSAGE, Constants.VALUE_DISPLAY_MESSAGE_NO);
        return tariffsResponse;
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

    private void setValues(JSONObject tariffsResponse, String lang) throws ServiceException {
        tariffsResponse.put(Constants.PARAM_CURR_PAGE, currentPage);
        tariffsResponse.put(Constants.PARAM_RECORDS_PER_PAGE, recordsPerPage);
        tariffsResponse.put(Constants.PARAM_NUMEBER_OF_PAGES, pagesNumber);
        Integer start = recordsPerPage * (currentPage - 1);
        Integer end = start + recordsPerPage > recordsNumber ? recordsNumber : start + recordsPerPage;
        tariffsResponse.put(Constants.ATTR_STATUS, Constants.ADMIN_PAGE_STATUS_SHOW_TARIFF);
        List<Tariff> tariffs = tariffService.getTariffs(start, end,
                lang);
        tariffsResponse.put(Constants.PARAM_TARIFFS, tariffs);
    }
}
