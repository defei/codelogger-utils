package org.codelogger.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.codelogger.utils.JudgeUtils;
import org.junit.Test;

import com.google.common.collect.Lists;

public class JudgeUtilsTest {

    byte byteValue = 'b';

    byte[] byteArray = { 'a', 'b', 'c', 'd', 'e' };

    short shortValue = 's';

    short[] shortArray = { 'a', 'b', 'c', 'd', 'e' };

    char charValue = 'c';

    char[] charArray = { 'a', 'b', 'c', 'd', 'e' };

    int intValue = 121;

    int[] intArray = { 1, 2, 3, 4, 5 };

    float floatValue = 1.21F;

    float[] floatArray = { 1.21F, 1.22F, 1.23F, 1.24F, 1.25F };

    long longValue = 121L;

    long[] longArray = { 1L, 2L, 3L, 4L, 5L };

    double doubleValue = 1.21D;

    double[] doubleArray = { 1.21D, 1.22D, 1.23D, 1.24D, 1.25D };

    boolean booleanValue = false;

    boolean[] booleanArray = { true, false, true, true, false };

    Object objectValue = new Object();

    Object[] objectArray = new Object[5];

    String stringValue = "Just for test!";

    String[] stringArray = { "a", "b", "c", "d", "e" };

    @Test
    public void equals() {

        byte[] otherByteArray = byteArray.clone();
        assertFalse(byteArray.equals(otherByteArray));
        assertTrue(JudgeUtils.equals(byteArray, otherByteArray));
        assertFalse(JudgeUtils.equals(byteArray, stringArray));
        short[] otherShortArray = shortArray.clone();
        assertFalse(shortArray.equals(otherShortArray));
        assertTrue(JudgeUtils.equals(shortArray, otherShortArray));
        assertFalse(JudgeUtils.equals(shortArray, stringArray));
        char[] otherCharArray = charArray.clone();
        assertFalse(charArray.equals(otherCharArray));
        assertTrue(JudgeUtils.equals(charArray, otherCharArray));
        assertFalse(JudgeUtils.equals(charArray, stringArray));
        int[] otherIntArray = intArray.clone();
        assertFalse(intArray.equals(otherIntArray));
        assertTrue(JudgeUtils.equals(intArray, otherIntArray));
        assertFalse(JudgeUtils.equals(intArray, stringArray));
        float[] otherFloatArray = floatArray.clone();
        assertFalse(floatArray.equals(otherFloatArray));
        assertTrue(JudgeUtils.equals(floatArray, otherFloatArray));
        assertFalse(JudgeUtils.equals(floatArray, stringArray));
        long[] otherLongArray = longArray.clone();
        assertFalse(longArray.equals(otherLongArray));
        assertTrue(JudgeUtils.equals(longArray, otherLongArray));
        assertFalse(JudgeUtils.equals(longArray, stringArray));
        double[] otherDoubleArray = doubleArray.clone();
        assertFalse(doubleArray.equals(otherDoubleArray));
        assertTrue(JudgeUtils.equals(doubleArray, otherDoubleArray));
        assertFalse(JudgeUtils.equals(doubleArray, stringArray));
        boolean[] otherBooleanArray = booleanArray.clone();
        assertFalse(booleanArray.equals(otherBooleanArray));
        assertTrue(JudgeUtils.equals(booleanArray, otherBooleanArray));
        assertFalse(JudgeUtils.equals(booleanArray, stringArray));
        Object[] otherObjectArray = objectArray.clone();
        assertFalse(objectArray.equals(otherObjectArray));
        assertTrue(JudgeUtils.equals(objectArray, otherObjectArray));
        assertFalse(JudgeUtils.equals(objectArray, byteArray));

        Object object = new Object();
        assertTrue(JudgeUtils.equals(null, null));
        assertFalse(JudgeUtils.equals(object, null));
        assertFalse(JudgeUtils.equals(null, object));
        assertFalse(JudgeUtils.equals(object, byteArray));
        assertFalse(JudgeUtils.equals(byteArray, object));

        String long1 = new String("a");
        String long2 = new String("a");
        assertTrue(JudgeUtils.equals(long1, long2));
    }

