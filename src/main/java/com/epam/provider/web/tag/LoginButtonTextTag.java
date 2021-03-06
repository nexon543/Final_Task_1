package com.epam.provider.web.tag;

import com.epam.provider.model.Profile;
import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.util.resource.ResourceManager;
import com.epam.provider.web.controller.command.Constants;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LoginButtonTextTag extends TagSupport {

  private static final Logger LOGGER = LogManager.getLogger(LoginButtonTextTag.class);

  @Override
  public int doStartTag() throws JspException {
    String locName = (String) pageContext.getSession().getAttribute(Constants.PARAM_LOCAL);
    Profile user = (Profile) pageContext.getSession().getAttribute(Constants.PARAM_USER);
    try {
      String buttonText = ResourceManager.getMessage(ResourceConstants.M_BUTTON_LOGIN, locName);
      String buttonCabText = ResourceManager
          .getMessage(ResourceConstants.M_BUTTON_LOGIN_CABINET, locName);
      if (user == null) {
        pageContext.getOut().write(buttonText);
      } else {
        pageContext.getOut().write(buttonCabText);
      }
    } catch (IOException e) {
      LOGGER.log(Level.ERROR, e.getMessage());
      throw new JspException("Error writing text of login button");
    }

    return SKIP_BODY;
  }
}