package com.sj.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.function.Supplier;

/**
 * Created by sunxyz on 2017/3/14.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    private static final String SUCCESS_MSG = "操作成功";
    private static final String ERROR_MSG = "操作失败";
    public static final Result<String> OK = new Result<>();
    public static final Result<String> ERROR = new Result<>();

    @Enumerated(EnumType.STRING)
    private Status status;

    private String msg;

    private T content;

    static {
        ERROR.setMsg(ERROR_MSG);
        ERROR.setStatus(Status.ERROR);
    }

    {
        status = Status.SUCCESS;
        msg = SUCCESS_MSG;
    }

    private Result() {

    }

    private Result(T content) {
        this.content = content;
    }

    public static Result<String> error() {
        return ERROR;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> httpResponse = new Result<>();
        httpResponse.setStatus(Status.ERROR);
        httpResponse.setMsg(msg);
        return httpResponse;
    }

    public static Result<String> ok() {
        return OK;
    }

    public static <T> Result<T> ok(T content) {
        return new Result<T>(content);
    }

    public static <T> Result<T> ok(String msg, T content) {
        Result<T> httpResponse = new Result<T>(content);
        httpResponse.setMsg(msg);
        return httpResponse;
    }

    public Result<T> orGetErrorMsg(Supplier<String> message) {
        return this.orGetErrorMsg(message.get());
    }

    public Result<T> orGetErrorMsg(String message) {
        return this.status.equals(Status.ERROR) ? error(message) : this;
    }

    public Result<T> orGetSuccessMsg(Supplier<String> message) {
        return this.orGetSuccessMsg(message.get());
    }

    public Result<T> orGetSuccessMsg(String message) {
        return this.status.equals(Status.SUCCESS) ? ok(message, this.getContent()) : this;
    }

    public Status getStatus() {
        return status;
    }

    public Result setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getContent() {
        return content;
    }

    public Result setContent(T content) {
        this.content = content;
        return this;
    }

    public  enum Status {
        SUCCESS, ERROR ,KICKOUT
    }

}
