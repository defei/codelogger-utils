package org.codelogger.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {

    private ExceptionUtils() {

    }

    public static ExceptionThrower iae = ExceptionThrowerFactory
            .instance(IllegalArgumentException.class);

    public static String createNestedMessagesFromThrowable(Throwable e) {

        String message = "";
        for (Throwable t = e; t != null; t = t.getCause()) {
            message = message + "-------- " + t.getClass().getName() + " --------\n";
            if (t.getMessage() != null)
                message = message + t.getMessage() + "\n\n";
            else {
                message = message + "No message available for this thrown object.\n\n";
            }
        }
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        e.printStackTrace(writer);
        writer.close();
        message = message + "-------- Stack Trace --------\n" + sw.getBuffer();
        return message;
    }

}
