using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DotNetRest.Models
{
    [Table("placement")]
    public class Placement
    {
        [Key]
        [Column("placement_id")]
        public int PlacementId { get; set; }

        [Column("student_id")]
        public int? StudentId { get; set; }

        [Column("company_name")]
        public string? CompanyName { get; set; }

        [Column("position")]
        public string? Position { get; set; }

        [Column("salary")]
        [Precision(10, 2)]
        public decimal? Salary { get; set; }

        [Column("placement_date")]
        public DateTime? PlacementDate { get; set; }

        [Column("created_date")]
        public DateTime? CreatedDate { get; set; }

        [Column("updated_date")]
        public DateTime? UpdatedDate { get; set; }

        public Placement()
        {
            CreatedDate = DateTime.Now;
            UpdatedDate = DateTime.Now;
        }
    }
}
