package com.acme.edu;

class Message {
    private String type;
    private Object value;
    private String output;

    Message(Object messsage) {
        type = messsage.getClass().getName();
        value = messsage;
    }

    String getOutput() {
        return output;
    }

    void setOutput(String output) {
        this.output = output;
    }

    String getType() {
        return type;
    }

    Object getValue() {
        return value;
    }

    void setValue(Object value) {
        this.value = value;
    }
}
