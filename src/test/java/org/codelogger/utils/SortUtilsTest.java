package org.codelogger.utils;

import static org.codelogger.utils.MathUtils.randomLong;
import static org.codelogger.utils.PrintUtils.println;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.codelogger.utils.JudgeUtils;
import org.codelogger.utils.MonitorUtils;
import org.codelogger.utils.SortUtils;
import org.codelogger.utils.Monitor;

import org.junit.Test;

public class SortUtilsTest {

    @Test
    public void greaterThanOrEquals() {

        boolean greaterOrEquals = SortUtils.greaterThanOrEquals(null, null);
        assertTrue(greaterOrEquals);
        greaterOrEquals = SortUtils.greaterThanOrEquals("a", null);
        assertTrue(greaterOrEquals);
        greaterOrEquals = SortUtils.greaterThanOrEquals(null, "b");
        assertFalse(greaterOrEquals);
        greaterOrEquals = SortUtils.greaterThanOrEquals("a", "a");
        assertTrue(greaterOrEquals);
        greaterOrEquals = SortUtils.greaterThanOrEquals("a", "b");
        assertFalse(greaterOrEquals);
        greaterOrEquals = SortUtils.greaterThanOrEquals("b", "a");
        assertTrue(greaterOrEquals);
        greaterOrEquals = SortUtils.greaterThanOrEquals(2, 1);
        assertTrue(greaterOrEquals);
    }

    @Test
    public void greaterThan() {

        boolean greaterThan = SortUtils.greaterThan(null, null);
        assertFalse(greaterThan);
        greaterThan = SortUtils.greaterThan("a", null);
        assertTrue(greaterThan);
        greaterThan = SortUtils.greaterThan(null, "b");
        assertFalse(greaterThan);
        greaterThan = SortUtils.greaterThan("a", "a");
        assertFalse(greaterThan);
        greaterThan = SortUtils.greaterThan("a", "b");
        assertFalse(greaterThan);
        greaterThan = SortUtils.greaterThan("b", "a");
        assertTrue(greaterThan);
        greaterThan = SortUtils.greaterThan(2, 1);
        assertTrue(greaterThan);
    }

    @Test
    public void lessThanOrEquals() {

        boolean lessOrequals = SortUtils.lessThanOrEquals(null, null);
        assertTrue(lessOrequals);
        lessOrequals = SortUtils.lessThanOrEquals("a", null);
        assertFalse(lessOrequals);
        lessOrequals = SortUtils.lessThanOrEquals(null, "b");
        assertTrue(lessOrequals);
        lessOrequals = SortUtils.lessThanOrEquals("a", "a");
        assertTrue(lessOrequals);
        lessOrequals = SortUtils.lessThanOrEquals("a", "b");
        assertTrue(lessOrequals);
        lessOrequals = SortUtils.lessThanOrEquals("b", "a");
        assertFalse(lessOrequals);
        lessOrequals = SortUtils.lessThanOrEquals(2, 1);
        assertFalse(lessOrequals);
    }

    @Test
    public void lessThan() {

        boolean lessThan = SortUtils.lessThan(null, null);
        assertFalse(lessThan);
        lessThan = SortUtils.lessThan("a", null);
        assertFalse(lessThan);
        lessThan = SortUtils.lessThan(null, "b");
        assertTrue(lessThan);
        lessThan = SortUtils.lessThan("a", "a");
        assertFalse(lessThan);
        lessThan = SortUtils.lessThan("a", "b");
        assertTrue(lessThan);
        lessThan = SortUtils.lessThan("b", "a");
        assertFalse(lessThan);
        lessThan = SortUtils.lessThan(2, 1);
        assertFalse(lessThan);
    }

    @Test
    public void asc() {

        String[] expectedResult = { null, "a", "b", "c", "f" };
        String[] comparableArray = { "c", "b", null, "a", "f" };
        Comparable<String>[] ordered = SortUtils.asc(comparableArray);
        println(comparableArray);
        assertFalse(JudgeUtils.equals(expectedResult, comparableArray));
        assertTrue(JudgeUtils.equals(expectedResult, ordered));
        comparableArray = null;
        SortUtils.asc(comparableArray);

        int[] expectedIntArray = { 1, 2, 3, 4, 5, 6 };
        int[] intArray = { 2, 4, 1, 5, 6, 3 };
        int[] orderedIntArray = SortUtils.ascClone(intArray);
        println(orderedIntArray);
        assertFalse(JudgeUtils.equals(expectedIntArray, intArray));
        assertTrue(JudgeUtils.equals(expectedIntArray, orderedIntArray));

        Object[] objectArray = { "a", 1L, 3D };
        Object[] asc = SortUtils.ascClone(objectArray);
        assertTrue(JudgeUtils.equals(objectArray, asc));
        SortUtils.ascClone(new Object());

        Long[] longArray = { 2L, 1L, 3L };
        Object objct = longArray;
        SortUtils.ascClone(objct);

    }

