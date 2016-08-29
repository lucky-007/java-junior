package com.acme.edu.interfaces;

import com.acme.edu.message.Message;

/**
 * Decorator interface
 */
public interface Decorator {
    /**
     * Method for decoration  message String
     * @param message input Message.
     * @return Decorated String
     */
    String decorate(Message message);
}
