package com.acme.edu.exceptions;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

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

    public LoggerAppendException(String s, Exception e) {
        super(s,e);
    }
}
