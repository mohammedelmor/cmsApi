package org.mohammed.cmsapi.exception;

public class BucketNotFoundException extends RuntimeException {

    public BucketNotFoundException(String message) {
        super(message);
    }

    public BucketNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
