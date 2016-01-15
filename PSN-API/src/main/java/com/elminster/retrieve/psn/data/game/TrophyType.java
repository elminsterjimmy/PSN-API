package com.elminster.retrieve.psn.data.game;

public enum TrophyType {

  UNKNOWN("Unknown", 0xFF),
  BRONZE("Bronze", 0x01),
  SILVER("Silver", 0x02),
  GOLD("Gold", 0x04), 
  PLATINUM("Platinum", 0x08);

  private final String name;
  private final int type;

  TrophyType(String name, int type) {
    this.name = name;
    this.type = type;
  }

  public String getName() {
    return name;
  }
  
  public int getType() {
    return type;
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
  
  public static TrophyType getTrophyType(int type) {
    for (TrophyType t : TrophyType.values()) {
      if (type == t.getType()) {
        return t;
      }
    }
    return UNKNOWN;
  }
}
