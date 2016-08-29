package com.acme.edu.exceptions;

public class LoggerAppendException extends Exception {
    public LoggerAppendException () {
        super();
    }

    public LoggerAppendException (String message) {
        super (message);
    }

    public LoggerAppendException (Throwable cause) {
        super (cause);
    }

    public LoggerAppendException (String message, Throwable cause) {
        super (message, cause);
    }
}
