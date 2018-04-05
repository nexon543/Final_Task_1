package com.epam.provider.web.controller.command;
import com.epam.provider.web.controller.command.impl.*;

import java.util.EnumMap;

/**
 * Created by HP on 27.03.2018.
 */
public class ActionFactory {
    private static EnumMap<ActionType, ActionCommand> actions;
    static {
        actions=new EnumMap<>(ActionType.class);
        actions.put(ActionType.LOGIN, new LoginCommand());
        actions.put(ActionType.LOGOUT, new LogoutCommand());
        actions.put(ActionType.GET_TARIFFS, new GetTariffsCommand());
        actions.put(ActionType.ADD_TARIFF, new AddTariffCommand());

    }
    public static ActionCommand defineCommand(String commandName) {
        if(commandName == null){
            return new EmptyCommand();
        }
        ActionType actionType=ActionType.GET_TARIFFS.valueOf(commandName.toUpperCase());
        return actions.get(actionType);
    }
}
