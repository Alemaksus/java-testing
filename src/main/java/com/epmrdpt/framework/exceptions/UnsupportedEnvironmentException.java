package com.epmrdpt.framework.exceptions;

public class UnsupportedEnvironmentException extends RuntimeException {

  public UnsupportedEnvironmentException(String message, Object... params) {
    super(String.format(message, params));
  }

  public UnsupportedEnvironmentException(String message, Throwable cause) {
    super(message, cause);
  }
}
