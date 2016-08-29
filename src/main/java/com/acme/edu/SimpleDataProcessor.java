package com.acme.edu;

import com.acme.edu.message.Message;
import com.acme.edu.interfaces.DataProcessor;

/**
 * Class of data processing for presentation of polymorphism.
 */
public class SimpleDataProcessor implements DataProcessor {

    private Message savedMessage;

    /**
     * Constructor of processor of sequence data.
     * Sets previous message to NULL.
     */
    public SimpleDataProcessor() {
        this.savedMessage = null;
    }

    /**
     * Processing sequential messages. If they are different types, this method just set the FlagToWrite.
     * If they are same type, method will compare theirs values and will do business logic with them,
     * setting or un-setting the FlagToWrite.
     *
     * User of this method have to check FlagToWrite by using getFlagToWrite method. And if it is true, user need to
     * send to {@link com.acme.edu.interfaces.Writer}.
     *
     * @param message input message.
     * @return Processed Message object.
     */
    @Override
    public Message processData(Message message) {
        Message outputMessage;
        if (savedMessage != null) {
            outputMessage = savedMessage;
            outputMessage.setFlagToWrite(true);
            savedMessage = message;
        } else {
            savedMessage = message;
            return savedMessage;
        }
        return outputMessage;
    }
}
