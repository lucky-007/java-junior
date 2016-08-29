package com.acme.edu.interfaces;

import com.acme.edu.message.Message;
import com.acme.edu.exceptions.LoggerAppendException;

/**
 * Interface of logger writer.
 */
public interface Writer {
    /**
     * Writes string from {@link Message}.getOutput method to different sources.
     * @param message input message
     * @throws LoggerAppendException if something goes wrong whilst writing.
     */
    void write(Message message) throws LoggerAppendException;
}
