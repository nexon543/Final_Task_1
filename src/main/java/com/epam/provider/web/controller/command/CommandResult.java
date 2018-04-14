package com.epam.provider.web.controller.command;

import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.util.resource.ResourceManager;

/**
 * This is a data structure for storing command execution result
 *
 * @author Gleb Aksenov
 * {@link ActionCommand}  invokes method execute()
 */
public class CommandResult {
    private ResponseType responseType;
    private String page;

    public CommandResult() {

    }

    public CommandResult(ResponseType responseType, String page) {
        this.responseType = responseType;
        this.page = page;
    }

    public CommandResult(CommandResultState state) {
        setState(state);
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

    public void setController(ActionType command) {
        page = "/Controller?command=" + command;
    }

    public void setState(CommandResultState state) {
        switch (state) {
            case REDIRECT_TARIFFS:
                responseType = ResponseType.REDIRECT;
                page = Constants.REQUEST_TARIFFS;
                break;
            case REDIRECT_INDEX:
                page = ResourceManager.getPagePath(ResourceConstants.PAGE_NAME_ERROR);
                responseType = ResponseType.REDIRECT;
                break;
            case REDIRECT_ERROR:
                responseType = ResponseType.FORWARD;
                page = ResourceManager.getPagePath(ResourceConstants.PAGE_NAME_ERROR);
                break;
            case REDIRECT_LOGIN:
                responseType = ResponseType.REDIRECT;
                page = Constants.REQUEST_LOGIN;
                break;
            case FORWARD_LOGIN:
                responseType = ResponseType.FORWARD;
                page = ResourceManager.getPagePath(ResourceConstants.PAGE_NAME_LOGIN);
                break;
            case FORWARD_TARIFF:
                page = ResourceManager.getPagePath(ResourceConstants.PAGE_NAME_TARIFF);
                responseType = ResponseType.FORWARD;
                break;
            case FORWARD_ADMIN:
                setPage(ResourceManager.getPagePath(ResourceConstants.PAGE_NAME_ADMIN));
                responseType = ResponseType.FORWARD;
                break;

        }
    }

    public void appendToRedirectParam(String paramKey, String paramValue) {
        String separator = "&";
        if (!page.contains("?")) {
            separator = "?";
        }
        page += separator + paramKey + "=" + paramValue;
    }

    @Override
    public String toString() {
        return "CommandResult{" +
                "responseType=" + responseType +
                ", page='" + page + '\'' +
                '}';
    }

    public enum ResponseType {
        FORWARD, REDIRECT
    }

    public enum CommandResultState {
        REDIRECT_TARIFFS, REDIRECT_INDEX, REDIRECT_ERROR, REDIRECT_LOGIN,
        FORWARD_LOGIN, FORWARD_TARIFF, FORWARD_ADMIN, REDIRECT_ADMIN, CONTROLLER
    }


}
