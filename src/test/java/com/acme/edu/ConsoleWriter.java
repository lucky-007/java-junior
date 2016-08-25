package com.acme.edu;

/**
 * Created by daniil on 25.08.16.
 */
public class ConsoleWriter implements Writer {
    @Override
    public void write(Message message) {
        System.out.println(message.getOutput());
    }
}
