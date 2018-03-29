package com.epam.provider.controller.command.impl;

import com.epam.provider.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by HP on 27.03.2018.
 */
public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        String page="index.jsp";
        req.getSession().invalidate();
        return page;
    }
}
