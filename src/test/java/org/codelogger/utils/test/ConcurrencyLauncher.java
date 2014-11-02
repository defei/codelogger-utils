package org.codelogger.utils.test;

public class ConcurrencyLauncher {

    public static void main(final String[] args) {

        ConcurrencyRunner concurrencyRunner1 = new ConcurrencyRunner("Thread1");
        ConcurrencyRunner concurrencyRunner2 = new ConcurrencyRunner("Thread2");
        ConcurrencyRunner concurrencyRunner3 = new ConcurrencyRunner("Thread3");
        Thread thread1 = new Thread(concurrencyRunner1);
        Thread thread2 = new Thread(concurrencyRunner2);
        Thread thread3 = new Thread(concurrencyRunner3);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
