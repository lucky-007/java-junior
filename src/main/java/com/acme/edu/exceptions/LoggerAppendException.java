package com.acme.edu.exceptions;

import java.io.FileNotFoundException;

/**
 * Exception if something went wrong while logging
 */
public class LoggerAppendException extends Exception {
    /**
     * Public default constructor
     * @param s
     */
    public  LoggerAppendException(String s) {
        super(s);
    }

    public LoggerAppendException(String s, FileNotFoundException e) {
        super(s,e);
    }
}
