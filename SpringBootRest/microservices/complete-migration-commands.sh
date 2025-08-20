#!/bin/bash

# 🚀 Complete Migration Commands for All Microservices
# Run this script from the RestApiDemo root directory

echo "🔄 Starting migration from monolithic to microservices..."

cd microservices

# Create necessary directories first
echo "📁 Creating directory structures..."

# Student Service directories
mkdir -p student-service/src/main/java/com/example/student/model
mkdir -p student-service/src/main/java/com/example/student/controller
mkdir -p student-service/src/main/java/com/example/student/service
mkdir -p student-service/src/main/java/com/example/student/service/impl
mkdir -p student-service/src/main/java/com/example/student/repository
mkdir -p student-service/src/main/java/com/example/student/dto

# Enquiry Service directories
mkdir -p enquiry-service/src/main/java/com/example/enquiry/model
mkdir -p enquiry-service/src/main/java/com/example/enquiry/controller
mkdir -p enquiry-service/src/main/java/com/example/enquiry/service
mkdir -p enquiry-service/src/main/java/com/example/enquiry/service/impl
mkdir -p enquiry-service/src/main/java/com/example/enquiry/repository
mkdir -p enquiry-service/src/main/java/com/example/enquiry/dto

# Payment Service directories
mkdir -p payment-service/src/main/java/com/example/payment/model
mkdir -p payment-service/src/main/java/com/example/payment/controller
mkdir -p payment-service/src/main/java/com/example/payment/service
mkdir -p payment-service/src/main/java/com/example/payment/service/impl
mkdir -p payment-service/src/main/java/com/example/payment/repository
mkdir -p payment-service/src/main/java/com/example/payment/dto

# Staff Service directories
mkdir -p staff-service/src/main/java/com/example/staff/model
mkdir -p staff-service/src/main/java/com/example/staff/controller
mkdir -p staff-service/src/main/java/com/example/staff/service
mkdir -p staff-service/src/main/java/com/example/staff/service/impl
mkdir -p staff-service/src/main/java/com/example/staff/repository
mkdir -p staff-service/src/main/java/com/example/staff/dto

# Contact Service directories
mkdir -p contact-service/src/main/java/com/example/contact/model
mkdir -p contact-service/src/main/java/com/example/contact/controller
mkdir -p contact-service/src/main/java/com/example/contact/service
mkdir -p contact-service/src/main/java/com/example/contact/service/impl
mkdir -p contact-service/src/main/java/com/example/contact/repository
mkdir -p contact-service/src/main/java/com/example/contact/dto

# Auth Service directories
mkdir -p auth-service/src/main/java/com/example/auth/model
mkdir -p auth-service/src/main/java/com/example/auth/controller
mkdir -p auth-service/src/main/java/com/example/auth/service
mkdir -p auth-service/src/main/java/com/example/auth/service/impl
mkdir -p auth-service/src/main/java/com/example/auth/repository
mkdir -p auth-service/src/main/java/com/example/auth/security
mkdir -p auth-service/src/main/java/com/example/auth/dto

echo "📋 Copying files to microservices..."

# ===============================
# 1. STUDENT SERVICE
# ===============================
echo "🎓 Migrating Student Service..."

# Models
cp ../src/main/java/com/example/demo/model/StudentMaster.java student-service/src/main/java/com/example/student/model/
cp ../src/main/java/com/example/demo/model/CourseMaster.java student-service/src/main/java/com/example/student/model/
cp ../src/main/java/com/example/demo/model/BatchMaster.java student-service/src/main/java/com/example/student/model/

# Controllers
cp ../src/main/java/com/example/demo/controller/StudentMasterController.java student-service/src/main/java/com/example/student/controller/
cp ../src/main/java/com/example/demo/controller/CourseMasterController.java student-service/src/main/java/com/example/student/controller/
cp ../src/main/java/com/example/demo/controller/BatchMasterController.java student-service/src/main/java/com/example/student/controller/

# Services
cp ../src/main/java/com/example/demo/service/StudentMasterService.java student-service/src/main/java/com/example/student/service/
cp ../src/main/java/com/example/demo/service/CourseMasterService.java student-service/src/main/java/com/example/student/service/
cp ../src/main/java/com/example/demo/service/BatchMasterService.java student-service/src/main/java/com/example/student/service/

