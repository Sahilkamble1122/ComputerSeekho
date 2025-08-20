using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DotNetRest.Data;
using DotNetRest.Models;
using Microsoft.EntityFrameworkCore;

namespace DotNetRest.Service.Impl
{
    public class FollowupService : IFollowupService
    {
        private readonly ApplicationDbContext _context;

        public FollowupService(ApplicationDbContext context)
        {
            _context = context;
        }

        // ===============================
        // CREATE OPERATIONS
        // ===============================
        public async Task<Followup> SaveFollowupAsync(Followup followup)
        {
            followup.CreatedDate = DateTime.Now;
            followup.UpdatedDate = DateTime.Now;
            _context.Followups.Add(followup);
            await _context.SaveChangesAsync();
            return followup;
        }

        public async Task<List<Followup>> SaveAllFollowupsAsync(List<Followup> followups)
        {
            var now = DateTime.Now;
            followups.ForEach(f => { f.CreatedDate = now; f.UpdatedDate = now; });
            _context.Followups.AddRange(followups);
            await _context.SaveChangesAsync();
            return followups;
        }

        // ===============================
        // READ OPERATIONS
        // ===============================
        public async Task<List<Followup>> GetAllFollowupsAsync()
        {
            return await _context.Followups.ToListAsync();
        }

        public async Task<Followup?> GetFollowupByIdAsync(int followupId)
        {
            return await _context.Followups.FindAsync(followupId);
        }

        public async Task<List<Followup>> GetFollowupsByEnquiryIdAsync(int enquiryId)
        {
            return await _context.Followups
                                 .Where(f => f.EnquiryId == enquiryId)
                                 .ToListAsync();
        }

        public async Task<List<Followup>> GetFollowupsByStudentIdAsync(int staffId)
        {
            return await _context.Followups
                                 .Where(f => f.StaffId == staffId)
                                 .ToListAsync();
        }

        public async Task<List<Followup>> GetFollowupsByStatusAsync(string status)
        {
            bool isActive = string.Equals(status, "Active", StringComparison.OrdinalIgnoreCase);
            return await _context.Followups
                                 .Where(f => f.IsActive.HasValue && f.IsActive.Value == isActive)
                                 .ToListAsync();
        }

        public async Task<List<Followup>> GetFollowupsByTypeAsync(string type)
        {
            return await _context.Followups
                                 .Where(f => !string.IsNullOrEmpty(f.FollowupMsg) && f.FollowupMsg.Contains(type))
                                 .ToListAsync();
        }

        public async Task<List<Followup>> GetFollowupsByDateRangeAsync(DateTime startDate, DateTime endDate)
        {
            return await _context.Followups
                                 .Where(f => f.FollowupDate.HasValue &&
                                             f.FollowupDate.Value.Date >= startDate.Date &&
                                             f.FollowupDate.Value.Date <= endDate.Date)
                                 .ToListAsync();
        }

        public async Task<List<Followup>> GetFollowupsByPriorityAsync(string priority)
        {
            return await _context.Followups
                                 .Where(f => !string.IsNullOrEmpty(f.FollowupMsg) && f.FollowupMsg.Contains(priority))
                                 .ToListAsync();
        }

        public async Task<long> CountFollowupsByEnquiryIdAsync(int enquiryId)
        {
            return await _context.Followups.LongCountAsync(f => f.EnquiryId == enquiryId);
        }

        public async Task<long> CountFollowupsByStatusAsync(string status)
        {
            bool isActive = string.Equals(status, "Active", StringComparison.OrdinalIgnoreCase);
            return await _context.Followups.LongCountAsync(f => f.IsActive.HasValue && f.IsActive.Value == isActive);
        }

        // ===============================
        // UPDATE OPERATIONS
        // ===============================
        public async Task<Followup> UpdateFollowupAsync(int followupId, Followup followupDetails)
        {
            var existing = await _context.Followups.FindAsync(followupId);
            if (existing == null)
                throw new KeyNotFoundException($"Followup not found with ID {followupId}");

            existing.EnquiryId = followupDetails.EnquiryId;
            existing.StaffId = followupDetails.StaffId;
            existing.FollowupDate = followupDetails.FollowupDate;
            existing.FollowupMsg = followupDetails.FollowupMsg;
            existing.IsActive = followupDetails.IsActive;
            existing.UpdatedDate = DateTime.Now;

            await _context.SaveChangesAsync();
            return existing;
        }

        public async Task<Followup> UpdateFollowupStatusAsync(int followupId, string status)
        {
            var followup = await _context.Followups.FindAsync(followupId);
            if (followup == null) throw new KeyNotFoundException();

            followup.IsActive = string.Equals(status, "Active", StringComparison.OrdinalIgnoreCase);
            followup.UpdatedDate = DateTime.Now;
            await _context.SaveChangesAsync();
            return followup;
        }

        public async Task<Followup> UpdateFollowupTypeAsync(int followupId, string type)
        {
            var followup = await _context.Followups.FindAsync(followupId);
            if (followup == null) throw new KeyNotFoundException();

            followup.FollowupMsg = type;
            followup.UpdatedDate = DateTime.Now;
            await _context.SaveChangesAsync();
            return followup;
        }

