# 🚀 REST API ENDPOINTS DOCUMENTATION

## 📋 **Base URL: `http://localhost:8080`**

---

## 🧪 **TEST ENDPOINTS**
**Base Path: `/api/test`**

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/test` | Basic test endpoint |
| `GET` | `/api/test/health` | Health check endpoint |
| `GET` | `/api/test/info` | Application information |

---

## 👨‍🎓 **STUDENT MANAGEMENT**
**Base Path: `/api/students`**

### 📖 **READ OPERATIONS**

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/students` | Get all students |
| `GET` | `/api/students/{studentId}` | Get student by ID |
| `GET` | `/api/students/search/name/{studentName}` | Search students by name |
| `GET` | `/api/students/search/email/{email}` | Search students by email |
| `GET` | `/api/students/search/mobile/{mobile}` | Search students by mobile |
| `GET` | `/api/students/search/gender/{gender}` | Search students by gender |
| `GET` | `/api/students/search/qualification/{qualification}` | Search students by qualification |
| `GET` | `/api/students/batch/{batchId}` | Get students by batch ID |
| `GET` | `/api/students/course/{courseId}` | Get students by course ID |
| `GET` | `/api/students/search/name-contains/{studentName}` | Search students by name containing |
| `GET` | `/api/students/search/email-contains/{email}` | Search students by email containing |
| `GET` | `/api/students/search/email-domain/{domain}` | Search students by email domain |
| `GET` | `/api/students/count/course/{courseId}` | Count students by course ID |
| `GET` | `/api/students/count/batch/{batchId}` | Count students by batch ID |

### ✏️ **UPDATE OPERATIONS**

| Method | Endpoint | Description |
|--------|----------|-------------|
| `PUT` | `/api/students/{studentId}` | Update student completely |
| `PATCH` | `/api/students/{studentId}/name` | Update student name |
| `PATCH` | `/api/students/{studentId}/email` | Update student email |
| `PATCH` | `/api/students/{studentId}/mobile` | Update student mobile |
| `PATCH` | `/api/students/{studentId}/address` | Update student address |
| `PATCH` | `/api/students/{studentId}/qualification` | Update student qualification |

### ➕ **CREATE OPERATIONS**

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/students` | Create new student |
| `POST` | `/api/students/bulk` | Create multiple students |

### 🗑️ **DELETE OPERATIONS**

| Method | Endpoint | Description |
|--------|----------|-------------|
| `DELETE` | `/api/students/{studentId}` | Delete student by ID |
| `DELETE` | `/api/students/batch/{batchId}` | Delete students by batch ID |
| `DELETE` | `/api/students/course/{courseId}` | Delete students by course ID |
| `DELETE` | `/api/students/all` | Delete all students |

### 🔍 **ADVANCED SEARCH & BUSINESS LOGIC**

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/students/search/global/{searchTerm}` | Global search across all fields |
| `GET` | `/api/students/search/date-range` | Search by date range and course |
| `GET` | `/api/students/pagination` | Get students with pagination |
| `GET` | `/api/students/sorted/name` | Get students sorted by name |
| `GET` | `/api/students/sorted/email` | Get students sorted by email |
| `GET` | `/api/students/sorted/batch` | Get students sorted by batch ID |
| `POST` | `/api/students/authenticate` | Student authentication |
| `GET` | `/api/students/search/email-pattern/{pattern}` | Search by email pattern |

---

## 📞 **ENQUIRY MANAGEMENT**
**Base Path: `/api/enquiries`**

