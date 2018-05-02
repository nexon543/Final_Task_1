package com.epam.provider.web.controller.command;

import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

public interface AjaxActionCommand {
    JSONObject execute(HttpServletRequest req);
}
