package org.codelogger.utils;

public class ExceptionThrowerFactory {

    private static IllegalArgumentExceptionThrower iae = IllegalArgumentExceptionThrower
            .getInstance();

    /**
     * Return specify exception type singleton exceptionThrower.
     * 
     * @param classType
     *            the exception you want to use to thrower.
     * @return an exception thrower instance.
     */
    public static ExceptionThrower instance(Class<? extends RuntimeException> classType) {

        iae.throwIfNull(classType, "The parameter of exception type can not be null.");
        if (classType.equals(IllegalArgumentException.class)) {
            return iae;
        } else {
            iae.throwIfTrue(true, "Not fond ExceptionThrower Utils for :", classType.getClass()
                    .toString());
            return null;
        }
    }
}
