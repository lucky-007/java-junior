package com.acme.edu;

import com.acme.edu.interfaces.DataProcessor;
import com.acme.edu.interfaces.Decorator;
import com.acme.edu.interfaces.Writer;

public class Logger {

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

    private final Decorator decorator;
    private final DataProcessor dataProcessor;
    private final Writer[] listOfWriters;


    private void decorateAndPrintMessage(Message message) {
        message.setResult(decorator.decorate(message));
        for (Writer writer: listOfWriters) {
            writer.write(message);
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

