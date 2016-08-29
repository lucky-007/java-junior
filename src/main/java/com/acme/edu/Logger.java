package com.acme.edu;

import com.acme.edu.exceptions.DecorateException;
import com.acme.edu.exceptions.LoggerAppendException;
import com.acme.edu.exceptions.LoggingNullPointerException;
import com.acme.edu.interfaces.DataProcessor;
import com.acme.edu.interfaces.Decorator;
import com.acme.edu.interfaces.Writer;
import com.acme.edu.message.Message;

public class Logger {

    private final Decorator decorator;
    private final DataProcessor dataProcessor;
    private final Writer[] listOfWriters;

    /**
     * Logger constructor.
     * @param decorator Decorator
     * @param dataProcessor DataProcessor
     * @param listOfWriters Writer
     */
    public Logger(Decorator decorator, DataProcessor dataProcessor, Writer... listOfWriters) {

        this.listOfWriters = new Writer[listOfWriters.length];

        this.decorator = decorator;
        this.dataProcessor = dataProcessor;
        System.arraycopy(listOfWriters, 0, this.listOfWriters, 0, listOfWriters.length);
    }

    /**
     * Terminates the process of logging
     * and adds the result of last logging request.
     */
    public void stopLogging() {
        decorateAndPrintMessage(dataProcessor.processData(null));
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes Object...
     */
    public void log (Object... mes) {
        try {
            for (Object element : mes) {
                log(element);
            }
        } catch (NullPointerException e) {
            throw new LoggingNullPointerException("Null pointer in the input of logger!", e);
        }
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes Object
     */
    public void log (Object mes) {
        try {
            Message message = new Message(mes);
            processMessage(message);
        } catch (NullPointerException e) {
            throw new LoggingNullPointerException("Null pointer in the input of logger!", e);
        }
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
     * @param mes int[][][]
     */
    public void logIntArray(int[][][] mes) {
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


    private void decorateAndPrintMessage(Message message) {
        try {
            message.setResult(decorator.decorate(message));
            for (Writer writer: listOfWriters) {
                try {
                    writer.write(message);
                } catch (LoggerAppendException e) {
                    e.printStackTrace();
                }
            }
        } catch (DecorateException e) {
            e.printStackTrace();
        }
    }

    private void processMessage(Message message) {
        Message savedMessage = dataProcessor.processData(message);
        if (savedMessage.getFlagToWrite()) {
            decorateAndPrintMessage(savedMessage);
            dataProcessor.setMessage(message);
        }
    }
}

