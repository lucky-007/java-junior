package com.acme.edu.strategies;

import com.acme.edu.interfaces.DataProcessorStrategy;
import com.acme.edu.message.Message;

/**
 * Class for BYTE data processing
 */
public class ByteDataProcessorStrategy implements DataProcessorStrategy {
    /**
     * Processing previous and new message where values are BYTES
     * @param message new
     * @param savedMessage previous
     */
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
