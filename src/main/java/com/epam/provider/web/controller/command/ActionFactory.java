package com.epam.provider.web.controller.command;

import com.epam.provider.web.controller.command.impl.AddBalance;
import com.epam.provider.web.controller.command.impl.AddProfileCommand;
import com.epam.provider.web.controller.command.impl.AddTariffCommand;
import com.epam.provider.web.controller.command.impl.ChangeTariffCommand;
import com.epam.provider.web.controller.command.impl.DeleteProfileCommand;
import com.epam.provider.web.controller.command.impl.DeleteTariffCommand;
import com.epam.provider.web.controller.command.impl.EmptyCommand;
import com.epam.provider.web.controller.command.impl.GetProfilesCommand;
import com.epam.provider.web.controller.command.impl.GetTariffsCommand;
import com.epam.provider.web.controller.command.impl.GetUpdatePageCommand;
import com.epam.provider.web.controller.command.impl.LoginCommand;
import com.epam.provider.web.controller.command.impl.LogoutCommand;
import com.epam.provider.web.controller.command.impl.SetLocal;
import com.epam.provider.web.controller.command.impl.UpdateProfileCommand;
import com.epam.provider.web.controller.command.impl.UpdateTariffCommand;
import java.util.EnumMap;

/**
 * @author Gleb Akseonov
 */
public class ActionFactory {

  private static EnumMap<ActionType, ActionCommand> actions;

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
  }

  public static ActionCommand defineCommand(String commandName) {
    if (commandName == null) {
      return new EmptyCommand();
    }
    ActionType actionType = ActionType.valueOf(commandName.toUpperCase());
    return actions.get(actionType);
  }
}
