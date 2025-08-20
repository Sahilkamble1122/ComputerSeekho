using DotNetRest.Models;

namespace DotNetRest.Service
{
    public interface IEnquiryService
    {
        // Create operations
        Task<Enquiry> SaveEnquiryAsync(Enquiry enquiry);
        Task<List<Enquiry>> SaveAllEnquiriesAsync(List<Enquiry> enquiries);

        // Read operations
        Task<List<Enquiry>> GetAllEnquiriesAsync();
        Task<Enquiry?> GetEnquiryByIdAsync(int id);
        Task<List<Enquiry>> GetEnquiriesByStudentNameAsync(string studentName);
        Task<List<Enquiry>> GetEnquiriesByMobileAsync(long mobile);
        Task<List<Enquiry>> GetEnquiriesByEmailAsync(string email);
        Task<List<Enquiry>> GetEnquiriesByStatusAsync(string status);
        Task<List<Enquiry>> GetEnquiriesByStudentNameContainingAsync(string studentName);
        Task<List<Enquiry>> GetEnquiriesByEmailContainingAsync(string email);
        Task<List<Enquiry>> GetEnquiriesByDateRangeAsync(string startDate, string endDate);
        Task<long> CountEnquiriesByStatusAsync(string status);

        // Update operations
        Task<Enquiry> UpdateEnquiryAsync(int id, Enquiry enquiryDetails);
        Task<Enquiry> UpdateEnquiryStatusAsync(int id, string status);
        Task<Enquiry> UpdateEnquiryStudentNameAsync(int id, string studentName);
        Task<Enquiry> UpdateEnquiryMobileAsync(int id, long mobile);
        Task<Enquiry> UpdateEnquiryEmailAsync(int id, string email);
        Task<Enquiry> UpdateEnquiryCounterAsync(int id, int enquiryCounter);
        Task<Enquiry> UpdateEnquiryClosureAsync(int id, int? closureReasonId, string? closureReason);
        Task<Enquiry> UpdateEnquiryStateAsync(int id, bool enquiryState);

        // Delete operations
        Task DeleteEnquiryAsync(int id);
        Task DeleteEnquiriesByStatusAsync(string status);
        Task DeleteAllEnquiriesAsync();

        // Business logic operations
        Task<List<Enquiry>> SearchEnquiriesAsync(string searchTerm);
        Task<List<Enquiry>> GetEnquiriesByDateRangeAndCourseAsync(string startDate, string endDate, int courseId);
        Task<List<Enquiry>> GetEnquiriesWithPaginationAsync(int page, int size);
        Task<List<Enquiry>> GetEnquiriesSortedByDateAsync();
        Task<List<Enquiry>> GetEnquiriesSortedByStudentNameAsync();
        Task<List<Enquiry>> GetEnquiriesSortedByStatusAsync();
        Task<List<Enquiry>> GetPendingEnquiriesAsync();
        Task<List<Enquiry>> GetCompletedEnquiriesAsync();
        Task<List<Enquiry>> GetFollowUpRequiredEnquiriesAsync();
    }
}
