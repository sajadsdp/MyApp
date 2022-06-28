package com.example.myapp.Aop;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private static Log LOGGER = LogFactory.getLog("Method_Logger");

    @Pointcut("execution(public * com.example.myapp.*.*.*(..))")
    public void allMethods(){}
    /* public haye package user shamel beshe va jahai ke @Log estefade mikonim. */

    @Around("allMethods() && @annotation(com.example.myapp.Aop.Log)")
    public Object  logAround(ProceedingJoinPoint joinPoint) throws Throwable{
        LOGGER.info(String.format(
                "Method %s is going call from class %s",
                joinPoint.getSignature().toString(),
                joinPoint.getTarget().getClass().getSimpleName() ));

        Object returnObj = joinPoint.proceed(joinPoint.getArgs());

        LOGGER.info(String.format(
                "Method %s has called from class %s with return value type %s",
                joinPoint.getSignature().toString(),
                joinPoint.getTarget().getClass().getSimpleName(),
                returnObj.getClass().getName() ));

        return returnObj;
    }
}
