package com.gohead.core.common;

import java.io.Serializable;

/**
 * Created by super me on 17/6/26.
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    protected long resultCode;
    protected String message;
    protected T data;

    public Result() {
    }

    public Result(long resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    public long getResultCode() {
        return resultCode;
    }

    public void setResultCode(long resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultCode=" + resultCode +
                ", message='" + message + '\'' +
                ", data={" + data.toString() + "}" +
                '}';
    }

}
