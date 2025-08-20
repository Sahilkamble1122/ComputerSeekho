using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DotNetRest.Models
{
    [Table("video_master")]
    public class VideoMaster
    {
        [Key]
        [Column("video_id")]
        public int VideoId { get; set; }

        [Column("video_name")]
        public string? VideoName { get; set; }

        [Column("video_url")]
        public string? VideoUrl { get; set; }

        [Column("video_description")]
        public string? VideoDescription { get; set; }

        [Column("album_id")]
        public int? AlbumId { get; set; }

        [Column("video_is_active")]
        public bool? VideoIsActive { get; set; }

        [Column("created_date")]
        public DateTime? CreatedDate { get; set; }

        [Column("updated_date")]
        public DateTime? UpdatedDate { get; set; }

        public VideoMaster()
        {
            CreatedDate = DateTime.Now;
            UpdatedDate = DateTime.Now;
        }
    }
}
