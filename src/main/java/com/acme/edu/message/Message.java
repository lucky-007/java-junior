package com.acme.edu.message;

/**
 * class-container of input values.
 */
public class Message {
    private String type;
    private Object value;
    private String result;
    private boolean flagToWrite;

    /**
     * Constructor.
     * @param message object of input logging value
     */
    public Message(Object message) {
        this.type = message.getClass().getName();
        this.value = message;
        this.flagToWrite = false;
    }

    /**
     * getter of result string
     * @return string
     */
    public String getResult() {
        return result;
    }

    /**
     * Equals method using type and value fields
     * @param o input object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Message) {
            Message message = (Message) o;
            return type.equals(message.type) && value.equals(message.value);
        }
        return false;
    }

    /**
     * HashCode method using type and value fields
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = type.hashCode();
        hash = 31 * hash + value.hashCode();
        return hash;
    }

    /**
     * setter of result string
     * @param output string
     */
    public void setResult(String output) {
        this.result = output;
    }

    /**
     * getter of a type of value
     * @return string
     */
    public String getType() {
        return type;
    }

    /**
     * getter of a value
     * @return object
     */
    public Object getValue() {
        return value;
    }

    /**
     * setter of a value
     * @param value Object
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * getter of a flag-to-write
     * @return boolean
     */
    public boolean getFlagToWrite() {
        return flagToWrite;
    }

    /**
     * setter of a flag-to-write
     * @param flagToWrite boolean
     */
    public void setFlagToWrite(boolean flagToWrite) {
        this.flagToWrite = flagToWrite;
    }
}
