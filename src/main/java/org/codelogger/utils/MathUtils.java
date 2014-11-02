package org.codelogger.utils;

import java.util.Random;

/**
 * A useful to handle math, likes get random value, number to string, and so on.
 * 
 * @author DengDefei
 * 
 */
public class MathUtils {

    private MathUtils() {

    }

    private static Random random = new Random();

    /**
     * Returns a natural integer value between 0 and integer max value.
     * 
     * @return a natural integer value between 0 and integer max value.
     */
    public static int randomInt() {

        return random.nextInt(Integer.MAX_VALUE);
    }

    /**
     * Returns a natural integer value between 0 and given max value.
     * 
     * @param max
     *            the max value to random.
     * @return a natural integer value between 0 and given max value.
     */
    public static int randomInt(final int max) {

        return random.nextInt(max + 1);
    }

    /**
     * Returns a natural integer number between given minimum value and max
     * value.
     * 
     * @param min
     *            the minimum value to random.
     * @param max
     *            the max value to random.
     * @return a natural integer number between given minimum value and max
     *         value.
     */
    public static int randomInt(final int min, final int max) {

        return (int) Math.round(Math.random() * (max - min) + min);
    }

    /**
     * Returns a random long value.
     * 
     * @return a random long value.
     */
    public static long randomLong() {

        return random.nextLong();
    }

    /**
     * Returns a natural long value between 0 and given max value.
     * 
     * @param max
     *            the max value to random.
     * @return a natural long value between 0 and given max value.
     */
    public static long randomLong(final long max) {

        return randomLong(0, max);
    }

    /**
     * Returns a natural long value between given minimum value and max value.
     * 
     * @param min
     *            the minimum value to random.
     * @param max
     *            the max value to random.
     * @return a natural long value between given minimum value and max value.
     */
    public static long randomLong(final long min, final long max) {

        return Math.round(Math.random() * (max - min) + min);
    }

    /**
     * Returns a double value between 0.0 and 1.0 from this random number.
     * 
     * @return a double value between 0.0 and 1.0 from this random number.
     */
    public static double randomDouble() {

        return random.nextDouble();
    }

    /**
     * Returns a float value between 0.0 and 1.0 from this random number.
     * 
     * @return a float value between 0.0 and 1.0 from this random number.
     */
    public static float randomFloat() {

        return random.nextFloat();
    }

    /**
     * Returns a long number by round up with specify number.<br>
     * Returns 0 if number == null.<br>
     * e.g: average is 3.5, then return 4.
     * 
     * @param number
     *            number to be handled.
     * @return a long number by round up with specify number.
     */
    public static <T extends Number> long RoundUp(final T number) {

        if (number == null) {
            return 0;
        }
        double value = number.doubleValue();
        if (value % 1 == 0) {
            return (long) value;
        } else {
            return (long) (value + 1);
        }
    }

    /**
     * Returns a long number by round down with specify number.<br>
     * Returns 0 if number == null.<br>
     * e.g: average is 3.5, then return 3.
     * 
     * @param number
     *            number to be handled.
     * @return a long number by round down with specify number.
     */
    public static <T extends Number> long RoundDown(final T number) {

        if (number == null) {
            return 0;
        }
        return number.longValue();
    }

    /**
     * Returns a double average number by given dividend and divisor.<br>
     * Returns 0 if dividend == null or divisor == null.<br>
     * Returns 1 if dividend == divisor or divisor == 0.
     * 
     * @param dividend
     *            number to be handled.
     * @param divisor
     *            number to be handled.
     * @return a double average number by given dividend and divisor.
     */
    public static <T extends Number> double divide(final T dividend, final T divisor) {

        if (JudgeUtils.hasNull(dividend, divisor)) {
            return 0d;
        }
        if (JudgeUtils.equals(dividend, divisor) || divisor.doubleValue() == 0) {
            return 1d;
        }
        return dividend.doubleValue() / divisor.doubleValue();
    }

    /**
     * Returns a long average number by round up with specify dividend and
     * divisor.<br>
     * Returns 0 if dividend == null or divisor == null.<br>
     * Returns 1 if dividend == divisor or divisor == 0.<br>
     * e.g: average is 3.5, then return 4.
     * 
     * @param dividend
     *            number to be handled.
     * @param divisor
     *            number to be handled.
     * @return a long average number by given dividend and divisor.
     */
    public static <T extends Number> long divideRoundUp(final T dividend, final T divisor) {

        double average = divide(dividend, divisor);
        return RoundUp(average);
    }

    /**
     * Returns a long average number by round down with specify dividend and
     * divisor.<br>
     * Returns 0 if dividend == null or divisor == null.<br>
     * Returns 1 if dividend == divisor or divisor == 0.<br>
     * e.g: average is 3.5, then return 3.
     * 
     * @param dividend
     *            number to be handled.
     * @param divisor
     *            number to be handled.
     * @return a long average number by given dividend and divisor.
     */
    public static <T extends Number> long divideRoundDown(final T dividend, final T divisor) {

        return (long) divide(dividend, divisor);
    }

    /**
     * Judge divisor is an aliquot part of dividend.</br> 判断被除数是否能被除数整除。
     * 
     * @param dividend
     *            number to be handled.被除数。
     * @param divisor
     *            number to be handled.除数。
     * @return true if divisor is an aliquot part of dividend, otherwise
     *         false.是否能被整除。
     */
    public static <T extends Number> boolean isAliquot(final T dividend, final T divisor) {

        return dividend.doubleValue() % divisor.doubleValue() == 0;
    }
}
