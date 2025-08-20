using DotNetRest.Models;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Reflection.Emit;

namespace DotNetRest.Data
{
    public class ApplicationDbContext : DbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options)
        {
        }

        public DbSet<Enquiry> Enquiries { get; set; }
        public DbSet<ContactUs> ContactUs { get; set; }
        public DbSet<BatchMaster> BatchMasters { get; set; }
        public DbSet<StudentMaster> StudentMasters { get; set; }
        public DbSet<CourseMaster> CourseMasters { get; set; }
        public DbSet<StaffMaster> StaffMasters { get; set; }
        public DbSet<AlbumMaster> AlbumMasters { get; set; }
        public DbSet<ImageMaster> ImageMasters { get; set; }
        public DbSet<VideoMaster> VideoMasters { get; set; }
        public DbSet<PaymentTypeMaster> PaymentTypeMasters { get; set; }
        public DbSet<ClosureReasonMaster> ClosureReasonMasters { get; set; }
        public DbSet<Placement> Placements { get; set; }
        public DbSet<Receipt> Receipts { get; set; }
        public DbSet<Followup> Followups { get; set; }
        public DbSet<PaymentWithType> PaymentWithTypes { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            // Configure decimal precision for financial fields
            modelBuilder.Entity<StudentMaster>()
                .Property(e => e.PendingFees)
                .HasPrecision(10, 2);

            modelBuilder.Entity<StudentMaster>()
                .Property(e => e.CourseFee)
                .HasPrecision(10, 2);
        }

        
    }
}
