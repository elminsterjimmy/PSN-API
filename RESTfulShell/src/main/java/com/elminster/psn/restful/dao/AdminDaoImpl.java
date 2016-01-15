package com.elminster.psn.restful.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.elminster.common.util.FileUtil;

@Repository
public class AdminDaoImpl implements IAdminDao {
  
  private final static Log logger = LogFactory.getLog(AdminDaoImpl.class);
  
  private final static String DUMP_DATA_LOC = "db/data.csv";
  private final static String DUMP_SQL = "SCRIPT '" + DUMP_DATA_LOC + "'";
  
  @PersistenceContext
  private final EntityManager entityManager;
  
  @Autowired
  public AdminDaoImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public void dumpData() throws Exception {
    logger.debug("dump data to " + DUMP_DATA_LOC);
    FileUtil.createFolder(DUMP_DATA_LOC);
    Query query = entityManager.createNativeQuery(DUMP_SQL);
    query.executeUpdate();
  }

  @Override
  public void restoreData() throws Exception {
    // TODO Auto-generated method stub
    logger.debug("restore data from " + DUMP_DATA_LOC);
    
  }

}