    @Test
    public void ascDeep() {

        String[] expectedResult = { null, "a", "b", "c", "f" };
        String[] comparableArray = { "c", "b", null, "a", "f" };
        SortUtils.ascDeep(comparableArray);
        println(comparableArray);
        assertTrue(JudgeUtils.equals(expectedResult, comparableArray));
        comparableArray = null;
        SortUtils.ascDeep(comparableArray);

        int[] expectedIntArray = { 1, 2, 3, 4, 5, 6 };
        int[] intArray = { 2, 4, 1, 5, 6, 3 };
        SortUtils.asc(intArray);
        println(intArray);
        assertTrue(JudgeUtils.equals(expectedIntArray, intArray));

        Object[] expectedObjectArray = { "a", 1L, 3D };
        Object[] objectArray = { "a", 1L, 3D };
        SortUtils.asc(objectArray);
        assertTrue(JudgeUtils.equals(expectedObjectArray, objectArray));
        SortUtils.asc(new Object());

        Long[] longArray = { 2L, 1L, 3L };
        Object objct = longArray;
        SortUtils.asc(objct);
    }

    @Test
    public void descClone() {

        String[] expectedResult = { "f", "c", "b", "a", null };
        String[] comparableArray = { "c", "b", null, "a", "f" };
        Comparable<String>[] ordered = SortUtils.desc(comparableArray);
        println(ordered);
        assertFalse(JudgeUtils.equals(expectedResult, comparableArray));
        assertTrue(JudgeUtils.equals(expectedResult, ordered));
        comparableArray = null;
        SortUtils.desc(comparableArray);

        int[] expectedIntArray = { 6, 5, 4, 3, 2, 1 };
        int[] intArray = { 2, 4, 1, 5, 6, 3 };
        int[] orderedIntArray = SortUtils.descClone(intArray);
        println(orderedIntArray);
        assertFalse(JudgeUtils.equals(expectedIntArray, intArray));
        assertTrue(JudgeUtils.equals(expectedIntArray, orderedIntArray));

        Object[] objectArray = { "a", 1L, 3D };
        Object[] desc = SortUtils.descClone(objectArray);
        assertTrue(JudgeUtils.equals(objectArray, desc));
        SortUtils.descClone(new Object());

        Long[] longArray = { 2L, 1L, 3L };
        Object objct = longArray;
        SortUtils.descClone(objct);
    }

    @Test
    public void descDeep() {

        String[] expectedResult = { "f", "c", "b", "a", null };
        String[] comparableArray = { "c", "b", null, "a", "f" };
        SortUtils.descDeep(comparableArray);
        println(comparableArray);
        assertTrue(JudgeUtils.equals(expectedResult, comparableArray));
        comparableArray = null;
        SortUtils.descDeep(comparableArray);

        int[] expectedIntArray = { 6, 5, 4, 3, 2, 1 };
        int[] intArray = { 2, 4, 1, 5, 6, 3 };
        SortUtils.desc(intArray);
        println(intArray);
        assertTrue(JudgeUtils.equals(expectedIntArray, intArray));

        Object[] expectedObjectArray = { "a", 1L, 3D };
        Object[] objectArray = { "a", 1L, 3D };
        SortUtils.desc(objectArray);
        assertTrue(JudgeUtils.equals(expectedObjectArray, objectArray));
        SortUtils.desc(new Object());

        Long[] longArray = { 2L, 1L, 3L };
        Object objct = longArray;
        SortUtils.desc(objct);
    }

    @Test
    public void reverse() {

        String[] nullArray = null;
        SortUtils.reverse(nullArray);
        assertTrue(JudgeUtils.equals(null, nullArray));

        String[] expectedArray = { "c", "b", "a" };
        String[] testArray = { "a", "b", "c" };
        assertFalse(JudgeUtils.equals(expectedArray, testArray));
        SortUtils.reverse(testArray);
        assertTrue(JudgeUtils.equals(expectedArray, testArray));

        Object nullObject = null;
        SortUtils.reverse(nullObject);
        assertTrue(JudgeUtils.equals(null, nullObject));

        Object object = new Object();
        SortUtils.reverse(object);
        assertTrue(JudgeUtils.equals(object, object));

        int[] expectedIntArray = { 3, 2, 1 };
        int[] testIntArray = { 1, 2, 3 };
        assertFalse(JudgeUtils.equals(expectedIntArray, testIntArray));
        SortUtils.reverse(testIntArray);
        assertTrue(JudgeUtils.equals(expectedIntArray, testIntArray));

        Object[] expectedObjectArray = { "c", 1L, "a" };
        SortUtils.reverse(expectedObjectArray);
        assertTrue(JudgeUtils.equals(expectedObjectArray, expectedObjectArray));

    }

    @Test
    public void performance() {

        int size = 1000;
        // performance divide line: 200
        println("Array size:%s", size);
        Long[] array = new Long[size];
        for (int i = 0; i < size; i++) {
            array[i] = randomLong(size);
        }

        println(array);
        Monitor monitor = MonitorUtils.getMonitor(this.getClass().getSimpleName(), "performance");
        monitor.start();
        Long[] ascClone = SortUtils.asc(array);
        monitor.stopAndprintResult("By asc");
        println(ascClone);
    }
}