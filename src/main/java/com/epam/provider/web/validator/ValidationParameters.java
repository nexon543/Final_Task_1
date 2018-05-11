package com.epam.provider.web.validator;


import com.epam.provider.model.Field;
import com.epam.provider.web.controller.command.ActionType;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public enum ValidationParameters {

  LOGIN(Field.PROFILE_LOGIN, RegExpConstant.LOGIN),
  PASS(Field.PROFILE_PASS, RegExpConstant.PASSWORD),
  FIRST_NAME(Field.PROFILE_FIRST_NAME, RegExpConstant.NAME),
  SECOND_NAME(Field.PROFILE_SECOND_NAME, RegExpConstant.NAME),
  ID_TARIFFS(Field.PROFILE_ID_TARIFFS, RegExpConstant.INTEGER),
  BALANCE(Field.PROFILE_BALANCE, RegExpConstant.INTEGER),
  PASSPORT(Field.PROFILE_PASSPORT, RegExpConstant.PASSPORT),
  ROLE(Field.PROFILE_ROLE, RegExpConstant.ROLE),
  DESCRIPTION(Field.TARIFF_DESCRIPTION, RegExpConstant.DESCRIPTION),
  TARIFF_NAME(Field.TARIFF_NAME, RegExpConstant.TARIFF_NAME),
  TRANSFER_SPEED(Field.TARIFF_TRANSFER_SPEED, RegExpConstant.INTEGER),
  RECIEVE_SPEED(Field.TARIFF_RECEIVE_SPEED, RegExpConstant.INTEGER),
  PRICE(Field.TARIFF_PRICE, RegExpConstant.INTEGER);

  private static Map<ActionType, Set<ValidationParameters>> validationFieldSet;

  static {
    validationFieldSet = new EnumMap<>(ActionType.class);
    validationFieldSet.put(ActionType.LOGIN, EnumSet.of(LOGIN, PASS));
    validationFieldSet.put(ActionType.ADD_BALANCE, EnumSet.of(BALANCE));
    validationFieldSet.put(ActionType.ADD_PROFILE,
        EnumSet.of(FIRST_NAME, SECOND_NAME, PASSPORT, LOGIN, PASS, ROLE, ID_TARIFFS));
    validationFieldSet.put(ActionType.ADD_TARIFF,
        EnumSet.of(DESCRIPTION, RECIEVE_SPEED, TRANSFER_SPEED, TARIFF_NAME, PRICE));
    validationFieldSet.put(ActionType.UPDATE_TARIFF, validationFieldSet.get(ActionType.ADD_TARIFF));
    validationFieldSet
        .put(ActionType.UPDATE_PROFILE, validationFieldSet.get(ActionType.ADD_PROFILE));
  }

  private Field name;
  private String regexp;

  ValidationParameters(Field name, String regexp) {
    this.name = name;
    this.regexp = regexp;
  }

  public static Set<ValidationParameters> getParamSet(ActionType action) {
    return validationFieldSet.get(action);
  }

  public String getName() {
    return name.getName();
  }

  public void setName(Field name) {
    this.name = name;
  }

  public String getRegexp() {
    return regexp;
  }

  public void setRegexp(String regexp) {
    this.regexp = regexp;
  }
}