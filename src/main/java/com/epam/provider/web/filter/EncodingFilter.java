package com.epam.provider.web.filter;

import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.web.controller.command.Constants;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class EncodingFilter implements Filter {

  private String code;

  @Override
  public void init(FilterConfig filterConfig) {
    code = filterConfig.getInitParameter("encoding");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String codeRequest = request.getCharacterEncoding();
    String contentType = request.getContentType();
    if (contentType != null && contentType.startsWith("application/x-www-form-urlencoded")) {
      request.setCharacterEncoding(code);
    }

    if (code != null && !code.equalsIgnoreCase(codeRequest)) {
      request.setCharacterEncoding(code);
      response.setCharacterEncoding(code);
    }
    HttpSession session = ((HttpServletRequest) request).getSession();
    String lang = (String) session.getAttribute(Constants.PARAM_LOCAL);
    if (lang == null) {
      session.setAttribute(Constants.PARAM_LOCAL, ResourceConstants.M_LANG_EN);
    }
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    code = null;
  }
}
