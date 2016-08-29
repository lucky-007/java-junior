package com.acme.edu;

import com.acme.edu.message.Message;
import com.acme.edu.interfaces.DataProcessor;

/**
 * Created by daniil on 28.08.16.
 */
public class SimpleDataProcessor implements DataProcessor {

    private Message savedMessage;

    public SimpleDataProcessor() {
        this.savedMessage = null;
    }

    @Override
    public Message processData(Message message) {
        if (savedMessage != null){
            savedMessage.setFlagToWrite(true);
        }
        else {
            savedMessage = message;
        }
        return savedMessage;
    }

    @Override
    public void setMessage(Message message) {
        this.savedMessage = message;
    }
}
