package com.epmrdpt.framework.exceptions;

public class MethodNameParseException extends RuntimeException {

  public MethodNameParseException(String message, Object... params) {
    super(String.format(message, params));
  }

  public MethodNameParseException(String message, Throwable cause) {
    super(message, cause);
  }
}

