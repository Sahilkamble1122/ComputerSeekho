package com.example.demo.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.example.demo.annotation.LogExecutionTime;

/**
 * AOP Aspect for logging method execution time and general logging
 */
@Aspect
@Component
public class LoggingAspect {
    
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    
    // Pointcut for all controller methods
    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    public void controllerMethods() {}
    
    // Pointcut for all service methods
    @Pointcut("execution(* com.example.demo.service.*.*(..))")
    public void serviceMethods() {}
    
    // Pointcut for methods with @LogExecutionTime annotation
    @Pointcut("@annotation(com.example.demo.annotation.LogExecutionTime)")
    public void logExecutionTimeMethods() {}
    
    /**
     * Around advice for methods with @LogExecutionTime annotation
     */
    @Around("logExecutionTimeMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogExecutionTime annotation = method.getAnnotation(LogExecutionTime.class);
        
        String methodName = method.getName();
        String className = method.getDeclaringClass().getSimpleName();
        String description = annotation.value().isEmpty() ? 
            String.format("%s.%s", className, methodName) : annotation.value();
        
        logger.info("Starting execution of: {}", description);
        
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        try {
            Object result = joinPoint.proceed();
            stopWatch.stop();
            
            logger.info("Completed execution of: {} in {} ms", 
                description, stopWatch.getTotalTimeMillis());
            
            return result;
        } catch (Exception e) {
            stopWatch.stop();
            logger.error("Exception in execution of: {} after {} ms - Error: {}", 
                description, stopWatch.getTotalTimeMillis(), e.getMessage());
            throw e;
        }
    }
    
    /**
     * Around advice for controller methods - logs request/response
     */
    @Around("controllerMethods()")
    public Object logControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        String className = signature.getMethod().getDeclaringClass().getSimpleName();
        
        logger.info("Controller Request: {}.{} with args: {}", 
            className, methodName, joinPoint.getArgs());
        
        try {
            Object result = joinPoint.proceed();
            logger.info("Controller Response: {}.{} returned: {}", 
                className, methodName, result);
            return result;
        } catch (Exception e) {
            logger.error("Controller Exception: {}.{} - Error: {}", 
                className, methodName, e.getMessage());
            throw e;
        }
    }
    
    /**
     * Around advice for service methods - logs business logic execution
     */
    @Around("serviceMethods()")
    public Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        String className = signature.getMethod().getDeclaringClass().getSimpleName();
        
        logger.debug("Service Method: {}.{} called", className, methodName);
        
        try {
            Object result = joinPoint.proceed();
            logger.debug("Service Method: {}.{} completed successfully", className, methodName);
            return result;
        } catch (Exception e) {
            logger.error("Service Exception: {}.{} - Error: {}", 
                className, methodName, e.getMessage());
            throw e;
        }
    }
}