### 📖 **READ OPERATIONS**

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/enquiries` | Get all enquiries |
| `GET` | `/api/enquiries/{enquiryId}` | Get enquiry by ID |
| `GET` | `/api/enquiries/search/name/{studentName}` | Search enquiries by student name |
| `GET` | `/api/enquiries/search/mobile/{mobile}` | Search enquiries by mobile |
| `GET` | `/api/enquiries/search/email/{email}` | Search enquiries by email |
| `GET` | `/api/enquiries/search/status/{status}` | Search enquiries by status |
| `GET` | `/api/enquiries/search/name-contains/{studentName}` | Search enquiries by name containing |
| `GET` | `/api/enquiries/search/email-contains/{email}` | Search enquiries by email containing |
| `GET` | `/api/enquiries/search/date-range` | Search enquiries by date range |
| `GET` | `/api/enquiries/count/status/{status}` | Count enquiries by status |

### ✏️ **UPDATE OPERATIONS**

| Method | Endpoint | Description |
|--------|----------|-------------|
| `PUT` | `/api/enquiries/{enquiryId}` | Update enquiry completely |
| `PATCH` | `/api/enquiries/{enquiryId}/status` | Update enquiry status |
| `PATCH` | `/api/enquiries/{enquiryId}/name` | Update enquiry student name |
| `PATCH` | `/api/enquiries/{enquiryId}/mobile` | Update enquiry mobile |
| `PATCH` | `/api/enquiries/{enquiryId}/email` | Update enquiry email |

### ➕ **CREATE OPERATIONS**

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/enquiries` | Create new enquiry |
| `POST` | `/api/enquiries/bulk` | Create multiple enquiries |

### 🗑️ **DELETE OPERATIONS**

| Method | Endpoint | Description |
|--------|----------|-------------|
| `DELETE` | `/api/enquiries/{enquiryId}` | Delete enquiry by ID |
| `DELETE` | `/api/enquiries/status/{status}` | Delete enquiries by status |
| `DELETE` | `/api/enquiries/all` | Delete all enquiries |

### 🔍 **ADVANCED SEARCH & BUSINESS LOGIC**

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/enquiries/search/global/{searchTerm}` | Global search across all fields |
| `GET` | `/api/enquiries/search/date-range-course` | Search by date range and course |
| `GET` | `/api/enquiries/pagination` | Get enquiries with pagination |
| `GET` | `/api/enquiries/sorted/date` | Get enquiries sorted by date |
| `GET` | `/api/enquiries/sorted/name` | Get enquiries sorted by student name |
| `GET` | `/api/enquiries/sorted/status` | Get enquiries sorted by status |
| `GET` | `/api/enquiries/pending` | Get pending enquiries |
| `GET` | `/api/enquiries/completed` | Get completed enquiries |
| `GET` | `/api/enquiries/follow-up-required` | Get follow-up required enquiries |

---

## 📚 **COURSE MANAGEMENT**
**Base Path: `/api/courses`**

### 📖 **READ OPERATIONS**

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/courses` | Get all courses |
| `GET` | `/api/courses/{courseId}` | Get course by ID |
| `GET` | `/api/courses/search/name/{courseName}` | Search courses by name |
| `GET` | `/api/courses/search/description/{description}` | Search courses by description |
| `GET` | `/api/courses/search/age-group/{ageGroup}` | Search courses by age group |
| `GET` | `/api/courses/active` | Get active courses |
| `GET` | `/api/courses/search/name-contains/{courseName}` | Search courses by name containing |
| `GET` | `/api/courses/search/age-group-contains/{ageGroup}` | Search courses by age group containing |
| `GET` | `/api/courses/search/duration-range` | Search courses by duration range |
| `GET` | `/api/courses/count/active` | Count active courses |
| `GET` | `/api/courses/count/age-group/{ageGroup}` | Count courses by age group |

### ✏️ **UPDATE OPERATIONS**

| Method | Endpoint | Description |
|--------|----------|-------------|
| `PUT` | `/api/courses/{courseId}` | Update course completely |
| `PATCH` | `/api/courses/{courseId}/status` | Update course status |
| `PATCH` | `/api/courses/{courseId}/name` | Update course name |
| `PATCH` | `/api/courses/{courseId}/description` | Update course description |
| `PATCH` | `/api/courses/{courseId}/duration` | Update course duration |
| `PATCH` | `/api/courses/{courseId}/syllabus` | Update course syllabus |

