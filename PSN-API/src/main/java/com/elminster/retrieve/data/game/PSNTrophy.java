package com.elminster.retrieve.data.game;

import com.elminster.common.util.ObjectUtil;

/**
 * The PSN trophy.
 * 
 * @author jgu
 * @version 1.0
 */
public class PSNTrophy {

  private String gameId;
  private String trophyId;
  private String title;
  private String description;
  private TrophyType type;
  private short point;
  private String imageUrl;
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
   * @return the trophyId
   */
  public String getTrophyId() {
    return trophyId;
  }
  /**
   * @param trophyId the trophyId to set
   */
  public void setTrophyId(String trophyId) {
    this.trophyId = trophyId;
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
   * @return the description
   */
  public String getDescription() {
    return description;
  }
  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }
  /**
   * @return the type
   */
  public TrophyType getType() {
    return type;
  }
  /**
   * @param type the type to set
   */
  public void setType(TrophyType type) {
    this.type = type;
  }
  /**
   * @return the point
   */
  public short getPoint() {
    return point;
  }
  /**
   * @param point the point to set
   */
  public void setPoint(short point) {
    this.point = point;
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
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return ObjectUtil.buildToStringByReflect(this);
  }
}
