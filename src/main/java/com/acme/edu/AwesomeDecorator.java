package com.acme.edu;

import com.acme.edu.interfaces.Decorator;

public class AwesomeDecorator implements Decorator {
    @Override
    public String decorate(Message message) {
        return "FIIIINEEEE:   " +message.getValue().toString() + "   Looooooooooo";
    }
}
