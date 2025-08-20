using Microsoft.AspNetCore.Mvc;
using DotNetRest.Models;
using DotNetRest.Service;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace DotNetRest.Controllers
{
    [Route("api/courses")]
    [ApiController]
    public class CourseMasterController : ControllerBase
    {
        private readonly ICourseMasterService _courseMasterService;

        public CourseMasterController(ICourseMasterService courseMasterService)
        {
            _courseMasterService = courseMasterService;
        }

        // ==============================
        // CREATE OPERATIONS
        // ==============================
        [HttpPost]
        public async Task<ActionResult<CourseMaster>> createCourse([FromBody] CourseMaster course)
        {
            try
            {
                var savedCourse = await _courseMasterService.SaveCourseAsync(course);
                return CreatedAtAction(nameof(getCourseById), new { courseId = savedCourse.CourseId }, savedCourse);
            }
            catch
            {
                return BadRequest();
            }
        }

        [HttpPost("bulk")]
        public async Task<ActionResult<List<CourseMaster>>> createMultipleCourses([FromBody] List<CourseMaster> courses)
        {
            try
            {
                var savedCourses = await _courseMasterService.SaveAllCoursesAsync(courses);
                return Created("", savedCourses);
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
        public async Task<ActionResult<List<CourseMaster>>> getAllCourses()
        {
            try
            {
                var courses = await _courseMasterService.GetAllCoursesAsync();
                return Ok(courses);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("{courseId}")]
        public async Task<ActionResult<CourseMaster>> getCourseById(int courseId)
        {
            try
            {
                var course = await _courseMasterService.GetCourseByIdAsync(courseId);
                if (course == null) return NotFound();
                return Ok(course);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/name/{courseName}")]
        public async Task<ActionResult<List<CourseMaster>>> getCoursesByName(string courseName)
        {
            try
            {
                var courses = await _courseMasterService.GetCoursesByNameAsync(courseName);
                return Ok(courses);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/description/{description}")]
        public async Task<ActionResult<List<CourseMaster>>> getCoursesByDescription(string description)
        {
            try
            {
                var courses = await _courseMasterService.GetCoursesByDescriptionAsync(description);
                return Ok(courses);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/age-group/{ageGroup}")]
        public async Task<ActionResult<List<CourseMaster>>> getCoursesByAgeGroup(string ageGroup)
        {
            try
            {
                var courses = await _courseMasterService.GetCoursesByAgeGroupAsync(ageGroup);
                return Ok(courses);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("active")]
        public async Task<ActionResult<List<CourseMaster>>> getActiveCourses()
        {
            try
            {
                var courses = await _courseMasterService.GetActiveCoursesAsync();
                return Ok(courses);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/name-contains/{courseName}")]
        public async Task<ActionResult<List<CourseMaster>>> getCoursesByNameContaining(string courseName)
        {
            try
            {
                var courses = await _courseMasterService.GetCoursesByNameContainingAsync(courseName);
                return Ok(courses);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/age-group-contains/{ageGroup}")]
        public async Task<ActionResult<List<CourseMaster>>> getCoursesByAgeGroupContaining(string ageGroup)
        {
            try
            {
                var courses = await _courseMasterService.GetCoursesByAgeGroupContainingAsync(ageGroup);
                return Ok(courses);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/duration-range")]
        public async Task<ActionResult<List<CourseMaster>>> getCoursesByDurationRange([FromQuery] int minDuration, [FromQuery] int maxDuration)
        {
            try
            {
                var courses = await _courseMasterService.GetCoursesByDurationRangeAsync(minDuration, maxDuration);
                return Ok(courses);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("count/active")]
        public async Task<ActionResult<long>> countActiveCourses()
        {
            try
            {
                var count = await _courseMasterService.CountActiveCoursesAsync();
                return Ok(count);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("count/age-group/{ageGroup}")]
        public async Task<ActionResult<long>> countCoursesByAgeGroup(string ageGroup)
        {
            try
            {
                var count = await _courseMasterService.CountCoursesByAgeGroupAsync(ageGroup);
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
        [HttpPut("{courseId}")]
        public async Task<ActionResult<CourseMaster>> updateCourse(int courseId, [FromBody] CourseMaster courseDetails)
        {
            try
            {
                var updatedCourse = await _courseMasterService.UpdateCourseAsync(courseId, courseDetails);
                return Ok(updatedCourse);
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

        [HttpPatch("{courseId}/status")]
        public async Task<ActionResult<CourseMaster>> updateCourseStatus(int courseId, [FromQuery] bool isActive)
        {
            try
            {
                var updatedCourse = await _courseMasterService.UpdateCourseStatusAsync(courseId, isActive);
                return Ok(updatedCourse);
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

        [HttpPatch("{courseId}/name")]
        public async Task<ActionResult<CourseMaster>> updateCourseName(int courseId, [FromQuery] string courseName)
        {
            try
            {
                var updatedCourse = await _courseMasterService.UpdateCourseNameAsync(courseId, courseName);
                return Ok(updatedCourse);
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

        [HttpPatch("{courseId}/description")]
        public async Task<ActionResult<CourseMaster>> updateCourseDescription(int courseId, [FromQuery] string description)
        {
            try
            {
                var updatedCourse = await _courseMasterService.UpdateCourseDescriptionAsync(courseId, description);
                return Ok(updatedCourse);
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

        [HttpPatch("{courseId}/duration")]
        public async Task<ActionResult<CourseMaster>> updateCourseDuration(int courseId, [FromQuery] int duration)
        {
            try
            {
                var updatedCourse = await _courseMasterService.UpdateCourseDurationAsync(courseId, duration);
                return Ok(updatedCourse);
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

        [HttpPatch("{courseId}/syllabus")]
        public async Task<ActionResult<CourseMaster>> updateCourseSyllabus(int courseId, [FromQuery] string syllabus)
        {
            try
            {
                var updatedCourse = await _courseMasterService.UpdateCourseSyllabusAsync(courseId, syllabus);
                return Ok(updatedCourse);
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
        [HttpDelete("{courseId}")]
        public async Task<IActionResult> deleteCourse(int courseId)
        {
            try
            {
                await _courseMasterService.DeleteCourseAsync(courseId);
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

        [HttpDelete("age-group/{ageGroup}")]
        public async Task<IActionResult> deleteCoursesByAgeGroup(string ageGroup)
        {
            try
            {
                await _courseMasterService.DeleteCoursesByAgeGroupAsync(ageGroup);
                return NoContent();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("inactive")]
        public async Task<IActionResult> deleteInactiveCourses()
        {
            try
            {
                await _courseMasterService.DeleteInactiveCoursesAsync();
                return NoContent();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("all")]
        public async Task<IActionResult> deleteAllCourses()
        {
            try
            {
                await _courseMasterService.DeleteAllCoursesAsync();
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
        public async Task<ActionResult<List<CourseMaster>>> searchCourses(string searchTerm)
        {
            try
            {
                var courses = await _courseMasterService.SearchCoursesAsync(searchTerm);
                return Ok(courses);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/duration-age-group")]
        public async Task<ActionResult<List<CourseMaster>>> getCoursesByDurationAndAgeGroup([FromQuery] int duration, [FromQuery] string ageGroup)
        {
            try
            {
                var courses = await _courseMasterService.GetCoursesByDurationAndAgeGroupAsync(duration, ageGroup);
                return Ok(courses);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("pagination")]
        public async Task<ActionResult<List<CourseMaster>>> getCoursesWithPagination([FromQuery] int page = 0, [FromQuery] int size = 10)
        {
            try
            {
                var courses = await _courseMasterService.GetCoursesWithPaginationAsync(page, size);
                return Ok(courses);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("sorted/name")]
        public async Task<ActionResult<List<CourseMaster>>> getCoursesSortedByName()
        {
            try
            {
                var courses = await _courseMasterService.GetCoursesSortedByNameAsync();
                return Ok(courses);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("sorted/duration")]
        public async Task<ActionResult<List<CourseMaster>>> getCoursesSortedByDuration()
        {
            try
            {
                var courses = await _courseMasterService.GetCoursesSortedByDurationAsync();
                return Ok(courses);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("sorted/age-group")]
        public async Task<ActionResult<List<CourseMaster>>> getCoursesSortedByAgeGroup()
        {
            try
            {
                var courses = await _courseMasterService.GetCoursesSortedByAgeGroupAsync();
                return Ok(courses);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("with-video")]
        public async Task<ActionResult<List<CourseMaster>>> getCoursesWithVideoContent()
        {
            try
            {
                var courses = await _courseMasterService.GetCoursesWithVideoContentAsync();
                return Ok(courses);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("search/syllabus/{keyword}")]
        public async Task<ActionResult<List<CourseMaster>>> getCoursesBySyllabusKeyword(string keyword)
        {
            try
            {
                var courses = await _courseMasterService.GetCoursesBySyllabusKeywordAsync(keyword);
                return Ok(courses);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        // ==============================
        // DUPLICATE DETECTION AND REMOVAL OPERATIONS
        // ==============================
        [HttpGet("duplicates/name")]
        public async Task<ActionResult<List<CourseMaster>>> findDuplicateCoursesByName()
        {
            try
            {
                var duplicates = await _courseMasterService.FindDuplicateCoursesByNameAsync();
                return Ok(duplicates);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("duplicates/name-description")]
        public async Task<ActionResult<List<CourseMaster>>> findDuplicateCoursesByNameAndDescription()
        {
            try
            {
                var duplicates = await _courseMasterService.FindDuplicateCoursesByNameAndDescriptionAsync();
                return Ok(duplicates);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("duplicates/name-description-duration")]
        public async Task<ActionResult<List<CourseMaster>>> findDuplicateCoursesByNameDescriptionAndDuration()
        {
            try
            {
                var duplicates = await _courseMasterService.FindDuplicateCoursesByNameDescriptionAndDurationAsync();
                return Ok(duplicates);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("duplicates/exact")]
        public async Task<ActionResult<List<CourseMaster>>> findExactDuplicateCourses()
        {
            try
            {
                var duplicates = await _courseMasterService.FindExactDuplicateCoursesAsync();
                return Ok(duplicates);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("duplicates/count/name")]
        public async Task<ActionResult<long>> countDuplicateCoursesByName()
        {
            try
            {
                var count = await _courseMasterService.CountDuplicateCoursesByNameAsync();
                return Ok(count);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("duplicates/count/name-description")]
        public async Task<ActionResult<long>> countDuplicateCoursesByNameAndDescription()
        {
            try
            {
                var count = await _courseMasterService.CountDuplicateCoursesByNameAndDescriptionAsync();
                return Ok(count);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("duplicates/name")]
        public async Task<IActionResult> removeDuplicateCoursesByName()
        {
            try
            {
                await _courseMasterService.RemoveDuplicateCoursesByNameAsync();
                return NoContent();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("duplicates/name-description")]
        public async Task<IActionResult> removeDuplicateCoursesByNameAndDescription()
        {
            try
            {
                await _courseMasterService.RemoveDuplicateCoursesByNameAndDescriptionAsync();
                return NoContent();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("duplicates/name-description-duration")]
        public async Task<IActionResult> removeDuplicateCoursesByNameDescriptionAndDuration()
        {
            try
            {
                await _courseMasterService.RemoveDuplicateCoursesByNameDescriptionAndDurationAsync();
                return NoContent();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("duplicates/all")]
        public async Task<IActionResult> removeAllDuplicates()
        {
            try
            {
                await _courseMasterService.RemoveAllDuplicatesAsync();
                return NoContent();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("duplicates/find-and-remove/name")]
        public async Task<ActionResult<List<CourseMaster>>> findAndRemoveDuplicatesByName()
        {
            try
            {
                var removedDuplicates = await _courseMasterService.FindAndRemoveDuplicatesByNameAsync();
                return Ok(removedDuplicates);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("duplicates/find-and-remove/name-description")]
        public async Task<ActionResult<List<CourseMaster>>> findAndRemoveDuplicatesByNameAndDescription()
        {
            try
            {
                var removedDuplicates = await _courseMasterService.FindAndRemoveDuplicatesByNameAndDescriptionAsync();
                return Ok(removedDuplicates);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("duplicates/find-and-remove/name-description-duration")]
        public async Task<ActionResult<List<CourseMaster>>> findAndRemoveDuplicatesByNameDescriptionAndDuration()
        {
            try
            {
                var removedDuplicates = await _courseMasterService.FindAndRemoveDuplicatesByNameDescriptionAndDurationAsync();
                return Ok(removedDuplicates);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("duplicates/find-and-remove/all")]
        public async Task<ActionResult<List<CourseMaster>>> findAndRemoveAllDuplicates()
        {
            try
            {
                var removedDuplicates = await _courseMasterService.FindAndRemoveAllDuplicatesAsync();
                return Ok(removedDuplicates);
            }
            catch
            {
                return StatusCode(500);
            }
        }
    }
}

