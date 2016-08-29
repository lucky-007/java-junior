package com.acme.edu.exceptions;

/**
 * Exception if anything failed whilst decoration
 */
public class DecorateException extends RuntimeException {
    /**
     * Exception constructor with string message and caused exception, calling super method
     * @param s exception string
     * @param e caused exception
     */
    public DecorateException(String s, ContentDecorateException e) {
        super(s,e);
    }
}
