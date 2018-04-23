package com.epam.provider.web.filter;

import com.epam.provider.model.Profile;
import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.util.resource.ResourceManager;
import com.epam.provider.web.controller.command.Constants;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(urlPatterns = "/Login")
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        Profile user = (Profile) session.getAttribute(Constants.PARAM_USER);
        if ((user != null) && (user.getProfileId() != null)) {
            if (Constants.ROLE_NAME_ADMIN.equals(user.getRole())) {
                req.getRequestDispatcher(ResourceManager.getPagePath(ResourceConstants.PAGE_NAME_ADMIN)).forward(servletRequest, servletResponse);
            } else {
                req.getRequestDispatcher(ResourceManager.getPagePath(ResourceConstants.PAGE_NAME_CLIENT)).forward(servletRequest, servletResponse);
            }
        } else {
            req.getRequestDispatcher(ResourceManager.getPagePath(ResourceConstants.PAGE_NAME_LOGIN)).forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}