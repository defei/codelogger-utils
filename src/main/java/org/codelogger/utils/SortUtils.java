package org.codelogger.utils;

import static org.codelogger.utils.ArrayUtils.buildArray;
import static org.codelogger.utils.ClassUtils.classIs;
import static org.codelogger.utils.ClassUtils.getComponentClass;
import static org.codelogger.utils.ClassUtils.getPrimitiveClass;

import java.lang.reflect.Array;

/**
 * A useful tools to handle comparable array or collection.
 * 
 * @author DengDefei
 * 
 */
public class SortUtils {

    private final static Class<?> comparableClass = Comparable.class;

    private SortUtils() {

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

        return JudgeUtils.greaterThanOrEquals(object, other);
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

        return JudgeUtils.greaterThan(object, other);
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

        return JudgeUtils.lessThanOrEquals(object, other);
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

        return JudgeUtils.lessThan(object, other);
    }

    /**
     * Return a new ordered array has all element of given comparable array by
     * ascending.
     * 
     * @param comparableArray
     *            comparable array to be handed.
     */
    public static <T extends Comparable<? super T>> T[] asc(final T[] comparableArray) {

        if (comparableArray == null) {
            return null;
        }
        T[] clone = comparableArray.clone();
        ascByReflex(clone);
        return clone;
    }

    /**
     * Orders given comparable array by ascending.
     * 
     * @param comparableArray
     *            comparable array to be handed.
     */
    public static <T extends Comparable<? super T>> void ascDeep(final T[] comparableArray) {

        if (ArrayUtils.isEmpty(comparableArray)) {
            return;
        }
        ascByReflex(comparableArray);
    }

    /**
     * Return a new ordered array has all element of given comparable array by
     * descending.
     * 
     * @param comparableArray
     *            comparable array to be handed.
     */
    public static <T extends Comparable<? super T>> T[] desc(final T[] comparableArray) {

        if (comparableArray == null) {
            return null;
        }
        T[] clone = comparableArray.clone();
        descByReflex(clone);
        return clone;
    }

    /**
     * Orders given comparable array by descending.
     * 
     * @param comparableArray
     *            comparable array to be handed.
     */
    public static <T extends Comparable<? super T>> void descDeep(final T[] comparableArray) {

        if (ArrayUtils.isEmpty(comparableArray)) {
            return;
        }
        descByReflex(comparableArray);
    }

    /**
     * Return a new ordered array has all element of given comparable array by
     * ascending.
     * 
     * @param comparableArray
     *            comparable array to be handed.
     */
    public static <T> T ascClone(final T comparableArray) {

        if (isComparableArray(comparableArray)) {
            T clone = BeanUtils.clone(comparableArray);
            ascByReflex(clone);
            return clone;
        }
        return comparableArray;
    }

    /**
     * Orders given comparable array by ascending.
     * 
     * @param comparableArray
     *            comparable array to be handed.
     */
    public static <T> void asc(final T comparableArray) {

        if (isComparableArray(comparableArray)) {
            ascByReflex(comparableArray);
        }
    }

    /**
     * Return a new ordered array has all element of given comparable array by
     * descending.
     * 
     * @param comparableArray
     *            comparable array to be handed.
     */
    public static <T> T descClone(final T comparableArray) {

        if (isComparableArray(comparableArray)) {
            T clone = BeanUtils.clone(comparableArray);
            descByReflex(clone);
            return clone;
        }
        return comparableArray;
    }

    /**
     * Orders given comparable array by descending.
     * 
     * @param comparableArray
     *            comparable array to be handed.
     */
    public static <T> void desc(final T comparableArray) {

        if (isComparableArray(comparableArray)) {
            descByReflex(comparableArray);
        }
    }

    /**
     * Sort the array in reverse order.
     * 
     * @param array
     *            object to be handled.
     */
    public static <T> void reverse(final T array) {

        if (ArrayUtils.isArray(array)) {
            int mlength = Array.getLength(array) - 1;
            Object temp = null;
            for (int i = 0, j = mlength; i < mlength; i++, j--) {
                Object arg0 = Array.get(array, i);
                Object arg1 = Array.get(array, j);
                temp = arg0;
                Array.set(array, i, arg1);
                Array.set(array, j, temp);
            }
        }
    }

