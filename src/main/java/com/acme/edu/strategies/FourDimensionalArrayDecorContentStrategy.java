package com.acme.edu.strategies;

import com.acme.edu.message.Message;
import com.acme.edu.interfaces.DecorContentStrategy;

/**
 * 4-dim array strategy
 */
public class FourDimensionalArrayDecorContentStrategy implements DecorContentStrategy {
    /**
     * 4-dim arr decoration
     * @param message input message
     * @return decorated string
     */
    @Override
    public String decorateContent(Message message) {
        return "{" + System.lineSeparator() + processThreeDimensionalArray((int[][][][])message.getValue()) + "}";
    }

    private String processThreeDimensionalArray(int[][][][] array4D) {
        ThreeDimensionalArrayDecorContentStrategy array3DecorContentStrategy = new ThreeDimensionalArrayDecorContentStrategy();
        String result = "";
        for (int[][][] array3D : array4D) {
            result += array3DecorContentStrategy.decorateContent(new Message(array3D));
            result += System.lineSeparator();
        }
        return result;
    }
}
