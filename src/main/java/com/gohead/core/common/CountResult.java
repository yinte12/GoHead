package com.gohead.core.common;

import java.io.Serializable;

/**
 * Created by super on 2018/8/30.
 */
public class CountResult<T> extends Result<T>
        implements Serializable {
    private static final long serialVersionUID = 2950876595993123290L;

    private int total;
    private int page;

    public int getTotle() {
        return total;
    }

    public void setTotle(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
