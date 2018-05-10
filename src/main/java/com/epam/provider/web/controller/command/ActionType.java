package com.epam.provider.web.controller.command;

import com.epam.provider.web.validator.ValidationParameters;
import java.util.EnumSet;

/**
 * @author Gleb Akseonov
 */
public enum ActionType {

  LOGIN,
  LOGOUT,
  GET_TARIFFS(CommandOwner.ANY),
  GET_TARIFFS_AJAX(CommandOwner.ANY),
  ADD_TARIFF(CommandOwner.ADMIN),
  DELETE_TARIFF(CommandOwner.ADMIN),
  SET_LOCALE,
  ADD_BALANCE(CommandOwner.CLIENT),
  CHANGE_TARIFF(CommandOwner.CLIENT_ADMIN),
  ADD_PROFILE(CommandOwner.ADMIN),
  GET_PROFILES(CommandOwner.ADMIN),
  UPDATE_TARIFF(CommandOwner.ADMIN),
  UPDATE_PROFILE(CommandOwner.CLIENT_ADMIN),
  DELETE_PROFILE(CommandOwner.ADMIN),
  GET_UPDATE_PAGE(CommandOwner.ADMIN);

  private CommandOwner owner;
  private EnumSet<ValidationParameters> validationParameters;

  ActionType(CommandOwner owner) {
    this.owner = owner;
  }

  ActionType() {
    this.owner = CommandOwner.ANY;
  }

  public boolean isAdminCommand() {
    return owner == CommandOwner.ADMIN || owner == CommandOwner.CLIENT_ADMIN
        || owner == CommandOwner.ANY;
  }

  public boolean isClientCommand() {
    return owner == CommandOwner.CLIENT || owner == CommandOwner.CLIENT_ADMIN
        || owner == CommandOwner.ANY;
  }

  public boolean isAnyCommand() {
    return owner == CommandOwner.ANY;
  }

  enum CommandOwner {
    ADMIN, CLIENT, CLIENT_ADMIN, ANY
  }

}