    @Test
    public void notSame() {

        int[] array1 = { 1, 2, 3 };
        int[] array2 = { 1, 2, 3 };
        int[] array3 = { 1, 2, 1 };
        boolean isNotSame = JudgeUtils.notSame(array1, array2);
        assertFalse(isNotSame);
        isNotSame = JudgeUtils.notSame(array1, array3);
        assertTrue(isNotSame);
    }

    @Test
    public void isNull() {

        boolean isNull = JudgeUtils.isNull(objectValue);
        assertFalse(isNull);
        objectValue = null;
        isNull = JudgeUtils.isNull(objectValue);
        assertTrue(isNull);
    }

    @Test
    public void allIsNull() {

        Object nullObject = null;
        assertFalse(JudgeUtils.allIsNull(objectValue));
        assertFalse(JudgeUtils.allIsNull(objectValue, nullObject));
        assertFalse(JudgeUtils.allIsNull(nullObject, objectValue));
        assertTrue(JudgeUtils.allIsNull(nullObject, nullObject));
        assertFalse(JudgeUtils.allIsNull(objectValue, objectValue));

        assertTrue(JudgeUtils.allIsNull(objectArray));
        objectArray = null;
        assertTrue(JudgeUtils.allIsNull(objectArray));
        Object[] emptyArray = {};
        assertTrue(JudgeUtils.allIsNull(emptyArray));
        Object[] haveNullArray = { null, new Object() };
        assertFalse(JudgeUtils.allIsNull(haveNullArray));
        Object[] notHaveNullArray = { new Object(), new Object() };
        assertFalse(JudgeUtils.allIsNull(notHaveNullArray));

        List<Object> emptyList = Lists.newArrayList();
        assertTrue(JudgeUtils.allIsNull(emptyList));
        List<Object> nullList = null;
        assertTrue(JudgeUtils.allIsNull(nullList));
        List<Object> allElementIsNull = Lists.newArrayList();
        allElementIsNull.add(nullObject);
        assertTrue(JudgeUtils.allIsNull(allElementIsNull));
        List<Object> notHaveNullList = Lists.newArrayList();
        notHaveNullList.add(objectValue);
        assertFalse(JudgeUtils.allIsNull(notHaveNullList));
    }

    @Test
    public void allIs() {

        Object nullObject = null;

        assertFalse(JudgeUtils.allIs(new Object(), 1));

        int[] intArray1 = { 1, 1, 1 };
        int[] intArray2 = { 1, 2, 1 };
        assertTrue(JudgeUtils.allIs(intArray1, 1));
        assertTrue(JudgeUtils.allIs(intArray1, intArray1));
        assertFalse(JudgeUtils.allIs(intArray2, 1));

        assertTrue(JudgeUtils.allIs(objectArray, nullObject));
        objectArray = null;
        assertTrue(JudgeUtils.allIs(objectArray, nullObject));
        Object[] emptyArray = {};
        assertTrue(JudgeUtils.allIs(emptyArray, nullObject));
        Object[] haveNullArray = { null, new Object() };
        assertFalse(JudgeUtils.allIs(haveNullArray, nullObject));
        Object[] notHaveNullArray = { new Object(), new Object() };
        assertFalse(JudgeUtils.allIs(notHaveNullArray, nullObject));

        List<Object> emptyList = Lists.newArrayList();
        assertTrue(JudgeUtils.allIs(emptyList, nullObject));
        List<Object> nullList = null;
        assertTrue(JudgeUtils.allIs(nullList, nullObject));
        List<Object> allElementIsNull = Lists.newArrayList();
        allElementIsNull.add(nullObject);
        assertTrue(JudgeUtils.allIs(allElementIsNull, nullObject));
        List<Object> notHaveNullList = Lists.newArrayList();
        notHaveNullList.add(objectValue);
        assertFalse(JudgeUtils.allIs(notHaveNullList, nullObject));
    }

    @Test
    public void isNotNull() {

        boolean isNotNull = JudgeUtils.isNotNull(objectValue);
        assertTrue(isNotNull);
        objectValue = null;
        isNotNull = JudgeUtils.isNotNull(objectValue);
        assertFalse(isNotNull);
    }

