package com.epam.provider.web.filter;


import com.epam.provider.util.RequestContent;
import com.epam.provider.web.controller.command.ActionType;
import com.epam.provider.web.controller.command.CommandResult;
import com.epam.provider.web.controller.command.Constants;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@WebFilter(urlPatterns = "/Controller")
public class ControllerFilter implements Filter {

  private static final Logger LOGGER = LogManager.getLogger(ControllerFilter.class);

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    RequestContent.initSession((HttpServletRequest)servletRequest);
    HttpServletRequest req = (HttpServletRequest) servletRequest;
    HttpServletResponse resp = (HttpServletResponse) servletResponse;
    String role = RequestContent.getCurrentUserRole();
    String commandStr = req.getParameter(Constants.PARAM_COMMAND);
    CommandResult commandResult = new CommandResult();
    boolean isValid = false;
    try {
      ActionType command = ActionType.valueOf(commandStr.toUpperCase());
      isValid = command.isAnyCommand();
      isValid = isValid || (Constants.ROLE_NAME_CLIENT.equals(role) && command.isClientCommand());
      isValid = isValid || (Constants.ROLE_NAME_ADMIN.equals(role) && command.isAdminCommand());
    } catch (Exception ex) {
      ex.printStackTrace();
      LOGGER.log(Level.ERROR, ex.getStackTrace());
      isValid = false;
    }
    if (!isValid) {
      commandResult.setState(CommandResult.CommandResultState.REDIRECT_ERROR);
      resp.sendRedirect(commandResult.getPage());
      LOGGER.log(Level.ERROR, "error operation");
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {

  }
}
