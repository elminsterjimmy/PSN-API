package com.elminster.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elminster.restful.service.IAdminService;
import com.elminster.retrieve.exception.ServiceException;

/**
 * The admin controller.
 * 
 * @author jgu
 * @version 1.0
 */
@Controller
public class AdminController {

  private final IAdminService adminService;
  
  @Autowired
  public AdminController(IAdminService adminService) {
    this.adminService = adminService;
  }
  
  @RequestMapping(value = "/admin/shutdown", method = RequestMethod.GET)
  public @ResponseBody int shutdown() throws ServiceException {
    return adminService.shutdown();
  }
}
