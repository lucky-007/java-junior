package com.acme.edu;

class ThreeDimensionalArrayDecorContentStrategy implements DecorContentStrategy {
    @Override
    public String decorate(Message message) {
        return "{" + System.lineSeparator() + TwoDimensionalArrayProccess((int[][][])message.getValue()) + "}";
    }

    private String TwoDimensionalArrayProccess(int[][][] array3D) {
        TwoDimensionalArrayDecorContentStrategy array2DecorContentStrategy = new TwoDimensionalArrayDecorContentStrategy();
        String result = "";
        for (int[][] array2D : array3D) {
            result += array2DecorContentStrategy.decorate(new Message(array2D));
            result += System.lineSeparator();
        }
        return result;
    }
}
