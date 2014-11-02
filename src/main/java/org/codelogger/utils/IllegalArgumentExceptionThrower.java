package org.codelogger.utils;

import java.util.Collection;
import java.util.Map;

public class IllegalArgumentExceptionThrower extends ExceptionThrower {

    private static IllegalArgumentExceptionThrower illegalArgumentExceptionThrower;

    private IllegalArgumentExceptionThrower() {

    }

    /**
     * Return the IllegalargumentExceptionThrower singleton instance.
     * 
     * @return an IllegalArgumentExceptionThrower instance.
     */
    public static synchronized IllegalArgumentExceptionThrower getInstance() {

        if (illegalArgumentExceptionThrower == null) {
            illegalArgumentExceptionThrower = new IllegalArgumentExceptionThrower();
        }
        return illegalArgumentExceptionThrower;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see ExceptionThrower#throwIfTrue(boolean,
     *      java.lang.String, java.lang.Object[])
     */
    @Override
    public void throwIfTrue(final boolean condition, final String message,
            final Object... messageParameters) {

        if (condition) {
            throw createIllegalArgumentException(message, messageParameters);
        }
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see ExceptionThrower#throwIfNull(java.lang.Object,
     *      java.lang.String, java.lang.Object[])
     */
    @Override
    public void throwIfNull(final Object obj, final String message,
            final Object... messageParameters) throws IllegalArgumentException {

        if (obj == null) {
            throw createIllegalArgumentException(message, messageParameters);
        }
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see ExceptionThrower#throwIfEmptyOrNull(java.util.Collection,
     *      java.lang.String, java.lang.Object[])
     */
    @Override
    public void throwIfEmptyOrNull(final Collection<?> collection, final String message,
            final Object... messageParameters) {

        if (CollectionUtils.isEmpty(collection)) {
            throw createIllegalArgumentException(message, messageParameters);
        }
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see ExceptionThrower#throwIfEmptyOrNull(java.util.Map,
     *      java.lang.String, java.lang.Object[])
     */
    @Override
    public void throwIfEmptyOrNull(final Map<?, ?> map, final String message,
            final Object... messageParameters) {

        if (MapUtils.isEmpty(map)) {
            throw createIllegalArgumentException(message, messageParameters);
        }
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see ExceptionThrower#throwIfEmptyOrNull(java.lang.Object[],
     *      java.lang.String, java.lang.Object[])
     */
    @Override
    public void throwIfEmptyOrNull(final Object[] array, final String message,
            final Object... messageParameters) {

        if (ArrayUtils.isEmpty(array)) {
            throw createIllegalArgumentException(message, messageParameters);
        }
    }

    private IllegalArgumentException createIllegalArgumentException(final String message,
            final Object... messageParameters) {

        return new IllegalArgumentException(createExceptionMessage(message, messageParameters));
    }
}
