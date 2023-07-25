package com.epmrdpt.framework.exceptions;

public class UnsupportedModeException extends RuntimeException{

  public UnsupportedModeException(String message, Object... params) {
    super(String.format(message, params));
  }

  public UnsupportedModeException(String message, Throwable cause) {
    super(message, cause);
  }
}
