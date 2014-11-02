package org.codelogger.utils;

import org.codelogger.utils.ExceptionUtils;
import org.junit.Test;

public class ExceptionUtilsTest {

    @Test
    public void createNestedMessagesFromThrowable() {

        try {
            throw new Exception("It is a test!");
        } catch (Exception e) {
            String createNestedMessagesFromThrowable = ExceptionUtils
                    .createNestedMessagesFromThrowable(e);
            System.out.println(createNestedMessagesFromThrowable);
        }
    }

    @Test
    public void createNestedMessagesFromThrowable1() {

        try {
            throw new Exception();
        } catch (Exception e) {
            String createNestedMessagesFromThrowable = ExceptionUtils
                    .createNestedMessagesFromThrowable(e);
            System.out.println(createNestedMessagesFromThrowable);
        }
    }
}
