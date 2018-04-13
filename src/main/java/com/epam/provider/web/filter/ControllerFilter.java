package com.epam.provider.web.filter;


import com.epam.provider.util.SessionRequestContent;
import com.epam.provider.web.controller.command.ActionType;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;
import com.epam.provider.web.validator.Validator;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/Controller")
public class ControllerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String role = SessionRequestContent.getUserRole(req);
        String commandStr = req.getParameter(Constants.PARAM_COMMAND);
        CommandResult commandResult = new CommandResult();
        boolean isInvalid = false;
        try {
            ActionType command = ActionType.valueOf(commandStr.toUpperCase());
            isInvalid = !Validator.validateClient(role) && command.isClientCommand();
            isInvalid = isInvalid && (!Validator.validateAdmin(role) && command.isAdminCommand());
        } catch (Exception ex) {
            isInvalid = true;
        }
        if (isInvalid) {
            commandResult.setState(CommandResult.CommandResultState.REDIRECT_ERROR);
            resp.sendRedirect(commandResult.getPage());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
