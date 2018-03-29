package com.epam.provider.controller.command;
import com.epam.provider.controller.command.impl.EmptyCommand;
import com.epam.provider.controller.command.impl.LoginCommand;
import com.epam.provider.controller.command.impl.LogoutCommand;

import java.util.EnumMap;

/**
 * Created by HP on 27.03.2018.
 */
public class ActionFactory {
    private EnumMap<ActionType, ActionCommand> actions;
    public ActionFactory(){
        actions=new EnumMap<>(ActionType.class);
        actions.put(ActionType.LOGIN, new LoginCommand());
        actions.put(ActionType.LOGOUT, new LogoutCommand());
    }
    public ActionCommand defineCommand(String commandName) {
        if(commandName == null){
            return new EmptyCommand();
        }
        ActionType actionType=ActionType.GET_TARIFFS.valueOf(commandName.toUpperCase());
        return actions.get(actionType);
    }
}
