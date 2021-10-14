package ru.study.exceptions;

public class ModelException extends RuntimeException {
    private final ErrorCode code;

    public ModelException(String message, Throwable cause, ErrorCode code) {
        super(message, cause);
        this.code = code;
    }

    public ModelException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }

    public ModelException(Throwable cause, ErrorCode code) {
        super(cause);
        this.code = code;
    }

    public ErrorCode getCode() {
        return this.code;
    }

    public enum ErrorCode {
        GETTING_DATA_FROM_MODEL_ERROR
    }
}
