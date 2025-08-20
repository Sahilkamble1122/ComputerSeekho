using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DotNetRest.Models
{
    [Table("batch_master")]
    public class BatchMaster
    {
        [Key]
        [Column("batch_id")]
        public int BatchId { get; set; }

        [Column("batch_name")]
        public string? BatchName { get; set; }

        [Column("batch_start_time")]
        public TimeSpan? BatchStartTime { get; set; }

        [Column("batch_end_time")]
        public TimeSpan? BatchEndTime { get; set; }

        [Column("course_id")]
        public int? CourseId { get; set; }

        [Column("presentation_date")]
        public DateTime? PresentationDate { get; set; }

        [Column("course_fees")]
        public int? CourseFees { get; set; }

        [Column("course_fees_from")]
        public DateTime? CourseFeesFrom { get; set; }

        [Column("course_fees_to")]
        public DateTime? CourseFeesTo { get; set; }

        [Column("batch_is_active")]
        public bool? BatchIsActive { get; set; }

        [Column("staff_id")]
        public int? StaffId { get; set; }

        [Column("created_date")]
        public DateTime? CreatedDate { get; set; }

        [Column("updated_date")]
        public DateTime? UpdatedDate { get; set; }

        [Column("batch_logo")]
        public string? BatchLogo { get; set; }

        // Default constructor to match Spring Boot behavior
        public BatchMaster()
        {
            CreatedDate = DateTime.Now;
            UpdatedDate = DateTime.Now;
        }
    }
}
