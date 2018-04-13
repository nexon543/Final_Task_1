package com.epam.provider.web.validator;


import com.epam.provider.web.controller.command.Constants;

public enum ParameterName {
    LOGIN(Constants.PARAM_LOGIN, RegExpConstant.LOGIN_REGEX),
    PASSWORD(Constants.PARAM_PASSWORD, RegExpConstant.PASSWORD_REGEX),
    FIRST_NAME(Constants.PARAM_FIRST_NAME, RegExpConstant.NAME_REGEX),
    SECOND_NAME(Constants.PARAM_SECOND_NAME, RegExpConstant.NAME_REGEX),
    ID_TARIFFS(Constants.PARAM_ID_TARIFFS, RegExpConstant.INTEGER_REGEX),
    BALANCE(Constants.PARAM_BALANCE, RegExpConstant.INTEGER_REGEX),
    PASSPORT(Constants.PARAM_PASSPORT, RegExpConstant.PASSPORT_REGEX);

    private String name;

    private String regexp;

    ParameterName(String name, String regexp) {
        this.name = name;
        this.regexp = regexp;
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