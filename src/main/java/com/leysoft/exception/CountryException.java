
package com.leysoft.exception;

public class CountryException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CountryException() {
        super();
    }

    public CountryException(String message) {
        super(message);
    }

    public CountryException(Throwable cause) {
        super(cause);
    }
}
