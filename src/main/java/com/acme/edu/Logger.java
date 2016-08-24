package com.acme.edu;

import java.util.Objects;

public class Logger {
    private static Object prevObj = null;

    private static void writer (String message) {
        System.out.println(message);
    }

    private static boolean checkWithPrevMessage(Object newObject) {
        if(prevObj == null) return false;
        return Objects.equals(newObject.getClass().getName(), prevObj.getClass().getName());
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

    public static void stopLogging() {
        if(prevObj != null) {
            writer(decor(prevObj) + prevObj);
        }
        prevObj = null;
    }

    public static void log (boolean mes) {
        if(prevObj != null) {
            writer(decor(prevObj) + prevObj);
        }
        prevObj = mes;
    }

    public static void log (byte mes) {
        if (checkWithPrevMessage(mes)) {
            if(Byte.MAX_VALUE - (byte)prevObj < mes) {
                writer(decor(prevObj) + (byte)prevObj);
                prevObj = Byte.MAX_VALUE;
            } else {
                prevObj = (byte)prevObj + mes;
            }
        } else {
            if(prevObj != null) {
                writer(decor(prevObj) + prevObj);
            }
            prevObj = mes;
        }
    }
    public static void log (int mes) {
        if (checkWithPrevMessage(mes)) {
            if(Integer.MAX_VALUE - (int)prevObj < mes) {
                writer(decor(prevObj) + prevObj);
                prevObj = Integer.MAX_VALUE;
            } else {
                prevObj = (int)prevObj + mes;
            }
        } else {
            if(prevObj != null) {
                writer(decor(prevObj) + prevObj);
            }
            prevObj = mes;
        }
    }
    public static void log (char mes) {
        if(prevObj != null) {
            writer(decor(prevObj) + prevObj);
        }
        prevObj = mes;
    }
    public static void log (String mes) {
        if(prevObj != null) {
            writer(decor(prevObj) + prevObj);
        }
        prevObj = mes;
    }
    public static void log (Object mes) {
        if(prevObj != null) {
            writer(decor(prevObj) + prevObj);
        }
        prevObj = mes;
    }
}

