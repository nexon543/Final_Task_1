package com.epam.provider.web.tag;


import com.epam.provider.model.User;
import com.epam.provider.web.controller.command.Constants;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class LoginButtonTextTag extends TagSupport {

    private static final Logger LOGGER = LogManager.getLogger(LoginButtonTextTag.class);

    @Override
    public int doStartTag() throws JspException {
        User user = (User) pageContext.getSession().getAttribute(Constants.PARAM_NAME_USER);
        try {
            if (user == null) {
                pageContext.getOut().write("Login");
            }
            else {
                pageContext.getOut().write("Go to cabinet");
            }
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }

        return SKIP_BODY;
    }
}
