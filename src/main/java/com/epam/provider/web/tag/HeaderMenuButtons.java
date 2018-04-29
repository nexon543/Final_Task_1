package com.epam.provider.web.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


public class HeaderMenuButtons extends TagSupport {

  @Override
  public int doAfterBody() throws JspException {
    StringBuilder buttons = new StringBuilder("<ul class=\"nav navbar-nav\">")
        .append(
            "<li class=\"active\"><a href=\"${pageContext.request.contextPath}/index.jsp\">Home</a></li>")
        .append(
            "<li><a href=\"${pageContext.request.contextPath}/Controller?command=get_tariffs\">Tariffs</a></li>")
        .append("</ul>");
    try {
      pageContext.getOut().write(buttons.toString());
    } catch (IOException e) {
      throw new JspException(e.getMessage());
    }
    return super.doAfterBody();
  }
}
