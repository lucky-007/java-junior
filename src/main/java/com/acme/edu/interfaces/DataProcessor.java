package com.acme.edu.interfaces;

import com.acme.edu.message.Message;

public interface DataProcessor {

    Message processData(Message message);
    void setMessage(Message message);
}
