# JWT Authentication Implementation

This document explains how JWT authentication has been implemented in the DotNetRest project.

## Overview

JWT (JSON Web Token) authentication has been implemented to secure the API endpoints. The system uses the existing `StaffMaster` table for user authentication.

## Configuration

### JWT Settings (appsettings.json)

```json
{
  "JwtSettings": {
    "SecretKey": "your-super-secret-key-with-at-least-32-characters",
    "Issuer": "DotNetRest",
    "Audience": "DotNetRestUsers",
    "ExpirationInMinutes": 60
  }
}
```

**Important**: Change the `SecretKey` to a secure, unique key in production.

## Authentication Flow

### 1. Login

**Endpoint**: `POST /api/auth/login`

**Request Body**:
```json
{
  "username": "staff_username",
  "password": "staff_password"
}
```

**Response**:
```json
{
  "success": true,
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "username": "staff_username",
  "role": "Admin",
  "expiresAt": "2024-01-01T12:00:00Z",
  "message": "Login successful"
}
```

### 2. Using the Token

Include the JWT token in the `Authorization` header for protected endpoints:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

## Protecting Endpoints

### Controller Level Protection

```csharp
[ApiController]
[Route("api/staffs")]
[JwtAuthorize] // Protects all endpoints in this controller
public class StaffController : ControllerBase
{
    // All endpoints require authentication
}
```

### Method Level Protection

```csharp
[HttpPost]
[JwtAuthorize("Admin")] // Requires Admin role
public async Task<IActionResult> CreateStaff([FromBody] StaffMaster staff)
{
    // Only Admin users can access this endpoint
}
```

### Role-Based Authorization

You can specify multiple roles:

```csharp
[JwtAuthorize("Admin", "Manager")] // Requires Admin OR Manager role
```

## Available Roles

The system uses the `StaffRole` field from the `StaffMaster` table. Common roles include:

- `Admin` - Full access to all endpoints
- `Manager` - Limited administrative access
- `User` - Basic access (default role)

## Token Validation

### Validate Token Endpoint

**Endpoint**: `POST /api/auth/validate`

**Headers**:
```
Authorization: Bearer your-jwt-token
```

**Response**:
```json
{
  "message": "Token is valid"
}
```

## Security Features

1. **Token Expiration**: Tokens expire after 60 minutes (configurable)
2. **Role-Based Access**: Different endpoints require different roles
3. **Secure Token Validation**: Uses HMAC SHA256 for token signing
4. **CORS Support**: Configured to work with frontend applications

## Error Responses

### Unauthorized (401)
```json
{
  "success": false,
  "message": "Invalid username or password"
}
```

### Forbidden (403)
```json
{
  "message": "Access denied"
}
```

## Implementation Details

### Services

- `IJwtService` / `JwtService`: Handles JWT token generation and validation
- `IAuthService` / `AuthService`: Handles authentication logic
- `JwtAuthorizeAttribute`: Custom authorization attribute

### Models

- `LoginRequest`: Login request model
- `LoginResponse`: Login response model
- `JwtSettings`: JWT configuration model

## Testing

### 1. Create a Staff User

First, create a staff user in your database:

```sql
INSERT INTO staff_master (staff_username, staff_password, staff_role, staff_name, staff_email)
VALUES ('admin', 'admin123', 'Admin', 'Administrator', 'admin@example.com');
```

### 2. Login

```bash
curl -X POST "https://localhost:7001/api/auth/login" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123"
  }'
```

### 3. Use Protected Endpoints

```bash
curl -X GET "https://localhost:7001/api/staffs" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## Production Considerations

1. **Change the Secret Key**: Use a strong, unique secret key
2. **Use HTTPS**: Always use HTTPS in production
3. **Password Hashing**: Implement proper password hashing (BCrypt, Argon2)
4. **Token Refresh**: Consider implementing token refresh mechanism
5. **Rate Limiting**: Add rate limiting to prevent brute force attacks
6. **Audit Logging**: Log authentication attempts and failures

## Troubleshooting

### Common Issues

1. **Token Expired**: Check the token expiration time
2. **Invalid Role**: Ensure the user has the required role
3. **Missing Authorization Header**: Include the Bearer token in the Authorization header
4. **CORS Issues**: Check CORS configuration for frontend applications

### Debug Mode

Enable detailed error messages in development:

```json
{
  "Logging": {
    "LogLevel": {
      "Default": "Information",
      "Microsoft.AspNetCore": "Warning"
    }
  }
}
```
