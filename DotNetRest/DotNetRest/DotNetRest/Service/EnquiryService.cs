
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using DotNetRest.Models;
using DotNetRest.Service;
using DotNetRest.Data;

namespace DotNetRest.Service.Impl
    {
        public class EnquiryService : IEnquiryService
        {
            private readonly ApplicationDbContext _context;

            public EnquiryService(ApplicationDbContext context)
            {
                _context = context;
            }

            // ==============================
            // CREATE OPERATIONS
            // ==============================
            public async Task<Enquiry> SaveEnquiryAsync(Enquiry enquiry)
            {
                if (enquiry.CreatedDate == null)
                {
                    enquiry.CreatedDate = DateTime.Now;
                }
                if (enquiry.UpdatedDate == null)
                {
                    enquiry.UpdatedDate = DateTime.Now;
                }
                _context.Enquiries.Add(enquiry);
                await _context.SaveChangesAsync();
                return enquiry;
            }

            public async Task<List<Enquiry>> SaveAllEnquiriesAsync(List<Enquiry> enquiries)
            {
                foreach (var enquiry in enquiries)
                {
                    if (enquiry.CreatedDate == null)
                    {
                        enquiry.CreatedDate = DateTime.Now;
                    }
                    if (enquiry.UpdatedDate == null)
                    {
                        enquiry.UpdatedDate = DateTime.Now;
                    }
                }
                _context.Enquiries.AddRange(enquiries);
                await _context.SaveChangesAsync();
                return enquiries;
            }

            // ==============================
            // READ OPERATIONS
            // ==============================
            public async Task<List<Enquiry>> GetAllEnquiriesAsync()
            {
                return await _context.Enquiries.ToListAsync();
            }

            public async Task<Enquiry?> GetEnquiryByIdAsync(int id)
            {
                return await _context.Enquiries.FindAsync(id);
            }

            public async Task<List<Enquiry>> GetEnquiriesByStudentNameAsync(string studentName)
            {
                return await _context.Enquiries
                    .Where(e => e.StudentName == studentName)
                    .ToListAsync();
            }

            public async Task<List<Enquiry>> GetEnquiriesByMobileAsync(long mobile)
            {
                return await _context.Enquiries
                    .Where(e => e.EnquirerMobile == mobile)
                    .ToListAsync();
            }

            public async Task<List<Enquiry>> GetEnquiriesByEmailAsync(string email)
            {
                return await _context.Enquiries
                    .Where(e => e.EnquirerEmailId == email)
                    .ToListAsync();
            }

            public async Task<List<Enquiry>> GetEnquiriesByStatusAsync(string status)
            {
                // Assuming status is based on enquiry_processed_flag
                bool isProcessed = status.ToLower() == "completed" || status.ToLower() == "processed";
                return await _context.Enquiries
                    .Where(e => e.EnquiryProcessedFlag == isProcessed)
                    .ToListAsync();
            }

            public async Task<List<Enquiry>> GetEnquiriesByStudentNameContainingAsync(string studentName)
            {
                return await _context.Enquiries
                    .Where(e => e.StudentName != null && e.StudentName.Contains(studentName))
                    .ToListAsync();
            }

            public async Task<List<Enquiry>> GetEnquiriesByEmailContainingAsync(string email)
            {
                return await _context.Enquiries
                    .Where(e => e.EnquirerEmailId != null && e.EnquirerEmailId.Contains(email))
                    .ToListAsync();
            }

            public async Task<List<Enquiry>> GetEnquiriesByDateRangeAsync(string startDate, string endDate)
            {
                if (DateTime.TryParse(startDate, out var start) && DateTime.TryParse(endDate, out var end))
                {
                    return await _context.Enquiries
                        .Where(e => e.EnquiryDate >= start.Date && e.EnquiryDate <= end.Date)
                        .ToListAsync();
                }
                return new List<Enquiry>();
            }

            public async Task<long> CountEnquiriesByStatusAsync(string status)
            {
                bool isProcessed = status.ToLower() == "completed" || status.ToLower() == "processed";
                return await _context.Enquiries
                    .LongCountAsync(e => e.EnquiryProcessedFlag == isProcessed);
            }

            // ==============================
            // UPDATE OPERATIONS
            // ==============================
            public async Task<Enquiry> UpdateEnquiryAsync(int id, Enquiry enquiryDetails)
            {
                var existingEnquiry = await _context.Enquiries.FindAsync(id);
                if (existingEnquiry == null)
                    throw new Exception($"Enquiry not found with id: {id}");

                existingEnquiry.EnquirerName = enquiryDetails.EnquirerName;
                existingEnquiry.EnquirerAddress = enquiryDetails.EnquirerAddress;
                existingEnquiry.EnquirerMobile = enquiryDetails.EnquirerMobile;
                existingEnquiry.EnquirerAlternateMobile = enquiryDetails.EnquirerAlternateMobile;
                existingEnquiry.EnquirerEmailId = enquiryDetails.EnquirerEmailId;
                existingEnquiry.EnquiryDate = enquiryDetails.EnquiryDate;
                existingEnquiry.EnquirerQuery = enquiryDetails.EnquirerQuery;
                existingEnquiry.ClosureReasonId = enquiryDetails.ClosureReasonId;
                existingEnquiry.ClosureReason = enquiryDetails.ClosureReason;
                existingEnquiry.EnquiryProcessedFlag = enquiryDetails.EnquiryProcessedFlag;
                existingEnquiry.CourseId = enquiryDetails.CourseId;
                existingEnquiry.AssignedStaffId = enquiryDetails.AssignedStaffId;
                existingEnquiry.StudentName = enquiryDetails.StudentName;
                existingEnquiry.EnquiryCounter = enquiryDetails.EnquiryCounter;
                existingEnquiry.FollowUpDate = enquiryDetails.FollowUpDate;
                existingEnquiry.EnquiryState = enquiryDetails.EnquiryState;
                existingEnquiry.UpdatedDate = DateTime.Now;

                await _context.SaveChangesAsync();
                return existingEnquiry;
            }

            public async Task<Enquiry> UpdateEnquiryStatusAsync(int id, string status)
            {
                var existingEnquiry = await _context.Enquiries.FindAsync(id);
                if (existingEnquiry == null)
                    throw new Exception($"Enquiry not found with id: {id}");

                existingEnquiry.EnquiryProcessedFlag = status.ToLower() == "completed" || status.ToLower() == "processed";
                existingEnquiry.UpdatedDate = DateTime.Now;
                await _context.SaveChangesAsync();
                return existingEnquiry;
            }

            public async Task<Enquiry> UpdateEnquiryStudentNameAsync(int id, string studentName)
            {
                var existingEnquiry = await _context.Enquiries.FindAsync(id);
                if (existingEnquiry == null)
                    throw new Exception($"Enquiry not found with id: {id}");

                existingEnquiry.StudentName = studentName;
                existingEnquiry.UpdatedDate = DateTime.Now;
                await _context.SaveChangesAsync();
                return existingEnquiry;
            }

            public async Task<Enquiry> UpdateEnquiryMobileAsync(int id, long mobile)
            {
                var existingEnquiry = await _context.Enquiries.FindAsync(id);
                if (existingEnquiry == null)
                    throw new Exception($"Enquiry not found with id: {id}");

                existingEnquiry.EnquirerMobile = mobile;
                existingEnquiry.UpdatedDate = DateTime.Now;
                await _context.SaveChangesAsync();
                return existingEnquiry;
            }

            public async Task<Enquiry> UpdateEnquiryEmailAsync(int id, string email)
            {
                var existingEnquiry = await _context.Enquiries.FindAsync(id);
                if (existingEnquiry == null)
                    throw new Exception($"Enquiry not found with id: {id}");

                existingEnquiry.EnquirerEmailId = email;
                existingEnquiry.UpdatedDate = DateTime.Now;
                await _context.SaveChangesAsync();
                return existingEnquiry;
            }

            public async Task<Enquiry> UpdateEnquiryCounterAsync(int id, int enquiryCounter)
            {
                var existingEnquiry = await _context.Enquiries.FindAsync(id);
                if (existingEnquiry == null)
                    throw new Exception($"Enquiry not found with id: {id}");

                existingEnquiry.EnquiryCounter = enquiryCounter;
                existingEnquiry.UpdatedDate = DateTime.Now;
                await _context.SaveChangesAsync();
                return existingEnquiry;
            }

            public async Task<Enquiry> UpdateEnquiryClosureAsync(int id, int? closureReasonId, string? closureReason)
            {
                var existingEnquiry = await _context.Enquiries.FindAsync(id);
                if (existingEnquiry == null)
                    throw new Exception($"Enquiry not found with id: {id}");

                existingEnquiry.ClosureReasonId = closureReasonId;
                existingEnquiry.ClosureReason = closureReason;
                existingEnquiry.UpdatedDate = DateTime.Now;
                await _context.SaveChangesAsync();
                return existingEnquiry;
            }

            public async Task<Enquiry> UpdateEnquiryStateAsync(int id, bool enquiryState)
            {
                var existingEnquiry = await _context.Enquiries.FindAsync(id);
                if (existingEnquiry == null)
                    throw new Exception($"Enquiry not found with id: {id}");

                existingEnquiry.EnquiryState = enquiryState;
                existingEnquiry.UpdatedDate = DateTime.Now;
                await _context.SaveChangesAsync();
                return existingEnquiry;
            }

            // ==============================
            // DELETE OPERATIONS
            // ==============================
            public async Task DeleteEnquiryAsync(int id)
            {
                var enquiry = await _context.Enquiries.FindAsync(id);
                if (enquiry == null)
                    throw new Exception($"Enquiry not found with id: {id}");

                _context.Enquiries.Remove(enquiry);
                await _context.SaveChangesAsync();
            }

            public async Task DeleteEnquiriesByStatusAsync(string status)
            {
                bool isProcessed = status.ToLower() == "completed" || status.ToLower() == "processed";
                var enquiries = await _context.Enquiries
                    .Where(e => e.EnquiryProcessedFlag == isProcessed)
                    .ToListAsync();

                _context.Enquiries.RemoveRange(enquiries);
                await _context.SaveChangesAsync();
            }

            public async Task DeleteAllEnquiriesAsync()
            {
                var allEnquiries = await _context.Enquiries.ToListAsync();
                _context.Enquiries.RemoveRange(allEnquiries);
                await _context.SaveChangesAsync();
            }

            // ==============================
            // BUSINESS LOGIC OPERATIONS
            // ==============================
            public async Task<List<Enquiry>> SearchEnquiriesAsync(string searchTerm)
            {
                return await _context.Enquiries
                    .Where(e => (e.EnquirerName != null && e.EnquirerName.Contains(searchTerm)) ||
                               (e.StudentName != null && e.StudentName.Contains(searchTerm)) ||
                               (e.EnquirerEmailId != null && e.EnquirerEmailId.Contains(searchTerm)) ||
                               (e.EnquirerQuery != null && e.EnquirerQuery.Contains(searchTerm)))
                    .ToListAsync();
            }

            public async Task<List<Enquiry>> GetEnquiriesByDateRangeAndCourseAsync(string startDate, string endDate, int courseId)
            {
                if (DateTime.TryParse(startDate, out var start) && DateTime.TryParse(endDate, out var end))
                {
                    return await _context.Enquiries
                        .Where(e => e.EnquiryDate >= start.Date && e.EnquiryDate <= end.Date && e.CourseId == courseId)
                        .ToListAsync();
                }
                return new List<Enquiry>();
            }

            public async Task<List<Enquiry>> GetEnquiriesWithPaginationAsync(int page, int size)
            {
                return await _context.Enquiries
                    .Skip(page * size)
                    .Take(size)
                    .ToListAsync();
            }

            public async Task<List<Enquiry>> GetEnquiriesSortedByDateAsync()
            {
                return await _context.Enquiries
                    .OrderBy(e => e.EnquiryDate)
                    .ToListAsync();
            }

            public async Task<List<Enquiry>> GetEnquiriesSortedByStudentNameAsync()
            {
                return await _context.Enquiries
                    .OrderBy(e => e.StudentName)
                    .ToListAsync();
            }

            public async Task<List<Enquiry>> GetEnquiriesSortedByStatusAsync()
            {
                return await _context.Enquiries
                    .OrderBy(e => e.EnquiryProcessedFlag)
                    .ToListAsync();
            }

            public async Task<List<Enquiry>> GetPendingEnquiriesAsync()
            {
                return await _context.Enquiries
                    .Where(e => e.EnquiryProcessedFlag == false)
                    .ToListAsync();
            }

            public async Task<List<Enquiry>> GetCompletedEnquiriesAsync()
            {
                return await _context.Enquiries
                    .Where(e => e.EnquiryProcessedFlag == true)
                    .ToListAsync();
            }

            public async Task<List<Enquiry>> GetFollowUpRequiredEnquiriesAsync()
            {
                return await _context.Enquiries
                    .Where(e => e.FollowUpDate != null && e.FollowUpDate <= DateTime.Now.Date)
                    .ToListAsync();
            }
        }
    }


