package com.gohead.core.common;

/**
 * Created by super me on 2017/6/26.
 */
public class Constants {

    public static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    public static final String DEFAULT_FAIL_MESSAGE = "FAIL";
    public static final String DEFAULT_ERROR_MESSAGE = "ERROR";

    public static final int RESULT_CODE_SUCCESS = 200;  // 成功处理请求
    public static final int RESULT_CODE_BAD_REQUEST = 412;  // bad request
    public static final int RESULT_CODE_SERVER_ERROR = 500;  // 没有对应结果

    public static final String ARTICLE_CACHE_KEY = "perfect-ssm:article:";//文章key
    public static final String PICTURE_CACHE_KEY = "perfect-ssm:picture:";//图片key

    public static final String RESULT_EMPTY_REQUEST = "暂无数据";


    /**
     * jwt
     */
    public static final String JWT_ID = "jwt";
    public static final String JWT_SECRET = "7786df7fc3a34e26a61c034d5ec8245d";
    public static final int JWT_TTL = 60 * 60 * 1000;  //millisecond
    public static final int JWT_REFRESH_INTERVAL = 55 * 60 * 1000;  //millisecond
    public static final int JWT_REFRESH_TTL = 12 * 60 * 60 * 1000;  //millisecond


}
