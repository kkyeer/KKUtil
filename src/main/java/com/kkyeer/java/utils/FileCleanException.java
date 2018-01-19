package com.kkyeer.java.utils;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 0:33 2018/1/20
 * @Modified By:
 */
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
