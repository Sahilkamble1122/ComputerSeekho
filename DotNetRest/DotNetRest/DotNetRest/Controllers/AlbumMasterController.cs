using DotNetRest.Models;
using DotNetRest.Service.Impl;
using Microsoft.AspNetCore.Mvc;

namespace DotNetRest.Controllers
{
    
        [ApiController]
        [Route("api/albums")]
        [Produces("application/json")]
        public class AlbumMasterController : ControllerBase
        {
            private readonly IAlbumMasterService _albumMasterService;

            public AlbumMasterController(IAlbumMasterService albumMasterService)
            {
                _albumMasterService = albumMasterService;
            }

            // ===========================================
            // CREATE OPERATIONS
            // ===========================================

            /// <summary>
            /// Creates a new album
            /// </summary>
            /// <param name="album">Album data to create</param>
            /// <returns>Created album</returns>
            [HttpPost]
            [ProducesResponseType(typeof(AlbumMaster), StatusCodes.Status201Created)]
            [ProducesResponseType(StatusCodes.Status400BadRequest)]
            public async Task<ActionResult<AlbumMaster>> CreateAlbum([FromBody] AlbumMaster album)
            {
                try
                {
                    var savedAlbum = await _albumMasterService.SaveAlbumAsync(album);
                    return CreatedAtAction(nameof(GetAlbumById), new { albumId = savedAlbum.AlbumId }, savedAlbum);
                }
                catch (Exception)
                {
                    return BadRequest();
                }
            }

            /// <summary>
            /// Creates multiple albums in bulk
            /// </summary>
            /// <param name="albums">List of albums to create</param>
            /// <returns>List of created albums</returns>
            [HttpPost("bulk")]
            [ProducesResponseType(typeof(IEnumerable<AlbumMaster>), StatusCodes.Status201Created)]
            [ProducesResponseType(StatusCodes.Status400BadRequest)]
            public async Task<ActionResult<IEnumerable<AlbumMaster>>> CreateMultipleAlbums([FromBody] IEnumerable<AlbumMaster> albums)
            {
                try
                {
                    var savedAlbums = await _albumMasterService.SaveAllAlbumsAsync(albums);
                    return CreatedAtAction(nameof(GetAllAlbums), savedAlbums);
                }
                catch (Exception)
                {
                    return BadRequest();
                }
            }

            // ===========================================
            // READ OPERATIONS
            // ===========================================

