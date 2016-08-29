package com.acme.edu.strategies;

import com.acme.edu.message.Message;
import com.acme.edu.interfaces.DecorContentStrategy;

public class OneDimensionalArrayDecorContentStrategy implements DecorContentStrategy {
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
