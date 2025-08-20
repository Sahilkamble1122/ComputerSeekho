using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DotNetRest.Models
{
    [Table("enquiry")]
    public class Enquiry
    {
        [Key]
        [Column("enquiry_id")]
        public int EnquiryId { get; set; }

        [Column("enquirer_name")]
        public string? EnquirerName { get; set; }

        [Column("enquirer_address")]
        public string? EnquirerAddress { get; set; }

        [Column("enquirer_mobile")]
        public long? EnquirerMobile { get; set; }

        [Column("enquirer_alternate_mobile")]
        public long? EnquirerAlternateMobile { get; set; }

        [Column("enquirer_email_id")]
        public string? EnquirerEmailId { get; set; }

        [Column("enquiry_date")]
        public DateTime? EnquiryDate { get; set; }

        [Column("enquirer_query")]
        public string? EnquirerQuery { get; set; }

        [Column("closure_reason_id")]
        public int? ClosureReasonId { get; set; }

        [Column("closure_reason")]
        public string? ClosureReason { get; set; }

        [Column("enquiry_processed_flag")]
        public bool? EnquiryProcessedFlag { get; set; }

        [Column("course_id")]
        public int? CourseId { get; set; }

        [Column("assigned_staff_id")]
        public int? AssignedStaffId { get; set; }

        [Column("student_name")]
        public string? StudentName { get; set; }

        [Column("enquiry_counter")]
        public int? EnquiryCounter { get; set; }

        [Column("follow_up_date")]
        public DateTime? FollowUpDate { get; set; }

        [Column("created_date")]
        public DateTime? CreatedDate { get; set; }

        [Column("updated_date")]
        public DateTime? UpdatedDate { get; set; }

        [Column("enquiry_state")]
        public bool? EnquiryState { get; set; }

        // Default constructor to match Spring Boot behavior
        public Enquiry()
        {
            EnquiryDate = DateTime.Now.Date;
            EnquiryCounter = 0; // Default counter value is 0
            CreatedDate = DateTime.Now;
            UpdatedDate = DateTime.Now;
            EnquiryProcessedFlag = false;
            EnquiryState = false; // Default state is false
        }
    }
}
