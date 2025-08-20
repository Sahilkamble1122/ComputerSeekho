package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.demo.annotation.AuditLog;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * AOP Aspect for audit logging
 */
@Aspect
@Component
public class AuditAspect {
    
    private static final Logger logger = LoggerFactory.getLogger(AuditAspect.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    // Pointcut for methods with @AuditLog annotation
    @Pointcut("@annotation(com.example.demo.annotation.AuditLog)")
    public void auditLogMethods() {}
    
    /**
     * Around advice for audit logging
     */
    @Around("auditLogMethods()")
    public Object auditMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AuditLog annotation = method.getAnnotation(AuditLog.class);
        
        String timestamp = LocalDateTime.now().format(formatter);
        String username = getCurrentUsername();
        String action = annotation.action();
        String module = annotation.module();
        String methodName = method.getName();
        String className = method.getDeclaringClass().getSimpleName();
        
        // Log method entry
        if (annotation.logRequest()) {
            logAuditEntry(timestamp, username, action, module, className, methodName, 
                "ENTRY", joinPoint.getArgs());
        }
        
        try {
            Object result = joinPoint.proceed();
            
            // Log method exit
            if (annotation.logResponse()) {
                logAuditEntry(timestamp, username, action, module, className, methodName, 
                    "EXIT", result);
            }
            
            return result;
        } catch (Exception e) {
            // Log exception
            logAuditException(timestamp, username, action, module, className, methodName, e);
            throw e;
        }
    }
    
    /**
     * After returning advice for successful operations
     */
    @AfterReturning(pointcut = "auditLogMethods()", returning = "result")
    public void auditAfterReturning(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AuditLog annotation = method.getAnnotation(AuditLog.class);
        
        String timestamp = LocalDateTime.now().format(formatter);
        String username = getCurrentUsername();
        String action = annotation.action();
        String module = annotation.module();
        String methodName = method.getName();
        String className = method.getDeclaringClass().getSimpleName();
        
        logAuditSuccess(timestamp, username, action, module, className, methodName, result);
    }
    
    /**
     * After throwing advice for exceptions
     */
    @AfterThrowing(pointcut = "auditLogMethods()", throwing = "exception")
    public void auditAfterThrowing(JoinPoint joinPoint, Exception exception) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AuditLog annotation = method.getAnnotation(AuditLog.class);
        
        String timestamp = LocalDateTime.now().format(formatter);
        String username = getCurrentUsername();
        String action = annotation.action();
        String module = annotation.module();
        String methodName = method.getName();
        String className = method.getDeclaringClass().getSimpleName();
        
        logAuditException(timestamp, username, action, module, className, methodName, exception);
    }
    
    /**
     * Log audit entry
     */
    private void logAuditEntry(String timestamp, String username, String action, String module,
                              String className, String methodName, String eventType, Object data) {
        try {
            String dataJson = objectMapper.writeValueAsString(data);
            logger.info("AUDIT [{}] User: {}, Action: {}, Module: {}, Class: {}, Method: {}, Event: {}, Data: {}", 
                timestamp, username, action, module, className, methodName, eventType, dataJson);
        } catch (Exception e) {
            logger.warn("Failed to serialize audit data: {}", e.getMessage());
            logger.info("AUDIT [{}] User: {}, Action: {}, Module: {}, Class: {}, Method: {}, Event: {}, Data: {}", 
                timestamp, username, action, module, className, methodName, eventType, data);
        }
    }
    
    /**
     * Log audit success
     */
    private void logAuditSuccess(String timestamp, String username, String action, String module,
                                String className, String methodName, Object result) {
        logger.info("AUDIT SUCCESS [{}] User: {}, Action: {}, Module: {}, Class: {}, Method: {}, Result: {}", 
            timestamp, username, action, module, className, methodName, result);
    }
    
    /**
     * Log audit exception
     */
    private void logAuditException(String timestamp, String username, String action, String module,
                                  String className, String methodName, Exception exception) {
        logger.error("AUDIT EXCEPTION [{}] User: {}, Action: {}, Module: {}, Class: {}, Method: {}, Error: {}", 
            timestamp, username, action, module, className, methodName, exception.getMessage());
    }
    
    /**
     * Get current authenticated username
     */
    private String getCurrentUsername() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                return authentication.getName();
            }
        } catch (Exception e) {
            logger.debug("Could not get current user: {}", e.getMessage());
        }
        return "ANONYMOUS";
    }
}
