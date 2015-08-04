package com.elminster.retrieve.data.user;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.elminster.common.util.ObjectUtil;

/**
 * The PSN user profile.
 * 
 * @author jgu
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown=true) 
public class PSNUserProfile {
  /** the user id. **/
  private String userId;
  /** the user name. **/
  private String username;
  /** the user's avatar url. **/
  private String userAvatarUrl;
  /** the user total earned points. **/
  private long totalPoint;
  /** the user's location. **/
  private String location;
  /** the user's bio . **/
  private String bio;
  /** the user's recent active. **/
  private String recentActive;
  /** the psn level. */
  private short level;
  /** the current level process (0-99). */
  private byte levelProcess;
  /** total bronze earned. */
  private int totalBronze;
  /** total silver earned. */
  private int totalSilver;
  /** total glod earned. */
  private int totalGold;
  /** total platinum earned. */
  private int totalPlatinum;
  /** is the user a plus member. */
  private int isPlusMember;
  /** the totoal level. */
  private short totalLevel;
  
  /**
   * @return the userId
   */
  public String getUserId() {
    return userId;
  }
  /**
   * @param userId the userId to set
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }
  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }
  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }
  /**
   * @return the userAvatarUrl
   */
  public String getUserAvatarUrl() {
    return userAvatarUrl;
  }
  /**
   * @param userAvatarUrl the userAvatarUrl to set
   */
  public void setUserAvatarUrl(String userAvatarUrl) {
    this.userAvatarUrl = userAvatarUrl;
  }
  /**
   * @return the totalPoint
   */
  public long getTotalPoint() {
    return totalPoint;
  }
  /**
   * @param totalPoint the totalPoint to set
   */
  public void setTotalPoint(long totalPoint) {
    this.totalPoint = totalPoint;
  }
  /**
   * @return the location
   */
  public String getLocation() {
    return location;
  }
  /**
   * @param location the location to set
   */
  public void setLocation(String location) {
    this.location = location;
  }
  /**
   * @return the bio
   */
  public String getBio() {
    return bio;
  }
  /**
   * @param bio the bio to set
   */
  public void setBio(String bio) {
    this.bio = bio;
  }
  /**
   * @return the recentActive
   */
  public String getRecentActive() {
    return recentActive;
  }
  /**
   * @param recentActive the recentActive to set
   */
  public void setRecentActive(String recentActive) {
    this.recentActive = recentActive;
  }
  /**
   * @return the isPlusMember
   */
  public boolean isPlusMember() {
    if (1 == isPlusMember) {
      return true;
    }
    return false;
  }
  /**
   * @param isPlusMember the isPlusMember to set
   */
  public void setPlusMember(boolean isPlusMember) {
    this.isPlusMember = isPlusMember ? 1 : 0;
  }
  /**
   * @return the level
   */
  public short getLevel() {
    return level;
  }
  /**
   * @param level the level to set
   */
  public void setLevel(short level) {
    this.level = level;
  }
  /**
   * @return the levelProcess
   */
  public byte getLevelProcess() {
    return levelProcess;
  }
  /**
   * @param levelProcess the levelProcess to set
   */
  public void setLevelProcess(byte levelProcess) {
    this.levelProcess = levelProcess;
  }
  /**
   * @return the totalBronze
   */
  public int getTotalBronze() {
    return totalBronze;
  }
  /**
   * @param totalBronze the totalBronze to set
   */
  public void setTotalBronze(int totalBronze) {
    this.totalBronze = totalBronze;
  }
  /**
   * @return the totalSilver
   */
  public int getTotalSilver() {
    return totalSilver;
  }
  /**
   * @param totalSilver the totalSilver to set
   */
  public void setTotalSilver(int totalSilver) {
    this.totalSilver = totalSilver;
  }
  /**
   * @return the totalGold
   */
  public int getTotalGold() {
    return totalGold;
  }
  /**
   * @param totalGold the totalGold to set
   */
  public void setTotalGold(int totalGold) {
    this.totalGold = totalGold;
  }
  /**
   * @return the totalPlatinum
   */
  public int getTotalPlatinum() {
    return totalPlatinum;
  }
  /**
   * @param totalPlatinum the totalPlatinum to set
   */
  public void setTotalPlatinum(int totalPlatinum) {
    this.totalPlatinum = totalPlatinum;
  }
  /**
   * @return the totalLevel
   */
  public short getTotalLevel() {
    return totalLevel;
  }
  /**
   * @param totalLevel the totalLevel to set
   */
  public void setTotalLevel(short totalLevel) {
    this.totalLevel = totalLevel;
  }
  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return ObjectUtil.buildToStringByReflect(this);
  }
}
