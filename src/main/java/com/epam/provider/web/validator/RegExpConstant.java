package com.epam.provider.web.validator;


public class RegExpConstant {

  public static final String PASSWORD = "^[A-Za-z0-9_-]{4,16}$";
  public static final String NAME = "^[А-Яа-яa-zA-Z0-9-]{3,16}$";
  public static final String LOGIN = "^[A-Za-z0-9_-]{4,16}$";
  public static final String INTEGER = "[0-9]{1,10}";
  public static final String PASSPORT = "^[A-Za-z0-9_-]{5,10}$";
  public static final String ROLE = "client|admin";
  public static final String DESCRIPTION = "^[A-Za-z0-9_-]{2,1500}$";
  public static final String TARIFF_NAME = "^[А-Яа-яa-zA-Z0-9-]{3,50}$";
  
}