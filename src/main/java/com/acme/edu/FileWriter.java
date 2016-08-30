package com.acme.edu;

import com.acme.edu.exceptions.LoggerAppendException;
import com.acme.edu.interfaces.Writer;
import com.acme.edu.message.Message;

import java.io.*;

public class FileWriter implements Writer {
    private File filePath;

    public FileWriter(File path) throws LoggerAppendException {
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

        filePath = new File(path, "log.txt");
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
        Writer writer = new FileWriter(new File(new File("logs"), "logs.txt"));
        Message m = new Message(5);
        m.setResult("12341234 => 5");
        writer.write(m);



    }
}
