package com.acme.edu.interfaces;

import com.acme.edu.message.Message;
import com.acme.edu.exceptions.LoggerAppendException;

public interface Writer {
    void write(Message message) throws LoggerAppendException;
}
