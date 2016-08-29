package com.acme.edu.exceptions;

public class DecorateException extends RuntimeException {
    public DecorateException(String s, ContentDecorateException e) {
        super(s,e);
    }
}
