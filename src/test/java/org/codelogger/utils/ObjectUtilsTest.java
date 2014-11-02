package org.codelogger.utils;

import static org.codelogger.utils.PrintUtils.println;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.codelogger.utils.ObjectUtils;
import org.junit.Test;

public class ObjectUtilsTest {

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
    public void listHashCode() {

        List<Long> list1 = new ArrayList<Long>();
        list1.add(1L);
        list1.add(2L);
        List<Long> list2 = new ArrayList<Long>();
        list2.add(1L);
        list2.add(2L);
        Assert.assertEquals(list1, list2);

    }

    @Test
    public void testEquals() {

        byte[] otherByteArray = byteArray.clone();
        assertFalse(byteArray.equals(otherByteArray));
        assertTrue(ObjectUtils.equals(byteArray, otherByteArray));
        assertFalse(ObjectUtils.equals(byteArray, stringArray));
        short[] otherShortArray = shortArray.clone();
        assertFalse(shortArray.equals(otherShortArray));
        assertTrue(ObjectUtils.equals(shortArray, otherShortArray));
        assertFalse(ObjectUtils.equals(shortArray, stringArray));
        char[] otherCharArray = charArray.clone();
        assertFalse(charArray.equals(otherCharArray));
        assertTrue(ObjectUtils.equals(charArray, otherCharArray));
        assertFalse(ObjectUtils.equals(charArray, stringArray));
        int[] otherIntArray = intArray.clone();
        assertFalse(intArray.equals(otherIntArray));
        assertTrue(ObjectUtils.equals(intArray, otherIntArray));
        assertFalse(ObjectUtils.equals(intArray, stringArray));
        float[] otherFloatArray = floatArray.clone();
        assertFalse(floatArray.equals(otherFloatArray));
        assertTrue(ObjectUtils.equals(floatArray, otherFloatArray));
        assertFalse(ObjectUtils.equals(floatArray, stringArray));
        long[] otherLongArray = longArray.clone();
        assertFalse(longArray.equals(otherLongArray));
        assertTrue(ObjectUtils.equals(longArray, otherLongArray));
        assertFalse(ObjectUtils.equals(longArray, stringArray));
        double[] otherDoubleArray = doubleArray.clone();
        assertFalse(doubleArray.equals(otherDoubleArray));
        assertTrue(ObjectUtils.equals(doubleArray, otherDoubleArray));
        assertFalse(ObjectUtils.equals(doubleArray, stringArray));
        boolean[] otherBooleanArray = booleanArray.clone();
        assertFalse(booleanArray.equals(otherBooleanArray));
        assertTrue(ObjectUtils.equals(booleanArray, otherBooleanArray));
        assertFalse(ObjectUtils.equals(booleanArray, stringArray));
        Object[] otherObjectArray = objectArray.clone();
        assertFalse(objectArray.equals(otherObjectArray));
        assertTrue(ObjectUtils.equals(objectArray, otherObjectArray));
        assertFalse(ObjectUtils.equals(objectArray, byteArray));

        Object object = new Object();
        assertTrue(ObjectUtils.equals(null, null));
        assertFalse(ObjectUtils.equals(object, null));
        assertFalse(ObjectUtils.equals(null, object));
        assertFalse(ObjectUtils.equals(object, byteArray));
        assertFalse(ObjectUtils.equals(byteArray, object));

        String long1 = new String("a");
        String long2 = new String("a");
        assertTrue(ObjectUtils.equals(long1, long2));
    }

    @Test
    public void byteHashCode() {

        assertNotSame(0, ObjectUtils.hashCode(byteValue));
        Byte otherValue = byteValue;
        assertEquals(ObjectUtils.hashCode(byteValue), ObjectUtils.hashCode(otherValue));
        assertNotSame(0, ObjectUtils.hashCode(byteArray));
        objectValue = byteArray;
        assertNotSame(0, ObjectUtils.hashCode(objectValue));
        byteArray = null;
        assertEquals(0, ObjectUtils.hashCode(byteArray));
    }

    @Test
    public void shortHashCode() {

        assertNotSame(0, ObjectUtils.hashCode(shortValue));
        Short otherValue = shortValue;
        assertEquals(ObjectUtils.hashCode(shortValue), ObjectUtils.hashCode(otherValue));
        assertNotSame(0, ObjectUtils.hashCode(shortArray));
        objectValue = shortArray;
        assertNotSame(0, ObjectUtils.hashCode(objectValue));
        shortArray = null;
        assertEquals(0, ObjectUtils.hashCode(shortArray));
    }

