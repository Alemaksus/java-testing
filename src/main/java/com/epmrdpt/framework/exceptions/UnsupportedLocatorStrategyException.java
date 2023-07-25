package com.epmrdpt.framework.exceptions;

public class UnsupportedLocatorStrategyException extends RuntimeException {

    public UnsupportedLocatorStrategyException(String message, Object... params) {
        super(String.format(message, params));
    }

    public UnsupportedLocatorStrategyException(String message, Throwable cause) {
        super(message, cause);
    }
}
