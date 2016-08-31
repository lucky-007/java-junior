package com.acme.edu;

import com.acme.edu.exceptions.LoggerAppendException;
import com.acme.edu.interfaces.Writer;
import com.acme.edu.message.Message;

import java.io.*;

import static java.lang.Thread.sleep;

public class FileWriter implements Writer {
    private PrintWriter out;

    public FileWriter(File path) throws LoggerAppendException {
        path = path.getAbsoluteFile();

        if (!path.getParentFile().exists()) {
            boolean created = path.getParentFile().mkdirs();
            if(!created) {
                throw new LoggerAppendException("FileWriter directory wasn't created");
            }
        }

        try {
            out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    new BufferedOutputStream(
                                            new FileOutputStream(path, true))),
                            32768),
                    true);
        } catch (FileNotFoundException e) {
            throw new LoggerAppendException("Can't create FileWriter", e);
        }
    }

    @Override
    public void write(Message message) {
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
}
