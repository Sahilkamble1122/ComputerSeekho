using DotNetRest.Models;

namespace DotNetRest.Service
{
    public interface IBatchMasterService
    {
        // CREATE OPERATIONS
        Task<BatchMaster> SaveBatchAsync(BatchMaster batch);
        Task<List<BatchMaster>> SaveAllBatchesAsync(List<BatchMaster> batches);

        // READ OPERATIONS
        Task<List<BatchMaster>> GetAllBatchesAsync();
        Task<BatchMaster?> GetBatchByIdAsync(int batchId);
        Task<List<BatchMaster>> GetBatchesByNameAsync(string batchName);
        Task<List<BatchMaster>> GetBatchesByCourseIdAsync(int courseId);
        Task<List<BatchMaster>> GetBatchesByStaffIdAsync(int staffId);
        Task<List<BatchMaster>> GetBatchesByStatusAsync(string status);
        Task<List<BatchMaster>> GetBatchesByNameContainingAsync(string batchName);
        Task<List<BatchMaster>> GetBatchesByCapacityRangeAsync(int minCapacity, int maxCapacity);
        Task<long> CountBatchesByCourseIdAsync(int courseId);
        Task<long> CountBatchesByStaffIdAsync(int staffId);

        // UPDATE OPERATIONS
        Task<BatchMaster> UpdateBatchAsync(int batchId, BatchMaster batchDetails);
        Task<BatchMaster> UpdateBatchNameAsync(int batchId, string batchName);
        Task<BatchMaster> UpdateBatchStatusAsync(int batchId, string status);
        Task<BatchMaster> UpdateBatchCapacityAsync(int batchId, int capacity);
        Task<BatchMaster> UpdateBatchStaffAsync(int batchId, int staffId);

        // DELETE OPERATIONS
        Task DeleteBatchAsync(int batchId);
        Task DeleteBatchesByCourseIdAsync(int courseId);
        Task DeleteBatchesByStaffIdAsync(int staffId);
        Task DeleteBatchesByStatusAsync(string status);
        Task DeleteAllBatchesAsync();

        // BUSINESS LOGIC OPERATIONS
        Task<List<BatchMaster>> SearchBatchesAsync(string searchTerm);
        Task<List<BatchMaster>> GetBatchesByCourseAndStaffAsync(int courseId, int staffId);
        Task<List<BatchMaster>> GetBatchesWithPaginationAsync(int page, int size);
        Task<List<BatchMaster>> GetBatchesSortedByNameAsync();
        Task<List<BatchMaster>> GetBatchesSortedByCapacityAsync();
        Task<List<BatchMaster>> GetBatchesSortedByCourseIdAsync();
        Task<List<BatchMaster>> GetActiveBatchesAsync();
        Task<List<BatchMaster>> GetAvailableBatchesAsync();
        Task<List<BatchMaster>> GetFullBatchesAsync();
    }
}
