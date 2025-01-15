package org.mohammed.cmsapi.exception;

public class BodyTypeAlreadyExistsException extends RuntimeException {

    public BodyTypeAlreadyExistsException(String message) {
        super(message);
    }

    public BodyTypeAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