            /// <summary>
            /// Gets all albums
            /// </summary>
            /// <returns>List of all albums</returns>
            [HttpGet]
            [ProducesResponseType(typeof(IEnumerable<AlbumMaster>), StatusCodes.Status200OK)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<ActionResult<IEnumerable<AlbumMaster>>> GetAllAlbums()
            {
                try
                {
                    var albums = await _albumMasterService.GetAllAlbumsAsync();
                    return Ok(albums);
                }
            catch (Exception ex)
            {
                // Log the exception here (using a logging framework or built-in logger)
                return StatusCode(500, new { message = "Internal server error", error = ex.Message });
            }
        }

            /// <summary>
            /// Gets an album by ID
            /// </summary>
            /// <param name="albumId">Album ID</param>
            /// <returns>Album if found</returns>
            [HttpGet("{albumId}")]
            [ProducesResponseType(typeof(AlbumMaster), StatusCodes.Status200OK)]
            [ProducesResponseType(StatusCodes.Status404NotFound)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<ActionResult<AlbumMaster>> GetAlbumById(int albumId)
            {
                try
                {
                    var album = await _albumMasterService.GetAlbumByIdAsync(albumId);
                    if (album == null)
                    {
                        return NotFound();
                    }
                    return Ok(album);
                }
                catch (Exception)
                {
                    return StatusCode(500, "Internal server error");
                }
            }

            /// <summary>
            /// Tests if an album exists
            /// </summary>
            /// <param name="albumId">Album ID to test</param>
            /// <returns>Existence status</returns>
            [HttpGet("test/{albumId}")]
            [ProducesResponseType(typeof(string), StatusCodes.Status200OK)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<ActionResult<string>> TestAlbumExists(int albumId)
            {
                try
                {
                    var album = await _albumMasterService.GetAlbumByIdAsync(albumId);
                    bool exists = album != null;
                    return Ok($"Album {albumId} exists: {exists}");
                }
                catch (Exception ex)
                {
                    return StatusCode(500, $"Error checking album: {ex.Message}");
                }
            }

            /// <summary>
            /// Gets albums by name
            /// </summary>
            /// <param name="albumName">Album name to search for</param>
            /// <returns>List of matching albums</returns>
            [HttpGet("search/name/{albumName}")]
            [ProducesResponseType(typeof(IEnumerable<AlbumMaster>), StatusCodes.Status200OK)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<ActionResult<IEnumerable<AlbumMaster>>> GetAlbumsByName(string albumName)
            {
                try
                {
                    var albums = await _albumMasterService.GetAlbumsByNameAsync(albumName);
                    return Ok(albums);
                }
                catch (Exception)
                {
                    return StatusCode(500, "Internal server error");
                }
            }

            /// <summary>
            /// Gets albums by description
            /// </summary>
            /// <param name="description">Description to search for</param>
            /// <returns>List of matching albums</returns>
            [HttpGet("search/description/{description}")]
            [ProducesResponseType(typeof(IEnumerable<AlbumMaster>), StatusCodes.Status200OK)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<ActionResult<IEnumerable<AlbumMaster>>> GetAlbumsByDescription(string description)
            {
                try
                {
                    var albums = await _albumMasterService.GetAlbumsByDescriptionAsync(description);
                    return Ok(albums);
                }
                catch (Exception)
                {
                    return StatusCode(500, "Internal server error");
                }
            }

            /// <summary>
            /// Gets albums by status
            /// </summary>
            /// <param name="status">Status to search for</param>
            /// <returns>List of matching albums</returns>
            [HttpGet("search/status/{status}")]
            [ProducesResponseType(typeof(IEnumerable<AlbumMaster>), StatusCodes.Status200OK)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<ActionResult<IEnumerable<AlbumMaster>>> GetAlbumsByStatus(string status)
            {
                try
                {
                    var albums = await _albumMasterService.GetAlbumsByStatusAsync(status);
                    return Ok(albums);
                }
                catch (Exception)
                {
                    return StatusCode(500, "Internal server error");
                }
            }

            /// <summary>
            /// Gets albums containing the specified name
            /// </summary>
            /// <param name="albumName">Partial album name to search for</param>
            /// <returns>List of matching albums</returns>
            [HttpGet("search/name-contains/{albumName}")]
            [ProducesResponseType(typeof(IEnumerable<AlbumMaster>), StatusCodes.Status200OK)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<ActionResult<IEnumerable<AlbumMaster>>> GetAlbumsByNameContaining(string albumName)
            {
                try
                {
                    var albums = await _albumMasterService.GetAlbumsByNameContainingAsync(albumName);
                    return Ok(albums);
                }
                catch (Exception)
                {
                    return StatusCode(500, "Internal server error");
                }
            }

            /// <summary>
            /// Gets all active albums
            /// </summary>
            /// <returns>List of active albums</returns>
            [HttpGet("active")]
            [ProducesResponseType(typeof(IEnumerable<AlbumMaster>), StatusCodes.Status200OK)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<ActionResult<IEnumerable<AlbumMaster>>> GetActiveAlbums()
            {
                try
                {
                    var albums = await _albumMasterService.GetActiveAlbumsAsync();
                    return Ok(albums);
                }
                catch (Exception)
                {
                    return StatusCode(500, "Internal server error");
                }
            }

            /// <summary>
            /// Counts active albums
            /// </summary>
            /// <returns>Count of active albums</returns>
            [HttpGet("count/active")]
            [ProducesResponseType(typeof(long), StatusCodes.Status200OK)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<ActionResult<long>> CountActiveAlbums()
            {
                try
                {
                    var count = await _albumMasterService.CountActiveAlbumsAsync();
                    return Ok(count);
                }
                catch (Exception)
                {
                    return StatusCode(500, "Internal server error");
                }
            }

            // ===========================================
            // UPDATE OPERATIONS
            // ===========================================

            /// <summary>
            /// Updates an album completely
            /// </summary>
            /// <param name="albumId">Album ID to update</param>
            /// <param name="albumDetails">Updated album data</param>
            /// <returns>Updated album</returns>
            [HttpPut("{albumId}")]
            [ProducesResponseType(typeof(AlbumMaster), StatusCodes.Status200OK)]
            [ProducesResponseType(StatusCodes.Status404NotFound)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<ActionResult<AlbumMaster>> UpdateAlbum(int albumId, [FromBody] AlbumMaster albumDetails)
            {
                try
                {
                    var updatedAlbum = await _albumMasterService.UpdateAlbumAsync(albumId, albumDetails);
                    return Ok(updatedAlbum);
                }
                catch (InvalidOperationException)
                {
                    return NotFound();
                }
                catch (Exception)
                {
                    return StatusCode(500, "Internal server error");
                }
            }

            /// <summary>
            /// Updates album name
            /// </summary>
            /// <param name="albumId">Album ID</param>
            /// <param name="albumName">New album name</param>
            /// <returns>Updated album</returns>
            [HttpPatch("{albumId}/name")]
            [ProducesResponseType(typeof(AlbumMaster), StatusCodes.Status200OK)]
            [ProducesResponseType(StatusCodes.Status404NotFound)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<ActionResult<AlbumMaster>> UpdateAlbumName(int albumId, [FromQuery] string albumName)
            {
                try
                {
                    var updatedAlbum = await _albumMasterService.UpdateAlbumNameAsync(albumId, albumName);
                    return Ok(updatedAlbum);
                }
                catch (InvalidOperationException)
                {
                    return NotFound();
                }
                catch (Exception)
                {
                    return StatusCode(500, "Internal server error");
                }
            }

            /// <summary>
            /// Updates album description
            /// </summary>
            /// <param name="albumId">Album ID</param>
            /// <param name="description">New description</param>
            /// <returns>Updated album</returns>
            [HttpPatch("{albumId}/description")]
            [ProducesResponseType(typeof(AlbumMaster), StatusCodes.Status200OK)]
            [ProducesResponseType(StatusCodes.Status404NotFound)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<ActionResult<AlbumMaster>> UpdateAlbumDescription(int albumId, [FromQuery] string description)
            {
                try
                {
                    var updatedAlbum = await _albumMasterService.UpdateAlbumDescriptionAsync(albumId, description);
                    return Ok(updatedAlbum);
                }
                catch (InvalidOperationException)
                {
                    return NotFound();
                }
                catch (Exception)
                {
                    return StatusCode(500, "Internal server error");
                }
            }

            /// <summary>
            /// Updates album status
            /// </summary>
            /// <param name="albumId">Album ID</param>
            /// <param name="status">New status</param>
            /// <returns>Updated album</returns>
            [HttpPatch("{albumId}/status")]
            [ProducesResponseType(typeof(AlbumMaster), StatusCodes.Status200OK)]
            [ProducesResponseType(StatusCodes.Status404NotFound)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<ActionResult<AlbumMaster>> UpdateAlbumStatus(int albumId, [FromQuery] string status)
            {
                try
                {
                    var updatedAlbum = await _albumMasterService.UpdateAlbumStatusAsync(albumId, status);
                    return Ok(updatedAlbum);
                }
                catch (InvalidOperationException)
                {
                    return NotFound();
                }
                catch (Exception)
                {
                    return StatusCode(500, "Internal server error");
                }
            }

            // ===========================================
            // DELETE OPERATIONS
            // ===========================================

            /// <summary>
            /// Deletes an album by ID
            /// </summary>
            /// <param name="albumId">Album ID to delete</param>
            /// <returns>No content on success</returns>
            [HttpDelete("{albumId}")]
            [ProducesResponseType(StatusCodes.Status204NoContent)]
            [ProducesResponseType(StatusCodes.Status404NotFound)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<IActionResult> DeleteAlbum(int albumId)
            {
                try
                {
                    Console.WriteLine($"Attempting to delete album with ID: {albumId}");
                    await _albumMasterService.DeleteAlbumAsync(albumId);
                    Console.WriteLine($"Successfully deleted album with ID: {albumId}");
                    return NoContent();
                }
                catch (InvalidOperationException ex)
                {
                    Console.WriteLine($"Album not found with ID: {albumId} - {ex.Message}");
                    return NotFound();
                }
                catch (Exception ex)
                {
                    Console.WriteLine($"Error deleting album with ID: {albumId} - {ex.Message}");
                    return StatusCode(500, "Internal server error");
                }
            }

            /// <summary>
            /// Deletes albums by status
            /// </summary>
            /// <param name="status">Status to delete by</param>
            /// <returns>No content on success</returns>
            [HttpDelete("status/{status}")]
            [ProducesResponseType(StatusCodes.Status204NoContent)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<IActionResult> DeleteAlbumsByStatus(string status)
            {
                try
                {
                    await _albumMasterService.DeleteAlbumsByStatusAsync(status);
                    return NoContent();
                }
                catch (Exception)
                {
                    return StatusCode(500, "Internal server error");
                }
            }

            /// <summary>
            /// Deletes all inactive albums
            /// </summary>
            /// <returns>No content on success</returns>
            [HttpDelete("inactive")]
            [ProducesResponseType(StatusCodes.Status204NoContent)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<IActionResult> DeleteInactiveAlbums()
            {
                try
                {
                    await _albumMasterService.DeleteInactiveAlbumsAsync();
                    return NoContent();
                }
                catch (Exception)
                {
                    return StatusCode(500, "Internal server error");
                }
            }

            /// <summary>
            /// Deletes all albums
            /// </summary>
            /// <returns>No content on success</returns>
            [HttpDelete("all")]
            [ProducesResponseType(StatusCodes.Status204NoContent)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<IActionResult> DeleteAllAlbums()
            {
                try
                {
                    await _albumMasterService.DeleteAllAlbumsAsync();
                    return NoContent();
                }
                catch (Exception)
                {
                    return StatusCode(500, "Internal server error");
                }
            }

            // ===========================================
            // BUSINESS LOGIC OPERATIONS
            // ===========================================

            /// <summary>
            /// Searches albums globally
            /// </summary>
            /// <param name="searchTerm">Term to search for</param>
            /// <returns>List of matching albums</returns>
            [HttpGet("search/global/{searchTerm}")]
            [ProducesResponseType(typeof(IEnumerable<AlbumMaster>), StatusCodes.Status200OK)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<ActionResult<IEnumerable<AlbumMaster>>> SearchAlbums(string searchTerm)
            {
                try
                {
                    var albums = await _albumMasterService.SearchAlbumsAsync(searchTerm);
                    return Ok(albums);
                }
                catch (Exception)
                {
                    return StatusCode(500, "Internal server error");
                }
            }

            /// <summary>
            /// Gets albums with pagination
            /// </summary>
            /// <param name="page">Page number (0-based)</param>
            /// <param name="size">Page size</param>
            /// <returns>List of albums for the specified page</returns>
            [HttpGet("pagination")]
            [ProducesResponseType(typeof(IEnumerable<AlbumMaster>), StatusCodes.Status200OK)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<ActionResult<IEnumerable<AlbumMaster>>> GetAlbumsWithPagination(
                [FromQuery] int page = 0,
                [FromQuery] int size = 10)
            {
                try
                {
                    var albums = await _albumMasterService.GetAlbumsWithPaginationAsync(page, size);
                    return Ok(albums);
                }
                catch (Exception)
                {
                    return StatusCode(500, "Internal server error");
                }
            }

            /// <summary>
            /// Gets albums sorted by name
            /// </summary>
            /// <returns>List of albums sorted by name</returns>
            [HttpGet("sorted/name")]
            [ProducesResponseType(typeof(IEnumerable<AlbumMaster>), StatusCodes.Status200OK)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<ActionResult<IEnumerable<AlbumMaster>>> GetAlbumsSortedByName()
            {
                try
                {
                    var albums = await _albumMasterService.GetAlbumsSortedByNameAsync();
                    return Ok(albums);
                }
                catch (Exception)
                {
                    return StatusCode(500, "Internal server error");
                }
            }

            /// <summary>
            /// Gets albums sorted by status
            /// </summary>
            /// <returns>List of albums sorted by status</returns>
            [HttpGet("sorted/status")]
            [ProducesResponseType(typeof(IEnumerable<AlbumMaster>), StatusCodes.Status200OK)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<ActionResult<IEnumerable<AlbumMaster>>> GetAlbumsSortedByStatus()
            {
                try
                {
                    var albums = await _albumMasterService.GetAlbumsSortedByStatusAsync();
                    return Ok(albums);
                }
                catch (Exception)
                {
                    return StatusCode(500, "Internal server error");
                }
            }

            /// <summary>
            /// Gets featured albums
            /// </summary>
            /// <returns>List of featured albums</returns>
            [HttpGet("featured")]
            [ProducesResponseType(typeof(IEnumerable<AlbumMaster>), StatusCodes.Status200OK)]
            [ProducesResponseType(StatusCodes.Status500InternalServerError)]
            public async Task<ActionResult<IEnumerable<AlbumMaster>>> GetFeaturedAlbums()
            {
                try
                {
                    var albums = await _albumMasterService.GetFeaturedAlbumsAsync();
                    return Ok(albums);
                }
                catch (Exception)
                {
                    return StatusCode(500, "Internal server error");
                }
            }
        }
}
