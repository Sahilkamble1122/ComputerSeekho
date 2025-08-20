using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using DotNetRest.Models; // Adjust namespace to where StudentMaster is located

namespace DotNetRest.Service
    {
        public interface IStudentMasterService
        {
            // Create operations
            Task<StudentMaster> SaveStudentAsync(StudentMaster student);
            Task<List<StudentMaster>> SaveAllStudentsAsync(List<StudentMaster> students);

            // Read operations
            Task<List<StudentMaster>> GetAllStudentsAsync();
            Task<StudentMaster?> GetStudentByIdAsync(int studentId);
            Task<List<StudentMaster>> GetStudentsByNameAsync(string studentName);
            Task<List<StudentMaster>> GetStudentsByEmailAsync(string email);
            Task<List<StudentMaster>> GetStudentsByMobileAsync(long mobile);
            Task<List<StudentMaster>> GetStudentsByGenderAsync(string gender);
            //Task<List<StudentMaster>> GetStudentsByQualificationAsync(string qualification);
            Task<List<StudentMaster>> GetStudentsByBatchIdAsync(int batchId);
            Task<List<StudentMaster>> GetStudentsByCourseIdAsync(int courseId);
            //Task<List<StudentMaster>> GetStudentsByNameContainingAsync(string studentName);
            //Task<List<StudentMaster>> GetStudentsByEmailContainingAsync(string email);
            //Task<List<StudentMaster>> GetStudentsByEmailDomainAsync(string domain);
            Task<long> CountStudentsByCourseIdAsync(int courseId);
            Task<long> CountStudentsByBatchIdAsync(int batchId);
            Task<long> CountPlacedStudentsAsync();
            Task<long> CountUnplacedStudentsAsync();

            // Update operations
            Task<StudentMaster> UpdateStudentAsync(int studentId, StudentMaster studentDetails);
            Task<StudentMaster> UpdateStudentPendingFeesAsync(int studentId, double pendingFees);
            Task<StudentMaster> UpdateStudentNameAsync(int studentId, string studentName);
            Task<StudentMaster> UpdateStudentEmailAsync(int studentId, string email);
            Task<StudentMaster> UpdateStudentMobileAsync(int studentId, long mobile);
            Task<StudentMaster> UpdateStudentAddressAsync(int studentId, string address);
            Task<StudentMaster> UpdateStudentQualificationAsync(int studentId, string qualification);
            Task<StudentMaster> UpdateStudentPlacementStatusAsync(int studentId, bool isPlaced);
            //Task<StudentMaster> UpdateStudentNameAsync(int studentId, string studentName);

            // Delete operations
            Task DeleteStudentAsync(int studentId);
            Task DeleteStudentsByBatchIdAsync(int batchId);
            Task DeleteStudentsByCourseIdAsync(int courseId);
            Task DeleteAllStudentsAsync();

            // Business logic operations
            Task<List<StudentMaster>> SearchStudentsAsync(string searchTerm);
            //Task<List<StudentMaster>> GetStudentsByDateRangeAndCourseAsync(string startDate, string endDate, int courseId);
            Task<List<StudentMaster>> GetStudentsWithPaginationAsync(int page, int size);
            Task<List<StudentMaster>> GetStudentsSortedByNameAsync();
            Task<List<StudentMaster>> GetStudentsSortedByEmailAsync();
            Task<List<StudentMaster>> GetStudentsSortedByBatchIdAsync();
            Task<StudentMaster?> AuthenticateStudentAsync(string username, string password);
            //Task<List<StudentMaster>> GetStudentsByEmailPatternAsync(string pattern);

            // Placement status operations
            Task<List<StudentMaster>> GetStudentsByPlacementStatusAsync(bool isPlaced);
            //Task<List<StudentMaster>> GetPlacedStudentsAsync();
            //Task<List<StudentMaster>> GetUnplacedStudentsAsync();
            //Task<long> CountPlacedStudentsAsync();
            //Task<List<StudentMaster>> GetStudentsByPlacementStatusAndCourseAsync(bool isPlaced, int courseId);
            //Task<List<StudentMaster>> GetStudentsByPlacementStatusAndBatchAsync(bool isPlaced, int batchId);
        }
    }

