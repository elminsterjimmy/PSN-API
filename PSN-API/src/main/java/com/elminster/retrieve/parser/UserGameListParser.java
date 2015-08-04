package com.elminster.retrieve.parser;

import java.util.ArrayList;
import java.util.List;

import com.elminster.common.constants.Constants.StringConstants;
import com.elminster.common.parser.IParser;
import com.elminster.common.parser.ParseException;
import com.elminster.common.util.CollectionUtil;
import com.elminster.common.util.StringUtil;
import com.elminster.retrieve.data.game.Platform;
import com.elminster.retrieve.data.json.JsonGame;
import com.elminster.retrieve.data.json.JsonGameList;
import com.elminster.retrieve.data.json.JsonTrophy;
import com.elminster.retrieve.data.user.PSNUserGame;

public class UserGameListParser implements IParser<JsonGameList, List<PSNUserGame>> {

  private static final String PLATFORM_SPLIT = StringConstants.COMMA;

  @Override
  public List<PSNUserGame> parse(JsonGameList json) throws ParseException {
    if (null == json) {
      return null;
    }
    List<PSNUserGame> userGames = new ArrayList<PSNUserGame>();
    List<JsonGame> list = json.getList();
    if (CollectionUtil.isNotEmpty(list)) {
      for (JsonGame jg : list) {
        PSNUserGame userGame = new PSNUserGame();
        int completion = jg.getProgress();
        userGame.setCompletionByPercent((byte)completion);
        JsonTrophy jt = jg.getTrophies();
        if (null != jt) {
          int bronze = jt.getBronze();
          int gold = jt.getGold();
          int platinum = jt.getPlatinum();
          int silver = jt.getSilver();
          userGame.setEarnedBronze((byte) bronze);
          userGame.setEarnedGold((byte) gold);
          userGame.setEarnedPlatinum((byte) platinum);
          userGame.setEarnedSilver((byte) silver);
          
          if (100 == completion) {
            userGame.setGlodCount((byte) gold);
            userGame.setBronzeCount((byte) bronze);
            userGame.setPlatinumCount((byte) platinum);
            userGame.setSilverCount((byte) silver);
          }
        }
        userGame.setGameId(jg.getGameId());
        userGame.setImageUrl(jg.getImgUrl());
        userGame.setPlatform(parsePlatform(jg.getPlatform()));
        userGame.setTitle(jg.getTitle());
        userGames.add(userGame);
      }
    }
    return userGames;
  }

  private List<Platform> parsePlatform(String platform) {
    List<Platform> list = null;
    if (StringUtil.isNotEmpty(platform)) {
      list = new ArrayList<Platform>();
      String[] split = platform.split(PLATFORM_SPLIT);
      for (String s : split) {
        list.add(Platform.getPlatformFromString(s));
      }
    }
    return list;
  }

}
