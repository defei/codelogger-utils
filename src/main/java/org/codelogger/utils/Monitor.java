package org.codelogger.utils;

import static org.codelogger.utils.ExceptionUtils.iae;

public class Monitor {

    private long startTime = 0L;

    private long stopTime = 0L;

    private final String className;

    private final String methodName;

    private final String CLASSNAME_ISNULL_MESSAGE = "The className can not be null.";

    private final String METHODNAME_ISNULL_MESSAGE = "The methodName can not be null.";

    private Monitor(final Object objectToBeMonitoring, final String methodName) {

        iae.throwIfNull(objectToBeMonitoring, CLASSNAME_ISNULL_MESSAGE);
        iae.throwIfNull(methodName, METHODNAME_ISNULL_MESSAGE);
        this.className = objectToBeMonitoring.getClass().getSimpleName();
        this.methodName = methodName;
    }

    public static Monitor getInstance(final Object objectToBeMonitoring, final String methodName) {

        return new Monitor(objectToBeMonitoring, methodName);
    }

    public void start() {

        startTime = System.currentTimeMillis();
        stopTime = 0L;
    }

    public long stopAndGetSpendTimeMillis() {

        iae.throwIfTrue(startTime == 0, "You must start monitor first!");
        return getStopTime() - startTime;
    }

    public void stopAndprintResult() {

        System.out.println(stopAndgetMonitorMessage());
    }

    public void stopAndprintResult(final String message, final Object... args) {

        System.out.println(stopMonitorAndgetMonitorMessage(String.format(message, args)));
    }

    public String stopAndgetMonitorMessage() {

        return stopMonitorAndgetMonitorMessage(null);
    }

    private String stopMonitorAndgetMonitorMessage(final String message) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" **===> You are spend ");
        stringBuilder.append(((double) stopAndGetSpendTimeMillis() / 1000));
        stringBuilder.append("s running: ");
        stringBuilder.append(className);
        stringBuilder.append(".");
        stringBuilder.append(methodName);
        if (message != null) {
            stringBuilder.append("(");
            stringBuilder.append(message);
            stringBuilder.append(")");
        }
        stringBuilder.append(" <===**");
        return stringBuilder.toString();
    }

    private long getStopTime() {

        return stopTime == 0 ? stopTime = System.currentTimeMillis() : stopTime;
    }
}
