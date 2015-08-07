package com.elminster.restful.service;

import com.elminster.retrieve.exception.ServiceException;


public interface IAdminService {

  public int shutdown() throws ServiceException;
}
