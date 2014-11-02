package org.codelogger.utils;

/**
 * A useful tools to handle object, likes to string, get hash code, compare
 * objects, get class name and so on.
 * 
 * @author DengDefei
 * 
 */
public abstract class ObjectUtils {

    private static final int INITIAL_HASH = 7;

    private static final int MULTIPLIER = 31;

    private static final String EMPTY_STRING = "";

    private static final String NULL_STRING = "null";

    private static final String ARRAY_START = "[";

    private static final String ARRAY_END = "]";

    private static final String EMPTY_ARRAY = "[]";

    private static final String ARRAY_ELEMENT_SEPARATOR = ",";

    private ObjectUtils() {

    }

    /**
     * Returns if source equals target.
     * 
     * @param source
     *            any object.
     * @param target
     *            any object.
     * @return if source equals target.
     */
    public static boolean equals(Object source, Object target) {

        return JudgeUtils.equals(source, target);
    }

    /**
     * Returns given object's hash code.
     * 
     * @param object
     *            any object.
     * @return hash code of given object.
     */
    public static int hashCode(Object object) {

        if (object == null) {
            return 0;
        }
        if (object.getClass().isArray()) {
            if ((object instanceof Object[])) {
                return hashCode((Object[]) object);
            }
            if ((object instanceof boolean[])) {
                return hashCode((boolean[]) object);
            }
            if ((object instanceof byte[])) {
                return hashCode((byte[]) object);
            }
            if ((object instanceof char[])) {
                return hashCode((char[]) object);
            }
            if ((object instanceof double[])) {
                return hashCode((double[]) object);
            }
            if ((object instanceof float[])) {
                return hashCode((float[]) object);
            }
            if ((object instanceof int[])) {
                return hashCode((int[]) object);
            }
            if ((object instanceof long[])) {
                return hashCode((long[]) object);
            }
            if ((object instanceof short[])) {
                return hashCode((short[]) object);
            }
        }
        return object.hashCode();
    }

    /**
     * Returns given array hash code.
     * 
     * @param array
     *            any object array.
     * @return hash code of given array by elements.
     */
    public static int hashCode(Object[] array) {

        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        int arraySize = array.length;
        for (int i = 0; i < arraySize; i++) {
            hash = MULTIPLIER * hash + hashCode(array[i]);
        }
        return hash;
    }

    /**
     * Returns given array hash code.
     * 
     * @param array
     *            any object array.
     * @return hash code of given array by elements.
     */
    public static int hashCode(boolean[] array) {

        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        int arraySize = array.length;
        for (int i = 0; i < arraySize; i++) {
            hash = MULTIPLIER * hash + hashCode(array[i]);
        }
        return hash;
    }

    /**
     * Returns given array hash code.
     * 
     * @param array
     *            any object array.
     * @return hash code of given array by elements.
     */
    public static int hashCode(byte[] array) {

        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        int arraySize = array.length;
        for (int i = 0; i < arraySize; i++) {
            hash = MULTIPLIER * hash + array[i];
        }
        return hash;
    }

    /**
     * Returns given array hash code.
     * 
     * @param array
     *            any object array.
     * @return hash code of given array by elements.
     */
    public static int hashCode(char[] array) {

        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        int arraySize = array.length;
        for (int i = 0; i < arraySize; i++) {
            hash = MULTIPLIER * hash + array[i];
        }
        return hash;
    }

    /**
     * Returns given array hash code.
     * 
     * @param array
     *            any object array.
     * @return hash code of given array by elements.
     */
    public static int hashCode(double[] array) {

        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        int arraySize = array.length;
        for (int i = 0; i < arraySize; i++) {
            hash = MULTIPLIER * hash + hashCode(array[i]);
        }
        return hash;
    }

    /**
     * Returns given array hash code.
     * 
     * @param array
     *            any object array.
     * @return hash code of given array by elements.
     */
    public static int hashCode(float[] array) {

        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        int arraySize = array.length;
        for (int i = 0; i < arraySize; i++) {
            hash = MULTIPLIER * hash + hashCode(array[i]);
        }
        return hash;
    }

    /**
     * Returns given array hash code.
     * 
     * @param array
     *            any object array.
     * @return hash code of given array by elements.
     */
    public static int hashCode(int[] array) {

        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        int arraySize = array.length;
        for (int i = 0; i < arraySize; i++) {
            hash = MULTIPLIER * hash + array[i];
        }
        return hash;
    }

    /**
     * Returns given array hash code.
     * 
     * @param array
     *            any object array.
     * @return hash code of given array by elements.
     */
    public static int hashCode(long[] array) {

        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        int arraySize = array.length;
        for (int i = 0; i < arraySize; i++) {
            hash = MULTIPLIER * hash + hashCode(array[i]);
        }
        return hash;
    }

