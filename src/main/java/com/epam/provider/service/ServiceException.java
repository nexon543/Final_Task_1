package com.epam.provider.service;


public class ServiceException extends Exception {

  public final static String MESS_SEARCH_USER_ERROR = "Can't execute serach user by login pass";
  public final static String MESS_SEARCH_PROFILE_ERROR = "Can't find profile for user";

  public ServiceException(String message) {
    super(message);
  }

  public ServiceException(String message, Exception e) {
    super(message, e);
  }
}
