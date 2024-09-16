package com.crio.coderhack.exception;

public class InvalidBadgeSetException extends RuntimeException {

    public InvalidBadgeSetException(String message) {
        super(message);
    }

    public InvalidBadgeSetException(String message, Throwable cause) {
        super(message, cause);
    }
}
