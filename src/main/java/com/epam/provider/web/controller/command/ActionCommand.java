package com.epam.provider.web.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Main interface
 *
 * @author Gleb Aksenov
 *         Interface of Command pattern which is using to delegate action from controller to separate classes
 */
public interface ActionCommand {
    /**
     * Executes single command on demand of client
     *
     * @param req needed to get data and set session attributes
     * @return {@link CommandResult}
     */
    CommandResult execute(HttpServletRequest req);
}
