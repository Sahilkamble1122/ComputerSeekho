using DotNetRest.Data;
using DotNetRest.Models;
using Microsoft.EntityFrameworkCore;

namespace DotNetRest.Service
{
    public class ClosureReasonMasterService : IClosureReasonMasterService
    {
        private readonly ApplicationDbContext _context;

        public ClosureReasonMasterService(ApplicationDbContext context)
        {
            _context = context;
        }

        // ==============================
        // CREATE OPERATIONS
        // ==============================
        public async Task<ClosureReasonMaster> SaveClosureReasonAsync(ClosureReasonMaster closureReason)
        {
            if (closureReason.CreatedDate == null)
            {
                closureReason.CreatedDate = DateTime.Now;
            }
            if (closureReason.UpdatedDate == null)
            {
                closureReason.UpdatedDate = DateTime.Now;
            }
            if (closureReason.ClosureReasonIsActive == null)
            {
                closureReason.ClosureReasonIsActive = true;
            }
            _context.ClosureReasonMasters.Add(closureReason);
            await _context.SaveChangesAsync();
            return closureReason;
        }

        public async Task<List<ClosureReasonMaster>> SaveAllClosureReasonsAsync(List<ClosureReasonMaster> closureReasons)
        {
            foreach (var closureReason in closureReasons)
            {
                if (closureReason.CreatedDate == null)
                {
                    closureReason.CreatedDate = DateTime.Now;
                }
                if (closureReason.UpdatedDate == null)
                {
                    closureReason.UpdatedDate = DateTime.Now;
                }
                if (closureReason.ClosureReasonIsActive == null)
                {
                    closureReason.ClosureReasonIsActive = true;
                }
            }
            _context.ClosureReasonMasters.AddRange(closureReasons);
            await _context.SaveChangesAsync();
            return closureReasons;
        }

        // ==============================
        // READ OPERATIONS
        // ==============================
        public async Task<List<ClosureReasonMaster>> GetAllClosureReasonsAsync()
        {
            return await _context.ClosureReasonMasters.ToListAsync();
        }

        public async Task<ClosureReasonMaster?> GetClosureReasonByIdAsync(int closureReasonId)
        {
            return await _context.ClosureReasonMasters.FindAsync(closureReasonId);
        }

        public async Task<List<ClosureReasonMaster>> GetClosureReasonsByNameAsync(string reasonName)
        {
            return await _context.ClosureReasonMasters
                .Where(c => c.ClosureReasonName == reasonName)
                .ToListAsync();
        }

        public async Task<List<ClosureReasonMaster>> GetClosureReasonsByDescriptionAsync(string description)
        {
            return await _context.ClosureReasonMasters
                .Where(c => c.ClosureReasonDesc == description)
                .ToListAsync();
        }

        public async Task<List<ClosureReasonMaster>> GetClosureReasonsByStatusAsync(string status)
        {
            return await _context.ClosureReasonMasters
                .Where(c => c.ClosureReasonIsActive.ToString() == status)
                .ToListAsync();
        }

        public async Task<List<ClosureReasonMaster>> GetClosureReasonsByNameContainingAsync(string reasonName)
        {
            return await _context.ClosureReasonMasters
                .Where(c => c.ClosureReasonName != null && c.ClosureReasonName.Contains(reasonName))
                .ToListAsync();
        }

        public async Task<List<ClosureReasonMaster>> GetActiveClosureReasonsAsync()
        {
            return await _context.ClosureReasonMasters
                .Where(c => c.ClosureReasonIsActive == true)
                .ToListAsync();
        }

        public async Task<long> CountActiveClosureReasonsAsync()
        {
            return await _context.ClosureReasonMasters
                .LongCountAsync(c => c.ClosureReasonIsActive == true);
        }

        // ==============================
        // UPDATE OPERATIONS
        // ==============================
        public async Task<ClosureReasonMaster> UpdateClosureReasonAsync(int closureReasonId, ClosureReasonMaster closureReasonDetails)
        {
            var existingClosureReason = await _context.ClosureReasonMasters.FindAsync(closureReasonId);
            if (existingClosureReason == null)
                throw new Exception($"Closure reason not found with id: {closureReasonId}");

            existingClosureReason.ClosureReasonName = closureReasonDetails.ClosureReasonName;
            existingClosureReason.ClosureReasonDesc = closureReasonDetails.ClosureReasonDesc;
            existingClosureReason.ClosureReasonIsActive = closureReasonDetails.ClosureReasonIsActive;
            existingClosureReason.UpdatedDate = DateTime.Now;

            await _context.SaveChangesAsync();
            return existingClosureReason;
        }

