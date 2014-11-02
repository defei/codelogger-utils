package org.codelogger.utils.exceptions;

public class BeanInstantiationException extends RuntimeException {

    private static final long serialVersionUID = 3474107265838776222L;

    public BeanInstantiationException(Class<?> clazz, String message) {

        super(formatMessage(clazz, message));
    }

    public BeanInstantiationException(Class<?> clazz, String message, Exception ex) {

        super(formatMessage(clazz, message), ex);
    }

    private static String formatMessage(Class<?> clazz, String message) {

        return String.format("Could not instantiate bean class [%s]: %s", clazz.getName(), message);
    }
}
