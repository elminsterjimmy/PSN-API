package com.elminster.psn.restful.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elminster.common.util.CollectionUtil;
import com.elminster.psn.restful.dao.IGameDao;
import com.elminster.psn.restful.dao.IPlatformDao;
import com.elminster.psn.restful.dao.ITrophyDao;
import com.elminster.psn.restful.domain.Game;
import com.elminster.psn.restful.domain.Trophy;
import com.elminster.retrieve.psn.data.game.Platform;
import com.elminster.retrieve.psn.data.game.TrophyType;
import com.elminster.retrieve.psn.data.user.PSNUserGame;
import com.elminster.retrieve.psn.exception.ServiceException;
import com.elminster.retrieve.psn.service.IPSNApi;
import com.elminster.retrieve.psn.service.PSNApiImpl;

@Service
@Transactional
public class UserGameServiceImpl implements IUserGameService {
  
  /** the PSN API. */
  private static final IPSNApi API = new PSNApiImpl();
  
  private final ITrophyDao trohpyDao;
  
  private final IGameDao gameDao;
  
  private final IPlatformDao platformDao;
  
  @Autowired
  public UserGameServiceImpl(IGameDao gameDao, ITrophyDao trophyDao, IPlatformDao platformDao) {
    this.gameDao = gameDao;
    this.trohpyDao = trophyDao;
    this.platformDao = platformDao;
  }

  @Override
  public List<PSNUserGame> getUserGameList(String username) throws ServiceException {
    // call api
    List<PSNUserGame> list = API.getPSNUserGameList(username);
    if (CollectionUtil.isNotEmpty(list)) {
      for (PSNUserGame ug : list) {
        String gameId = ug.getGameId();
        Game game = gameDao.findByGameId(gameId);
        if (null == game) {
          game = new Game();
        }
        game.setGameId(gameId);
        game.setImageUrl(ug.getImageUrl());
        List<Platform> platforms = ug.getPlatform();
        if (CollectionUtil.isNotEmpty(platforms)) {
          List<com.elminster.psn.restful.domain.Platform> pl = new ArrayList<>();
          for (Platform platform : platforms) {
            String platformName = platform.getName();
            com.elminster.psn.restful.domain.Platform p = platformDao.findByPlatform(platformName);
            if (p == null) {
              p = new com.elminster.psn.restful.domain.Platform();
              p.setPlatform(platformName);
            }
            pl.add(p);
          }
          game.setPlatform(pl);
        }
        game.setTitle(ug.getTitle());
        // update trophies
        updateTrophies(game);
        gameDao.save(game);
      }
    }
    return list;
  }

  private Game updateTrophies(final Game game) {
    // get trophies
    List<Trophy> trophies = trohpyDao.findByGameId(game.getId());
    if (CollectionUtil.isNotEmpty(trophies)) {
      int bronze = 0;
      int silver = 0;
      int gold = 0;
      int platinum = 0;
      for (Trophy trophy : trophies) {
        if (TrophyType.BRONZE.getType() == trophy.getType()) {
          bronze++;
        } else if (TrophyType.SILVER.getType() == trophy.getType()) {
          silver++;
        } else if (TrophyType.GOLD.getType() == trophy.getType()) {
          gold++;
        } else if (TrophyType.PLATINUM.getType() == trophy.getType()) {
          platinum++;
        }
      }
      game.setBronzeCount(bronze);
      game.setGlodCount(gold);
      game.setSilverCount(silver);
      game.setPlatinumCount(platinum);
      game.setTotalCount(bronze + gold + silver + platinum);
    }
    return game;
  }

}
