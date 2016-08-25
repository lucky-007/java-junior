package com.acme.edu.iteration02;

import com.acme.edu.ConsoleDecorator;
import com.acme.edu.ConsoleWriter;
import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    private Logger logger;

    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
        logger = new Logger(new ConsoleDecorator(), new ConsoleWriter());
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

    @Test
    public void shouldLogSequentIntegersAsSum() throws IOException {
        //region when
        logger.log("str 1");
        logger.log(1);
        logger.log(2);
        logger.log("str 2");
        logger.log(0);
        logger.stopLogging();
        //endregion

        //region then
        assertSysoutContains("str 1");
        assertSysoutContains("3");
        assertSysoutContains("str 2");
        assertSysoutContains("0");
        //endregion
    }

    @Test
    public void shouldLogCorrectlyIntegerOverflowOnPositiveSideWhenSequentIntegers() {
        //region when
        logger.log("str 1");
        logger.log(10);
        logger.log(Integer.MAX_VALUE);
        logger.log("str 2");
        logger.log(0);
        logger.stopLogging();
        //endregion

        //region then

        assertSysoutContains("str 1");
        assertSysoutContains("10");
        assertSysoutContains("" + Integer.MAX_VALUE);
        assertSysoutContains("str 2");
        assertSysoutContains("0");

        //endregion
    }

    @Test
    public void shouldLogCorrectlyIntegerOverflowOnNegativeSideWhenSequentIntegers() {
        //region when
        logger.log("str 1");
        logger.log(-10);
        logger.log(Integer.MIN_VALUE);
        logger.log("str 2");
        logger.log(0);
        logger.stopLogging();
        //endregion

        //region then

        assertSysoutContains("str 1");
        assertSysoutContains("-10");
        assertSysoutContains("" + Integer.MIN_VALUE);
        assertSysoutContains("str 2");
        assertSysoutContains("0");

        //endregion
    }

    @Test
    public void shouldLogCorrectlyByteOverflowOnPositiveSideWhenSequentBytes() {
        //region when
        logger.log("str 1");
        logger.log((byte)10);
        logger.log((byte)Byte.MAX_VALUE);
        logger.log("str 2");
        logger.log(0);
        logger.stopLogging();
        //endregion

        //region then

        assertSysoutContains("str 1");
        assertSysoutContains("10");
        assertSysoutContains("" + Byte.MAX_VALUE);
        assertSysoutContains("str 2");
        assertSysoutContains("0");
        //endregion
    }

    @Test
    public void shouldLogCorrectlyByteOverflowOnNegativeSideWhenSequentBytes() {
        //region when
        logger.log("str 1");
        logger.log((byte)-10);
        logger.log((byte)Byte.MIN_VALUE);
        logger.log("str 2");
        logger.log(0);
        logger.stopLogging();
        //endregion

        //region then

        assertSysoutContains("str 1");
        assertSysoutContains("-10");
        assertSysoutContains("" + Byte.MIN_VALUE);
        assertSysoutContains("str 2");
        assertSysoutContains("0");
        //endregion
    }

    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException {
        //region when
        logger.log("str 1");
        logger.log("str 2");
        logger.log("str 2");
        logger.log(0);
        logger.log("str 2");
        logger.log("str 3");
        logger.log("str 3");
        logger.log("str 3");
        logger.stopLogging();
        //endregion

        //region then

        assertSysoutContains("str 1");
        assertSysoutContains("str 2 (x2)");
        assertSysoutContains("0");
        assertSysoutContains("str 2");
        assertSysoutContains("str 3 (x3)");
        //endregion
    }


}