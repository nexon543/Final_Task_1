package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Tariff;
import com.epam.provider.service.TariffService;
import com.epam.provider.service.impl.TariffServiceImpl;
import com.epam.provider.util.SessionRequestContent;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteTariffCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(DeleteTariffCommand.class);
    private TariffService tariffService = new TariffServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest req) {
        CommandResult res=new CommandResult(CommandResult.CommandResultState.REDIRECT_ADMIN);
        Integer id=req.getParameter("id");
        tariffService.deleteTariff(id);
        return null;
    }
}
