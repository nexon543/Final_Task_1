package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Profile;
import com.epam.provider.model.Tariff;
import com.epam.provider.service.ProfileService;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.TariffService;
import com.epam.provider.service.impl.ProfileServiceImpl;
import com.epam.provider.service.impl.TariffServiceImpl;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class GetUpdatableEntityCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(GetTariffsCommand.class);
    ProfileService profileService = new ProfileServiceImpl();
    TariffService tariffService = new TariffServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest req) {
        CommandResult result = new CommandResult(CommandResult.CommandResultState.FORWARD_ADMIN);
        String entity = req.getParameter(Constants.PARAM_UPDATED_ENTITY);
        req.setAttribute("updatedEntityName", entity);
        Integer id = Integer.parseInt(req.getParameter("id"));
        try {
            switch (entity) {
                case "tariff":
                    Tariff tariff = tariffService.getTariffById(id);
                    req.setAttribute("updatableTariff", tariff);
                    req.setAttribute(Constants.PARAM_ADMIN_STATUS, Constants.ADMIN_STATUS_UPDATE_TARIFF);
                    break;
                case "profile":
                    Profile profile = profileService.getById(id);
                    req.setAttribute("updatableTariff", profile);
                    req.setAttribute(Constants.PARAM_ADMIN_STATUS, Constants.ADMIN_STATUS_UPDATE_PROFILE);
                    break;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
        return result;
    }


    private Tariff getTariff(HttpServletRequest req) {
        Tariff newTariff = new Tariff();
        newTariff.setPrice(Integer.parseInt(req.getParameter("price")));
        newTariff.setRecievingSpeed(Integer.parseInt(req.getParameter("recievingSpeed")));
        newTariff.setTransferSpeed(Integer.parseInt(req.getParameter("transferSpeed")));
        newTariff.setDescription(req.getParameter("description"));
        newTariff.setName(req.getParameter("name"));
        newTariff.setLang(req.getParameter("lang"));
        return newTariff;
    }
}
