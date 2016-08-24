package com.acme.edu;

import java.lang.reflect.Array;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logger {
    private static Object savedObj = null;
    private static int stringsCount = 1;

    private static void printSavedObjectAndSaveNewOne(Object mes) {
        if(savedObj != null) {
            writer(decor(savedObj) + formatObjectNote(savedObj));
        }
        savedObj = mes;
    }

    private static String formatObjectNote(Object mes) {
        String className = mes.getClass().getName();
        String objectNote = "";
        switch (className) {
            case "java.lang.Boolean":
            case "java.lang.Byte":
            case "java.lang.Integer":
            case "java.lang.Character":
            case "java.lang.String":
            case "java.lang.Object":
                objectNote = mes.toString();
                break;
            case "[I":
                int[] array = ((int[])mes);
                objectNote = "{";
                for (int element : array) {
                    objectNote += element + ", ";
                }
                objectNote = objectNote.substring(0, objectNote.length() - 2) + "}";
                break;
            case "[[I":
                int[][] matrix = ((int[][])mes);
                objectNote = "{" + System.lineSeparator();
                for (int[] aMatrix : matrix) {
                    objectNote += "{";
                    for (int anAMatrix : aMatrix) {
                        objectNote += anAMatrix + ", ";
                    }
                    objectNote = objectNote.substring(0, objectNote.length() - 2) + "}" + System.lineSeparator();
                }
                objectNote += "}";
                break;
            case "[[[[I":
                int[][][][] multimatrix = ((int[][][][])mes);
                objectNote = "{" + System.lineSeparator();
                for (int[][][] aMultimatrix : multimatrix) {
                    objectNote += "{" + System.lineSeparator();
                    for (int[][] anAMultimatrix : aMultimatrix) {
                        objectNote += "{" + System.lineSeparator();
                        for (int[] anAnAMultimatrix : anAMultimatrix) {
                            objectNote += "{" + System.lineSeparator();
                            for (int anAnAnAMultimatrix : anAnAMultimatrix) {
                                objectNote += anAnAnAMultimatrix + ", ";
                            }
                            objectNote = objectNote.substring(0, objectNote.length() - 2) + System.lineSeparator() + "}" + System.lineSeparator();
                        }
                        objectNote += "}" + System.lineSeparator();
                    }
                    objectNote += "}" + System.lineSeparator();
                }
                objectNote += "}";
                break;
        }
        return objectNote;
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
            case "[I":
                dec = "primitives array: ";
                break;
            case "[[I":
                dec = "primitives matrix: ";
                break;
            case "[[[[I":
                dec = "primitives multimatrix: ";
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
        printSavedObjectAndSaveNewOne(null);
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes boolean
     */
    public static void log (boolean mes) {
        printSavedObjectAndSaveNewOne(mes);
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes byte
     */
    public static void log (byte mes) {
        if (checkWithPrevMessage(mes)) {
            if(Byte.MAX_VALUE - (byte) savedObj < mes) {
                printSavedObjectAndSaveNewOne(Byte.MAX_VALUE);
            } else {
                savedObj = (byte)((byte)savedObj + mes);
            }
        } else {
            printSavedObjectAndSaveNewOne(mes);
        }
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes integer
     */
    public static void log (int mes) {
        if (checkWithPrevMessage(mes)) {
            if(Integer.MAX_VALUE - (int) savedObj < mes) {
                printSavedObjectAndSaveNewOne(Integer.MAX_VALUE);
            } else {
                savedObj = (int) savedObj + mes;
            }
        } else {
            printSavedObjectAndSaveNewOne(mes);
        }
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes char
     */
    public static void log (char mes) {
        printSavedObjectAndSaveNewOne(mes);
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes String
     */
    public static void log (String mes) {
        if(checkWithPrevMessage(mes)) {
            Pattern p = Pattern.compile("^"+mes);
            Matcher m = p.matcher((String)savedObj);
            if(m.matches()) {
                savedObj = mes + " (x" + (++stringsCount) + ")";
            } else {
                printSavedObjectAndSaveNewOne(mes);
            }
        } else {
            printSavedObjectAndSaveNewOne(mes);
        }
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes Object
     */
    public static void log (Object mes) {
        printSavedObjectAndSaveNewOne(mes);
    }

    public static void log (int[] mes) {
        printSavedObjectAndSaveNewOne(mes);
    }

    public static void log (int[][] mes) {
        printSavedObjectAndSaveNewOne(mes);
    }

    public static void log (int[][][] mes) {
        printSavedObjectAndSaveNewOne(mes);
    }
}