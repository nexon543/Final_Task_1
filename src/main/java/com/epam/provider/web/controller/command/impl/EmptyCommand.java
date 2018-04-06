package com.epam.provider.web.controller.command.impl;

import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.CommandResult;

import javax.servlet.http.HttpServletRequest;


public class EmptyCommand implements ActionCommand {

    @Override
    public CommandResult execute(HttpServletRequest req) {
        return null;
    }
}
