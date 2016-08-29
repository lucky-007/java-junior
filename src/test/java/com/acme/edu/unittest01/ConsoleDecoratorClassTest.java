package com.acme.edu.unittest01;

import com.acme.edu.ConsoleDecorator;
import com.acme.edu.interfaces.DecorContentStrategy;
import com.acme.edu.message.Message;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConsoleDecoratorClassTest {
    private ConsoleDecorator consoleDecorator;
    private Message messageMock;
    private DecorContentStrategy decorContentStrategyMock;
    private String result;

    @Before
    public void setUp() {

        consoleDecorator = new ConsoleDecorator();
        messageMock = mock(Message.class);
        decorContentStrategyMock = mock(DecorContentStrategy.class);
    }

    @Test
    public void shouldDecorateByte() {
        //region Given
        when(messageMock.getType()).thenReturn("java.lang.Byte");
        when(messageMock.getValue()).thenReturn((byte)1);

//        when(decorContentStrategyMock.decorateContent(messageMock)).thenReturn("1");
        //endregion

        //region When
        result = consoleDecorator.decorate(messageMock);
        //endregion

        //region Then
        assertEquals("primitive: 1", result);
        //endregion
    }

    @Test
    public void shouldDecorateInteger() {
        //region Given
        when(messageMock.getType()).thenReturn("java.lang.Integer");
        when(messageMock.getValue()).thenReturn(1);
        //endregion

        //region When
        result = consoleDecorator.decorate(messageMock);
        //endregion

        //region Then
        assertEquals("primitive: 1", result);
        //endregion
    }

    @Test
    public void shouldDecorateBoolean() {
        //region Given
        when(messageMock.getType()).thenReturn("java.lang.Boolean");
        when(messageMock.getValue()).thenReturn(true);
        //endregion

        //region When
        result = consoleDecorator.decorate(messageMock);
        //endregion

        //region Then
        assertEquals("primitive: true", result);
        //endregion
    }

    @Test
    public void shouldDecorateString() {
        //region Given
        when(messageMock.getType()).thenReturn("java.lang.String");
        when(messageMock.getValue()).thenReturn("1");
        //endregion

        //region When
        result = consoleDecorator.decorate(messageMock);
        //endregion

        //region Then
        assertEquals("string: 1", result);
        //endregion
    }

    @Test
    public void shouldDecorateObject() {
        //region Given
        when(messageMock.getType()).thenReturn("java.lang.Object");
        when(messageMock.getValue()).thenReturn("java.lang.Object@11111111");
        //endregion

        //region When
        result = consoleDecorator.decorate(messageMock);
        //endregion

        //region Then
        assertEquals("reference: java.lang.Object@11111111", result);
        //endregion
    }

    @Test
    public void shouldDecorateCharacter() {
        //region Given
        when(messageMock.getType()).thenReturn("java.lang.Character");
        when(messageMock.getValue()).thenReturn('1');
        //endregion

        //region When
        result = consoleDecorator.decorate(messageMock);
        //endregion

        //region Then
        assertEquals("char: 1", result);
        //endregion
    }

    @Test
    public void shouldDecorateOneDimensionalArray() {
        //region Given
        when(messageMock.getType()).thenReturn("[I");
        when(messageMock.getValue()).thenReturn(new int[] {1,0,1});
        //endregion

        //region When
        result = consoleDecorator.decorate(messageMock);
        //endregion

        //region Then
        assertEquals("primitives array: {1, 0, 1}", result);
        //endregion
    }

    @Test
    public void shouldDecorateTwoDimensionalArray() {
        //region Given
        when(messageMock.getType()).thenReturn("[[I");
        when(messageMock.getValue()).thenReturn(new int[][] {{1},{0},{1}});
        //endregion

        //region When
        result = consoleDecorator.decorate(messageMock);
        //endregion

        //region Then
        assertEquals("primitives matrix: {" + System.lineSeparator()
                + "{1}" + System.lineSeparator()
                + "{0}" + System.lineSeparator()
                + "{1}" + System.lineSeparator()
                + "}", result);
        //endregion
    }
}
