package com.acme.edu;

import com.acme.edu.interfaces.Decorator;
import com.acme.edu.message.Message;

/**
 * Decorator for demonstration polymorphism.
 */
public class AwesomeDecorator implements Decorator {
    /**
     * Method for decoration  message String
     * @param message Input message
     * @return Decorated String
     */
    @Override
    public String decorate(Message message) {
        return "FIIIINEEEE:   " +message.getValue().toString() + "   Looooooooooo";
    }
}
