package com.epmrdpt.framework.exceptions;

public class NotSupportLanguageException extends RuntimeException{
    public NotSupportLanguageException(String message, Object... args) {
        super(String.format(message, args)); }
}
