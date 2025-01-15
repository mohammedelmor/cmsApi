package org.mohammed.cmsapi.exception;

public class BucketAlreadyExistsException extends RuntimeException {

    public BucketAlreadyExistsException(String message) {
        super(message);
    }

    public BucketAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
