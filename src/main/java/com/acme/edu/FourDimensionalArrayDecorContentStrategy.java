package com.acme.edu;

class FourDimensionalArrayDecorContentStrategy implements DecorContentStrategy {
    @Override
    public String decorateContent(Message message) {
        return "{" + System.lineSeparator() + ThreeDimensionalArrayProccess((int[][][][])message.getValue()) + "}";
    }

    private String ThreeDimensionalArrayProccess(int[][][][] array4D) {
        ThreeDimensionalArrayDecorContentStrategy array3DecorContentStrategy = new ThreeDimensionalArrayDecorContentStrategy();
        String result = "";
        for (int[][][] array3D : array4D) {
            result += array3DecorContentStrategy.decorateContent(new Message(array3D));
            result += System.lineSeparator();
        }
        return result;
    }
}
