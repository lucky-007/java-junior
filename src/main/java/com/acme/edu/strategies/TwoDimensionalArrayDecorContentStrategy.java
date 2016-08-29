package com.acme.edu.strategies;

import com.acme.edu.message.Message;
import com.acme.edu.interfaces.DecorContentStrategy;

public class TwoDimensionalArrayDecorContentStrategy implements DecorContentStrategy {
    /**
     * 2-dimm arr decoration
     * @param message input message
     * @return decorated string
     */
    @Override
    public String decorateContent(Message message) {
        return "{" + System.lineSeparator() + processOneDimensionalArray((int[][])message.getValue()) + "}";
    }

    private String processOneDimensionalArray(int[][] array2D) {
        OneDimensionalArrayDecorContentStrategy array1DecorContentStrategy = new OneDimensionalArrayDecorContentStrategy();
        String result = "";
        for (int[] array1D : array2D) {
            result += array1DecorContentStrategy.decorateContent(new Message(array1D));
            result += System.lineSeparator();
        }
        return result;
    }
}
