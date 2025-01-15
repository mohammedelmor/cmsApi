package org.mohammed.cmsapi.exception;

public class MuscleBalanceDeleteException extends RuntimeException {

    public MuscleBalanceDeleteException(String message) {
        super(message);
    }

    public MuscleBalanceDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
