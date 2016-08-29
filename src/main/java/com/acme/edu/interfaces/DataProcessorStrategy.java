package com.acme.edu.interfaces;

import com.acme.edu.message.Message;

public interface DataProcessorStrategy {
    void processData(Message message, Message savedMessage);
}
