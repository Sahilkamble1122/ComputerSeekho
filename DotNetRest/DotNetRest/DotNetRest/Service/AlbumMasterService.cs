using DotNetRest.Data;
using DotNetRest.Models;
using DotNetRest.Service.Impl;
using Microsoft.EntityFrameworkCore;

namespace DotNetRest.Service
{
    public class AlbumMasterService : IAlbumMasterService
    {
        private readonly ApplicationDbContext _context;

        public AlbumMasterService(ApplicationDbContext context)
        {
            _context = context;
        }

        // CREATE OPERATIONS
        public async Task<AlbumMaster> SaveAlbumAsync(AlbumMaster album)
        {
            if (album.CreatedDate == null)
                album.CreatedDate = DateTime.Now;
            if (album.UpdatedDate == null)
                album.UpdatedDate = DateTime.Now;

            _context.AlbumMasters.Add(album);
            await _context.SaveChangesAsync();
            return album;
        }

        public async Task<IEnumerable<AlbumMaster>> SaveAllAlbumsAsync(IEnumerable<AlbumMaster> albums)
        {
            foreach (var album in albums)
            {
                if (album.CreatedDate == null)
                    album.CreatedDate = DateTime.Now;
                if (album.UpdatedDate == null)
                    album.UpdatedDate = DateTime.Now;
            }

            _context.AlbumMasters.AddRange(albums);
            await _context.SaveChangesAsync();
            return albums;
        }

        // READ OPERATIONS
        public async Task<IEnumerable<AlbumMaster>> GetAllAlbumsAsync()
        {
            return await _context.AlbumMasters.ToListAsync();
        }

        public async Task<AlbumMaster?> GetAlbumByIdAsync(int albumId)
        {
            return await _context.AlbumMasters.FindAsync(albumId);
        }

        public async Task<IEnumerable<AlbumMaster>> GetAlbumsByNameAsync(string albumName)
        {
            return await _context.AlbumMasters
                .Where(a => a.AlbumName == albumName)
                .ToListAsync();
        }

        public async Task<IEnumerable<AlbumMaster>> GetAlbumsByDescriptionAsync(string description)
        {
            return await _context.AlbumMasters
                .Where(a => a.AlbumDescription == description)
                .ToListAsync();
        }

        public async Task<IEnumerable<AlbumMaster>> GetAlbumsByStatusAsync(string status)
        {
            bool isActive = "Active".Equals(status, StringComparison.OrdinalIgnoreCase);
            return await _context.AlbumMasters
                .Where(a => a.AlbumIsActive == isActive)
                .ToListAsync();
        }

        public async Task<IEnumerable<AlbumMaster>> GetAlbumsByNameContainingAsync(string albumName)
        {
            return await _context.AlbumMasters
                .Where(a => a.AlbumName.Contains(albumName))
                .ToListAsync();
        }

        public async Task<IEnumerable<AlbumMaster>> GetActiveAlbumsAsync()
        {
            return await _context.AlbumMasters
                .Where(a => (bool)a.AlbumIsActive)
                .ToListAsync();
        }

        public async Task<long> CountActiveAlbumsAsync()
        {
            return await _context.AlbumMasters
                .Where(a =>(bool) a.AlbumIsActive)
                .LongCountAsync();
        }

        // UPDATE OPERATIONS
        public async Task<AlbumMaster> UpdateAlbumAsync(int albumId, AlbumMaster albumDetails)
        {
            var existingAlbum = await _context.AlbumMasters.FindAsync(albumId);
            if (existingAlbum == null)
                throw new InvalidOperationException($"Album not found with id: {albumId}");

            existingAlbum.AlbumName = albumDetails.AlbumName;
            existingAlbum.AlbumDescription = albumDetails.AlbumDescription;
            existingAlbum.AlbumIsActive = albumDetails.AlbumIsActive;
            existingAlbum.UpdatedDate = DateTime.Now;

            await _context.SaveChangesAsync();
            return existingAlbum;
        }

