package com.acme.edu;

import com.acme.edu.exceptions.ContentDecorateException;
import com.acme.edu.exceptions.DecorateException;
import com.acme.edu.interfaces.DecorContentStrategy;
import com.acme.edu.interfaces.Decorator;
import com.acme.edu.message.Message;
import com.acme.edu.strategies.*;

public class ConsoleDecorator implements Decorator {

    private DecorContentStrategy decorContentStrategy = null;

    /**
     * Decorate output string with specification of customer.
     * Uses {@link Message}.setOutput method to set output string.
     * @param message
     * @see Message
     */
    @Override
    public String decorate(Message message) {
        try {
            return  getContent(message) + decorContentStrategy.decorateContent(message);
        } catch (ContentDecorateException e) {
            throw new DecorateException("Can\'t decorate message", e);
        }
    }

    private String getContent(Message message) throws ContentDecorateException {
        String result;
        switch (message.getType()) {
            case "java.lang.Boolean":
            case "java.lang.Byte":
            case "java.lang.Integer":
                decorContentStrategy = new ToStringDecorContentStrategy();
                result = "primitive: ";
                break;
            case "java.lang.Character":
                decorContentStrategy = new ToStringDecorContentStrategy();
                result = "char: ";
                break;
            case "java.lang.String":
                decorContentStrategy = new ToStringDecorContentStrategy();
                result = "string: ";
                break;
            case "java.lang.Object":
                decorContentStrategy = new ToStringDecorContentStrategy();
                result = "reference: ";
                break;
            case "[I":
                decorContentStrategy = new OneDimensionalArrayDecorContentStrategy();
                result = "primitives array: ";
                break;
            case "[[I":
                decorContentStrategy = new TwoDimensionalArrayDecorContentStrategy();
                result = "primitives matrix: ";
                break;
            case "[[[I":
                decorContentStrategy = new ThreeDimensionalArrayDecorContentStrategy();
                result = "primitives multimatrix: ";
                break;
            case "[[[[I":
                decorContentStrategy = new FourDimensionalArrayDecorContentStrategy();
                result = "primitives multimatrix: ";
                break;
            default:
                throw new ContentDecorateException("Wrong input type.");
        }
        return result;
    }
}
