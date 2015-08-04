package com.elminster.retrieve.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.elminster.common.config.CommonConfiguration;

/**
 * The Configuration.
 * 
 * @author jgu
 * @version 1.0
 */
public class Configuration extends CommonConfiguration {
  /** the XBox live URL properties. */
  private static final String PSN_URL_PROPERTIES = "PSNUrls.properties";
  /** the user profile xpath properties. */
  private static final String USER_PROFILE_XPATH_PROPERTIES = "UserProfileXPath.properties";
  /** the user's game list xpath properties. */
  private static final String USER_GAME_LIST_XPATH_PROPERTIES = "GameListXPath.properties";
  /** the user's game achievement list xpath properties. */
  private static final String USER_GAME_ACHIEVE_XPATH_PROPERTIES = "GameAchieveXPath.properties";
  /** the properties file list. */
  private static final List<String> propertiesList = new ArrayList<String>();
  
  /**
   * initialize the properties list.
   */
  static {
    propertiesList.add(PSN_URL_PROPERTIES);
    propertiesList.add(USER_PROFILE_XPATH_PROPERTIES);
    propertiesList.add(USER_GAME_LIST_XPATH_PROPERTIES);
    propertiesList.add(USER_GAME_ACHIEVE_XPATH_PROPERTIES);
  }
  
  /** the singleton instance. */
  public static final Configuration INSTANCE = new Configuration();
  
  /**
   * Constructor.
   */
  private Configuration() {
    super();
  }

  /**
   * Load the resource files.
   */
  protected void loadResources() {
    try {
      for (String p : propertiesList) {
        properties.load(Configuration.class.getClassLoader().getResourceAsStream(p));
      }
    } catch (IOException e) {
      throw new IllegalStateException("Cannot initialize the configuration: " + e);
    }
  }
}
