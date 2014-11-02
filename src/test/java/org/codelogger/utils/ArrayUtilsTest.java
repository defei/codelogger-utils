package org.codelogger.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.codelogger.utils.ArrayUtils;
import org.codelogger.utils.JudgeUtils;
import org.junit.Test;

public class ArrayUtilsTest {

    Object[] objectArray;

    boolean[] booleanArray;

    byte[] byteArray;

    short[] shortArray;

    char[] charArray;

    int[] intArray;

    float[] floatArrayl;

    double[] doubleArray;

    long[] longArray;

    @Test
    public void isEmpty() {

        assertTrue(ArrayUtils.isEmpty(objectArray));
        objectArray = new Object[0];
        assertTrue(ArrayUtils.isEmpty(objectArray));
        objectArray = new Object[21];
        assertFalse(ArrayUtils.isEmpty(objectArray));

        assertTrue(ArrayUtils.isEmpty(booleanArray));
        booleanArray = new boolean[0];
        assertTrue(ArrayUtils.isEmpty(booleanArray));
        booleanArray = new boolean[21];
        assertFalse(ArrayUtils.isEmpty(booleanArray));

        assertTrue(ArrayUtils.isEmpty(byteArray));
        byteArray = new byte[0];
        assertTrue(ArrayUtils.isEmpty(byteArray));
        byteArray = new byte[21];
        assertFalse(ArrayUtils.isEmpty(byteArray));

        assertTrue(ArrayUtils.isEmpty(shortArray));
        shortArray = new short[0];
        assertTrue(ArrayUtils.isEmpty(shortArray));
        shortArray = new short[21];
        assertFalse(ArrayUtils.isEmpty(shortArray));

        assertTrue(ArrayUtils.isEmpty(charArray));
        charArray = new char[0];
        assertTrue(ArrayUtils.isEmpty(charArray));
        charArray = new char[21];
        assertFalse(ArrayUtils.isEmpty(charArray));

        assertTrue(ArrayUtils.isEmpty(intArray));
        intArray = new int[0];
        assertTrue(ArrayUtils.isEmpty(intArray));
        intArray = new int[21];
        assertFalse(ArrayUtils.isEmpty(intArray));

        assertTrue(ArrayUtils.isEmpty(floatArrayl));
        floatArrayl = new float[0];
        assertTrue(ArrayUtils.isEmpty(floatArrayl));
        floatArrayl = new float[21];
        assertFalse(ArrayUtils.isEmpty(floatArrayl));

        assertTrue(ArrayUtils.isEmpty(doubleArray));
        doubleArray = new double[0];
        assertTrue(ArrayUtils.isEmpty(doubleArray));
        doubleArray = new double[21];
        assertFalse(ArrayUtils.isEmpty(doubleArray));

        assertTrue(ArrayUtils.isEmpty(longArray));
        longArray = new long[0];
        assertTrue(ArrayUtils.isEmpty(longArray));
        longArray = new long[21];
        assertFalse(ArrayUtils.isEmpty(longArray));

        objectArray = new Object[0];
        Object obj = objectArray;
        assertTrue(ArrayUtils.isEmpty(obj));

        assertFalse(ArrayUtils.isEmpty(new Object()));
    }

