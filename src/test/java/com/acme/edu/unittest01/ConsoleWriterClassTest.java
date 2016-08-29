package com.acme.edu.unittest01;

import com.acme.edu.ConsoleWriter;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.exceptions.LoggerAppendException;
import com.acme.edu.message.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConsoleWriterClassTest implements SysoutCaptureAndAssertionAbility {
    private ConsoleWriter consoleWriter;
    private Message messageMock;

    @Before
    public void setUp() throws IOException {
        resetOut();
        captureSysout();
        consoleWriter = new ConsoleWriter();
        messageMock = mock(Message.class);
    }

    @After
    public void tearDown() {
        resetOut();
    }

    @Test
    public void shouldWriteMessageInToConsole() throws LoggerAppendException {
        //region Given
        when(messageMock.getResult()).thenReturn("Hello, world");
        //endregion

        //region When
        consoleWriter.write(messageMock);
        //endregion

        //region Then
        assertSysoutContains("Hello, world");
        //endregion
    }

    @Test(expected = LoggerAppendException.class)
    public void shouldThrowLoggerAppendException() throws LoggerAppendException {
        //region Given
        when(messageMock.getResult()).thenThrow(LoggerAppendException.class);
        //endregion

        //region When
        consoleWriter.write(messageMock);
        //endregion
    }
}
