package com.acme.edu;

import com.acme.edu.interfaces.Decorator;
import com.acme.edu.message.Message;

public class AwesomeDecorator implements Decorator {
    /**
     * Decorator for demonstration polymorphism.
     * @param message Input message
     * @return Decorated String
     */
    @Override
    public String decorate(Message message) {
        return "FIIIINEEEE:   " +message.getValue().toString() + "   Looooooooooo";
    }
}