    @Test
    public void isNotEmpty() {

        assertFalse(ArrayUtils.isNotEmpty(objectArray));
        objectArray = new Object[0];
        assertFalse(ArrayUtils.isNotEmpty(objectArray));
        objectArray = new Object[21];
        assertTrue(ArrayUtils.isNotEmpty(objectArray));

        assertFalse(ArrayUtils.isNotEmpty(booleanArray));
        booleanArray = new boolean[0];
        assertFalse(ArrayUtils.isNotEmpty(booleanArray));
        booleanArray = new boolean[21];
        assertTrue(ArrayUtils.isNotEmpty(booleanArray));

        assertFalse(ArrayUtils.isNotEmpty(byteArray));
        byteArray = new byte[0];
        assertFalse(ArrayUtils.isNotEmpty(byteArray));
        byteArray = new byte[21];
        assertTrue(ArrayUtils.isNotEmpty(byteArray));

        assertFalse(ArrayUtils.isNotEmpty(shortArray));
        shortArray = new short[0];
        assertFalse(ArrayUtils.isNotEmpty(shortArray));
        shortArray = new short[21];
        assertTrue(ArrayUtils.isNotEmpty(shortArray));

        assertFalse(ArrayUtils.isNotEmpty(charArray));
        charArray = new char[0];
        assertFalse(ArrayUtils.isNotEmpty(charArray));
        charArray = new char[21];
        assertTrue(ArrayUtils.isNotEmpty(charArray));

        assertFalse(ArrayUtils.isNotEmpty(intArray));
        intArray = new int[0];
        assertFalse(ArrayUtils.isNotEmpty(intArray));
        intArray = new int[21];
        assertTrue(ArrayUtils.isNotEmpty(intArray));

        assertFalse(ArrayUtils.isNotEmpty(floatArrayl));
        floatArrayl = new float[0];
        assertFalse(ArrayUtils.isNotEmpty(floatArrayl));
        floatArrayl = new float[21];
        assertTrue(ArrayUtils.isNotEmpty(floatArrayl));

        assertFalse(ArrayUtils.isNotEmpty(doubleArray));
        doubleArray = new double[0];
        assertFalse(ArrayUtils.isNotEmpty(doubleArray));
        doubleArray = new double[21];
        assertTrue(ArrayUtils.isNotEmpty(doubleArray));

        assertFalse(ArrayUtils.isNotEmpty(longArray));
        longArray = new long[0];
        assertFalse(ArrayUtils.isNotEmpty(longArray));
        longArray = new long[21];
        assertTrue(ArrayUtils.isNotEmpty(longArray));

        objectArray = new Object[0];
        Object obj = objectArray;
        assertFalse(ArrayUtils.isNotEmpty(obj));
    }

    @Test
    public void isArray() {

        int[] intArray = { 1, 2, 3 };
        Object[] objectArray = { "a", "b" };
        assertTrue(ArrayUtils.isArray(intArray));
        assertTrue(ArrayUtils.isArray(objectArray));
        assertFalse(ArrayUtils.isArray(new Object()));
        assertFalse(ArrayUtils.isArray(null));
    }

    @Test
    public void contains() {

        String[] stringArray = { "a", "b", "c", "d", "e" };
        String containedValue = "b";
        String notContainedValue = "f";
        assertTrue(ArrayUtils.contains(stringArray, containedValue));
        assertFalse(ArrayUtils.contains(stringArray, notContainedValue));
        stringArray = null;
        assertFalse(ArrayUtils.contains(stringArray, notContainedValue));
    }

    @Test
    public void append() {

        String[] stringArray = { "a", "b", "c", "d", "e" };
        String[] stringArray1 = { "t", "e", "s", "t", "f" };
        String[] nullArray = null;
        String notContainedValue = "f";
        String notContainedValue1 = "t";
        String notContainedValue2 = "a";
        String notContainedValue3 = "e";
        String nullValue = null;

        String[] result = ArrayUtils.append(stringArray, notContainedValue);
        assertTrue(ArrayUtils.contains(result, notContainedValue));
        assertEquals(result[5], notContainedValue);

        result = ArrayUtils.append(nullArray, notContainedValue);
        assertTrue(ArrayUtils.contains(result, notContainedValue));
        assertEquals(result[0], notContainedValue);

        result = ArrayUtils.append(nullArray, nullValue);
        assertNull(result);

        result = ArrayUtils.append(nullArray, nullArray);
        assertNull(result);

        result = ArrayUtils.append(stringArray, stringArray1);
        assertTrue(ArrayUtils.contains(result, notContainedValue));
        assertEquals(result[0], notContainedValue2);
        assertEquals(result[5], notContainedValue1);
        assertEquals(result[9], notContainedValue);

        result = ArrayUtils.append(nullArray, stringArray1);
        assertTrue(ArrayUtils.contains(result, notContainedValue));
        assertEquals(result[0], notContainedValue1);

        result = ArrayUtils.append(stringArray, nullArray);
        assertTrue(ArrayUtils.contains(result, notContainedValue2));
        assertEquals(result[0], notContainedValue2);

        result = ArrayUtils.append(notContainedValue, stringArray);
        assertTrue(ArrayUtils.contains(result, notContainedValue));
        assertEquals(result[0], notContainedValue);
        assertEquals(result[5], notContainedValue3);

        result = ArrayUtils.append(notContainedValue, nullArray);
        assertTrue(ArrayUtils.contains(result, notContainedValue));
        assertEquals(result[0], notContainedValue);
    }

