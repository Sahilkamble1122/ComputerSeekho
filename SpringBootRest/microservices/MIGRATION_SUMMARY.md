# 🚀 **Complete Microservices Migration Commands**

## 📋 **Overview**
This document provides all the commands needed to migrate your monolithic Spring Boot application to microservices architecture.

## 🎯 **Services Breakdown**

### **1. Student Service** (Port: 8081)
**Files to migrate:**
- **Models**: `StudentMaster.java`, `CourseMaster.java`, `BatchMaster.java`
- **Controllers**: `StudentMasterController.java`, `CourseMasterController.java`, `BatchMasterController.java`
- **Services**: `StudentMasterService.java`, `CourseMasterService.java`, `BatchMasterService.java`
- **Implementations**: `StudentMasterServiceImpl.java`, `CourseMasterServiceImpl.java`, `BatchMasterServiceImpl.java`
- **Repositories**: `StudentMasterRepository.java`, `CourseMasterRepository.java`, `BatchMasterRepository.java`
- **DTOs**: `StudentDTO.java`, `CourseDTO.java`

### **2. Enquiry Service** (Port: 8082)
**Files to migrate:**
- **Models**: `Enquiry.java`, `Followup.java`, `ClosureReasonMaster.java`
- **Controllers**: `EnquiryController.java`, `FollowupController.java`, `ClosureReasonMasterController.java`
- **Services**: `EnquiryService.java`, `FollowupService.java`, `ClosureReasonMasterService.java`
- **Implementations**: `EnquiryServiceImpl.java`, `FollowupServiceImpl.java`, `ClosureReasonMasterServiceImpl.java`
- **Repositories**: `EnquiryRepository.java`, `FollowupRepository.java`, `ClosureReasonMasterRepository.java`
- **DTOs**: `EnquiryDTO.java`, `EnquiryClosurePatchDTO.java`

### **3. Payment Service** (Port: 8083)
**Files to migrate:**
- **Models**: `PaymentWithTypeDTO.java`, `Receipt.java`, `PaymentTypeMaster.java`
- **Controllers**: `PaymentWithTypeController.java`, `ReceiptController.java`, `PaymentTypeMasterController.java`
- **Services**: `PaymentWithTypeService.java`, `ReceiptService.java`, `PaymentTypeMasterService.java`
- **Implementations**: `PaymentWithTypeServiceImpl.java`, `ReceiptServiceImpl.java`, `PaymentTypeMasterServiceImpl.java`
- **Repositories**: `PaymentWithTypeRepository.java`, `ReceiptRepository.java`, `PaymentTypeMasterRepository.java`

### **4. Staff Service** (Port: 8084)
**Files to migrate:**
- **Models**: `StaffMaster.java`
- **Controllers**: `StaffMasterController.java`
- **Services**: `StaffMasterService.java`
- **Implementations**: `StaffMasterServiceImpl.java`
- **Repositories**: `StaffMasterRepository.java`
- **DTOs**: `StaffDTO.java`

### **5. Contact Service** (Port: 8085)
**Files to migrate:**
- **Models**: `ContactUs.java`
- **Controllers**: `ContactUsController.java`
- **Services**: `ContactUsService.java`
- **Implementations**: `ContactUsServiceImpl.java`
- **Repositories**: `ContactUsRepository.java`

### **6. Auth Service** (Port: 8086)
**Files to migrate:**
- **Controllers**: `AuthController.java`
- **Services**: `AuthService.java`
- **Security**: `CustomUserDetailsService.java`, `JwtAuthenticationFilter.java`, `JwtTokenUtil.java`, `SecurityConfig.java`
- **DTOs**: `AuthRequest.java`, `AuthResponse.java`

### **7. Media Service** (Port: 8087) - Optional
**Files to migrate:**
- **Models**: `AlbumMaster.java`, `ImageMaster.java`, `VideoMaster.java`, `Placement.java`
- **Controllers**: `AlbumMasterController.java`, `ImageMasterController.java`, `VideoMasterController.java`, `PlacementController.java`
- **Services**: `AlbumMasterService.java`, `ImageMasterService.java`, `VideoMasterService.java`, `PlacementService.java`
- **Implementations**: `AlbumMasterServiceImpl.java`, `ImageMasterServiceImpl.java`, `VideoMasterServiceImpl.java`, `PlacementServiceImpl.java`
- **Repositories**: `AlbumMasterRepository.java`, `ImageMasterRepository.java`, `VideoMasterRepository.java`, `PlacementRepository.java`

