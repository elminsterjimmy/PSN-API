package com.elminster.retrieve.data.game;

public enum TrophyType {

  UNKNOWN("Unknown"),
  BRONZE("Bronze"),
  SILVER("Silver"),
  GOLD("Gold"), 
  PLATINUM("Platinum");

  private final String name;

  TrophyType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
  
  public static TrophyType getTrophyType(String type) {
    switch (type) {
      case "BRONZE":
        return BRONZE;
      case "SILVER":
        return SILVER;
      case "GOLD":
        return GOLD;
      case "PLATINUM":
        return PLATINUM;
      default:
        return UNKNOWN;
    }
  }
}