    @Test
    public void subArray() {

        Integer[] ecpectedArray = { 3, 0, 6 };
        Integer[] array = { 1, 2, 3, 0, 6, 1, 5, 3, 1, 3, 0, 6 };
        Integer[] subArray = ArrayUtils.subArray(array, 9);
        assertTrue(JudgeUtils.equals(ecpectedArray, subArray));
        subArray = ArrayUtils.subArray(array, -1);
        assertTrue(JudgeUtils.equals(array, subArray));
        subArray = ArrayUtils.subArray(null, 1);
        assertTrue(JudgeUtils.equals(null, subArray));
        subArray = ArrayUtils.subArray(array, 2, 4);
        assertTrue(JudgeUtils.equals(ecpectedArray, subArray));
        subArray = ArrayUtils.subArray(array, 4, 2);
        assertTrue(JudgeUtils.equals(array, subArray));
        subArray = ArrayUtils.subArray(array, -1, 2);
        assertTrue(JudgeUtils.equals(array, subArray));
        subArray = ArrayUtils.subArray(array, 1, 19);
        assertTrue(JudgeUtils.equals(array, subArray));
        subArray = ArrayUtils.subArray(null, 4, 2);
        assertTrue(JudgeUtils.equals(null, subArray));
    }

    @Test
    public void remove() {

        Integer[] array = { 1, 2, 3, 0, 6, 1, 5, 3, 1 };
        Integer[] expectedValues = { 2, 3, 0, 6, 5, 3 };
        Integer[] removedArray = ArrayUtils.remove(array, 1);
        for (int i = 0; i < expectedValues.length; i++) {
            assertEquals(expectedValues[i], removedArray[i]);
        }
        Integer[] integers = ArrayUtils.remove(null, 1);
        assertNull(integers);
        Integer[] removedArray1 = ArrayUtils.remove(array, 9);
        assertTrue(JudgeUtils.equals(array, removedArray1));
    }

    @Test
    public void count() {

        Integer[] array = { 1, 2, 3, 0, 6, 1, 5, 3, 1 };
        int expectedSize = 3;
        int count = ArrayUtils.count(array, 1);
        assertEquals(expectedSize, count);

        Integer[] array1 = {};
        int expectedSize1 = 0;
        int count1 = ArrayUtils.count(array1, 1);
        assertEquals(expectedSize1, count1);
    }

    @Test
    public void buildArray() {

        int expectedSize = 5;
        int defaultIntValue = 1;
        Date defaultDateVsalue = new Date();
        Integer[] ints = ArrayUtils.buildArray(int.class, expectedSize, defaultIntValue);
        Date[] dates = ArrayUtils.buildArray(Date.class, expectedSize, defaultDateVsalue);
        for (int i = 0; i < expectedSize; i++) {
            assertEquals(defaultIntValue, ints[i].intValue());
            assertEquals(defaultDateVsalue, dates[i]);
        }
    }

    @Test
    public void toArray() {

        String expectedValue0 = "a";
        String expectedValue1 = "c";
        String expectedValue2 = "b";

        int[] nullIntegerArray = null;
        Integer[] nullArray = ArrayUtils.toArray(nullIntegerArray, Integer.class);
        assertNull(nullArray);

        List<String> list = new ArrayList<String>();
        list.add(expectedValue0);
        list.add(expectedValue1);
        list.add(expectedValue2);
        String[] result = ArrayUtils.toArray(list);
        assertEquals(expectedValue0, result[0]);
        assertEquals(expectedValue1, result[1]);
        assertEquals(expectedValue2, result[2]);

        Set<String> set = new LinkedHashSet<String>();
        set.add(expectedValue0);
        set.add(expectedValue1);
        set.add(expectedValue2);
        result = ArrayUtils.toArray(set);
        assertEquals(expectedValue0, result[0]);
        assertEquals(expectedValue1, result[1]);
        assertEquals(expectedValue2, result[2]);

        Set<String> set1 = new HashSet<String>();
        set1.add(expectedValue0);
        set1.add(expectedValue1);
        set1.add(expectedValue2);
        result = ArrayUtils.toArray(set1);
        assertEquals(3, result.length);

        int[] intArray = { 1, 2, 3, 4, 5 };
        Integer[] integers = ArrayUtils.toArray(intArray, Integer.class);
        int index = 0;
        for (Integer element : integers) {

            assertEquals(intArray[index++], element.intValue());
        }
    }

