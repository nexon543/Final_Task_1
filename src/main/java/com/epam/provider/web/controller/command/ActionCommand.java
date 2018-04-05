package com.epam.provider.web.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by HP on 27.03.2018.
 */
public interface ActionCommand {
    CommandResult execute(HttpServletRequest req);
}
