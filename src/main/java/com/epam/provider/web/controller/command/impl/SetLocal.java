package com.epam.provider.web.controller.command.impl;

import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.util.resource.ResourceManager;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Gleb Akseonov
 */
public class SetLocal implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest req) {
        CommandResult result = new CommandResult();
        result.setResponseType(CommandResult.ResponseType.REDIRECT);
        String reqUri=req.getRequestURI();

        result.setPage(ResourceManager.getPagePath(ResourceConstants.PAGE_NAME_INDEX_REDIRECT));
        String local = req.getParameter(Constants.PARAM_LOCAL);
        req.getSession().setAttribute(Constants.PARAM_LOCAL, local);
        return result;
    }
}
