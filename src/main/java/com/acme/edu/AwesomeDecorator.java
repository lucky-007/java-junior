package com.acme.edu;

public class AwesomeDecorator implements Decorator {
    @Override
    public String decorate(Message message) {
        return "FIIIINEEEE:   " +message.getValue().toString() + "   Looooooooooo";
    }
}
