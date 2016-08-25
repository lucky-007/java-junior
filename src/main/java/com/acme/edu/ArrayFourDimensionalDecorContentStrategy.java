package com.acme.edu;

class ArrayFourDimensionalDecorContentStrategy implements DecorContentStrategy {
    @Override
    public String decorate(Message message) {
        ArrayThreeDimensionalDecorContentStrategy array3DecorContentStrategy = new ArrayThreeDimensionalDecorContentStrategy();
        String content;
        int[][][][] array4D = ((int[][][][])message.getValue());
        content = "{" + System.lineSeparator();
        for (int[][][] array3D : array4D) {
            content += array3DecorContentStrategy.decorate(new Message(array3D));
            content += System.lineSeparator();
        }
        content += "}";
        return content;
    }
}
