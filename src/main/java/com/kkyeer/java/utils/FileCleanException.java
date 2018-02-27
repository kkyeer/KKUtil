package com.kkyeer.java.utils;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 0:33 2018/1/20
 * @Modified By:
 */
class FileCleanException extends Exception {
    FileCleanException() {
    }

    FileCleanException(String message) {
        super(message);
    }

    FileCleanException(String message, Throwable cause) {
        super(message, cause);
    }

    FileCleanException(Throwable cause) {
        super(cause);
    }

    FileCleanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
