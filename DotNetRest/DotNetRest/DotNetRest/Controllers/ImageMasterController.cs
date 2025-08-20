using Microsoft.AspNetCore.Mvc;
using DotNetRest.Models;
using DotNetRest.Service;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using System.IO;

namespace DotNetRest.Controllers
{
    [Route("api/images")]
    [ApiController]
    public class ImageMasterController : ControllerBase
    {
        private readonly IImageMasterService _imageMasterService;

        public ImageMasterController(IImageMasterService imageMasterService)
        {
            _imageMasterService = imageMasterService;
        }

        // ==============================
        // CREATE OPERATIONS
        // ==============================
        [HttpPost]
        public async Task<ActionResult<ImageMaster>> createImage([FromBody] ImageMaster image)
        {
            try
            {
                var savedImage = await _imageMasterService.SaveImageAsync(image);
                return CreatedAtAction(nameof(getImageById), new { imageId = savedImage.ImageId }, savedImage);
            }
            catch
            {
                return BadRequest();
            }
        }

        [HttpPost("bulk")]
        public async Task<ActionResult<List<ImageMaster>>> createMultipleImages([FromBody] List<ImageMaster> images)
        {
            try
            {
                var savedImages = await _imageMasterService.SaveAllImagesAsync(images);
                return Created("", savedImages);
            }
            catch
            {
                return BadRequest();
            }
        }

        [HttpPost("upload")]
        public async Task<ActionResult<string>> uploadImage(IFormFile file)
        {
            if (file == null || file.Length == 0)
            {
                return BadRequest("No file selected");
            }
            try
            {
                // Get the project root directory (where the application is running from)
                string projectRoot = Directory.GetCurrentDirectory();
                string uploadsDir = Path.Combine(projectRoot, "uploads");
                
                // Create uploads directory if it doesn't exist
                Directory.CreateDirectory(uploadsDir);
                
                // Generate a safe filename
                string originalFilename = file.FileName;
                string safeFilename = DateTimeOffset.UtcNow.ToUnixTimeMilliseconds() + "_" +
                    (originalFilename != null ? System.Text.RegularExpressions.Regex.Replace(originalFilename, @"[^a-zA-Z0-9.\-_]", "_") : "image");
                
                // Save the file
                string filePath = Path.Combine(uploadsDir, safeFilename);
                using (var stream = new FileStream(filePath, FileMode.Create))
                {
                    await file.CopyToAsync(stream);
                }
                
                // Return relative path for consistency
                string relativePath = "uploads/" + safeFilename;
                return Ok("File uploaded successfully: " + relativePath);
            }
            catch (IOException e)
            {
                return StatusCode(500, "Error uploading file: " + e.Message);
            }
            catch (Exception e)
            {
                return StatusCode(500, "Error creating uploads directory: " + e.Message);
            }
        }

