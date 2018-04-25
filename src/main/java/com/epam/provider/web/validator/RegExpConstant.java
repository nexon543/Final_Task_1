package com.epam.provider.web.validator;


public class RegExpConstant {
    public static final String PASSWORD_REGEX = "^[A-Za-z0-9_-]{4,16}$";
            //"(?=^.{4,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    public static final String NAME_REGEX = "^[А-Яа-яa-zA-Z0-9-]{3,16}$";
    public static final String LOGIN_REGEX = "^[A-Za-z0-9_-]{4,16}$";
    public static final String INTEGER_REGEX = "[0-9]{1,10}";
    public static final String PASSPORT_REGEX = "^[A-Za-z0-9_-]{5,10}$)";
    public static final String ROLE="client|admin";
    public static final String DESCRIPTION="^[A-Za-z0-9_-]{2,1500}$";
    public static final String TARIFF_NAME_REGEX = "^[А-Яа-яa-zA-Z0-9-]{3,50}$";
}