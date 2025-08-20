# Performance Optimization Guide for 1000 Concurrent Requests

## Overview
This guide provides comprehensive strategies to optimize your Spring Boot application for handling 1000 concurrent requests efficiently.

## 1. Database Connection Pool Optimization

### Current Configuration (Optimized)
```properties
# HikariCP Configuration for 1000 concurrent requests
spring.datasource.hikari.maximum-pool-size=100
spring.datasource.hikari.minimum-idle=20
spring.datasource.hikari.idle-timeout=300000
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.max-lifetime=1800000
spring.datasource.hikari.leak-detection-threshold=60000
spring.datasource.hikari.validation-timeout=5000
```

### MySQL Optimization
```properties
# MySQL specific optimizations
spring.datasource.hikari.data-source-properties.rewriteBatchedStatements=true
spring.datasource.hikari.data-source-properties.cachePrepStmts=true
spring.datasource.hikari.data-source-properties.prepStmtCacheSize=250
spring.datasource.hikari.data-source-properties.prepStmtCacheSqlLimit=2048
spring.datasource.hikari.data-source-properties.useServerPrepStmts=true
```

## 2. Server Configuration

### Tomcat Thread Pool
```properties
# Tomcat thread pool configuration
server.tomcat.threads.max=400
server.tomcat.threads.min-spare=50
server.tomcat.max-connections=8192
server.tomcat.accept-count=100
server.tomcat.connection-timeout=20000
```

### Response Compression
```properties
# Enable compression
server.compression.enabled=true
server.compression.mime-types=text/html,text/xml,text/plain,text/css,text/javascript,application/javascript,application/json
server.compression.min-response-size=1024
```

## 3. JPA/Hibernate Optimization

### Batch Processing
```properties
# Hibernate batch optimizations
spring.jpa.properties.hibernate.jdbc.batch_size=50
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true
spring.jpa.properties.hibernate.jdbc.batch_versioned_data=true
spring.jpa.properties.hibernate.jdbc.fetch_size=50
```

### Disable SQL Logging in Production
```properties
# Disable SQL logging for better performance
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=false
```

## 4. Caching Strategy

### Caffeine Cache Configuration
```java
@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .maximumSize(500)
                .expireAfterWrite(Duration.ofMinutes(5))
                .recordStats());
        return cacheManager;
    }
}
```

### Cache Annotations Usage
```java
@Cacheable(value = "students", key = "#studentId")
public Optional<StudentMaster> getStudentById(Integer studentId) {
    return studentMasterRepository.findById(studentId);
}

@CacheEvict(value = "students", allEntries = true)
public StudentMaster saveStudent(StudentMaster student) {
    return studentMasterRepository.save(student);
}
```

## 5. Async Processing

### Async Configuration
```java
@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(1000);
        executor.setThreadNamePrefix("AsyncThread-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
```

### Async Method Usage
```java
@Async("taskExecutor")
public CompletableFuture<List<StudentMaster>> getStudentsWithPaginationAsync(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<StudentMaster> studentPage = studentMasterRepository.findAll(pageable);
    return CompletableFuture.completedFuture(studentPage.getContent());
}
```

## 6. Monitoring and Metrics

### Actuator Configuration
```properties
# Enable monitoring endpoints
management.endpoints.web.exposure.include=health,info,metrics,prometheus
management.endpoint.health.show-details=when-authorized
management.endpoints.web.base-path=/actuator
management.metrics.export.prometheus.enabled=true
management.metrics.distribution.percentiles-histogram.http.server.requests=true
```

## 7. Logging Optimization

### Production Logging
```properties
# Reduce logging in production
logging.level.com.example.demo=INFO
logging.level.org.springframework.web=WARN
logging.level.org.hibernate.SQL=WARN
logging.level.org.hibernate.type.descriptor.sql=WARN
logging.level.com.zaxxer.hikari=WARN
```

## 8. JSON Serialization Optimization

### Jackson Configuration
```properties
# JSON optimization
spring.jackson.serialization.write-dates-as-timestamps=false
spring.jackson.time-zone=UTC
spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
spring.jackson.serialization.fail-on-empty-beans=false
```

## 9. Repository Optimization

