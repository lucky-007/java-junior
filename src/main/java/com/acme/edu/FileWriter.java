package com.acme.edu;

import com.acme.edu.exceptions.LoggerAppendException;
import com.acme.edu.interfaces.Writer;
import com.acme.edu.message.Message;

import java.io.*;

import static java.lang.Thread.sleep;

public class FileWriter implements Writer {
    private File filePath;

    public FileWriter(File path) throws LoggerAppendException {
        String filename = path.getName();
        path = path.getAbsoluteFile();
        if(!path.isDirectory()) {
            path = path.getParentFile();
        }

        if (!path.exists()) {
            boolean created = path.mkdirs();
            if(!created) {
                throw new LoggerAppendException("Logger directory wasn't created");
            }
        }

        filePath = new File(path, filename);
    }

    @Override
    public void write(Message message) throws LoggerAppendException {
        try (PrintWriter out = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                            new BufferedOutputStream(
                                    new FileOutputStream(filePath, true)), "UTF-8")), true)) {
            out.println(message.getResult());
        } catch (FileNotFoundException e) {
            throw new LoggerAppendException("Troubles whilst writing to file", e);
        } catch (UnsupportedEncodingException e) {
            throw new LoggerAppendException("Encoding exception", e);
        }
    }

    public static void main(String[] args) throws LoggerAppendException {
        Writer writer = new FileWriter(new File(new File("logs"), "log.txt"));
        Message m = new Message(5);
        for(int i=0; i<10000; i++) {
            m.setResult("primitive message that need to overflow the threshold " + i);
            writer.write(m);
            if(i%1000 == 0) {
                System.out.println(" printing "+ i);
            }
        }
        System.out.println("done");
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        writer.write(m);
    }
}
