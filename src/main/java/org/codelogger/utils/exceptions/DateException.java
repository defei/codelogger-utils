package org.codelogger.utils.exceptions;

public class DateException extends RuntimeException {

    private static final long serialVersionUID = -5911762713815549558L;

    public DateException(String message) {

        super(message);
    }

    public DateException(String message, Throwable e) {

        super(message, e);
    }
}