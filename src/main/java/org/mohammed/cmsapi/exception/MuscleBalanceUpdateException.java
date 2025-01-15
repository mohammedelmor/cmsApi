package org.mohammed.cmsapi.exception;

public class MuscleBalanceUpdateException extends RuntimeException {

    public MuscleBalanceUpdateException(String message) {
        super(message);
    }

    public MuscleBalanceUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
