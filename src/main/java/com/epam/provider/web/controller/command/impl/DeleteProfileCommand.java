package com.epam.provider.web.controller.command.impl;

import com.epam.provider.service.ProfileService;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.ServiceFactory;
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

public class DeleteProfileCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(DeleteTariffCommand.class);
    private ProfileService profileService= ServiceFactory.getProfileService();

    @Override
    public CommandResult execute(HttpServletRequest req) {
        CommandResult res = new CommandResult(CommandResult.CommandResultState.CONTROLLER_GET_PROFILE);
        String idStr = req.getParameter(Constants.PARAM_DELET_ENTITY_ID);
        if (idStr != null) {
            Integer id = Integer.parseInt(idStr);
            try {
                profileService.deleteProfile(id);
                if (profileService.getById(id) == null) {
                    res.appendParamToRedirect(Constants.PARAM_SUCCESS_MESSAGE, ResourceManager.getMessage(ResourceConstants.M_SUCCESS_DELETE_PROFILE));
                }else{
                    res.appendParamToRedirect(Constants.PARAM_ERROR_MESSAGE, ResourceManager.getMessage(ResourceConstants.M_ERROR_DELETE_PROFILE));
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
            }
        }
        else {
            res.appendParamToRedirect(Constants.PARAM_ERROR_MESSAGE, ResourceManager.getMessage(ResourceConstants.M_ERROR_DELETE_PROFILE));
        }
        return res;
    }
}
