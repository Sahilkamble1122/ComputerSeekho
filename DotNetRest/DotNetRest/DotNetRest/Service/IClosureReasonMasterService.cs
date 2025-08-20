using DotNetRest.Models;

namespace DotNetRest.Service
{
    public interface IClosureReasonMasterService
    {
        // CREATE OPERATIONS
        Task<ClosureReasonMaster> SaveClosureReasonAsync(ClosureReasonMaster closureReason);
        Task<List<ClosureReasonMaster>> SaveAllClosureReasonsAsync(List<ClosureReasonMaster> closureReasons);

        // READ OPERATIONS
        Task<List<ClosureReasonMaster>> GetAllClosureReasonsAsync();
        Task<ClosureReasonMaster?> GetClosureReasonByIdAsync(int closureReasonId);
        Task<List<ClosureReasonMaster>> GetClosureReasonsByNameAsync(string reasonName);
        Task<List<ClosureReasonMaster>> GetClosureReasonsByDescriptionAsync(string description);
        Task<List<ClosureReasonMaster>> GetClosureReasonsByStatusAsync(string status);
        Task<List<ClosureReasonMaster>> GetClosureReasonsByNameContainingAsync(string reasonName);
        Task<List<ClosureReasonMaster>> GetActiveClosureReasonsAsync();
        Task<long> CountActiveClosureReasonsAsync();

        // UPDATE OPERATIONS
        Task<ClosureReasonMaster> UpdateClosureReasonAsync(int closureReasonId, ClosureReasonMaster closureReasonDetails);
        Task<ClosureReasonMaster> UpdateClosureReasonNameAsync(int closureReasonId, string reasonName);
        Task<ClosureReasonMaster> UpdateClosureReasonDescriptionAsync(int closureReasonId, string description);
        Task<ClosureReasonMaster> UpdateClosureReasonStatusAsync(int closureReasonId, string status);

        // DELETE OPERATIONS
        Task DeleteClosureReasonAsync(int closureReasonId);
        Task DeleteClosureReasonsByStatusAsync(string status);
        Task DeleteInactiveClosureReasonsAsync();
        Task DeleteAllClosureReasonsAsync();

        // BUSINESS LOGIC OPERATIONS
        Task<List<ClosureReasonMaster>> SearchClosureReasonsAsync(string searchTerm);
        Task<List<ClosureReasonMaster>> GetClosureReasonsWithPaginationAsync(int page, int size);
        Task<List<ClosureReasonMaster>> GetClosureReasonsSortedByNameAsync();
        Task<List<ClosureReasonMaster>> GetClosureReasonsSortedByStatusAsync();
        Task<List<ClosureReasonMaster>> GetFrequentlyUsedClosureReasonsAsync();
    }
}
