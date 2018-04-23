package com.epam.provider.web.controller.command;

import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.util.resource.ResourceManager;

import static com.epam.provider.web.controller.command.CommandResult.CommandResultState.FORWARD_CLIENT;

/**
 * This is a data structure for storing command execution result
 *
 * @author Gleb Aksenov
 *         {@link ActionCommand}  invokes method execute()
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

    public void setControllerRequest(ActionType command) {
        page = "/Controller?command=" + command;
        responseType=ResponseType.REDIRECT;
    }

    public void setState(CommandResultState state) {
        switch (state) {
            case REDIRECT_INDEX:
                page = ResourceManager.getPagePath(ResourceConstants.PAGE_NAME_INDEX);
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
            case REDIRECT_ADMIN:
                setPage(ResourceManager.getPagePath(ResourceConstants.PAGE_NAME_ADMIN_REDIRECT));
                setResponseType(ResponseType.REDIRECT);
                break;
            case GET_TARIFFS:
                setControllerRequest(ActionType.GET_TARIFFS);
                setResponseType(ResponseType.REDIRECT);
                break;
            case FORWARD_ADD_PROFILE:
                responseType=ResponseType.FORWARD;
                page=ResourceManager.getPagePath(ResourceConstants.PAGE_NAME_ADD_PROFILE);
                break;
            case FORWARD_UPDATE_PROFILE:
                responseType=ResponseType.FORWARD;
                page=ResourceManager.getPagePath(ResourceConstants.PAGE_NAME_UPDATE_PROFILE);
                break;
            case FORWARD_UPDATE_TARIFF:
                responseType=ResponseType.FORWARD;
                page=ResourceManager.getPagePath(ResourceConstants.PAGE_NAME_UPDATE_TARIFF);
                break;
            case FORWARD_ADD_TARIFF:
                responseType=ResponseType.FORWARD;
                page=ResourceManager.getPagePath(ResourceConstants.PAGE_NAME_ADD_TARIFF);
                break;
            case FORWARD_SHOW_PROFILE:
                responseType=ResponseType.FORWARD;
                page=ResourceManager.getPagePath(ResourceConstants.PAGE_NAME_SHOW_PROFILES);
                break;
            case FORWARD_CLIENT:
                responseType=ResponseType.FORWARD;
                page=ResourceManager.getPagePath(ResourceConstants.PAGE_NAME_CLIENT);
                break;
            case CONTROLLER_GET_PROFILE:
                responseType=ResponseType.REDIRECT;
                setControllerRequest(ActionType.GET_PROFILES);
                break;
            case GET_UPDATE_PAGE:
                responseType=ResponseType.REDIRECT;
                setControllerRequest(ActionType.GET_UPDATE_PAGE);
                break;
        }
    }

    public void appendParamToRedirect(String paramKey, String paramValue) {
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
        GET_TARIFFS, REDIRECT_INDEX, REDIRECT_ERROR, REDIRECT_LOGIN,
        FORWARD_LOGIN, FORWARD_TARIFF, FORWARD_ADMIN, REDIRECT_ADMIN,
        CONTROLLER, FORWARD_CLIENT, REDIRECT_CLIENT, FORWARD_UPDATE_TARIFF,
        FORWARD_UPDATE_PROFILE, FORWARD_ADD_PROFILE, FORWARD_ADD_TARIFF,
        FORWARD_SHOW_PROFILE, CONTROLLER_GET_PROFILE, GET_UPDATE_PAGE
    }
}
