package com.acme.edu.exceptions;

public class LoggingNullPointerException extends RuntimeException {
    public LoggingNullPointerException() {
        super();
    }

    public LoggingNullPointerException(String message) {
        super (message);
    }

    public LoggingNullPointerException(Throwable cause) {
        super (cause);
    }

    public LoggingNullPointerException(String message, Throwable cause) {
        super (message, cause);
    }
}
