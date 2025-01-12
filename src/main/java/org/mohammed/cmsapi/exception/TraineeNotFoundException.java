package org.mohammed.cmsapi.exception;

public class TraineeNotFoundException extends RuntimeException {
    public TraineeNotFoundException(String message) {
        super(message);
    }

    public TraineeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
