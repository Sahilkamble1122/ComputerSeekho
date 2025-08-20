package com.example.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AOP Configuration
 * Enables AspectJ auto-proxy and component scanning for aspects
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = {
    "com.example.demo.aspect",
    "com.example.demo.annotation"
})
public class AopConfig {
    // Configuration is handled by annotations
}