    @Test
    public void allIsNotNull() {

        Object nullObject = null;
        assertTrue(JudgeUtils.allIsNotNull(objectValue));
        assertFalse(JudgeUtils.allIsNotNull(nullObject));
        assertFalse(JudgeUtils.allIsNotNull(objectValue, nullObject));
        assertFalse(JudgeUtils.allIsNotNull(nullObject, objectValue));
        assertFalse(JudgeUtils.allIsNotNull(nullObject, nullObject));
        assertTrue(JudgeUtils.allIsNotNull(objectValue, objectValue));

        assertFalse(JudgeUtils.allIsNotNull(objectArray));
        objectArray = null;
        assertFalse(JudgeUtils.allIsNotNull(objectArray));
        Object[] emptyArray = {};
        assertTrue(JudgeUtils.allIsNotNull(emptyArray));
        Object[] haveNullArray = { new Object(), null };
        assertFalse(JudgeUtils.allIsNotNull(haveNullArray));
        Object[] notHaveNullArray = { new Object(), new Object() };
        assertTrue(JudgeUtils.allIsNotNull(notHaveNullArray));

        List<Object> emptyList = Lists.newArrayList();
        assertTrue(JudgeUtils.allIsNotNull(emptyList));
        List<Object> nullList = null;
        assertFalse(JudgeUtils.allIsNotNull(nullList));
        List<Object> allElementIsNull = Lists.newArrayList();
        allElementIsNull.add(nullObject);
        assertFalse(JudgeUtils.allIsNotNull(allElementIsNull));
        List<Object> notHaveNullList = Lists.newArrayList();
        notHaveNullList.add(objectValue);
        assertTrue(JudgeUtils.allIsNotNull(notHaveNullList));
    }

    @Test
    public void allIsNot() {

        Object nullObject = null;

        assertTrue(JudgeUtils.allIsNot(new Object(), 1));

        int[] intArray1 = { 1, 1, 1 };
        int[] intArray2 = { 1, 2, 1 };
        assertTrue(JudgeUtils.allIsNot(intArray1, 2));
        assertFalse(JudgeUtils.allIsNot(intArray2, 2));
        assertFalse(JudgeUtils.allIsNot(intArray2, intArray2));

        assertFalse(JudgeUtils.allIsNot(objectArray, nullObject));
        objectArray = null;
        assertFalse(JudgeUtils.allIsNot(objectArray, nullObject));
        Object[] emptyArray = {};
        assertTrue(JudgeUtils.allIsNot(emptyArray, nullObject));
        Object[] haveNullArray = { null, new Object() };
        assertFalse(JudgeUtils.allIsNot(haveNullArray, nullObject));
        Object[] notHaveNullArray = { new Object(), new Object() };
        assertTrue(JudgeUtils.allIsNot(notHaveNullArray, nullObject));

        List<Object> emptyList = Lists.newArrayList();
        assertTrue(JudgeUtils.allIsNot(emptyList, nullObject));
        List<Object> nullList = null;
        assertFalse(JudgeUtils.allIsNot(nullList, nullObject));
        List<Object> allElementIsNull = Lists.newArrayList();
        allElementIsNull.add(nullObject);
        assertFalse(JudgeUtils.allIsNot(allElementIsNull, nullObject));
        List<Object> notHaveNullList = Lists.newArrayList();
        notHaveNullList.add(objectValue);
        assertTrue(JudgeUtils.allIsNot(notHaveNullList, nullObject));
    }

    @Test
    public void allIsTrue() {

        assertFalse(JudgeUtils.allIsTrue(true, false));
        assertFalse(JudgeUtils.allIsTrue(false, false));
        assertTrue(JudgeUtils.allIsTrue(true, true));
        assertFalse(JudgeUtils.allIsTrue(false));
    }

    @Test
    public void allIsFalse() {

        assertFalse(JudgeUtils.allIsFalse(true, false));
        assertFalse(JudgeUtils.allIsFalse(false, true));
        assertTrue(JudgeUtils.allIsFalse(false, false));
        assertFalse(JudgeUtils.allIsFalse(true, true));
        assertTrue(JudgeUtils.allIsFalse(false));
    }

