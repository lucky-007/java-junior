package com.acme.edu.strategies;

import com.acme.edu.message.Message;
import com.acme.edu.interfaces.DecorContentStrategy;

/**
 * 1-dim array strategy
 */
public class OneDimensionalArrayDecorContentStrategy implements DecorContentStrategy {
    /**
     * 1-dim arr decoration
     * @param message input message
     * @return decor string
     */
    @Override
    public String decorateContent(Message message) {
        String content = "{";
        int[] array1D = (int[])message.getValue();
        for (int element : array1D) {
            content += element + ", ";
        }
        return content.substring(0, content.length() - 2) + "}";
    }
}
