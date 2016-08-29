package com.acme.edu;

import com.acme.edu.interfaces.Decorator;
import com.acme.edu.message.Message;

public class AwesomeDecorator implements Decorator {
    @Override
    public String decorate(Message message) {
        return "FIIIINEEEE:   " +message.getValue().toString() + "   Looooooooooo";
    }
}
