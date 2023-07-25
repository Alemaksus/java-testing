package com.epmrdpt.framework.exceptions;

public class UnsupportedScopeException extends RuntimeException{

  public UnsupportedScopeException(String message, Object... params) {
    super(String.format(message, params));
  }

  public UnsupportedScopeException(String message, Throwable cause) {
    super(message, cause);
  }
}
