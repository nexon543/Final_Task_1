package com.epam.provider.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by HP on 27.03.2018.
 */
public interface ActionCommand {
    public CommandResult execute(HttpServletRequest req);
}
