package com.elminster.retrieve.data.json;

import java.util.List;

public class JsonGameUser {

  private String handle;
  private String avatarUrl;
  private int isPlusUser;
  private int progress;
  private JsonTrophy trophies;
  private List<JsonTrophyInfo> list;
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
   * @return the list
   */
  public List<JsonTrophyInfo> getList() {
    return list;
  }
  /**
   * @param list the list to set
   */
  public void setList(List<JsonTrophyInfo> list) {
    this.list = list;
  }
}
