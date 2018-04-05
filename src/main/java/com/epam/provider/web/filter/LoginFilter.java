package com.epam.provider.web.filter;

import com.epam.provider.web.controller.command.Constants;
import com.epam.provider.model.User;
import com.epam.provider.util.resource.ConfigResourceManager;
import com.epam.provider.util.resource.ResourceConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@WebFilter(urlPatterns = "/jsp/login.jsp")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)servletRequest;
        HttpSession session=req.getSession();
        User user=(User)session.getAttribute(Constants.PARAM_NAME_USER);
        if (user != null){
            if (Constants.ROLE_NAME_ADMIN.equals(user.getRole())){
                req.getRequestDispatcher(ConfigResourceManager.getPagePath(ResourceConstants.PAGE_NAME_ADMIN)).forward(servletRequest, servletResponse);
            }
            else{
                req.getRequestDispatcher(ConfigResourceManager.getPagePath(ResourceConstants.PAGE_NAME_CLIENT)).forward(servletRequest, servletResponse);
            }
        }
        else{
            req.getRequestDispatcher(ConfigResourceManager.getPagePath(ResourceConstants.PAGE_NAME_LOGIN)).forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