    /**
     * Returns given array hash code.
     * 
     * @param array
     *            any object array.
     * @return hash code of given array by elements.
     */
    public static int hashCode(short[] array) {

        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        int arraySize = array.length;
        for (int i = 0; i < arraySize; i++) {
            hash = MULTIPLIER * hash + array[i];
        }
        return hash;
    }

    /**
     * Returns given boolean value hash code.
     * 
     * @param bool
     *            any boolean value.
     * @return hash code of given boolean value.
     */
    public static int hashCode(boolean bool) {

        return bool ? 1231 : 1237;
    }

    /**
     * Returns given double value hash code.
     * 
     * @param dbl
     *            any double value.
     * @return hash code of given double value.
     */
    public static int hashCode(double dbl) {

        long bits = Double.doubleToLongBits(dbl);
        return hashCode(bits);
    }

    /**
     * Returns given float value hash code.
     * 
     * @param flt
     *            any float value.
     * @return hash code of given float value.
     */
    public static int hashCode(float flt) {

        return Float.floatToIntBits(flt);
    }

    /**
     * Returns given long value hash code.
     * 
     * @param lng
     *            any long value.
     * @return hash code of given long value.
     */
    public static int hashCode(long lng) {

        return (int) (lng ^ lng >>> 32);
    }

    /**
     * Returns given object class name and string identity hash code.<br/>
     * eg: java.lang.Object@36d251a3
     * 
     * @param object
     *            any object.
     * @return class name and string identity hash code of given object.
     */
    public static String identityToString(Object object) {

        if (object == null) {
            return EMPTY_STRING;
        }
        return String.format("%s@%s", object.getClass().getName(), getIdentityHexString(object));
    }

    /**
     * Returns given object string identity hash code.<br/>
     * eg: 36d251a3
     * 
     * @param object
     *            any object.
     * @return string identity hash code of given object.
     */
    public static String getIdentityHexString(Object object) {

        return Integer.toHexString(System.identityHashCode(object));
    }

    /**
     * Returns display string of given object.<br/>
     * eg: int[] array = {1,2,3} ==> "[1,2,3]".<br>
     * eg: "example" ==> "example".<br>
     * eg: object ==> "java.lang.Object@1314520".
     * 
     * @param object
     *            any object.
     * @return display string of given object.
     */
    public static String getDisplayString(Object object) {

        if (object == null) {
            return EMPTY_STRING;
        }
        return toString(object);
    }

    /**
     * Returns display string of given object.<br/>
     * eg: int[] array = {1,2,3} ==> "[1,2,3]".<br>
     * eg: "example" ==> "example".<br>
     * eg: object ==> "java.lang.Object@1314520".
     * 
     * @param object
     *            any object.
     * @return display string of given object.
     */
    public static String toString(Object object) {

        if (object == null) {
            return NULL_STRING;
        }
        if ((object instanceof String)) {
            return (String) object;
        }
        if ((object instanceof Object[])) {
            return toString((Object[]) object);
        }
        if ((object instanceof boolean[])) {
            return toString((boolean[]) object);
        }
        if ((object instanceof byte[])) {
            return toString((byte[]) object);
        }
        if ((object instanceof char[])) {
            return toString((char[]) object);
        }
        if ((object instanceof double[])) {
            return toString((double[]) object);
        }
        if ((object instanceof float[])) {
            return toString((float[]) object);
        }
        if ((object instanceof int[])) {
            return toString((int[]) object);
        }
        if ((object instanceof long[])) {
            return toString((long[]) object);
        }
        if ((object instanceof short[])) {
            return toString((short[]) object);
        }
        String str = object.toString();
        return str != null ? str : EMPTY_STRING;
    }

