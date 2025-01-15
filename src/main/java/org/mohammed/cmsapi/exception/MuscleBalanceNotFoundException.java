package org.mohammed.cmsapi.exception;

public class MuscleBalanceNotFoundException extends RuntimeException {
    public MuscleBalanceNotFoundException(String message) {
        super(message);
    }

    public MuscleBalanceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
