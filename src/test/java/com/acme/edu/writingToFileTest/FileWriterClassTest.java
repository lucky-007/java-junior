package com.acme.edu.writingToFileTest;

import com.acme.edu.FileWriter;
import com.acme.edu.exceptions.LoggerAppendException;
import com.acme.edu.interfaces.Writer;
import com.acme.edu.message.Message;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileWriterClassTest {
    private Writer writer;
    private Message message;
    private File path = new File(new File("." + File.separator +  "log"), "log.txt");

    private void deleteFilesAndFolder() {
        if (path.getParentFile() != null) {
            if(path.getParentFile().listFiles() != null) {
                for (File f : path.getParentFile().listFiles()) {
                    f.delete();
                }
            }
            path.getParentFile().delete();
        }
    }

    private void flushAndClose() {
        writer.flush();
        writer.close();
    }

    @Before
    public void setUp() {
        deleteFilesAndFolder();

        try {
            writer = new FileWriter(path);
        } catch (LoggerAppendException e) {
            e.printStackTrace();
        }

        message = mock(Message.class);
    }

    @After
    public void clear () {
        deleteFilesAndFolder();
    }

    @Test
    public void shouldCreateAndWriteToFile () throws LoggerAppendException {
        String toWrite = "primitive: 5";
        when(message.getResult()).thenReturn(toWrite);
        writer.write(message);
        flushAndClose();

        try {
            String file = FileUtils.readFileToString(path);
            assertThat(file).contains(toWrite);
        } catch (IOException e) {
            throw new LoggerAppendException("Can't read file in tests",e);
        }
    }

    @Test
    public void shouldWriteToSameFileWithNewLogger () throws LoggerAppendException {
        String s1 = "primitive: 5";
        String s2 = "primitive: 0";
        when(message.getResult()).thenReturn(s1);
        writer.write(message);
        flushAndClose();

        try {
            writer = new FileWriter(path);
            when(message.getResult()).thenReturn(s2);
            writer.write(message);
        } catch (LoggerAppendException e) {
            e.printStackTrace();
        }

        try {
            String file = FileUtils.readFileToString(path);
            assertThat(file).contains(s1 + System.lineSeparator() + s2);
        } catch (IOException e) {
            throw new LoggerAppendException("Can't read file in tests",e);
        }
        flushAndClose();
    }

//    @Test(expected = LoggerAppendException.class)
//    public void shouldThrowLoggerAppendException () throws LoggerAppendException {
//        String toWrite = "primitive: 5";
//        when(message.getResult()).thenReturn(toWrite);
//        deleteFilesAndFolder();
//        writer.write(message);
//    }
}
