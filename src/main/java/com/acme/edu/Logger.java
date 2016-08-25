package com.acme.edu;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logger {

    private Decorator decorator;
    private Writer writer;
    private Message savedMessage = null;
    private int stringsCount = 1;

    private void processSequenceData(Message message) {
        switch(message.getType()) {
            case "java.lang.Byte":
                if(Byte.MAX_VALUE - Math.abs((byte)savedMessage.getValue()) < (byte)message.getValue()) {
                    printSavedObjectAndSaveNewOne(message);
                } else {
                    savedMessage.setValue ( (byte)((byte) savedMessage.getValue() + (byte)message.getValue()));
                }
                break;
            case "java.lang.Integer":
                if(Integer.MAX_VALUE - Math.abs((int)savedMessage.getValue()) < (int)message.getValue()) {
                    printSavedObjectAndSaveNewOne(message);
                } else {
                    savedMessage.setValue ((int) savedMessage.getValue() + (int)message.getValue());
                }
                break;
            case "java.lang.String":
                Pattern p = Pattern.compile("^"+ message.getValue() +"($| \\(x\\d+\\)$)");
                Matcher m = p.matcher((String) savedMessage.getValue());
                if(m.matches()) {
                    savedMessage.setValue( message.getValue() + " (x" + (++stringsCount) + ")");
                } else {
                    printSavedObjectAndSaveNewOne(message);
                    stringsCount = 1;
                }
                break;
            default:
                printSavedObjectAndSaveNewOne(message);
                break;
        }
    }
    public Logger(Decorator decorator, Writer writer) {
        this.decorator = decorator;
        this.writer = writer;
    }

    private void printSavedObjectAndSaveNewOne(Message message) {
        if (savedMessage != null) {
            decorator.decorate(savedMessage);
            writer.write(savedMessage);
        }
        savedMessage = message;
    }

    private boolean checkWithPrevMessage(Message newMessage) {
        return savedMessage != null && Objects.equals(newMessage.getType(), savedMessage.getType());
    }

    /**
     * Terminates the process of logging
     * and adds the result of last logging request.
     */
    public void stopLogging() {
        printSavedObjectAndSaveNewOne(null);
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes Object
     */
    public void log (Object mes) {
        Message message = new Message(mes);
        if (checkWithPrevMessage(message)) {
            processSequenceData(message);
        } else {
            printSavedObjectAndSaveNewOne(message);
        }
    }

    public void log (Object... mes) {
        for (Object element: mes) {
            log(element);
        }
    }

    public void logIntArray(int[] mes) {
        Message message = new Message(mes);
        if (checkWithPrevMessage(message)) {
            processSequenceData(message);
        } else {
            printSavedObjectAndSaveNewOne(message);
        }
    }

    public void logIntArray(int[][] mes) {
        Message message = new Message(mes);
        if (checkWithPrevMessage(message)) {
            processSequenceData(message);
        } else {
            printSavedObjectAndSaveNewOne(message);
        }
    }

    public void logIntArray(int[][][][] mes) {
        Message message = new Message(mes);
        if (checkWithPrevMessage(message)) {
            processSequenceData(message);
        } else {
            printSavedObjectAndSaveNewOne(message);
        }
    }
}