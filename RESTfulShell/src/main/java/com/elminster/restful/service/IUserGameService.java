package com.elminster.restful.service;

import java.util.List;

import com.elminster.retrieve.data.user.PSNUserGame;
import com.elminster.retrieve.exception.ServiceException;

public interface IUserGameService {

  public List<PSNUserGame> getUserGameList(String username) throws ServiceException;
}
