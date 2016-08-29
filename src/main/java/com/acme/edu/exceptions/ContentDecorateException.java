package com.acme.edu.exceptions;

public class ContentDecorateException extends Exception {
    public ContentDecorateException(String s, NullPointerException e) {
        super(s,e);
    }

    public ContentDecorateException(String s) {
        super(s);
    }
}
