package com.epam.provider.web.controller.command.impl;

import com.epam.provider.model.Profile;
import com.epam.provider.model.Tariff;
import com.epam.provider.service.ProfileService;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.ServiceFactory;
import com.epam.provider.service.TariffService;
import com.epam.provider.util.RequestContent;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeTariffCommand implements ActionCommand {

    private TariffService tariffService = ServiceFactory.getTariffService();
    private ProfileService profileService;

    public ChangeTariffCommand() {
        profileService = ServiceFactory.getProfileService();
    }

    @Override
    public CommandResult execute(HttpServletRequest req) {
        Profile profile = RequestContent.getSessionProfile(req);
        HttpSession session = req.getSession();
        String lang=(String)session.getAttribute(Constants.PARAM_LOCAL);
        profile.setIdTariffs(Integer.parseInt(req.getParameter("change_tariff")));
        CommandResult res = new CommandResult(CommandResult.CommandResultState.REDIRECT_LOGIN);
        try {
            if (profileService.updateUsersTariff(profile.getProfileId(), profile.getIdTariffs())) {
                Tariff tariff = tariffService.getTariffById(profile.getIdTariffs(), lang);
                session.setAttribute(Constants.ATT_SESSION_PROFILE_TARIFF, tariff);
                res.appendParamToRedirect(Constants.PARAM_SUCCESS_MESSAGE, "you have successfully switched your tariff to: " + tariff.getName());
            }
        } catch (ServiceException e) {
            res.appendParamToRedirect(Constants.PARAM_ERROR_MESSAGE, "error");
        }
        return res;
    }


}
