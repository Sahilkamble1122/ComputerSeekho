# 🔄 **Code Migration Guide: Monolithic to Microservices**

## 📋 **Overview**
This guide will help you migrate your existing monolithic Spring Boot application to microservices architecture.

## 🎯 **Migration Steps**

### **Step 1: Copy Existing Code to Microservices**

#### **1.1 Student Service Migration**
```bash
# Copy StudentMaster related files
cp ../RestApiDemo/src/main/java/com/example/demo/model/StudentMaster.java microservices/student-service/src/main/java/com/example/student/model/
cp ../RestApiDemo/src/main/java/com/example/demo/controller/StudentMasterController.java microservices/student-service/src/main/java/com/example/student/controller/
cp ../RestApiDemo/src/main/java/com/example/demo/service/StudentMasterService.java microservices/student-service/src/main/java/com/example/student/service/
cp ../RestApiDemo/src/main/java/com/example/demo/service/impl/StudentMasterServiceImpl.java microservices/student-service/src/main/java/com/example/student/service/impl/
cp ../RestApiDemo/src/main/java/com/example/demo/repository/StudentMasterRepository.java microservices/student-service/src/main/java/com/example/student/repository/

# Copy Course and Batch related files (if they exist)
cp ../RestApiDemo/src/main/java/com/example/demo/model/Course.java microservices/student-service/src/main/java/com/example/student/model/
cp ../RestApiDemo/src/main/java/com/example/demo/model/Batch.java microservices/student-service/src/main/java/com/example/student/model/
```

#### **1.2 Enquiry Service Migration**
```bash
# Copy Enquiry related files
cp ../RestApiDemo/src/main/java/com/example/demo/model/Enquiry.java microservices/enquiry-service/src/main/java/com/example/enquiry/model/
cp ../RestApiDemo/src/main/java/com/example/demo/controller/EnquiryController.java microservices/enquiry-service/src/main/java/com/example/enquiry/controller/
cp ../RestApiDemo/src/main/java/com/example/demo/service/EnquiryService.java microservices/enquiry-service/src/main/java/com/example/enquiry/service/
cp ../RestApiDemo/src/main/java/com/example/demo/service/impl/EnquiryServiceImpl.java microservices/enquiry-service/src/main/java/com/example/enquiry/service/impl/
cp ../RestApiDemo/src/main/java/com/example/demo/repository/EnquiryRepository.java microservices/enquiry-service/src/main/java/com/example/enquiry/repository/
```

#### **1.3 Payment Service Migration**
```bash
# Copy PaymentWithTypeDTO and Receipt related files
cp ../RestApiDemo/src/main/java/com/example/demo/model/PaymentWithTypeDTO.java microservices/payment-service/src/main/java/com/example/payment/model/
cp ../RestApiDemo/src/main/java/com/example/demo/model/Receipt.java microservices/payment-service/src/main/java/com/example/payment/model/
cp ../RestApiDemo/src/main/java/com/example/demo/controller/PaymentWithTypeController.java microservices/payment-service/src/main/java/com/example/payment/controller/
cp ../RestApiDemo/src/main/java/com/example/demo/controller/ReceiptController.java microservices/payment-service/src/main/java/com/example/payment/controller/
cp ../RestApiDemo/src/main/java/com/example/demo/service/PaymentWithTypeService.java microservices/payment-service/src/main/java/com/example/payment/service/
cp ../RestApiDemo/src/main/java/com/example/demo/service/ReceiptService.java microservices/payment-service/src/main/java/com/example/payment/service/
cp ../RestApiDemo/src/main/java/com/example/demo/service/impl/PaymentWithTypeServiceImpl.java microservices/payment-service/src/main/java/com/example/payment/service/impl/
cp ../RestApiDemo/src/main/java/com/example/demo/service/impl/ReceiptServiceImpl.java microservices/payment-service/src/main/java/com/example/payment/service/impl/
cp ../RestApiDemo/src/main/java/com/example/demo/repository/PaymentWithTypeRepository.java microservices/payment-service/src/main/java/com/example/payment/repository/
cp ../RestApiDemo/src/main/java/com/example/demo/repository/ReceiptRepository.java microservices/payment-service/src/main/java/com/example/payment/repository/
```

