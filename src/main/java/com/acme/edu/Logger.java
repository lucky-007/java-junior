package com.acme.edu;

import java.util.Objects;

public class Logger {
    private static Object savedObj = null;

    private static void printSavedObject(Object mes) {
        if(savedObj != null) {
            writer(decor(savedObj) + savedObj);
        }
        savedObj = mes;
    }

    private static void writer (String message) {
        System.out.println(message);
    }

    private static String decor(Object newObject) {
        String className = newObject.getClass().getName();
        String dec = "";
        switch (className) {
            case "java.lang.Boolean":
            case "java.lang.Byte":
            case "java.lang.Integer":
                dec = "primitive: ";
                break;
            case "java.lang.Character":
                dec = "char: ";
                break;
            case "java.lang.String":
                dec = "string: ";
                break;
            case "java.lang.Object":
                dec ="reference: ";
                break;
        }
        return dec;
    }

    private static boolean checkWithPrevMessage(Object newObject) {
        return savedObj != null && Objects.equals(newObject.getClass().getName(), savedObj.getClass().getName());
    }

    /**
     * Terminates the process of logging
     * and adds the result of last logging request.
     */
    public static void stopLogging() {
        printSavedObject(null);
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes boolean
     */
    public static void log (boolean mes) {
        printSavedObject(mes);
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes byte
     */
    public static void log (byte mes) {
        if (checkWithPrevMessage(mes)) {
            if(Byte.MAX_VALUE - (byte) savedObj < mes) {
                writer(decor(savedObj) + (byte) savedObj);
                savedObj = Byte.MAX_VALUE;
            } else {
                savedObj = (byte)((byte)savedObj + mes);
            }
        } else {
            printSavedObject(mes);
        }
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes integer
     */
    public static void log (int mes) {
        if (checkWithPrevMessage(mes)) {
            if(Integer.MAX_VALUE - (int) savedObj < mes) {
                writer(decor(savedObj) + savedObj);
                savedObj = Integer.MAX_VALUE;
            } else {
                savedObj = (int) savedObj + mes;
            }
        } else {
            printSavedObject(mes);
        }
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes char
     */
    public static void log (char mes) {
        printSavedObject(mes);
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes String
     */
    public static void log (String mes) {
        printSavedObject(mes);
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes Object
     */
    public static void log (Object mes) {
        printSavedObject(mes);
    }
}