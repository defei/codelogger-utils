package org.codelogger.utils;

import static junit.framework.Assert.assertNotNull;

import org.codelogger.utils.MonitorUtils;
import org.codelogger.utils.Monitor;

import org.junit.Test;

public class MonitorUtilsTest {

    @Test
    public void getInstance() {

        Monitor monitor = MonitorUtils.getMonitor("MonitorUtilsTest", "getInstence");
        assertNotNull(monitor);
    }

}
