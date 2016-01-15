package com.elminster.psn.restful.service;

import java.util.List;

import com.elminster.retrieve.psn.data.user.PSNUserGame;
import com.elminster.retrieve.psn.exception.ServiceException;

public interface IUserGameService {

  public List<PSNUserGame> getUserGameList(String username) throws ServiceException;
}
