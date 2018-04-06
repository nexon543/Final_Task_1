package com.epam.provider.web.controller;

import com.epam.provider.web.controller.command.ActionCommand;
import com.epam.provider.web.controller.command.ActionFactory;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/Controller")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ActionFactory client = new ActionFactory();
        String commandStr = req.getParameter(Constants.PARAM_NAME_COMMAND);
        ActionCommand command = client.defineCommand(commandStr);
        CommandResult commandResult = command.execute(req);
        String page = commandResult.getPage();
        if (CommandResult.ResponseType.FORWARD == (commandResult.getResponseType())) {
            req.getRequestDispatcher(page).forward(req, resp);
        } else {
            resp.sendRedirect(page);
        }
    }
}
