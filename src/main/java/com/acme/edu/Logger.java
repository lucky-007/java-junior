package com.acme.edu;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logger {

    /**
     * Logger constructor.
     * @param decorator Decorator
     * @param writer Writer
     */
    public Logger(Decorator decorator, Writer writer) {
        this.decorator = decorator;
        this.writer = writer;
        savedMessage = null;
        stringsCount = 1;
    }

    /**
     * Terminates the process of logging
     * and adds the result of last logging request.
     */
    public void stopLogging() {
        decoratePrintSavedMessageAndSaveNewOne(null);
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes Object...
     */
    public void log (Object... mes) {
        for (Object element: mes) {
            log(element);
        }
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes Object
     */
    public void log (Object mes) {
        Message message = new Message(mes);
        processMessage(message);
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes int[]
     */
    public void logIntArray(int[] mes) {
        Message message = new Message(mes);
        processMessage(message);
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes int[][]
     */
    public void logIntArray(int[][] mes) {
        Message message = new Message(mes);
        processMessage(message);
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes int[][][][]
     */
    public void logIntArray(int[][][][] mes) {
        Message message = new Message(mes);
        processMessage(message);
    }

    private final Decorator decorator;
    private final Writer writer;
    private Message savedMessage;
    private int stringsCount;

    private void processSequenceData(Message message) {
        switch(message.getType()) {
            case "java.lang.Byte":
                if((int) Byte.MAX_VALUE - (int) (byte) savedMessage.getValue() < (int) (byte) message.getValue()) {
                    decoratePrintSavedMessageAndSaveNewOne(message);
                } else {
                    savedMessage.setValue ( (byte)((byte) savedMessage.getValue() + (byte)message.getValue()));
                }
                break;
            case "java.lang.Integer":
                if((long) Integer.MAX_VALUE - (long) (int) savedMessage.getValue() < (long) (int) message.getValue()) {
                    decoratePrintSavedMessageAndSaveNewOne(message);
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
                    decoratePrintSavedMessageAndSaveNewOne(message);
                    stringsCount = 1;
                }
                break;
            default:
                decoratePrintSavedMessageAndSaveNewOne(message);
                break;
        }
    }

    private void decoratePrintSavedMessageAndSaveNewOne(Message message) {
        if (savedMessage != null) {
            decorator.decorate(savedMessage);
            writer.write(savedMessage);
        }
        savedMessage = message;
    }

    private boolean checkWithPrevMessage(Message newMessage) {
        return savedMessage != null && Objects.equals(newMessage.getType(), savedMessage.getType());
    }

    private void processMessage(Message message) {
        if (checkWithPrevMessage(message)) {
            processSequenceData(message);
        } else {
            decoratePrintSavedMessageAndSaveNewOne(message);
        }
    }
}