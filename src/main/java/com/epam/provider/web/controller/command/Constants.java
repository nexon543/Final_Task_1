package com.epam.provider.web.controller.command;

/**
 * @author Gleb Akseonov
 */
public class Constants {

  public static String PARAM_COMMAND = "command";
  public static String ATTR_SESSION_PROFILE = "session_profile";
  public static String PARAM_USER = "user";
  public static String PARAM_LOCAL = "local";
  public static String PARAM_CURRENT_PAGE_REQUEST_NAME = "currentPageReq";
  public static String VALUE_CURRENT_PAGE_REQUEST_NAME_TARIFFS="get_tariffs";
  public static String PARAM_DISPLAY_MESSAGE = "displayMessage";
  public static String VALUE_DISPLAY_MESSAGE_YES = "yes";
  public static String VALUE_DISPLAY_MESSAGE_NO = "no";
  public static String DEFAULT_LANG="en";

  ////////////////////////////////////////////////////////////
  /////////////////////PAGINATION SESSION PARAMS//////////////
  ////////////////////////////////////////////////////////////


  public static String PARAM_FIRST_NAME = "first_name";
  public static String PARAM_SECOND_NAME = "second_name";
  public static String PARAM_PASSPORT = "passport";
  public static String PARAM_TARIFFS_ID = "id_tariffs";
  public static String PARAM_BALANCE = "balance";
  public static String ATT_SESSION_PROFILE_TARIFF = "session_profile_tariff";
  public static String PARAM_LOGIN = "login";
  public static String PARAM_PASSWORD = "password";
  public static String PARAM_ROLE = "role";
  public static String PARAM_DESCRIPTION = "description";
  public static String PARAM_PROFILE_ID = "profile_id";
  public static String PARAM_CHANGE_TARIFF="change_tariff";

  public static String PARAM_TARIFF_NAME = "tariff_name";
  public static String PARAM_RECEIVING_SPEED = "receiving_speed";
  public static String PARAM_TRANSFER_SPEED = "transfer_speed";
  public static String PARAM_PRICE = "price";

  ////////////////////////////////////////////////////////////

  public static String PARAM_RECORDS_PER_PAGE = "recordsPerPage";
  public static String PARAM_TARIFFS = "tariff_list";
  public static String PARAM_NEW_TARIFF_OBJ = "newTariff";
  public static String PARAM_CURRENT_PAGE = "currPage";
  public static String PARAM_NUMEBER_OF_PAGES = "pagesNumber";
  public static String PARAM_CURR_PAGE = "currentPage";
  public static Integer VALUE_RECORDS_PERPAGE = 4;
  public static Integer VALUE_CURRENT_PAGE=1;
  public static String PARAM_USERS = "users";

  ////////////////////////////////////////////////////////////

  public static String PARAM_LANG = "lang";
  public static String ATTR_ERROR_MESSAGE = "messageError";
  public static String ATTR_SUCCESS_MESSAGE = "messageSuccess";
  public static String PARAM_IS_SUCCESS = "isSuccess";
  public static String ROLE_NAME_ADMIN = "admin";
  public static String ROLE_NAME_CLIENT = "client";
  public static String ADMIN_PAGE_STATUS_SHOW_TARIFF = "show_tariffs";
  public static String ADMIN_PAGE_STATUS_SHOW_USERS = "show_users";
  public static String ATTR_STATUS = "state";
  public static String ADMIN_STATUS_UPDATE_TARIFF = "update_tariff";
  public static String ADMIN_STATUS_UPDATE_PROFILE = "update_profile";
  public static String PARAM_DELET_ENTITY_ID = "id";

  /*
   *Says which kind of entity (Profile or Tariff) will be updated.
   * Needed to restore data about this entity by id for the further updates.
   */
  public static String PARAM_UPDATED_ENTITY = "entity";
  public static String VALUE_UPDATED_ENTITY_PROFILE = "profile";
  public static String ATTR_UPDATABLE_TARIFF = "updatableTariff";
  public static String ATTR_UPDATABLE_PROFILE = "updatableProfile";
  public static String ATTR_ALL_TARIFFS = "tariffsAvailableForUser";
  public static String ATTR_CREATE_PROFILE_FORM_OBJECT = "createProfile";

  ////////////////////////////////////////////////////////

  public static String REQUEST_LOGIN = "/Login";
  public static String REQUEST_TARIFFS = "/Tariffs";
  public static String REQUEST_UPDATE_PROFILE="/UpdateProfile";
  public static String REQUEST_UPDATE_TARIFF="/UpdateTariff";

  /////////////////////////////////////////////////////////

  public static String AJAX_PARAM_TARIFFS="tariffs";
}
