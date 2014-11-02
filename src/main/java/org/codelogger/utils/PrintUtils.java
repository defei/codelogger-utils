package org.codelogger.utils;

public class PrintUtils {

    private PrintUtils() {

    }

    /****************
     * For boolean. *
     ***************/
    public static void print(final boolean[] arg0) {

        print(ObjectUtils.toString(arg0));
    }

    public static void println(final boolean[] arg0) {

        print(arg0);
        println();
    }

    /*************
     * For byte. *
     *************/
    public static void print(final byte[] arg0) {

        print(ObjectUtils.toString(arg0));
    }

    public static void println(final byte[] arg0) {

        print(arg0);
        println();
    }

    /**************
     * For short. *
     *************/
    public static void print(final short[] arg0) {

        print(ObjectUtils.toString(arg0));
    }

    public static void println(final short[] arg0) {

        print(arg0);
        println();
    }

    /*************
     * For char. *
     *************/
    public static void print(final char[] arg0) {

        print(ObjectUtils.toString(arg0));
    }

    public static void println(char[] arg0) {

        print(arg0);
        println();
    }

    /************
     * For int. *
     ************/
    public static void print(final int[] arg0) {

        print(ObjectUtils.toString(arg0));
    }

    public static void println(final int[] arg0) {

        print(arg0);
        println();
    }

    /**************
     * For float. *
     **************/
    public static void print(final float[] arg0) {

        print(ObjectUtils.toString(arg0));
    }

    public static void println(final float[] arg0) {

        print(arg0);
        println();
    }

    /*************
     * For long. *
     *************/
    public static void print(final long[] arg0) {

        print(ObjectUtils.toString(arg0));
    }

    public static void println(final long[] arg0) {

        print(arg0);
        println();
    }

    /***************
     * For double. *
     ***************/
    public static void print(final double[] arg0) {

        print(ObjectUtils.toString(arg0));
    }

    public static void println(final double[] arg0) {

        print(arg0);
        println();
    }

    /**************
     * for Object *
     **************/

    public static void print(String format, Object... args) {

        Object[] stringArgs = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            stringArgs[i] = ObjectUtils.toString(args[i]);
        }
        print(String.format(format, stringArgs));
    }

    public static void println(String format, Object... args) {

        print(format, args);
        println();
    }

    public static void print(Object[] arg0) {

        print(ObjectUtils.toString(arg0));
    }

    public static void println(Object[] arg0) {

        print(arg0);
        println();
    }

    /**********
     * format *
     **********/

    public static void print(Object arg0) {

        System.out.print(ObjectUtils.toString(arg0));
    }

    public static void println(Object arg0) {

        System.out.println(arg0);
    }

    public static void println() {

        System.out.println();
    }
}