package com.sj.exception;

/**
 * Created by sunxyz on 2017/3/13.
 */
public class SanJiException extends RuntimeException {

    private String title;

    public SanJiException(String message) {
        super(message);
    }

    public SanJiException(String title, String message) {
        this(message);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
