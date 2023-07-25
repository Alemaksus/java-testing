package com.epmrdpt.framework.exceptions;

public class NoSuchTrainingException extends NullPointerException{

  public NoSuchTrainingException(String message, Object... params) {
    super(String.format(message, params));
  }

  public NoSuchTrainingException(String message, Throwable cause) {
    super(message);
  }
}
