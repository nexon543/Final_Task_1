package com.epam.provider.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Created by HP on 03.04.2018.
 */
@WebFilter(urlPatterns = "/*", initParams = {@WebInitParam(name="encoding", value = "utf-8")})
public class EncodingFilter implements Filter {

    private String code;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();

        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        code = null;
    }
}