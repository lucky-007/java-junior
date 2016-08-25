package com.acme.edu;

class Array2DecorContentStrategy implements DecorContentStrategy {
    @Override
    public String decorate(Message message) {
        String content;
        int[][] matrix = ((int[][])message.getValue());
        content = "{" + System.lineSeparator();
        for (int[] row : matrix) {
            content += "{";
            for (int element : row) {
                content += element + ", ";
            }
            content = content.substring(0, content.length() - 2) + "}" + System.lineSeparator();
        }
        content += "}";
        return content;
    }
}
