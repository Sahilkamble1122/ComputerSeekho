using DotNetRest.Data;
using DotNetRest.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DotNetRest.Service
{
    public class ReceiptService : IReceiptService
    {
        private readonly ApplicationDbContext _context;

        public ReceiptService(ApplicationDbContext context)
        {
            _context = context;
        }

        // ==============================
        // CREATE OPERATIONS
        // ==============================
        public async Task<Receipt> SaveReceiptAsync(Receipt receipt)
        {
            if (receipt.CreatedDate == null)
            {
                receipt.CreatedDate = DateTime.Now;
            }
            if (receipt.UpdatedDate == null)
            {
                receipt.UpdatedDate = DateTime.Now;
            }
            _context.Receipts.Add(receipt);
            await _context.SaveChangesAsync();
            return receipt;
        }

        public async Task<List<Receipt>> SaveAllReceiptsAsync(List<Receipt> receipts)
        {
            foreach (var receipt in receipts)
            {
                if (receipt.CreatedDate == null)
                {
                    receipt.CreatedDate = DateTime.Now;
                }
                if (receipt.UpdatedDate == null)
                {
                    receipt.UpdatedDate = DateTime.Now;
                }
            }
            _context.Receipts.AddRange(receipts);
            await _context.SaveChangesAsync();
            return receipts;
        }

        // ==============================
        // READ OPERATIONS
        // ==============================
        public async Task<List<Receipt>> GetAllReceiptsAsync()
        {
            return await _context.Receipts.ToListAsync();
        }

        public async Task<Receipt?> GetReceiptByIdAsync(int id)
        {
            return await _context.Receipts.FindAsync(id);
        }

        public async Task<List<Receipt>> GetReceiptsByPaymentIdAsync(int paymentId)
        {
            return await _context.Receipts
                .Where(r => r.PaymentId == paymentId)
                .ToListAsync();
        }

        public async Task<List<Receipt>> GetReceiptsByAmountRangeAsync(double minAmount, double maxAmount)
        {
            return await _context.Receipts
                .Where(r => r.ReceiptAmount >= minAmount && r.ReceiptAmount <= maxAmount)
                .ToListAsync();
        }

        public async Task<List<Receipt>> GetReceiptsByDateRangeAsync(DateTime startDate, DateTime endDate)
        {
            return await _context.Receipts
                .Where(r => r.ReceiptDate >= startDate && r.ReceiptDate <= endDate)
                .ToListAsync();
        }

        // ==============================
        // UPDATE OPERATIONS
        // ==============================
        public async Task<Receipt> UpdateReceiptAsync(int id, Receipt receipt)
        {
            var existingReceipt = await _context.Receipts.FindAsync(id);
            if (existingReceipt == null)
                throw new Exception($"Receipt not found with id: {id}");

            existingReceipt.ReceiptNumber = receipt.ReceiptNumber;
            existingReceipt.ReceiptDate = receipt.ReceiptDate;
            existingReceipt.ReceiptAmount = receipt.ReceiptAmount;
            existingReceipt.PaymentId = receipt.PaymentId;
            existingReceipt.UpdatedDate = DateTime.Now;

            await _context.SaveChangesAsync();
            return existingReceipt;
        }

        public async Task<Receipt> UpdateReceiptAmountAsync(int id, double amount)
        {
            var existingReceipt = await _context.Receipts.FindAsync(id);
            if (existingReceipt == null)
                throw new Exception($"Receipt not found with id: {id}");

            existingReceipt.ReceiptAmount = amount;
            existingReceipt.UpdatedDate = DateTime.Now;
            await _context.SaveChangesAsync();
            return existingReceipt;
        }

        public async Task<Receipt> UpdateReceiptNumberAsync(int id, string receiptNumber)
        {
            var existingReceipt = await _context.Receipts.FindAsync(id);
            if (existingReceipt == null)
                throw new Exception($"Receipt not found with id: {id}");

            existingReceipt.ReceiptNumber = receiptNumber;
            existingReceipt.UpdatedDate = DateTime.Now;
            await _context.SaveChangesAsync();
            return existingReceipt;
        }

        // ==============================
        // DELETE OPERATIONS
        // ==============================
        public async Task DeleteReceiptAsync(int id)
        {
            var receipt = await _context.Receipts.FindAsync(id);
            if (receipt == null)
                throw new Exception($"Receipt not found with id: {id}");

            _context.Receipts.Remove(receipt);
            await _context.SaveChangesAsync();
        }

        public async Task DeleteReceiptsByPaymentIdAsync(int paymentId)
        {
            var receipts = await _context.Receipts
                .Where(r => r.PaymentId == paymentId)
                .ToListAsync();

            _context.Receipts.RemoveRange(receipts);
            await _context.SaveChangesAsync();
        }

        // ==============================
        // BUSINESS LOGIC OPERATIONS
        // ==============================
        public async Task<double> GetTotalAmountByPaymentIdAsync(int paymentId)
        {
            return await _context.Receipts
                .Where(r => r.PaymentId == paymentId)
                .SumAsync(r => r.ReceiptAmount ?? 0);
        }

        public async Task<long> CountReceiptsByPaymentIdAsync(int paymentId)
        {
            return await _context.Receipts
                .LongCountAsync(r => r.PaymentId == paymentId);
        }

        public async Task<double> GetTotalAmountByDateRangeAsync(DateTime startDate, DateTime endDate)
        {
            return await _context.Receipts
                .Where(r => r.ReceiptDate >= startDate && r.ReceiptDate <= endDate)
                .SumAsync(r => r.ReceiptAmount ?? 0);
        }

        public async Task<long> CountReceiptsByDateRangeAsync(DateTime startDate, DateTime endDate)
        {
            return await _context.Receipts
                .LongCountAsync(r => r.ReceiptDate >= startDate && r.ReceiptDate <= endDate);
        }
    }
}
