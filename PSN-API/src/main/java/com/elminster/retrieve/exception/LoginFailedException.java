package com.elminster.retrieve.exception;

public class LoginFailedException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 2919047598000958481L;

  public LoginFailedException() {
    super();
  }

  public LoginFailedException(String message, Throwable cause) {
    super(message, cause);
  }

  public LoginFailedException(String message) {
    super(message);
  }

  public LoginFailedException(Throwable cause) {
    super(cause);
  }

}