# Service Implementations
cp ../src/main/java/com/example/demo/service/impl/StudentMasterServiceImpl.java student-service/src/main/java/com/example/student/service/impl/
cp ../src/main/java/com/example/demo/service/impl/CourseMasterServiceImpl.java student-service/src/main/java/com/example/student/service/impl/
cp ../src/main/java/com/example/demo/service/impl/BatchMasterServiceImpl.java student-service/src/main/java/com/example/student/service/impl/

# Repositories
cp ../src/main/java/com/example/demo/repository/StudentMasterRepository.java student-service/src/main/java/com/example/student/repository/
cp ../src/main/java/com/example/demo/repository/CourseMasterRepository.java student-service/src/main/java/com/example/student/repository/
cp ../src/main/java/com/example/demo/repository/BatchMasterRepository.java student-service/src/main/java/com/example/student/repository/

# DTOs
cp ../src/main/java/com/example/demo/dto/StudentDTO.java student-service/src/main/java/com/example/student/dto/
cp ../src/main/java/com/example/demo/dto/CourseDTO.java student-service/src/main/java/com/example/student/dto/

# ===============================
# 2. ENQUIRY SERVICE
# ===============================
echo "📋 Migrating Enquiry Service..."

# Models
cp ../src/main/java/com/example/demo/model/Enquiry.java enquiry-service/src/main/java/com/example/enquiry/model/
cp ../src/main/java/com/example/demo/model/Followup.java enquiry-service/src/main/java/com/example/enquiry/model/
cp ../src/main/java/com/example/demo/model/ClosureReasonMaster.java enquiry-service/src/main/java/com/example/enquiry/model/

# Controllers
cp ../src/main/java/com/example/demo/controller/EnquiryController.java enquiry-service/src/main/java/com/example/enquiry/controller/
cp ../src/main/java/com/example/demo/controller/FollowupController.java enquiry-service/src/main/java/com/example/enquiry/controller/
cp ../src/main/java/com/example/demo/controller/ClosureReasonMasterController.java enquiry-service/src/main/java/com/example/enquiry/controller/

# Services
cp ../src/main/java/com/example/demo/service/EnquiryService.java enquiry-service/src/main/java/com/example/enquiry/service/
cp ../src/main/java/com/example/demo/service/FollowupService.java enquiry-service/src/main/java/com/example/enquiry/service/
cp ../src/main/java/com/example/demo/service/ClosureReasonMasterService.java enquiry-service/src/main/java/com/example/enquiry/service/

# Service Implementations
cp ../src/main/java/com/example/demo/service/impl/EnquiryServiceImpl.java enquiry-service/src/main/java/com/example/enquiry/service/impl/
cp ../src/main/java/com/example/demo/service/impl/FollowupServiceImpl.java enquiry-service/src/main/java/com/example/enquiry/service/impl/
cp ../src/main/java/com/example/demo/service/impl/ClosureReasonMasterServiceImpl.java enquiry-service/src/main/java/com/example/enquiry/service/impl/

# Repositories
cp ../src/main/java/com/example/demo/repository/EnquiryRepository.java enquiry-service/src/main/java/com/example/enquiry/repository/
cp ../src/main/java/com/example/demo/repository/FollowupRepository.java enquiry-service/src/main/java/com/example/enquiry/repository/
cp ../src/main/java/com/example/demo/repository/ClosureReasonMasterRepository.java enquiry-service/src/main/java/com/example/enquiry/repository/

# DTOs
cp ../src/main/java/com/example/demo/dto/EnquiryDTO.java enquiry-service/src/main/java/com/example/enquiry/dto/
cp ../src/main/java/com/example/demo/dto/EnquiryClosurePatchDTO.java enquiry-service/src/main/java/com/example/enquiry/dto/

# ===============================
# 3. PAYMENT SERVICE
# ===============================
echo "💳 Migrating Payment Service..."

