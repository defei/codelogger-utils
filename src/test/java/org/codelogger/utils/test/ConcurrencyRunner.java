package org.codelogger.utils.test;

import org.codelogger.utils.Monitor;
import org.codelogger.utils.MonitorUtils;
import org.codelogger.utils.VerifyUtils;

public class ConcurrencyRunner implements Runnable {

    private final String name;

    public ConcurrencyRunner(final String name) {

        this.name = name;
    }

    public void run() {

        long times = 1000000;
        Monitor monitor = MonitorUtils
                .getMonitor(VerifyUtils.class.getSimpleName(), "isIPAddress");
        monitor.start();
        for (long i = 0; i < times; i++) {
            VerifyUtils.isIPAddress("123.123.123.123");
        }
        monitor.stopAndprintResult("%s run %s times", name, times);
    }

}
