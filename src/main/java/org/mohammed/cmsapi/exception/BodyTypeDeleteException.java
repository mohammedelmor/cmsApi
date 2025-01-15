package org.mohammed.cmsapi.exception;

public class BodyTypeDeleteException extends RuntimeException {

    public BodyTypeDeleteException(String message) {
        super(message);
    }

    public BodyTypeDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
