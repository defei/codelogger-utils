package org.codelogger.utils.exceptions;

public class FatalBeanException extends RuntimeException {

    private static final long serialVersionUID = 1352342940405060665L;

    public FatalBeanException(String msg) {

        super(msg);
    }

    public FatalBeanException(String msg, Throwable cause) {

        super(msg, cause);
    }
}
