using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DotNetRest.Models
{
    [Table("closure_reason_master")]
    public class ClosureReasonMaster
    {
        [Key]
        [Column("closure_reason_id")]
        public int ClosureReasonId { get; set; }

        [Column("closure_reason_name")]
        public string? ClosureReasonName { get; set; }

        [Column("closure_reason_desc")]
        public string? ClosureReasonDesc { get; set; }

        [Column("closure_reason_is_active")]
        public bool? ClosureReasonIsActive { get; set; }

        [Column("created_date")]
        public DateTime? CreatedDate { get; set; }

        [Column("updated_date")]
        public DateTime? UpdatedDate { get; set; }

        // Default constructor to match Spring Boot behavior
        public ClosureReasonMaster()
        {
            ClosureReasonIsActive = true;
            CreatedDate = DateTime.Now;
            UpdatedDate = DateTime.Now;
        }
    }
}
