package com.acme.edu;

class TwoDimensionalArrayDecorContentStrategy implements DecorContentStrategy {
    @Override
    public String decorate(Message message) {
        return "{" + System.lineSeparator() + OneDimensionalArrayProccess((int[][])message.getValue()) + "}";
    }

    private String OneDimensionalArrayProccess(int[][] array2D) {
        OneDimensionalArrayDecorContentStrategy array1DecorContentStrategy = new OneDimensionalArrayDecorContentStrategy();
        String result = "";
        for (int[] array1D : array2D) {
            result += array1DecorContentStrategy.decorate(new Message(array1D));
            result += System.lineSeparator();
        }
        return result;
    }
}
