using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using DotNetRest.Data; // Replace with your actual namespace
using DotNetRest.Models;

namespace DotNetRest.Service
    {
        public class StaffMasterService : IStaffMasterService
        {
            private readonly ApplicationDbContext _context;

            public StaffMasterService(ApplicationDbContext context)
            {
                _context = context;
            }

            // CREATE OPERATIONS
            public async Task<StaffMaster> SaveStaffAsync(StaffMaster staff)
            {
                staff.UpdatedDate = staff.UpdatedDate == default ? DateTime.Now : staff.UpdatedDate;
                _context.StaffMasters.Add(staff);
                await _context.SaveChangesAsync();
                return staff;
            }

            public async Task<List<StaffMaster>> SaveAllStaffAsync(List<StaffMaster> staffList)
            {
                staffList.ForEach(s => s.UpdatedDate = s.UpdatedDate == default ? DateTime.Now : s.UpdatedDate);
                _context.StaffMasters.AddRange(staffList);
                await _context.SaveChangesAsync();
                return staffList;
            }

            // READ OPERATIONS
            public async Task<List<StaffMaster>> GetAllStaffAsync()
            {
                return await _context.StaffMasters.ToListAsync();
            }

            public async Task<StaffMaster?> GetStaffByIdAsync(int staffId)
            {
                return await _context.StaffMasters.FindAsync(staffId);
            }

            public async Task<List<StaffMaster>> GetStaffByNameAsync(string staffName)
            {
                return await _context.StaffMasters
                    .Where(s => s.StaffName == staffName)
                    .ToListAsync();
            }

            public async Task<List<StaffMaster>> GetStaffByEmailAsync(string email)
            {
                return await _context.StaffMasters
                    .Where(s => s.StaffEmail == email)
                    .ToListAsync();
            }

            public async Task<List<StaffMaster>> GetStaffByMobileAsync(long mobile)
            {
                return await _context.StaffMasters
                    .Where(s => s.StaffMobile == mobile)
                    .ToListAsync();
            }

            public async Task<List<StaffMaster>> GetStaffByDepartmentAsync(string department)
            {
                return await _context.StaffMasters
                    .Where(s => s.StaffRole == department)
                    .ToListAsync();
            }

            public async Task<List<StaffMaster>> GetStaffByDesignationAsync(string designation)
            {
                return await _context.StaffMasters
                    .Where(s => s.StaffRole == designation)
                    .ToListAsync();
            }

            public async Task<List<StaffMaster>> GetStaffByStatusAsync(string status)
            {
                // If no dedicated status column, return all
                return await _context.StaffMasters.ToListAsync();
            }

            public async Task<List<StaffMaster>> GetStaffByNameContainingAsync(string staffName)
            {
                return await _context.StaffMasters
                    .Where(s => EF.Functions.Like(s.StaffName, $"%{staffName}%"))
                    .ToListAsync();
            }

            public async Task<List<StaffMaster>> GetStaffByEmailContainingAsync(string email)
            {
                return await _context.StaffMasters
                    .Where(s => EF.Functions.Like(s.StaffEmail, $"%{email}%"))
                    .ToListAsync();
            }

            public async Task<long> CountStaffByDepartmentAsync(string department)
            {
                return await _context.StaffMasters.LongCountAsync(s => s.StaffRole == department);
            }

            public async Task<long> CountStaffByDesignationAsync(string designation)
            {
                return await _context.StaffMasters.LongCountAsync(s => s.StaffRole == designation);
            }

            // UPDATE OPERATIONS
            public async Task<StaffMaster> UpdateStaffAsync(int staffId, StaffMaster staffDetails)
            {
                var staff = await _context.StaffMasters.FindAsync(staffId);
                if (staff == null) throw new Exception($"Staff not found with id: {staffId}");

                staff.StaffName = staffDetails.StaffName;
                staff.PhotoUrl = staffDetails.PhotoUrl;
                staff.StaffMobile = staffDetails.StaffMobile;
                staff.StaffEmail = staffDetails.StaffEmail;
                staff.StaffUsername = staffDetails.StaffUsername;
                staff.StaffPassword = staffDetails.StaffPassword;
                staff.StaffRole = staffDetails.StaffRole;
                staff.UpdatedDate = DateTime.Now;

                await _context.SaveChangesAsync();
                return staff;
            }

            public async Task<StaffMaster> UpdateStaffNameAsync(int staffId, string staffName)
            {
                var staff = await _context.StaffMasters.FindAsync(staffId);
                if (staff == null) throw new Exception($"Staff not found with id: {staffId}");
                staff.StaffName = staffName;
                staff.UpdatedDate = DateTime.Now;
                await _context.SaveChangesAsync();
                return staff;
            }

            public async Task<StaffMaster> UpdateStaffEmailAsync(int staffId, string email)
            {
                var staff = await _context.StaffMasters.FindAsync(staffId);
                if (staff == null) throw new Exception($"Staff not found with id: {staffId}");
                staff.StaffEmail = email;
                staff.UpdatedDate = DateTime.Now;
                await _context.SaveChangesAsync();
                return staff;
            }

            public async Task<StaffMaster> UpdateStaffMobileAsync(int staffId, long mobile)
            {
                var staff = await _context.StaffMasters.FindAsync(staffId);
                if (staff == null) throw new Exception($"Staff not found with id: {staffId}");
                staff.StaffMobile = mobile;
                staff.UpdatedDate = DateTime.Now;
                await _context.SaveChangesAsync();
                return staff;
            }

            public async Task<StaffMaster> UpdateStaffDepartmentAsync(int staffId, string department)
            {
                var staff = await _context.StaffMasters.FindAsync(staffId);
                if (staff == null) throw new Exception($"Staff not found with id: {staffId}");
                staff.StaffRole = department;
                staff.UpdatedDate = DateTime.Now;
                await _context.SaveChangesAsync();
                return staff;
            }

            public async Task<StaffMaster> UpdateStaffDesignationAsync(int staffId, string designation)
            {
                var staff = await _context.StaffMasters.FindAsync(staffId);
                if (staff == null) throw new Exception($"Staff not found with id: {staffId}");
                staff.StaffRole = designation;
                staff.UpdatedDate = DateTime.Now;
                await _context.SaveChangesAsync();
                return staff;
            }

            public async Task<StaffMaster> UpdateStaffStatusAsync(int staffId, string status)
            {
                var staff = await _context.StaffMasters.FindAsync(staffId);
                if (staff == null) throw new Exception($"Staff not found with id: {staffId}");
                staff.StaffRole = status; // If no status column, reuse role
                staff.UpdatedDate = DateTime.Now;
                await _context.SaveChangesAsync();
                return staff;
            }

            // DELETE OPERATIONS
            public async Task DeleteStaffAsync(int staffId)
            {
                var staff = await _context.StaffMasters.FindAsync(staffId);
                if (staff == null) throw new Exception($"Staff not found with id: {staffId}");
                _context.StaffMasters.Remove(staff);
                await _context.SaveChangesAsync();
            }

            public async Task DeleteStaffByDepartmentAsync(string department)
            {
                var staffList = await _context.StaffMasters.Where(s => s.StaffRole == department).ToListAsync();
                _context.StaffMasters.RemoveRange(staffList);
                await _context.SaveChangesAsync();
            }

            public async Task DeleteStaffByDesignationAsync(string designation)
            {
                var staffList = await _context.StaffMasters.Where(s => s.StaffRole == designation).ToListAsync();
                _context.StaffMasters.RemoveRange(staffList);
                await _context.SaveChangesAsync();
            }

            public async Task DeleteStaffByStatusAsync(string status)
            {
                // If no status column, delete all
                var allStaff = await _context.StaffMasters.ToListAsync();
                _context.StaffMasters.RemoveRange(allStaff);
                await _context.SaveChangesAsync();
            }

            public async Task DeleteAllStaffAsync()
            {
                var allStaff = await _context.StaffMasters.ToListAsync();
                _context.StaffMasters.RemoveRange(allStaff);
                await _context.SaveChangesAsync();
            }

            // BUSINESS LOGIC OPERATIONS
            public async Task<List<StaffMaster>> SearchStaffAsync(string searchTerm)
            {
                return await _context.StaffMasters
                    .Where(s => EF.Functions.Like(s.StaffName, $"%{searchTerm}%") ||
                                EF.Functions.Like(s.StaffEmail, $"%{searchTerm}%") ||
                                EF.Functions.Like(s.StaffRole, $"%{searchTerm}%"))
                    .ToListAsync();
            }

            public async Task<List<StaffMaster>> GetStaffByDepartmentAndDesignationAsync(string department, string designation)
            {
                return await _context.StaffMasters
                    .Where(s => s.StaffRole == department && s.StaffRole == designation)
                    .ToListAsync();
            }

            public async Task<List<StaffMaster>> GetStaffWithPaginationAsync(int page, int size)
            {
                return await _context.StaffMasters
                    .Skip(page * size)
                    .Take(size)
                    .ToListAsync();
            }

            public async Task<List<StaffMaster>> GetStaffSortedByNameAsync()
            {
                return await _context.StaffMasters.OrderBy(s => s.StaffName).ToListAsync();
            }

            public async Task<List<StaffMaster>> GetStaffSortedByDepartmentAsync()
            {
                return await _context.StaffMasters.OrderBy(s => s.StaffRole).ToListAsync();
            }

            public async Task<List<StaffMaster>> GetStaffSortedByDesignationAsync()
            {
                return await _context.StaffMasters.OrderBy(s => s.StaffRole).ToListAsync();
            }

            public async Task<StaffMaster?> AuthenticateStaffAsync(string username, string password)
            {
                return await _context.StaffMasters
                    .FirstOrDefaultAsync(s => s.StaffUsername == username && s.StaffPassword == password);
            }

            public async Task<List<StaffMaster>> GetActiveStaffAsync()
            {
                return await _context.StaffMasters.ToListAsync(); // All staff if no status
            }

            public async Task<List<StaffMaster>> GetInactiveStaffAsync()
            {
                return await _context.StaffMasters.ToListAsync(); // All staff if no status
            }

            public async Task<List<StaffMaster>> GetStaffByEmailDomainAsync(string domain)
            {
                return await _context.StaffMasters
                    .Where(s => s.StaffEmail.EndsWith(domain))
                    .ToListAsync();
            }
        }
    }


