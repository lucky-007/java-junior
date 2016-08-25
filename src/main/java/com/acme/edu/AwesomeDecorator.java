package com.acme.edu;

public class AwesomeDecorator implements Decorator {
    @Override
    public void decorate(Message message) {
        message.setOutput("FIIIINEEEE:   " +message.getValue().toString() + "   Looooooooooo");
    }
}