    @Test
    public void charHashCode() {

        assertNotSame(0, ObjectUtils.hashCode(charValue));
        Character otherValue = charValue;
        assertEquals(ObjectUtils.hashCode(charValue), ObjectUtils.hashCode(otherValue));
        assertNotSame(0, ObjectUtils.hashCode(charArray));
        objectValue = charArray;
        assertNotSame(0, ObjectUtils.hashCode(objectValue));
        charArray = null;
        assertEquals(0, ObjectUtils.hashCode(charArray));
    }

    @Test
    public void intHashCode() {

        assertNotSame(0, ObjectUtils.hashCode(intValue));
        Integer otherValue = intValue;
        assertEquals(ObjectUtils.hashCode(intValue), ObjectUtils.hashCode(otherValue));
        assertNotSame(0, ObjectUtils.hashCode(intArray));
        objectValue = intArray;
        assertNotSame(0, ObjectUtils.hashCode(objectValue));
        intArray = null;
        assertEquals(0, ObjectUtils.hashCode(intArray));
    }

    @Test
    public void floatHashCode() {

        assertNotSame(0, ObjectUtils.hashCode(floatValue));
        Float otherValue = floatValue;
        assertEquals(ObjectUtils.hashCode(floatValue), ObjectUtils.hashCode(otherValue));
        assertNotSame(0, ObjectUtils.hashCode(floatArray));
        objectValue = floatArray;
        assertNotSame(0, ObjectUtils.hashCode(objectValue));
        floatArray = null;
        assertEquals(0, ObjectUtils.hashCode(floatArray));
    }

    @Test
    public void longHashCode() {

        assertNotSame(0, ObjectUtils.hashCode(longValue));
        Long otherValue = longValue;
        assertEquals(ObjectUtils.hashCode(longValue), ObjectUtils.hashCode(otherValue));
        assertNotSame(0, ObjectUtils.hashCode(longArray));
        objectValue = longArray;
        assertNotSame(0, ObjectUtils.hashCode(objectValue));
        longArray = null;
        assertEquals(0, ObjectUtils.hashCode(longArray));
    }

    @Test
    public void doubleHashCode() {

        assertNotSame(0, ObjectUtils.hashCode(doubleValue));
        Double otherValue = doubleValue;
        assertEquals(ObjectUtils.hashCode(doubleValue), ObjectUtils.hashCode(otherValue));
        assertNotSame(0, ObjectUtils.hashCode(doubleArray));
        objectValue = doubleArray;
        assertNotSame(0, ObjectUtils.hashCode(objectValue));
        doubleArray = null;
        assertEquals(0, ObjectUtils.hashCode(doubleArray));
    }

    @Test
    public void booleanHashCode() {

        assertNotSame(0, ObjectUtils.hashCode(true));
        assertNotSame(0, ObjectUtils.hashCode(false));
        assertNotSame(ObjectUtils.hashCode(true), ObjectUtils.hashCode(false));
        Boolean otherValue = booleanValue;
        assertEquals(ObjectUtils.hashCode(booleanValue), ObjectUtils.hashCode(otherValue));
        assertNotSame(0, ObjectUtils.hashCode(booleanArray));
        objectValue = booleanArray;
        assertNotSame(0, ObjectUtils.hashCode(objectValue));
        booleanArray = null;
        assertEquals(0, ObjectUtils.hashCode(booleanArray));
    }

    @Test
    public void objectHashCode() {

        assertNotSame(0, ObjectUtils.hashCode(objectValue));
        Object otherValue = objectValue;
        assertEquals(ObjectUtils.hashCode(objectValue), ObjectUtils.hashCode(otherValue));
        assertNotSame(0, ObjectUtils.hashCode(objectArray));
        objectValue = objectArray;
        assertNotSame(0, ObjectUtils.hashCode(objectValue));
        objectArray = null;
        assertEquals(0, ObjectUtils.hashCode(objectArray));
    }

