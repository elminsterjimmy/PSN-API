package com.elminster.psn.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elminster.psn.restful.Application;
import com.elminster.psn.restful.service.IAdminService;
import com.elminster.retrieve.psn.exception.ServiceException;

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
  
  @RequestMapping(value = "/admin/dump", method = RequestMethod.GET)
  public @ResponseBody int dump() throws ServiceException {
    return adminService.savepoint();
  }
  
  @RequestMapping(value = "/admin/shutdown", method = RequestMethod.GET)
  public void shutdown() {
    final int exitCode = adminService.savepoint();
    ExitCodeGenerator exitCodeGenerator = new ExitCodeGenerator() {

      @Override
      public int getExitCode() {
        return exitCode;
      }
      
    };
    SpringApplication.exit(Application.context, exitCodeGenerator); 
  }
}
