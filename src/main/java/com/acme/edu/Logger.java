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
        tryDecorateAndPrintMessage(dataProcessor.processData(null));
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
            createAndProcessMessage(mes);
        } catch (NullPointerException e) {
            throw new LoggingNullPointerException("Null pointer in the input of logger!", e);
        }
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes int[]
     */
    public void logIntArray(int[] mes) {
        createAndProcessMessage(mes);
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes int[][]
     */
    public void logIntArray(int[][] mes) {
        createAndProcessMessage(mes);
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes int[][][]
     */
    public void logIntArray(int[][][] mes) {
        createAndProcessMessage(mes);
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes int[][][][]
     */
    public void logIntArray(int[][][][] mes) {
        createAndProcessMessage(mes);
    }

    private void createAndProcessMessage(Object mes) {
        Message message = new Message(mes);
        processMessage(message);
    }


    private void processMessage(Message message) {
        Message savedMessage = dataProcessor.processData(message);
        if (savedMessage.getFlagToWrite()) {
            tryDecorateAndPrintMessage(savedMessage);
        }
    }

    private void tryDecorateAndPrintMessage(Message message) {
        try {
            decorateAndPrintMessage(message);
        } catch (DecorateException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void decorateAndPrintMessage(Message message) {
        message.setResult(decorator.decorate(message));
        for (Writer writer: listOfWriters) {
            try {
                writer.write(message);
            } catch (LoggerAppendException e) {
                e.printStackTrace();
            }
        }
    }
}