    /**
     * Returns display string of given array.<br/>
     * eg: int[] array = {1,2,3} ==> "[1,2,3]".<br>
     * 
     * @param array
     *            any array.
     * @return display string of given array.
     */
    public static String toString(Object[] array) {

        if (array == null) {
            return NULL_STRING;
        }
        int length = array.length;
        if (length == 0) {
            return EMPTY_ARRAY;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                builder.append(ARRAY_START);
            } else {
                builder.append(ARRAY_ELEMENT_SEPARATOR);
            }
            builder.append(toString(array[i]));
        }
        builder.append(ARRAY_END);
        return builder.toString();
    }

    /**
     * Returns display string of given boolean array.<br/>
     * eg: boolean[] array = {true,false,true} ==> "[true,false,true]".<br>
     * 
     * @param array
     *            any array.
     * @return display string of given array.
     */
    public static String toString(boolean[] array) {

        if (array == null) {
            return NULL_STRING;
        }
        int length = array.length;
        if (length == 0) {
            return EMPTY_ARRAY;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                builder.append(ARRAY_START);
            } else {
                builder.append(ARRAY_ELEMENT_SEPARATOR);
            }

            builder.append(array[i]);
        }
        builder.append(ARRAY_END);
        return builder.toString();
    }

    /**
     * Returns display string of given byte array.<br/>
     * eg: byte[] array = {'a','b','c'} ==> "[97,98,99]".<br>
     * 
     * @param array
     *            any array.
     * @return display string of given array.
     */
    public static String toString(byte[] array) {

        if (array == null) {
            return NULL_STRING;
        }
        int length = array.length;
        if (length == 0) {
            return EMPTY_ARRAY;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                builder.append(ARRAY_START);
            } else {
                builder.append(ARRAY_ELEMENT_SEPARATOR);
            }
            builder.append(array[i]);
        }
        builder.append(ARRAY_END);
        return builder.toString();
    }

    /**
     * Returns display string of given char array.<br/>
     * eg: char[] array = {'a','b','c'} ==> "['a','b','c']".<br>
     * 
     * @param array
     *            any array.
     * @return display string of given array.
     */
    public static String toString(char[] array) {

        if (array == null) {
            return NULL_STRING;
        }
        int length = array.length;
        if (length == 0) {
            return EMPTY_ARRAY;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                builder.append(ARRAY_START);
            } else {
                builder.append(ARRAY_ELEMENT_SEPARATOR);
            }
            builder.append("'").append(array[i]).append("'");
        }
        builder.append(ARRAY_END);
        return builder.toString();
    }

    /**
     * Returns display string of given double array.<br/>
     * eg: double[] array = {1.1d,2.1d,3.1d} ==> "[1.1,2.1,3.1]".<br>
     * 
     * @param array
     *            any array.
     * @return display string of given array.
     */
    public static String toString(double[] array) {

        if (array == null) {
            return NULL_STRING;
        }
        int length = array.length;
        if (length == 0) {
            return EMPTY_ARRAY;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                builder.append(ARRAY_START);
            } else {
                builder.append(ARRAY_ELEMENT_SEPARATOR);
            }

            builder.append(array[i]);
        }
        builder.append(ARRAY_END);
        return builder.toString();
    }

    /**
     * Returns display string of given float array.<br/>
     * eg: float[] array = {1.1f,2.1f,3.1f} ==> "[1.1,2.1,3.1]".<br>
     * 
     * @param array
     *            any array.
     * @return display string of given array.
     */
    public static String toString(float[] array) {

        if (array == null) {
            return NULL_STRING;
        }
        int length = array.length;
        if (length == 0) {
            return EMPTY_ARRAY;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                builder.append(ARRAY_START);
            } else {
                builder.append(ARRAY_ELEMENT_SEPARATOR);
            }

            builder.append(array[i]);
        }
        builder.append(ARRAY_END);
        return builder.toString();
    }

    /**
     * Returns display string of given int array.<br/>
     * eg: int[] array = {1,2,3} ==> "[1,2,3]".<br>
     * 
     * @param array
     *            any array.
     * @return display string of given array.
     */
    public static String toString(int[] array) {

        if (array == null) {
            return NULL_STRING;
        }
        int length = array.length;
        if (length == 0) {
            return EMPTY_ARRAY;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                builder.append(ARRAY_START);
            } else {
                builder.append(ARRAY_ELEMENT_SEPARATOR);
            }
            builder.append(array[i]);
        }
        builder.append(ARRAY_END);
        return builder.toString();
    }

    /**
     * Returns display string of given long array.<br/>
     * eg: long[] array = {1L,2L,3L} ==> "[1,2,3]".<br>
     * 
     * @param array
     *            any array.
     * @return display string of given array.
     */
    public static String toString(long[] array) {

        if (array == null) {
            return NULL_STRING;
        }
        int length = array.length;
        if (length == 0) {
            return EMPTY_ARRAY;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                builder.append(ARRAY_START);
            } else {
                builder.append(ARRAY_ELEMENT_SEPARATOR);
            }
            builder.append(array[i]);
        }
        builder.append(ARRAY_END);
        return builder.toString();
    }

    /**
     * Returns display string of given short array.<br/>
     * eg: short[] array = {'a','b','c'} ==> "[97,98,99]".<br>
     * 
     * @param array
     *            any array.
     * @return display string of given array.
     */
    public static String toString(short[] array) {

        if (array == null) {
            return NULL_STRING;
        }
        int length = array.length;
        if (length == 0) {
            return EMPTY_ARRAY;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                builder.append(ARRAY_START);
            } else {
                builder.append(ARRAY_ELEMENT_SEPARATOR);
            }
            builder.append(array[i]);
        }
        builder.append(ARRAY_END);
        return builder.toString();
    }
}