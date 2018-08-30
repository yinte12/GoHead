package com.gohead.core.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by super on 2018/8/30.
 */
public class CountResult<T> implements Serializable {
    private static final long serialVersionUID = 2950876595993123290L;

    protected long resultCode;
    protected String message;
    private int total;
    private int page;
    private List<?> data;

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }


    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
