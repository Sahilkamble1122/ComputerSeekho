using MathNet.Numerics;
using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DotNetRest.Models
{
    [Table("payment_with_type")]
    public class PaymentWithType
    {
        [Key]
        [Column("payment_id")]
        public int? PaymentId { get; set; }

        [Column("payment_type_id")]
        public int? PaymentTypeId { get; set; }

        [Column("payment_type_desc")]
        public string? PaymentTypeDesc { get; set; }

        [Column("payment_date")]
        public DateTime? PaymentDate { get; set; }

        [Column("student_id")]
        public int? StudentId { get; set; }

        [Column("course_id")]
        public int? CourseId { get; set; }

        [Column("batch_id")]
        public int? BatchId { get; set; }

        [Column("amount")]
        public double? Amount { get; set; }

        [Column("status")]
        public string? Status { get; set; }

        [Column("created_date")]
        public DateTime? CreatedDate { get; set; }

        [Column("updated_date")]
        public DateTime? UpdatedDate { get; set; }
    }
}
