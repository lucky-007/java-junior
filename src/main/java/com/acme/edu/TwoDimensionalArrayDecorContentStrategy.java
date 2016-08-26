package com.acme.edu;

class TwoDimensionalArrayDecorContentStrategy implements DecorContentStrategy {
    @Override
    public String decorateContent(Message message) {
        return "{" + System.lineSeparator() + OneDimensionalArrayProccess((int[][])message.getValue()) + "}";
    }

    private String OneDimensionalArrayProccess(int[][] array2D) {
        OneDimensionalArrayDecorContentStrategy array1DecorContentStrategy = new OneDimensionalArrayDecorContentStrategy();
        String result = "";
        for (int[] array1D : array2D) {
            result += array1DecorContentStrategy.decorateContent(new Message(array1D));
            result += System.lineSeparator();
        }
        return result;
    }
}
