package com.acme.edu;

class Array1DecorContentStrategy implements DecorContentStrategy{
    @Override
    public String decorate(Message message) {
        String content;
        int[] array = ((int[])message.getValue());
        content = "{";
        for (int element : array) {
            content += element + ", ";
        }
        return content.substring(0, content.length() - 2) + "}";
    }
}
