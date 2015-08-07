package com.elminster.restful.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elminster.restful.domain.Trophy;

@Repository
public interface ITrophyDao extends JpaRepository<Trophy, String> {

  public List<Trophy> findByGameId(String gameId);
  
}
