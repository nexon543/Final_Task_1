package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Profile;
import com.epam.provider.model.Tariff;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.TariffService;
import com.epam.provider.service.impl.TariffServiceImpl;
import com.epam.provider.util.SessionRequestContent;
import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
        SessionRequestContent sessionRequestContent = new SessionRequestContent(req);
        CommandResult res = new CommandResult();
        res.setState(CommandResult.CommandResultState.REDIRECT_LOGIN);
        try {
            HttpSession session = req.getSession();
            Profile profile = sessionRequestContent.getUser();
            if (profile == null) {
                profile = new Profile();
            }
            countPages(req);
            setSessionValues(session, profile);
            appendMessageIfExists(req, res);
            return res;
        } catch (ServiceException e) {
            res.setState(CommandResult.CommandResultState.REDIRECT_ERROR);
            LOGGER.log(Level.ERROR, "error while counting tariff pages");
        }
        return res;
    }

    private void appendMessageIfExists(HttpServletRequest req, CommandResult res){
        String message = req.getParameter(Constants.PARAM_ERROR_MESSAGE);
        if (message != null) {
            res.appendToRedirectParam(Constants.PARAM_ERROR_MESSAGE, message);
        }
        message = req.getParameter(Constants.PARAM_SUCCESS_MESSAGE);
        if (message != null) {
            res.appendToRedirectParam(Constants.PARAM_SUCCESS_MESSAGE, message);
        }

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
        if (currentPage>pagesNumber){
            currentPage=pagesNumber;
        }
    }

    private void setSessionValues(HttpSession session, Profile profile) throws ServiceException {
        session.setAttribute(Constants.PARAM_CURR_PAGE, currentPage);
        session.setAttribute(Constants.PARAM_RECORDS_PER_PAGE, recordsPerPage);
        session.setAttribute(Constants.PARAM_NUMEBER_OF_PAGES, pagesNumber);
        Integer start = recordsPerPage * (currentPage - 1);
        Integer end = start + recordsPerPage > recordsNumber ? recordsNumber : start + recordsPerPage;
        String lang = (String) session.getAttribute(Constants.PARAM_LANG);
        if (Constants.ROLE_NAME_ADMIN.equals(profile.getRole())) {
            session.setAttribute(Constants.PARAM_ADMIN_STATUS, Constants.ADMIN_PAGE_STATUS_SHOW_TARIFF);
        }
        List<Tariff> tariffs = tariffService.getTariffs(start, end,
                "en");
        session.setAttribute(Constants.PARAM_TARIFFS, tariffs);
    }
}
