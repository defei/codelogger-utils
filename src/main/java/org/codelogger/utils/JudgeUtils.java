package org.codelogger.utils;

import static org.codelogger.utils.ArrayUtils.contains;
import static org.codelogger.utils.ArrayUtils.isArray;
import static org.codelogger.utils.ArrayUtils.isEmpty;
import static org.codelogger.utils.ArrayUtils.toArray;
import static org.codelogger.utils.ClassUtils.getComponentClass;

import java.util.Arrays;
import java.util.Collection;

/**
 * A useful tools to judge like equal, not same, is null, is not null, all is
 * null, all is not null, greater or less and so on...
 * 
 * @author DengDefei
 * 
 */
public class JudgeUtils {

    private JudgeUtils() {

    }

    /**
     * Returns if source equals target.
     * 
     * @param <T>
     * 
     * @param source
     *            any object.
     * @param target
     *            any object.
     * @return if source equals target.
     */
    public static <T> boolean equals(final T source, final T target) {

        if (source == target) {
            return true;
        }
        if ((source == null) || (target == null)) {
            return false;
        }
        if (source.equals(target)) {
            return true;
        }
        if ((source.getClass().isArray()) && (target.getClass().isArray())) {
            if (((source instanceof Object[])) && ((target instanceof Object[]))) {
                return Arrays.equals((Object[]) source, (Object[]) target);
            }
            if (((source instanceof boolean[])) && ((target instanceof boolean[]))) {
                return Arrays.equals((boolean[]) source, (boolean[]) target);
            }
            if (((source instanceof byte[])) && ((target instanceof byte[]))) {
                return Arrays.equals((byte[]) source, (byte[]) target);
            }
            if (((source instanceof char[])) && ((target instanceof char[]))) {
                return Arrays.equals((char[]) source, (char[]) target);
            }
            if (((source instanceof double[])) && ((target instanceof double[]))) {
                return Arrays.equals((double[]) source, (double[]) target);
            }
            if (((source instanceof float[])) && ((target instanceof float[]))) {
                return Arrays.equals((float[]) source, (float[]) target);
            }
            if (((source instanceof int[])) && ((target instanceof int[]))) {
                return Arrays.equals((int[]) source, (int[]) target);
            }
            if (((source instanceof long[])) && ((target instanceof long[]))) {
                return Arrays.equals((long[]) source, (long[]) target);
            }
            if (((source instanceof short[])) && ((target instanceof short[]))) {
                return Arrays.equals((short[]) source, (short[]) target);
            }
        }
        return false;
    }

    /**
     * Returns if source not equals target.
     * 
     * @param source
     *            any object.
     * @param target
     *            any object.
     * @return if source not equals target.
     */
    public static boolean notSame(final Object source, final Object target) {

        return !equals(source, target);
    }

    /**
     * Returns if object is null.
     * 
     * @param object
     *            any object.
     * @return if object is null.
     */
    public static boolean isNull(final Object object) {

        return object == null;
    }

    /**
     * Returns if all given object is null.<br>
     * If only given an array, judge if all given array element is null.<br>
     * If only given a collection, judge if all given collection element is
     * null.<br>
     * If only given an array or collection is not null and empty return true.
     * 
     * @param objects
     *            any objects.
     * @return if all given object is null.
     */
    public static boolean allIsNull(final Object object, final Object... objects) {

        if (object == null) {
            return allIs(objects, null);
        }
        if (isEmpty(objects)) {
            if (isArray(object)) {
                return allIs(toArray(object, getComponentClass(object)), null);
            }
            if (object instanceof Collection) {
                return allIs((Collection<?>) object, null);
            }
        }
        return false;
    }

