package com.acme.edu;

import com.acme.edu.exceptions.LoggerAppendException;
import com.acme.edu.interfaces.Writer;
import com.acme.edu.message.Message;

import java.io.*;

import static java.lang.Thread.sleep;

public class FileWriter implements Writer {
    private PrintWriter out;

    public FileWriter(File path) throws LoggerAppendException {
        String filename = path.getName();
        path = path.getAbsoluteFile();
        if(!path.isDirectory()) {
            path = path.getParentFile();
        }

        if (!path.exists()) {
            boolean created = path.mkdirs();
            if(!created) {
                throw new LoggerAppendException("FileWriter directory wasn't created");
            }
        }

        File filePath = new File(path, filename);
        try {
            out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    new BufferedOutputStream(
                                            new FileOutputStream(filePath, true))),
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

//    public static void main(String[] args) throws LoggerAppendException {
//        Writer writer = new FileWriter(new File(new File("logs"), "log.txt"));
//        Message m = new Message(5);
//        for(int i=0; i<100000; i++) {
//            m.setResult("primitive message that need to overflow the threshold " + i);
//            writer.write(m);
//            if(i%1000 == 0) {
//                System.out.println(" printing "+ i);
//                try {
//                    sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        try {
//            sleep(5000);
//            System.out.println("done");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        writer.write(m);
//        writer.flush();
//        try {
//            sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        writer.close();
//    }
}
