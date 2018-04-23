package com.epam.provider.web.filter;

import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.util.resource.ResourceManager;
import com.epam.provider.web.controller.command.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


//@WebFilter(urlPatterns = "/*", initParams = {@WebInitParam(name = "encoding", value = "utf-8")})
public class EncodingFilter implements Filter {

    private String code;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        String contentType = request.getContentType();
        if (contentType != null && contentType.startsWith("application/x-www-form-urlencoded")) {
            request.setCharacterEncoding(code);
        }

        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        HttpSession session = ((HttpServletRequest)request).getSession();
        String lang=(String)session.getAttribute(Constants.PARAM_LOCAL);
        if (lang == null){
            session.setAttribute(Constants.PARAM_LOCAL, ResourceConstants.M_LANG_EN);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        code = null;
    }
}
