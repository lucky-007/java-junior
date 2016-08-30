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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileWriterClassTest {
    private Writer writer;
    private Message message;
    private File path = new File(new File("logs"), "log.txt");

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
    public void shouldCreateAndWriteToFile () throws LoggerAppendException, IOException {
        when(message.getResult()).thenReturn("primitive: 5");
        writer.write(message);

        List list = FileUtils.readLines(path.getAbsoluteFile(), "UTF-8");
        assertEquals("primitive: 5", list.get(0));
    }
}