### ➕ **CREATE OPERATIONS**

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/courses` | Create new course |
| `POST` | `/api/courses/bulk` | Create multiple courses |

### 🗑️ **DELETE OPERATIONS**

| Method | Endpoint | Description |
|--------|----------|-------------|
| `DELETE` | `/api/courses/{courseId}` | Delete course by ID |
| `DELETE` | `/api/courses/age-group/{ageGroup}` | Delete courses by age group |
| `DELETE` | `/api/courses/inactive` | Delete inactive courses |
| `DELETE` | `/api/courses/all` | Delete all courses |

### 🔍 **ADVANCED SEARCH & BUSINESS LOGIC**

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/courses/search/global/{searchTerm}` | Global search across all fields |
| `GET` | `/api/courses/search/duration-age-group` | Search by duration and age group |
| `GET` | `/api/courses/pagination` | Get courses with pagination |
| `GET` | `/api/courses/sorted/name` | Get courses sorted by name |
| `GET` | `/api/courses/sorted/duration` | Get courses sorted by duration |
| `GET` | `/api/courses/sorted/age-group` | Get courses sorted by age group |
| `GET` | `/api/courses/with-video` | Get courses with video content |
| `GET` | `/api/courses/search/syllabus/{keyword}` | Search courses by syllabus keyword |

---

## 🧪 **TESTING THE API**

### **1. Health Check:**
```bash
curl http://localhost:8080/api/test/health
```

### **2. Get All Students:**
```bash
curl http://localhost:8080/api/students
```

### **3. Get All Enquiries:**
```bash
curl http://localhost:8080/api/enquiries
```

### **4. Get All Courses:**
```bash
curl http://localhost:8080/api/courses
```

### **5. Search Students by Name:**
```bash
curl http://localhost:8080/api/students/search/name/Rahul
```

### **6. Get Pending Enquiries:**
```bash
curl http://localhost:8080/api/enquiries/pending
```

### **7. Get Active Courses:**
```bash
curl http://localhost:8080/api/courses/active
```

---

## 🔧 **TROUBLESHOOTING**

### **If URLs are not working:**

1. **Check if application is running:**
   ```bash
   curl http://localhost:8080/api/test/health
   ```

2. **Check application logs:**
   ```bash
   mvn spring-boot:run
   ```

3. **Verify database connection:**
   - Check `application.properties` for correct database credentials
   - Ensure MySQL server is running

4. **Check for compilation errors:**
   ```bash
   mvn clean compile
   ```

5. **Verify CORS settings:**
   - All controllers have `@CrossOrigin(origins = "*")`

---

## 📊 **RESPONSE FORMATS**

### **Success Response:**
```json
{
  "status": "success",
  "data": [...],
  "message": "Operation completed successfully"
}
```

### **Error Response:**
```json
{
  "status": "error",
  "message": "Error description",
  "timestamp": "2024-01-15T10:30:00"
}
```

---

## 🎯 **COMMON HTTP STATUS CODES**

| Code | Description |
|------|-------------|
| `200` | OK - Request successful |
| `201` | Created - Resource created successfully |
| `400` | Bad Request - Invalid input |
| `404` | Not Found - Resource not found |
| `500` | Internal Server Error - Server error |

---

## 📝 **USAGE EXAMPLES**

### **Create a Student:**
```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "studentName": "John Doe",
    "studentEmail": "john.doe@email.com",
    "studentMobile": 9876543210,
    "studentAddress": "123 Main Street",
    "studentGender": "Male",
    "studentQualification": "B.Tech",
    "batchId": 1,
    "courseId": 1
  }'
```

### **Update Student Email:**
```bash
curl -X PATCH "http://localhost:8080/api/students/1/email?email=newemail@example.com"
```

### **Search Enquiries by Status:**
```bash
curl http://localhost:8080/api/enquiries/search/status/pending
```

---

## ✅ **VERIFICATION CHECKLIST**

- [ ] Application compiles successfully
- [ ] Database connection is working
- [ ] All controllers are properly annotated
- [ ] CORS is enabled for frontend integration
- [ ] Dummy data is loaded correctly
- [ ] All endpoints return proper HTTP status codes

Your Spring Boot REST API is now ready with comprehensive endpoints for student, enquiry, and course management! 