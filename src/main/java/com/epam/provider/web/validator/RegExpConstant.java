package com.epam.provider.web.validator;


public class RegExpConstant {
    public static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])\\w{6,15}$";
    public static final String NAME_REGEX = "^[А-Яа-яa-zA-Z0-9-]{3,16}$";
    public static final String LOGIN_REGEX = "^[A-Za-z0-9_-]{5,16}$";
    public static final String INTEGER_REGEX = "\\d(1,11)";
    public static final String PASSPORT_REGEX = "^([MP])([\\d]{6})";
    public static final String ROLE="client|admin";
    public static final String DESCRIPTION="^[A-Za-z0-9_-]{10,200}$";
    public static final String TARIFF_NAME_REGEX = "^[А-Яа-яa-zA-Z0-9-]{3,50}$";
}