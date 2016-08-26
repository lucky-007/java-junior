package com.acme.edu;

class ToStringDecorContentStrategy implements DecorContentStrategy {
    @Override
    public String decorateContent(Message message) {
        return message.getValue().toString();
    }
}
