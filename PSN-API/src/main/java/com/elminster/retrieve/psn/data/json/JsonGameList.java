package com.elminster.retrieve.psn.data.json;

import java.util.List;

public class JsonGameList {

  private String handle;
  private String avatarUrl;
  private int isPlusUser;
  private int curLevel;
  private int overallprogress;
  private int totalResults;
  private List<JsonGame> list;
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
   * @return the overallprogress
   */
  public int getOverallprogress() {
    return overallprogress;
  }
  /**
   * @param overallprogress the overallprogress to set
   */
  public void setOverallprogress(int overallprogress) {
    this.overallprogress = overallprogress;
  }
  /**
   * @return the totalResults
   */
  public int getTotalResults() {
    return totalResults;
  }
  /**
   * @param totalResults the totalResults to set
   */
  public void setTotalResults(int totalResults) {
    this.totalResults = totalResults;
  }
  /**
   * @return the list
   */
  public List<JsonGame> getList() {
    return list;
  }
  /**
   * @param list the list to set
   */
  public void setList(List<JsonGame> list) {
    this.list = list;
  }
}
