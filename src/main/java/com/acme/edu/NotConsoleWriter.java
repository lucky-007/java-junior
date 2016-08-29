package com.acme.edu;

import com.acme.edu.exceptions.LoggerAppendException;
import com.acme.edu.interfaces.Writer;
import com.acme.edu.message.Message;

/**
 * Writer to console for demonstration of polymorphism.
 */
public class NotConsoleWriter implements Writer {
    /**
     * Writes string from {@link Message}.getOutput method to console with prefix.
     * @param message input message
     * @throws LoggerAppendException if something goes wrong whilst writing.
     */
    @Override
    public void write(Message message) throws LoggerAppendException {
        System.out.println("[NOT CONSOLE]: " + message.getResult());
    }
}
