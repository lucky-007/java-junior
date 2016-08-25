package com.acme.edu;

class ToStringDecorContentStrategy implements DecorContentStrategy {
    @Override
    public String decorate(Message message) {
        return message.getValue().toString();
    }
}
