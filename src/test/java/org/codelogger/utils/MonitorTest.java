package org.codelogger.utils;

import static junit.framework.Assert.assertTrue;

import org.codelogger.utils.Monitor;
import org.junit.Test;

public class MonitorTest {

    private Monitor monitor;

    @Test(expected = IllegalArgumentException.class)
    public void stopMonitorAndGetSpendTimeMillis() {

        monitor = Monitor.getInstance("MonitorTest", "getSpendTimeMillis");
        monitor.stopAndGetSpendTimeMillis();
    }

    @Test
    public void stopMonitorAndGetSpendTimeMillis1() throws InterruptedException {

        monitor = Monitor.getInstance("MonitorTest", "getSpendTimeMillis1");
        monitor.start();
        Thread.sleep(300);
        long spendTimeMillis = monitor.stopAndGetSpendTimeMillis();
        assertTrue(spendTimeMillis > 200);
    }

    @Test
    public void stopMonitorAndgetMonitorMessage() throws InterruptedException {

        monitor = Monitor.getInstance("MonitorTest", "stopMonitorAndgetMonitorMessage");
        monitor.start();
        Thread.sleep(200);
        String message = monitor.stopAndgetMonitorMessage();
        assertTrue(message.contains("stopMonitorAndgetMonitorMessage"));
        System.out.println(message);
    }

    @Test
    public void stopMonitorAndprintResult() throws InterruptedException {

        monitor = Monitor.getInstance("MonitorTest", "stopMonitorAndprintResult");
        monitor.start();
        Thread.sleep(200);
        monitor.stopAndprintResult();
    }

    @Test
    public void stopMonitorAndprintResult_haveMessage() throws InterruptedException {

        monitor = Monitor.getInstance("MonitorTest", "stopMonitorAndprintResult");
        for (int i = 0; i < 3; i++) {
            monitor.start();
            Thread.sleep(200);
            System.out.println(monitor.stopAndgetMonitorMessage());
            monitor.stopAndprintResult("Step:%s", i);
        }
    }

}
