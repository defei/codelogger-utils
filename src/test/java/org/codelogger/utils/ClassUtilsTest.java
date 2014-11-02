package org.codelogger.utils;

import static org.codelogger.utils.PrintUtils.println;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.codelogger.utils.ClassUtils;
import org.codelogger.utils.exceptions.BeanInstantiationException;

import org.junit.Test;

public class ClassUtilsTest {

    Object objectValue = new Object();

    @Test
    public void getClassTest() {

        Class<Object> class1 = ClassUtils.getClass(objectValue);
        assertEquals(Object.class, class1);
        class1 = ClassUtils.getClass(null);
        assertEquals(null, class1);
    }

    @Test
    public void getComponentClass() {

        String[] stringArray = { "a", "b" };
        int[] intArray = { 1, 2, 3 };
        Class<?> componentClass = ClassUtils.getComponentClass(stringArray);
        assertEquals(String.class, componentClass);
        componentClass = ClassUtils.getComponentClass(intArray);
        assertEquals(int.class, componentClass);
        componentClass = ClassUtils.getComponentClass(null);
        assertEquals(null, componentClass);
        componentClass = ClassUtils.getComponentClass(new Object());
        assertEquals(null, componentClass);
    }

    @Test
    public void getWrapperClass() {

        Object[] testValues = { null, false, (byte) 'a', (short) 'a', 'a', 1, 1F, 1L, 1D };
        Class<?>[] testClass = { null, boolean.class, byte.class, short.class, char.class,
                int.class, float.class, long.class, double.class };
        Class<?>[] expectedClass = { null, Boolean.class, Byte.class, Short.class, Character.class,
                Integer.class, Float.class, Long.class, Double.class };

        for (int i = 0; i < testValues.length; i++) {
            Class<?> wrapperClass1 = ClassUtils.getWrapperClass(testValues[i]);
            Class<?> wrapperClass2 = ClassUtils.getWrapperClass(testClass[i]);
            assertEquals(expectedClass[i], wrapperClass1);
            assertEquals(expectedClass[i], wrapperClass2);
        }
    }

    @Test
    public void getPrimitiveClass() {

        Object[] testValues = { null, false, (byte) 'a', (short) 'a', 'a', 1, 1F, 1L, 1D, "a" };
        Class<?>[] testClass = { null, Boolean.class, Byte.class, Short.class, Character.class,
                Integer.class, Float.class, Long.class, Double.class, String.class };
        Class<?>[] expectedClass = { null, boolean.class, byte.class, short.class, char.class,
                int.class, float.class, long.class, double.class, String.class };

        for (int i = 0; i < testValues.length; i++) {
            Class<?> primitiveClass1 = ClassUtils.getPrimitiveClass(testValues[i]);
            Class<?> primitiveClass2 = ClassUtils.getPrimitiveClass(testClass[i]);
            assertEquals(expectedClass[i], primitiveClass1);
            assertEquals(expectedClass[i], primitiveClass2);
        }
    }

    @Test
    public void instantiateClass_noParameter() {

        Object object = ClassUtils.instantiateClass(Object.class);
        assertNotNull(object);
        try {
            ClassUtils.instantiateClass(Boolean.class);
        } catch (Exception e) {
            assertEquals(BeanInstantiationException.class, e.getClass());
        }
    }

    @Test
    public void instantiateClass_haveParameter() {

        String object = ClassUtils.instantiateClass(String.class, new Class<?>[] { String.class },
                new Object[] { "false" });
        assertNotNull(object);
        try {
            ClassUtils.instantiateClass(Long.class, new Class<?>[] { boolean.class },
                    new Object[] { false });
        } catch (Exception e) {
            assertEquals(BeanInstantiationException.class, e.getClass());
        }
    }

    @Test
    public void isDataObject() {

        boolean objectIsPrimitiveClass = ClassUtils.isDataObject(objectValue);
        assertFalse(objectIsPrimitiveClass);
        boolean intIsPrimitiveClass = ClassUtils.isDataObject(1);
        assertTrue(intIsPrimitiveClass);
        objectValue = null;
        boolean nulltIsPrimitiveClass = ClassUtils.isDataObject(objectValue);
        assertTrue(nulltIsPrimitiveClass);

        boolean objectIsPrimitiveClass1 = ClassUtils.isDataClass(Object.class);
        assertFalse(objectIsPrimitiveClass1);
        boolean intIsPrimitiveClass1 = ClassUtils.isDataClass(int.class);
        assertTrue(intIsPrimitiveClass1);
        Class<?> nullClass = null;
        boolean nulltIsPrimitiveClass1 = ClassUtils.isDataClass(nullClass);
        assertTrue(nulltIsPrimitiveClass1);

        boolean stringIsPrimitiveClass = ClassUtils.isDataClass(String.class);
        assertTrue(stringIsPrimitiveClass);
    }

    @Test
    public void getClassName() {

        String objectClassName = ClassUtils.getClassName(objectValue);
        assertEquals(Object.class.getName(), objectClassName);
        println(objectClassName);
        String nullClassName = ClassUtils.getClassName(null);
        assertEquals("null", nullClassName);
    }

    @Test
    public void getSimpleClassName() {

        String objectSimpleClassName = ClassUtils.getSimpleClassName(objectValue);
        assertEquals(Object.class.getSimpleName(), objectSimpleClassName);
        println(objectSimpleClassName);
        String nullClassName = ClassUtils.getSimpleClassName(null);
        assertEquals("null", nullClassName);
    }
}