        public async Task<Followup> UpdateFollowupPriorityAsync(int followupId, string priority)
        {
            var followup = await _context.Followups.FindAsync(followupId);
            if (followup == null) throw new KeyNotFoundException();

            followup.FollowupMsg = priority;
            followup.UpdatedDate = DateTime.Now;
            await _context.SaveChangesAsync();
            return followup;
        }

        public async Task<Followup> UpdateFollowupNotesAsync(int followupId, string notes)
        {
            var followup = await _context.Followups.FindAsync(followupId);
            if (followup == null) throw new KeyNotFoundException();

            followup.FollowupMsg = notes;
            followup.UpdatedDate = DateTime.Now;
            await _context.SaveChangesAsync();
            return followup;
        }

        // ===============================
        // DELETE OPERATIONS
        // ===============================
        public async Task DeleteFollowupAsync(int followupId)
        {
            var followup = await _context.Followups.FindAsync(followupId);
            if (followup == null) throw new KeyNotFoundException();
            _context.Followups.Remove(followup);
            await _context.SaveChangesAsync();
        }

        public async Task DeleteFollowupsByEnquiryIdAsync(int enquiryId)
        {
            var followups = _context.Followups.Where(f => f.EnquiryId == enquiryId);
            _context.Followups.RemoveRange(followups);
            await _context.SaveChangesAsync();
        }

        public async Task DeleteFollowupsByStatusAsync(string status)
        {
            bool isActive = string.Equals(status, "Active", StringComparison.OrdinalIgnoreCase);
            var followups = _context.Followups.Where(f => f.IsActive.HasValue && f.IsActive.Value == isActive);
            _context.Followups.RemoveRange(followups);
            await _context.SaveChangesAsync();
        }

        public async Task DeleteAllFollowupsAsync()
        {
            _context.Followups.RemoveRange(_context.Followups);
            await _context.SaveChangesAsync();
        }

        // ===============================
        // BUSINESS LOGIC
        // ===============================
        public async Task<List<Followup>> SearchFollowupsAsync(string searchTerm)
        {
            return await _context.Followups
                                 .Where(f => !string.IsNullOrEmpty(f.FollowupMsg) && f.FollowupMsg.Contains(searchTerm))
                                 .ToListAsync();
        }

        public async Task<List<Followup>> GetFollowupsByEnquiryAndStatusAsync(int enquiryId, string status)
        {
            bool isActive = string.Equals(status, "Active", StringComparison.OrdinalIgnoreCase);
            return await _context.Followups
                                 .Where(f => f.EnquiryId == enquiryId && f.IsActive.HasValue && f.IsActive.Value == isActive)
                                 .ToListAsync();
        }

        public async Task<List<Followup>> GetFollowupsWithPaginationAsync(int page, int size)
        {
            page = Math.Max(page, 0);
            size = Math.Max(size, 1);

            return await _context.Followups
                                 .Skip(page * size)
                                 .Take(size)
                                 .ToListAsync();
        }

        public async Task<List<Followup>> GetFollowupsSortedByDateAsync()
        {
            return await _context.Followups
                                 .OrderBy(f => f.FollowupDate ?? DateTime.MaxValue)
                                 .ToListAsync();
        }

        public async Task<List<Followup>> GetFollowupsSortedByPriorityAsync()
        {
            return await _context.Followups
                                 .OrderBy(f => f.FollowupMsg)
                                 .ToListAsync();
        }

        public async Task<List<Followup>> GetFollowupsSortedByTypeAsync()
        {
            return await _context.Followups
                                 .OrderBy(f => f.FollowupMsg)
                                 .ToListAsync();
        }

        public async Task<List<Followup>> GetPendingFollowupsAsync()
        {
            return await _context.Followups
                                 .Where(f => f.IsActive.HasValue && !f.IsActive.Value)
                                 .ToListAsync();
        }

        public async Task<List<Followup>> GetCompletedFollowupsAsync()
        {
            return await _context.Followups
                                 .Where(f => f.IsActive.HasValue && f.IsActive.Value)
                                 .ToListAsync();
        }

        public async Task<List<Followup>> GetOverdueFollowupsAsync()
        {
            var today = DateTime.Today;
            return await _context.Followups
                                 .Where(f => f.FollowupDate.HasValue &&
                                             f.FollowupDate.Value.Date < today &&
                                             f.IsActive.HasValue && !f.IsActive.Value)
                                 .ToListAsync();
        }

        public async Task<List<Followup>> GetHighPriorityFollowupsAsync()
        {
            return await _context.Followups
                                 .Where(f => !string.IsNullOrEmpty(f.FollowupMsg) && f.FollowupMsg.Contains("High"))
                                 .ToListAsync();
        }

        public async Task<List<Followup>> GetTodayFollowupsAsync()
        {
            var today = DateTime.Today;
            return await _context.Followups
                                 .Where(f => f.FollowupDate.HasValue &&
                                             f.FollowupDate.Value.Date == today)
                                 .ToListAsync();
        }

        public async Task<List<Followup>> GetUpcomingFollowupsAsync()
        {
            var today = DateTime.Today;
            return await _context.Followups
                                 .Where(f => f.FollowupDate.HasValue &&
                                             f.FollowupDate.Value.Date > today &&
                                             f.IsActive.HasValue && !f.IsActive.Value)
                                 .ToListAsync();
        }
    }
}
