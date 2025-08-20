using DotNetRest.Models;

namespace DotNetRest.Service.Impl
{
    public interface IAlbumMasterService
    {
        Task<AlbumMaster> SaveAlbumAsync(AlbumMaster album);
        Task<IEnumerable<AlbumMaster>> SaveAllAlbumsAsync(IEnumerable<AlbumMaster> albums);

        // READ OPERATIONS
        Task<IEnumerable<AlbumMaster>> GetAllAlbumsAsync();
        Task<AlbumMaster?> GetAlbumByIdAsync(int albumId);
        Task<IEnumerable<AlbumMaster>> GetAlbumsByNameAsync(string albumName);
        Task<IEnumerable<AlbumMaster>> GetAlbumsByDescriptionAsync(string description);
        Task<IEnumerable<AlbumMaster>> GetAlbumsByStatusAsync(string status);
        Task<IEnumerable<AlbumMaster>> GetAlbumsByNameContainingAsync(string albumName);
        Task<IEnumerable<AlbumMaster>> GetActiveAlbumsAsync();
        Task<long> CountActiveAlbumsAsync();

        // UPDATE OPERATIONS
        Task<AlbumMaster> UpdateAlbumAsync(int albumId, AlbumMaster albumDetails);
        Task<AlbumMaster> UpdateAlbumNameAsync(int albumId, string albumName);
        Task<AlbumMaster> UpdateAlbumDescriptionAsync(int albumId, string description);
        Task<AlbumMaster> UpdateAlbumStatusAsync(int albumId, string status);

        // DELETE OPERATIONS
        Task DeleteAlbumAsync(int albumId);
        Task DeleteAlbumsByStatusAsync(string status);
        Task DeleteInactiveAlbumsAsync();
        Task DeleteAllAlbumsAsync();

        // BUSINESS LOGIC OPERATIONS
        Task<IEnumerable<AlbumMaster>> SearchAlbumsAsync(string searchTerm);
        Task<IEnumerable<AlbumMaster>> GetAlbumsWithPaginationAsync(int page, int size);
        Task<IEnumerable<AlbumMaster>> GetAlbumsSortedByNameAsync();
        Task<IEnumerable<AlbumMaster>> GetAlbumsSortedByStatusAsync();
        Task<IEnumerable<AlbumMaster>> GetFeaturedAlbumsAsync();
    }
}
