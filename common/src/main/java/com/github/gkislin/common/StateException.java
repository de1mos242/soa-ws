package com.github.gkislin.common;

public class StateException extends RuntimeException {

    private ExceptionType type;

    public StateException() {
    }

    public StateException(ExceptionType type, Throwable e) {
        super(e);
        this.type = type;
    }

    public StateException(String message, ExceptionType type, Throwable e) {
        super(message, e);
        this.type = type;
    }

    public ExceptionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.getDescr() + " (" + getMessage() + ")";
    }
}