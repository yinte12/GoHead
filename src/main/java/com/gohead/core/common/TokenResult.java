package com.gohead.core.common;

import java.io.Serializable;

/**
 * Created by super on 2018/8/17.
 */
public class TokenResult<T> extends Result<T>
        implements Serializable {
    private static final long serialVersionUID = 4026018981957568081L;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
