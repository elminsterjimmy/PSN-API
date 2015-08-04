package com.elminster.retrieve.data.json;

import java.util.List;

public class JsonCompareTrophies {

  private String handle;
  private String imgUrl;
  private String gameId;
  private String gameTitle;
  private List<JsonGameUser> users;
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
   * @return the imgUrl
   */
  public String getImgUrl() {
    return imgUrl;
  }
  /**
   * @param imgUrl the imgUrl to set
   */
  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }
  /**
   * @return the gameId
   */
  public String getGameId() {
    return gameId;
  }
  /**
   * @param gameId the gameId to set
   */
  public void setGameId(String gameId) {
    this.gameId = gameId;
  }
  /**
   * @return the gameTitle
   */
  public String getGameTitle() {
    return gameTitle;
  }
  /**
   * @param gameTitle the gameTitle to set
   */
  public void setGameTitle(String gameTitle) {
    this.gameTitle = gameTitle;
  }
  /**
   * @return the users
   */
  public List<JsonGameUser> getUsers() {
    return users;
  }
  /**
   * @param users the users to set
   */
  public void setUsers(List<JsonGameUser> users) {
    this.users = users;
  }
}