    private static <T> boolean isComparableArray(final T comparableArray) {

        if (ArrayUtils.isArray(comparableArray)) {
            Class<?> componentClass = getComponentClass(comparableArray);
            return componentClass.isPrimitive() || classIs(componentClass, comparableClass);
        }
        return false;
    }

    private static <T> void ascByReflex(final Object comparableArray) {

        boolean isAscending = true;
        int length = Array.getLength(comparableArray);
        if (length < 200) {
            bubbleSortByReflex(comparableArray, isAscending);
        } else {
            Object mergeSortByReflex = mergeSortByReflex(comparableArray, isAscending);
            ArrayUtils.copy(mergeSortByReflex, comparableArray);
        }
    }

    private static <T> void descByReflex(final Object comparableArray) {

        boolean isAscending = false;
        int length = Array.getLength(comparableArray);
        if (length < 200) {
            bubbleSortByReflex(comparableArray, isAscending);
        } else {
            Object mergeSortByReflex = mergeSortByReflex(comparableArray, isAscending);
            ArrayUtils.copy(mergeSortByReflex, comparableArray);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static void bubbleSortByReflex(final Object comparableArray, final boolean isAscending) {

        int length = Array.getLength(comparableArray);
        int mlength = length - 1;
        for (int i = 0; i < mlength; i++) {
            for (int j = i + 1; j < length; j++) {
                Comparable arg0 = (Comparable) Array.get(comparableArray, i);
                Comparable arg1 = (Comparable) Array.get(comparableArray, j);
                boolean isSwap = false;
                if (isAscending) {
                    isSwap = greaterThanOrEquals(arg0, arg1);
                } else {
                    isSwap = lessThanOrEquals(arg0, arg1);
                }
                if (isSwap) {
                    Array.set(comparableArray, i, arg1);
                    Array.set(comparableArray, j, arg0);
                }
            }
        }
    }

    private static <T> T mergeSortByReflex(final T comparableArray, final boolean isAscending) {

        int size = Array.getLength(comparableArray);
        if (size < 2) {
            return comparableArray;
        }
        int maxIndex = size - 1;
        int subArray0Size = size / 2 - 1;
        T subArray0 = ArrayUtils.subArray(comparableArray, 0, subArray0Size);
        T subArray1 = ArrayUtils.subArray(comparableArray, subArray0Size + 1, maxIndex);
        T sortedSubArray0 = mergeSortByReflex(subArray0, isAscending);
        T sortedSubArray1 = mergeSortByReflex(subArray1, isAscending);
        T mergedArray = merge(sortedSubArray0, sortedSubArray1, isAscending);
        return mergedArray;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static <T> T merge(final T array0, final T array1, final boolean isAscending) {

        int length0 = Array.getLength(array0);
        int length1 = Array.getLength(array1);
        int size = length0 + length1;
        T result = (T) buildArray(getPrimitiveClass(getComponentClass(array0)), size, null);
        int index = 0;
        int index0 = 0;
        int index1 = 0;
        while (index < size) {

            if (index0 < length0) {
                Comparable element0 = (Comparable) Array.get(array0, index0);
                if (index1 < length1) {
                    Comparable element1 = (Comparable) Array.get(array1, index1);
                    boolean isSwap = false;
                    if (isAscending) {
                        isSwap = greaterThanOrEquals(element0, element1);
                    } else {
                        isSwap = lessThanOrEquals(element0, element1);
                    }
                    if (isSwap) {
                        Array.set(result, index, element1);
                        index1++;
                    } else {
                        Array.set(result, index, element0);
                        index0++;
                    }
                } else {
                    Array.set(result, index, element0);
                    index0++;
                }
            } else {
                Array.set(result, index, Array.get(array1, index1++));
            }

            index++;
        }

        return result;
    }
}
