package com.jens.aspect;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(public * com.jens.controller..*Controller.*(..))")
    public void controllerPointcut(){}

    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint){
        //开始打印日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        //打印请求信息
        log.info("---------开始--------");
        log.info("请求地址: {} {}",request.getRequestURL().toString(),request.getMethod());
        log.info("类名方法: {}.{}",signature.getDeclaringTypeName(),name);
        log.info("远程地址: {}",request.getRemoteAddr());

        //打印请求参数
        Object[] args = joinPoint.getArgs();

        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if(args[i] instanceof ServletRequest
            || args[i] instanceof ServletResponse
            || args[i] instanceof MultipartFile){
                continue;
            }
            arguments[i] = args[i];
        }

        //排除字段，敏感字段或太长的字段不显示
        String[] excludeProperties = {"password","file"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludeFilter = filters.addFilter();
        excludeFilter.addExcludes(excludeProperties);
        log.info("请求参数： {}", JSONObject.toJSONString(arguments,excludeFilter));
    }

    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        //排除字段，敏感字段或太长的字段不显示
        String[] excludeProperties = {"password","file"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludeFilter = filters.addFilter();
        excludeFilter.addExcludes(excludeProperties);
        log.info("返回结果: {}",JSONObject.toJSONString(result,excludeFilter));
        log.info("--------结束 耗时 : {} ms ---------",System.currentTimeMillis() - startTime);
        return result;
    }
}
