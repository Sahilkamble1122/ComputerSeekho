using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DotNetRest.Models
{
    [Table("followup")]
    public class Followup
    {
        [Key]
        [Column("followup_id")]
        public int FollowupId { get; set; }

        [Column("enquiry_id")]
        public int? EnquiryId { get; set; }

        [Column("staff_id")]
        public int? StaffId { get; set; }

        [Column("followup_date")]
        public DateTime? FollowupDate { get; set; }

        [Column("followup_msg")]
        public string? FollowupMsg { get; set; }

        [Column("is_active")]
        public bool? IsActive { get; set; }

        [Column("created_date")]
        public DateTime? CreatedDate { get; set; }

        [Column("updated_date")]
        public DateTime? UpdatedDate { get; set; }

        public Followup()
        {
            FollowupDate = DateTime.Now;
            CreatedDate = DateTime.Now;
            UpdatedDate = DateTime.Now;
        }
    }
}
