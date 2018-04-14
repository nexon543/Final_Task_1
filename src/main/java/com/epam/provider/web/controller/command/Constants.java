package com.epam.provider.web.controller.command;

/**
 * @author Gleb Akseonov
 */
public class Constants {

    //////////////////////////////////////////////////////////
    /////////////////////LOGIN SESSION PARAMS/////////////////
    //////////////////////////////////////////////////////////

    public static String PARAM_LOGIN = "login";
    public static String PARAM_PASSWORD = "password";
    public static String PARAM_COMMAND = "command";
    public static String PARAM_PROFILE = "profile";
    public static String PARAM_USER = "user";
    public static String PARAM_LOCAL = "local";

    ////////////////////////////////////////////////////////////
    /////////////////////PAGINATION SESSION PARAMS//////////////
    ////////////////////////////////////////////////////////////
    public static String PARAM_FIRST_NAME = "first_name";
    public static String PARAM_SECOND_NAME = "second_name";
    public static String PARAM_PASSPORT = "passport";
    public static String PARAM_ID_TARIFFS = "id_tariffs";
    public static String PARAM_BALANCE = "balance";
    public static String PARAM_TARIFF_FOR_USER = "tariff";

    ////////////////////////////////////////////////////////////
    ////////////////////

    public static String PARAM_RECORDS_PER_PAGE = "recordsPerPage";
    public static String PARAM_TARIFFS = "tariffs";
    public static String PARAM_NEW_TARIFF_OBJ = "newTariff";
    public static String PARAM_CURRENT_PAGE = "currPage";
    public static String PARAM_NUMEBER_OF_PAGES = "pagesNumber";
    public static String PARAM_CURR_PAGE = "currentPage";
    public static Integer VALUE_RECORDS_PERPAGE = 4;
    public static String PARAM_USERS = "users";

    ////////////////////////////////////////////////////////////

    public static String PARAM_LANG = "lang";
    public static String PARAM_ERROR_MESSAGE = "messageError";
    public static String PARAM_SUCCESS_MESSAGE = "messageSuccess";
    public static String PARAM_IS_SUCCESS = "isSuccess";
    public static String ROLE_NAME_ADMIN = "admin";
    public static String ROLE_NAME_CLIENT = "client";
    public static String ADMIN_PAGE_STATUS_SHOW_TARIFF = "show_tariffs";
    public static String ADMIN_PAGE_STATUS_SHOW_USERS = "show_users";
    public static String PARAM_ADMIN_STATUS = "state";
    public static String ADMIN_STATUS_UPDATE_TARIFF = "update_tariff";
    public static String ADMIN_STATUS_UPDATE_PROFILE = "update_profile";
    public static String PARAM_DELET_ENTITY_ID = "id";

    //////////////////////////////////////////////////////////////
    ////////////////////////UPDATE////////////////////////////////
    //////////////////////////////////////////////////////////////

    public static String PARAM_UPDATED_ENTITY = "entity";

    ////////////////////////////////////////////////////////

    public static String REQUEST_LOGIN = "/Login";
    public static String REQUEST_TARIFFS = "/Tariffs  ";


}
