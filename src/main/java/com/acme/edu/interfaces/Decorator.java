package com.acme.edu.interfaces;

import com.acme.edu.message.Message;
import com.acme.edu.exceptions.DecorateException;

public interface Decorator {
    String decorate(Message message) throws DecorateException;
}
