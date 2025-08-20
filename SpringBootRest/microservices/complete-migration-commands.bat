@echo off
REM 🚀 Complete Migration Commands for All Microservices (Windows)
REM Run this script from the RestApiDemo root directory

echo 🔄 Starting migration from monolithic to microservices...

cd microservices

REM Create necessary directories first
echo 📁 Creating directory structures...

REM Student Service directories
mkdir student-service\src\main\java\com\example\student\model 2>nul
mkdir student-service\src\main\java\com\example\student\controller 2>nul
mkdir student-service\src\main\java\com\example\student\service 2>nul
mkdir student-service\src\main\java\com\example\student\service\impl 2>nul
mkdir student-service\src\main\java\com\example\student\repository 2>nul
mkdir student-service\src\main\java\com\example\student\dto 2>nul

REM Enquiry Service directories
mkdir enquiry-service\src\main\java\com\example\enquiry\model 2>nul
mkdir enquiry-service\src\main\java\com\example\enquiry\controller 2>nul
mkdir enquiry-service\src\main\java\com\example\enquiry\service 2>nul
mkdir enquiry-service\src\main\java\com\example\enquiry\service\impl 2>nul
mkdir enquiry-service\src\main\java\com\example\enquiry\repository 2>nul
mkdir enquiry-service\src\main\java\com\example\enquiry\dto 2>nul

REM Payment Service directories
mkdir payment-service\src\main\java\com\example\payment\model 2>nul
mkdir payment-service\src\main\java\com\example\payment\controller 2>nul
mkdir payment-service\src\main\java\com\example\payment\service 2>nul
mkdir payment-service\src\main\java\com\example\payment\service\impl 2>nul
mkdir payment-service\src\main\java\com\example\payment\repository 2>nul
mkdir payment-service\src\main\java\com\example\payment\dto 2>nul

REM Staff Service directories
mkdir staff-service\src\main\java\com\example\staff\model 2>nul
mkdir staff-service\src\main\java\com\example\staff\controller 2>nul
mkdir staff-service\src\main\java\com\example\staff\service 2>nul
mkdir staff-service\src\main\java\com\example\staff\service\impl 2>nul
mkdir staff-service\src\main\java\com\example\staff\repository 2>nul
mkdir staff-service\src\main\java\com\example\staff\dto 2>nul

REM Contact Service directories
mkdir contact-service\src\main\java\com\example\contact\model 2>nul
mkdir contact-service\src\main\java\com\example\contact\controller 2>nul
mkdir contact-service\src\main\java\com\example\contact\service 2>nul
mkdir contact-service\src\main\java\com\example\contact\service\impl 2>nul
mkdir contact-service\src\main\java\com\example\contact\repository 2>nul
mkdir contact-service\src\main\java\com\example\contact\dto 2>nul

REM Auth Service directories
mkdir auth-service\src\main\java\com\example\auth\model 2>nul
mkdir auth-service\src\main\java\com\example\auth\controller 2>nul
mkdir auth-service\src\main\java\com\example\auth\service 2>nul
mkdir auth-service\src\main\java\com\example\auth\service\impl 2>nul
mkdir auth-service\src\main\java\com\example\auth\repository 2>nul
mkdir auth-service\src\main\java\com\example\auth\security 2>nul
mkdir auth-service\src\main\java\com\example\auth\dto 2>nul

echo 📋 Copying files to microservices...

REM ===============================
REM 1. STUDENT SERVICE
REM ===============================
echo 🎓 Migrating Student Service...

REM Models
copy ..\src\main\java\com\example\demo\model\StudentMaster.java student-service\src\main\java\com\example\student\model\
copy ..\src\main\java\com\example\demo\model\CourseMaster.java student-service\src\main\java\com\example\student\model\
copy ..\src\main\java\com\example\demo\model\BatchMaster.java student-service\src\main\java\com\example\student\model\

REM Controllers
copy ..\src\main\java\com\example\demo\controller\StudentMasterController.java student-service\src\main\java\com\example\student\controller\
copy ..\src\main\java\com\example\demo\controller\CourseMasterController.java student-service\src\main\java\com\example\student\controller\
copy ..\src\main\java\com\example\demo\controller\BatchMasterController.java student-service\src\main\java\com\example\student\controller\

REM Services
copy ..\src\main\java\com\example\demo\service\StudentMasterService.java student-service\src\main\java\com\example\student\service\
copy ..\src\main\java\com\example\demo\service\CourseMasterService.java student-service\src\main\java\com\example\student\service\
copy ..\src\main\java\com\example\demo\service\BatchMasterService.java student-service\src\main\java\com\example\student\service\

