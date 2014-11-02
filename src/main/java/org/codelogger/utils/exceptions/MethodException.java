package org.codelogger.utils.exceptions;

public class MethodException extends RuntimeException {

    private static final long serialVersionUID = 5629181447383120510L;

    public MethodException(String msg) {

        super(msg);
    }

    public MethodException(String msg, Throwable cause) {

        super(msg, cause);
    }
}
