package com.acme.edu;

public class Logger {
    public static void log(Object message) {
        switch (message.getClass().getName()) {
            case "java.lang.Boolean":
            case "java.lang.Byte":
            case "java.lang.Integer":
                System.out.println("primitive: " + message);
                break;
            case "java.lang.Character":
                System.out.println("char: " + message);
                break;
            case "java.lang.String":
                System.out.println("string: " + message);
                break;
            case "java.lang.Object":
                System.out.println("reference: " + message);
                break;
        }
    }
}

