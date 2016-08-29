package com.acme.edu.strategies;

import com.acme.edu.message.Message;
import com.acme.edu.interfaces.DecorContentStrategy;

/**
 * Primitives decoration strategy
 */
public class ToStringDecorContentStrategy implements DecorContentStrategy {
    /**
     * primitive values decoration
     * @param message input message
     * @return decor string
     */
    @Override
    public String decorateContent(Message message) {
        return message.getValue().toString();
    }
}
