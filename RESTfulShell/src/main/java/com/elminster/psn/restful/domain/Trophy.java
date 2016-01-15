package com.elminster.psn.restful.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * The trophy entity.
 * 
 * @author jgu
 * @version 1.0
 */
@Entity
public class Trophy implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(generator="system-uuid")
  @GenericGenerator(name="system-uuid", strategy = "uuid")
  private String id;
  @Column(nullable = false, length=255)
  private String gameId;
  @Column(nullable = false, length=255)
  private String trophyId;
  @Column(nullable = false, length=3)
  private int trophyOrder;
  @Column(nullable = false, length=1024)
  private String title;
  @Column(nullable = false, length=4)
  private int point = 0;
  @Column(nullable = false, length=3)
  private int type;
  @Column(nullable = false, length=1024)
  private String description;
  @Column(length=1024)
  private String imageUrl;
  @Column(length=1) // 0: not updated (no one earned), 1: updated
  private int status = 0;
  /**
   * @return the id
   */
  public String getId() {
    return id;
  }
  /**
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
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
   * @return the trophyOrder
   */
  public int getTrophyOrder() {
    return trophyOrder;
  }
  /**
   * @param trophyOrder the trophyOrder to set
   */
  public void setTrophyOrder(int trophyOrder) {
    this.trophyOrder = trophyOrder;
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
   * @return the point
   */
  public int getPoint() {
    return point;
  }
  /**
   * @param point the point to set
   */
  public void setPoint(int point) {
    this.point = point;
  }
  /**
   * @return the type
   */
  public int getType() {
    return type;
  }
  /**
   * @param type the type to set
   */
  public void setType(int type) {
    this.type = type;
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
   * @return the status
   */
  public int getStatus() {
    return status;
  }
  /**
   * @param status the status to set
   */
  public void setStatus(int status) {
    this.status = status;
  }
}
