package com.elminster.retrieve.data.game;

/**
 * The platform enum.
 * 
 * @author jgu
 * @version 1.0
 */
public enum Platform {

  UNKNOWN("Unknown"), PSN("PSN"), PSP("PSP"), PS2("PS2"), PS3("PS3"), PSV("PSV"), PS4("PS4");

  private final String name;

  Platform(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public static Platform getPlatformFromString(String platforStr) {
    switch (platforStr) {
      case "psn":
        return PSN;
      case "psp":
        return PSP;
      case "ps2":
        return PS2;
      case "ps3":
        return PS3;
      case "ps4":
        return PS4;
      case "vita":
        return PSV;
      default:
        return UNKNOWN;
    }
  }
}
