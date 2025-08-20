using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using DotNetRest.Data;
using DotNetRest.Models;
using DotNetRest.Service;
using System.IO;

namespace DotNetRest.Service.Impl
    {
        public class ImageMasterService : IImageMasterService
        {
            private readonly ApplicationDbContext _context;

            public ImageMasterService(ApplicationDbContext context)
            {
                _context = context;
            }

            // ==============================
            // CREATE OPERATIONS
            // ==============================
            public async Task<ImageMaster> SaveImageAsync(ImageMaster image)
            {
                if (image.CreatedDate == null)
                {
                    image.CreatedDate = DateTime.Now;
                }
                if (image.UpdatedDate == null)
                {
                    image.UpdatedDate = DateTime.Now;
                }
                _context.ImageMasters.Add(image);
                await _context.SaveChangesAsync();
                return image;
            }

            public async Task<List<ImageMaster>> SaveAllImagesAsync(List<ImageMaster> images)
            {
                foreach (var image in images)
                {
                    if (image.CreatedDate == null)
                    {
                        image.CreatedDate = DateTime.Now;
                    }
                    if (image.UpdatedDate == null)
                    {
                        image.UpdatedDate = DateTime.Now;
                    }
                }
                _context.ImageMasters.AddRange(images);
                await _context.SaveChangesAsync();
                return images;
            }

            // ==============================
            // READ OPERATIONS
            // ==============================
            public async Task<List<ImageMaster>> GetAllImagesAsync()
            {
                return await _context.ImageMasters.ToListAsync();
            }

            public async Task<ImageMaster?> GetImageByIdAsync(int imageId)
            {
                return await _context.ImageMasters.FindAsync(imageId);
            }

            public async Task<List<ImageMaster>> GetImagesByNameAsync(string imageName)
            {
                // Assuming image name is stored in image_path or we need to extract it
                return await _context.ImageMasters
                    .Where(i => i.ImagePath != null && i.ImagePath.Contains(imageName))
                    .ToListAsync();
            }

            public async Task<List<ImageMaster>> GetImagesByTypeAsync(string imageType)
            {
                // Assuming image type is determined by file extension in image_path
                return await _context.ImageMasters
                    .Where(i => i.ImagePath != null && i.ImagePath.EndsWith("." + imageType.ToLower()))
                    .ToListAsync();
            }

            public async Task<List<ImageMaster>> GetImagesByStatusAsync(string status)
            {
                // Assuming status is based on image_is_active
                bool isActive = status.ToLower() == "active" || status.ToLower() == "true";
                return await _context.ImageMasters
                    .Where(i => i.ImageIsActive == isActive)
                    .ToListAsync();
            }

            public async Task<List<ImageMaster>> GetImagesByNameContainingAsync(string imageName)
            {
                return await _context.ImageMasters
                    .Where(i => i.ImagePath != null && i.ImagePath.Contains(imageName))
                    .ToListAsync();
            }

            public async Task<List<ImageMaster>> GetActiveImagesAsync()
            {
                return await _context.ImageMasters
                    .Where(i => i.ImageIsActive == true)
                    .ToListAsync();
            }

            public async Task<long> CountActiveImagesAsync()
            {
                return await _context.ImageMasters
                    .LongCountAsync(i => i.ImageIsActive == true);
            }

            // ==============================
            // UPDATE OPERATIONS
            // ==============================
            public async Task<ImageMaster> UpdateImageAsync(int imageId, ImageMaster imageDetails)
            {
                var existingImage = await _context.ImageMasters.FindAsync(imageId);
                if (existingImage == null)
                    throw new Exception($"Image not found with id: {imageId}");

                existingImage.ImagePath = imageDetails.ImagePath;
                existingImage.AlbumId = imageDetails.AlbumId;
                existingImage.IsAlbumCover = imageDetails.IsAlbumCover;
                existingImage.ImageIsActive = imageDetails.ImageIsActive;
                existingImage.UpdatedDate = DateTime.Now;

                await _context.SaveChangesAsync();
                return existingImage;
            }

            public async Task<ImageMaster> UpdateImageNameAsync(int imageId, string imageName)
            {
                var existingImage = await _context.ImageMasters.FindAsync(imageId);
                if (existingImage == null)
                    throw new Exception($"Image not found with id: {imageId}");

                // Update the image path with new name (assuming we need to modify the path)
                if (existingImage.ImagePath != null)
                {
                    var directory = Path.GetDirectoryName(existingImage.ImagePath);
                    var extension = Path.GetExtension(existingImage.ImagePath);
                    existingImage.ImagePath = Path.Combine(directory ?? "", imageName + extension);
                }
                existingImage.UpdatedDate = DateTime.Now;
                await _context.SaveChangesAsync();
                return existingImage;
            }

            public async Task<ImageMaster> UpdateImageTypeAsync(int imageId, string imageType)
            {
                var existingImage = await _context.ImageMasters.FindAsync(imageId);
                if (existingImage == null)
                    throw new Exception($"Image not found with id: {imageId}");

                // Update the image path with new type/extension
                if (existingImage.ImagePath != null)
                {
                    var directory = Path.GetDirectoryName(existingImage.ImagePath);
                    var nameWithoutExtension = Path.GetFileNameWithoutExtension(existingImage.ImagePath);
                    existingImage.ImagePath = Path.Combine(directory ?? "", nameWithoutExtension + "." + imageType.ToLower());
                }
                existingImage.UpdatedDate = DateTime.Now;
                await _context.SaveChangesAsync();
                return existingImage;
            }

            public async Task<ImageMaster> UpdateImageStatusAsync(int imageId, string status)
            {
                var existingImage = await _context.ImageMasters.FindAsync(imageId);
                if (existingImage == null)
                    throw new Exception($"Image not found with id: {imageId}");

                existingImage.ImageIsActive = status.ToLower() == "active" || status.ToLower() == "true";
                existingImage.UpdatedDate = DateTime.Now;
                await _context.SaveChangesAsync();
                return existingImage;
            }

            // ==============================
            // DELETE OPERATIONS
            // ==============================
            public async Task DeleteImageAsync(int imageId)
            {
                var image = await _context.ImageMasters.FindAsync(imageId);
                if (image == null)
                    throw new Exception($"Image not found with id: {imageId}");

                _context.ImageMasters.Remove(image);
                await _context.SaveChangesAsync();
            }

            public async Task DeleteImagesByTypeAsync(string imageType)
            {
                var images = await _context.ImageMasters
                    .Where(i => i.ImagePath != null && i.ImagePath.EndsWith("." + imageType.ToLower()))
                    .ToListAsync();

                _context.ImageMasters.RemoveRange(images);
                await _context.SaveChangesAsync();
            }

            public async Task DeleteImagesByStatusAsync(string status)
            {
                bool isActive = status.ToLower() == "active" || status.ToLower() == "true";
                var images = await _context.ImageMasters
                    .Where(i => i.ImageIsActive == isActive)
                    .ToListAsync();

                _context.ImageMasters.RemoveRange(images);
                await _context.SaveChangesAsync();
            }

            public async Task DeleteInactiveImagesAsync()
            {
                var images = await _context.ImageMasters
                    .Where(i => i.ImageIsActive == false)
                    .ToListAsync();

                _context.ImageMasters.RemoveRange(images);
                await _context.SaveChangesAsync();
            }

            public async Task DeleteAllImagesAsync()
            {
                var allImages = await _context.ImageMasters.ToListAsync();
                _context.ImageMasters.RemoveRange(allImages);
                await _context.SaveChangesAsync();
            }

            // ==============================
            // BUSINESS LOGIC OPERATIONS
            // ==============================
            public async Task<List<ImageMaster>> SearchImagesAsync(string searchTerm)
            {
                return await _context.ImageMasters
                    .Where(i => (i.ImagePath != null && i.ImagePath.Contains(searchTerm)) ||
                               (i.AlbumId != null && i.AlbumId.ToString().Contains(searchTerm)))
                    .ToListAsync();
            }

            public async Task<List<ImageMaster>> GetImagesWithPaginationAsync(int page, int size)
            {
                return await _context.ImageMasters
                    .Skip(page * size)
                    .Take(size)
                    .ToListAsync();
            }

            public async Task<List<ImageMaster>> GetImagesSortedByNameAsync()
            {
                return await _context.ImageMasters
                    .OrderBy(i => i.ImagePath)
                    .ToListAsync();
            }

            public async Task<List<ImageMaster>> GetImagesSortedByTypeAsync()
            {
                return await _context.ImageMasters
                    .OrderBy(i => Path.GetExtension(i.ImagePath ?? ""))
                    .ToListAsync();
            }

            public async Task<List<ImageMaster>> GetImagesSortedByStatusAsync()
            {
                return await _context.ImageMasters
                    .OrderBy(i => i.ImageIsActive)
                    .ToListAsync();
            }

            public async Task<List<ImageMaster>> GetFeaturedImagesAsync()
            {
                // Assuming featured images are those marked as album covers
                return await _context.ImageMasters
                    .Where(i => i.IsAlbumCover == true)
                    .ToListAsync();
            }

            public async Task<List<ImageMaster>> GetRecentImagesAsync()
            {
                // Get images created in the last 30 days
                var thirtyDaysAgo = DateTime.Now.AddDays(-30);
                return await _context.ImageMasters
                    .Where(i => i.CreatedDate >= thirtyDaysAgo)
                    .OrderByDescending(i => i.CreatedDate)
                    .ToListAsync();
            }
        }
    }

