package com.elminster.retrieve.service;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

import com.elminster.common.parser.IParser;
import com.elminster.common.parser.ParseException;
import com.elminster.common.retrieve.RetrieveException;
import com.elminster.common.util.ExceptionUtil;
import com.elminster.retrieve.constants.PropertyKey;
import com.elminster.retrieve.data.game.PSNGame;
import com.elminster.retrieve.data.game.PSNTrophy;
import com.elminster.retrieve.data.json.JsonCompareTrophies;
import com.elminster.retrieve.data.json.JsonGameList;
import com.elminster.retrieve.data.json.JsonUserProfile;
import com.elminster.retrieve.data.user.PSNUserGame;
import com.elminster.retrieve.data.user.PSNUserProfile;
import com.elminster.retrieve.data.user.PSNUserTrophy;
import com.elminster.retrieve.exception.ServiceException;
import com.elminster.retrieve.parser.UserGameListParser;
import com.elminster.retrieve.parser.UserGameTrophyParser;
import com.elminster.retrieve.parser.UserProfileParser;
import com.elminster.retrieve.runnable.BaseRetriever;
import com.elminster.retrieve.runnable.CompareGameRetriever;
import com.elminster.retrieve.util.Configuration;

/**
 * The implementation of PSN API.
 * 
 * @author jgu
 * @version 1.0
 */
public class PSNApiImpl implements IPSNApi {
  /** the logger. */
  private static final Log logger = LogFactory.getLog(PSNApiImpl.class);

  /**
   * {@inheritDoc}
   */
  @Override
  public PSNUserProfile getPSNUserProfile(String psnUsername) throws ServiceException {
    String userProfileUrl = Configuration.INSTANCE.getStringProperty(PropertyKey.PSN_USER_PROFILE_URL);
    long current = System.currentTimeMillis();
    String url = MessageFormat.format(userProfileUrl, psnUsername,
        String.valueOf(current) /* the magic number for current time*/);
    BaseRetriever retriever = new BaseRetriever(url);
    try {
      String json = retriever.retrieve().getBody();
      if (logger.isDebugEnabled()) {
        logger.debug("received json: " + json);
      }
      ObjectMapper mapper = new ObjectMapper();
      JsonUserProfile jsonUserProfile = mapper.readValue(json, JsonUserProfile.class);
      IParser<JsonUserProfile, PSNUserProfile> userProfileParser = new UserProfileParser();
      return userProfileParser.parse(jsonUserProfile);
    } catch (RetrieveException | IOException | ParseException e) {
      logger.error(ExceptionUtil.getFullStackTrace(e));
      throw new ServiceException("Failed to get user profile for user: [" + psnUsername + "]. Caused by: " + e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<PSNUserGame> getPSNUserGameList(String psnUsername) throws ServiceException {
    String userGameListUrl = Configuration.INSTANCE.getStringProperty(PropertyKey.PSN_USER_GAME_LIST_URL);
    long current = System.currentTimeMillis();
    String url = MessageFormat.format(userGameListUrl, psnUsername,
        String.valueOf(current) /* the magic number for current time*/);
    BaseRetriever retriever = new BaseRetriever(url);
    try {
      String json = retriever.retrieve().getBody();
      if (logger.isDebugEnabled()) {
        logger.debug("received json: " + json);
      }
      ObjectMapper mapper = new ObjectMapper();
      JsonGameList jsonGameList = mapper.readValue(json, JsonGameList.class);
      IParser<JsonGameList, List<PSNUserGame>> userGameListParser = new UserGameListParser();
      return userGameListParser.parse(jsonGameList);
    } catch (RetrieveException | IOException | ParseException e) {
      logger.error(ExceptionUtil.getFullStackTrace(e));
      throw new ServiceException("Failed to get user's game list for user: [" + psnUsername + "]. Caused by: " + e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<PSNUserTrophy> getPSNUserGameTrophies(String psnUsername, String psnGameId) throws ServiceException {
    String compareGameUrl = Configuration.INSTANCE.getStringProperty(PropertyKey.PSN_COMPARE_GAME_URL);
    String url = MessageFormat.format(compareGameUrl, psnGameId, psnUsername);
    BaseRetriever retriever = new CompareGameRetriever(url);
    try {
      String json = retriever.retrieve().getBody();
      if (logger.isDebugEnabled()) {
        logger.debug("received json: " + json);
      }
      ObjectMapper mapper = new ObjectMapper();
      JsonCompareTrophies jsonCompareTrophies = mapper.readValue(json, JsonCompareTrophies.class);
      IParser<JsonCompareTrophies, List<PSNUserTrophy>> userGameTrophyParser = new UserGameTrophyParser();
      return userGameTrophyParser.parse(jsonCompareTrophies);
    } catch (RetrieveException | IOException | ParseException e) {
      logger.error(ExceptionUtil.getFullStackTrace(e));
      throw new ServiceException("Failed to get user's game achievement for user: [" + psnUsername + "], gameId: ["
          + psnGameId + "]. Caused by: " + e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<PSNTrophy> getPSNGameTrophies(String psnGameId) throws ServiceException {
    throw new UnsupportedOperationException("Unsupported yet.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PSNGame getPSNGameSummary(String psnGameId) throws ServiceException {
    throw new UnsupportedOperationException("Unsupported yet.");
  }

}
