using DotNetRest.Data;
using DotNetRest.Models;
using Microsoft.EntityFrameworkCore;

namespace DotNetRest.Service
{
    public class BatchMasterService : IBatchMasterService
    {
        private readonly ApplicationDbContext _context;

        public BatchMasterService(ApplicationDbContext context)
        {
            _context = context;
        }

        // ==============================
        // CREATE OPERATIONS
        // ==============================
        public async Task<BatchMaster> SaveBatchAsync(BatchMaster batch)
        {
            if (batch.CreatedDate == null)
            {
                batch.CreatedDate = DateTime.Now;
            }
            if (batch.UpdatedDate == null)
            {
                batch.UpdatedDate = DateTime.Now;
            }
            _context.BatchMasters.Add(batch);
            await _context.SaveChangesAsync();
            return batch;
        }

        public async Task<List<BatchMaster>> SaveAllBatchesAsync(List<BatchMaster> batches)
        {
            foreach (var batch in batches)
            {
                if (batch.CreatedDate == null)
                {
                    batch.CreatedDate = DateTime.Now;
                }
                if (batch.UpdatedDate == null)
                {
                    batch.UpdatedDate = DateTime.Now;
                }
            }
            _context.BatchMasters.AddRange(batches);
            await _context.SaveChangesAsync();
            return batches;
        }

        // ==============================
        // READ OPERATIONS
        // ==============================
        public async Task<List<BatchMaster>> GetAllBatchesAsync()
        {
            return await _context.BatchMasters.ToListAsync();
        }

        public async Task<BatchMaster?> GetBatchByIdAsync(int batchId)
        {
            return await _context.BatchMasters.FindAsync(batchId);
        }

        public async Task<List<BatchMaster>> GetBatchesByNameAsync(string batchName)
        {
            return await _context.BatchMasters
                .Where(b => b.BatchName == batchName)
                .ToListAsync();
        }

        public async Task<List<BatchMaster>> GetBatchesByCourseIdAsync(int courseId)
        {
            return await _context.BatchMasters
                .Where(b => b.CourseId == courseId)
                .ToListAsync();
        }

        public async Task<List<BatchMaster>> GetBatchesByStaffIdAsync(int staffId)
        {
            return await _context.BatchMasters
                .Where(b => b.StaffId == staffId)
                .ToListAsync();
        }

        public async Task<List<BatchMaster>> GetBatchesByStatusAsync(string status)
        {
            return await _context.BatchMasters
                .Where(b => b.BatchIsActive.ToString() == status)
                .ToListAsync();
        }

        public async Task<List<BatchMaster>> GetBatchesByNameContainingAsync(string batchName)
        {
            return await _context.BatchMasters
                .Where(b => b.BatchName != null && b.BatchName.Contains(batchName))
                .ToListAsync();
        }

        public async Task<List<BatchMaster>> GetBatchesByCapacityRangeAsync(int minCapacity, int maxCapacity)
        {
            // Note: Since BatchMaster doesn't have a capacity field in the model,
            // this would need to be implemented based on your business logic
            // For now, returning empty list to match Spring Boot structure
            return new List<BatchMaster>();
        }

        public async Task<long> CountBatchesByCourseIdAsync(int courseId)
        {
            return await _context.BatchMasters
                .LongCountAsync(b => b.CourseId == courseId);
        }

        public async Task<long> CountBatchesByStaffIdAsync(int staffId)
        {
            return await _context.BatchMasters
                .LongCountAsync(b => b.StaffId == staffId);
        }

        // ==============================
        // UPDATE OPERATIONS
        // ==============================
        public async Task<BatchMaster> UpdateBatchAsync(int batchId, BatchMaster batchDetails)
        {
            var existingBatch = await _context.BatchMasters.FindAsync(batchId);
            if (existingBatch == null)
                throw new Exception($"Batch not found with id: {batchId}");

            existingBatch.BatchName = batchDetails.BatchName;
            existingBatch.BatchStartTime = batchDetails.BatchStartTime;
            existingBatch.BatchEndTime = batchDetails.BatchEndTime;
            existingBatch.CourseId = batchDetails.CourseId;
            existingBatch.PresentationDate = batchDetails.PresentationDate;
            existingBatch.CourseFees = batchDetails.CourseFees;
            existingBatch.CourseFeesFrom = batchDetails.CourseFeesFrom;
            existingBatch.CourseFeesTo = batchDetails.CourseFeesTo;
            existingBatch.BatchIsActive = batchDetails.BatchIsActive;
            existingBatch.StaffId = batchDetails.StaffId;
            existingBatch.BatchLogo = batchDetails.BatchLogo;
            existingBatch.UpdatedDate = DateTime.Now;

            await _context.SaveChangesAsync();
            return existingBatch;
        }

        public async Task<BatchMaster> UpdateBatchNameAsync(int batchId, string batchName)
        {
            var existingBatch = await _context.BatchMasters.FindAsync(batchId);
            if (existingBatch == null)
                throw new Exception($"Batch not found with id: {batchId}");

            existingBatch.BatchName = batchName;
            existingBatch.UpdatedDate = DateTime.Now;
            await _context.SaveChangesAsync();
            return existingBatch;
        }

