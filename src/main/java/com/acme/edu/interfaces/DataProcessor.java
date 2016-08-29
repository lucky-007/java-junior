package com.acme.edu.interfaces;

import com.acme.edu.message.Message;

/**
 * Interface for processing data.
 */
public interface DataProcessor {
    /**
     * Method for processing data of {@link Message} type.
     * @param message input message
     * @return processed Message
     */
    Message processData(Message message);
}
