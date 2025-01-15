package org.mohammed.cmsapi.exception;

public class MuscleBalanceAlreadyExistsException extends RuntimeException {

    public MuscleBalanceAlreadyExistsException(String message) {
        super(message);
    }

    public MuscleBalanceAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
