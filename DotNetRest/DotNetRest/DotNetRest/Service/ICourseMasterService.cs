using DotNetRest.Models;

namespace DotNetRest.Service
{
    public interface ICourseMasterService
    {
        // Create operations
        Task<CourseMaster> SaveCourseAsync(CourseMaster course);
        Task<List<CourseMaster>> SaveAllCoursesAsync(List<CourseMaster> courses);

        // Read operations
        Task<List<CourseMaster>> GetAllCoursesAsync();
        Task<CourseMaster?> GetCourseByIdAsync(int courseId);
        Task<List<CourseMaster>> GetCoursesByNameAsync(string courseName);
        Task<List<CourseMaster>> GetCoursesByDescriptionAsync(string description);
        Task<List<CourseMaster>> GetCoursesByAgeGroupAsync(string ageGroup);
        Task<List<CourseMaster>> GetActiveCoursesAsync();
        Task<List<CourseMaster>> GetCoursesByNameContainingAsync(string courseName);
        Task<List<CourseMaster>> GetCoursesByAgeGroupContainingAsync(string ageGroup);
        Task<List<CourseMaster>> GetCoursesByDurationRangeAsync(int minDuration, int maxDuration);
        Task<long> CountActiveCoursesAsync();
        Task<long> CountCoursesByAgeGroupAsync(string ageGroup);

        // Update operations
        Task<CourseMaster> UpdateCourseAsync(int courseId, CourseMaster courseDetails);
        Task<CourseMaster> UpdateCourseStatusAsync(int courseId, bool isActive);
        Task<CourseMaster> UpdateCourseNameAsync(int courseId, string courseName);
        Task<CourseMaster> UpdateCourseDescriptionAsync(int courseId, string description);
        Task<CourseMaster> UpdateCourseDurationAsync(int courseId, int duration);
        Task<CourseMaster> UpdateCourseSyllabusAsync(int courseId, string syllabus);

        // Delete operations
        Task DeleteCourseAsync(int courseId);
        Task DeleteCoursesByAgeGroupAsync(string ageGroup);
        Task DeleteInactiveCoursesAsync();
        Task DeleteAllCoursesAsync();

        // Business logic operations
        Task<List<CourseMaster>> SearchCoursesAsync(string searchTerm);
        Task<List<CourseMaster>> GetCoursesByDurationAndAgeGroupAsync(int duration, string ageGroup);
        Task<List<CourseMaster>> GetCoursesWithPaginationAsync(int page, int size);
        Task<List<CourseMaster>> GetCoursesSortedByNameAsync();
        Task<List<CourseMaster>> GetCoursesSortedByDurationAsync();
        Task<List<CourseMaster>> GetCoursesSortedByAgeGroupAsync();
        Task<List<CourseMaster>> GetCoursesWithVideoContentAsync();
        Task<List<CourseMaster>> GetCoursesBySyllabusKeywordAsync(string keyword);

        // Duplicate detection and removal operations
        Task<List<CourseMaster>> FindDuplicateCoursesByNameAsync();
        Task<List<CourseMaster>> FindDuplicateCoursesByNameAndDescriptionAsync();
        Task<List<CourseMaster>> FindDuplicateCoursesByNameDescriptionAndDurationAsync();
        Task<List<CourseMaster>> FindExactDuplicateCoursesAsync();
        Task<long> CountDuplicateCoursesByNameAsync();
        Task<long> CountDuplicateCoursesByNameAndDescriptionAsync();
        Task RemoveDuplicateCoursesByNameAsync();
        Task RemoveDuplicateCoursesByNameAndDescriptionAsync();
        Task RemoveDuplicateCoursesByNameDescriptionAndDurationAsync();
        Task RemoveAllDuplicatesAsync();
        Task<List<CourseMaster>> FindAndRemoveDuplicatesByNameAsync();
        Task<List<CourseMaster>> FindAndRemoveDuplicatesByNameAndDescriptionAsync();
        Task<List<CourseMaster>> FindAndRemoveDuplicatesByNameDescriptionAndDurationAsync();
        Task<List<CourseMaster>> FindAndRemoveAllDuplicatesAsync();
    }
}
