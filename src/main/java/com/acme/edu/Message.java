package com.acme.edu;

/**
 * Created by daniil on 25.08.16.
 */
public class Message {
    private String type;
    private Object value;
    private String output;

    public Message(Object mes) {
        type = mes.getClass().getName();
        value = mes;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
}
