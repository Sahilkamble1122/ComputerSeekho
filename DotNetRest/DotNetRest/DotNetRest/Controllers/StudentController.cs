using DotNetRest.Models;
using DotNetRest.Service;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Numerics;
using System.Threading.Tasks;

namespace DotNetRest.Controllers
{
    [ApiController]
    [Route("api/students")]
    [Produces("application/json")]
    public class StudentMasterController : ControllerBase
    {
        private readonly IStudentMasterService _studentMasterService;

        public StudentMasterController(IStudentMasterService studentMasterService)
        {
            _studentMasterService = studentMasterService;
        }

        // ===========================================
        // CREATE OPERATIONS
        // ===========================================

        [HttpPost]
        public async Task<ActionResult<StudentMaster>> CreateStudent([FromBody] StudentMaster student)
        {
            try
            {
                
                var savedStudent = await _studentMasterService.SaveStudentAsync(student);
                return CreatedAtAction(nameof(GetStudentById), new { studentId = savedStudent.StudentId }, savedStudent);
            }
            catch
            {
                return BadRequest();
            }
        }

        [HttpPost("form")]
        [Consumes("multipart/form-data")]
        public async Task<ActionResult<StudentMaster>> CreateStudentFromForm(
            [FromForm] string studentName,
            [FromForm] string studentAddress,
            [FromForm] string studentGender,
            [FromForm] string studentDob,
            [FromForm] string studentQualification,
            [FromForm] string studentMobile,
            [FromForm] string studentEmail,
            [FromForm] int courseId,
            [FromForm] string studentPassword,
            [FromForm] string studentUsername,
            [FromForm] int batchId,
            [FromForm] IFormFile photo)
        {
            try
            {
                var student = new StudentMaster
                {
                    
                    StudentName = studentName,
                    StudentAddress = studentAddress,
                    StudentGender = studentGender,
                    StudentDob = ParseFlexibleDate(studentDob),
                    StudentQualification = studentQualification,
                    StudentMobile = ParseMobile(studentMobile),
                    StudentEmail = studentEmail,
                    CourseId = courseId,
                    StudentPassword = studentPassword,
                    StudentUsername = studentUsername,
                    BatchId = batchId
                };

                if (photo != null && photo.Length > 0)
                {
                    var uploadsDir = Path.Combine(Directory.GetCurrentDirectory(), "uploads");
                    Directory.CreateDirectory(uploadsDir);

                    var uniqueFileName = $"{DateTimeOffset.UtcNow.ToUnixTimeMilliseconds()}_{Path.GetFileName(photo.FileName)}";
                    var filePath = Path.Combine(uploadsDir, uniqueFileName);

                    using (var stream = new FileStream(filePath, FileMode.Create))
                    {
                        await photo.CopyToAsync(stream);
                    }

                    student.PhotoUrl = Path.Combine("uploads", uniqueFileName).Replace("\\", "/");
                }

                var savedStudent = await _studentMasterService.SaveStudentAsync(student);
                return CreatedAtAction(nameof(GetStudentById), new { studentId = savedStudent.StudentId }, savedStudent);
            }
            catch
            {
                return BadRequest();
            }
        }

        [HttpPost("bulk")]
        public async Task<ActionResult<List<StudentMaster>>> CreateMultipleStudents([FromBody] List<StudentMaster> students)
        {
            try
            {
                var savedStudents = await _studentMasterService.SaveAllStudentsAsync(students);
                return Created("", savedStudents);
            }
            catch
            {
                return BadRequest();
            }
        }

        // ===========================================
        // READ OPERATIONS
        // ===========================================

        [HttpGet]
        public async Task<ActionResult<List<StudentMaster>>> GetAllStudents()
        {
            try
            {
                var students = await _studentMasterService.GetAllStudentsAsync();
                return Ok(students);

            }
            catch (Exception ex)
            {
                // Log the exception here (using a logging framework or built-in logger)
                return StatusCode(500, new { message = "Internal server error", error = ex.Message });
            }

        }

