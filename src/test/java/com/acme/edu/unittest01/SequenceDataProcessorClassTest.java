package com.acme.edu.unittest01;

import com.acme.edu.SequenceDataProcessor;
import com.acme.edu.message.Message;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SequenceDataProcessorClassTest {
    private SequenceDataProcessor  sequenceDataProcessor = new SequenceDataProcessor();
    private Message mesMock, resultMessage;

    @Before
    public void setUp() {
    }

    @Test
    public void shouldReturnMessageWithFalseFlag() {
//        when(mesMock.getType()).thenReturn("java.lang.Byte");
//        when(mesMock.getValue()).thenReturn((byte)4);
        resultMessage = sequenceDataProcessor.processData(new Message((byte)4));

//        resultMessage = sequenceDataProcessor.processData(mesMock);
        assertEquals(resultMessage.getFlagToWrite(), false);
    }

    @Test
    public void shouldReturnNullMessage() {
        resultMessage = sequenceDataProcessor.processData(null);
        assertEquals(resultMessage, null);
    }

    @Test
    public void shouldReturnPreviousMessageWithFlagTrue() {
        Message initMessage = new Message((byte)4);
        resultMessage = sequenceDataProcessor.processData(initMessage);
        resultMessage = sequenceDataProcessor.processData(null);

        assertEquals((resultMessage.equals(initMessage) && (resultMessage.getFlagToWrite())), true);
    }

    @Test
    public void shouldReturnNullAfterTwoNullsMessages() {
        resultMessage = sequenceDataProcessor.processData(new Message(4));
        resultMessage = sequenceDataProcessor.processData(null);
        resultMessage = sequenceDataProcessor.processData(null);

        assertEquals(resultMessage, null);
    }

    @Test
    public void shouldReturnSumOfInts() {
        resultMessage = sequenceDataProcessor.processData(new Message(4));
        resultMessage = sequenceDataProcessor.processData(new Message(4));
        resultMessage = sequenceDataProcessor.processData(null);

        assertEquals((new Message(8)).equals(resultMessage), true);
    }

    @Test
    public void shouldReturnSumOfBytes() {
        resultMessage = sequenceDataProcessor.processData(new Message((byte)4));
        resultMessage = sequenceDataProcessor.processData(new Message((byte)4));
        resultMessage = sequenceDataProcessor.processData(null);

        assertEquals((new Message((byte)8)).equals(resultMessage), true);
    }

    @Test
    public void shouldReturnStringAfterInt () {
        Message initMessage = new Message("asd");
        resultMessage = sequenceDataProcessor.processData(new Message(4));
        resultMessage = sequenceDataProcessor.processData(initMessage);
        resultMessage = sequenceDataProcessor.processData(null);

        assertEquals(initMessage.equals(resultMessage), true);
    }

    @Test
    public void shouldIncrementCounterOfStrings () {
        resultMessage = sequenceDataProcessor.processData(new Message("asd"));
        resultMessage = sequenceDataProcessor.processData(new Message("asd"));
        resultMessage = sequenceDataProcessor.processData(new Message("asd"));
        resultMessage = sequenceDataProcessor.processData(null);

        assertEquals(new Message("asd (x3)").equals(resultMessage), true);
    }

    @Ignore
    public void shouldReturnPreviousResultIfOverflowByte () {}

    @Ignore
    public void shouldReturnPreviousResultIfOverflowInteger () {}

    @Test
    public void shouldIncrementCounterOfSequentSeriesOfStrings () {
        resultMessage = sequenceDataProcessor.processData(new Message("asd"));
        resultMessage = sequenceDataProcessor.processData(new Message("asd"));
        resultMessage = sequenceDataProcessor.processData(new Message("asd"));
        resultMessage = sequenceDataProcessor.processData(null);
        resultMessage = sequenceDataProcessor.processData(new Message("asd"));
        resultMessage = sequenceDataProcessor.processData(new Message("asd"));
        resultMessage = sequenceDataProcessor.processData(null);

        assertEquals(new Message("asd (x2)").equals(resultMessage), true);
    }
}
