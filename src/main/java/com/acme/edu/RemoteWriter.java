package com.acme.edu;

import com.acme.edu.exceptions.LoggerAppendException;
import com.acme.edu.interfaces.Writer;
import com.acme.edu.message.Message;

import java.io.*;
import java.net.Socket;

public class RemoteWriter implements Writer{
    private PrintWriter out;

    public RemoteWriter() throws LoggerAppendException {
        try {
            out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    new BufferedOutputStream(
                                            new Socket("localhost", 1010).getOutputStream())),
                            32768),
                    true);
        } catch (IOException e) {
            throw new LoggerAppendException("Can't create RemoteWriter", e);
        }
    }

    @Override
    public void write(Message message) throws LoggerAppendException {
        out.println(message.getResult());
    }

    @Override
    public void flush() {
        out.flush();
    }

    @Override
    public void close() {
        out.close();
    }

    public static void main(String[] args) {
        try {
            RemoteWriter remoteWriter = new RemoteWriter();
            Message message = new Message(23);
            message.setResult("blahblahblah");
            remoteWriter.write(message);
        } catch (LoggerAppendException e) {
            e.printStackTrace();
        }
    }
}
