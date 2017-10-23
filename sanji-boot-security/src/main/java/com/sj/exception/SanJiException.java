package com.sj.exception;

/**
 * Created by yangrd on 2017/7/15.
 */
public class SanJiException extends RuntimeException {

    public SanJiException() {
    }

    public SanJiException(String message) {
        super(message);
    }

    public SanJiException(String message, Throwable cause) {
        super(message, cause);
    }
}