        [HttpGet("{studentId}")]
        public async Task<ActionResult<StudentMaster>> GetStudentById(int studentId)
        {
            var student = await _studentMasterService.GetStudentByIdAsync(studentId);
            if (student == null)
                return NotFound();
            return Ok(student);
        }

        [HttpGet("search/name/{studentName}")]
        public async Task<ActionResult<List<StudentMaster>>> GetStudentsByName(string studentName)
        {
            var students = await _studentMasterService.GetStudentsByNameAsync(studentName);
            return Ok(students);
        }

        [HttpGet("search/email/{email}")]
        public async Task<ActionResult<List<StudentMaster>>> GetStudentsByEmail(string email)
        {
            try
            {
                var students = await _studentMasterService.GetStudentsByEmailAsync(email);
                return Ok(students);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = "Internal server error", error = ex.Message });
            }
        }

        [HttpGet("search/mobile/{mobile}")]
        public async Task<ActionResult<List<StudentMaster>>> GetStudentsByMobile(long mobile)
        {
            try
            {
                var students = await _studentMasterService.GetStudentsByMobileAsync(mobile);
                return Ok(students);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = "Internal server error", error = ex.Message });
            }
        }

        [HttpGet("search/gender/{gender}")]
        public async Task<ActionResult<List<StudentMaster>>> GetStudentsByGender(string gender)
        {
            try
            {
                var students = await _studentMasterService.GetStudentsByGenderAsync(gender);
                return Ok(students);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = "Internal server error", error = ex.Message });
            }
        }

        [HttpGet("search/batch/{batchId}")]
        public async Task<ActionResult<List<StudentMaster>>> GetStudentsByBatchId(int batchId)
        {
            try
            {
                var students = await _studentMasterService.GetStudentsByBatchIdAsync(batchId);
                return Ok(students);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = "Internal server error", error = ex.Message });
            }
        }

        [HttpGet("search/course/{courseId}")]
        public async Task<ActionResult<List<StudentMaster>>> GetStudentsByCourseId(int courseId)
        {
            try
            {
                var students = await _studentMasterService.GetStudentsByCourseIdAsync(courseId);
                return Ok(students);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = "Internal server error", error = ex.Message });
            }
        }

        [HttpGet("count/batch/{batchId}")]
        public async Task<ActionResult<long>> CountStudentsByBatchId(int batchId)
        {
            try
            {
                var count = await _studentMasterService.CountStudentsByBatchIdAsync(batchId);
                return Ok(count);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = "Internal server error", error = ex.Message });
            }
        }

        [HttpGet("count/course/{courseId}")]
        public async Task<ActionResult<long>> CountStudentsByCourseId(int courseId)
        {
            try
            {
                var count = await _studentMasterService.CountStudentsByCourseIdAsync(courseId);
                return Ok(count);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = "Internal server error", error = ex.Message });
            }
        }

        [HttpGet("count/placed")]
        public async Task<ActionResult<long>> CountPlacedStudents()
        {
            try
            {
                var count = await _studentMasterService.CountPlacedStudentsAsync();
                return Ok(count);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = "Internal server error", error = ex.Message });
            }
        }

        [HttpGet("count/unplaced")]
        public async Task<ActionResult<long>> CountUnplacedStudents()
        {
            try
            {
                var count = await _studentMasterService.CountUnplacedStudentsAsync();
                return Ok(count);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = "Internal server error", error = ex.Message });
            }
        }

        [HttpGet("search/global/{searchTerm}")]
        public async Task<ActionResult<List<StudentMaster>>> SearchStudents(string searchTerm)
        {
            try
            {
                var students = await _studentMasterService.SearchStudentsAsync(searchTerm);
                return Ok(students);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = "Internal server error", error = ex.Message });
            }
        }

        [HttpGet("search/date-range")]
        public async Task<ActionResult<List<StudentMaster>>> GetStudentsByDateRangeAndCourse(
            [FromQuery] DateTime startDate, [FromQuery] DateTime endDate, [FromQuery] int courseId)
        {
            try
            {
                // Note: This endpoint needs to be implemented in the service
                return StatusCode(501, new { message = "Not implemented yet" });
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = "Internal server error", error = ex.Message });
            }
        }

        [HttpGet("pagination")]
        public async Task<ActionResult<List<StudentMaster>>> GetStudentsWithPagination(
            [FromQuery] int page = 0, [FromQuery] int size = 10)
        {
            try
            {
                var students = await _studentMasterService.GetStudentsWithPaginationAsync(page, size);
                return Ok(students);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = "Internal server error", error = ex.Message });
            }
        }

        [HttpGet("sorted/name")]
        public async Task<ActionResult<List<StudentMaster>>> GetStudentsSortedByName()
        {
            try
            {
                var students = await _studentMasterService.GetStudentsSortedByNameAsync();
                return Ok(students);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = "Internal server error", error = ex.Message });
            }
        }

        [HttpGet("sorted/email")]
        public async Task<ActionResult<List<StudentMaster>>> GetStudentsSortedByEmail()
        {
            try
            {
                var students = await _studentMasterService.GetStudentsSortedByEmailAsync();
                return Ok(students);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = "Internal server error", error = ex.Message });
            }
        }

        [HttpGet("sorted/batch")]
        public async Task<ActionResult<List<StudentMaster>>> GetStudentsSortedByBatchId()
        {
            try
            {
                var students = await _studentMasterService.GetStudentsSortedByBatchIdAsync();
                return Ok(students);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = "Internal server error", error = ex.Message });
            }
        }

        [HttpPost("authenticate")]
        public async Task<ActionResult<StudentMaster>> AuthenticateStudent([FromQuery] string username, [FromQuery] string password)
        {
            try
            {
                var student = await _studentMasterService.AuthenticateStudentAsync(username, password);
                if (student == null)
                    return Unauthorized();
                return Ok(student);
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = "Internal server error", error = ex.Message });
            }
        }

        [HttpGet("test")]
        public ActionResult<string> TestEndpoint()
        {
            return Ok("Student API is working!");
        }

        // ===========================================
        // UPDATE OPERATIONS
        // ===========================================

        [HttpPut("{studentId}")]
        public async Task<ActionResult<StudentMaster>> UpdateStudent(int studentId, [FromBody] StudentMaster studentDetails)
        {
            try
            {
                var updatedStudent = await _studentMasterService.UpdateStudentAsync(studentId, studentDetails);
                return Ok(updatedStudent);
            }
            catch (KeyNotFoundException)
            {
                return NotFound();
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }

        [HttpPatch("{studentId}/pending-fees")]
        public async Task<ActionResult<StudentMaster>> UpdateStudentPendingFees(int studentId, [FromQuery] double pendingFees)
        {
            try
            {
                var updatedStudent = await _studentMasterService.UpdateStudentPendingFeesAsync(studentId, pendingFees);
                return Ok(updatedStudent);
            }
            catch (KeyNotFoundException)
            {
                return NotFound();
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }

        [HttpPatch("{studentId}/name")]
        public async Task<ActionResult<StudentMaster>> UpdateStudentName(int studentId, [FromQuery] string studentName)
        {
            try
            {
                var updatedStudent = await _studentMasterService.UpdateStudentNameAsync(studentId, studentName);
                return Ok(updatedStudent);
            }
            catch (KeyNotFoundException)
            {
                return NotFound();
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }

        [HttpPatch("{studentId}/email")]
        public async Task<ActionResult<StudentMaster>> UpdateStudentEmail(int studentId, [FromQuery] string email)
        {
            try
            {
                var updatedStudent = await _studentMasterService.UpdateStudentEmailAsync(studentId, email);
                return Ok(updatedStudent);
            }
            catch (KeyNotFoundException)
            {
                return NotFound();
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }

        [HttpPatch("{studentId}/mobile")]
        public async Task<ActionResult<StudentMaster>> UpdateStudentMobile(int studentId, [FromQuery] long mobile)
        {
            try
            {
                var updatedStudent = await _studentMasterService.UpdateStudentMobileAsync(studentId, mobile);
                return Ok(updatedStudent);
            }
            catch (KeyNotFoundException)
            {
                return NotFound();
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }

        [HttpPatch("{studentId}/address")]
        public async Task<ActionResult<StudentMaster>> UpdateStudentAddress(int studentId, [FromQuery] string address)
        {
            try
            {
                var updatedStudent = await _studentMasterService.UpdateStudentAddressAsync(studentId, address);
                return Ok(updatedStudent);
            }
            catch (KeyNotFoundException)
            {
                return NotFound();
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }

        [HttpPatch("{studentId}/qualification")]
        public async Task<ActionResult<StudentMaster>> UpdateStudentQualification(int studentId, [FromQuery] string qualification)
        {
            try
            {
                var updatedStudent = await _studentMasterService.UpdateStudentQualificationAsync(studentId, qualification);
                return Ok(updatedStudent);
            }
            catch (KeyNotFoundException)
            {
                return NotFound();
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }

        [HttpPatch("{studentId}/isplaced")]
        public async Task<ActionResult<StudentMaster>> UpdateStudentIsPlaced(int studentId, [FromQuery] bool isPlaced)
        {
            try
            {
                var updatedStudent = await _studentMasterService.UpdateStudentPlacementStatusAsync(studentId, isPlaced);
                return Ok(updatedStudent);
            }
            catch (KeyNotFoundException)
            {
                return NotFound();
            }
            catch
            {
                return StatusCode(StatusCodes.Status500InternalServerError);
            }
        }

        // ===========================================
        // DELETE OPERATIONS
        // ===========================================

        [HttpDelete("{studentId}")]
        public async Task<IActionResult> DeleteStudent(int studentId)
        {
            try
            {
                await _studentMasterService.DeleteStudentAsync(studentId);
                return NoContent();
            }
            catch (KeyNotFoundException)
            {
                return NotFound();
            }
        }

        [HttpDelete("batch/{batchId}")]
        public async Task<IActionResult> DeleteStudentsByBatchId(int batchId)
        {
            try
            {
                await _studentMasterService.DeleteStudentsByBatchIdAsync(batchId);
                return NoContent();
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = "Internal server error", error = ex.Message });
            }
        }

        [HttpDelete("course/{courseId}")]
        public async Task<IActionResult> DeleteStudentsByCourseId(int courseId)
        {
            try
            {
                await _studentMasterService.DeleteStudentsByCourseIdAsync(courseId);
                return NoContent();
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = "Internal server error", error = ex.Message });
            }
        }

        [HttpDelete("all")]
        public async Task<IActionResult> DeleteAllStudents()
        {
            try
            {
                await _studentMasterService.DeleteAllStudentsAsync();
                return NoContent();
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = "Internal server error", error = ex.Message });
            }
        }

        // ===========================================
        // UTIL METHODS
        // ===========================================

        private DateTime? ParseFlexibleDate(string input)
        {
            if (string.IsNullOrWhiteSpace(input)) return null;

            if (DateTime.TryParse(input, out var date))
                return date;

            string[] formats = { "dd/MM/yyyy", "dd-MM-yyyy" };
            if (DateTime.TryParseExact(input, formats, null, System.Globalization.DateTimeStyles.None, out date))
                return date;

            throw new ArgumentException($"Invalid date format: {input}");
        }

        private long? ParseMobile(string input)
        {
            if (string.IsNullOrWhiteSpace(input)) return null;
            var digits = new string(input.Where(char.IsDigit).ToArray());
            return string.IsNullOrEmpty(digits) ? null : long.Parse(digits);
        }
    }
}
