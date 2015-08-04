package com.elminster.retrieve.service;

import java.util.List;

import com.elminster.retrieve.data.game.PSNTrophy;
import com.elminster.retrieve.data.game.PSNGame;
import com.elminster.retrieve.data.user.PSNUserTrophy;
import com.elminster.retrieve.data.user.PSNUserGame;
import com.elminster.retrieve.data.user.PSNUserProfile;
import com.elminster.retrieve.exception.ServiceException;

public interface IPSNApi {

  public PSNUserProfile getPSNUserProfile(String psnUsername) throws ServiceException;
  
  public List<PSNUserGame> getPSNUserGameList(String psnUsername) throws ServiceException;
  
  public List<PSNUserTrophy> getPSNUserGameTrophies(String psnUsername, String psnGameId) throws ServiceException;
  
  // depending on aggregation in background. Need to think what happened if the game id is not being retrieved before.
  public List<PSNTrophy> getPSNGameTrophies(String psnGameId) throws ServiceException;
  
  public PSNGame getPSNGameSummary(String psnGameId) throws ServiceException;
  
}
