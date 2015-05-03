package org.codelogger.utils.exceptions;

public class EncodingException extends RuntimeException {

    private static final long serialVersionUID = -5911762713815549558L;

    public EncodingException(String message) {

        super(message);
    }

    public EncodingException(String message, Throwable e) {

        super(message, e);
    }
}