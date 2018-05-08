package com.epam.provider.web.controller;

import com.epam.provider.web.controller.command.ActionFactory;
import com.epam.provider.web.controller.command.AjaxActionCommand;
import com.epam.provider.web.controller.command.Constants;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(Constants.PARAM_COMMAND);
        AjaxActionCommand command = ActionFactory.defineAjaxCommand(commandName);
        JSONObject result = command.execute(request);
        response.getWriter().write(result.toString());
    }
}
