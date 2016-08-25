package com.acme.edu;

class Array4DecorContentStrategy implements DecorContentStrategy {
    @Override
    public String decorate(Message message) {
        String content;
        int[][][][] multimatrix4 = ((int[][][][])message.getValue());
        content = "{" + System.lineSeparator();
        for (int[][][] multimatrix3 : multimatrix4) {
            content += "{" + System.lineSeparator();
            for (int[][] multimatrix2 : multimatrix3) {
                content += "{" + System.lineSeparator();
                for (int[] multimatrix1 : multimatrix2) {
                    content += "{" + System.lineSeparator();
                    for (int element : multimatrix1) {
                        content += element + ", ";
                    }
                    content = content.substring(0, content.length() - 2) + System.lineSeparator() + "}" + System.lineSeparator();
                }
                content += "}" + System.lineSeparator();
            }
            content += "}" + System.lineSeparator();
        }
        content += "}";
        return content;
    }
}
