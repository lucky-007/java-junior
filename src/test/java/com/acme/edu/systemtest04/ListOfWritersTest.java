package com.acme.edu.systemtest04;

import com.acme.edu.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

public class ListOfWritersTest implements SysoutCaptureAndAssertionAbility {
    private Logger logger;
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
        logger = new Logger(new ConsoleDecorator(), new SequenceDataProcessor(), new ConsoleWriter(), new NotConsoleWriter());
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
        assertSysoutContains("primitive: ");
        assertSysoutContains("1");
        assertSysoutContains("[NOT CONSOLE]: ");
        assertSysoutContains("1");
        //endregion
    }
}
