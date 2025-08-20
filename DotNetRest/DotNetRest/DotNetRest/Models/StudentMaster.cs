using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DotNetRest.Models
{

    [Table("student_master")]
    public class StudentMaster
    {
        [Key]
        [Column("student_id")]
        public int StudentId { get; set; }

        [Column("student_name")]
        public string? StudentName { get; set; }

        [Column("student_address")]
        public string? StudentAddress { get; set; }

        [Column("student_gender")]
        public string? StudentGender { get; set; }

        [Column("photo_url")]
        public string? PhotoUrl { get; set; }

        [Column("student_dob")]
        public DateTime? StudentDob { get; set; }

        [Column("student_qualification")]
        public string? StudentQualification { get; set; }

        [Column("student_mobile")]
        public long? StudentMobile { get; set; }

        [Column("student_email")]
        public string? StudentEmail { get; set; }

        [Column("batch_id")]
        public int? BatchId { get; set; }

        [Column("course_id")]
        public int? CourseId { get; set; }

        [Column("student_password")]
        public string? StudentPassword { get; set; }

        [Column("student_username")]
        public string? StudentUsername { get; set; }

        [Column("is_placed")]
        public bool? IsPlaced { get; set; }

        [Column("created_date")]
        public DateTime? CreatedDate { get; set; }

        [Column("updated_date")]
        public DateTime? UpdatedDate { get; set; }

        [Column("pending_fees")]
        public double? PendingFees { get; set; }

        [Column("course_fee")]
        public double? CourseFee { get; set; }

        public StudentMaster()
        {
            CreatedDate = DateTime.Now;
            UpdatedDate = DateTime.Now;
            this.IsPlaced = false;
        }
    }
}
