package com.elminster.psn.restful.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elminster.psn.restful.domain.Game;

public interface IGameDao extends JpaRepository<Game, String> {
  public Game findByGameId(String gameId);
}
