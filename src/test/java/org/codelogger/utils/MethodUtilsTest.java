package org.codelogger.utils;

import static org.codelogger.utils.MethodUtils.EMPTY_PARAMETER_CLASSTYPES;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;

import org.codelogger.utils.exceptions.MethodException;
import org.codelogger.utils.bean.TestBeanA;
import org.codelogger.utils.bean.TestSubClass;

import org.junit.Test;

public class MethodUtilsTest {

    @Test
    public void findPublicMethod() {

        Method method = MethodUtils.findPublicMethod(TestSubClass.class, "setName",
                new Class<?>[] { String.class });
        assertNotNull(method);
        method = MethodUtils.findPublicMethod(TestSubClass.class, "setKey",
                new Class<?>[] { String.class });
        assertNotNull(method);
        method = MethodUtils.findPublicMethod(TestSubClass.class, "NoSuchMethod",
                new Class<?>[] { String.class });
        assertNull(method);
        method = MethodUtils.findPublicMethod(TestSubClass.class, "method",
                EMPTY_PARAMETER_CLASSTYPES);
        assertNull(method);
    }

    @Test
    public void findMethod() {

        Method method = MethodUtils.findPublicMethod(TestSubClass.class, "setName",
                new Class<?>[] { String.class });
        assertNotNull(method);
        method = MethodUtils.findPublicMethod(TestSubClass.class, "setKey",
                new Class<?>[] { String.class });
        assertNotNull(method);
        method = MethodUtils.findMethod(TestSubClass.class, "NoSuchMethod",
                new Class<?>[] { String.class });
        assertNull(method);
        method = MethodUtils.findMethod(TestSubClass.class, "method", EMPTY_PARAMETER_CLASSTYPES);
        assertNotNull(method);
    }

    @Test
    public void invoke() {

        String expectedValue = "test";
        TestBeanA source = new TestBeanA();
        String methodName = "setName";
        Class<?>[] parameterTypes = { String.class };
        Object[] parameterValues = { expectedValue };
        MethodUtils.invoke(source, methodName, parameterTypes, parameterValues);
        assertEquals(expectedValue, source.getName());

        try {
            MethodUtils.invoke(source, methodName, new Class<?>[] {}, parameterValues);
        } catch (Exception e) {
            assertEquals(MethodException.class, e.getClass());
        }

        methodName = "toString";
        String value = (String) MethodUtils.invoke(source, methodName, null, parameterValues);
        assertTrue(value.contains(expectedValue));
        PrintUtils.println(value);

    }
}
