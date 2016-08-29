package com.acme.edu;

import com.acme.edu.interfaces.DecorContentStrategy;

class ToStringDecorContentStrategy implements DecorContentStrategy {
    @Override
    public String decorateContent(Message message) {
        return message.getValue().toString();
    }
}
