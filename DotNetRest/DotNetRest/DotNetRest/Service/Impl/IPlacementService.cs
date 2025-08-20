using DotNetRest.Models;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace DotNetRest.Service
    {
        public interface IPlacementService
        {
            // ===========================================
            // CREATE OPERATIONS
            // ===========================================
            Task<Placement> SavePlacementAsync(Placement placement);
            Task<List<Placement>> SaveAllPlacementsAsync(List<Placement> placements);

            // ===========================================
            // READ OPERATIONS
            // ===========================================
            Task<List<Placement>> GetAllPlacementsAsync();
            Task<Placement?> GetPlacementByIdAsync(int placementId);
            Task<List<Placement>> GetPlacementsByStudentIdAsync(int studentId);
            Task<List<Placement>> GetPlacementsByCompanyNameAsync(string companyName);
            Task<List<Placement>> GetPlacementsByPositionAsync(string position);
            Task<List<Placement>> GetPlacementsByStatusAsync(string status);
            Task<List<Placement>> GetPlacementsBySalaryRangeAsync(double minSalary, double maxSalary);
            Task<List<Placement>> GetPlacementsByDateRangeAsync(DateTime startDate, DateTime endDate);
            Task<long> CountPlacementsByStudentIdAsync(int studentId);
            Task<long> CountPlacementsByStatusAsync(string status);

            // ===========================================
            // UPDATE OPERATIONS
            // ===========================================
            Task<Placement> UpdatePlacementAsync(int placementId, Placement placementDetails);
            Task<Placement> UpdatePlacementStatusAsync(int placementId, string status);
            Task<Placement> UpdatePlacementSalaryAsync(int placementId, double salary);
            Task<Placement> UpdatePlacementCompanyAsync(int placementId, string companyName);
            Task<Placement> UpdatePlacementPositionAsync(int placementId, string position);
            Task<Placement> UpdatePlacementIsPlacedAsync(int placementId, bool isPlaced);

            // ===========================================
            // DELETE OPERATIONS
            // ===========================================
            Task DeletePlacementAsync(int placementId);
            Task DeletePlacementsByStudentIdAsync(int studentId);
            Task DeletePlacementsByStatusAsync(string status);
            Task DeleteAllPlacementsAsync();

            // ===========================================
            // BUSINESS LOGIC OPERATIONS
            // ===========================================
            Task<List<Placement>> SearchPlacementsAsync(string searchTerm);
            Task<List<Placement>> GetPlacementsByCompanyAndPositionAsync(string companyName, string position);
            Task<List<Placement>> GetPlacementsWithPaginationAsync(int page, int size);
            Task<List<Placement>> GetPlacementsSortedBySalaryAsync();
            Task<List<Placement>> GetPlacementsSortedByDateAsync();
            Task<List<Placement>> GetPlacementsSortedByCompanyAsync();
            Task<List<Placement>> GetSuccessfulPlacementsAsync();
            Task<List<Placement>> GetPendingPlacementsAsync();
            Task<List<Placement>> GetFailedPlacementsAsync();
            Task<double> GetAverageSalaryByCompanyAsync(string companyName);
            Task<double> GetTotalSalaryByStudentIdAsync(int studentId);
        }
    }


