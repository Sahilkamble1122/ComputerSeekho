using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DotNetRest.Models
{

    [Table("album_master")]
    public class AlbumMaster
    {

        [Key]
        [Column("album_id")]
        public int AlbumId { get; set; }

        [Column("album_name")]
        public string? AlbumName { get; set; }

        [Column("album_description")]
        public string? AlbumDescription { get; set; }

        [Column("start_date")]
        public DateTime? StartDate { get; set; }

        [Column("end_date")]
        public DateTime? EndDate { get; set; }

        [Column("album_is_active")]
        public bool? AlbumIsActive { get; set; }

        [Column("created_date")]
        public DateTime? CreatedDate { get; set; }

        [Column("updated_date")]
        public DateTime? UpdatedDate { get; set; }

        public AlbumMaster()
        {
            StartDate = DateTime.Now;
            EndDate = DateTime.Now;
            CreatedDate = DateTime.Now;
            UpdatedDate = DateTime.Now;
        }
    }
}