    @Test
    public void greaterThanOrEquals() {

        boolean greaterOrEquals = JudgeUtils.greaterThanOrEquals(null, null);
        assertTrue(greaterOrEquals);
        greaterOrEquals = JudgeUtils.greaterThanOrEquals("a", null);
        assertTrue(greaterOrEquals);
        greaterOrEquals = JudgeUtils.greaterThanOrEquals(null, "b");
        assertFalse(greaterOrEquals);
        greaterOrEquals = JudgeUtils.greaterThanOrEquals("a", "a");
        assertTrue(greaterOrEquals);
        greaterOrEquals = JudgeUtils.greaterThanOrEquals("a", "b");
        assertFalse(greaterOrEquals);
        greaterOrEquals = JudgeUtils.greaterThanOrEquals("b", "a");
        assertTrue(greaterOrEquals);
        greaterOrEquals = JudgeUtils.greaterThanOrEquals(2, 1);
        assertTrue(greaterOrEquals);
    }

    @Test
    public void greaterThan() {

        boolean greaterThan = JudgeUtils.greaterThan(null, null);
        assertFalse(greaterThan);
        greaterThan = JudgeUtils.greaterThan("a", null);
        assertTrue(greaterThan);
        greaterThan = JudgeUtils.greaterThan(null, "b");
        assertFalse(greaterThan);
        greaterThan = JudgeUtils.greaterThan("a", "a");
        assertFalse(greaterThan);
        greaterThan = JudgeUtils.greaterThan("a", "b");
        assertFalse(greaterThan);
        greaterThan = JudgeUtils.greaterThan("b", "a");
        assertTrue(greaterThan);
        greaterThan = JudgeUtils.greaterThan(2, 1);
        assertTrue(greaterThan);
    }

    @Test
    public void lessThanOrEquals() {

        boolean lessOrequals = JudgeUtils.lessThanOrEquals(null, null);
        assertTrue(lessOrequals);
        lessOrequals = JudgeUtils.lessThanOrEquals("a", null);
        assertFalse(lessOrequals);
        lessOrequals = JudgeUtils.lessThanOrEquals(null, "b");
        assertTrue(lessOrequals);
        lessOrequals = JudgeUtils.lessThanOrEquals("a", "a");
        assertTrue(lessOrequals);
        lessOrequals = JudgeUtils.lessThanOrEquals("a", "b");
        assertTrue(lessOrequals);
        lessOrequals = JudgeUtils.lessThanOrEquals("b", "a");
        assertFalse(lessOrequals);
        lessOrequals = JudgeUtils.lessThanOrEquals(2, 1);
        assertFalse(lessOrequals);
    }

    @Test
    public void lessThan() {

        boolean lessThan = JudgeUtils.lessThan(null, null);
        assertFalse(lessThan);
        lessThan = JudgeUtils.lessThan("a", null);
        assertFalse(lessThan);
        lessThan = JudgeUtils.lessThan(null, "b");
        assertTrue(lessThan);
        lessThan = JudgeUtils.lessThan("a", "a");
        assertFalse(lessThan);
        lessThan = JudgeUtils.lessThan("a", "b");
        assertTrue(lessThan);
        lessThan = JudgeUtils.lessThan("b", "a");
        assertFalse(lessThan);
        lessThan = JudgeUtils.lessThan(2, 1);
        assertFalse(lessThan);
    }

    @Test
    public void hasNull() {

        boolean hasNull = JudgeUtils.hasNull(new Object(), new Object());
        assertFalse(hasNull);
        hasNull = JudgeUtils.hasNull(new Object(), new Object(), null);
        assertTrue(hasNull);
    }

    @Test
    public void targetInElements() {

        Boolean targetInElements = JudgeUtils.targetInElements("a", "b", "a", "c");
        assertTrue(targetInElements);
        targetInElements = JudgeUtils.targetInElements("d", "b", "a", "c");
        assertFalse(targetInElements);
        targetInElements = JudgeUtils.targetInElements("a", stringArray);
        assertTrue(targetInElements);
        targetInElements = JudgeUtils.targetInElements("z", stringArray);
        assertFalse(targetInElements);
    }
}
