package com.elminster.retrieve.psn.parser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elminster.common.parser.IParser;
import com.elminster.common.parser.ParseException;
import com.elminster.common.util.CollectionUtil;
import com.elminster.common.util.DateUtil;
import com.elminster.common.util.StringUtil;
import com.elminster.retrieve.psn.data.game.TrophyType;
import com.elminster.retrieve.psn.data.json.JsonCompareTrophies;
import com.elminster.retrieve.psn.data.json.JsonGameUser;
import com.elminster.retrieve.psn.data.json.JsonTrophyInfo;
import com.elminster.retrieve.psn.data.user.PSNUserTrophy;

public class UserGameTrophyParser extends BaseParser implements IParser<JsonCompareTrophies, List<PSNUserTrophy>> {

  /** the logger. */
  private static final Log logger = LogFactory.getLog(UserGameTrophyParser.class);
  
  /**
   * {@inheritDoc}
   */
  @Override
  public List<PSNUserTrophy> parse(JsonCompareTrophies json) throws ParseException {
    if (null == json) {
      return null;
    }
    if (logger.isDebugEnabled()) {
      logger.debug(json);
    }
    List<PSNUserTrophy> userTrophies = new ArrayList<PSNUserTrophy>();
    String gameId = json.getGameId();
    List<JsonGameUser> gameUsers = json.getUsers();
    if (CollectionUtil.isNotEmpty(gameUsers)) {
      // get the opponent's trophy list
      JsonGameUser gameUser = gameUsers.get(0);
      if (null != gameUser) {
        List<JsonTrophyInfo> list = gameUser.getList();
        if (CollectionUtil.isNotEmpty(list)) {
          for (JsonTrophyInfo ti : list) {
            PSNUserTrophy userTrophy = new PSNUserTrophy();
            userTrophy.setDescription(ti.getDesc());
            String trophyWon = ti.getTrophyWon();
            if (StringUtil.isNotEmpty(trophyWon) && !"0".equals(trophyWon)) {
              String trophyStamp = ti.getTrophyStamp();
              // eg. 2015-01-07T15:45:12Z
              Date date = null;
              try {
                date = DateUtil.parserDateStr(trophyStamp, DateUtil.ISO8601_FORMAT);
                userTrophy.setEarned(true);
                userTrophy.setEarnedDate(date);
              } catch (java.text.ParseException e) {
                logger.warn("failed to parse the date: " + trophyStamp);
              }
            }
            userTrophy.setGameId(gameId);
            userTrophy.setImageUrl(parseUrl(ti.getImgUrl()));
            userTrophy.setTitle(ti.getTitle());
            userTrophy.setTrophyId(String.valueOf(ti.getTrophyId()));
            userTrophy.setType(TrophyType.getTrophyType(ti.getType()));
            userTrophies.add(userTrophy);
          }
        }
      }
    }
    
    return userTrophies;
  }

}
