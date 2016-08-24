package com.acme.edu;

public class Logger {
    private static void writer (String message) {
        System.out.println(message);
    }

    public static void log(Object message) {
        switch (message.getClass().getName()) {
            case "java.lang.Boolean":
            case "java.lang.Byte":
            case "java.lang.Integer":
                writer("primitive: " + message);
                break;
            case "java.lang.Character":
                writer("char: " + message);
                break;
            case "java.lang.String":
                writer("string: " + message);
                break;
            case "java.lang.Object":
                writer("reference: " + message);
                break;
        }
    }
}