### Custom Queries with Indexing
```java
@Repository
public interface StudentMasterRepository extends JpaRepository<StudentMaster, Integer> {
    
    // Use indexes for frequently queried fields
    @Query("SELECT s FROM StudentMaster s WHERE s.studentEmail = :email")
    List<StudentMaster> findByStudentEmail(@Param("email") String email);
    
    // Batch operations
    @Modifying
    @Query("UPDATE StudentMaster s SET s.studentIsActive = :status WHERE s.courseId = :courseId")
    void updateStudentsByCourseId(@Param("courseId") Integer courseId, @Param("status") Boolean status);
}
```

## 10. Controller Optimization

### Response Caching
```java
@RestController
@RequestMapping("/api/students")
public class StudentMasterController {
    
    @GetMapping("/{id}")
    @Cacheable(value = "students", key = "#id")
    public ResponseEntity<StudentMaster> getStudentById(@PathVariable Integer id) {
        return studentMasterService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    @Cacheable(value = "students", key = "'all'")
    public ResponseEntity<List<StudentMaster>> getAllStudents() {
        return ResponseEntity.ok(studentMasterService.getAllStudents());
    }
}
```

## 11. Database Indexing

### Recommended Indexes
```sql
-- Primary indexes (already exist)
CREATE INDEX idx_student_email ON student_master(student_email);
CREATE INDEX idx_student_course ON student_master(course_id);
CREATE INDEX idx_student_batch ON student_master(batch_id);
CREATE INDEX idx_student_name ON student_master(student_name);

-- Composite indexes for complex queries
CREATE INDEX idx_student_course_batch ON student_master(course_id, batch_id);
CREATE INDEX idx_student_created_date ON student_master(created_date);
```

## 12. Load Testing

### JMeter Configuration
```xml
<!-- Thread Group for 1000 concurrent users -->
<ThreadGroup>
  <stringProp name="ThreadGroup.num_threads">1000</stringProp>
  <stringProp name="ThreadGroup.ramp_time">60</stringProp>
  <stringProp name="ThreadGroup.duration">300</stringProp>
</ThreadGroup>
```

## 13. Performance Monitoring

### Key Metrics to Monitor
- Response Time (P95, P99)
- Throughput (requests/second)
- Error Rate
- Database Connection Pool Usage
- Memory Usage
- CPU Usage
- Cache Hit Rate

### Prometheus Queries
```promql
# Response time 95th percentile
histogram_quantile(0.95, rate(http_server_requests_seconds_bucket[5m]))

# Throughput
rate(http_server_requests_total[5m])

# Error rate
rate(http_server_requests_total{status=~"5.."}[5m])
```

## 14. Additional Optimizations

### 1. Use Connection Pooling
- HikariCP is already configured optimally
- Monitor connection pool metrics

### 2. Implement Circuit Breaker
```java
@CircuitBreaker(name = "studentService", fallbackMethod = "fallbackGetStudent")
public StudentMaster getStudentById(Integer id) {
    return studentMasterRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found"));
}
```

### 3. Use Response Entity Caching
```java
@Cacheable(value = "responses", key = "#request")
public ResponseEntity<?> cachedResponse(HttpServletRequest request) {
    // Implementation
}
```

### 4. Optimize JSON Responses
```java
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentResponse {
    private Integer id;
    private String name;
    // Only include necessary fields
}
```

## 15. Production Deployment

### JVM Tuning
```bash
# JVM options for high concurrency
java -Xms2g -Xmx4g \
     -XX:+UseG1GC \
     -XX:MaxGCPauseMillis=200 \
     -XX:+UseStringDeduplication \
     -jar your-application.jar
```

### Docker Configuration
```dockerfile
FROM openjdk:17-jre-slim
COPY target/RestApiDemo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xms2g", "-Xmx4g", "-jar", "/app.jar"]
```

## 16. Expected Performance Improvements

With these optimizations, you should achieve:
- **Response Time**: < 100ms for 95% of requests
- **Throughput**: 1000+ requests/second
- **Error Rate**: < 1%
- **Database Connections**: Efficiently managed pool
- **Memory Usage**: Optimized with caching
- **CPU Usage**: Reduced with async processing

## 17. Monitoring Setup

### Health Checks
```java
@Component
public class DatabaseHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        try {
            // Check database connectivity
            return Health.up().build();
        } catch (Exception e) {
            return Health.down().withException(e).build();
        }
    }
}
```

This comprehensive optimization strategy will significantly improve your application's performance under high concurrent load. 