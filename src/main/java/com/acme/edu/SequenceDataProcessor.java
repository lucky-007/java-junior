package com.acme.edu;

import com.acme.edu.interfaces.DataProcessor;
import com.acme.edu.interfaces.DataProcessorStrategy;
import com.acme.edu.message.Message;
import com.acme.edu.strategies.ByteDataProcessorStrategy;
import com.acme.edu.strategies.IntDataProcessorStrategy;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class processing data with compliance with business logic.
 */
public class SequenceDataProcessor implements DataProcessor {

    private Message savedMessage;
    private int count;

    /**
     * Constructor of processor of sequence data.
     * Sets previous message to NULL and count to ZERO.
     */
    public SequenceDataProcessor() {
        this.savedMessage = null;
        this.count = 1;
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
            if ((message != null) && Objects.equals(message.getType(), savedMessage.getType())){
                processSequenceData(message);
            } else {
                savedMessage.setFlagToWrite(true);
                count = 1;
            }
            outputMessage = savedMessage;
            if (savedMessage.getFlagToWrite()) {
                savedMessage = message;
            }
        } else {
            savedMessage = message;
            return savedMessage;
        }
        return outputMessage;
    }

    private void processSequenceData(Message message) {
        DataProcessorStrategy dataProcessorStrategy;
        switch(message.getType()) {
            case "java.lang.Byte":
                dataProcessorStrategy = new ByteDataProcessorStrategy();
                dataProcessorStrategy.processData(message, savedMessage);
                break;
            case "java.lang.Integer":
                dataProcessorStrategy = new IntDataProcessorStrategy();
                dataProcessorStrategy.processData(message, savedMessage);
                break;
            case "java.lang.String":
                processStringData(message);
                break;
            default:
                savedMessage.setFlagToWrite(true);
                break;
        }
    }

    private void processStringData(Message message) {
        Pattern p = Pattern.compile("^"+ message.getValue() +"($| \\(x\\d+\\)$)");
        Matcher m = p.matcher((String) savedMessage.getValue());
        if(m.matches()) {
            savedMessage.setValue(message.getValue() + " (x" + (++count) + ")");
        } else {
            savedMessage.setFlagToWrite(true);
            count = 1;
        }
    }
}