## 🚀 **Migration Commands**

### **For Linux/Mac:**
```bash
cd microservices
chmod +x complete-migration-commands.sh
./complete-migration-commands.sh

# Update package names
chmod +x update-packages.sh
./update-packages.sh
```

### **For Windows:**
```batch
cd microservices
complete-migration-commands.bat

REM Update package names manually or use PowerShell
```

## 📝 **Manual Copy Commands (Individual Services)**

### **Student Service:**
```bash
cd microservices

# Copy StudentMaster files to student-service
cp ../src/main/java/com/example/demo/model/StudentMaster.java student-service/src/main/java/com/example/student/model/
cp ../src/main/java/com/example/demo/controller/StudentMasterController.java student-service/src/main/java/com/example/student/controller/
cp ../src/main/java/com/example/demo/service/StudentMasterService.java student-service/src/main/java/com/example/student/service/
cp ../src/main/java/com/example/demo/service/impl/StudentMasterServiceImpl.java student-service/src/main/java/com/example/student/service/impl/
cp ../src/main/java/com/example/demo/repository/StudentMasterRepository.java student-service/src/main/java/com/example/student/repository/

# Copy Course files to student-service
cp ../src/main/java/com/example/demo/model/CourseMaster.java student-service/src/main/java/com/example/student/model/
cp ../src/main/java/com/example/demo/controller/CourseMasterController.java student-service/src/main/java/com/example/student/controller/
cp ../src/main/java/com/example/demo/service/CourseMasterService.java student-service/src/main/java/com/example/student/service/
cp ../src/main/java/com/example/demo/service/impl/CourseMasterServiceImpl.java student-service/src/main/java/com/example/student/service/impl/
cp ../src/main/java/com/example/demo/repository/CourseMasterRepository.java student-service/src/main/java/com/example/student/repository/

# Copy Batch files to student-service
cp ../src/main/java/com/example/demo/model/BatchMaster.java student-service/src/main/java/com/example/student/model/
cp ../src/main/java/com/example/demo/controller/BatchMasterController.java student-service/src/main/java/com/example/student/controller/
cp ../src/main/java/com/example/demo/service/BatchMasterService.java student-service/src/main/java/com/example/student/service/
cp ../src/main/java/com/example/demo/service/impl/BatchMasterServiceImpl.java student-service/src/main/java/com/example/student/service/impl/
cp ../src/main/java/com/example/demo/repository/BatchMasterRepository.java student-service/src/main/java/com/example/student/repository/

# Copy DTOs
cp ../src/main/java/com/example/demo/dto/StudentDTO.java student-service/src/main/java/com/example/student/dto/
cp ../src/main/java/com/example/demo/dto/CourseDTO.java student-service/src/main/java/com/example/student/dto/
```

### **Enquiry Service:**
```bash
# Copy Enquiry files to enquiry-service
cp ../src/main/java/com/example/demo/model/Enquiry.java enquiry-service/src/main/java/com/example/enquiry/model/
cp ../src/main/java/com/example/demo/controller/EnquiryController.java enquiry-service/src/main/java/com/example/enquiry/controller/
cp ../src/main/java/com/example/demo/service/EnquiryService.java enquiry-service/src/main/java/com/example/enquiry/service/
cp ../src/main/java/com/example/demo/service/impl/EnquiryServiceImpl.java enquiry-service/src/main/java/com/example/enquiry/service/impl/
cp ../src/main/java/com/example/demo/repository/EnquiryRepository.java enquiry-service/src/main/java/com/example/enquiry/repository/

# Copy Followup files to enquiry-service
cp ../src/main/java/com/example/demo/model/Followup.java enquiry-service/src/main/java/com/example/enquiry/model/
cp ../src/main/java/com/example/demo/controller/FollowupController.java enquiry-service/src/main/java/com/example/enquiry/controller/
cp ../src/main/java/com/example/demo/service/FollowupService.java enquiry-service/src/main/java/com/example/enquiry/service/
cp ../src/main/java/com/example/demo/service/impl/FollowupServiceImpl.java enquiry-service/src/main/java/com/example/enquiry/service/impl/
cp ../src/main/java/com/example/demo/repository/FollowupRepository.java enquiry-service/src/main/java/com/example/enquiry/repository/

# Copy ClosureReasonMaster files to enquiry-service
cp ../src/main/java/com/example/demo/model/ClosureReasonMaster.java enquiry-service/src/main/java/com/example/enquiry/model/
cp ../src/main/java/com/example/demo/controller/ClosureReasonMasterController.java enquiry-service/src/main/java/com/example/enquiry/controller/
cp ../src/main/java/com/example/demo/service/ClosureReasonMasterService.java enquiry-service/src/main/java/com/example/enquiry/service/
cp ../src/main/java/com/example/demo/service/impl/ClosureReasonMasterServiceImpl.java enquiry-service/src/main/java/com/example/enquiry/service/impl/
cp ../src/main/java/com/example/demo/repository/ClosureReasonMasterRepository.java enquiry-service/src/main/java/com/example/enquiry/repository/

# Copy DTOs
cp ../src/main/java/com/example/demo/dto/EnquiryDTO.java enquiry-service/src/main/java/com/example/enquiry/dto/
cp ../src/main/java/com/example/demo/dto/EnquiryClosurePatchDTO.java enquiry-service/src/main/java/com/example/enquiry/dto/
```

