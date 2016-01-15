package com.elminster.psn.restful.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elminster.psn.restful.domain.Platform;

@Repository
public interface IPlatformDao extends JpaRepository<Platform, String> {

  public Platform findByPlatform(String platform);
}