# Models
cp ../src/main/java/com/example/demo/model/PaymentWithTypeDTO.java payment-service/src/main/java/com/example/payment/model/
cp ../src/main/java/com/example/demo/model/Receipt.java payment-service/src/main/java/com/example/payment/model/
cp ../src/main/java/com/example/demo/model/PaymentTypeMaster.java payment-service/src/main/java/com/example/payment/model/

# Controllers
cp ../src/main/java/com/example/demo/controller/PaymentWithTypeController.java payment-service/src/main/java/com/example/payment/controller/
cp ../src/main/java/com/example/demo/controller/ReceiptController.java payment-service/src/main/java/com/example/payment/controller/
cp ../src/main/java/com/example/demo/controller/PaymentTypeMasterController.java payment-service/src/main/java/com/example/payment/controller/

# Services
cp ../src/main/java/com/example/demo/service/PaymentWithTypeService.java payment-service/src/main/java/com/example/payment/service/
cp ../src/main/java/com/example/demo/service/ReceiptService.java payment-service/src/main/java/com/example/payment/service/
cp ../src/main/java/com/example/demo/service/PaymentTypeMasterService.java payment-service/src/main/java/com/example/payment/service/

# Service Implementations
cp ../src/main/java/com/example/demo/service/impl/PaymentWithTypeServiceImpl.java payment-service/src/main/java/com/example/payment/service/impl/
cp ../src/main/java/com/example/demo/service/impl/ReceiptServiceImpl.java payment-service/src/main/java/com/example/payment/service/impl/
cp ../src/main/java/com/example/demo/service/impl/PaymentTypeMasterServiceImpl.java payment-service/src/main/java/com/example/payment/service/impl/

# Repositories
cp ../src/main/java/com/example/demo/repository/PaymentWithTypeRepository.java payment-service/src/main/java/com/example/payment/repository/
cp ../src/main/java/com/example/demo/repository/ReceiptRepository.java payment-service/src/main/java/com/example/payment/repository/
cp ../src/main/java/com/example/demo/repository/PaymentTypeMasterRepository.java payment-service/src/main/java/com/example/payment/repository/

# ===============================
# 4. STAFF SERVICE
# ===============================
echo "👥 Migrating Staff Service..."

# Models
cp ../src/main/java/com/example/demo/model/StaffMaster.java staff-service/src/main/java/com/example/staff/model/

# Controllers
cp ../src/main/java/com/example/demo/controller/StaffMasterController.java staff-service/src/main/java/com/example/staff/controller/

# Services
cp ../src/main/java/com/example/demo/service/StaffMasterService.java staff-service/src/main/java/com/example/staff/service/

# Service Implementations
cp ../src/main/java/com/example/demo/service/impl/StaffMasterServiceImpl.java staff-service/src/main/java/com/example/staff/service/impl/

# Repositories
cp ../src/main/java/com/example/demo/repository/StaffMasterRepository.java staff-service/src/main/java/com/example/staff/repository/

# DTOs
cp ../src/main/java/com/example/demo/dto/StaffDTO.java staff-service/src/main/java/com/example/staff/dto/

# ===============================
# 5. CONTACT SERVICE
# ===============================
echo "📞 Migrating Contact Service..."

# Models
cp ../src/main/java/com/example/demo/model/ContactUs.java contact-service/src/main/java/com/example/contact/model/

# Controllers
cp ../src/main/java/com/example/demo/controller/ContactUsController.java contact-service/src/main/java/com/example/contact/controller/

# Services
cp ../src/main/java/com/example/demo/service/ContactUsService.java contact-service/src/main/java/com/example/contact/service/

# Service Implementations
cp ../src/main/java/com/example/demo/service/impl/ContactUsServiceImpl.java contact-service/src/main/java/com/example/contact/service/impl/

# Repositories
cp ../src/main/java/com/example/demo/repository/ContactUsRepository.java contact-service/src/main/java/com/example/contact/repository/

# ===============================
# 6. AUTH SERVICE
# ===============================
echo "🔐 Migrating Auth Service..."

# Controllers
cp ../src/main/java/com/example/demo/controller/AuthController.java auth-service/src/main/java/com/example/auth/controller/

# Services
cp ../src/main/java/com/example/demo/service/AuthService.java auth-service/src/main/java/com/example/auth/service/