### **Payment Service:**
```bash
# Copy PaymentWithTypeDTO files to payment-service
cp ../src/main/java/com/example/demo/model/PaymentWithTypeDTO.java payment-service/src/main/java/com/example/payment/model/
cp ../src/main/java/com/example/demo/controller/PaymentWithTypeController.java payment-service/src/main/java/com/example/payment/controller/
cp ../src/main/java/com/example/demo/service/PaymentWithTypeService.java payment-service/src/main/java/com/example/payment/service/
cp ../src/main/java/com/example/demo/service/impl/PaymentWithTypeServiceImpl.java payment-service/src/main/java/com/example/payment/service/impl/
cp ../src/main/java/com/example/demo/repository/PaymentWithTypeRepository.java payment-service/src/main/java/com/example/payment/repository/

# Copy Receipt files to payment-service
cp ../src/main/java/com/example/demo/model/Receipt.java payment-service/src/main/java/com/example/payment/model/
cp ../src/main/java/com/example/demo/controller/ReceiptController.java payment-service/src/main/java/com/example/payment/controller/
cp ../src/main/java/com/example/demo/service/ReceiptService.java payment-service/src/main/java/com/example/payment/service/
cp ../src/main/java/com/example/demo/service/impl/ReceiptServiceImpl.java payment-service/src/main/java/com/example/payment/service/impl/
cp ../src/main/java/com/example/demo/repository/ReceiptRepository.java payment-service/src/main/java/com/example/payment/repository/

# Copy PaymentTypeMaster files to payment-service
cp ../src/main/java/com/example/demo/model/PaymentTypeMaster.java payment-service/src/main/java/com/example/payment/model/
cp ../src/main/java/com/example/demo/controller/PaymentTypeMasterController.java payment-service/src/main/java/com/example/payment/controller/
cp ../src/main/java/com/example/demo/service/PaymentTypeMasterService.java payment-service/src/main/java/com/example/payment/service/
cp ../src/main/java/com/example/demo/service/impl/PaymentTypeMasterServiceImpl.java payment-service/src/main/java/com/example/payment/service/impl/
cp ../src/main/java/com/example/demo/repository/PaymentTypeMasterRepository.java payment-service/src/main/java/com/example/payment/repository/
```

### **Staff Service:**
```bash
# Copy StaffMaster files to staff-service
cp ../src/main/java/com/example/demo/model/StaffMaster.java staff-service/src/main/java/com/example/staff/model/
cp ../src/main/java/com/example/demo/controller/StaffMasterController.java staff-service/src/main/java/com/example/staff/controller/
cp ../src/main/java/com/example/demo/service/StaffMasterService.java staff-service/src/main/java/com/example/staff/service/
cp ../src/main/java/com/example/demo/service/impl/StaffMasterServiceImpl.java staff-service/src/main/java/com/example/staff/service/impl/
cp ../src/main/java/com/example/demo/repository/StaffMasterRepository.java staff-service/src/main/java/com/example/staff/repository/
cp ../src/main/java/com/example/demo/dto/StaffDTO.java staff-service/src/main/java/com/example/staff/dto/
```

### **Contact Service:**
```bash
# Copy ContactUs files to contact-service
cp ../src/main/java/com/example/demo/model/ContactUs.java contact-service/src/main/java/com/example/contact/model/
cp ../src/main/java/com/example/demo/controller/ContactUsController.java contact-service/src/main/java/com/example/contact/controller/
cp ../src/main/java/com/example/demo/service/ContactUsService.java contact-service/src/main/java/com/example/contact/service/
cp ../src/main/java/com/example/demo/service/impl/ContactUsServiceImpl.java contact-service/src/main/java/com/example/contact/service/impl/
cp ../src/main/java/com/example/demo/repository/ContactUsRepository.java contact-service/src/main/java/com/example/contact/repository/
```