    @Test
    public void stringHashCode() {

        assertNotSame(0, ObjectUtils.hashCode(stringValue));
        String otherValue = stringValue;
        assertEquals(ObjectUtils.hashCode(stringValue), ObjectUtils.hashCode(otherValue));
        assertNotSame(0, ObjectUtils.hashCode(stringArray));
        objectValue = stringArray;
        assertNotSame(0, ObjectUtils.hashCode(objectValue));
        stringArray = null;
        assertEquals(0, ObjectUtils.hashCode(stringArray));
    }

    @Test
    public void identityToString() {

        String objectIdentityToString = ObjectUtils.identityToString(objectValue);
        println(objectIdentityToString);
        String nullIdentityToString = ObjectUtils.identityToString(null);
        assertEquals("", nullIdentityToString);
        println(nullIdentityToString);
    }

    @Test
    public void getIdentityHexString() {

        String objectIdentityHexString = ObjectUtils.getIdentityHexString(objectValue);
        println(objectIdentityHexString);
        String nullIdentityHexString = ObjectUtils.getIdentityHexString(null);
        assertEquals("0", nullIdentityHexString);
        println(nullIdentityHexString);
    }

    @Test
    public void getDisplayString() {

        String intArrayDisplayString = ObjectUtils.getDisplayString(intArray);
        assertEquals("[1,2,3,4,5]", intArrayDisplayString);
        println(intArrayDisplayString);
        String nullDisplayString = ObjectUtils.getDisplayString(null);
        assertEquals("", nullDisplayString);
    }

    @Test
    public void testToString() {

        ObjectUtils.toString(objectValue);
        objectValue = null;
        ObjectUtils.toString(objectValue);
        objectValue = byteArray;
        ObjectUtils.toString(objectValue);
        objectValue = shortArray;
        ObjectUtils.toString(objectValue);
        objectValue = charArray;
        ObjectUtils.toString(objectValue);
        objectValue = intArray;
        ObjectUtils.toString(objectValue);
        objectValue = floatArray;
        ObjectUtils.toString(objectValue);
        objectValue = longArray;
        ObjectUtils.toString(objectValue);
        objectValue = doubleArray;
        ObjectUtils.toString(objectValue);
        objectValue = booleanArray;
        ObjectUtils.toString(objectValue);
        objectValue = objectArray;
        ObjectUtils.toString(objectValue);

        ObjectUtils.toString(objectArray);
        objectArray = null;
        ObjectUtils.toString(objectArray);

        ObjectUtils.toString(byteValue);
        ObjectUtils.toString(shortValue);
        ObjectUtils.toString(charValue);
        ObjectUtils.toString(intValue);
        ObjectUtils.toString(floatValue);
        ObjectUtils.toString(longValue);
        ObjectUtils.toString(doubleValue);
        ObjectUtils.toString(booleanValue);
        ObjectUtils.toString(stringValue);

        ObjectUtils.toString(byteArray);
        byteArray = null;
        ObjectUtils.toString(byteArray);
        byteArray = new byte[0];
        ObjectUtils.toString(byteArray);

        ObjectUtils.toString(shortArray);
        shortArray = null;
        ObjectUtils.toString(shortArray);
        shortArray = new short[0];
        ObjectUtils.toString(shortArray);

        ObjectUtils.toString(charArray);
        charArray = null;
        ObjectUtils.toString(charArray);
        charArray = new char[0];
        ObjectUtils.toString(charArray);

        ObjectUtils.toString(intArray);
        intArray = null;
        ObjectUtils.toString(intArray);
        intArray = new int[0];
        ObjectUtils.toString(intArray);

        println(ObjectUtils.toString(floatArray));
        floatArray = null;
        ObjectUtils.toString(floatArray);
        floatArray = new float[0];
        ObjectUtils.toString(floatArray);

        ObjectUtils.toString(longArray);
        longArray = null;
        ObjectUtils.toString(longArray);
        longArray = new long[0];
        ObjectUtils.toString(longArray);

        ObjectUtils.toString(doubleArray);
        doubleArray = null;
        ObjectUtils.toString(doubleArray);
        doubleArray = new double[0];
        ObjectUtils.toString(doubleArray);

        ObjectUtils.toString(booleanArray);
        booleanArray = null;
        ObjectUtils.toString(booleanArray);
        booleanArray = new boolean[0];
        ObjectUtils.toString(booleanArray);

        ObjectUtils.toString(stringArray);
        stringArray = null;
        ObjectUtils.toString(stringArray);
        stringArray = new String[0];
        ObjectUtils.toString(stringArray);

    }
}
