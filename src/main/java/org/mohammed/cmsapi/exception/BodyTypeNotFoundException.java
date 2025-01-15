package org.mohammed.cmsapi.exception;

public class BodyTypeNotFoundException extends RuntimeException {
    public BodyTypeNotFoundException(String message) {
        super(message);
    }

    public BodyTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
