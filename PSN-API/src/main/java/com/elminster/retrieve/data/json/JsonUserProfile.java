package com.elminster.retrieve.data.json;

public class JsonUserProfile {

  private String handle;
  private String avatarUrl;
  private int isPlusUser;
  private int curLevel;
  private int progress;
  private JsonTrophy trophies;
  private int totalLevel;
  /**
   * @return the handle
   */
  public String getHandle() {
    return handle;
  }
  /**
   * @param handle the handle to set
   */
  public void setHandle(String handle) {
    this.handle = handle;
  }
  /**
   * @return the avatarUrl
   */
  public String getAvatarUrl() {
    return avatarUrl;
  }
  /**
   * @param avatarUrl the avatarUrl to set
   */
  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }
  /**
   * @return the isPlusUser
   */
  public int getIsPlusUser() {
    return isPlusUser;
  }
  /**
   * @param isPlusUser the isPlusUser to set
   */
  public void setIsPlusUser(int isPlusUser) {
    this.isPlusUser = isPlusUser;
  }
  /**
   * @return the curLevel
   */
  public int getCurLevel() {
    return curLevel;
  }
  /**
   * @param curLevel the curLevel to set
   */
  public void setCurLevel(int curLevel) {
    this.curLevel = curLevel;
  }
  /**
   * @return the progress
   */
  public int getProgress() {
    return progress;
  }
  /**
   * @param progress the progress to set
   */
  public void setProgress(int progress) {
    this.progress = progress;
  }
  /**
   * @return the trophies
   */
  public JsonTrophy getTrophies() {
    return trophies;
  }
  /**
   * @param trophies the trophies to set
   */
  public void setTrophies(JsonTrophy trophies) {
    this.trophies = trophies;
  }
  /**
   * @return the totalLevel
   */
  public int getTotalLevel() {
    return totalLevel;
  }
  /**
   * @param totalLevel the totalLevel to set
   */
  public void setTotalLevel(int totalLevel) {
    this.totalLevel = totalLevel;
  }
}
