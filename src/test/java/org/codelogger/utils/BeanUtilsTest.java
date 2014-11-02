package org.codelogger.utils;

import static org.codelogger.utils.PrintUtils.println;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codelogger.utils.exceptions.BeanInstantiationException;
import org.codelogger.utils.bean.TestBeanA;
import org.codelogger.utils.bean.TestBeanB;
import org.codelogger.utils.bean.TestInterface;
import org.codelogger.utils.bean.TestInterfaceImpl;
import org.codelogger.utils.bean.TestNoDefaultConstructor;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class BeanUtilsTest {

    @Test
    public void cloneTest_SourceIsObject() {

        String exceptedValue1 = "testName";
        String exceptedValue2 = "testAccount";
        String exceptedValue3 = "testSort";
        TestBeanA source = new TestBeanA(exceptedValue3, exceptedValue3, 1L);
        source.setName(exceptedValue1);
        source.setAccount(exceptedValue2);
        TestBeanA clonedValue = BeanUtils.clone(source);
        assertNotSame(source, clonedValue);
        assertEquals(exceptedValue1, clonedValue.getName());
        assertEquals(exceptedValue2, clonedValue.getAccount());
        assertNotSame(-1, clonedValue.toString().indexOf(exceptedValue3));
        println(clonedValue);

        Date date = DateUtils.getDateFromString("2012-12-21 00:00:00");
        Date clonedDate = BeanUtils.clone(date);
        assertEquals(date, clonedDate);
    }

    @Test
    public void cloneTest_SourceIsMap() {

        String expectedValue = "a";
        Map<String, String> testMap = Maps.newHashMap();
        testMap.put(expectedValue, expectedValue);
        Map<String, String> clonedMap = BeanUtils.clone(testMap);
        assertEquals(1, clonedMap.size());
        assertEquals(expectedValue, clonedMap.get(expectedValue));
    }

    @Test
    public void cloneTest_SourceIsCollection() {

        String expectedValue1 = "a";
        String expectedValue2 = "b";
        List<String> testMap = Lists.newArrayList();
        testMap.add(expectedValue1);
        testMap.add(expectedValue2);
        List<String> clonedMap = BeanUtils.clone(testMap);
        assertEquals(2, clonedMap.size());
        assertEquals(expectedValue1, clonedMap.get(0));
        assertEquals(expectedValue2, clonedMap.get(1));
    }

    @Test
    public void clone_SourceIsInterFace() {

        String expectedName = "sexafei";
        TestInterface source = new TestInterfaceImpl(expectedName);
        TestInterface clonedValue = BeanUtils.clone(source);
        assertNotSame(source, clonedValue);
        assertNotSame(-1, clonedValue.hello().indexOf(expectedName));
    }

    @Test
    public void clone_SourceNoDefaultConstructor() {

        String expectedName = "sexafei";
        TestNoDefaultConstructor source = new TestNoDefaultConstructor(expectedName);
        try {
            BeanUtils.clone(source);
        } catch (Exception e) {
            assertEquals(BeanInstantiationException.class, e.getClass());
        }
    }

    @Test
    public void clone_8PrimitiveTypeAndWrapperType() {

        boolean booleanValue = true;
        Boolean clonedBooleanValue = BeanUtils.clone(booleanValue);
        assertEquals(booleanValue, clonedBooleanValue.booleanValue());
        Boolean booleanValue1 = true;
        Boolean clonedBooleanValue1 = BeanUtils.clone(booleanValue1);
        assertEquals(booleanValue1, clonedBooleanValue1);

        byte byteValue = 'a';
        Byte clonedByteValue = BeanUtils.clone(byteValue);
        assertEquals(byteValue, clonedByteValue.byteValue());

        short shortValue = 'a';
        Short clonedShortValue = BeanUtils.clone(shortValue);
        assertEquals(shortValue, clonedShortValue.shortValue());

        char charValue = 'a';
        Character clonedCharValue = BeanUtils.clone(charValue);
        assertEquals(charValue, clonedCharValue.charValue());

        int intValue = 1;
        Integer clonedIntValue = BeanUtils.clone(intValue);
        assertEquals(intValue, clonedIntValue.intValue());

        float floatValue = 1F;
        Float clonedfloatValue = BeanUtils.clone(floatValue);
        assertTrue(floatValue == clonedfloatValue.floatValue());

        long longValue = 1;
        Long clonedlongValue = BeanUtils.clone(longValue);
        assertEquals(longValue, clonedlongValue.longValue());

        double doubleValue = 1;
        Double cloneddoubleValue = BeanUtils.clone(doubleValue);
        assertTrue(doubleValue == cloneddoubleValue.doubleValue());

        String stringValue = "Test";
        String clonedStringValue = BeanUtils.clone(stringValue);
        assertEquals(stringValue, clonedStringValue);
    }

    @Test
    public void clone_8PrimitiveTypeArrayAndWrapperType() {

        boolean[] booleanValue = { true, false };
        boolean[] clonedBooleanValue = BeanUtils.clone(booleanValue);
        assertEquals(booleanValue[0], clonedBooleanValue[0]);

        byte[] byteValue = { 'a', 'b' };
        byte[] clonedByteValue = BeanUtils.clone(byteValue);
        assertEquals(byteValue[0], clonedByteValue[0]);

        short[] shortValue = { 'a', 'b' };
        short[] clonedShortValue = BeanUtils.clone(shortValue);
        assertEquals(shortValue[0], clonedShortValue[0]);

        char[] charValue = { 'a', 'b' };
        char[] clonedcharValue = BeanUtils.clone(charValue);
        assertEquals(charValue[0], clonedcharValue[0]);

        int[] intValue = { 1, 2 };
        int[] clonedIntValue = BeanUtils.clone(intValue);
        assertEquals(intValue[0], clonedIntValue[0]);

        float[] floatValue = { 1F, 2F };
        float[] clonedfloatValue = BeanUtils.clone(floatValue);
        assertTrue(floatValue[0] == clonedfloatValue[0]);

        long[] longValue = { 1, 2 };
        long[] clonedlongValue = BeanUtils.clone(longValue);
        assertEquals(longValue[0], clonedlongValue[0]);

        double[] doubleValue = { 1, 2 };
        double[] cloneddoubleValue = BeanUtils.clone(doubleValue);
        assertTrue(doubleValue[0] == cloneddoubleValue[0]);

        String[] stringValue = { "aaa", "bbb" };
        String[] clonedStringValue = BeanUtils.clone(stringValue);
        assertEquals(stringValue[0], clonedStringValue[0]);
    }

    @Test
    public void copyProperties_sameType() {

        String exceptedValue1 = "testName";
        String exceptedValue2 = "testAccount";
        String exceptedValue3 = "testSort";
        TestBeanA source = new TestBeanA(exceptedValue3, exceptedValue3, 1L);
        source.setName(exceptedValue1);
        source.setAccount(exceptedValue2);
        TestBeanA destination = new TestBeanA();
        println(destination);
        BeanUtils.copyProperties(source, destination);
        assertEquals(exceptedValue1, destination.getName());
        assertEquals(exceptedValue2, destination.getAccount());
        assertNotSame(-1, destination.toString().indexOf(exceptedValue3));
        println(destination);
    }

    @Test
    public void copyProperties_differentType() {

        String exceptedValue1 = "testName";
        String exceptedValue2 = "testAccount";
        String exceptedValue3 = "testSort";
        TestBeanA source = new TestBeanA(exceptedValue3, exceptedValue3, 1L);
        source.setName(exceptedValue1);
        source.setAccount(exceptedValue2);
        TestBeanB destination = new TestBeanB();
        println(destination);
        BeanUtils.copyProperties(source, destination);
        assertEquals(exceptedValue1, destination.getName());
        assertEquals(exceptedValue2, destination.getAccount());
        assertNotSame(-1, destination.toString().indexOf(exceptedValue3));
        println(destination);
    }

    @Test
    public void newInstance_onlyByClass() {

        Class<String> clazz = String.class;
        String instance = BeanUtils.newInstance(clazz);
        assertNotNull(instance);
        try {
            clazz = null;
            BeanUtils.newInstance(clazz);
        } catch (Exception e) {
            assertEquals(NullPointerException.class, e.getClass());
        }
        try {
            Class<?> clazz1 = Map.class;
            BeanUtils.newInstance(clazz1);
        } catch (Exception e) {
            assertEquals(BeanInstantiationException.class, e.getClass());
        }
    }

    @Test
    public void newInstance_giveParameters() {

        String expectedValue = "test";
        Class<String> clazz = String.class;
        Class<?>[] parameterTypes = {};
        Object[] parameterValues = {};
        String instance = BeanUtils.newInstance(clazz, parameterTypes, parameterValues);
        assertNotNull(instance);
        instance = BeanUtils.newInstance(clazz, String.class, expectedValue);
        assertEquals(expectedValue, instance);
        parameterTypes = new Class<?>[] { String.class, String.class, Long.class };
        parameterValues = new Object[] { expectedValue, expectedValue, 1L };
        Class<TestBeanA> testBeanAClass = TestBeanA.class;
        TestBeanA testBeanA = BeanUtils
                .newInstance(testBeanAClass, parameterTypes, parameterValues);
        assertNotNull(testBeanA);
        assertTrue(testBeanA.toString().contains(expectedValue));
        try {
            clazz = null;
            BeanUtils.newInstance(clazz);
        } catch (Exception e) {
            assertEquals(NullPointerException.class, e.getClass());
        }
    }
}
