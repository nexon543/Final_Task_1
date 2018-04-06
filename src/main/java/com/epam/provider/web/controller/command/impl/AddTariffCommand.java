package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Tariff;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.TariffService;
import com.epam.provider.util.resource.ConfigResourceManager;
import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.ActionType;
import com.epam.provider.web.controller.command.CommandResult;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddTariffCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(AddTariffCommand.class);
    TariffService tariffService = new TariffService();

    @Override
    public CommandResult execute(HttpServletRequest req) {
        CommandResult res = new CommandResult();
        res.setPage("/Controller?command=" + ActionType.GET_TARIFFS);
        Tariff newTariff = new Tariff();
        newTariff.setPrice(Integer.parseInt(req.getParameter("price")));
        newTariff.setRecievingSpeed(Integer.parseInt(req.getParameter("recievingSpeed")));
        newTariff.setTransferSpeed(Integer.parseInt(req.getParameter("transferSpeed")));
        newTariff.setRecievingSpeed(Integer.parseInt(req.getParameter("recievingSpeed")));
        newTariff.setDescription(req.getParameter("description"));
        newTariff.setName(req.getParameter("name"));
        newTariff.setLang(req.getParameter("lang"));
        try {
            tariffService.insert(newTariff);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "can't add new tariff");
            res.setPage(ConfigResourceManager.getPagePath(ConfigResourceManager.getPagePath(ResourceConstants.PAGE_NAME_ERROR)));
        }
        res.setResponseType(CommandResult.ResponseType.REDIRECT);
        return res;
    }
}
