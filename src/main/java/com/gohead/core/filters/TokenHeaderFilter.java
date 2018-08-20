package com.gohead.core.filters;

import com.alibaba.fastjson.JSON;
import com.gohead.core.common.Constants;
import com.gohead.core.common.Result;
import com.gohead.core.common.ResultGenerator;
import com.gohead.core.util.JwtUtil;
import org.apache.log4j.Logger;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by super on 2018/8/17.
 */
public class TokenHeaderFilter implements HandlerInterceptor {
    private static final Logger log = Logger.getLogger(TokenHeaderFilter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // TODO Auto-generated method stub
        String uri = request.getRequestURI();
        String token = null;
        if (uri.equals("/")) {
            return true;
        } else if (uri.contains("/login")) {
            return true;
        } else {
            token = request.getHeader("token");
            if (token == null || !JwtUtil.isValidToken(token)) {
                Result result = ResultGenerator.getErrorResult(Constants.RESULT_STRING_INVALID_TOKEN, Constants.RESULT_CODE_INVALID_TOKEN);
                dealErrorReturn(request, response, JSON.toJSONString(result));
                return false;
            }
            token = JwtUtil.updateToken(token);
        }
        response.setHeader("token",token);
        return true;
    }

    // 检测到没有token，直接返回不验证
    public void dealErrorReturn(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object obj) {
        PrintWriter writer = null;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html; charset=utf-8");
        try {
            writer = httpServletResponse.getWriter();
            writer.print(obj);

        } catch (IOException ex) {
            log.error("response error", ex);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
