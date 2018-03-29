package com.epam.provider.controller.command.impl;

import com.epam.provider.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by HP on 27.03.2018.
 */
public class EmptyCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) {
        return null;
    }
}
