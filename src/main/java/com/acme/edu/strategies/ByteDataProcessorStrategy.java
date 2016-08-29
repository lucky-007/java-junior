package com.acme.edu.strategies;

import com.acme.edu.message.Message;
import com.acme.edu.interfaces.CountingDataProcessorStrategy;

/**
 * Created by daniil on 29.08.16.
 */
public class ByteDataProcessorStrategy implements CountingDataProcessorStrategy {
    @Override
    public void processData(Message message, Message savedMessage) {
        if(Byte.MAX_VALUE - Math.abs((byte) savedMessage.getValue()) < (byte) message.getValue()
                || Byte.MIN_VALUE + Math.abs((byte) savedMessage.getValue()) > (byte) message.getValue()) {
            savedMessage.setFlagToWrite(true);
        } else {
            savedMessage.setValue((byte)((byte) savedMessage.getValue() + (byte) message.getValue()));
        }
    }
}
