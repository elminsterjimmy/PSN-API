package com.elminster.restful.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elminster.common.util.CollectionUtil;
import com.elminster.restful.dao.ITrophyDao;
import com.elminster.restful.domain.Trophy;
import com.elminster.retrieve.data.user.PSNUserTrophy;
import com.elminster.retrieve.exception.ServiceException;
import com.elminster.retrieve.service.IPSNApi;
import com.elminster.retrieve.service.PSNApiImpl;

@Service
@Transactional
public class UserGameTrophyServiceImpl implements IUserGameTrophyService {
  
  /** the PSN API. */
  private static final IPSNApi API = new PSNApiImpl();
  
  private final ITrophyDao trohpyDao;
  
  @Autowired
  public UserGameTrophyServiceImpl(ITrophyDao trophyDao) {
    this.trohpyDao = trophyDao;
  }
  
  public List<PSNUserTrophy> getUserGameTrophyList(String username, String gameId) throws ServiceException {
    // call the api
    List<PSNUserTrophy> list = API.getPSNUserGameTrophies(username, gameId);
    if (CollectionUtil.isNotEmpty(list)) {
      // insert/update trophy info
      List<Trophy> trophies = trohpyDao.findByGameId(gameId);
      List<Trophy> insert = new ArrayList<Trophy>(list.size());
      if (CollectionUtil.isNotEmpty(trophies)) {
        // exist, update
        int fetchedSize = list.size();
        int cachedSize = trophies.size();
        if (fetchedSize >= cachedSize) {
          for (int i = 0; i < fetchedSize; i++) {
            Trophy entity = trophies.get(i);
            PSNUserTrophy ut = list.get(i);
            if ((0 == entity.getStatus()) && ut.isEarned()) {
              entity.setDescription(ut.getDescription());
              entity.setGameId(ut.getGameId());
              entity.setImageUrl(ut.getImageUrl());
              entity.setTrophyOrder(i);
              entity.setTrophyId(ut.getTrophyId());
              entity.setTitle(ut.getTitle());
              entity.setType(ut.getType().getType());
              entity.setStatus(ut.isEarned() ? 1 : 0);
              insert.add(entity);
            } else if ((1 == entity.getStatus()) && !ut.isEarned()) {
              // feed back with cached info
              ut.setTitle(entity.getTitle());
              ut.setDescription(entity.getDescription());
              ut.setImageUrl(entity.getImageUrl());
            }
          }
          // add new ones
          for (int i = cachedSize; i < fetchedSize; i++) {
            PSNUserTrophy ut = list.get(i);
            Trophy entity = new Trophy();
            entity.setDescription(ut.getDescription());
            entity.setGameId(ut.getGameId());
            entity.setImageUrl(ut.getImageUrl());
            entity.setTrophyId(ut.getTrophyId());
            entity.setTrophyOrder(i);
            entity.setTitle(ut.getTitle());
            entity.setType(ut.getType().getType());
            entity.setStatus(ut.isEarned() ? 1 : 0);
            insert.add(entity);
          }
        }
      } else {
        // not exist, insert
        int idx = 0;
        for (PSNUserTrophy ut : list) {
          Trophy entity = new Trophy();
          entity.setDescription(ut.getDescription());
          entity.setGameId(ut.getGameId());
          entity.setImageUrl(ut.getImageUrl());
          entity.setTrophyId(ut.getTrophyId());
          entity.setTrophyOrder(idx++);
          entity.setTitle(ut.getTitle());
          entity.setType(ut.getType().getType());
          entity.setStatus(ut.isEarned() ? 1 : 0);
          insert.add(entity);
        }
      }
      trohpyDao.save(insert);
    }
    return list;
  }
}
