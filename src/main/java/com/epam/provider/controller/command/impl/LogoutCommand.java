package com.epam.provider.controller.command.impl;

import com.epam.provider.controller.command.ActionCommand;
import com.epam.provider.controller.command.CommandResult;
import com.epam.provider.util.resource.ConfigResourceManager;
import com.epam.provider.util.resource.ResourceConstants;

import javax.servlet.http.HttpServletRequest;
import java.net.Authenticator;

/**
 * Created by HP on 27.03.2018.
 */
public class LogoutCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest req) {
        CommandResult result=new CommandResult(CommandResult.ResponseType.FORWARD, ConfigResourceManager.getPagePath(ResourceConstants.PAGE_NAME_INDEX));
        req.getSession().invalidate();
        return result;
    }
}
