package com.acme.edu.strategies;

import com.acme.edu.message.Message;
import com.acme.edu.interfaces.DecorContentStrategy;

public class FourDimensionalArrayDecorContentStrategy implements DecorContentStrategy {
    @Override
    public String decorateContent(Message message) {
        return "{" + System.lineSeparator() + ThreeDimensionalArrayProcess((int[][][][])message.getValue()) + "}";
    }

    private String ThreeDimensionalArrayProcess(int[][][][] array4D) {
        ThreeDimensionalArrayDecorContentStrategy array3DecorContentStrategy = new ThreeDimensionalArrayDecorContentStrategy();
        String result = "";
        for (int[][][] array3D : array4D) {
            result += array3DecorContentStrategy.decorateContent(new Message(array3D));
            result += System.lineSeparator();
        }
        return result;
    }
}
