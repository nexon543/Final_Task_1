package com.epam.provider.web.validator;


import com.epam.provider.model.fields.ProfileField;
import com.epam.provider.model.fields.TableFieldName;
import com.epam.provider.model.fields.TariffField;
import com.epam.provider.web.controller.command.ActionType;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public enum ValidationParameters {

  LOGIN(ProfileField.LOGIN, RegExpConstant.LOGIN),
  PASS(ProfileField.PASS, RegExpConstant.PASSWORD),
  FIRST_NAME(ProfileField.FIRST_NAME, RegExpConstant.NAME),
  SECOND_NAME(ProfileField.SECOND_NAME, RegExpConstant.NAME),
  ID_TARIFFS(ProfileField.ID_TARIFFS, RegExpConstant.INTEGER),
  BALANCE(ProfileField.BALANCE, RegExpConstant.INTEGER),
  PASSPORT(ProfileField.PASSPORT, RegExpConstant.PASSPORT),
  ROLE(ProfileField.ROLE, RegExpConstant.ROLE),
  DESCRIPTION(TariffField.DESCRIPTION, RegExpConstant.DESCRIPTION),
  TARIFF_NAME(TariffField.NAME, RegExpConstant.TARIFF_NAME),
  TRANSFER_SPEED(TariffField.TRANSFER_SPEED, RegExpConstant.INTEGER),
  RECIEVE_SPEED(TariffField.RECIEVE_SPEED, RegExpConstant.INTEGER),
  PRICE(TariffField.PRICE, RegExpConstant.INTEGER);

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

  private TableFieldName name;
  private String regexp;

  ValidationParameters(TableFieldName name, String regexp) {
    this.name = name;
    this.regexp = regexp;
  }

  public static Set<ValidationParameters> getParamSet(ActionType action) {
    return validationFieldSet.get(action);
  }

  public String getName() {
    return name.getName();
  }

  public void setName(TableFieldName name) {
    this.name = name;
  }

  public String getRegexp() {
    return regexp;
  }

  public void setRegexp(String regexp) {
    this.regexp = regexp;
  }
}