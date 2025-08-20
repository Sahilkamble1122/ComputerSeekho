# 🏗️ **Microservices Architecture Setup**

## 📋 **Overview**
This directory contains the complete microservices architecture for your Spring Boot application, migrated from a monolithic structure.

## 🎯 **Architecture Overview**

### **Services Structure:**
```
microservices/
├── eureka-server/          # Service Discovery (Port: 8761)
├── api-gateway/           # API Gateway (Port: 8080)
├── student-service/       # Student Management (Port: 8081)
├── enquiry-service/       # Enquiry Management (Port: 8082)
├── payment-service/       # Payment Management (Port: 8083)
├── staff-service/         # Staff Management (Port: 8084)
├── contact-service/       # Contact Management (Port: 8085)
├── auth-service/          # Authentication (Port: 8086)
├── docker-compose.yml     # Docker orchestration
├── deploy.sh             # Deployment script
└── README.md             # This file
```

### **Service Responsibilities:**

| Service | Port | Database | Responsibilities |
|---------|------|----------|------------------|
| **Eureka Server** | 8761 | - | Service discovery and registration |
| **API Gateway** | 8080 | - | Routing, load balancing, security |
| **Student Service** | 8081 | student_db | Student, Course, Batch management |
| **Enquiry Service** | 8082 | enquiry_db | Enquiry management and follow-ups |
| **Payment Service** | 8083 | payment_db | Payment and Receipt management |
| **Staff Service** | 8084 | staff_db | Staff management and roles |
| **Contact Service** | 8085 | contact_db | Contact form management |
| **Auth Service** | 8086 | auth_db | Authentication and JWT tokens |

## 🚀 **Quick Start**

### **Prerequisites:**
- Java 17 or higher
- Maven 3.6+
- Docker and Docker Compose
- MySQL 8.0

### **Step 1: Clone and Setup**
```bash
# Navigate to microservices directory
cd microservices

# Make deployment script executable
chmod +x deploy.sh
```

### **Step 2: Copy Your Existing Code**
```bash
# Copy your existing code from monolithic app
# Follow the code-migration-guide.md for detailed steps

# Quick copy commands:
cp -r ../RestApiDemo/src/main/java/com/example/demo/model/* student-service/src/main/java/com/example/student/model/
cp -r ../RestApiDemo/src/main/java/com/example/demo/controller/* student-service/src/main/java/com/example/student/controller/
# ... repeat for other services
```

### **Step 3: Update Package Names**
```bash
# Update package declarations in all Java files
# Change from: package com.example.demo;
# To: package com.example.[service-name];

# Example for student service:
find student-service -name "*.java" -exec sed -i 's/package com.example.demo;/package com.example.student;/g' {} \;
```

### **Step 4: Deploy with Docker**
```bash
# Build and run all services
./deploy.sh

# Or manually:
docker-compose up -d
```

## 🔧 **Configuration**

### **Database Configuration:**
Each service can use either:
- **Shared Database** (easier migration): All services use the same database
- **Separate Databases** (recommended): Each service has its own database

### **Service Discovery:**
- **Eureka Server**: Manages service registration and discovery
- **Load Balancing**: Automatic load balancing through Eureka

### **API Gateway:**
- **Routing**: Routes requests to appropriate services
- **Security**: JWT token validation
- **CORS**: Cross-origin resource sharing

## 📊 **Monitoring and Health Checks**

### **Health Check Endpoints:**
```
http://localhost:8761/eureka/          # Eureka Dashboard
http://localhost:8080/actuator/health  # API Gateway Health
http://localhost:8081/actuator/health  # Student Service Health
http://localhost:8082/actuator/health  # Enquiry Service Health
http://localhost:8083/actuator/health  # Payment Service Health
http://localhost:8084/actuator/health  # Staff Service Health
http://localhost:8085/actuator/health  # Contact Service Health
http://localhost:8086/actuator/health  # Auth Service Health
```

### **Service URLs:**
```
API Gateway: http://localhost:8080
Student API: http://localhost:8080/api/students
Enquiry API: http://localhost:8080/api/enquiries
Payment API: http://localhost:8080/api/payments
Staff API: http://localhost:8080/api/staff
Contact API: http://localhost:8080/api/contactus
Auth API: http://localhost:8080/api/auth
```

## 🔄 **Cross-Service Communication**

