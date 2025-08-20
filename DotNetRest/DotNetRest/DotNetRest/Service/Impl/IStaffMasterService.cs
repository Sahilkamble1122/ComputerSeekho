using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using DotNetRest.Models; // Replace with your actual namespace

namespace DotNetRest.Service
    {
        public interface IStaffMasterService
        {
            // CREATE OPERATIONS
            Task<StaffMaster> SaveStaffAsync(StaffMaster staff);
            Task<List<StaffMaster>> SaveAllStaffAsync(List<StaffMaster> staffList);

            // READ OPERATIONS
            Task<List<StaffMaster>> GetAllStaffAsync();
            Task<StaffMaster?> GetStaffByIdAsync(int staffId);
            Task<List<StaffMaster>> GetStaffByNameAsync(string staffName);
            Task<List<StaffMaster>> GetStaffByEmailAsync(string email);
            Task<List<StaffMaster>> GetStaffByMobileAsync(long mobile);
            Task<List<StaffMaster>> GetStaffByDepartmentAsync(string department);
            Task<List<StaffMaster>> GetStaffByDesignationAsync(string designation);
            Task<List<StaffMaster>> GetStaffByStatusAsync(string status);
            Task<List<StaffMaster>> GetStaffByNameContainingAsync(string staffName);
            Task<List<StaffMaster>> GetStaffByEmailContainingAsync(string email);
            Task<long> CountStaffByDepartmentAsync(string department);
            Task<long> CountStaffByDesignationAsync(string designation);

            // UPDATE OPERATIONS
            Task<StaffMaster> UpdateStaffAsync(int staffId, StaffMaster staffDetails);
            Task<StaffMaster> UpdateStaffNameAsync(int staffId, string staffName);
            Task<StaffMaster> UpdateStaffEmailAsync(int staffId, string email);
            Task<StaffMaster> UpdateStaffMobileAsync(int staffId, long mobile);
            Task<StaffMaster> UpdateStaffDepartmentAsync(int staffId, string department);
            Task<StaffMaster> UpdateStaffDesignationAsync(int staffId, string designation);
            Task<StaffMaster> UpdateStaffStatusAsync(int staffId, string status);

            // DELETE OPERATIONS
            Task DeleteStaffAsync(int staffId);
            Task DeleteStaffByDepartmentAsync(string department);
            Task DeleteStaffByDesignationAsync(string designation);
            Task DeleteStaffByStatusAsync(string status);
            Task DeleteAllStaffAsync();

            // BUSINESS LOGIC OPERATIONS
            Task<List<StaffMaster>> SearchStaffAsync(string searchTerm);
            Task<List<StaffMaster>> GetStaffByDepartmentAndDesignationAsync(string department, string designation);
            Task<List<StaffMaster>> GetStaffWithPaginationAsync(int page, int size);
            Task<List<StaffMaster>> GetStaffSortedByNameAsync();
            Task<List<StaffMaster>> GetStaffSortedByDepartmentAsync();
            Task<List<StaffMaster>> GetStaffSortedByDesignationAsync();
            Task<StaffMaster?> AuthenticateStaffAsync(string username, string password);
            Task<List<StaffMaster>> GetActiveStaffAsync();
            Task<List<StaffMaster>> GetInactiveStaffAsync();
            Task<List<StaffMaster>> GetStaffByEmailDomainAsync(string domain);
        }
    }

