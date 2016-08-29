package com.acme.edu;

import com.acme.edu.interfaces.DataProcessor;

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
        if ((savedMessage != null)){
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
        switch(message.getType()) {
            case "java.lang.Byte":
                if(Byte.MAX_VALUE - Math.abs((byte) savedMessage.getValue()) < (byte) message.getValue()
                        || Byte.MIN_VALUE + Math.abs((byte) savedMessage.getValue()) > (byte) message.getValue()) {
                    savedMessage.setFlagToWrite(true);
                } else {
                    savedMessage.setValue((byte)((byte) savedMessage.getValue() + (byte) message.getValue()));
                }
                break;
            case "java.lang.Integer":
                if(Integer.MAX_VALUE - Math.abs((int) savedMessage.getValue()) < (int) message.getValue()
                        || Integer.MIN_VALUE + Math.abs((int) savedMessage.getValue()) > (int) message.getValue()) {
                    savedMessage.setFlagToWrite(true);
                } else {
                    savedMessage.setValue((int) savedMessage.getValue() + (int) message.getValue());
                }
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
