package com.elminster.retrieve.data.game;

import java.util.List;

import com.elminster.common.util.ObjectUtil;

/**
 * The game.
 * 
 * @author jgu
 * @version 1.0
 */
public class PSNGame {

  private String gameId;
  private String title;
  private short totalPoint;
  private byte bronzeCount;
  private byte silverCount;
  private byte goldCount;
  private byte platinumCount;
  private String imageUrl;
  private List<Platform> platform;
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
   * @return the totalPoint
   */
  public short getTotalPoint() {
    return totalPoint;
  }
  /**
   * @param totalPoint the totalPoint to set
   */
  public void setTotalPoint(short totalPoint) {
    this.totalPoint = totalPoint;
  }
  /**
   * @return the imageUrl
   */
  public String getImageUrl() {
    return imageUrl;
  }
  /**
   * @param imageUrl the imageUrl to set
   */
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
  /**
   * @return the platform
   */
  public List<Platform> getPlatform() {
    return platform;
  }
  /**
   * @param platform the platform to set
   */
  public void setPlatform(List<Platform> platform) {
    this.platform = platform;
  }
  
  /**
   * @return the bronzeCount
   */
  public byte getBronzeCount() {
    return bronzeCount;
  }
  /**
   * @param bronzeCount the bronzeCount to set
   */
  public void setBronzeCount(byte bronzeCount) {
    this.bronzeCount = bronzeCount;
  }
  /**
   * @return the silverCount
   */
  public byte getSilverCount() {
    return silverCount;
  }
  /**
   * @param silverCount the silverCount to set
   */
  public void setSilverCount(byte silverCount) {
    this.silverCount = silverCount;
  }
  /**
   * @return the glodCount
   */
  public byte getGoldCount() {
    return goldCount;
  }
  /**
   * @param glodCount the glodCount to set
   */
  public void setGoldCount(byte goldCount) {
    this.goldCount = goldCount;
  }
  /**
   * @return the platinumCount
   */
  public byte getPlatinumCount() {
    return platinumCount;
  }
  /**
   * @param platinumCount the platinumCount to set
   */
  public void setPlatinumCount(byte platinumCount) {
    this.platinumCount = platinumCount;
  }
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return ObjectUtil.buildToStringByReflect(this);
  }
}
