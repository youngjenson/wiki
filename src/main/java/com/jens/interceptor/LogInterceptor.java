package com.jens.interceptor;

import com.mysql.cj.exceptions.ClosedOnExpiredPasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器：Spring框架特有的，常用于登录校验，权限校验，请求日志打印
 */
/*@Component
@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("-----------LogInterceptor 开始-----------");
        log.info("请求地址: {} {}",request.getRequestURL().toString(),request.getMethod());
        log.info("远程地址: {}",request.getRemoteAddr());

        long startTime = System.currentTimeMillis();
        request.setAttribute("requestStartTime",startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (long) request.getAttribute("requestStartTime");
        log.info("-------------LogInterceptor 结束 耗时: {} ms",System.currentTimeMillis() - startTime);
    }
}*/
