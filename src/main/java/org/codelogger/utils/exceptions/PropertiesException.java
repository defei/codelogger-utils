package org.codelogger.utils.exceptions;

public class PropertiesException extends RuntimeException {

    private static final long serialVersionUID = 380479822766098413L;

    public PropertiesException(final String message) {

        super(message);
    }

    public PropertiesException(final Throwable cause) {

        super(cause);
    }

    public PropertiesException(final String message, final Throwable cause) {

        super(message, cause);
    }

}