### **Auth Service:**
```bash
# Copy Auth files to auth-service
cp ../src/main/java/com/example/demo/controller/AuthController.java auth-service/src/main/java/com/example/auth/controller/
cp ../src/main/java/com/example/demo/service/AuthService.java auth-service/src/main/java/com/example/auth/service/

# Copy Security files to auth-service
cp ../src/main/java/com/example/demo/security/CustomUserDetailsService.java auth-service/src/main/java/com/example/auth/security/
cp ../src/main/java/com/example/demo/security/JwtAuthenticationFilter.java auth-service/src/main/java/com/example/auth/security/
cp ../src/main/java/com/example/demo/security/JwtTokenUtil.java auth-service/src/main/java/com/example/auth/security/
cp ../src/main/java/com/example/demo/security/SecurityConfig.java auth-service/src/main/java/com/example/auth/security/

# Copy DTOs to auth-service
cp ../src/main/java/com/example/demo/dto/AuthRequest.java auth-service/src/main/java/com/example/auth/dto/
cp ../src/main/java/com/example/demo/dto/AuthResponse.java auth-service/src/main/java/com/example/auth/dto/
```

## 📝 **Package Update Commands**

After copying all files, update package names:

```bash
# Student Service
find student-service -name "*.java" -type f -exec sed -i 's/package com\.example\.demo/package com.example.student/g' {} \;
find student-service -name "*.java" -type f -exec sed -i 's/import com\.example\.demo\./import com.example.student./g' {} \;

# Enquiry Service
find enquiry-service -name "*.java" -type f -exec sed -i 's/package com\.example\.demo/package com.example.enquiry/g' {} \;
find enquiry-service -name "*.java" -type f -exec sed -i 's/import com\.example\.demo\./import com.example.enquiry./g' {} \;

# Payment Service
find payment-service -name "*.java" -type f -exec sed -i 's/package com\.example\.demo/package com.example.payment/g' {} \;
find payment-service -name "*.java" -type f -exec sed -i 's/import com\.example\.demo\./import com.example.payment./g' {} \;

# Staff Service
find staff-service -name "*.java" -type f -exec sed -i 's/package com\.example\.demo/package com.example.staff/g' {} \;
find staff-service -name "*.java" -type f -exec sed -i 's/import com\.example\.demo\./import com.example.staff./g' {} \;

# Contact Service
find contact-service -name "*.java" -type f -exec sed -i 's/package com\.example\.demo/package com.example.contact/g' {} \;
find contact-service -name "*.java" -type f -exec sed -i 's/import com\.example\.demo\./import com.example.contact./g' {} \;

# Auth Service
find auth-service -name "*.java" -type f -exec sed -i 's/package com\.example\.demo/package com.example.auth/g' {} \;
find auth-service -name "*.java" -type f -exec sed -i 's/import com\.example\.demo\./import com.example.auth./g' {} \;
```

## 🎯 **Next Steps After Migration**

1. **Test Each Service**:
   ```bash
   cd student-service && mvn clean compile
   cd ../enquiry-service && mvn clean compile
   cd ../payment-service && mvn clean compile
   cd ../staff-service && mvn clean compile
   cd ../contact-service && mvn clean compile
   cd ../auth-service && mvn clean compile
   ```

2. **Deploy Services**:
   ```bash
   cd microservices
   ./deploy.sh
   ```

3. **Access Services**:
   - **Eureka Dashboard**: http://localhost:8761
   - **API Gateway**: http://localhost:8080
   - **Student Service**: http://localhost:8081
   - **Enquiry Service**: http://localhost:8082
   - **Payment Service**: http://localhost:8083
   - **Staff Service**: http://localhost:8084
   - **Contact Service**: http://localhost:8085
   - **Auth Service**: http://localhost:8086

## 🚀 **Benefits After Migration**

- ✅ **Independent Scaling**: Scale each service based on demand
- ✅ **Technology Flexibility**: Use different technologies per service
- ✅ **Team Independence**: Different teams can work on different services
- ✅ **Fault Isolation**: One service failure doesn't affect others
- ✅ **Independent Deployment**: Deploy services separately
- ✅ **Better Testing**: Test services in isolation

---

**Ready to transform your monolithic application into microservices! 🎉**
