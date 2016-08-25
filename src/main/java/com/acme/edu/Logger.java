package com.acme.edu;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logger {

    private Decorator decorator;
    private Writer writer;
    private Message savedMessage = null;
    private int stringsCount = 1;

    public Logger(Decorator decorator, Writer writer) {
        this.decorator = decorator;
        this.writer = writer;
    }
//todo just save
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

//    public static void log (byte mes) {
//        if (checkWithPrevMessage(mes)) {
//            if(Byte.MAX_VALUE - (byte) savedMessage < mes) {
//                printSavedObjectAndSaveNewOne(mes);
//            } else {
//                savedMessage = (byte)((byte) savedMessage + mes);
//            }
//        } else {
//            printSavedObjectAndSaveNewOne(mes);
//        }
//    }
//
//    /**
//     * Overloaded method for logging for different types of inputs parameters.
//     * @param mes integer
//     */
//    public static void log (int mes) {
//        if (checkWithPrevMessage(mes)) {
//            if(Integer.MAX_VALUE - (int) savedMessage < mes) {
//                printSavedObjectAndSaveNewOne(mes);
//            } else {
//                savedMessage = (int) savedMessage + mes;
//            }
//        } else {
//            printSavedObjectAndSaveNewOne(mes);
//        }
//    }
//
//    /**
//     * Overloaded method for logging for different types of inputs parameters.
//     * @param mes char
//     */
//    public static void log (char mes) {
//        printSavedObjectAndSaveNewOne(mes);
//    }
//
//    /**
//     * Overloaded method for logging for different types of inputs parameters.
//     * @param mes String
//     */
//    public static void log (String mes) {
//        if(checkWithPrevMessage(mes)) {
//            Pattern p = Pattern.compile("^"+mes+"($| \\(x\\d+\\)$)");
//            Matcher m = p.matcher((String) savedMessage);
//            if(m.matches()) {
//                savedMessage = mes + " (x" + (++stringsCount) + ")";
//            } else {
//                printSavedObjectAndSaveNewOne(mes);
//                stringsCount = 1;
//            }
//        } else {
//            printSavedObjectAndSaveNewOne(mes);
//        }
//    }


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

    private void processSequenceData(Message message) {
        switch(message.getType()) {
            case "java.lang.Byte":
                if(Byte.MAX_VALUE - (byte) savedMessage.getValue() < (byte)message.getValue()) {
                    printSavedObjectAndSaveNewOne(message);
                } else {
                    savedMessage.setValue ( (byte)((byte) savedMessage.getValue() + (byte)message.getValue()));
                }
                break;
            case "java.lang.Integer":
                if(Integer.MAX_VALUE - (int) savedMessage.getValue() < (int)message.getValue()) {
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
        }
    }

//    /**
//     * Overloaded method for logging for different types of inputs parameters.
//     * @param mes two-dimensional array of ints
//     */
//    public static void log (int[][] mes) {
//        printSavedObjectAndSaveNewOne(mes);
//    }
//
//    /**
//     * Overloaded method for logging for different types of inputs parameters.
//     * @param mes four-dimensional array of ints
//     */
//    public static void log (int[][][][] mes) {
//        printSavedObjectAndSaveNewOne(mes);
//    }
//
//    /**
//     * Overloaded method for logging for different types of inputs parameters.
//     * @param mes vararg of Strings
//     */
//    public static void log (String... mes) {
//        for (String element: mes) {
//            log(element);
//        }
//    }
//
//    /**
//     * Overloaded method for logging for different types of inputs parameters.
//     * @param mes varargs of ints and one-dimensional array of them
//     */
//    public static void log (int... mes) {
//        printSavedObjectAndSaveNewOne(mes);
//    }
}