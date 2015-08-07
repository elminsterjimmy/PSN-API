package com.elminster.restful.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elminster.restful.domain.Game;

public interface IGameDao extends JpaRepository<Game, String> {
  public Game findByGameId(String gameId);
}