# Security
cp ../src/main/java/com/example/demo/security/CustomUserDetailsService.java auth-service/src/main/java/com/example/auth/security/
cp ../src/main/java/com/example/demo/security/JwtAuthenticationFilter.java auth-service/src/main/java/com/example/auth/security/
cp ../src/main/java/com/example/demo/security/JwtTokenUtil.java auth-service/src/main/java/com/example/auth/security/
cp ../src/main/java/com/example/demo/security/SecurityConfig.java auth-service/src/main/java/com/example/auth/security/

# DTOs
cp ../src/main/java/com/example/demo/dto/AuthRequest.java auth-service/src/main/java/com/example/auth/dto/
cp ../src/main/java/com/example/demo/dto/AuthResponse.java auth-service/src/main/java/com/example/auth/dto/

# ===============================
# 7. MEDIA/CONTENT SERVICE (Optional)
# ===============================
echo "🎬 Creating Media Service for Gallery content..."

# Create media service directories
mkdir -p media-service/src/main/java/com/example/media/model
mkdir -p media-service/src/main/java/com/example/media/controller
mkdir -p media-service/src/main/java/com/example/media/service
mkdir -p media-service/src/main/java/com/example/media/service/impl
mkdir -p media-service/src/main/java/com/example/media/repository

# Copy media-related files
cp ../src/main/java/com/example/demo/model/AlbumMaster.java media-service/src/main/java/com/example/media/model/
cp ../src/main/java/com/example/demo/model/ImageMaster.java media-service/src/main/java/com/example/media/model/
cp ../src/main/java/com/example/demo/model/VideoMaster.java media-service/src/main/java/com/example/media/model/
cp ../src/main/java/com/example/demo/model/Placement.java media-service/src/main/java/com/example/media/model/

# Controllers
cp ../src/main/java/com/example/demo/controller/AlbumMasterController.java media-service/src/main/java/com/example/media/controller/
cp ../src/main/java/com/example/demo/controller/ImageMasterController.java media-service/src/main/java/com/example/media/controller/
cp ../src/main/java/com/example/demo/controller/VideoMasterController.java media-service/src/main/java/com/example/media/controller/
cp ../src/main/java/com/example/demo/controller/PlacementController.java media-service/src/main/java/com/example/media/controller/

# Services
cp ../src/main/java/com/example/demo/service/AlbumMasterService.java media-service/src/main/java/com/example/media/service/
cp ../src/main/java/com/example/demo/service/ImageMasterService.java media-service/src/main/java/com/example/media/service/
cp ../src/main/java/com/example/demo/service/VideoMasterService.java media-service/src/main/java/com/example/media/service/
cp ../src/main/java/com/example/demo/service/PlacementService.java media-service/src/main/java/com/example/media/service/

# Service Implementations
cp ../src/main/java/com/example/demo/service/impl/AlbumMasterServiceImpl.java media-service/src/main/java/com/example/media/service/impl/
cp ../src/main/java/com/example/demo/service/impl/ImageMasterServiceImpl.java media-service/src/main/java/com/example/media/service/impl/
cp ../src/main/java/com/example/demo/service/impl/VideoMasterServiceImpl.java media-service/src/main/java/com/example/media/service/impl/
cp ../src/main/java/com/example/demo/service/impl/PlacementServiceImpl.java media-service/src/main/java/com/example/media/service/impl/

# Repositories
cp ../src/main/java/com/example/demo/repository/AlbumMasterRepository.java media-service/src/main/java/com/example/media/repository/
cp ../src/main/java/com/example/demo/repository/ImageMasterRepository.java media-service/src/main/java/com/example/media/repository/
cp ../src/main/java/com/example/demo/repository/VideoMasterRepository.java media-service/src/main/java/com/example/media/repository/
cp ../src/main/java/com/example/demo/repository/PlacementRepository.java media-service/src/main/java/com/example/media/repository/

echo "✅ File migration completed!"
echo ""
echo "📋 Next Steps:"
echo "1. Update package names in all copied files"
echo "2. Update import statements"
echo "3. Create missing service configurations"
echo "4. Test each service independently"
echo ""
echo "🚀 Run './update-packages.sh' to automatically update package names"
