using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using DotNetRest.Models;

namespace DotNetRest.Service
    {
        public interface IFollowupService
        {
            // CREATE OPERATIONS
            Task<Followup> SaveFollowupAsync(Followup followup);
            Task<List<Followup>> SaveAllFollowupsAsync(List<Followup> followups);

            // READ OPERATIONS
            Task<List<Followup>> GetAllFollowupsAsync();
            Task<Followup?> GetFollowupByIdAsync(int followupId);
            Task<List<Followup>> GetFollowupsByEnquiryIdAsync(int enquiryId);
            Task<List<Followup>> GetFollowupsByStudentIdAsync(int studentId);
            Task<List<Followup>> GetFollowupsByStatusAsync(string status);
            Task<List<Followup>> GetFollowupsByTypeAsync(string followupType);
            Task<List<Followup>> GetFollowupsByDateRangeAsync(DateTime startDate, DateTime endDate);
            Task<List<Followup>> GetFollowupsByPriorityAsync(string priority);
            Task<long> CountFollowupsByEnquiryIdAsync(int enquiryId);
            Task<long> CountFollowupsByStatusAsync(string status);

            // UPDATE OPERATIONS
            Task<Followup> UpdateFollowupAsync(int followupId, Followup followupDetails);
            Task<Followup> UpdateFollowupStatusAsync(int followupId, string status);
            Task<Followup> UpdateFollowupTypeAsync(int followupId, string followupType);
            Task<Followup> UpdateFollowupPriorityAsync(int followupId, string priority);
            Task<Followup> UpdateFollowupNotesAsync(int followupId, string notes);

            // DELETE OPERATIONS
            Task DeleteFollowupAsync(int followupId);
            Task DeleteFollowupsByEnquiryIdAsync(int enquiryId);
            Task DeleteFollowupsByStatusAsync(string status);
            Task DeleteAllFollowupsAsync();

            // BUSINESS LOGIC OPERATIONS
            Task<List<Followup>> SearchFollowupsAsync(string searchTerm);
            Task<List<Followup>> GetFollowupsByEnquiryAndStatusAsync(int enquiryId, string status);
            Task<List<Followup>> GetFollowupsWithPaginationAsync(int page, int size);
            Task<List<Followup>> GetFollowupsSortedByDateAsync();
            Task<List<Followup>> GetFollowupsSortedByPriorityAsync();
            Task<List<Followup>> GetFollowupsSortedByTypeAsync();
            Task<List<Followup>> GetPendingFollowupsAsync();
            Task<List<Followup>> GetCompletedFollowupsAsync();
            Task<List<Followup>> GetOverdueFollowupsAsync();
            Task<List<Followup>> GetHighPriorityFollowupsAsync();
            Task<List<Followup>> GetTodayFollowupsAsync();
            Task<List<Followup>> GetUpcomingFollowupsAsync();
        }
    }

