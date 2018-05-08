package com.epam.provider.web.controller.command;

import com.epam.provider.web.controller.command.impl.*;

import java.util.EnumMap;

/**
 * @author Gleb Akseonov
 */
public class ActionFactory {

  private static EnumMap<ActionType, ActionCommand> actions;
  private static EnumMap<ActionType, AjaxActionCommand> ajaxActions;

  static {
    actions = new EnumMap<>(ActionType.class);
    actions.put(ActionType.LOGIN, new LoginCommand());
    actions.put(ActionType.LOGOUT, new LogoutCommand());
    actions.put(ActionType.GET_TARIFFS, new GetTariffsCommand());
    actions.put(ActionType.ADD_TARIFF, new AddTariffCommand());
    actions.put(ActionType.SET_LOCALE, new SetLocal());
    actions.put(ActionType.ADD_BALANCE, new AddBalance());
    actions.put(ActionType.ADD_PROFILE, new AddProfileCommand());
    actions.put(ActionType.CHANGE_TARIFF, new ChangeTariffCommand());
    actions.put(ActionType.GET_UPDATE_PAGE, new GetUpdatePageCommand());
    actions.put(ActionType.UPDATE_TARIFF, new UpdateTariffCommand());
    actions.put(ActionType.UPDATE_PROFILE, new UpdateProfileCommand());
    actions.put(ActionType.GET_PROFILES, new GetProfilesCommand());
    actions.put(ActionType.DELETE_TARIFF, new DeleteTariffCommand());
    actions.put(ActionType.DELETE_PROFILE, new DeleteProfileCommand());

    ajaxActions = new EnumMap<>(ActionType.class);
    ajaxActions.put(ActionType.GET_TARIFFS_AJAX, new GetTariffsAjaxCommand());

  }

  public static ActionCommand defineCommand(String commandName) {
    if (commandName == null) {
      return new EmptyCommand();
    }
    ActionType actionType = ActionType.valueOf(commandName.toUpperCase());
    return actions.get(actionType);
  }

  public static AjaxActionCommand defineAjaxCommand(String commandName){
    return ajaxActions.get(commandName);
  }
}
