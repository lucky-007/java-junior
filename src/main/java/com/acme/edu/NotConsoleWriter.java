package com.acme.edu;

import com.acme.edu.exceptions.LoggerAppendException;
import com.acme.edu.interfaces.Writer;

public class NotConsoleWriter implements Writer {
    @Override
    public void write(Message message) throws LoggerAppendException {
        System.out.println("[NOT CONSOLE]: " + message.getResult());
    }
}