REM Service Implementations
copy ..\src\main\java\com\example\demo\service\impl\StudentMasterServiceImpl.java student-service\src\main\java\com\example\student\service\impl\
copy ..\src\main\java\com\example\demo\service\impl\CourseMasterServiceImpl.java student-service\src\main\java\com\example\student\service\impl\
copy ..\src\main\java\com\example\demo\service\impl\BatchMasterServiceImpl.java student-service\src\main\java\com\example\student\service\impl\

REM Repositories
copy ..\src\main\java\com\example\demo\repository\StudentMasterRepository.java student-service\src\main\java\com\example\student\repository\
copy ..\src\main\java\com\example\demo\repository\CourseMasterRepository.java student-service\src\main\java\com\example\student\repository\
copy ..\src\main\java\com\example\demo\repository\BatchMasterRepository.java student-service\src\main\java\com\example\student\repository\

REM DTOs
copy ..\src\main\java\com\example\demo\dto\StudentDTO.java student-service\src\main\java\com\example\student\dto\
copy ..\src\main\java\com\example\demo\dto\CourseDTO.java student-service\src\main\java\com\example\student\dto\

REM ===============================
REM 2. ENQUIRY SERVICE
REM ===============================
echo 📋 Migrating Enquiry Service...

REM Models
copy ..\src\main\java\com\example\demo\model\Enquiry.java enquiry-service\src\main\java\com\example\enquiry\model\
copy ..\src\main\java\com\example\demo\model\Followup.java enquiry-service\src\main\java\com\example\enquiry\model\
copy ..\src\main\java\com\example\demo\model\ClosureReasonMaster.java enquiry-service\src\main\java\com\example\enquiry\model\

REM Controllers
copy ..\src\main\java\com\example\demo\controller\EnquiryController.java enquiry-service\src\main\java\com\example\enquiry\controller\
copy ..\src\main\java\com\example\demo\controller\FollowupController.java enquiry-service\src\main\java\com\example\enquiry\controller\
copy ..\src\main\java\com\example\demo\controller\ClosureReasonMasterController.java enquiry-service\src\main\java\com\example\enquiry\controller\

REM Services
copy ..\src\main\java\com\example\demo\service\EnquiryService.java enquiry-service\src\main\java\com\example\enquiry\service\
copy ..\src\main\java\com\example\demo\service\FollowupService.java enquiry-service\src\main\java\com\example\enquiry\service\
copy ..\src\main\java\com\example\demo\service\ClosureReasonMasterService.java enquiry-service\src\main\java\com\example\enquiry\service\

REM Service Implementations
copy ..\src\main\java\com\example\demo\service\impl\EnquiryServiceImpl.java enquiry-service\src\main\java\com\example\enquiry\service\impl\
copy ..\src\main\java\com\example\demo\service\impl\FollowupServiceImpl.java enquiry-service\src\main\java\com\example\enquiry\service\impl\
copy ..\src\main\java\com\example\demo\service\impl\ClosureReasonMasterServiceImpl.java enquiry-service\src\main\java\com\example\enquiry\service\impl\

REM Repositories
copy ..\src\main\java\com\example\demo\repository\EnquiryRepository.java enquiry-service\src\main\java\com\example\enquiry\repository\
copy ..\src\main\java\com\example\demo\repository\FollowupRepository.java enquiry-service\src\main\java\com\example\enquiry\repository\
copy ..\src\main\java\com\example\demo\repository\ClosureReasonMasterRepository.java enquiry-service\src\main\java\com\example\enquiry\repository\

REM DTOs
copy ..\src\main\java\com\example\demo\dto\EnquiryDTO.java enquiry-service\src\main\java\com\example\enquiry\dto\
copy ..\src\main\java\com\example\demo\dto\EnquiryClosurePatchDTO.java enquiry-service\src\main\java\com\example\enquiry\dto\

REM ===============================
REM 3. PAYMENT SERVICE
REM ===============================
echo 💳 Migrating Payment Service...

REM Models
copy ..\src\main\java\com\example\demo\model\PaymentWithTypeDTO.java payment-service\src\main\java\com\example\payment\model\
copy ..\src\main\java\com\example\demo\model\Receipt.java payment-service\src\main\java\com\example\payment\model\
copy ..\src\main\java\com\example\demo\model\PaymentTypeMaster.java payment-service\src\main\java\com\example\payment\model\

REM Controllers
copy ..\src\main\java\com\example\demo\controller\PaymentWithTypeController.java payment-service\src\main\java\com\example\payment\controller\
copy ..\src\main\java\com\example\demo\controller\ReceiptController.java payment-service\src\main\java\com\example\payment\controller\
copy ..\src\main\java\com\example\demo\controller\PaymentTypeMasterController.java payment-service\src\main\java\com\example\payment\controller\

