package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for audit logging
 * Usage: @AuditLog(action = "CREATE_USER", module = "USER_MANAGEMENT")
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditLog {
    String action() default "";
    String module() default "";
    boolean logRequest() default true;
    boolean logResponse() default false;
}
