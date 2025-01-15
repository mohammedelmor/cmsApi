package org.mohammed.cmsapi.exception;

public class BodyTypeCreationException extends RuntimeException {

    public BodyTypeCreationException(String message) {
        super(message);
    }

    public BodyTypeCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