        public async Task<ClosureReasonMaster> UpdateClosureReasonNameAsync(int closureReasonId, string reasonName)
        {
            var existingClosureReason = await _context.ClosureReasonMasters.FindAsync(closureReasonId);
            if (existingClosureReason == null)
                throw new Exception($"Closure reason not found with id: {closureReasonId}");

            existingClosureReason.ClosureReasonName = reasonName;
            existingClosureReason.UpdatedDate = DateTime.Now;
            await _context.SaveChangesAsync();
            return existingClosureReason;
        }

        public async Task<ClosureReasonMaster> UpdateClosureReasonDescriptionAsync(int closureReasonId, string description)
        {
            var existingClosureReason = await _context.ClosureReasonMasters.FindAsync(closureReasonId);
            if (existingClosureReason == null)
                throw new Exception($"Closure reason not found with id: {closureReasonId}");

            existingClosureReason.ClosureReasonDesc = description;
            existingClosureReason.UpdatedDate = DateTime.Now;
            await _context.SaveChangesAsync();
            return existingClosureReason;
        }

        public async Task<ClosureReasonMaster> UpdateClosureReasonStatusAsync(int closureReasonId, string status)
        {
            var existingClosureReason = await _context.ClosureReasonMasters.FindAsync(closureReasonId);
            if (existingClosureReason == null)
                throw new Exception($"Closure reason not found with id: {closureReasonId}");

            existingClosureReason.ClosureReasonIsActive = status.ToLower() == "true";
            existingClosureReason.UpdatedDate = DateTime.Now;
            await _context.SaveChangesAsync();
            return existingClosureReason;
        }

        // ==============================
        // DELETE OPERATIONS
        // ==============================
        public async Task DeleteClosureReasonAsync(int closureReasonId)
        {
            var closureReason = await _context.ClosureReasonMasters.FindAsync(closureReasonId);
            if (closureReason == null)
                throw new Exception($"Closure reason not found with id: {closureReasonId}");

            _context.ClosureReasonMasters.Remove(closureReason);
            await _context.SaveChangesAsync();
        }

        public async Task DeleteClosureReasonsByStatusAsync(string status)
        {
            var closureReasons = await _context.ClosureReasonMasters
                .Where(c => c.ClosureReasonIsActive.ToString() == status)
                .ToListAsync();

            _context.ClosureReasonMasters.RemoveRange(closureReasons);
            await _context.SaveChangesAsync();
        }

        public async Task DeleteInactiveClosureReasonsAsync()
        {
            var closureReasons = await _context.ClosureReasonMasters
                .Where(c => c.ClosureReasonIsActive == false)
                .ToListAsync();

            _context.ClosureReasonMasters.RemoveRange(closureReasons);
            await _context.SaveChangesAsync();
        }

        public async Task DeleteAllClosureReasonsAsync()
        {
            var allClosureReasons = await _context.ClosureReasonMasters.ToListAsync();
            _context.ClosureReasonMasters.RemoveRange(allClosureReasons);
            await _context.SaveChangesAsync();
        }

        // ==============================
        // BUSINESS LOGIC OPERATIONS
        // ==============================
        public async Task<List<ClosureReasonMaster>> SearchClosureReasonsAsync(string searchTerm)
        {
            return await _context.ClosureReasonMasters
                .Where(c => (c.ClosureReasonName != null && c.ClosureReasonName.Contains(searchTerm)) ||
                           (c.ClosureReasonDesc != null && c.ClosureReasonDesc.Contains(searchTerm)))
                .ToListAsync();
        }

        public async Task<List<ClosureReasonMaster>> GetClosureReasonsWithPaginationAsync(int page, int size)
        {
            return await _context.ClosureReasonMasters
                .Skip(page * size)
                .Take(size)
                .ToListAsync();
        }

        public async Task<List<ClosureReasonMaster>> GetClosureReasonsSortedByNameAsync()
        {
            return await _context.ClosureReasonMasters
                .OrderBy(c => c.ClosureReasonName)
                .ToListAsync();
        }

        public async Task<List<ClosureReasonMaster>> GetClosureReasonsSortedByStatusAsync()
        {
            return await _context.ClosureReasonMasters
                .OrderBy(c => c.ClosureReasonIsActive)
                .ToListAsync();
        }

        public async Task<List<ClosureReasonMaster>> GetFrequentlyUsedClosureReasonsAsync()
        {
            // Note: This would need to be implemented based on your business logic
            // For now, returning active closure reasons to match Spring Boot structure
            return await _context.ClosureReasonMasters
                .Where(c => c.ClosureReasonIsActive == true)
                .ToListAsync();
        }
    }
}
