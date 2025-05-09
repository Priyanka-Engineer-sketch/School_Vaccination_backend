package com.schoolvaccination.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.schoolvaccination.controller..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments: {}", 
                    joinPoint.getSignature().toShortString(), 
                    joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* com.schoolvaccination.controller..*(..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: {} with result: {}", 
                    joinPoint.getSignature().toShortString(), 
                    result);
    }

    @AfterThrowing(pointcut = "execution(* com.schoolvaccination.controller..*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("Exception in method: {} with cause: {}", 
                     joinPoint.getSignature().toShortString(), 
                     error.getMessage());
    }
}