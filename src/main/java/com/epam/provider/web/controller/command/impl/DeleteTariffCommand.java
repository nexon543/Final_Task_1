package com.epam.provider.web.controller.command.impl;

import com.epam.provider.service.ServiceException;
import com.epam.provider.service.TariffService;
import com.epam.provider.service.impl.TariffServiceImpl;
import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.util.resource.ResourceManager;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteTariffCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(DeleteTariffCommand.class);
    private TariffService tariffService = new TariffServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest req) {
        CommandResult res = new CommandResult(CommandResult.CommandResultState.FORWARD_ADMIN);
        String idStr = req.getParameter(Constants.PARAM_DELET_ENTITY_ID);
        if (idStr != null) {
            Integer id = Integer.parseInt(idStr);
            try {
                tariffService.deleteTariff(id);
                if (tariffService.getTariffById(id) == null) {
                    req.setAttribute(Constants.PARAM_SUCCESS_MESSAGE, ResourceManager.getMessage(ResourceConstants.M_SUCCESS_DELETE_TARIFF));
                }else{
                    req.setAttribute(Constants.PARAM_ERROR_MESSAGE, ResourceManager.getMessage(ResourceConstants.M_ERROR_DELETE_TARIFF));
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
            }
        }
        return res;
    }
}
