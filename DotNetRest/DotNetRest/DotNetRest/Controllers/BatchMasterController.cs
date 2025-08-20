using Microsoft.AspNetCore.Mvc;
using DotNetRest.Models;
using DotNetRest.Service;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace DotNetRest.Controllers
{
    [Route("api/batches")]
    [ApiController]
    public class BatchMasterController : ControllerBase
    {
        private readonly IBatchMasterService _batchMasterService;

        public BatchMasterController(IBatchMasterService batchMasterService)
        {
            _batchMasterService = batchMasterService;
        }

        // ==============================
        // CREATE OPERATIONS
        // ==============================
        [HttpPost]
        public async Task<ActionResult<BatchMaster>> createBatch([FromBody] BatchMaster batch)
        {
            try
            {
                var savedBatch = await _batchMasterService.SaveBatchAsync(batch);
                return CreatedAtAction(nameof(getBatchById), new { batchId = savedBatch.BatchId }, savedBatch);
            }
            catch
            {
                return BadRequest();
            }
        }

        [HttpPost("bulk")]
        public async Task<ActionResult<List<BatchMaster>>> createMultipleBatches([FromBody] List<BatchMaster> batches)
        {
            try
            {
                var savedBatches = await _batchMasterService.SaveAllBatchesAsync(batches);
                return Created("", savedBatches);
            }
            catch
            {
                return BadRequest();
            }
        }

        // ==============================
        // READ OPERATIONS
        // ==============================
        [HttpGet]
        public async Task<ActionResult<List<BatchMaster>>> getAllBatches()
        {
            try
            {
                var batches = await _batchMasterService.GetAllBatchesAsync();
                return Ok(batches);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("{batchId}")]
        public async Task<ActionResult<BatchMaster>> getBatchById(int batchId)
        {
            try
            {
                var batch = await _batchMasterService.GetBatchByIdAsync(batchId);
                if (batch == null) return NotFound();
                return Ok(batch);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/name/{batchName}")]
        public async Task<ActionResult<List<BatchMaster>>> getBatchesByName(string batchName)
        {
            try
            {
                var batches = await _batchMasterService.GetBatchesByNameAsync(batchName);
                return Ok(batches);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/course/{courseId}")]
        public async Task<ActionResult<List<BatchMaster>>> getBatchesByCourseId(int courseId)
        {
            try
            {
                var batches = await _batchMasterService.GetBatchesByCourseIdAsync(courseId);
                return Ok(batches);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/staff/{staffId}")]
        public async Task<ActionResult<List<BatchMaster>>> getBatchesByStaffId(int staffId)
        {
            try
            {
                var batches = await _batchMasterService.GetBatchesByStaffIdAsync(staffId);
                return Ok(batches);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/status/{status}")]
        public async Task<ActionResult<List<BatchMaster>>> getBatchesByStatus(string status)
        {
            try
            {
                var batches = await _batchMasterService.GetBatchesByStatusAsync(status);
                return Ok(batches);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/name-contains/{batchName}")]
        public async Task<ActionResult<List<BatchMaster>>> getBatchesByNameContaining(string batchName)
        {
            try
            {
                var batches = await _batchMasterService.GetBatchesByNameContainingAsync(batchName);
                return Ok(batches);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/capacity-range")]
        public async Task<ActionResult<List<BatchMaster>>> getBatchesByCapacityRange([FromQuery] int minCapacity, [FromQuery] int maxCapacity)
        {
            try
            {
                var batches = await _batchMasterService.GetBatchesByCapacityRangeAsync(minCapacity, maxCapacity);
                return Ok(batches);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("count/course/{courseId}")]
        public async Task<ActionResult<long>> countBatchesByCourseId(int courseId)
        {
            try
            {
                var count = await _batchMasterService.CountBatchesByCourseIdAsync(courseId);
                return Ok(count);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("count/staff/{staffId}")]
        public async Task<ActionResult<long>> countBatchesByStaffId(int staffId)
        {
            try
            {
                var count = await _batchMasterService.CountBatchesByStaffIdAsync(staffId);
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
        [HttpPut("{batchId}")]
        public async Task<ActionResult<BatchMaster>> updateBatch(int batchId, [FromBody] BatchMaster batchDetails)
        {
            try
            {
                var updatedBatch = await _batchMasterService.UpdateBatchAsync(batchId, batchDetails);
                return Ok(updatedBatch);
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

        [HttpPatch("{batchId}/name")]
        public async Task<ActionResult<BatchMaster>> updateBatchName(int batchId, [FromQuery] string batchName)
        {
            try
            {
                var updatedBatch = await _batchMasterService.UpdateBatchNameAsync(batchId, batchName);
                return Ok(updatedBatch);
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

        [HttpPatch("{batchId}/status")]
        public async Task<ActionResult<BatchMaster>> updateBatchStatus(int batchId, [FromQuery] string status)
        {
            try
            {
                var updatedBatch = await _batchMasterService.UpdateBatchStatusAsync(batchId, status);
                return Ok(updatedBatch);
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

        [HttpPatch("{batchId}/capacity")]
        public async Task<ActionResult<BatchMaster>> updateBatchCapacity(int batchId, [FromQuery] int capacity)
        {
            try
            {
                var updatedBatch = await _batchMasterService.UpdateBatchCapacityAsync(batchId, capacity);
                return Ok(updatedBatch);
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

        [HttpPatch("{batchId}/staff")]
        public async Task<ActionResult<BatchMaster>> updateBatchStaff(int batchId, [FromQuery] int staffId)
        {
            try
            {
                var updatedBatch = await _batchMasterService.UpdateBatchStaffAsync(batchId, staffId);
                return Ok(updatedBatch);
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
        [HttpDelete("{batchId}")]
        public async Task<IActionResult> deleteBatch(int batchId)
        {
            try
            {
                await _batchMasterService.DeleteBatchAsync(batchId);
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

        [HttpDelete("course/{courseId}")]
        public async Task<IActionResult> deleteBatchesByCourseId(int courseId)
        {
            try
            {
                await _batchMasterService.DeleteBatchesByCourseIdAsync(courseId);
                return NoContent();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("staff/{staffId}")]
        public async Task<IActionResult> deleteBatchesByStaffId(int staffId)
        {
            try
            {
                await _batchMasterService.DeleteBatchesByStaffIdAsync(staffId);
                return NoContent();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("status/{status}")]
        public async Task<IActionResult> deleteBatchesByStatus(string status)
        {
            try
            {
                await _batchMasterService.DeleteBatchesByStatusAsync(status);
                return NoContent();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("all")]
        public async Task<IActionResult> deleteAllBatches()
        {
            try
            {
                await _batchMasterService.DeleteAllBatchesAsync();
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
        public async Task<ActionResult<List<BatchMaster>>> searchBatches(string searchTerm)
        {
            try
            {
                var batches = await _batchMasterService.SearchBatchesAsync(searchTerm);
                return Ok(batches);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/course-staff")]
        public async Task<ActionResult<List<BatchMaster>>> getBatchesByCourseAndStaff([FromQuery] int courseId, [FromQuery] int staffId)
        {
            try
            {
                var batches = await _batchMasterService.GetBatchesByCourseAndStaffAsync(courseId, staffId);
                return Ok(batches);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("pagination")]
        public async Task<ActionResult<List<BatchMaster>>> getBatchesWithPagination([FromQuery] int page = 0, [FromQuery] int size = 10)
        {
            try
            {
                var batches = await _batchMasterService.GetBatchesWithPaginationAsync(page, size);
                return Ok(batches);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("sorted/name")]
        public async Task<ActionResult<List<BatchMaster>>> getBatchesSortedByName()
        {
            try
            {
                var batches = await _batchMasterService.GetBatchesSortedByNameAsync();
                return Ok(batches);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("sorted/capacity")]
        public async Task<ActionResult<List<BatchMaster>>> getBatchesSortedByCapacity()
        {
            try
            {
                var batches = await _batchMasterService.GetBatchesSortedByCapacityAsync();
                return Ok(batches);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("sorted/course")]
        public async Task<ActionResult<List<BatchMaster>>> getBatchesSortedByCourseId()
        {
            try
            {
                var batches = await _batchMasterService.GetBatchesSortedByCourseIdAsync();
                return Ok(batches);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("active")]
        public async Task<ActionResult<List<BatchMaster>>> getActiveBatches()
        {
            try
            {
                var batches = await _batchMasterService.GetActiveBatchesAsync();
                return Ok(batches);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("available")]
        public async Task<ActionResult<List<BatchMaster>>> getAvailableBatches()
        {
            try
            {
                var batches = await _batchMasterService.GetAvailableBatchesAsync();
                return Ok(batches);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("full")]
        public async Task<ActionResult<List<BatchMaster>>> getFullBatches()
        {
            try
            {
                var batches = await _batchMasterService.GetFullBatchesAsync();
                return Ok(batches);
            }
            catch
            {
                return StatusCode(500);
            }
        }
    }
}
