package com.acme.edu.exceptions;

/**
 * exception if user passed null pointer to logging method
 */
public class LoggingNullPointerException extends RuntimeException {
    /**
     * Exception constructor with string message and caused exception, calling super method
     * @param message exception message
     * @param cause caused exception
     */
    public LoggingNullPointerException(String message, Throwable cause) {
        super (message, cause);
    }
}
