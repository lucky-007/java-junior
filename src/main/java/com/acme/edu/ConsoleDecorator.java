package com.acme.edu;

import com.acme.edu.exceptions.ContentDecorateException;
import com.acme.edu.exceptions.DecorateException;
import com.acme.edu.interfaces.DecorContentStrategy;
import com.acme.edu.interfaces.Decorator;
import com.acme.edu.message.Message;
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
    public String decorate(Message message) {
        try {
            return  getContent(message) + getPostfix(message);
        } catch (ContentDecorateException e) {
            throw new DecorateException("Can\'t decorate message", e);
        }
    }

    private String getPostfix(Message message) {
        return "";
    }

    private String getContent(Message message) throws ContentDecorateException {
        String result = "";
        DecorContentStrategy decorContentStrategy = null;
        switch (message.getType()) {
            case "java.lang.Boolean":
            case "java.lang.Byte":
            case "java.lang.Integer":
                decorContentStrategy = new ToStringDecorContentStrategy();
                result = "primitive: " + decorContentStrategy.decorateContent(message);
                break;
            case "java.lang.Character":
                decorContentStrategy = new ToStringDecorContentStrategy();
                result = "char: " + decorContentStrategy.decorateContent(message);
                break;
            case "java.lang.String":
                decorContentStrategy = new ToStringDecorContentStrategy();
                result = "string: " + decorContentStrategy.decorateContent(message);
                break;
            case "java.lang.Object":
                decorContentStrategy = new ToStringDecorContentStrategy();
                result = "reference: " + decorContentStrategy.decorateContent(message);
                break;
            case "[I":
                decorContentStrategy = new OneDimensionalArrayDecorContentStrategy();
                result = "primitives array: " + decorContentStrategy.decorateContent(message);
                break;
            case "[[I":
                decorContentStrategy = new TwoDimensionalArrayDecorContentStrategy();
                result = "primitives matrix: " + decorContentStrategy.decorateContent(message);
                break;
            case "[[[[I":
                decorContentStrategy = new FourDimensionalArrayDecorContentStrategy();
                result = "primitives multimatrix: " + decorContentStrategy.decorateContent(message);
                break;
            default:
                throw new ContentDecorateException("Wrong input type.");
        }
        return result;
    }
}
