package com.acme.edu.exceptions;

/**
 * Exception if decoreation of the Value is failed
 */
public class ContentDecorateException extends Exception {
    /**
     * Exception constructor with string message, calling super method
     * @param s exception string
     */
    public ContentDecorateException(String s) {
        super(s);
    }
}
