package org.codelogger.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.codelogger.utils.JudgeUtils;
import org.codelogger.utils.MathUtils;
import org.junit.Test;

public class MathUtilsTest {

    @Test
    public void randomInt() {

        for (int i = 1; i < 100; i++) {
            int randomNumber = MathUtils.randomInt();
            assertTrue(randomNumber >= 0 && randomNumber <= Integer.MAX_VALUE);
        }
    }

    @Test
    public void randomInt_betweenZeroAndMax() {

        for (int i = 1; i < 100; i++) {
            int randomNumber = MathUtils.randomInt(10);
            assertTrue(randomNumber >= 0 && randomNumber <= 10);
        }
        int randomNumber = MathUtils.randomInt(0);
        assertEquals(0, randomNumber);
    }

    @Test
    public void randomInt_betweenMinAndMax() {

        for (int i = 1; i < 100; i++) {
            int randomNumber = MathUtils.randomInt(80, 90);
            assertTrue(randomNumber >= 80 && randomNumber <= 90);
        }
        int randomNumber = MathUtils.randomInt(80, 80);
        assertEquals(80, randomNumber);
    }

    @Test
    public void randomDouble() {

        for (int i = 1; i < 100; i++) {
            double randomNumber = MathUtils.randomDouble();
            assertTrue(randomNumber >= 0 && randomNumber <= Double.MAX_VALUE);
        }
    }

    @Test
    public void randomFloat() {

        for (int i = 1; i < 100; i++) {
            float randomNumber = MathUtils.randomFloat();
            assertTrue(randomNumber >= 0 && randomNumber <= Float.MAX_VALUE);
        }
    }

    @Test
    public void randomLong() {

        for (int i = 1; i < 100; i++) {
            long randomNumber = MathUtils.randomLong();
            assertTrue(randomNumber >= Long.MIN_VALUE && randomNumber <= Long.MAX_VALUE);
        }
    }

    @Test
    public void randomLong_BetweenZeroAndMax() {

        for (int i = 1; i < 100; i++) {
            long randomNumber = MathUtils.randomLong(10);
            assertTrue(randomNumber >= 0 && randomNumber <= 10);
        }
    }

    @Test
    public void randomLong_betweenMinAndMax() {

        for (int i = 1; i < 100; i++) {
            long randomNumber = MathUtils.randomLong(80, 90);
            assertTrue(randomNumber >= 80 && randomNumber <= 90);
        }
        long randomNumber = MathUtils.randomLong(80, 80);
        assertEquals(80, randomNumber);
    }

    @Test
    public void RoundUp() {

        double testValue = 3.5D;
        long actual = MathUtils.RoundUp(testValue);
        long expected = 4L;
        assertEquals(expected, actual);

        actual = MathUtils.RoundUp(null);
        expected = 0L;
        assertEquals(expected, actual);
    }

    @Test
    public void RoundDown() {

        double testValue = 3.5D;
        long actual = MathUtils.RoundDown(testValue);
        long expected = 3L;
        assertEquals(expected, actual);

        actual = MathUtils.RoundDown(null);
        expected = 0L;
        assertEquals(expected, actual);
    }

    @Test
    public void getAverage() {

        double average = MathUtils.divide(3L, 2L);
        assertTrue(JudgeUtils.equals(1.5d, average));

        average = MathUtils.divide(16L, 5L);
        assertTrue(JudgeUtils.equals(3.2d, average));

        average = MathUtils.divide(15L, null);
        assertTrue(JudgeUtils.equals(0d, average));

        average = MathUtils.divide(15L, 0L);
        assertTrue(JudgeUtils.equals(1d, average));

        average = MathUtils.divide(15L, 15L);
        assertTrue(JudgeUtils.equals(1d, average));
    }

    @Test
    public void getAverageRoundUp() {

        long average = MathUtils.divideRoundUp(3L, 2L);
        assertTrue(JudgeUtils.equals(2L, average));

        average = MathUtils.divideRoundUp(15L, 5L);
        assertTrue(JudgeUtils.equals(3L, average));

        average = MathUtils.divideRoundUp(15L, null);
        assertTrue(JudgeUtils.equals(0L, average));

        average = MathUtils.divideRoundUp(15L, 0L);
        assertTrue(JudgeUtils.equals(1L, average));
    }

    @Test
    public void getAverageRoundDown() {

        long average = MathUtils.divideRoundDown(3L, 2L);
        assertTrue(JudgeUtils.equals(1L, average));

        average = MathUtils.divideRoundDown(15L, 5L);
        assertTrue(JudgeUtils.equals(3L, average));

        average = MathUtils.divideRoundDown(15L, null);
        assertTrue(JudgeUtils.equals(0L, average));

        average = MathUtils.divideRoundDown(15L, 0L);
        assertTrue(JudgeUtils.equals(1L, average));
    }

    @Test
    public void isAliquot() {

        int dividend = 5;
        int divisor = 2;
        boolean aliquot = MathUtils.isAliquot(dividend, divisor);
        assertFalse(aliquot);
        dividend = 4;
        aliquot = MathUtils.isAliquot(dividend, divisor);
        assertTrue(aliquot);

    }
}
