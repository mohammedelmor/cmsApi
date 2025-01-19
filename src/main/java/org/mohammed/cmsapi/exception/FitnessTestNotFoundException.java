package org.mohammed.cmsapi.exception;

public class FitnessTestNotFoundException extends RuntimeException {
    public FitnessTestNotFoundException(String message) {
        super(message);
    }

    public FitnessTestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
