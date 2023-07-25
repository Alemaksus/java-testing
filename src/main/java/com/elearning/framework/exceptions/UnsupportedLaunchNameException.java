package com.epmrdpt.framework.exceptions;

public class UnsupportedLaunchNameException extends RuntimeException {

  public UnsupportedLaunchNameException(String message, Object... params) {
    super(String.format(message, params));
  }
}
