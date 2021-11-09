package com.lance.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class WebLogAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * com.lance.modules.*.rest..*(..))")
    public void webLog() {}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) return;
        HttpServletRequest request = attributes.getRequest();
        LOGGER.info("--------------------------------------- Start ----------------------------------------");
        LOGGER.info("URL             : {}", request.getRequestURL().toString());
        LOGGER.info("HTTP Method     : {}", request.getMethod());
        LOGGER.info("Class Method    : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        LOGGER.info("IP              : {}", request.getRemoteAddr());
        LOGGER.info("Request Args    : {}", joinPoint.getArgs());
        LOGGER.info("---------------------------------------- End -----------------------------------------");
    }

//    @After("webLog()")
//    public void doAfter() {
//        LOGGER.info("---------------------------------------- End -----------------------------------------");
//    }

//    @Around("webLog()")
//    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();
//        Object result = proceedingJoinPoint.proceed();
//        LOGGER.info("Response Args   : {}", JSON.toJSON(result));
//        LOGGER.info("Time-Consuming  : {}", System.currentTimeMillis() - startTime);
//        return result;
//    }
}
