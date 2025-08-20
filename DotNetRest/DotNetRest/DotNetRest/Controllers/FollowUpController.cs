using System;
using System.Collections.Generic;
using System.Text.Json;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using DotNetRest.Models;
using DotNetRest.Service;

namespace DotNetRest.Controllers
{
    [Route("api/followups")]
    [ApiController]
    public class FollowupController : ControllerBase
    {
        private readonly IFollowupService _followupService;

        public FollowupController(IFollowupService followupService)
        {
            _followupService = followupService;
        }

        // ===============================
        // CREATE OPERATIONS
        // ===============================
        [HttpPost]
        public async Task<ActionResult<Followup>> CreateFollowup([FromBody] Followup followup)
        {
            try
            {
                var saved = await _followupService.SaveFollowupAsync(followup);
                return CreatedAtAction(nameof(GetFollowupById), new { followupId = saved.FollowupId }, saved);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = ex.Message, stackTrace = ex.StackTrace });
            }
        }

        [HttpPost("bulk")]
        public async Task<ActionResult<List<Followup>>> CreateMultipleFollowups([FromBody] List<Followup> followups)
        {
            try
            {
                var saved = await _followupService.SaveAllFollowupsAsync(followups);
                return Created("", saved);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = ex.Message, stackTrace = ex.StackTrace });
            }
        }

        // ===============================
        // READ OPERATIONS
        // ===============================
        [HttpGet]
        public async Task<ActionResult<List<Followup>>> GetAllFollowups()
        {
            try
            {
                var followups = await _followupService.GetAllFollowupsAsync();
                return Ok(followups);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = ex.Message });
            }
        }

        [HttpGet("{followupId}")]
        public async Task<ActionResult<Followup>> GetFollowupById(int followupId)
        {
            try
            {
                var followup = await _followupService.GetFollowupByIdAsync(followupId);
                if (followup == null) return NotFound(new { message = "Followup not found" });
                return Ok(followup);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = ex.Message });
            }
        }

        [HttpGet("search/enquiry/{enquiryId}")]
        public async Task<ActionResult<List<Followup>>> GetFollowupsByEnquiryId(int enquiryId)
        {
            try
            {
                var followups = await _followupService.GetFollowupsByEnquiryIdAsync(enquiryId);
                return Ok(followups);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = ex.Message });
            }
        }

        [HttpGet("search/student/{staffId}")]
        public async Task<ActionResult<List<Followup>>> GetFollowupsByStudentId(int staffId)
        {
            try
            {
                var followups = await _followupService.GetFollowupsByStudentIdAsync(staffId);
                return Ok(followups);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = ex.Message });
            }
        }

        [HttpGet("search/status/{status}")]
        public async Task<ActionResult<List<Followup>>> GetFollowupsByStatus(string status)
        {
            try
            {
                var followups = await _followupService.GetFollowupsByStatusAsync(status);
                return Ok(followups);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = ex.Message });
            }
        }

        [HttpGet("search/type/{type}")]
        public async Task<ActionResult<List<Followup>>> GetFollowupsByType(string type)
        {
            try
            {
                var followups = await _followupService.GetFollowupsByTypeAsync(type);
                return Ok(followups);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = ex.Message });
            }
        }

        [HttpGet("search/date-range")]
        public async Task<ActionResult<List<Followup>>> GetFollowupsByDateRange([FromQuery] DateTime startDate, [FromQuery] DateTime endDate)
        {
            try
            {
                var followups = await _followupService.GetFollowupsByDateRangeAsync(startDate, endDate);
                return Ok(followups);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = ex.Message });
            }
        }

        [HttpGet("search/priority/{priority}")]
        public async Task<ActionResult<List<Followup>>> GetFollowupsByPriority(string priority)
        {
            try
            {
                var followups = await _followupService.GetFollowupsByPriorityAsync(priority);
                return Ok(followups);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = ex.Message });
            }
        }

        // ===============================
        // UPDATE OPERATIONS
        // ===============================
        [HttpPut("{followupId}")]
        public async Task<ActionResult<Followup>> UpdateFollowup(int followupId, [FromBody] Followup followupDetails)
        {
            try
            {
                var updated = await _followupService.UpdateFollowupAsync(followupId, followupDetails);
                return Ok(updated);
            }
            catch (KeyNotFoundException)
            {
                return NotFound(new { message = "Followup not found" });
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = ex.Message });
            }
        }

        [HttpPatch("{followupId}/status")]
        public async Task<ActionResult<Followup>> UpdateFollowupStatus(int followupId, [FromQuery] string status)
        {
            try
            {
                var updated = await _followupService.UpdateFollowupStatusAsync(followupId, status);
                return Ok(updated);
            }
            catch (KeyNotFoundException)
            {
                return NotFound(new { message = "Followup not found" });
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = ex.Message });
            }
        }

        // Similar PATCH endpoints for type, priority, notes
        // ===============================
        // DELETE OPERATIONS
        // ===============================
        [HttpDelete("{followupId}")]
        public async Task<IActionResult> DeleteFollowup(int followupId)
        {
            try
            {
                await _followupService.DeleteFollowupAsync(followupId);
                return NoContent();
            }
            catch (KeyNotFoundException)
            {
                return NotFound(new { message = "Followup not found" });
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = ex.Message });
            }
        }

        [HttpDelete("enquiry/{enquiryId}")]
        public async Task<IActionResult> DeleteFollowupsByEnquiryId(int enquiryId)
        {
            try
            {
                await _followupService.DeleteFollowupsByEnquiryIdAsync(enquiryId);
                return NoContent();
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = ex.Message });
            }
        }

        [HttpDelete("status/{status}")]
        public async Task<IActionResult> DeleteFollowupsByStatus(string status)
        {
            try
            {
                await _followupService.DeleteFollowupsByStatusAsync(status);
                return NoContent();
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = ex.Message });
            }
        }

        [HttpDelete("all")]
        public async Task<IActionResult> DeleteAllFollowups()
        {
            try
            {
                await _followupService.DeleteAllFollowupsAsync();
                return NoContent();
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = ex.Message });
            }
        }

        // ===============================
        // BUSINESS LOGIC OPERATIONS
        // ===============================
        [HttpGet("search/global/{searchTerm}")]
        public async Task<ActionResult<List<Followup>>> SearchFollowups(string searchTerm)
        {
            try
            {
                var followups = await _followupService.SearchFollowupsAsync(searchTerm);
                return Ok(followups);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = ex.Message });
            }
        }
    }
}
