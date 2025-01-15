package org.mohammed.cmsapi.exception;

public class MuscleBalanceCreationException extends RuntimeException {

    public MuscleBalanceCreationException(String message) {
        super(message);
    }

    public MuscleBalanceCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
