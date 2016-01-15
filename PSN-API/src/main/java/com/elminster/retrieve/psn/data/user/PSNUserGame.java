package com.elminster.retrieve.psn.data.user;

import com.elminster.common.util.ObjectUtil;
import com.elminster.retrieve.psn.data.game.PSNGame;

public class PSNUserGame extends PSNGame {

  private short earnedPoint;
  private byte earnedBronze;
  private byte earnedSilver;
  private byte earnedGold;
  private byte earnedPlatinum;
  private byte completionPercent;
  /**
   * @return the earnedPoint
   */
  public short getEarnedPoint() {
    return earnedPoint;
  }
  /**
   * @param earnedPoint the earnedPoint to set
   */
  public void setEarnedPoint(short earnedPoint) {
    this.earnedPoint = earnedPoint;
  }
  /**
   * @return the completion
   */
  public byte getCompletionByPercent() {
    return completionPercent;
  }
  /**
   * @param completion the completion to set
   */
  public void setCompletionByPercent(byte completion) {
    this.completionPercent = completion;
  }
  
  /**
   * @return the earnedBronze
   */
  public byte getEarnedBronze() {
    return earnedBronze;
  }
  /**
   * @param earnedBronze the earnedBronze to set
   */
  public void setEarnedBronze(byte earnedBronze) {
    this.earnedBronze = earnedBronze;
  }
  /**
   * @return the earnedSilver
   */
  public byte getEarnedSilver() {
    return earnedSilver;
  }
  /**
   * @param earnedSilver the earnedSilver to set
   */
  public void setEarnedSilver(byte earnedSilver) {
    this.earnedSilver = earnedSilver;
  }
  /**
   * @return the earnedGold
   */
  public byte getEarnedGold() {
    return earnedGold;
  }
  /**
   * @param earnedGold the earnedGold to set
   */
  public void setEarnedGold(byte earnedGold) {
    this.earnedGold = earnedGold;
  }
  /**
   * @return the earnedPlatinum
   */
  public byte getEarnedPlatinum() {
    return earnedPlatinum;
  }
  /**
   * @param earnedPlatinum the earnedPlatinum to set
   */
  public void setEarnedPlatinum(byte earnedPlatinum) {
    this.earnedPlatinum = earnedPlatinum;
  }
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return ObjectUtil.buildToStringByReflect(this);
  }
}
