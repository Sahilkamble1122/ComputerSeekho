using DotNetRest.Models;

namespace DotNetRest.Service
{
    public interface IImageMasterService
    {
        // CREATE OPERATIONS
        Task<ImageMaster> SaveImageAsync(ImageMaster image);
        Task<List<ImageMaster>> SaveAllImagesAsync(List<ImageMaster> images);

        // READ OPERATIONS
        Task<List<ImageMaster>> GetAllImagesAsync();
        Task<ImageMaster?> GetImageByIdAsync(int imageId);
        Task<List<ImageMaster>> GetImagesByNameAsync(string imageName);
        Task<List<ImageMaster>> GetImagesByTypeAsync(string imageType);
        Task<List<ImageMaster>> GetImagesByStatusAsync(string status);
        Task<List<ImageMaster>> GetImagesByNameContainingAsync(string imageName);
        Task<List<ImageMaster>> GetActiveImagesAsync();
        Task<long> CountActiveImagesAsync();

        // UPDATE OPERATIONS
        Task<ImageMaster> UpdateImageAsync(int imageId, ImageMaster imageDetails);
        Task<ImageMaster> UpdateImageNameAsync(int imageId, string imageName);
        Task<ImageMaster> UpdateImageTypeAsync(int imageId, string imageType);
        Task<ImageMaster> UpdateImageStatusAsync(int imageId, string status);

        // DELETE OPERATIONS
        Task DeleteImageAsync(int imageId);
        Task DeleteImagesByTypeAsync(string imageType);
        Task DeleteImagesByStatusAsync(string status);
        Task DeleteInactiveImagesAsync();
        Task DeleteAllImagesAsync();

        // BUSINESS LOGIC OPERATIONS
        Task<List<ImageMaster>> SearchImagesAsync(string searchTerm);
        Task<List<ImageMaster>> GetImagesWithPaginationAsync(int page, int size);
        Task<List<ImageMaster>> GetImagesSortedByNameAsync();
        Task<List<ImageMaster>> GetImagesSortedByTypeAsync();
        Task<List<ImageMaster>> GetImagesSortedByStatusAsync();
        Task<List<ImageMaster>> GetFeaturedImagesAsync();
        Task<List<ImageMaster>> GetRecentImagesAsync();
    }
}
