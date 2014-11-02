package org.codelogger.utils.exceptions;

public class HttpException extends RuntimeException {

    private static final long serialVersionUID = 8368845127235795357L;

    public HttpException(final String message) {

        super(message);
    }

    public HttpException(final Throwable cause) {

        super(cause);
    }

    public HttpException(final String message, final Throwable cause) {

        super(message, cause);
    }
}