        public async Task<BatchMaster> UpdateBatchStatusAsync(int batchId, string status)
        {
            var existingBatch = await _context.BatchMasters.FindAsync(batchId);
            if (existingBatch == null)
                throw new Exception($"Batch not found with id: {batchId}");

            existingBatch.BatchIsActive = status.ToLower() == "true";
            existingBatch.UpdatedDate = DateTime.Now;
            await _context.SaveChangesAsync();
            return existingBatch;
        }

        public async Task<BatchMaster> UpdateBatchCapacityAsync(int batchId, int capacity)
        {
            var existingBatch = await _context.BatchMasters.FindAsync(batchId);
            if (existingBatch == null)
                throw new Exception($"Batch not found with id: {batchId}");

            // Note: Since BatchMaster doesn't have a capacity field in the model,
            // this would need to be implemented based on your business logic
            existingBatch.UpdatedDate = DateTime.Now;
            await _context.SaveChangesAsync();
            return existingBatch;
        }

        public async Task<BatchMaster> UpdateBatchStaffAsync(int batchId, int staffId)
        {
            var existingBatch = await _context.BatchMasters.FindAsync(batchId);
            if (existingBatch == null)
                throw new Exception($"Batch not found with id: {batchId}");

            existingBatch.StaffId = staffId;
            existingBatch.UpdatedDate = DateTime.Now;
            await _context.SaveChangesAsync();
            return existingBatch;
        }

        // ==============================
        // DELETE OPERATIONS
        // ==============================
        public async Task DeleteBatchAsync(int batchId)
        {
            var batch = await _context.BatchMasters.FindAsync(batchId);
            if (batch == null)
                throw new Exception($"Batch not found with id: {batchId}");

            _context.BatchMasters.Remove(batch);
            await _context.SaveChangesAsync();
        }

        public async Task DeleteBatchesByCourseIdAsync(int courseId)
        {
            var batches = await _context.BatchMasters
                .Where(b => b.CourseId == courseId)
                .ToListAsync();

            _context.BatchMasters.RemoveRange(batches);
            await _context.SaveChangesAsync();
        }

        public async Task DeleteBatchesByStaffIdAsync(int staffId)
        {
            var batches = await _context.BatchMasters
                .Where(b => b.StaffId == staffId)
                .ToListAsync();

            _context.BatchMasters.RemoveRange(batches);
            await _context.SaveChangesAsync();
        }

        public async Task DeleteBatchesByStatusAsync(string status)
        {
            var batches = await _context.BatchMasters
                .Where(b => b.BatchIsActive.ToString() == status)
                .ToListAsync();

            _context.BatchMasters.RemoveRange(batches);
            await _context.SaveChangesAsync();
        }

        public async Task DeleteAllBatchesAsync()
        {
            var allBatches = await _context.BatchMasters.ToListAsync();
            _context.BatchMasters.RemoveRange(allBatches);
            await _context.SaveChangesAsync();
        }

        // ==============================
        // BUSINESS LOGIC OPERATIONS
        // ==============================
        public async Task<List<BatchMaster>> SearchBatchesAsync(string searchTerm)
        {
            return await _context.BatchMasters
                .Where(b => (b.BatchName != null && b.BatchName.Contains(searchTerm)) ||
                           (b.BatchLogo != null && b.BatchLogo.Contains(searchTerm)))
                .ToListAsync();
        }

        public async Task<List<BatchMaster>> GetBatchesByCourseAndStaffAsync(int courseId, int staffId)
        {
            return await _context.BatchMasters
                .Where(b => b.CourseId == courseId && b.StaffId == staffId)
                .ToListAsync();
        }

        public async Task<List<BatchMaster>> GetBatchesWithPaginationAsync(int page, int size)
        {
            return await _context.BatchMasters
                .Skip(page * size)
                .Take(size)
                .ToListAsync();
        }

        public async Task<List<BatchMaster>> GetBatchesSortedByNameAsync()
        {
            return await _context.BatchMasters
                .OrderBy(b => b.BatchName)
                .ToListAsync();
        }

        public async Task<List<BatchMaster>> GetBatchesSortedByCapacityAsync()
        {
            // Note: Since BatchMaster doesn't have a capacity field in the model,
            // this would need to be implemented based on your business logic
            // For now, returning sorted by name to match Spring Boot structure
            return await _context.BatchMasters
                .OrderBy(b => b.BatchName)
                .ToListAsync();
        }

        public async Task<List<BatchMaster>> GetBatchesSortedByCourseIdAsync()
        {
            return await _context.BatchMasters
                .OrderBy(b => b.CourseId)
                .ToListAsync();
        }

        public async Task<List<BatchMaster>> GetActiveBatchesAsync()
        {
            return await _context.BatchMasters
                .Where(b => b.BatchIsActive == true)
                .ToListAsync();
        }

        public async Task<List<BatchMaster>> GetAvailableBatchesAsync()
        {
            // Note: This would need to be implemented based on your business logic
            // For now, returning active batches to match Spring Boot structure
            return await _context.BatchMasters
                .Where(b => b.BatchIsActive == true)
                .ToListAsync();
        }

        public async Task<List<BatchMaster>> GetFullBatchesAsync()
        {
            // Note: This would need to be implemented based on your business logic
            // For now, returning empty list to match Spring Boot structure
            return new List<BatchMaster>();
        }
    }
}





