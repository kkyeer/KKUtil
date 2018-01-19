package com.kkyeer.java.utils;

public class FileCleanException extends Exception {
    public FileCleanException() {
    }

    FileCleanException(String message) {
        super(message);
    }

    public FileCleanException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileCleanException(Throwable cause) {
        super(cause);
    }

    public FileCleanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
