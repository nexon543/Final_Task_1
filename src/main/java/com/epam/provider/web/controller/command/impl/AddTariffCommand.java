package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Tariff;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.TariffService;
import com.epam.provider.service.impl.TariffServiceImpl;
import com.epam.provider.util.SessionRequestContent;
import com.epam.provider.util.resource.ConfigResourceManager;
import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.ActionType;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;
import com.epam.provider.web.validator.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * This command is adding new tariff entered by admin
 *
 * @author Gleb Aksenov
 *         {@link ActionCommand}  invokes method execute()
 */
public class AddTariffCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(AddTariffCommand.class);
    private TariffService tariffService = new TariffServiceImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(HttpServletRequest req) {
        CommandResult res = new CommandResult();
        res.setController(ActionType.GET_TARIFFS);
        Tariff tariff = SessionRequestContent.getTariff(req);
        try {
            tariffService.createTariff(tariff);
            res.appendToRedirectParam(Constants.PARAM_SUCCESS_MESSAGE,"tariff was successfully created");
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "can't add new tariff");
            res.setPage(ConfigResourceManager.getPagePath(ResourceConstants.PAGE_NAME_ERROR));
        }
        return res;
    }
}