    /**
     * Returns true if given array equals given condition or all given array
     * element equals given condition; false otherwise.
     * 
     * @param array
     *            object to be tested.
     * @param condition
     *            object to be tested.
     * @return true if given array equals given condition or all given array
     *         element equals given condition; false otherwise.
     */
    public static boolean allIs(final Object array, final Object condition) {

        if (isArray(array)) {
            if (notSame(array, condition)) {
                for (Object element : toArray(array, getComponentClass(array))) {
                    if (notSame(element, condition)) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return equals(array, condition);
        }
    }

    /**
     * Returns if all given array element is equals condition.<br>
     * If array is not null and empty return true.
     * 
     * @param array
     *            any array.
     * @param condition
     *            any object
     * @return if all given array element is equals condition.
     */
    public static boolean allIs(final Object[] array, final Object condition) {

        if (notSame(array, condition)) {
            for (Object element : array) {
                if (notSame(element, condition)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns if all given collection element is equals condition.<br>
     * If collection is not null and empty return true.
     * 
     * @param collection
     *            any collection.
     * @param condition
     *            any object
     * @return if all given collection element is equals condition.
     */
    public static boolean allIs(final Collection<?> collection, final Object condition) {

        if (notSame(collection, condition)) {
            for (Object element : collection) {
                if (notSame(element, condition)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns if object is not null.
     * 
     * @param object
     *            any object.
     * @return if object is not null.
     */
    public static boolean isNotNull(final Object object) {

        return object != null;
    }

    /**
     * Returns if all given object is not null and objects is not empty.
     * 
     * @param objects
     *            any objects.
     * @return if all given object is not null.
     */
    public static boolean allIsNotNull(final Object object, final Object... objects) {

        if (object == null) {
            return false;
        }
        if (isEmpty(objects)) {
            if (isArray(object)) {
                return allIsNot(toArray(object, getComponentClass(object)), null);
            }
            if (object instanceof Collection) {
                return allIsNot((Collection<?>) object, null);
            }
        }
        return allIsNot(objects, null);

    }

    /**
     * Returns if all given array element is not equals given condition.<br>
     * If array is not null and empty return true.
     * 
     * @param array
     *            any array.
     * @param condition
     *            any condition.
     * @return if all given array element is not equals given condition.
     */
    public static boolean allIsNot(final Object[] array, final Object condition) {

        if (equals(array, condition)) {
            return false;
        }
        return !contains(array, condition);
    }

    /**
     * Returns true if given array is not an array and not equals condition or
     * given array is an array and all given array element is not equals given
     * condition; false otherwise.
     * 
     * @param array
     *            object to be tested.
     * @param condition
     *            object condition to be tested.
     * @return true if given array is not an array and not equals condition or
     *         given array is an array and all given array element is not equals
     *         given condition; false otherwise.
     */
    public static boolean allIsNot(final Object array, final Object condition) {

        if (equals(array, condition)) {
            return false;
        }
        if (isArray(array)) {
            return !contains(toArray(array, getComponentClass(array)), condition);
        } else {
            return true;
        }
    }

    /**
     * Returns if all given collection element is not equals given condition.<br>
     * If array is not null and empty return true.
     * 
     * @param collection
     *            any collection.
     * @param condition
     *            any condition.
     * @return if all given collection element is not equals given condition.
     */
    public static boolean allIsNot(final Collection<?> collection, final Object condition) {

        if (notSame(collection, condition)) {
            return !collection.contains(condition);
        }
        return false;
    }

    /**
     * Returns if all given condition is true.
     * 
     * @param conditions
     *            any booleans.
     * @return if all given condition is true.
     */
    public static boolean allIsTrue(final Boolean condition, final Boolean... conditions) {

        if (condition) {
            return allIs(conditions, true);
        }
        return false;
    }

    /**
     * Returns if all given condition is false.
     * 
     * @param conditions
     *            any booleans.
     * @return if all given condition is false.
     */
    public static boolean allIsFalse(final Boolean condition, final Boolean... conditions) {

        if (condition) {
            return false;
        }
        return allIs(conditions, false);
    }

    /**
     * Returns true if at least one given object is null; false otherwise.
     * 
     * @param objects
     *            any objects.
     * @return true if at least one given object is null; false otherwise.
     */
    public static boolean hasNull(final Object object, final Object... objects) {

        return !allIsNotNull(object, objects);
    }

    /**
     * Returns true if object equals other or object greater than other; false
     * otherwise.
     * 
     * @param object
     *            comparable object to test.
     * @param other
     *            comparable object to test.
     * @return true if object equals other or object above other; false
     *         otherwise.
     */
    public static <T extends Comparable<? super T>> boolean greaterThanOrEquals(final T object,
            final T other) {

        if (object == other || other == null) {
            return true;
        }
        if (object == null) {
            return false;
        }
        int compareValue = object.compareTo(other);
        return compareValue >= 0;
    }

    /**
     * Returns true if object greater than other; false otherwise.
     * 
     * @param object
     *            comparable object to test.
     * @param other
     *            comparable object to test.
     * @return true if object greater than other; false otherwise.
     */
    public static <T extends Comparable<? super T>> boolean greaterThan(final T object,
            final T other) {

        if (object == other) {
            return false;
        } else {
            if (object == null) {
                return false;
            }
            if (other == null) {
                return true;
            }
        }
        int compareValue = object.compareTo(other);
        return compareValue > 0;
    }

    /**
     * Returns true if object equals other or object less than other; false
     * otherwise.
     * 
     * @param object
     *            comparable object to test.
     * @param other
     *            comparable object to test.
     * @return true if object equals other or object above other; false
     *         otherwise.
     */
    public static <T extends Comparable<? super T>> boolean lessThanOrEquals(final T object,
            final T other) {

        if (object == other || object == null) {
            return true;
        }
        if (other == null) {
            return false;
        }
        int compareValue = object.compareTo(other);
        return compareValue <= 0;
    }

    /**
     * Returns true if object less than other; false otherwise.
     * 
     * @param object
     *            comparable object to test.
     * @param other
     *            comparable object to test.
     * @return true if object less than other; false otherwise.
     */
    public static <T extends Comparable<? super T>> boolean lessThan(final T object, final T other) {

        if (object == other) {
            return false;
        } else {
            if (object == null) {
                return true;
            }
            if (other == null) {
                return false;
            }
        }
        int compareValue = object.compareTo(other);
        return compareValue < 0;
    }

    public static <T> Boolean targetInElements(T target, T... elements) {

        for (T t : elements) {
            if (equals(t, target)) {
                return true;
            }
        }
        return false;
    }
}
