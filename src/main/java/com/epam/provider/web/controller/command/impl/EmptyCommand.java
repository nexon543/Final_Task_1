package com.epam.provider.web.controller.command.impl;

import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.util.resource.ResourceManager;
import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;

import javax.servlet.http.HttpServletRequest;

/**
 * This command is returned when there no commands to return
 *
 * @author Gleb Aksenov
 * {@link ActionCommand}  invokes method execute()
 */
public class EmptyCommand implements ActionCommand {

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(HttpServletRequest req) {
        CommandResult result = new CommandResult(CommandResult.ResponseType.REDIRECT,
                ResourceManager.getPagePath(ResourceConstants.PAGE_NAME_INDEX_REDIRECT));
        return result;
    }
}
