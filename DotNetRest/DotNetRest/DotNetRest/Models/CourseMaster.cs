using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DotNetRest.Models
{
    [Table("course_master")]
    public class CourseMaster
    {
        [Key]
        [Column("course_id")]
        public int CourseId { get; set; }

        [Column("course_name")]
        public string? CourseName { get; set; }

        [Column("course_description")]
        public string? CourseDescription { get; set; }

        [Column("course_duration")]
        public int? CourseDuration { get; set; }

        [Column("course_syllabus")]
        public string? CourseSyllabus { get; set; }

        [Column("age_grp_type")]
        public string? AgeGrpType { get; set; }

        [Column("course_is_active")]
        public bool? CourseIsActive { get; set; }

        [Column("cover_photo")]
        public string? CoverPhoto { get; set; }

        [Column("video_id")]
        public int? VideoId { get; set; }

        [Column("created_date")]
        public DateTime? CreatedDate { get; set; }

        [Column("updated_date")]
        public DateTime? UpdatedDate { get; set; }

        // Default constructor to match Spring Boot behavior
        public CourseMaster()
        {
            CreatedDate = DateTime.Now;
            UpdatedDate = DateTime.Now;
        }
    }
}