        // ==============================
        // READ OPERATIONS
        // ==============================
        [HttpGet]
        public async Task<ActionResult<List<ImageMaster>>> getAllImages()
        {
            try
            {
                var images = await _imageMasterService.GetAllImagesAsync();
                return Ok(images);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("{imageId}")]
        public async Task<ActionResult<ImageMaster>> getImageById(int imageId)
        {
            try
            {
                var image = await _imageMasterService.GetImageByIdAsync(imageId);
                if (image == null) return NotFound();
                return Ok(image);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/name/{imageName}")]
        public async Task<ActionResult<List<ImageMaster>>> getImagesByName(string imageName)
        {
            try
            {
                var images = await _imageMasterService.GetImagesByNameAsync(imageName);
                return Ok(images);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/type/{imageType}")]
        public async Task<ActionResult<List<ImageMaster>>> getImagesByType(string imageType)
        {
            try
            {
                var images = await _imageMasterService.GetImagesByTypeAsync(imageType);
                return Ok(images);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/status/{status}")]
        public async Task<ActionResult<List<ImageMaster>>> getImagesByStatus(string status)
        {
            try
            {
                var images = await _imageMasterService.GetImagesByStatusAsync(status);
                return Ok(images);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/name-contains/{imageName}")]
        public async Task<ActionResult<List<ImageMaster>>> getImagesByNameContaining(string imageName)
        {
            try
            {
                var images = await _imageMasterService.GetImagesByNameContainingAsync(imageName);
                return Ok(images);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("active")]
        public async Task<ActionResult<List<ImageMaster>>> getActiveImages()
        {
            try
            {
                var images = await _imageMasterService.GetActiveImagesAsync();
                return Ok(images);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("count/active")]
        public async Task<ActionResult<long>> countActiveImages()
        {
            try
            {
                var count = await _imageMasterService.CountActiveImagesAsync();
                return Ok(count);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        // ==============================
        // UPDATE OPERATIONS
        // ==============================
        [HttpPut("{imageId}")]
        public async Task<ActionResult<ImageMaster>> updateImage(int imageId, [FromBody] ImageMaster imageDetails)
        {
            try
            {
                var updatedImage = await _imageMasterService.UpdateImageAsync(imageId, imageDetails);
                return Ok(updatedImage);
            }
            catch (Exception e) when (e.Message.Contains("not found"))
            {
                return NotFound();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpPatch("{imageId}/name")]
        public async Task<ActionResult<ImageMaster>> updateImageName(int imageId, [FromQuery] string imageName)
        {
            try
            {
                var updatedImage = await _imageMasterService.UpdateImageNameAsync(imageId, imageName);
                return Ok(updatedImage);
            }
            catch (Exception e) when (e.Message.Contains("not found"))
            {
                return NotFound();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpPatch("{imageId}/type")]
        public async Task<ActionResult<ImageMaster>> updateImageType(int imageId, [FromQuery] string imageType)
        {
            try
            {
                var updatedImage = await _imageMasterService.UpdateImageTypeAsync(imageId, imageType);
                return Ok(updatedImage);
            }
            catch (Exception e) when (e.Message.Contains("not found"))
            {
                return NotFound();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpPatch("{imageId}/status")]
        public async Task<ActionResult<ImageMaster>> updateImageStatus(int imageId, [FromQuery] string status)
        {
            try
            {
                var updatedImage = await _imageMasterService.UpdateImageStatusAsync(imageId, status);
                return Ok(updatedImage);
            }
            catch (Exception e) when (e.Message.Contains("not found"))
            {
                return NotFound();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        // ==============================
        // DELETE OPERATIONS
        // ==============================
        [HttpDelete("{imageId}")]
        public async Task<IActionResult> deleteImage(int imageId)
        {
            try
            {
                await _imageMasterService.DeleteImageAsync(imageId);
                return NoContent();
            }
            catch (Exception e) when (e.Message.Contains("not found"))
            {
                return NotFound();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("type/{imageType}")]
        public async Task<IActionResult> deleteImagesByType(string imageType)
        {
            try
            {
                await _imageMasterService.DeleteImagesByTypeAsync(imageType);
                return NoContent();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("status/{status}")]
        public async Task<IActionResult> deleteImagesByStatus(string status)
        {
            try
            {
                await _imageMasterService.DeleteImagesByStatusAsync(status);
                return NoContent();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("inactive")]
        public async Task<IActionResult> deleteInactiveImages()
        {
            try
            {
                await _imageMasterService.DeleteInactiveImagesAsync();
                return NoContent();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("all")]
        public async Task<IActionResult> deleteAllImages()
        {
            try
            {
                await _imageMasterService.DeleteAllImagesAsync();
                return NoContent();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        // ==============================
        // BUSINESS LOGIC OPERATIONS
        // ==============================
        [HttpGet("search/global/{searchTerm}")]
        public async Task<ActionResult<List<ImageMaster>>> searchImages(string searchTerm)
        {
            try
            {
                var images = await _imageMasterService.SearchImagesAsync(searchTerm);
                return Ok(images);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("pagination")]
        public async Task<ActionResult<List<ImageMaster>>> getImagesWithPagination([FromQuery] int page = 0, [FromQuery] int size = 10)
        {
            try
            {
                var images = await _imageMasterService.GetImagesWithPaginationAsync(page, size);
                return Ok(images);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("sorted/name")]
        public async Task<ActionResult<List<ImageMaster>>> getImagesSortedByName()
        {
            try
            {
                var images = await _imageMasterService.GetImagesSortedByNameAsync();
                return Ok(images);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("sorted/type")]
        public async Task<ActionResult<List<ImageMaster>>> getImagesSortedByType()
        {
            try
            {
                var images = await _imageMasterService.GetImagesSortedByTypeAsync();
                return Ok(images);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("sorted/status")]
        public async Task<ActionResult<List<ImageMaster>>> getImagesSortedByStatus()
        {
            try
            {
                var images = await _imageMasterService.GetImagesSortedByStatusAsync();
                return Ok(images);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("featured")]
        public async Task<ActionResult<List<ImageMaster>>> getFeaturedImages()
        {
            try
            {
                var images = await _imageMasterService.GetFeaturedImagesAsync();
                return Ok(images);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("recent")]
        public async Task<ActionResult<List<ImageMaster>>> getRecentImages()
        {
            try
            {
                var images = await _imageMasterService.GetRecentImagesAsync();
                return Ok(images);
            }
            catch
            {
                return StatusCode(500);
            }
        }
    }
}
