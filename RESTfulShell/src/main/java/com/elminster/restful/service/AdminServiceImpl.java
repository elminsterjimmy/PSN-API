package com.elminster.restful.service;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elminster.common.util.ExceptionUtil;
import com.elminster.restful.dao.IAdminDao;
import com.elminster.restful.data.ExitCode;
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
  private EntityManager em;
  
  @Autowired
  public AdminServiceImpl(IAdminDao adminDao) {
    this.adminDao = adminDao;
  }
  
  @Autowired
  public void setEntityManager(EntityManager em) {
    this.em = em;
  }
  
  /**
   * dump the data.
   */
  @Override
  public int savepoint() {
    int ec = ExitCode.NORMAL.getCode();
    // save cookies
    try {
      SystemSetting.INSTANCE.persist();
    } catch (IOException e1) {
      ec &= ExitCode.COOKIE_SAVE_FAILED.getCode();
    }
    // dump db
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    try {
      adminDao.dumpData();
      tx.commit();
    } catch (Exception e) {
      logger.error("failed to dump data. Cause: " + ExceptionUtil.getFullStackTrace(e));
      tx.rollback();
      ec &= ExitCode.DB_DUMP_FAILED.getCode();
    }
    return ec;
  }
}
