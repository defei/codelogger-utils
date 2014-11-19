package org.codelogger.utils;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class MonitorUtilsTest {

    @Test
    public void getInstance() {

        Monitor monitor = MonitorUtils.getMonitor("MonitorUtilsTest", "getInstence");
        assertNotNull(monitor);
    }

}
