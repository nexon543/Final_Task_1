package com.epam.provider.web.tag;


import com.epam.provider.model.Profile;
import com.epam.provider.util.Localizer;
import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.web.controller.command.Constants;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ResourceBundle;

public class LoginButtonTextTag extends TagSupport {

    private static final Logger LOGGER = LogManager.getLogger(LoginButtonTextTag.class);
    private Localizer localizer = new Localizer();

    @Override
    public int doStartTag() throws JspException {
        String locName = (String) pageContext.getSession().getAttribute(Constants.PARAM_LOCAL);

        Profile user = (Profile) pageContext.getSession().getAttribute(Constants.PARAM_USER);
        ResourceBundle bundle = ResourceBundle.getBundle(ResourceConstants.RESOURCE_PATH_LOCALIZATION);
        try {
            String buttonText = localizer.getLocalizedText(locName, ResourceConstants.MESSAGE_KEY_BUTTON_LOGIN);
            if (user == null) {
                pageContext.getOut().write(buttonText);
            } else {
                pageContext.getOut().write("Go to cabinet");
            }
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }

        return SKIP_BODY;
    }
}
