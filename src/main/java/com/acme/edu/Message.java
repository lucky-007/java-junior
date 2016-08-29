package com.acme.edu;

public class Message {
    private String type;
    private Object value;
    private String result;
    private boolean flagToWrite;

    public Message(Object messsage) {
        this.type = messsage.getClass().getName();
        this.value = messsage;
        this.flagToWrite = false;
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

    public boolean getFlagToWrite() {
        return flagToWrite;
    }

    public void setFlagToWrite(boolean flagToWrite) {
        this.flagToWrite = flagToWrite;
    }
}
