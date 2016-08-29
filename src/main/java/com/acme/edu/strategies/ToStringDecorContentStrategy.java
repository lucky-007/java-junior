package com.acme.edu.strategies;

import com.acme.edu.message.Message;
import com.acme.edu.interfaces.DecorContentStrategy;

public class ToStringDecorContentStrategy implements DecorContentStrategy {
    @Override
    public String decorateContent(Message message) {
        return message.getValue().toString();
    }
}
