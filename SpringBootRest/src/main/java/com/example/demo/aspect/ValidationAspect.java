package com.example.demo.aspect;

import java.lang.reflect.Method;
import java.util.Set;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.demo.annotation.ValidateInput;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

/**
 * AOP Aspect for input validation
 */
@Aspect
@Component
public class ValidationAspect {
    
    private static final Logger logger = LoggerFactory.getLogger(ValidationAspect.class);
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();
    
    // Pointcut for methods with @ValidateInput annotation
    @Pointcut("@annotation(com.example.demo.annotation.ValidateInput)")
    public void validateInputMethods() {}
    
    /**
     * Before advice for input validation
     */
    @Before("validateInputMethods()")
    public void validateInput(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ValidateInput annotation = method.getAnnotation(ValidateInput.class);
        
        if (!annotation.validate()) {
            return; // Skip validation if disabled
        }
        
        Object[] args = joinPoint.getArgs();
        String[] paramNames = signature.getParameterNames();
        
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            String paramName = paramNames[i];
            
            if (arg == null) {
                logger.warn("Null parameter detected: {} in method: {}", 
                    paramName, method.getName());
                continue;
            }
            
            // Validate using Bean Validation annotations
            Set<ConstraintViolation<Object>> violations = validator.validate(arg);
            if (!violations.isEmpty()) {
                StringBuilder errorMessage = new StringBuilder();
                errorMessage.append("Validation failed for parameter '").append(paramName)
                    .append("' in method '").append(method.getName()).append("': ");
                
                for (ConstraintViolation<Object> violation : violations) {
                    errorMessage.append(violation.getPropertyPath())
                        .append(": ").append(violation.getMessage()).append("; ");
                }
                
                logger.error(errorMessage.toString());
                throw new IllegalArgumentException(errorMessage.toString());
            }
            
            // Custom validation logic
            performCustomValidation(arg, paramName, method.getName());
        }
        
        logger.debug("Input validation passed for method: {}", method.getName());
    }
    
    /**
     * Custom validation logic
     */
    private void performCustomValidation(Object arg, String paramName, String methodName) {
        if (arg instanceof String) {
            String stringValue = (String) arg;
            if (!StringUtils.hasText(stringValue)) {
                String errorMsg = String.format("Parameter '%s' cannot be empty in method '%s'", 
                    paramName, methodName);
                logger.error(errorMsg);
                throw new IllegalArgumentException(errorMsg);
            }
        }
        
        if (arg instanceof Number) {
            Number numberValue = (Number) arg;
            if (numberValue.doubleValue() < 0) {
                String errorMsg = String.format("Parameter '%s' cannot be negative in method '%s'", 
                    paramName, methodName);
                logger.error(errorMsg);
                throw new IllegalArgumentException(errorMsg);
            }
        }
        
        // Add more custom validation rules as needed
        if (arg instanceof java.util.Collection) {
            java.util.Collection<?> collection = (java.util.Collection<?>) arg;
            if (collection.isEmpty()) {
                String errorMsg = String.format("Parameter '%s' cannot be empty in method '%s'", 
                    paramName, methodName);
                logger.error(errorMsg);
                throw new IllegalArgumentException(errorMsg);
            }
        }
    }
}
