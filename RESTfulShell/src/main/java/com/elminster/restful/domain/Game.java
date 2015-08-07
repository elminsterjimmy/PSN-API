package com.elminster.restful.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Game implements Serializable {

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
  @Column(nullable = false, length=1024)
  private String title;
  @Column(nullable = false, length=4)
  private int totalPoint = 0;
  @Column(nullable = false, length=3)
  private int bronzeCount = 0;
  @Column(nullable = false, length=3)
  private int silverCount = 0;
  @Column(nullable = false, length=3)
  private int glodCount = 0;
  @Column(nullable = false, length=3)
  private int platinumCount = 0;
  @Column(length=1024)
  private String imageUrl;
  @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)    
  @JoinTable(name = "game_platform",
      joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id") },     
      inverseJoinColumns = { @JoinColumn(name = "platform_id", referencedColumnName = "id")     
  })   
  private List<Platform> platform;
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
  public int getTotalPoint() {
    return totalPoint;
  }
  /**
   * @param totalPoint the totalPoint to set
   */
  public void setTotalPoint(int totalPoint) {
    this.totalPoint = totalPoint;
  }
  /**
   * @return the bronzeCount
   */
  public int getBronzeCount() {
    return bronzeCount;
  }
  /**
   * @param bronzeCount the bronzeCount to set
   */
  public void setBronzeCount(int bronzeCount) {
    this.bronzeCount = bronzeCount;
  }
  /**
   * @return the silverCount
   */
  public int getSilverCount() {
    return silverCount;
  }
  /**
   * @param silverCount the silverCount to set
   */
  public void setSilverCount(int silverCount) {
    this.silverCount = silverCount;
  }
  /**
   * @return the glodCount
   */
  public int getGlodCount() {
    return glodCount;
  }
  /**
   * @param glodCount the glodCount to set
   */
  public void setGlodCount(int glodCount) {
    this.glodCount = glodCount;
  }
  /**
   * @return the platinumCount
   */
  public int getPlatinumCount() {
    return platinumCount;
  }
  /**
   * @param platinumCount the platinumCount to set
   */
  public void setPlatinumCount(int platinumCount) {
    this.platinumCount = platinumCount;
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
}
