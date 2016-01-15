package com.elminster.retrieve.psn.service;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

import com.elminster.common.constants.Constants.StringConstants;
import com.elminster.common.parser.IParser;
import com.elminster.common.parser.ParseException;
import com.elminster.common.retrieve.RetrieveException;
import com.elminster.common.util.ExceptionUtil;
import com.elminster.common.util.FileUtil;
import com.elminster.retrieve.psn.constants.PropertyKey;
import com.elminster.retrieve.psn.data.game.PSNGame;
import com.elminster.retrieve.psn.data.game.PSNTrophy;
import com.elminster.retrieve.psn.data.json.JsonCompareTrophies;
import com.elminster.retrieve.psn.data.json.JsonGameList;
import com.elminster.retrieve.psn.data.json.JsonUserProfile;
import com.elminster.retrieve.psn.data.user.PSNUserGame;
import com.elminster.retrieve.psn.data.user.PSNUserProfile;
import com.elminster.retrieve.psn.data.user.PSNUserTrophy;
import com.elminster.retrieve.psn.exception.ServiceException;
import com.elminster.retrieve.psn.parser.UserGameListParser;
import com.elminster.retrieve.psn.parser.UserGameTrophyParser;
import com.elminster.retrieve.psn.parser.UserProfileParser;
import com.elminster.retrieve.psn.runnable.BaseRetriever;
import com.elminster.retrieve.psn.runnable.CompareGameRetriever;
import com.elminster.retrieve.psn.util.Configuration;

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
    File cookieFile = new File(BaseRetriever.COOKIE_FILE);
    if (!cookieFile.exists()) {
      getPSNUserProfile("test");
    }
    try {
      String userinfo = getCookieValue("userinfo");
      long current = System.currentTimeMillis();
      // magic number 0.0xxxxxxxxxxxxxxxx
      double magicNumber = Math.random();
      String url = MessageFormat.format(compareGameUrl, psnGameId, psnUsername, userinfo, String.valueOf(current), String.valueOf(magicNumber));
      BaseRetriever retriever = new CompareGameRetriever(url);
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

  /**
   * Get the userinfo cookie.
   * @throws IOException on error
   */
  public String getCookieValue(String key) throws IOException {
    File cookieFile = new File(BaseRetriever.COOKIE_FILE);
    if (cookieFile.exists()) {
      String cookieInfo = FileUtil.readFile2String(cookieFile.getAbsolutePath());
      if (null != cookieInfo) {
        String[] cookies = cookieInfo.split(StringConstants.AND);
        for (String cookie : cookies) {
          int idx = cookie.indexOf(StringConstants.EQUAL);
          String cookieKey = cookie.substring(0, idx);
          if (key.equals(cookieKey)) {
            String value = cookie.substring(idx + 1);
            if (logger.isDebugEnabled()) {
              logger.debug("cookie: " + cookieKey + "=" + value);
            }
            return value.replaceAll(StringConstants.EQUAL, "%3D").replaceAll(StringConstants.SLASH, "%2F");
          }
        }
      }
    }
    throw new IllegalStateException(key + " is not available.");
  }
}
