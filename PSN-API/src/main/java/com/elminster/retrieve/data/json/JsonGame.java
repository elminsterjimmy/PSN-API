package com.elminster.retrieve.data.json;

public class JsonGame {

  private String platform;
  private int progress;
  private JsonTrophy trophies;
  private String imgUrl;
  private String title;
  private String gameId;
  /**
   * @return the platform
   */
  public String getPlatform() {
    return platform;
  }
  /**
   * @param platform the platform to set
   */
  public void setPlatform(String platform) {
    this.platform = platform;
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
   * @return the title
   */
  public String getTitle() {
    return title;
  }
  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
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
}
