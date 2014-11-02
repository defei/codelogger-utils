package org.codelogger.utils.exception;

import org.codelogger.utils.exceptions.InvalidRangeException;

import org.junit.Test;

public class InvalidRangeExceptionTest {

    @Test
    public void create() {

        InvalidRangeException invalidRangeException = new InvalidRangeException("Test message!");
        invalidRangeException.printStackTrace();
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException(
                "IllegalArgumentException");
        InvalidRangeException haveOtherExceptionMessage = new InvalidRangeException(
                "Test message!", illegalArgumentException);
        haveOtherExceptionMessage.printStackTrace();
    }
}
