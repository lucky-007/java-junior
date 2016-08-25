package com.acme.edu;

import com.acme.edu.Decorator;

public class AwesomeDecorator implements Decorator {
    @Override
    public void decorate(Message message) {
        message.setOutput("FIIIINEEEE:   " +message.getValue().toString() + "   Looooooooooo");
    }
}
