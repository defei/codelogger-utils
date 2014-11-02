package org.codelogger.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VerifyUtilsTest implements Runnable {

    private String name;

    @Test
    public void isMobileNumber() {

        boolean isMobileNumber = VerifyUtils.isMobileNumber("13350090421");
        assertTrue(isMobileNumber);

        isMobileNumber = VerifyUtils.isMobileNumber("15999999999");
        assertTrue(isMobileNumber);

        isMobileNumber = VerifyUtils.isMobileNumber(null);
        assertFalse(isMobileNumber);
    }

    @Test
    public void isEmail() {

        boolean isEmail = VerifyUtils.isEmail("aaa@b.cc");
        assertFalse(isEmail);

        isEmail = VerifyUtils.isEmail(null);
        assertFalse(isEmail);

        isEmail = VerifyUtils.isEmail("aaa@-b.cc");
        assertFalse(isEmail);

        isEmail = VerifyUtils.isEmail("aaa@bb.cc");
        assertTrue(isEmail);
    }

    @Test
    public void isIPAddress() {

        boolean isIPAddress = VerifyUtils.isIPAddress("123.321.123.123");
        assertFalse(isIPAddress);

        isIPAddress = VerifyUtils.isIPAddress(null);
        assertFalse(isIPAddress);

        isIPAddress = VerifyUtils.isIPAddress("123.123.123.123");
        assertTrue(isIPAddress);

    }

    public static void main(final String[] args) {

        VerifyUtilsTest concurrencyRunner1 = new VerifyUtilsTest();
        concurrencyRunner1.setName("Thread1");
        VerifyUtilsTest concurrencyRunner2 = new VerifyUtilsTest();
        concurrencyRunner2.setName("Thread2");
        VerifyUtilsTest concurrencyRunner3 = new VerifyUtilsTest();
        concurrencyRunner3.setName("Thread3");
        Thread thread1 = new Thread(concurrencyRunner1);
        Thread thread2 = new Thread(concurrencyRunner2);
        Thread thread3 = new Thread(concurrencyRunner3);
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public void run() {

        long times = 10000;
        Monitor monitor = MonitorUtils
                .getMonitor(VerifyUtils.class.getSimpleName(), "isIPAddress");
        monitor.start();
        for (long i = 0; i < times; i++) {
            VerifyUtils.isIPAddress("123.123.123.123");
        }
        monitor.stopAndprintResult("%s run %s times", name, times);
    }

    public String getName() {

        return name;
    }

    public void setName(final String name) {

        this.name = name;
    }
}
