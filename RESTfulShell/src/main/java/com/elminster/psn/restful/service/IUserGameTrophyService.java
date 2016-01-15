package com.elminster.psn.restful.service;

import java.util.List;

import com.elminster.retrieve.psn.data.user.PSNUserTrophy;
import com.elminster.retrieve.psn.exception.ServiceException;

public interface IUserGameTrophyService {

  List<PSNUserTrophy> getUserGameTrophyList(String username, String gameId) throws ServiceException;
}
