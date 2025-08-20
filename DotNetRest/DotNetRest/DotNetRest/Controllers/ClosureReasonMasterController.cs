using Microsoft.AspNetCore.Mvc;
using DotNetRest.Models;
using DotNetRest.Service;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace DotNetRest.Controllers
{
    [Route("api/closure-reasons")]
    [ApiController]
    public class ClosureReasonMasterController : ControllerBase
    {
        private readonly IClosureReasonMasterService _closureReasonMasterService;

        public ClosureReasonMasterController(IClosureReasonMasterService closureReasonMasterService)
        {
            _closureReasonMasterService = closureReasonMasterService;
        }

        // ==============================
        // CREATE OPERATIONS
        // ==============================
        [HttpPost]
        public async Task<ActionResult<ClosureReasonMaster>> createClosureReason([FromBody] ClosureReasonMaster closureReason)
        {
            try
            {
                var savedClosureReason = await _closureReasonMasterService.SaveClosureReasonAsync(closureReason);
                return CreatedAtAction(nameof(getClosureReasonById), new { closureReasonId = savedClosureReason.ClosureReasonId }, savedClosureReason);
            }
            catch
            {
                return BadRequest();
            }
        }

        [HttpPost("bulk")]
        public async Task<ActionResult<List<ClosureReasonMaster>>> createMultipleClosureReasons([FromBody] List<ClosureReasonMaster> closureReasons)
        {
            try
            {
                var savedClosureReasons = await _closureReasonMasterService.SaveAllClosureReasonsAsync(closureReasons);
                return Created("", savedClosureReasons);
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
        public async Task<ActionResult<List<ClosureReasonMaster>>> getAllClosureReasons()
        {
            try
            {
                var closureReasons = await _closureReasonMasterService.GetAllClosureReasonsAsync();
                return Ok(closureReasons);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("{closureReasonId}")]
        public async Task<ActionResult<ClosureReasonMaster>> getClosureReasonById(int closureReasonId)
        {
            try
            {
                var closureReason = await _closureReasonMasterService.GetClosureReasonByIdAsync(closureReasonId);
                if (closureReason == null) return NotFound();
                return Ok(closureReason);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/name/{reasonName}")]
        public async Task<ActionResult<List<ClosureReasonMaster>>> getClosureReasonsByName(string reasonName)
        {
            try
            {
                var closureReasons = await _closureReasonMasterService.GetClosureReasonsByNameAsync(reasonName);
                return Ok(closureReasons);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/description/{description}")]
        public async Task<ActionResult<List<ClosureReasonMaster>>> getClosureReasonsByDescription(string description)
        {
            try
            {
                var closureReasons = await _closureReasonMasterService.GetClosureReasonsByDescriptionAsync(description);
                return Ok(closureReasons);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/status/{status}")]
        public async Task<ActionResult<List<ClosureReasonMaster>>> getClosureReasonsByStatus(string status)
        {
            try
            {
                var closureReasons = await _closureReasonMasterService.GetClosureReasonsByStatusAsync(status);
                return Ok(closureReasons);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/name-contains/{reasonName}")]
        public async Task<ActionResult<List<ClosureReasonMaster>>> getClosureReasonsByNameContaining(string reasonName)
        {
            try
            {
                var closureReasons = await _closureReasonMasterService.GetClosureReasonsByNameContainingAsync(reasonName);
                return Ok(closureReasons);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("active")]
        public async Task<ActionResult<List<ClosureReasonMaster>>> getActiveClosureReasons()
        {
            try
            {
                var closureReasons = await _closureReasonMasterService.GetActiveClosureReasonsAsync();
                return Ok(closureReasons);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("count/active")]
        public async Task<ActionResult<long>> countActiveClosureReasons()
        {
            try
            {
                var count = await _closureReasonMasterService.CountActiveClosureReasonsAsync();
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
        [HttpPut("{closureReasonId}")]
        public async Task<ActionResult<ClosureReasonMaster>> updateClosureReason(int closureReasonId, [FromBody] ClosureReasonMaster closureReasonDetails)
        {
            try
            {
                var updatedClosureReason = await _closureReasonMasterService.UpdateClosureReasonAsync(closureReasonId, closureReasonDetails);
                return Ok(updatedClosureReason);
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

        [HttpPatch("{closureReasonId}/name")]
        public async Task<ActionResult<ClosureReasonMaster>> updateClosureReasonName(int closureReasonId, [FromQuery] string reasonName)
        {
            try
            {
                var updatedClosureReason = await _closureReasonMasterService.UpdateClosureReasonNameAsync(closureReasonId, reasonName);
                return Ok(updatedClosureReason);
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

        [HttpPatch("{closureReasonId}/description")]
        public async Task<ActionResult<ClosureReasonMaster>> updateClosureReasonDescription(int closureReasonId, [FromQuery] string description)
        {
            try
            {
                var updatedClosureReason = await _closureReasonMasterService.UpdateClosureReasonDescriptionAsync(closureReasonId, description);
                return Ok(updatedClosureReason);
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

        [HttpPatch("{closureReasonId}/status")]
        public async Task<ActionResult<ClosureReasonMaster>> updateClosureReasonStatus(int closureReasonId, [FromQuery] string status)
        {
            try
            {
                var updatedClosureReason = await _closureReasonMasterService.UpdateClosureReasonStatusAsync(closureReasonId, status);
                return Ok(updatedClosureReason);
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
        [HttpDelete("{closureReasonId}")]
        public async Task<IActionResult> deleteClosureReason(int closureReasonId)
        {
            try
            {
                await _closureReasonMasterService.DeleteClosureReasonAsync(closureReasonId);
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

        [HttpDelete("status/{status}")]
        public async Task<IActionResult> deleteClosureReasonsByStatus(string status)
        {
            try
            {
                await _closureReasonMasterService.DeleteClosureReasonsByStatusAsync(status);
                return NoContent();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("inactive")]
        public async Task<IActionResult> deleteInactiveClosureReasons()
        {
            try
            {
                await _closureReasonMasterService.DeleteInactiveClosureReasonsAsync();
                return NoContent();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("all")]
        public async Task<IActionResult> deleteAllClosureReasons()
        {
            try
            {
                await _closureReasonMasterService.DeleteAllClosureReasonsAsync();
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
        public async Task<ActionResult<List<ClosureReasonMaster>>> searchClosureReasons(string searchTerm)
        {
            try
            {
                var closureReasons = await _closureReasonMasterService.SearchClosureReasonsAsync(searchTerm);
                return Ok(closureReasons);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("pagination")]
        public async Task<ActionResult<List<ClosureReasonMaster>>> getClosureReasonsWithPagination([FromQuery] int page = 0, [FromQuery] int size = 10)
        {
            try
            {
                var closureReasons = await _closureReasonMasterService.GetClosureReasonsWithPaginationAsync(page, size);
                return Ok(closureReasons);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("sorted/name")]
        public async Task<ActionResult<List<ClosureReasonMaster>>> getClosureReasonsSortedByName()
        {
            try
            {
                var closureReasons = await _closureReasonMasterService.GetClosureReasonsSortedByNameAsync();
                return Ok(closureReasons);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("sorted/status")]
        public async Task<ActionResult<List<ClosureReasonMaster>>> getClosureReasonsSortedByStatus()
        {
            try
            {
                var closureReasons = await _closureReasonMasterService.GetClosureReasonsSortedByStatusAsync();
                return Ok(closureReasons);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("frequently-used")]
        public async Task<ActionResult<List<ClosureReasonMaster>>> getFrequentlyUsedClosureReasons()
        {
            try
            {
                var closureReasons = await _closureReasonMasterService.GetFrequentlyUsedClosureReasonsAsync();
                return Ok(closureReasons);
            }
            catch
            {
                return StatusCode(500);
            }
        }
    }
}