### **Using RestTemplate:**
```java
@Autowired
private RestTemplate restTemplate;

public StudentWithEnquiriesDTO getStudentWithEnquiries(Integer studentId) {
    StudentMaster student = studentService.getStudentById(studentId);
    
    // Call enquiry service
    String enquiryUrl = "http://enquiry-service/api/enquiries/student/" + studentId;
    ResponseEntity<List<Enquiry>> enquiryResponse = restTemplate.getForEntity(enquiryUrl, List.class);
    List<Enquiry> enquiries = enquiryResponse.getBody();
    
    return new StudentWithEnquiriesDTO(student, enquiries);
}
```

### **Using Feign Client:**
```java
@FeignClient(name = "enquiry-service")
public interface EnquiryClient {
    
    @GetMapping("/api/enquiries/student/{studentId}")
    List<Enquiry> getEnquiriesByStudentId(@PathVariable("studentId") Integer studentId);
}
```

## 🔐 **Authentication and Security**

### **JWT Token Flow:**
1. Client sends credentials to Auth Service
2. Auth Service validates and returns JWT token
3. Client includes token in subsequent requests
4. API Gateway validates token before routing

### **Protected Endpoints:**
```bash
# Login to get token
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password"}'

# Use token for protected endpoints
curl -H "Authorization: Bearer YOUR_TOKEN" \
  http://localhost:8080/api/students
```

## 🐳 **Docker Commands**

### **Build and Run:**
```bash
# Build all services
docker-compose build

# Run all services
docker-compose up -d

# Check status
docker-compose ps

# View logs
docker-compose logs -f [service-name]

# Stop all services
docker-compose down
```

### **Individual Service Commands:**
```bash
# Build specific service
docker build -t student-service ./student-service

# Run specific service
docker run -p 8081:8081 student-service

# View logs
docker logs -f [container-id]
```

## 🧪 **Testing**

### **Unit Tests:**
```bash
# Test individual service
cd student-service
mvn test

# Test all services
for service in */; do
    cd $service
    mvn test
    cd ..
done
```

### **Integration Tests:**
```bash
# Test service endpoints
curl http://localhost:8081/api/students
curl http://localhost:8082/api/enquiries
curl http://localhost:8083/api/payments
```

### **Load Testing:**
```bash
# Test API Gateway routing
ab -n 1000 -c 10 http://localhost:8080/api/students
```

## 📈 **Performance Monitoring**

### **Metrics Endpoints:**
```
http://localhost:8080/actuator/metrics
http://localhost:8081/actuator/metrics
http://localhost:8082/actuator/metrics
```

### **Custom Metrics:**
```java
@RestController
public class MetricsController {
    
    @Autowired
    private MeterRegistry meterRegistry;
    
    @GetMapping("/custom-metric")
    public void incrementMetric() {
        meterRegistry.counter("custom.counter").increment();
    }
}
```

## 🔧 **Troubleshooting**

### **Common Issues:**

#### **1. Service Not Starting:**
```bash
# Check logs
docker-compose logs [service-name]

# Check health
curl http://localhost:[port]/actuator/health
```

#### **2. Database Connection Issues:**
```bash
# Check MySQL connection
docker exec -it [mysql-container] mysql -u root -p

# Check database exists
SHOW DATABASES;
```

#### **3. Service Discovery Issues:**
```bash
# Check Eureka dashboard
http://localhost:8761

# Check service registration
curl http://localhost:8761/eureka/apps
```

#### **4. API Gateway Routing Issues:**
```bash
# Check gateway routes
curl http://localhost:8080/actuator/gateway/routes

# Test direct service access
curl http://localhost:8081/api/students
```

## 📚 **Documentation**

- **Migration Guide**: `code-migration-guide.md`
- **Implementation Guide**: `microservices-implementation-guide.md`
- **API Documentation**: Each service has its own API endpoints

## 🚀 **Production Deployment**

### **Environment Variables:**
```bash
# Set production environment
export SPRING_PROFILES_ACTIVE=prod
export MYSQL_HOST=production-db-host
export MYSQL_PASSWORD=secure-password
```

### **Kubernetes Deployment:**
```bash
# Create Kubernetes manifests
kubectl apply -f k8s/

# Deploy to cluster
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
```

## 📞 **Support**

For issues and questions:
1. Check the troubleshooting section
2. Review service logs
3. Check health endpoints
4. Verify configuration files

## 🎯 **Next Steps**

1. **Complete Code Migration**: Copy all your existing code to respective services
2. **Update Package Names**: Change all package declarations
3. **Test Each Service**: Verify individual service functionality
4. **Test Integration**: Verify cross-service communication
5. **Deploy to Production**: Use Docker or Kubernetes
6. **Monitor Performance**: Set up monitoring and alerting

---

**Happy Microservices Development! 🚀**
