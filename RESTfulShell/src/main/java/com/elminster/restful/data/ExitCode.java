package com.elminster.restful.data;

public enum ExitCode {

  NORMAL(0),
  COOKIE_SAVE_FAILED(0x01),
  DB_DUMP_FAILED(0x02);
  
  private final int code;
  
  ExitCode(int code) {
    this.code = code;
  }
  
  public int getCode() {
    return code;
  }
}
