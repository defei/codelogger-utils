package org.codelogger.utils;

public class MonitorUtils {

    private MonitorUtils() {

    }

    public static Monitor getMonitor(final String className, final String methodName) {

        return Monitor.getInstance(className, methodName);
    }

}