REM Services
copy ..\src\main\java\com\example\demo\service\PaymentWithTypeService.java payment-service\src\main\java\com\example\payment\service\
copy ..\src\main\java\com\example\demo\service\ReceiptService.java payment-service\src\main\java\com\example\payment\service\
copy ..\src\main\java\com\example\demo\service\PaymentTypeMasterService.java payment-service\src\main\java\com\example\payment\service\

REM Service Implementations
copy ..\src\main\java\com\example\demo\service\impl\PaymentWithTypeServiceImpl.java payment-service\src\main\java\com\example\payment\service\impl\
copy ..\src\main\java\com\example\demo\service\impl\ReceiptServiceImpl.java payment-service\src\main\java\com\example\payment\service\impl\
copy ..\src\main\java\com\example\demo\service\impl\PaymentTypeMasterServiceImpl.java payment-service\src\main\java\com\example\payment\service\impl\

REM Repositories
copy ..\src\main\java\com\example\demo\repository\PaymentWithTypeRepository.java payment-service\src\main\java\com\example\payment\repository\
copy ..\src\main\java\com\example\demo\repository\ReceiptRepository.java payment-service\src\main\java\com\example\payment\repository\
copy ..\src\main\java\com\example\demo\repository\PaymentTypeMasterRepository.java payment-service\src\main\java\com\example\payment\repository\

REM ===============================
REM 4. STAFF SERVICE
REM ===============================
echo 👥 Migrating Staff Service...

REM Models
copy ..\src\main\java\com\example\demo\model\StaffMaster.java staff-service\src\main\java\com\example\staff\model\

REM Controllers
copy ..\src\main\java\com\example\demo\controller\StaffMasterController.java staff-service\src\main\java\com\example\staff\controller\

REM Services
copy ..\src\main\java\com\example\demo\service\StaffMasterService.java staff-service\src\main\java\com\example\staff\service\

REM Service Implementations
copy ..\src\main\java\com\example\demo\service\impl\StaffMasterServiceImpl.java staff-service\src\main\java\com\example\staff\service\impl\

REM Repositories
copy ..\src\main\java\com\example\demo\repository\StaffMasterRepository.java staff-service\src\main\java\com\example\staff\repository\

REM DTOs
copy ..\src\main\java\com\example\demo\dto\StaffDTO.java staff-service\src\main\java\com\example\staff\dto\

REM ===============================
REM 5. CONTACT SERVICE
REM ===============================
echo 📞 Migrating Contact Service...

REM Models
copy ..\src\main\java\com\example\demo\model\ContactUs.java contact-service\src\main\java\com\example\contact\model\

REM Controllers
copy ..\src\main\java\com\example\demo\controller\ContactUsController.java contact-service\src\main\java\com\example\contact\controller\

REM Services
copy ..\src\main\java\com\example\demo\service\ContactUsService.java contact-service\src\main\java\com\example\contact\service\

REM Service Implementations
copy ..\src\main\java\com\example\demo\service\impl\ContactUsServiceImpl.java contact-service\src\main\java\com\example\contact\service\impl\

REM Repositories
copy ..\src\main\java\com\example\demo\repository\ContactUsRepository.java contact-service\src\main\java\com\example\contact\repository\

REM ===============================
REM 6. AUTH SERVICE
REM ===============================
echo 🔐 Migrating Auth Service...

REM Controllers
copy ..\src\main\java\com\example\demo\controller\AuthController.java auth-service\src\main\java\com\example\auth\controller\

REM Services
copy ..\src\main\java\com\example\demo\service\AuthService.java auth-service\src\main\java\com\example\auth\service\

REM Security
copy ..\src\main\java\com\example\demo\security\CustomUserDetailsService.java auth-service\src\main\java\com\example\auth\security\
copy ..\src\main\java\com\example\demo\security\JwtAuthenticationFilter.java auth-service\src\main\java\com\example\auth\security\
copy ..\src\main\java\com\example\demo\security\JwtTokenUtil.java auth-service\src\main\java\com\example\auth\security\
copy ..\src\main\java\com\example\demo\security\SecurityConfig.java auth-service\src\main\java\com\example\auth\security\

REM DTOs
copy ..\src\main\java\com\example\demo\dto\AuthRequest.java auth-service\src\main\java\com\example\auth\dto\
copy ..\src\main\java\com\example\demo\dto\AuthResponse.java auth-service\src\main\java\com\example\auth\dto\

echo ✅ File migration completed!
echo.
echo 📋 Next Steps:
echo 1. Update package names in all copied files
echo 2. Update import statements
echo 3. Create missing service configurations
echo 4. Test each service independently
echo.
echo 🚀 Run 'update-packages.bat' to automatically update package names

pause
