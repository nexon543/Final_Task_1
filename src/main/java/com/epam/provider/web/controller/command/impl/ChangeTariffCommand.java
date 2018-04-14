package com.epam.provider.web.controller.command.impl;

import com.epam.provider.service.ProfileService;
import com.epam.provider.service.ServiceFactory;
import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.util.resource.ResourceManager;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;

import javax.servlet.http.HttpServletRequest;

public class ChangeTariffCommand implements ActionCommand {

    ProfileService profileService;

    public ChangeTariffCommand() {
        profileService = ServiceFactory.getProfileService();
    }

    @Override
    public CommandResult execute(HttpServletRequest req) {
        CommandResult result = new CommandResult(CommandResult.ResponseType.FORWARD, ResourceManager.getPagePath(ResourceConstants.PAGE_NAME_LOGIN));

        return result;
    }
}
