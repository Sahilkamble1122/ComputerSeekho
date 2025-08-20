using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DotNetRest.Models
{
    [Table("receipt_master")]
    public class Receipt
    {
        [Key]
        [Column("receipt_id")]
        public int ReceiptId { get; set; }

        [Column("receipt_number")]
        public string? ReceiptNumber { get; set; }

        [Column("receipt_date")]
        public DateTime? ReceiptDate { get; set; }

        [Column("receipt_amount")]
        public double? ReceiptAmount { get; set; }

        [Column("payment_id")]
        public int? PaymentId { get; set; }

        [Column("student_id")]
        public int? StudentId { get; set; }

        [Column("created_date")]
        public DateTime? CreatedDate { get; set; }

        [Column("updated_date")]
        public DateTime? UpdatedDate { get; set; }
    }
}
