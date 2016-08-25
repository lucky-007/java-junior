package com.acme.edu;

class ArrayThreeDimensionalDecorContentStrategy implements DecorContentStrategy {
    @Override
    public String decorate(Message message) {
        ArrayTwoDimensionalDecorContentStrategy array2DecorContentStrategy = new ArrayTwoDimensionalDecorContentStrategy();
        String content;
        int[][][] array3D = ((int[][][])message.getValue());
        content = "{" + System.lineSeparator();
        for (int[][] array2D : array3D) {
            content += array2DecorContentStrategy.decorate(new Message(array2D));
            content += System.lineSeparator();
        }
        content += "}";
        return content;
    }
}
