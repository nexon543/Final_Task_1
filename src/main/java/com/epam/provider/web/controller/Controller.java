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

/**
 * The class provides controller for client requests of application.
 *
 * @author Gleb Aksenov
 * @see HttpServlet
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {

    /**
     * @param req  request from client to get parameters
     * @param resp response to client with parameters to work with on client side
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     * @throws ServletException if the request could not be handled
     * @see HttpServletRequest
     * @see HttpServletResponse
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }


    /**
     * @param req  request from client to get parameters
     * @param resp response to client with parameters to work with on client side
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     * @throws ServletException if the request could not be handled
     * @see HttpServletRequest
     * @see HttpServletResponse
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * processRequest
     * Defines command name  and directs request to command
     * If an error occurs, it is redirected to the error page.
     *
     * @param req  request from client to get parameters to work with
     * @param resp response to client with parameters to work with on client side
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     * @throws ServletException if the request could not be handled
     * @see ActionCommand
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandStr = req.getParameter(Constants.PARAM_COMMAND);
        ActionCommand command = ActionFactory.defineCommand(commandStr);
        CommandResult commandResult = command.execute(req);
        String page = commandResult.getPage();
        if (CommandResult.ResponseType.FORWARD == (commandResult.getResponseType())) {
            req.getRequestDispatcher(page).forward(req, resp);
        } else {
            resp.sendRedirect(page);
        }
    }
}
