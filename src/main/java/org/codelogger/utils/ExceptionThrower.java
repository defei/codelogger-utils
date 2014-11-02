package org.codelogger.utils;

import java.util.Collection;
import java.util.Map;

public abstract class ExceptionThrower {

    /**
     * If condition is true, throw a RuntimeException.
     * 
     * @param condition
     * @param message
     *            The exception's message.
     * @param messageParameters
     *            The exception message's parameters.
     */
    public abstract void throwIfTrue(boolean condition, String message, Object... messageParameters);

    /**
     * If parameter obj is null, throw a RuntimeException.
     * 
     * @param obj
     * @param message
     * @param messageParameters
     */
    public abstract void throwIfNull(Object obj, String message, Object... messageParameters);

    /**
     * If parameter collection is empty or null, throw a RuntimeException.
     * 
     * @param collection
     * @param message
     * @param messageParameters
     */
    public abstract void throwIfEmptyOrNull(Collection<?> collection, String message,
            Object... messageParameters);

    /**
     * If parameter map is empty or null, throw a RuntimeException.
     * 
     * @param map
     * @param message
     * @param messageParameters
     */
    public abstract void throwIfEmptyOrNull(Map<?, ?> map, String message,
            Object... messageParameters);

    /**
     * If parameter array is empty or null, throw a RuntimeException.
     * 
     * @param array
     * @param message
     * @param messageParameters
     */
    public abstract void throwIfEmptyOrNull(Object[] array, String message,
            Object... messageParameters);

    /**
     * Create a string message by string format.
     * 
     * @param message
     * @param messageParameters
     * @return
     */
    protected String createExceptionMessage(final String message, final Object... messageParameters) {

        if (ArrayUtils.isEmpty(messageParameters)) {
            return message;
        } else {
            return String.format(message, messageParameters);
        }
    }
}
