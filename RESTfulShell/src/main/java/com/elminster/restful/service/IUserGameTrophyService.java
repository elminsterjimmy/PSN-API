package com.elminster.restful.service;

import java.util.List;

import com.elminster.retrieve.data.user.PSNUserTrophy;
import com.elminster.retrieve.exception.ServiceException;

public interface IUserGameTrophyService {

  List<PSNUserTrophy> getUserGameTrophyList(String username, String gameId) throws ServiceException;
}