        public async Task<AlbumMaster> UpdateAlbumNameAsync(int albumId, string albumName)
        {
            var album = await _context.AlbumMasters.FindAsync(albumId);
            if (album == null)
                throw new InvalidOperationException($"Album not found with id: {albumId}");

            album.AlbumName = albumName;
            album.UpdatedDate = DateTime.Now;

            await _context.SaveChangesAsync();
            return album;
        }

        public async Task<AlbumMaster> UpdateAlbumDescriptionAsync(int albumId, string description)
        {
            var album = await _context.AlbumMasters.FindAsync(albumId);
            if (album == null)
                throw new InvalidOperationException($"Album not found with id: {albumId}");

            album.AlbumDescription = description;
            album.UpdatedDate = DateTime.Now;

            await _context.SaveChangesAsync();
            return album;
        }

        public async Task<AlbumMaster> UpdateAlbumStatusAsync(int albumId, string status)
        {
            var album = await _context.AlbumMasters.FindAsync(albumId);
            if (album == null)
                throw new InvalidOperationException($"Album not found with id: {albumId}");

            album.AlbumIsActive = "Active".Equals(status, StringComparison.OrdinalIgnoreCase);
            album.UpdatedDate = DateTime.Now;

            await _context.SaveChangesAsync();
            return album;
        }

        // DELETE OPERATIONS
        public async Task DeleteAlbumAsync(int albumId)
        {
            var album = await _context.AlbumMasters.FindAsync(albumId);
            if (album == null)
                throw new InvalidOperationException($"Album not found with id: {albumId}");

            _context.AlbumMasters.Remove(album);
            await _context.SaveChangesAsync();
        }

        public async Task DeleteAlbumsByStatusAsync(string status)
        {
            bool isActive = "Active".Equals(status, StringComparison.OrdinalIgnoreCase);
            var albumsToDelete = await _context.AlbumMasters
                .Where(a => a.AlbumIsActive == isActive)
                .ToListAsync();

            _context.AlbumMasters.RemoveRange(albumsToDelete);
            await _context.SaveChangesAsync();
        }

        public async Task DeleteInactiveAlbumsAsync()
        {
            var inactiveAlbums = await _context.AlbumMasters
                .Where(a => (bool)!a.AlbumIsActive)
                .ToListAsync();

            _context.AlbumMasters.RemoveRange(inactiveAlbums);
            await _context.SaveChangesAsync();
        }

        public async Task DeleteAllAlbumsAsync()
        {
            var allAlbums = await _context.AlbumMasters.ToListAsync();
            _context.AlbumMasters.RemoveRange(allAlbums);
            await _context.SaveChangesAsync();
        }

        // BUSINESS LOGIC OPERATIONS
        public async Task<IEnumerable<AlbumMaster>> SearchAlbumsAsync(string searchTerm)
        {
            return await _context.AlbumMasters
                .Where(a => a.AlbumName.Contains(searchTerm) ||
                           a.AlbumDescription.Contains(searchTerm))
                .ToListAsync();
        }

        public async Task<IEnumerable<AlbumMaster>> GetAlbumsWithPaginationAsync(int page, int size)
        {
            return await _context.AlbumMasters
                .Skip(page * size)
                .Take(size)
                .ToListAsync();
        }

        public async Task<IEnumerable<AlbumMaster>> GetAlbumsSortedByNameAsync()
        {
            return await _context.AlbumMasters
                .OrderBy(a => a.AlbumName)
                .ToListAsync();
        }

        public async Task<IEnumerable<AlbumMaster>> GetAlbumsSortedByStatusAsync()
        {
            return await _context.AlbumMasters
                .OrderBy(a => a.AlbumIsActive)
                .ToListAsync();
        }

        public async Task<IEnumerable<AlbumMaster>> GetFeaturedAlbumsAsync()
        {
            // For now, return active albums as featured
            // This could be enhanced with additional logic later
            return await _context.AlbumMasters
                .Where(a => (bool)a.AlbumIsActive)
                .ToListAsync();
        }
    }
}
