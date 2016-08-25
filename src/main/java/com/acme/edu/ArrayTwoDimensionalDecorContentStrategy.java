package com.acme.edu;

class ArrayTwoDimensionalDecorContentStrategy implements DecorContentStrategy {
    @Override
    public String decorate(Message message) {
        ArrayOneDimensionalDecorContentStrategy array1DecorContentStrategy = new ArrayOneDimensionalDecorContentStrategy();
        String content;
        int[][] array2D = ((int[][])message.getValue());
        content = "{" + System.lineSeparator();
        for (int[] array1D : array2D) {
            content += array1DecorContentStrategy.decorate(new Message(array1D));
            content += System.lineSeparator();
        }
        content += "}";
        return content;
    }
}
