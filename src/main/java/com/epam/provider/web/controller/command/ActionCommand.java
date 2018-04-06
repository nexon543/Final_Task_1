package com.epam.provider.web.controller.command;

import javax.servlet.http.HttpServletRequest;


public interface ActionCommand {
    CommandResult execute(HttpServletRequest req);
}
