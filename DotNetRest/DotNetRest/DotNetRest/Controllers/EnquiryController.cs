using Microsoft.AspNetCore.Mvc;
using DotNetRest.Models;
using DotNetRest.Service;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace DotNetRest.Controllers
{
    [Route("api/enquiries")]
    [ApiController]
    public class EnquiryController : ControllerBase
    {
        private readonly IEnquiryService _enquiryService;

        public EnquiryController(IEnquiryService enquiryService)
        {
            _enquiryService = enquiryService;
        }

        // ==============================
        // CREATE OPERATIONS
        // ==============================
        [HttpPost]
        public async Task<ActionResult<object>> createEnquiry([FromBody] Enquiry enquiry)
        {
            try
            {
                // Removed verbose debug logging for production

                var savedEnquiry = await _enquiryService.SaveEnquiryAsync(enquiry);
                return CreatedAtAction(nameof(getEnquiryById), new { enquiryId = savedEnquiry.EnquiryId }, savedEnquiry);
            }
            catch (ArgumentException e)
            {
                // Validation error
                return BadRequest(new { error = e.Message });
            }
            catch (Exception e)
            {
                // Generic error
                return BadRequest(new { error = $"Failed to create enquiry: {e.Message}" });
            }
        }

        [HttpPost("bulk")]
        public async Task<ActionResult<List<Enquiry>>> createMultipleEnquiries([FromBody] List<Enquiry> enquiries)
        {
            try
            {
                var savedEnquiries = await _enquiryService.SaveAllEnquiriesAsync(enquiries);
                return Created("", savedEnquiries);
            }
            catch
            {
                return BadRequest();
            }
        }

        // Removed test endpoint used for debugging

        // ==============================
        // READ OPERATIONS
        // ==============================
        [HttpGet]
        public async Task<ActionResult<List<Enquiry>>> getAllEnquiries()
        {
            try
            {
                var enquiries = await _enquiryService.GetAllEnquiriesAsync();
                return Ok(enquiries);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("{enquiryId}")]
        public async Task<ActionResult<Enquiry>> getEnquiryById(int enquiryId)
        {
            try
            {
                var enquiry = await _enquiryService.GetEnquiryByIdAsync(enquiryId);
                if (enquiry == null) return NotFound();
                return Ok(enquiry);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/name/{studentName}")]
        public async Task<ActionResult<List<Enquiry>>> getEnquiriesByStudentName(string studentName)
        {
            try
            {
                var enquiries = await _enquiryService.GetEnquiriesByStudentNameAsync(studentName);
                return Ok(enquiries);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/mobile/{mobile}")]
        public async Task<ActionResult<List<Enquiry>>> getEnquiriesByMobile(long mobile)
        {
            try
            {
                var enquiries = await _enquiryService.GetEnquiriesByMobileAsync(mobile);
                return Ok(enquiries);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/email/{email}")]
        public async Task<ActionResult<List<Enquiry>>> getEnquiriesByEmail(string email)
        {
            try
            {
                var enquiries = await _enquiryService.GetEnquiriesByEmailAsync(email);
                return Ok(enquiries);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/status/{status}")]
        public async Task<ActionResult<List<Enquiry>>> getEnquiriesByStatus(string status)
        {
            try
            {
                var enquiries = await _enquiryService.GetEnquiriesByStatusAsync(status);
                return Ok(enquiries);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/name-contains/{studentName}")]
        public async Task<ActionResult<List<Enquiry>>> getEnquiriesByStudentNameContaining(string studentName)
        {
            try
            {
                var enquiries = await _enquiryService.GetEnquiriesByStudentNameContainingAsync(studentName);
                return Ok(enquiries);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/email-contains/{email}")]
        public async Task<ActionResult<List<Enquiry>>> getEnquiriesByEmailContaining(string email)
        {
            try
            {
                var enquiries = await _enquiryService.GetEnquiriesByEmailContainingAsync(email);
                return Ok(enquiries);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/date-range")]
        public async Task<ActionResult<List<Enquiry>>> getEnquiriesByDateRange([FromQuery] string startDate, [FromQuery] string endDate)
        {
            try
            {
                var enquiries = await _enquiryService.GetEnquiriesByDateRangeAsync(startDate, endDate);
                return Ok(enquiries);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("count/status/{status}")]
        public async Task<ActionResult<long>> countEnquiriesByStatus(string status)
        {
            try
            {
                var count = await _enquiryService.CountEnquiriesByStatusAsync(status);
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
        [HttpPut("{enquiryId}")]
        public async Task<ActionResult<Enquiry>> updateEnquiry(int enquiryId, [FromBody] Enquiry enquiryDetails)
        {
            try
            {
                var updatedEnquiry = await _enquiryService.UpdateEnquiryAsync(enquiryId, enquiryDetails);
                return Ok(updatedEnquiry);
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

        [HttpPatch("{enquiryId}/status")]
        public async Task<ActionResult<Enquiry>> updateEnquiryStatus(int enquiryId, [FromQuery] string status)
        {
            try
            {
                var updatedEnquiry = await _enquiryService.UpdateEnquiryStatusAsync(enquiryId, status);
                return Ok(updatedEnquiry);
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

        [HttpPatch("{enquiryId}/name")]
        public async Task<ActionResult<Enquiry>> updateEnquiryStudentName(int enquiryId, [FromQuery] string studentName)
        {
            try
            {
                var updatedEnquiry = await _enquiryService.UpdateEnquiryStudentNameAsync(enquiryId, studentName);
                return Ok(updatedEnquiry);
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

        [HttpPatch("{enquiryId}/mobile")]
        public async Task<ActionResult<Enquiry>> updateEnquiryMobile(int enquiryId, [FromQuery] long mobile)
        {
            try
            {
                var updatedEnquiry = await _enquiryService.UpdateEnquiryMobileAsync(enquiryId, mobile);
                return Ok(updatedEnquiry);
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

        [HttpPatch("{enquiryId}/email")]
        public async Task<ActionResult<Enquiry>> updateEnquiryEmail(int enquiryId, [FromQuery] string email)
        {
            try
            {
                var updatedEnquiry = await _enquiryService.UpdateEnquiryEmailAsync(enquiryId, email);
                return Ok(updatedEnquiry);
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

        [HttpPatch("{enquiryId}/counter")]
        public async Task<ActionResult<Enquiry>> updateEnquiryCounter(int enquiryId, [FromQuery] int enquiryCounter)
        {
            try
            {
                var updatedEnquiry = await _enquiryService.UpdateEnquiryCounterAsync(enquiryId, enquiryCounter);
                return Ok(updatedEnquiry);
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

        [HttpPatch("{enquiryId}/closure")]
        public async Task<ActionResult<Enquiry>> updateEnquiryClosure(int enquiryId, [FromBody] EnquiryClosurePatchDTO closurePatch)
        {
            try
            {
                var updated = await _enquiryService.UpdateEnquiryClosureAsync(enquiryId, closurePatch.ClosureReasonId, closurePatch.ClosureReason);
                return Ok(updated);
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

        [HttpPatch("{enquiryId}/state")]
        public async Task<ActionResult<Enquiry>> updateEnquiryState(int enquiryId, [FromQuery] bool enquiryState)
        {
            try
            {
                var updated = await _enquiryService.UpdateEnquiryStateAsync(enquiryId, enquiryState);
                return Ok(updated);
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
        [HttpDelete("{enquiryId}")]
        public async Task<IActionResult> deleteEnquiry(int enquiryId)
        {
            try
            {
                await _enquiryService.DeleteEnquiryAsync(enquiryId);
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
        public async Task<IActionResult> deleteEnquiriesByStatus(string status)
        {
            try
            {
                await _enquiryService.DeleteEnquiriesByStatusAsync(status);
                return NoContent();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("all")]
        public async Task<IActionResult> deleteAllEnquiries()
        {
            try
            {
                await _enquiryService.DeleteAllEnquiriesAsync();
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
        public async Task<ActionResult<List<Enquiry>>> searchEnquiries(string searchTerm)
        {
            try
            {
                var enquiries = await _enquiryService.SearchEnquiriesAsync(searchTerm);
                return Ok(enquiries);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/date-range-course")]
        public async Task<ActionResult<List<Enquiry>>> getEnquiriesByDateRangeAndCourse([FromQuery] string startDate, [FromQuery] string endDate, [FromQuery] int courseId)
        {
            try
            {
                var enquiries = await _enquiryService.GetEnquiriesByDateRangeAndCourseAsync(startDate, endDate, courseId);
                return Ok(enquiries);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("pagination")]
        public async Task<ActionResult<List<Enquiry>>> getEnquiriesWithPagination([FromQuery] int page = 0, [FromQuery] int size = 10)
        {
            try
            {
                var enquiries = await _enquiryService.GetEnquiriesWithPaginationAsync(page, size);
                return Ok(enquiries);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("sorted/date")]
        public async Task<ActionResult<List<Enquiry>>> getEnquiriesSortedByDate()
        {
            try
            {
                var enquiries = await _enquiryService.GetEnquiriesSortedByDateAsync();
                return Ok(enquiries);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("sorted/name")]
        public async Task<ActionResult<List<Enquiry>>> getEnquiriesSortedByStudentName()
        {
            try
            {
                var enquiries = await _enquiryService.GetEnquiriesSortedByStudentNameAsync();
                return Ok(enquiries);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("sorted/status")]
        public async Task<ActionResult<List<Enquiry>>> getEnquiriesSortedByStatus()
        {
            try
            {
                var enquiries = await _enquiryService.GetEnquiriesSortedByStatusAsync();
                return Ok(enquiries);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("pending")]
        public async Task<ActionResult<List<Enquiry>>> getPendingEnquiries()
        {
            try
            {
                var enquiries = await _enquiryService.GetPendingEnquiriesAsync();
                return Ok(enquiries);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("completed")]
        public async Task<ActionResult<List<Enquiry>>> getCompletedEnquiries()
        {
            try
            {
                var enquiries = await _enquiryService.GetCompletedEnquiriesAsync();
                return Ok(enquiries);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("follow-up-required")]
        public async Task<ActionResult<List<Enquiry>>> getFollowUpRequiredEnquiries()
        {
            try
            {
                var enquiries = await _enquiryService.GetFollowUpRequiredEnquiriesAsync();
                return Ok(enquiries);
            }
            catch
            {
                return StatusCode(500);
            }
        }
    }
}
