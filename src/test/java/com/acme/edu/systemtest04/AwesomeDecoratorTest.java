package com.acme.edu.systemtest04;

import com.acme.edu.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

public class AwesomeDecoratorTest implements SysoutCaptureAndAssertionAbility {
    private Logger logger;
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
        logger = new Logger(new AwesomeDecorator(), new SequenceDataProcessor(), new ConsoleWriter());
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

    @Test
    public void loggingToDifferentSource() throws IOException {
        //region when
        logger.log(1);
        logger.stopLogging();
        //endregion

        //region then
        assertSysoutContains("FIIIINEEEE:   ");
        assertSysoutContains("1");
        assertSysoutContains("   Looooooooooo");
        //endregion
    }
}