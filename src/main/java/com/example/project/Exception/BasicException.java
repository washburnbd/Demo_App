package com.example.project.Exception;

public class BasicException {

    public String message;
    public String cause;
    public String stackTrace;

    public BasicException (String message, String cause, String stackTrace) {
        this.message = message;
        this.cause = cause;
        this.stackTrace = stackTrace;
    }

    public String getMessage() {
        return message;
    }

    public String getCause() {
        return cause;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    @Override
    public String toString() {
        return "BasicException: {" +
                "message='" + message + '\'' +
                ", cause='" + cause + '\'' +
                ", stackTrace='" + stackTrace + '\'' +
                '}';
    }
}
