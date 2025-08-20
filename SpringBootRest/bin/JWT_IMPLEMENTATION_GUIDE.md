# JWT Tokenization Implementation Guide

## Overview
This guide explains the JWT (JSON Web Token) implementation in your Spring Boot application for secure authentication and authorization.

## 🚀 **JWT Implementation Features**

### **1. Token Management**
- **Access Token**: Short-lived (24 hours) for API access
- **Refresh Token**: Long-lived (7 days) for token renewal
- **Token Validation**: Automatic validation on each request
- **Token Refresh**: Automatic token renewal mechanism

### **2. Security Features**
- **Password Encryption**: BCrypt password hashing
- **Role-based Access**: STUDENT, STAFF, ADMIN roles
- **Stateless Authentication**: No server-side session storage
- **CORS Support**: Cross-origin request handling

## 📁 **Files Created**

### **Security Components**
1. **`JwtTokenUtil.java`** - JWT token generation and validation
2. **`JwtAuthenticationFilter.java`** - Request interceptor for token validation
3. **`CustomUserDetailsService.java`** - User authentication service
4. **`SecurityConfig.java`** - Spring Security configuration

### **Authentication Components**
1. **`AuthService.java`** - Authentication business logic
2. **`AuthController.java`** - REST endpoints for auth
3. **`AuthRequest.java`** - Login request DTO
4. **`AuthResponse.java`** - Authentication response DTO

## 🔧 **Configuration**

### **application.properties**
```properties
# JWT Configuration
jwt.secret=your-256-bit-secret-key-here-make-it-very-long-and-secure-for-production-use
jwt.expiration=86400000
jwt.refresh-expiration=604800000
jwt.header=Authorization
jwt.prefix=Bearer 
```

### **Security Configuration**
```java
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    // Configured endpoints:
    // /api/auth/** - Public access
    // /api/students/** - STUDENT, STAFF, ADMIN roles
    // /api/staff/** - STAFF role only
    // /api/admin/** - ADMIN role only
}
```

## 🔐 **API Endpoints**

### **Authentication Endpoints**
```
POST /api/auth/login
POST /api/auth/refresh
POST /api/auth/register/student
POST /api/auth/register/staff
POST /api/auth/validate
GET  /api/auth/public/health
```

### **Protected Endpoints**
```
GET    /api/students/** - Requires authentication
POST   /api/students/** - Requires authentication
PUT    /api/students/** - Requires authentication
DELETE /api/students/** - Requires authentication
```

## 📝 **Usage Examples**

### **1. User Registration**
```bash
# Register a student
curl -X POST http://localhost:8080/api/auth/register/student \
  -H "Content-Type: application/json" \
  -d '{
    "studentName": "John Doe",
    "studentEmail": "john@example.com",
    "studentUsername": "johndoe",
    "studentPassword": "password123"
  }'

# Register a staff member
curl -X POST http://localhost:8080/api/auth/register/staff \
  -H "Content-Type: application/json" \
  -d '{
    "staffName": "Jane Smith",
    "staffEmail": "jane@example.com",
    "staffUsername": "janesmith",
    "staffPassword": "password123",
    "staffRole": "TEACHER"
  }'
```

### **2. User Login**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "johndoe",
    "password": "password123"
  }'
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiJ9...",
  "tokenType": "Bearer",
  "username": "johndoe",
  "role": "STUDENT",
  "expiresIn": 86400000
}
```

### **3. Accessing Protected Resources**
```bash
curl -X GET http://localhost:8080/api/students/1 \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9..."
```

### **4. Token Refresh**
```bash
curl -X POST http://localhost:8080/api/auth/refresh \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9..."
```

### **5. Token Validation**
```bash
curl -X POST http://localhost:8080/api/auth/validate \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9..."
```

## 🔒 **Security Features**

### **1. Password Encryption**
```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

### **2. Role-based Authorization**
```java
// In controllers
@PreAuthorize("hasRole('STAFF')")
@GetMapping("/admin-only")
public ResponseEntity<?> adminOnly() {
    // Only STAFF can access
}

@PreAuthorize("hasAnyRole('STUDENT', 'STAFF')")
@GetMapping("/student-staff")
public ResponseEntity<?> studentStaff() {
    // STUDENT and STAFF can access
}
```

