package com.elminster.restful.service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import com.elminster.common.util.ExceptionUtil;
import com.elminster.restful.Application;
import com.elminster.restful.dao.IAdminDao;
import com.elminster.restful.data.ExitCode;
import com.elminster.retrieve.exception.ServiceException;
import com.elminster.retrieve.util.SystemSetting;

/**
 * The admin service.
 * 
 * @author jgu
 * @version 1.0
 */
@Service
public class AdminServiceImpl implements IAdminService {

  /** the logger. **/
  private static final Log logger = LogFactory.getLog(AdminServiceImpl.class);
  
  private final IAdminDao adminDao;
  
  @Autowired
  public AdminServiceImpl(IAdminDao adminDao) {
    this.adminDao = adminDao;
  }
  
  /**
   * Shutdown the server.
   */
  @Transactional
  @Override
  public int shutdown() throws ServiceException {
    logger.info("User shutdown.");
    int ec = ExitCode.NORMAL.getCode();
    // save cookies
    try {
      SystemSetting.INSTANCE.persist();
    } catch (IOException e1) {
      ec &= ExitCode.COOKIE_SAVE_FAILED.getCode();
    }
    // dump db
    try {
      adminDao.dumpData();
    } catch (Exception e) {
      logger.error("failed to dump data. Cause: " + ExceptionUtil.getFullStackTrace(e));
      ec &= ExitCode.DB_DUMP_FAILED.getCode();
    }
    final int exitCode = ec;
    ExitCodeGenerator exitCodeGenerator = new ExitCodeGenerator() {

      @Override
      public int getExitCode() {
        return exitCode;
      }
      
    };
    SpringApplication.exit(Application.context, exitCodeGenerator); 
    return exitCode;
  }
}
