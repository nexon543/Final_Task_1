package com.epam.provider.web.controller.command;

import com.epam.provider.util.dependency.AppContext;
import com.epam.provider.util.dependency.DependencyException;
import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.web.controller.command.impl.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.EnumMap;

/**
 * @author Gleb Akseonov
 */
public class ActionFactory {

    private static EnumMap<ActionType, ActionCommand> actions;
    private static EnumMap<ActionType, AjaxActionCommand> ajaxActions;
    private static final Logger LOGGER = Logger.getLogger(ActionFactory.class);

    static {
        try {
            actions = new EnumMap<>(ActionType.class);
            actions.put(ActionType.LOGIN, (ActionCommand) AppContext.getBean(ResourceConstants.DEPNDENCY_LOGIN));
            actions.put(ActionType.LOGOUT, (ActionCommand) AppContext.getBean(ResourceConstants.DEPNDENCY_LOGOUT));
            actions.put(ActionType.GET_TARIFFS, new GetTariffsCommand());
            actions.put(ActionType.ADD_TARIFF, new AddTariffCommand());
            actions.put(ActionType.SET_LOCALE, new SetLocalCommand());
            actions.put(ActionType.ADD_BALANCE, (ActionCommand) AppContext.getBean(ResourceConstants.DEPNDENCY_ADD_BALANCE));
            actions.put(ActionType.ADD_PROFILE, new AddProfileCommand());
            actions.put(ActionType.CHANGE_TARIFF, new ChangeTariffCommand());
            actions.put(ActionType.GET_UPDATE_PAGE, new GetUpdatePageCommand());
            actions.put(ActionType.UPDATE_TARIFF, new UpdateTariffCommand());
            actions.put(ActionType.UPDATE_PROFILE, new UpdateProfileCommand());
            actions.put(ActionType.GET_PROFILES, (ActionCommand) AppContext.getBean(ResourceConstants.DEPNDENCY_GET_PROFILES));
            actions.put(ActionType.DELETE_TARIFF, new DeleteTariffCommand());
            actions.put(ActionType.DELETE_PROFILE, new DeleteProfileCommand());
        } catch (DependencyException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
        ajaxActions = new EnumMap<>(ActionType.class);
        ajaxActions.put(ActionType.GET_TARIFFS_AJAX, new GetTariffsAjaxCommand());
        ajaxActions.put(ActionType.ADD_BALANCE_AJAX, new AddBalanceAjaxCommand());
    }

    public static ActionCommand defineCommand(String commandName) {
        if (commandName == null) {
            return new EmptyCommand();
        }
        ActionType actionType = ActionType.valueOf(commandName.toUpperCase());
        return actions.get(actionType);
    }

    public static AjaxActionCommand defineAjaxCommand(String commandName) {
        ActionType actionType = ActionType.valueOf(commandName.toUpperCase());
        return ajaxActions.get(actionType);
    }
}
