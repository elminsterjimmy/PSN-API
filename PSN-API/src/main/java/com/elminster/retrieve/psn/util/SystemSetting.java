package com.elminster.retrieve.psn.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elminster.common.config.CommonConfiguration;
import com.elminster.common.util.DateUtil;
import com.elminster.common.util.FileUtil;

/**
 * The system settings.
 * 
 * @author jgu
 * @version 1.0
 */
public class SystemSetting extends CommonConfiguration {

  /** the system properties. */
  private static final String SYSTEM_PROPERTIES = "system.properties";
  /** the property name: last api called time. */
  private static final String LAST_API_CALLED_TIME = "LAST_API_CALLED_TIME";
  /** the property name: PSN username. */
  private static final String PSN_USERNAME = "PSN_USERNAME";
  /** the property name: PSN password. */
  private static final String PSN_PASSWORD = "PSN_PASSWORD";
  
  /** the singleton instance. */
  public static final SystemSetting INSTANCE = new SystemSetting();
  /** the logger. */
  private static final Log logger = LogFactory.getLog(SystemSetting.class);
  
  /**
   * Constructor.
   */
  private SystemSetting() {
    super();
  }

  /**
   * Load the resource files.
   */
  protected void loadResources() {
    // load the system properties, if not exist create it.
    if (!FileUtil.isFileExist(SYSTEM_PROPERTIES)) {
      try {
        FileUtil.createNewFile(SYSTEM_PROPERTIES);
      } catch (IOException e) {
        if (logger.isWarnEnabled()) {
          logger.warn("failed to create system properties.");
        }
      }
    }
    InputStream is = null;
    try {
      is = new FileInputStream(SYSTEM_PROPERTIES);
      properties.load(is);
    } catch (IOException e) {
      throw new IllegalStateException("Cannot initialize the system setting: " + e);
    } finally {
      if (null != is) {
        try {
          is.close();
        } catch (IOException e) {
          if (logger.isDebugEnabled()) {
            logger.debug(e);
          }
        }
      }
    }
  }

  /**
   * @see com.elminster.common.config.CommonConfiguration#persist()
   */
  @Override
  public void persist() throws IOException {
    super.persist();
    String comments = "updated on " + DateUtil.getCurrentDateStr();
    OutputStream out = null;
    try {
      out = new FileOutputStream(SYSTEM_PROPERTIES);
      properties.store(out, comments);
    } finally {
      if (null != out) {
        out.close();
      }
    }
  }
  
  /**
   * Get the PSN username.
   * @return the PSN username
   */
  public String getPSNUsername() {
    return this.getStringProperty(PSN_USERNAME);
  }
  
  /**
   * Get the PSN password.
   * @return the PSN password
   */
  public String getPSNPassword() {
    return this.getStringProperty(PSN_PASSWORD);
  }
  
  //////////// FIXME The API stats should be stored in the db. For convenience, using properties instead. ////////////
  /**
   * Update the last API called time.
   */
  public void updateLastApiCalledTime() {
    setProperty(LAST_API_CALLED_TIME, System.currentTimeMillis());
  }
  
  /**
   * Get the last API called time.
   * @return the last api called time
   */
  public long getLastApiCalledTime() {
    return this.getLongProperty(LAST_API_CALLED_TIME, 0L);
  }
}