#### **1.4 Staff Service Migration**
```bash
# Copy StaffMaster related files
cp ../RestApiDemo/src/main/java/com/example/demo/model/StaffMaster.java microservices/staff-service/src/main/java/com/example/staff/model/
cp ../RestApiDemo/src/main/java/com/example/demo/controller/StaffMasterController.java microservices/staff-service/src/main/java/com/example/staff/controller/
cp ../RestApiDemo/src/main/java/com/example/demo/service/StaffMasterService.java microservices/staff-service/src/main/java/com/example/staff/service/
cp ../RestApiDemo/src/main/java/com/example/demo/service/impl/StaffMasterServiceImpl.java microservices/staff-service/src/main/java/com/example/staff/service/impl/
cp ../RestApiDemo/src/main/java/com/example/demo/repository/StaffMasterRepository.java microservices/staff-service/src/main/java/com/example/staff/repository/
```

#### **1.5 Contact Service Migration**
```bash
# Copy ContactUs related files
cp ../RestApiDemo/src/main/java/com/example/demo/model/ContactUs.java microservices/contact-service/src/main/java/com/example/contact/model/
cp ../RestApiDemo/src/main/java/com/example/demo/controller/ContactUsController.java microservices/contact-service/src/main/java/com/example/contact/controller/
cp ../RestApiDemo/src/main/java/com/example/demo/service/ContactUsService.java microservices/contact-service/src/main/java/com/example/contact/service/
cp ../RestApiDemo/src/main/java/com/example/demo/service/impl/ContactUsServiceImpl.java microservices/contact-service/src/main/java/com/example/contact/service/impl/
cp ../RestApiDemo/src/main/java/com/example/demo/repository/ContactUsRepository.java microservices/contact-service/src/main/java/com/example/contact/repository/
```

### **Step 2: Update Package Declarations**

#### **2.1 Student Service Package Updates**
```java
// Update all files in student-service
// Change from: package com.example.demo;
// To: package com.example.student;

// Update imports in all files
// Change from: import com.example.demo.model.*;
// To: import com.example.student.model.*;
```

#### **2.2 Enquiry Service Package Updates**
```java
// Update all files in enquiry-service
// Change from: package com.example.demo;
// To: package com.example.enquiry;

// Update imports in all files
// Change from: import com.example.demo.model.*;
// To: import com.example.enquiry.model.*;
```

#### **2.3 Payment Service Package Updates**
```java
// Update all files in payment-service
// Change from: package com.example.demo;
// To: package com.example.payment;

// Update imports in all files
// Change from: import com.example.demo.model.*;
// To: import com.example.payment.model.*;
```

#### **2.4 Staff Service Package Updates**
```java
// Update all files in staff-service
// Change from: package com.example.demo;
// To: package com.example.staff;

// Update imports in all files
// Change from: import com.example.demo.model.*;
// To: import com.example.staff.model.*;
```

#### **2.5 Contact Service Package Updates**
```java
// Update all files in contact-service
// Change from: package com.example.demo;
// To: package com.example.contact;

// Update imports in all files
// Change from: import com.example.demo.model.*;
// To: import com.example.contact.model.*;
```

### **Step 3: Create Cross-Service Communication**

#### **3.1 Add RestTemplate Configuration**
```java
// Add to each service's main application class
@Bean
@LoadBalanced
public RestTemplate restTemplate() {
    return new RestTemplate();
}
```

#### **3.2 Create DTOs for Cross-Service Communication**
```java
// Create in each service for cross-service data transfer
public class StudentWithEnquiriesDTO {
    private StudentMaster student;
    private List<Enquiry> enquiries;
    // getters, setters, constructors
}

public class PaymentWithReceiptsDTO {
    private PaymentWithTypeDTO payment;
    private List<Receipt> receipts;
    // getters, setters, constructors
}
```

#### **3.3 Implement Cross-Service Calls**
```java
// Example in StudentController
@GetMapping("/{studentId}/with-enquiries")
public ResponseEntity<StudentWithEnquiriesDTO> getStudentWithEnquiries(@PathVariable Integer studentId) {
    StudentMaster student = studentService.getStudentById(studentId);
    
    // Call enquiry service
    String enquiryUrl = "http://enquiry-service/api/enquiries/student/" + studentId;
    ResponseEntity<List<Enquiry>> enquiryResponse = restTemplate.getForEntity(enquiryUrl, List.class);
    List<Enquiry> enquiries = enquiryResponse.getBody();
    
    StudentWithEnquiriesDTO result = new StudentWithEnquiriesDTO(student, enquiries);
    return ResponseEntity.ok(result);
}
```

