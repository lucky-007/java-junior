package com.acme.edu.interfaces;

import com.acme.edu.message.Message;

/**
 * interface for different content decorations
 */
public interface DecorContentStrategy {
    /**
     * Method decorating message
     * @param message input message
     * @return decorated string
     */
    String decorateContent(Message message);
}
