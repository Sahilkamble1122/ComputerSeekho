using DotNetRest.Models;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace DotNetRest.Service
    {
        public interface IReceiptService
        {
            // ===========================================
            // CREATE OPERATIONS
            // ===========================================
            Task<Receipt> SaveReceiptAsync(Receipt receipt);
            Task<List<Receipt>> SaveAllReceiptsAsync(List<Receipt> receipts);

            // ===========================================
            // READ OPERATIONS
            // ===========================================
            Task<List<Receipt>> GetAllReceiptsAsync();
            Task<Receipt?> GetReceiptByIdAsync(int id);
            Task<List<Receipt>> GetReceiptsByPaymentIdAsync(int paymentId);
            Task<List<Receipt>> GetReceiptsByAmountRangeAsync(double minAmount, double maxAmount);
            Task<List<Receipt>> GetReceiptsByDateRangeAsync(DateTime startDate, DateTime endDate);

            // ===========================================
            // UPDATE OPERATIONS
            // ===========================================
            Task<Receipt> UpdateReceiptAsync(int id, Receipt receipt);
            Task<Receipt> UpdateReceiptAmountAsync(int id, double amount);
            Task<Receipt> UpdateReceiptNumberAsync(int id, string receiptNumber);

            // ===========================================
            // DELETE OPERATIONS
            // ===========================================
            Task DeleteReceiptAsync(int id);
            Task DeleteReceiptsByPaymentIdAsync(int paymentId);

            // ===========================================
            // BUSINESS LOGIC OPERATIONS
            // ===========================================
            Task<double> GetTotalAmountByPaymentIdAsync(int paymentId);
            Task<long> CountReceiptsByPaymentIdAsync(int paymentId);
            Task<double> GetTotalAmountByDateRangeAsync(DateTime startDate, DateTime endDate);
            Task<long> CountReceiptsByDateRangeAsync(DateTime startDate, DateTime endDate);
        }
    }

