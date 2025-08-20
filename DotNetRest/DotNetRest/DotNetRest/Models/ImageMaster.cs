using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DotNetRest.Models
{
    [Table("image_master")]
    public class ImageMaster
    {
        [Key]
        [Column("image_id")]
        public int ImageId { get; set; }

        [Column("image_path")]
        public string? ImagePath { get; set; }

        [Column("album_id")]
        public int? AlbumId { get; set; }

        [Column("is_album_cover")]
        public bool? IsAlbumCover { get; set; }

        [Column("image_is_active")]
        public bool? ImageIsActive { get; set; }

        [Column("created_date")]
        public DateTime? CreatedDate { get; set; }

        [Column("updated_date")]
        public DateTime? UpdatedDate { get; set; }

        // Default constructor to match Spring Boot behavior
        public ImageMaster()
        {
            CreatedDate = DateTime.Now;
            UpdatedDate = DateTime.Now;
        }
    }
}
