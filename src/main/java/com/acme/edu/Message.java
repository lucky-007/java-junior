package com.acme.edu;

public class Message {
    private String type;
    private Object value;
    private String result;

    public Message(Object messsage) {
        type = messsage.getClass().getName();
        value = messsage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String output) {
        this.result = output;
    }

    public String getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
