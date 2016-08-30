package com.acme.edu.unittest01;

import com.acme.edu.message.Message;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MessageClassTest {
    private Message mes1, mes2;

    @Before
    public void setUp() {
    }

    @Test
    public void shouldEqualsTwoLinks() {
        mes1 = new Message(2);
        mes2 = mes1;
        assertEquals(mes1, mes2);
    }

    @Test
    public void shouldEqualsTwoSameMessages () {
        mes1 = new Message("sd");
        mes2 = new Message("sd");
        assertEquals(mes1, mes2);
    }

    @Test
    public void shouldEqualsTwoSameMessagesWithDifferentResultStrings () {
        mes1 = new Message("sd");
        mes2 = new Message("sd");
        mes1.setResult("abc123");
        mes2.setResult("abc");
        assertEquals(mes1, mes2);
    }

    @Test
    public void shouldEqualsTwoSameMessagesWithDifferentFlags () {
        mes1 = new Message("sd");
        mes2 = new Message("sd");
        mes1.setFlagToWrite(false);
        mes2.setFlagToWrite(true);
        assertEquals(mes1, mes2);
    }

    @Test
    public void shouldEqualsHashTwoLinks() {
        mes1 = new Message(2);
        mes2 = mes1;
        assertEquals(mes1.hashCode(), mes2.hashCode());
    }

    @Test
    public void shouldEqualsHashTwoSameMessages () {
        mes1 = new Message("sd");
        mes2 = new Message("sd");
        assertEquals(mes1.hashCode(), mes2.hashCode());
    }

    @Test
    public void shouldEqualsHashTwoSameMessagesWithDifferentResultStrings () {
        mes1 = new Message("sd");
        mes2 = new Message("sd");
        mes1.setResult("abc123");
        mes2.setResult("abc");
        assertEquals(mes1.hashCode(), mes2.hashCode());
    }

    @Test
    public void shouldEqualsHashTwoSameMessagesWithDifferentFlags () {
        mes1 = new Message("sd");
        mes2 = new Message("sd");
        mes1.setFlagToWrite(false);
        mes2.setFlagToWrite(true);
        assertEquals(mes1.hashCode(), mes2.hashCode());
    }

    @Test
    public void shouldNotEqualsWithDifferentTypes () {
        mes1 = new Message(3);
        mes2 = new Message("3");
        assertEquals(mes1.equals(mes2), false);
    }

    @Test
    public void shouldNotEqualsTwoMessages () {
        mes1 = new Message(3);
        mes2 = new Message(4);
        assertEquals(mes1.equals(mes2), false);
    }

    @Test
    public void shouldNotEqualsWithNullPointer () {
        mes1 = new Message(3);
        assertEquals(mes1.equals(null), false);
    }

    @Test
    public void shouldNotEqualsHashTwoMessages () {
        mes1 = new Message(33);
        mes2 = new Message(4);
        mes1.setResult("abc123");
        mes2.setResult("abc");
        assertEquals(mes1.hashCode() == mes2.hashCode(), false);
    }
}
