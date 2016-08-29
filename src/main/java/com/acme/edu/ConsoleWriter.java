package com.acme.edu;

import com.acme.edu.exceptions.LoggerAppendException;
import com.acme.edu.interfaces.Writer;
import com.acme.edu.message.Message;

public class ConsoleWriter implements Writer {
    /**
     * Writes string from {@link Message}.getOutput method to console.
     * @param message
     */
    @Override
    public void write(Message message) throws LoggerAppendException {
        System.out.println(message.getResult());
    }
}
