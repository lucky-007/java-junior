package com.acme.edu.unittest01;

import com.acme.edu.RemoteWriter;
import com.acme.edu.Server;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.exceptions.LoggerAppendException;
import com.acme.edu.message.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RemoteWriterClassTest implements SysoutCaptureAndAssertionAbility {
    private RemoteWriter remoteWriter;
    private Server server;
    private Message message;

    @Before
    public void setUp() {
        server = new Server();
        try {
            remoteWriter = new RemoteWriter();
        } catch (LoggerAppendException e) {
            e.printStackTrace();
        }
        message = mock(Message.class);
    }

    @After
    public void clear () {
    }

    private void flushAndClose () {
        remoteWriter.flush();
        remoteWriter.close();
    }

    @Ignore
    @Test
    public void shouldLogToConsoleThroughSocket () {
        String toWrite = "primitive: 5";
        when(message.getResult()).thenReturn(toWrite);
        try {
            remoteWriter.write(message);
        } catch (LoggerAppendException e) {
            e.printStackTrace();
        }
        flushAndClose();
        assertSysoutContains(toWrite);
    }
}
