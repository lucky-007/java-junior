package com.acme.edu.strategies;

import com.acme.edu.Message;
import com.acme.edu.interfaces.CountingDataProcessorStrategy;

/**
 * Created by daniil on 29.08.16.
 */
public class IntDataProcessorStrategy implements CountingDataProcessorStrategy {
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
