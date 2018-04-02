package com.epam.provider.service;

/**
 * Created by HP on 29.03.2018.
 */
public class ServiceException extends Exception {
    public final static String MESS_SEARCH_USER_ERROR="Can't execute serach user by login pass";
    public final static String MESS_SEARCH_PROFILE_ERROR="Can't find profile for user";
    public ServiceException(String message){
        super(message);
    }
}
