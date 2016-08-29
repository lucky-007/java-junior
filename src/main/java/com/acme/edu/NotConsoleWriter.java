package com.acme.edu;

import com.acme.edu.interfaces.Writer;

public class NotConsoleWriter implements Writer {
    @Override
    public void write(Message message) {
        System.out.println("[NOT CONSOLE]: " + message.getResult());
    }
}
