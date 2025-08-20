
using DotNetRest.Data;
using DotNetRest.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DotNetRest.Service
{
    public class CourseMasterService : ICourseMasterService
    {
        private readonly ApplicationDbContext _context;

        public CourseMasterService(ApplicationDbContext context)
        {
            _context = context;
        }

        // ==============================
        // CREATE OPERATIONS
        // ==============================
        public async Task<CourseMaster> SaveCourseAsync(CourseMaster course)
        {
            if (course.CreatedDate == null)
            {
                course.CreatedDate = DateTime.Now;
            }
            if (course.UpdatedDate == null)
            {
                course.UpdatedDate = DateTime.Now;
            }
            _context.CourseMasters.Add(course);
            await _context.SaveChangesAsync();
            return course;
        }

        public async Task<List<CourseMaster>> SaveAllCoursesAsync(List<CourseMaster> courses)
        {
            foreach (var course in courses)
            {
                if (course.CreatedDate == null)
                {
                    course.CreatedDate = DateTime.Now;
                }
                if (course.UpdatedDate == null)
                {
                    course.UpdatedDate = DateTime.Now;
                }
            }
            _context.CourseMasters.AddRange(courses);
            await _context.SaveChangesAsync();
            return courses;
        }

        // ==============================
        // READ OPERATIONS
        // ==============================
        public async Task<List<CourseMaster>> GetAllCoursesAsync()
        {
            return await _context.CourseMasters.ToListAsync();
        }

        public async Task<CourseMaster?> GetCourseByIdAsync(int courseId)
        {
            return await _context.CourseMasters.FindAsync(courseId);
        }

        public async Task<List<CourseMaster>> GetCoursesByNameAsync(string courseName)
        {
            return await _context.CourseMasters
                .Where(c => c.CourseName == courseName)
                .ToListAsync();
        }

        public async Task<List<CourseMaster>> GetCoursesByDescriptionAsync(string description)
        {
            return await _context.CourseMasters
                .Where(c => c.CourseDescription == description)
                .ToListAsync();
        }

        public async Task<List<CourseMaster>> GetCoursesByAgeGroupAsync(string ageGroup)
        {
            return await _context.CourseMasters
                .Where(c => c.AgeGrpType == ageGroup)
                .ToListAsync();
        }

        public async Task<List<CourseMaster>> GetActiveCoursesAsync()
        {
            return await _context.CourseMasters
                .Where(c => c.CourseIsActive == true)
                .ToListAsync();
        }

        public async Task<List<CourseMaster>> GetCoursesByNameContainingAsync(string courseName)
        {
            return await _context.CourseMasters
                .Where(c => c.CourseName != null && c.CourseName.Contains(courseName))
                .ToListAsync();
        }

        public async Task<List<CourseMaster>> GetCoursesByAgeGroupContainingAsync(string ageGroup)
        {
            return await _context.CourseMasters
                .Where(c => c.AgeGrpType != null && c.AgeGrpType.Contains(ageGroup))
                .ToListAsync();
        }

        public async Task<List<CourseMaster>> GetCoursesByDurationRangeAsync(int minDuration, int maxDuration)
        {
            return await _context.CourseMasters
                .Where(c => c.CourseDuration >= minDuration && c.CourseDuration <= maxDuration)
                .ToListAsync();
        }

        public async Task<long> CountActiveCoursesAsync()
        {
            return await _context.CourseMasters
                .LongCountAsync(c => c.CourseIsActive == true);
        }

        public async Task<long> CountCoursesByAgeGroupAsync(string ageGroup)
        {
            return await _context.CourseMasters
                .LongCountAsync(c => c.AgeGrpType == ageGroup);
        }

        // ==============================
        // UPDATE OPERATIONS
        // ==============================
        public async Task<CourseMaster> UpdateCourseAsync(int courseId, CourseMaster courseDetails)
        {
            var existingCourse = await _context.CourseMasters.FindAsync(courseId);
            if (existingCourse == null)
                throw new Exception($"Course not found with id: {courseId}");

            existingCourse.CourseName = courseDetails.CourseName;
            existingCourse.CourseDescription = courseDetails.CourseDescription;
            existingCourse.CourseDuration = courseDetails.CourseDuration;
            existingCourse.CourseSyllabus = courseDetails.CourseSyllabus;
            existingCourse.AgeGrpType = courseDetails.AgeGrpType;
            existingCourse.CourseIsActive = courseDetails.CourseIsActive;
            existingCourse.CoverPhoto = courseDetails.CoverPhoto;
            existingCourse.VideoId = courseDetails.VideoId;
            existingCourse.UpdatedDate = DateTime.Now;

            await _context.SaveChangesAsync();
            return existingCourse;
        }

        public async Task<CourseMaster> UpdateCourseStatusAsync(int courseId, bool isActive)
        {
            var existingCourse = await _context.CourseMasters.FindAsync(courseId);
            if (existingCourse == null)
                throw new Exception($"Course not found with id: {courseId}");

            existingCourse.CourseIsActive = isActive;
            existingCourse.UpdatedDate = DateTime.Now;
            await _context.SaveChangesAsync();
            return existingCourse;
        }

        public async Task<CourseMaster> UpdateCourseNameAsync(int courseId, string courseName)
        {
            var existingCourse = await _context.CourseMasters.FindAsync(courseId);
            if (existingCourse == null)
                throw new Exception($"Course not found with id: {courseId}");

            existingCourse.CourseName = courseName;
            existingCourse.UpdatedDate = DateTime.Now;
            await _context.SaveChangesAsync();
            return existingCourse;
        }

        public async Task<CourseMaster> UpdateCourseDescriptionAsync(int courseId, string description)
        {
            var existingCourse = await _context.CourseMasters.FindAsync(courseId);
            if (existingCourse == null)
                throw new Exception($"Course not found with id: {courseId}");

            existingCourse.CourseDescription = description;
            existingCourse.UpdatedDate = DateTime.Now;
            await _context.SaveChangesAsync();
            return existingCourse;
        }

        public async Task<CourseMaster> UpdateCourseDurationAsync(int courseId, int duration)
        {
            var existingCourse = await _context.CourseMasters.FindAsync(courseId);
            if (existingCourse == null)
                throw new Exception($"Course not found with id: {courseId}");

            existingCourse.CourseDuration = duration;
            existingCourse.UpdatedDate = DateTime.Now;
            await _context.SaveChangesAsync();
            return existingCourse;
        }

        public async Task<CourseMaster> UpdateCourseSyllabusAsync(int courseId, string syllabus)
        {
            var existingCourse = await _context.CourseMasters.FindAsync(courseId);
            if (existingCourse == null)
                throw new Exception($"Course not found with id: {courseId}");

            existingCourse.CourseSyllabus = syllabus;
            existingCourse.UpdatedDate = DateTime.Now;
            await _context.SaveChangesAsync();
            return existingCourse;
        }

        // ==============================
        // DELETE OPERATIONS
        // ==============================
        public async Task DeleteCourseAsync(int courseId)
        {
            var course = await _context.CourseMasters.FindAsync(courseId);
            if (course == null)
                throw new Exception($"Course not found with id: {courseId}");

            _context.CourseMasters.Remove(course);
            await _context.SaveChangesAsync();
        }

        public async Task DeleteCoursesByAgeGroupAsync(string ageGroup)
        {
            var courses = await _context.CourseMasters
                .Where(c => c.AgeGrpType == ageGroup)
                .ToListAsync();

            _context.CourseMasters.RemoveRange(courses);
            await _context.SaveChangesAsync();
        }

        public async Task DeleteInactiveCoursesAsync()
        {
            var courses = await _context.CourseMasters
                .Where(c => c.CourseIsActive == false)
                .ToListAsync();

            _context.CourseMasters.RemoveRange(courses);
            await _context.SaveChangesAsync();
        }

        public async Task DeleteAllCoursesAsync()
        {
            var allCourses = await _context.CourseMasters.ToListAsync();
            _context.CourseMasters.RemoveRange(allCourses);
            await _context.SaveChangesAsync();
        }

        // ==============================
        // BUSINESS LOGIC OPERATIONS
        // ==============================
        public async Task<List<CourseMaster>> SearchCoursesAsync(string searchTerm)
        {
            return await _context.CourseMasters
                .Where(c => (c.CourseName != null && c.CourseName.Contains(searchTerm)) ||
                           (c.CourseDescription != null && c.CourseDescription.Contains(searchTerm)) ||
                           (c.CourseSyllabus != null && c.CourseSyllabus.Contains(searchTerm)) ||
                           (c.AgeGrpType != null && c.AgeGrpType.Contains(searchTerm)))
                .ToListAsync();
        }

        public async Task<List<CourseMaster>> GetCoursesByDurationAndAgeGroupAsync(int duration, string ageGroup)
        {
            return await _context.CourseMasters
                .Where(c => c.CourseDuration == duration && c.AgeGrpType == ageGroup)
                .ToListAsync();
        }

        public async Task<List<CourseMaster>> GetCoursesWithPaginationAsync(int page, int size)
        {
            return await _context.CourseMasters
                .Skip(page * size)
                .Take(size)
                .ToListAsync();
        }

        public async Task<List<CourseMaster>> GetCoursesSortedByNameAsync()
        {
            return await _context.CourseMasters
                .OrderBy(c => c.CourseName)
                .ToListAsync();
        }

        public async Task<List<CourseMaster>> GetCoursesSortedByDurationAsync()
        {
            return await _context.CourseMasters
                .OrderBy(c => c.CourseDuration)
                .ToListAsync();
        }

        public async Task<List<CourseMaster>> GetCoursesSortedByAgeGroupAsync()
        {
            return await _context.CourseMasters
                .OrderBy(c => c.AgeGrpType)
                .ToListAsync();
        }

        public async Task<List<CourseMaster>> GetCoursesWithVideoContentAsync()
        {
            return await _context.CourseMasters
                .Where(c => c.VideoId != null)
                .ToListAsync();
        }

        public async Task<List<CourseMaster>> GetCoursesBySyllabusKeywordAsync(string keyword)
        {
            return await _context.CourseMasters
                .Where(c => c.CourseSyllabus != null && c.CourseSyllabus.Contains(keyword))
                .ToListAsync();
        }

        // ==============================
        // DUPLICATE DETECTION AND REMOVAL OPERATIONS
        // ==============================
        public async Task<List<CourseMaster>> FindDuplicateCoursesByNameAsync()
        {
            return await _context.CourseMasters
                .GroupBy(c => c.CourseName)
                .Where(g => g.Count() > 1)
                .SelectMany(g => g)
                .ToListAsync();
        }

        public async Task<List<CourseMaster>> FindDuplicateCoursesByNameAndDescriptionAsync()
        {
            return await _context.CourseMasters
                .GroupBy(c => new { c.CourseName, c.CourseDescription })
                .Where(g => g.Count() > 1)
                .SelectMany(g => g)
                .ToListAsync();
        }

        public async Task<List<CourseMaster>> FindDuplicateCoursesByNameDescriptionAndDurationAsync()
        {
            return await _context.CourseMasters
                .GroupBy(c => new { c.CourseName, c.CourseDescription, c.CourseDuration })
                .Where(g => g.Count() > 1)
                .SelectMany(g => g)
                .ToListAsync();
        }

        public async Task<List<CourseMaster>> FindExactDuplicateCoursesAsync()
        {
            return await _context.CourseMasters
                .GroupBy(c => new { c.CourseName, c.CourseDescription, c.CourseDuration, c.AgeGrpType, c.CourseSyllabus })
                .Where(g => g.Count() > 1)
                .SelectMany(g => g)
                .ToListAsync();
        }

        public async Task<long> CountDuplicateCoursesByNameAsync()
        {
            return await _context.CourseMasters
                .GroupBy(c => c.CourseName)
                .Where(g => g.Count() > 1)
                .SelectMany(g => g)
                .LongCountAsync();
        }

        public async Task<long> CountDuplicateCoursesByNameAndDescriptionAsync()
        {
            return await _context.CourseMasters
                .GroupBy(c => new { c.CourseName, c.CourseDescription })
                .Where(g => g.Count() > 1)
                .SelectMany(g => g)
                .LongCountAsync();
        }

        public async Task RemoveDuplicateCoursesByNameAsync()
        {
            var duplicates = await _context.CourseMasters
                .GroupBy(c => c.CourseName)
                .Where(g => g.Count() > 1)
                .SelectMany(g => g.Skip(1)) // Keep first, remove rest
                .ToListAsync();

            _context.CourseMasters.RemoveRange(duplicates);
            await _context.SaveChangesAsync();
        }

        public async Task RemoveDuplicateCoursesByNameAndDescriptionAsync()
        {
            var duplicates = await _context.CourseMasters
                .GroupBy(c => new { c.CourseName, c.CourseDescription })
                .Where(g => g.Count() > 1)
                .SelectMany(g => g.Skip(1)) // Keep first, remove rest
                .ToListAsync();

            _context.CourseMasters.RemoveRange(duplicates);
            await _context.SaveChangesAsync();
        }

        public async Task RemoveDuplicateCoursesByNameDescriptionAndDurationAsync()
        {
            var duplicates = await _context.CourseMasters
                .GroupBy(c => new { c.CourseName, c.CourseDescription, c.CourseDuration })
                .Where(g => g.Count() > 1)
                .SelectMany(g => g.Skip(1)) // Keep first, remove rest
                .ToListAsync();

            _context.CourseMasters.RemoveRange(duplicates);
            await _context.SaveChangesAsync();
        }

        public async Task RemoveAllDuplicatesAsync()
        {
            var duplicates = await _context.CourseMasters
                .GroupBy(c => new { c.CourseName, c.CourseDescription, c.CourseDuration, c.AgeGrpType, c.CourseSyllabus })
                .Where(g => g.Count() > 1)
                .SelectMany(g => g.Skip(1)) // Keep first, remove rest
                .ToListAsync();

            _context.CourseMasters.RemoveRange(duplicates);
            await _context.SaveChangesAsync();
        }

        public async Task<List<CourseMaster>> FindAndRemoveDuplicatesByNameAsync()
        {
            var duplicates = await _context.CourseMasters
                .GroupBy(c => c.CourseName)
                .Where(g => g.Count() > 1)
                .SelectMany(g => g.Skip(1)) // Keep first, remove rest
                .ToListAsync();

            _context.CourseMasters.RemoveRange(duplicates);
            await _context.SaveChangesAsync();
            return duplicates;
        }

        public async Task<List<CourseMaster>> FindAndRemoveDuplicatesByNameAndDescriptionAsync()
        {
            var duplicates = await _context.CourseMasters
                .GroupBy(c => new { c.CourseName, c.CourseDescription })
                .Where(g => g.Count() > 1)
                .SelectMany(g => g.Skip(1)) // Keep first, remove rest
                .ToListAsync();

            _context.CourseMasters.RemoveRange(duplicates);
            await _context.SaveChangesAsync();
            return duplicates;
        }

        public async Task<List<CourseMaster>> FindAndRemoveDuplicatesByNameDescriptionAndDurationAsync()
        {
            var duplicates = await _context.CourseMasters
                .GroupBy(c => new { c.CourseName, c.CourseDescription, c.CourseDuration })
                .Where(g => g.Count() > 1)
                .SelectMany(g => g.Skip(1)) // Keep first, remove rest
                .ToListAsync();

            _context.CourseMasters.RemoveRange(duplicates);
            await _context.SaveChangesAsync();
            return duplicates;
        }

        public async Task<List<CourseMaster>> FindAndRemoveAllDuplicatesAsync()
        {
            var duplicates = await _context.CourseMasters
                .GroupBy(c => new { c.CourseName, c.CourseDescription, c.CourseDuration, c.AgeGrpType, c.CourseSyllabus })
                .Where(g => g.Count() > 1)
                .SelectMany(g => g.Skip(1)) // Keep first, remove rest
                .ToListAsync();

            _context.CourseMasters.RemoveRange(duplicates);
            await _context.SaveChangesAsync();
            return duplicates;
        }
    }
}


