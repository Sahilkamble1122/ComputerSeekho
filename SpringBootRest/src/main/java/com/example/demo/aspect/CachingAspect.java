package com.example.demo.aspect;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import com.example.demo.annotation.Cacheable;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * AOP Aspect for method result caching
 */
@Aspect
@Component
public class CachingAspect {
    
    private static final Logger logger = LoggerFactory.getLogger(CachingAspect.class);
    
    // In-memory cache using Caffeine
    private final Cache<String, Object> cache = Caffeine.newBuilder()
        .maximumSize(1000)
        .expireAfterWrite(300, TimeUnit.SECONDS)
        .build();
    
    // Pointcut for methods with @Cacheable annotation
    @Pointcut("@annotation(com.example.demo.annotation.Cacheable)")
    public void cacheableMethods() {}
    
    /**
     * Around advice for methods with @Cacheable annotation
     */
    @Around("cacheableMethods()")
    public Object cacheMethodResult(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Cacheable annotation = method.getAnnotation(Cacheable.class);
        
        // Generate cache key
        String cacheKey = generateCacheKey(joinPoint, annotation);
        
        // Check if result exists in cache
        Object cachedResult = cache.getIfPresent(cacheKey);
        if (cachedResult != null) {
            logger.debug("Cache HIT for key: {}", cacheKey);
            return cachedResult;
        }
        
        logger.debug("Cache MISS for key: {}", cacheKey);
        
        // Execute method and cache result
        try {
            Object result = joinPoint.proceed();
            cache.put(cacheKey, result);
            logger.debug("Cached result for key: {}", cacheKey);
            return result;
        } catch (Exception e) {
            logger.error("Error executing cached method: {}", e.getMessage());
            throw e;
        }
    }
    
    /**
     * Generate cache key based on method signature and parameters
     */
    private String generateCacheKey(ProceedingJoinPoint joinPoint, Cacheable annotation) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        String className = signature.getMethod().getDeclaringClass().getSimpleName();
        
        // If custom key is provided, use SpEL to evaluate it
        if (!annotation.key().isEmpty()) {
            try {
                ExpressionParser parser = new SpelExpressionParser();
                StandardEvaluationContext context = new StandardEvaluationContext();
                
                // Add method parameters to context
                String[] paramNames = signature.getParameterNames();
                Object[] args = joinPoint.getArgs();
                for (int i = 0; i < paramNames.length; i++) {
                    context.setVariable(paramNames[i], args[i]);
                }
                
                Expression expression = parser.parseExpression(annotation.key());
                String customKey = expression.getValue(context, String.class);
                return String.format("%s:%s:%s", className, methodName, customKey);
            } catch (Exception e) {
                logger.warn("Failed to parse custom cache key: {}, using default", annotation.key());
            }
        }
        
        // Default key generation
        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append(className).append(":").append(methodName);
        
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            keyBuilder.append(":");
            for (Object arg : args) {
                keyBuilder.append(arg != null ? arg.toString() : "null").append("_");
            }
            keyBuilder.setLength(keyBuilder.length() - 1); // Remove last underscore
        }
        
        return keyBuilder.toString();
    }
    
    /**
     * Clear cache manually (can be called from service methods)
     */
    public void clearCache() {
        cache.invalidateAll();
        logger.info("Cache cleared");
    }
    
    /**
     * Get cache statistics
     */
    public String getCacheStats() {
        return String.format("Cache Stats - Size: %d, Hit Rate: %.2f%%", 
            cache.estimatedSize(), 
            cache.stats().hitRate() * 100);
    }
}
