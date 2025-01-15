package org.mohammed.cmsapi.exception;

public class BodyTypeUpdateException extends RuntimeException {

    public BodyTypeUpdateException(String message) {
        super(message);
    }

    public BodyTypeUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
