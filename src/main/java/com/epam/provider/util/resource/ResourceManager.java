package com.epam.provider.util.resource;

import java.util.*;

/**
 * @author Gleb Aksenov
 */
public class ResourceManager {

  private static ResourceBundle pageBundle;
  private static ResourceBundle resourceBundle;
  private static Locale currentLocale;
  private static ResourceBundle messageBundle;
  private static ResourceBundle dependencyBundle;
  private static ResourceBundle defaultMessageBundle;

  private ResourceManager() {
  }

  public static void initResources() {
    resourceBundle = ResourceBundle.getBundle(ResourceConstants.RESOURCE_PATH_DATABASE);
    messageBundle = ResourceBundle.getBundle(ResourceConstants.RESOURCE_PATH_MESSAGE);
    pageBundle = ResourceBundle.getBundle(ResourceConstants.RESOURCE_PATH_PAGES);
    dependencyBundle=ResourceBundle.getBundle(ResourceConstants.RESOURCE_PATH_DEPENDENCY);
    defaultMessageBundle = ResourceBundle
        .getBundle(ResourceConstants.RESOURCE_PATH_MESSAGE, Locale.getDefault());
    currentLocale = Locale.getDefault();
  }

  /**
   * @param key key of message in bundle
   * @param lang need to choose bundle with correct language
   * @return message by key from appropriate resource bundle according to locale
   */
  public static String getMessage(String key, String lang) {
    if (!ResourceManager.currentLocale.getLanguage().equals(lang)) {
      ResourceManager.currentLocale = new Locale(lang);
      messageBundle = ResourceBundle
          .getBundle(ResourceConstants.RESOURCE_PATH_MESSAGE, currentLocale);
    }
    return messageBundle.getString(key);
  }

  /**
   * This method gets messages by key from default resource bundle
   */
  public static String getMessage(String key) {
    return defaultMessageBundle.getString(key);
  }

  public static String getPagePath(String pageName) {
    return pageBundle.getString(pageName);
  }


  public static String getDatabaseProperty(String databaseParamKey) {
    return resourceBundle.getString(databaseParamKey);
  }

  public static Map<String,String> getDIMap(){
    Map<String, String> result=new HashMap<>();
    Enumeration<String>keys=dependencyBundle.getKeys();
    while (keys.hasMoreElements()){
      String key=keys.nextElement();
      result.put(key, dependencyBundle.getString(key));
    }
    return result;
  }

}
