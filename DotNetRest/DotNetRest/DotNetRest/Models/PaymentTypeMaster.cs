using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DotNetRest.Models
{
    [Table("payment_type_master")]
    public class PaymentTypeMaster
    {
        [Key]
        [Column("payment_type_id")]
        public int PaymentTypeId { get; set; }

        [Column("payment_type_name")]
        public string? PaymentTypeName { get; set; }

        [Column("payment_type_desc")]
        public string? PaymentTypeDesc { get; set; }

        [NotMapped]
        [Column("payment_type_is_active")]
        public bool? PaymentTypeIsActive { get; set; }

        [Column("created_date")]
        public DateTime? CreatedDate { get; set; }

        [Column("updated_date")]
        public DateTime? UpdatedDate { get; set; }

        public PaymentTypeMaster()
        {
            CreatedDate = DateTime.Now;
            UpdatedDate = DateTime.Now;
        }
    }
}
