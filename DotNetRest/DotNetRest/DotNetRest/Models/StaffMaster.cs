using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DotNetRest.Models
{
    [Table("staff_master")]
    public class StaffMaster
    {

        [Key]
        [Column("staff_id")]
        public int StaffId { get; set; }

        [Column("staff_name")]
        public string? StaffName { get; set; }

        [Column("staff_address")]
        public string? StaffAddress { get; set; }

        [Column("staff_gender")]
        public string? StaffGender { get; set; }

        [Column("staff_mobile")]
        public long? StaffMobile { get; set; }

        [Column("staff_email")]
        public string? StaffEmail { get; set; }

        [Column("staff_password")]
        public string? StaffPassword { get; set; }

        [Column("staff_username")]
        public string? StaffUsername { get; set; }

        [Column("staff_role")]
        public string? StaffRole { get; set; }

        [Column("photo_url")]
        public string? PhotoUrl { get; set; }

        [Column("created_date")]
        public DateTime? CreatedDate { get; set; }

        [Column("updated_date")]
        public DateTime? UpdatedDate { get; set; }

        public StaffMaster()
        {
            CreatedDate = DateTime.Now;
            UpdatedDate = DateTime.Now;
        }
    }
}
