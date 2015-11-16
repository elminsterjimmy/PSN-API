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
  /** the properties file list. */
  private static final List<String> propertiesList = new ArrayList<String>();
  
  /**
   * initialize the properties list.
   */
  static {
    propertiesList.add(PSN_URL_PROPERTIES);
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
