package com.epam.provider.web.controller.command.impl;

import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.util.resource.ConfigResourceManager;
import com.epam.provider.util.resource.ResourceConstants;

import javax.servlet.http.HttpServletRequest;


public class LogoutCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest req) {
        CommandResult result=new CommandResult(CommandResult.ResponseType.FORWARD, ConfigResourceManager.getPagePath(ResourceConstants.PAGE_NAME_INDEX));
        req.getSession().invalidate();
        return result;
    }
}
