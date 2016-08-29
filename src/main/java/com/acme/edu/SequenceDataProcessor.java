package com.acme.edu;

import com.acme.edu.interfaces.DataProcessor;
import com.acme.edu.interfaces.DataProcessorStrategy;
import com.acme.edu.strategies.ByteDataProcessorStrategy;
import com.acme.edu.strategies.IntDataProcessorStrategy;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SequenceDataProcessor implements DataProcessor {

    public SequenceDataProcessor() {
        this.savedMessage = null;
        this.count = 0;
    }

    @Override
    public Message processData(Message message) {
        if (savedMessage != null){
            if (message != null) {
                if (Objects.equals(message.getType(), savedMessage.getType())){
                    processSequenceData(message);
                }
                else {
                    savedMessage.setFlagToWrite(true);
                }
            }
            else {
                savedMessage.setFlagToWrite(true);
            }
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

    private Message savedMessage;
    private int count;

    private void processSequenceData(Message message) {
        DataProcessorStrategy dataProcessorStrategy = null;
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
                Pattern p = Pattern.compile("^"+ message.getValue() +"($| \\(x\\d+\\)$)");
                Matcher m = p.matcher((String) savedMessage.getValue());
                if(m.matches()) {
                    savedMessage.setValue(message.getValue() + " (x" + (++count) + ")");
                } else {
                    savedMessage.setFlagToWrite(true);
                    count = 1;
                }
                break;
            default:
                savedMessage.setFlagToWrite(true);
                break;
        }
    }
}
