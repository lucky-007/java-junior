package com.acme.edu.interfaces;

import com.acme.edu.message.Message;

/**
 * Interface for processing data.
 */
public interface DataProcessorStrategy {
    /**
     * Method that process message comparing with previous message
     * @param message input message
     * @param savedMessage processed message
     */
    void processData(Message message, Message savedMessage);
}
