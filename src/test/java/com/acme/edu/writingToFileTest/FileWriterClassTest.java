package com.acme.edu.writingToFileTest;

import com.acme.edu.FileWriter;
import com.acme.edu.exceptions.LoggerAppendException;
import com.acme.edu.interfaces.Writer;
import com.acme.edu.message.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileWriterClassTest {
    private Writer writer;
    private Message message;
    private File path = new File(new File("logs"), "log.txt");

    @Before
    public void setUp() {
        try {
            writer = new FileWriter(path);
        } catch (LoggerAppendException e) {
            e.printStackTrace();
        }

        message = mock(Message.class);
        path.delete();
    }

    @After
    public void clear () {
        path.delete();
    }

    @Test
    public void shouldCreateAndWriteToFile () throws LoggerAppendException {
        when(message.getResult()).thenReturn("primitive: 5");
        writer.write(message);


    }
}
