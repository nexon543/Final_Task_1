package com.epam.provider.web.controller.command;

import com.epam.provider.util.resource.ConfigResourceManager;
import com.epam.provider.util.resource.ResourceConstants;

/**
 * Created by HP on 29.03.2018.
 */
public class CommandResult {
    public enum ResponseType {
        FORWARD, REDIRECT
    }

    private ResponseType responseType;

    private String page;

    public CommandResult() {
        page = ConfigResourceManager.getPagePath(ResourceConstants.PAGE_NAME_ERROR);
        responseType = ResponseType.REDIRECT;
    }

    public CommandResult(ResponseType responseType, String page) {
        this.responseType = responseType;
        this.page = page;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "CommandResult{" +
                "responseType=" + responseType +
                ", page='" + page + '\'' +
                '}';
    }
}
