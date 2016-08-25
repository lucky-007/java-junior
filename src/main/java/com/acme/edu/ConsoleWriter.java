package com.acme.edu;

public class ConsoleWriter implements Writer {
    /**
     * Writes string from {@link Message}.getOutput method to console.
     * @param message
     */
    @Override
    public void write(Message message) {
        System.out.println(message.getOutput());
    }
}
