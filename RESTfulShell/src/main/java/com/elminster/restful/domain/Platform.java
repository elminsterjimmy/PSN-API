package com.elminster.restful.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Platform implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator="system-uuid")
  @GenericGenerator(name="system-uuid", strategy = "uuid")
  private String id;
  @Column(nullable = false, length=255)
  private String platform;
  @ManyToMany(mappedBy="platform", fetch = FetchType.LAZY)
  private List<Game> games;
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
   * @return the games
   */
  public List<Game> getGames() {
    return games;
  }
  /**
   * @param games the games to set
   */
  public void setGames(List<Game> games) {
    this.games = games;
  }
  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((platform == null) ? 0 : platform.hashCode());
    return result;
  }
  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Platform other = (Platform) obj;
    if (platform == null) {
      if (other.platform != null)
        return false;
    } else if (!platform.equals(other.platform))
      return false;
    return true;
  }
}
