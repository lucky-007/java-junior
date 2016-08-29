package com.acme.edu.strategies;

import com.acme.edu.message.Message;
import com.acme.edu.interfaces.DecorContentStrategy;

public class TwoDimensionalArrayDecorContentStrategy implements DecorContentStrategy {
    @Override
    public String decorateContent(Message message) {
        return "{" + System.lineSeparator() + OneDimensionalArrayProcess((int[][])message.getValue()) + "}";
    }

    private String OneDimensionalArrayProcess(int[][] array2D) {
        OneDimensionalArrayDecorContentStrategy array1DecorContentStrategy = new OneDimensionalArrayDecorContentStrategy();
        String result = "";
        for (int[] array1D : array2D) {
            result += array1DecorContentStrategy.decorateContent(new Message(array1D));
            result += System.lineSeparator();
        }
        return result;
    }
}
