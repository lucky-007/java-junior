package com.acme.edu;

/**
 * Created by daniil on 25.08.16.
 */
public class ConsoleDecorator implements Decorator {
    @Override
    public void decorate(Message message) {
        message.setOutput(getPrefix(message) + decorateContent(message) + getPostfix(message));
    }

    private String getPostfix(Message message) {
        return "";
    }

    private String decorateContent(Message message) {
        String content = "";
        switch (message.getType()) {
            case "java.lang.Boolean":
            case "java.lang.Byte":
            case "java.lang.Integer":
            case "java.lang.Character":
            case "java.lang.String":
            case "java.lang.Object":
                content = message.getValue().toString();
                break;
            case "[I":
                int[] array = ((int[])message.getValue());
                content = "{";
                for (int element : array) {
                    content += element + ", ";
                }
                content = content.substring(0, content.length() - 2) + "}";
                break;
            case "[[I":
                int[][] matrix = ((int[][])message.getValue());
                content = "{" + System.lineSeparator();
                for (int[] row : matrix) {
                    content += "{";
                    for (int element : row) {
                        content += element + ", ";
                    }
                    content = content.substring(0, content.length() - 2) + "}" + System.lineSeparator();
                }
                content += "}";
                break;
            case "[[[[I":
                int[][][][] multimatrix4 = ((int[][][][])message.getValue());
                content = "{" + System.lineSeparator();
                for (int[][][] multimatrix3 : multimatrix4) {
                    content += "{" + System.lineSeparator();
                    for (int[][] multimatrix2 : multimatrix3) {
                        content += "{" + System.lineSeparator();
                        for (int[] multimatrix1 : multimatrix2) {
                            content += "{" + System.lineSeparator();
                            for (int element : multimatrix1) {
                                content += element + ", ";
                            }
                            content = content.substring(0, content.length() - 2) + System.lineSeparator() + "}" + System.lineSeparator();
                        }
                        content += "}" + System.lineSeparator();
                    }
                    content += "}" + System.lineSeparator();
                }
                content += "}";
                break;
        }
        return content;
    }

    private String getPrefix(Message message) {
        String prefix = "";
        switch (message.getType()) {
            case "java.lang.Boolean":
            case "java.lang.Byte":
            case "java.lang.Integer":
                prefix = "primitive: ";
                break;
            case "[I":
                prefix = "primitives array: ";
                break;
            case "[[I":
                prefix = "primitives matrix: ";
                break;
            case "[[[[I":
                prefix = "primitives multimatrix: ";
                break;
            case "java.lang.Character":
                prefix = "char: ";
                break;
            case "java.lang.String":
                prefix = "string: ";
                break;
            case "java.lang.Object":
                prefix ="reference: ";
                break;
        }
        return prefix;
    }
}
