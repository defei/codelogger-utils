package org.codelogger.utils;

import java.lang.reflect.Method;

import org.codelogger.utils.exceptions.MethodException;

/**
 * A useful tools to handle methods, like find public/all method, invoke methods
 * and so on...
 * 
 * @author DengDefei
 * 
 */
public class MethodUtils {

    private static final String INVOKE_METHOD_FAILED = "Invoke given method failed.";

    public static final Class<?>[] EMPTY_PARAMETER_CLASSTYPES = {};

    public static final Object[] EMPTY_PARAMETER_VALUES = {};

    private MethodUtils() {

    }

    /**
     * Returns a public Method object that reflects the specified public member
     * method of the class or interface represented by given class.<br>
     * Returns null if not find.
     * 
     * @param clazz
     *            the method belong to.
     * @param methodName
     *            the name of the method which you want to get.
     * @param parameterTypes
     *            the parameter types of the method which you want to get.
     * @return a public Method object that reflects the specified public member
     *         method of the class or interface represented by given class.
     */
    public static Method findPublicMethod(Class<?> clazz, String methodName,
            Class<?>[] parameterTypes) {

        try {
            return clazz.getMethod(methodName, parameterTypes);
        } catch (Exception e) {
            Class<?> superclass = clazz.getSuperclass();
            if (superclass != null) {
                return findPublicMethod(superclass, methodName, parameterTypes);
            }
        }
        return null;
    }

    /**
     * Returns a Method object that reflects the specified declared method of
     * the class or interface represented by given class.<br>
     * Returns null if not find.
     * 
     * @param clazz
     *            the method belong to.
     * @param methodName
     *            the name of the method which you want to get.
     * @param parameterTypes
     *            the parameter types of the method which you want to get.
     * @return a Method object that reflects the specified public member method
     *         of the class or interface represented by given class.
     */
    public static Method findMethod(Class<?> clazz, String methodName, Class<?>[] parameterTypes) {

        try {
            return clazz.getDeclaredMethod(methodName, parameterTypes);
        } catch (Exception e) {
            Class<?> superclass = clazz.getSuperclass();
            if (superclass != null) {
                return findMethod(superclass, methodName, parameterTypes);
            }
        }
        return null;
    }

    /**
     * Invokes method which name equals given method name and parameter types
     * equals given parameter types on the given source with the given
     * parameters.
     * 
     * @param source
     *            the object which you want to handle.
     * @param methodName
     *            the name of the method which you want to call.
     * @param parameterTypes
     *            the parameter types of the method which you want to call.
     * @param parameterValues
     *            the parameter values of the method which you want to call.
     * @return an object which is invoked method returns.
     * @throws MethodException
     */
    public static Object invoke(Object source, String methodName, Class<?>[] parameterTypes,
            Object[] parameterValues) throws MethodException {

        Class<? extends Object> clazz = source.getClass();
        Method method;
        if (ArrayUtils.isEmpty(parameterTypes)) {
            method = findMethod(clazz, methodName, EMPTY_PARAMETER_CLASSTYPES);
            return invoke(source, method, EMPTY_PARAMETER_VALUES);
        }
        method = findMethod(clazz, methodName, parameterTypes);
        return invoke(source, method, parameterValues);
    }

    /**
     * Invokes given method on the given source object with the specified
     * parameters.
     * 
     * @param source
     *            the object which you want to handle.
     * @param method
     *            the method which you want to call.
     * @param parameterValues
     *            the parameter values of the method which you want to call.
     * @return an object which is invoked method returns.
     * @throws MethodException
     */
    public static Object invoke(Object source, Method method, Object[] parameterValues)
            throws MethodException {

        try {
            return method.invoke(source, parameterValues);
        } catch (Exception e) {
            throw new MethodException(INVOKE_METHOD_FAILED, e);
        }
    }
}
