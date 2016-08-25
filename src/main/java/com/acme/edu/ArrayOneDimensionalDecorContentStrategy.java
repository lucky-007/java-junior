package com.acme.edu;

class ArrayOneDimensionalDecorContentStrategy implements DecorContentStrategy{
    @Override
    public String decorate(Message message) {
        String content;
        int[] array1D = ((int[])message.getValue());
        content = "{";
        for (int element : array1D) {
            content += element + ", ";
        }
        return content.substring(0, content.length() - 2) + "}";
    }
}
