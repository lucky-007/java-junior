package com.acme.edu;

import com.acme.edu.exceptions.ContentDecorateException;
import com.acme.edu.exceptions.DecorateException;
import com.acme.edu.interfaces.DecorContentStrategy;
import com.acme.edu.interfaces.Decorator;
import com.acme.edu.strategies.FourDimensionalArrayDecorContentStrategy;
import com.acme.edu.strategies.OneDimensionalArrayDecorContentStrategy;
import com.acme.edu.strategies.ToStringDecorContentStrategy;
import com.acme.edu.strategies.TwoDimensionalArrayDecorContentStrategy;

public class ConsoleDecorator implements Decorator {
    /**
     * Decorate output string with specification of customer.
     * Uses {@link Message}.setOutput method to set output string.
     * @param message
     * @see Message
     */
    @Override
    public String decorate(Message message) throws DecorateException {
        try {
            return  getPrefix(message) + getContent(message) + getPostfix(message);
        } catch (ContentDecorateException e) {
            throw new DecorateException("Can\'t decorate message", e);
        }
    }

    private String getPostfix(Message message) {
        return "";
    }

    private String getContent(Message message) throws ContentDecorateException {
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
                decorContentStrategy = new OneDimensionalArrayDecorContentStrategy();
                break;
            case "[[I":
                decorContentStrategy = new TwoDimensionalArrayDecorContentStrategy();
                break;
            case "[[[[I":
                decorContentStrategy = new FourDimensionalArrayDecorContentStrategy();
                break;
            default:
                throw new ContentDecorateException("Wrong input type.");
        }
        return decorContentStrategy.decorateContent(message);
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
