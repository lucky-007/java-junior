package com.acme.edu;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logger {

    private Decorator decorator;
    private Writer writer;
    private static Object savedObj = null;
    private static int stringsCount = 1;

    public Logger(Decorator decorator, Writer writer) {
        this.decorator = decorator;
        this.writer = writer;
    }

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
                for (int[] row : matrix) {
                    objectNote += "{";
                    for (int element : row) {
                        objectNote += element + ", ";
                    }
                    objectNote = objectNote.substring(0, objectNote.length() - 2) + "}" + System.lineSeparator();
                }
                objectNote += "}";
                break;
            case "[[[[I":
                int[][][][] multimatrix4 = ((int[][][][])mes);
                objectNote = "{" + System.lineSeparator();
                for (int[][][] multimatrix3 : multimatrix4) {
                    objectNote += "{" + System.lineSeparator();
                    for (int[][] multimatrix2 : multimatrix3) {
                        objectNote += "{" + System.lineSeparator();
                        for (int[] multimatrix1 : multimatrix2) {
                            objectNote += "{" + System.lineSeparator();
                            for (int element : multimatrix1) {
                                objectNote += element + ", ";
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
                printSavedObjectAndSaveNewOne(mes);
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
                printSavedObjectAndSaveNewOne(mes);
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
            Pattern p = Pattern.compile("^"+mes+"($| \\(x\\d+\\)$)");
            Matcher m = p.matcher((String)savedObj);
            if(m.matches()) {
                savedObj = mes + " (x" + (++stringsCount) + ")";
            } else {
                printSavedObjectAndSaveNewOne(mes);
                stringsCount = 1;
            }
        } else {
            printSavedObjectAndSaveNewOne(mes);
        }
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes Object
     */
    public void log (Object mes) {
        Message message = new Message(mes);
        decorator.decorate(message);
        writer.write(message);
        printSavedObjectAndSaveNewOne(mes);
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes two-dimensional array of ints
     */
    public static void log (int[][] mes) {
        printSavedObjectAndSaveNewOne(mes);
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes four-dimensional array of ints
     */
    public static void log (int[][][][] mes) {
        printSavedObjectAndSaveNewOne(mes);
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes vararg of Strings
     */
    public static void log (String... mes) {
        for (String element: mes) {
            log(element);
        }
    }

    /**
     * Overloaded method for logging for different types of inputs parameters.
     * @param mes varargs of ints and one-dimensional array of them
     */
    public static void log (int... mes) {
        printSavedObjectAndSaveNewOne(mes);
    }
}