### **3. Token Validation**
```java
// Automatic validation on every request
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // Validates JWT token in Authorization header
    // Sets authentication context if valid
}
```

## 🛡️ **Security Best Practices**

### **1. Token Security**
- **Secret Key**: Use a strong 256-bit secret key
- **Token Expiration**: Short-lived access tokens (24 hours)
- **Refresh Tokens**: Long-lived refresh tokens (7 days)
- **HTTPS**: Always use HTTPS in production

### **2. Password Security**
- **BCrypt Hashing**: All passwords are hashed with BCrypt
- **Salt**: BCrypt automatically adds salt
- **Validation**: Password validation on registration

### **3. CORS Configuration**
```java
@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOriginPatterns(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowCredentials(true);
    return source;
}
```

## 📊 **Performance Optimizations**

### **1. Token Caching**
```java
@Cacheable(value = "tokens", key = "#username")
public String generateToken(UserDetails userDetails) {
    // Token generation with caching
}
```

### **2. User Details Caching**
```java
@Cacheable(value = "users", key = "#username")
public UserDetails loadUserByUsername(String username) {
    // User details with caching
}
```

## 🔍 **Monitoring & Debugging**

### **1. Token Validation Endpoint**
```bash
# Check if token is valid
curl -X POST http://localhost:8080/api/auth/validate \
  -H "Authorization: Bearer YOUR_TOKEN"
```

### **2. Health Check**
```bash
# Check authentication service health
curl -X GET http://localhost:8080/api/auth/public/health
```

### **3. Logging**
```properties
# Enable JWT debugging
logging.level.com.example.demo.security=DEBUG
logging.level.org.springframework.security=DEBUG
```

## 🚨 **Error Handling**

### **Common Error Responses**
```json
// Invalid credentials
{
  "error": "Invalid username or password"
}

// Expired token
{
  "error": "Token has expired"
}

// Invalid token
{
  "error": "Invalid token"
}

// Insufficient privileges
{
  "error": "Access denied"
}
```

## 🔄 **Token Lifecycle**

### **1. Login Flow**
```
1. User submits credentials
2. System validates credentials
3. System generates access token (24h) + refresh token (7d)
4. User receives tokens
5. User includes access token in subsequent requests
```

### **2. Token Refresh Flow**
```
1. Access token expires
2. User sends refresh token
3. System validates refresh token
4. System generates new access token + refresh token
5. User receives new tokens
```

### **3. Logout Flow**
```
1. Client discards tokens
2. No server-side logout needed (stateless)
3. Tokens become invalid after expiration
```

## 📋 **Implementation Checklist**

### **✅ Completed**
- [x] JWT token generation and validation
- [x] Password encryption with BCrypt
- [x] Role-based authorization
- [x] Token refresh mechanism
- [x] CORS configuration
- [x] Authentication endpoints
- [x] Security configuration
- [x] User registration
- [x] Token validation endpoint

### **🔄 Next Steps**
- [ ] Implement token blacklisting for logout
- [ ] Add rate limiting for auth endpoints
- [ ] Implement password reset functionality
- [ ] Add email verification
- [ ] Implement 2FA (Two-Factor Authentication)
- [ ] Add audit logging for security events

## 🔧 **Production Configuration**

### **1. Strong Secret Key**
```properties
# Generate a strong secret key
jwt.secret=your-very-long-and-secure-256-bit-secret-key-for-production-use-only
```

### **2. HTTPS Configuration**
```properties
# Enable HTTPS
server.ssl.enabled=true
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=your-keystore-password
```

### **3. Token Expiration**
```properties
# Shorter expiration for production
jwt.expiration=3600000
jwt.refresh-expiration=604800000
```

## 📚 **Testing**

### **1. Test Authentication**
```bash
# Test login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "testuser", "password": "password123"}'
```

### **2. Test Protected Endpoint**
```bash
# Test with valid token
curl -X GET http://localhost:8080/api/students \
  -H "Authorization: Bearer YOUR_TOKEN"
```

### **3. Test Invalid Token**
```bash
# Test with invalid token
curl -X GET http://localhost:8080/api/students \
  -H "Authorization: Bearer invalid-token"
```

This JWT implementation provides a secure, scalable authentication system for your Spring Boot application with proper token management, role-based access control, and performance optimizations. 