### **Step 4: Database Configuration**

#### **4.1 Shared Database Approach (Easier Migration)**
```yaml
# Update application.yml in each service to use the same database
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/restapidemo
    username: root
    password: root
```

#### **4.2 Separate Database Approach (Recommended for Production)**
```yaml
# Each service uses its own database
# student-service
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/student_db

# enquiry-service
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/enquiry_db

# payment-service
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/payment_db
```

### **Step 5: Authentication Service Implementation**

#### **5.1 Create User Entity**
```java
// auth-service/src/main/java/com/example/auth/model/User.java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(unique = true)
    private String username;
    
    private String password;
    private String role;
    
    // getters, setters
}
```

#### **5.2 Create JWT Service**
```java
// auth-service/src/main/java/com/example/auth/service/JwtService.java
@Service
public class JwtService {
    
    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration}")
    private Long expiration;
    
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
```

#### **5.3 Create Auth Controller**
```java
// auth-service/src/main/java/com/example/auth/controller/AuthController.java
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        try {
            String token = authService.authenticate(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(new AuthResponse(token, "Login successful"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(null, "Invalid credentials"));
        }
    }
}
```

### **Step 6: Testing and Validation**

#### **6.1 Test Each Service Independently**
```bash
# Test Student Service
cd microservices/student-service
mvn spring-boot:run
curl http://localhost:8081/actuator/health

# Test Enquiry Service
cd ../enquiry-service
mvn spring-boot:run
curl http://localhost:8082/actuator/health

# Test Payment Service
cd ../payment-service
mvn spring-boot:run
curl http://localhost:8083/actuator/health
```

#### **6.2 Test Service Discovery**
```bash
# Start Eureka Server
cd ../eureka-server
mvn spring-boot:run

# Check Eureka Dashboard
# Open http://localhost:8761 in browser
```

#### **6.3 Test API Gateway**
```bash
# Start API Gateway
cd ../api-gateway
mvn spring-boot:run

# Test routing through gateway
curl http://localhost:8080/api/students
curl http://localhost:8080/api/enquiries
curl http://localhost:8080/api/payments
```

### **Step 7: Deployment**

#### **7.1 Local Deployment**
```bash
# Run the deployment script
cd microservices
chmod +x deploy.sh
./deploy.sh
```

#### **7.2 Docker Deployment**
```bash
# Build and run with Docker Compose
cd microservices
docker-compose up -d

# Check service status
docker-compose ps

# View logs
docker-compose logs -f student-service
```

## 🎯 **Migration Checklist**

- [ ] Copy all model classes to respective services
- [ ] Copy all controllers to respective services
- [ ] Copy all services and implementations to respective services
- [ ] Copy all repositories to respective services
- [ ] Update package declarations in all files
- [ ] Update import statements in all files
- [ ] Configure database connections
- [ ] Implement cross-service communication
- [ ] Set up authentication service
- [ ] Test each service independently
- [ ] Test service discovery
- [ ] Test API gateway routing
- [ ] Deploy with Docker
- [ ] Monitor service health

## 🚀 **Quick Commands**

```bash
# Copy all files at once (Windows)
xcopy "..\RestApiDemo\src\main\java\com\example\demo\*" "microservices\student-service\src\main\java\com\example\student\" /E /I

# Copy all files at once (Linux/Mac)
cp -r ../RestApiDemo/src/main/java/com/example/demo/* microservices/student-service/src/main/java/com/example/student/

# Find and replace package names (Linux/Mac)
find microservices/student-service -name "*.java" -exec sed -i 's/package com.example.demo;/package com.example.student;/g' {} \;

# Build all services
for service in student-service enquiry-service payment-service staff-service contact-service auth-service; do
    cd microservices/$service
    mvn clean package -DskipTests
    cd ..
done
```

This migration guide will help you successfully transform your monolithic application into a microservices architecture! 🚀
