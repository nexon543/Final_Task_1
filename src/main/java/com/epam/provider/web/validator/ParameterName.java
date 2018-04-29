package com.epam.provider.web.validator;


import com.epam.provider.web.controller.command.ActionType;
import com.epam.provider.web.controller.command.Constants;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Set;

public enum ParameterName {
  LOGIN(Constants.PARAM_LOGIN, RegExpConstant.LOGIN_REGEX),
  PASSWORD(Constants.PARAM_PASSWORD, RegExpConstant.PASSWORD_REGEX),
  FIRST_NAME(Constants.PARAM_FIRST_NAME, RegExpConstant.NAME_REGEX),
  SECOND_NAME(Constants.PARAM_SECOND_NAME, RegExpConstant.NAME_REGEX),
  ID_TARIFFS(Constants.PARAM_TARIFFS_ID, RegExpConstant.INTEGER_REGEX),
  BALANCE(Constants.PARAM_BALANCE, RegExpConstant.INTEGER_REGEX),
  PASSPORT(Constants.PARAM_PASSPORT, RegExpConstant.PASSPORT_REGEX),
  ROLE(Constants.PARAM_ROLE, RegExpConstant.ROLE),
  DESCRIPTION(Constants.PARAM_DESCRIPTION, RegExpConstant.DESCRIPTION),
  TARIFF_NAME(Constants.PARAM_TARIFF_NAME, RegExpConstant.TARIFF_NAME_REGEX),
  TRANSFER_SPEED(Constants.PARAM_TRANSFER_SPEED, RegExpConstant.INTEGER_REGEX),
  RECIEVING_SPEED(Constants.PARAM_RECEIVING_SPEED, RegExpConstant.INTEGER_REGEX),
  PRICE(Constants.PARAM_PRICE, RegExpConstant.INTEGER_REGEX);

  private static EnumMap<ActionType, EnumSet<ParameterName>> paramNameSets;

  static {
    paramNameSets = new EnumMap<>(ActionType.class);
    paramNameSets.put(ActionType.LOGIN, EnumSet.of(LOGIN, PASSWORD));
    paramNameSets.put(ActionType.ADD_BALANCE, EnumSet.of(BALANCE));
    paramNameSets.put(ActionType.ADD_PROFILE,
        EnumSet.of(FIRST_NAME, SECOND_NAME, PASSPORT, LOGIN, PASSWORD, ROLE, ID_TARIFFS));
    paramNameSets.put(ActionType.ADD_TARIFF,
        EnumSet.of(DESCRIPTION, RECIEVING_SPEED, TRANSFER_SPEED, TARIFF_NAME, PRICE));
    paramNameSets.put(ActionType.UPDATE_TARIFF, paramNameSets.get(ActionType.ADD_TARIFF));
    paramNameSets.put(ActionType.UPDATE_PROFILE, paramNameSets.get(ActionType.ADD_PROFILE));
  }

  private String name;
  private String regexp;

  ParameterName(String name, String regexp) {
    this.name = name;
    this.regexp = regexp;
  }

  public static Set<ParameterName> getParamSet(ActionType action) {
    return paramNameSets.get(action);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRegexp() {
    return regexp;
  }

  public void setRegexp(String regexp) {
    this.regexp = regexp;
  }

}