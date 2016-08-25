package com.acme.edu;

public class ConsoleDecorator implements Decorator {
    /**
     * Decorate output string with specification of customer.
     * Uses {@link Message}.setOutput method to set output string.
     * @param message
     * @see Message
     */
    @Override
    public void decorate(Message message) {
        message.setOutput(getPrefix(message) +
                chooseDecorStrategy(message).decorate(message) +
                getPostfix(message)
        );
    }

    private String getPostfix(Message message) {
        return "";
    }

    private DecorContentStrategy chooseDecorStrategy(Message message) {
        DecorContentStrategy decorContentStrategy = null;
        switch (message.getType()) {
            case "java.lang.Boolean":
            case "java.lang.Byte":
            case "java.lang.Integer":
            case "java.lang.Character":
            case "java.lang.String":
            case "java.lang.Object":
                decorContentStrategy = new ToStringDecorContentStrategy();
                break;
            case "[I":
                decorContentStrategy = new ArrayOneDimensionalDecorContentStrategy();
                break;
            case "[[I":
                decorContentStrategy = new ArrayTwoDimensionalDecorContentStrategy();
                break;
            case "[[[[I":
                decorContentStrategy = new ArrayFourDimensionalDecorContentStrategy();
                break;
        }
        return decorContentStrategy;
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