    @Test
    public void toList() {

        String[] stringArray = { "a", "b", "c", "d", "e" };
        int[] intArray = { 1, 2, 3, 4, 5 };
        List<String> list = ArrayUtils.toList(stringArray);
        int index = 0;
        for (String element : list) {

            assertEquals(stringArray[index++], element);
        }

        List<Integer> list2 = ArrayUtils.toList(intArray, Integer.class);
        index = 0;
        for (Integer element : list2) {

            assertEquals(intArray[index++], element.intValue());
        }

        String[] nullArray = null;
        List<String> nullList = ArrayUtils.toList(nullArray);
        assertTrue(nullList.isEmpty());
    }

    @Test
    public void toSet() {

        String[] stringArray = { "a", "b", "c", "d", "e", "a", null };
        int[] intArray = { 1, 2, 3, 4, 5, 4, 1, 3 };
        Set<String> set = ArrayUtils.toSet(stringArray);
        assertEquals(6, set.size());

        Set<Integer> set2 = ArrayUtils.toSet(intArray, Integer.class);
        assertEquals(5, set2.size());

        String[] nullArray = null;
        Set<String> nullSet = ArrayUtils.toSet(nullArray);
        assertTrue(nullSet.isEmpty());
    }

    @Test
    public void getLastElement_isNotEmpty_returnCorrectValue() {

        String expected = "c";
        String[] stringArray = { "a", "b", expected };
        String lastElement = ArrayUtils.getLastElement(stringArray);
        assertEquals(expected, lastElement);
    }

    @Test
    public void getLastElement_isEmpty_returnNull() {

        String expected = null;
        String[] stringArray = {};
        String lastElement = ArrayUtils.getLastElement(stringArray);
        assertEquals(expected, lastElement);
    }

    @Test
    public void getLastElement_isNull_returnNull() {

        Object expected = null;
        Object lastElement = ArrayUtils.getLastElement(objectArray);
        assertEquals(expected, lastElement);
    }

    @Test
    public void join_isArray_noNullElement() {

        String[] array = { "a", "b", "c" };
        String separator = ",";
        String expected = "a,b,c";
        String actual = ArrayUtils.join(array, separator);
        assertEquals(expected, actual);
    }

    @Test
    public void join_isArray_hasNullElement() {

        String[] array = { "a", null, "c" };
        String separator = ",";
        String expected = "a,null,c";
        String actual = ArrayUtils.join(array, separator);
        assertEquals(expected, actual);
    }

    @Test
    public void join_isArray_arrayIsNull() {

        String[] array = null;
        String separator = ",";
        String expected = "";
        String actual = ArrayUtils.join(array, separator);
        assertEquals(expected, actual);
    }

    @Test
    public void join_isArray_arrayIsEmpty() {

        String[] array = {};
        String separator = ",";
        String expected = "";
        String actual = ArrayUtils.join(array, separator);
        assertEquals(expected, actual);
    }

    @Test
    public void join_isObject_noNullElement() {

        String[] array = { "a", "b", "c" };
        Object arrayObject = array;
        String separator = ",";
        String expected = "a,b,c";
        String actual = ArrayUtils.join(arrayObject, separator);
        assertEquals(expected, actual);
    }

    @Test
    public void join_isObject_hasNullElement() {

        String[] array = { "a", null, "c" };
        Object arrayObject = array;
        String separator = ",";
        String expected = "a,null,c";
        String actual = ArrayUtils.join(arrayObject, separator);
        assertEquals(expected, actual);
    }

    @Test
    public void join_isObject_arrayIsNull() {

        String[] array = null;
        Object arrayObject = array;
        String separator = ",";
        String expected = "";
        String actual = ArrayUtils.join(arrayObject, separator);
        assertEquals(expected, actual);
    }

    @Test
    public void join_isObject_arrayIsEmpty() {

        String[] array = {};
        Object arrayObject = array;
        String separator = ",";
        String expected = "";
        String actual = ArrayUtils.join(arrayObject, separator);
        assertEquals(expected, actual);
    }
}