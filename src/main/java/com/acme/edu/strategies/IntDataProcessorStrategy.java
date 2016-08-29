package com.acme.edu.strategies;

import com.acme.edu.message.Message;
import com.acme.edu.interfaces.CountingDataProcessorStrategy;

/**
 * Class for INTEGER data processing
 */
public class IntDataProcessorStrategy implements CountingDataProcessorStrategy {
    /**
     * Processing previous and new message where values are INTEGERS
     * @param message input message
     * @param savedMessage processed message
     */
    @Override
    public void processData(Message message, Message savedMessage) {
        if(Integer.MAX_VALUE - Math.abs((int) savedMessage.getValue()) < (int) message.getValue()
                || Integer.MIN_VALUE + Math.abs((int) savedMessage.getValue()) > (int) message.getValue()) {
            savedMessage.setFlagToWrite(true);
        } else {
            savedMessage.setValue((int) savedMessage.getValue() + (int) message.getValue());
        }
    }
}
