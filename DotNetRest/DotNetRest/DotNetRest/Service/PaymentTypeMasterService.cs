using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using DotNetRest.Data;
using DotNetRest.Models;
using DotNetRest.Service;

namespace DotNetRest.Service.Impl
    {
        public class PaymentTypeMasterService : IPaymentTypeMasterService
        {
            private readonly ApplicationDbContext _context;

            public PaymentTypeMasterService(ApplicationDbContext context)
            {
                _context = context;
            }

            // CREATE OPERATIONS
            public async Task<PaymentTypeMaster> SavePaymentTypeAsync(PaymentTypeMaster paymentType)
            {
                _context.PaymentTypeMasters.Add(paymentType);
                await _context.SaveChangesAsync();
                return paymentType;
            }

            public async Task<List<PaymentTypeMaster>> SaveAllPaymentTypesAsync(List<PaymentTypeMaster> paymentTypes)
            {
                _context.PaymentTypeMasters.AddRange(paymentTypes);
                await _context.SaveChangesAsync();
                return paymentTypes;
            }

            // READ OPERATIONS
            public async Task<List<PaymentTypeMaster>> GetAllPaymentTypesAsync()
            {
                return await _context.PaymentTypeMasters.ToListAsync();
            }

            public async Task<PaymentTypeMaster?> GetPaymentTypeByIdAsync(int paymentTypeId)
            {
                return await _context.PaymentTypeMasters.FindAsync(paymentTypeId);
            }

            public async Task<List<PaymentTypeMaster>> GetPaymentTypesByNameAsync(string paymentTypeName)
            {
                return await _context.PaymentTypeMasters
                    .Where(p => p.PaymentTypeDesc == paymentTypeName)
                    .ToListAsync();
            }

            public async Task<List<PaymentTypeMaster>> GetPaymentTypesByDescriptionAsync(string description)
            {
                return await _context.PaymentTypeMasters
                    .Where(p => p.PaymentTypeDesc == description)
                    .ToListAsync();
            }

            public async Task<List<PaymentTypeMaster>> GetPaymentTypesByStatusAsync(string status)
            {
                // Assuming no status field, returning all for now
                return await _context.PaymentTypeMasters.ToListAsync();
            }

            public async Task<List<PaymentTypeMaster>> GetPaymentTypesByNameContainingAsync(string paymentTypeName)
            {
                return await _context.PaymentTypeMasters
                    .Where(p => EF.Functions.Like(p.PaymentTypeDesc, $"%{paymentTypeName}%"))
                    .ToListAsync();
            }

            public async Task<List<PaymentTypeMaster>> GetActivePaymentTypesAsync()
            {
                // No active field, returning all
                return await _context.PaymentTypeMasters.ToListAsync();
            }

            public async Task<long> CountActivePaymentTypesAsync()
            {
                // No active field, count all
                return await _context.PaymentTypeMasters.LongCountAsync();
            }

            // UPDATE OPERATIONS
            public async Task<PaymentTypeMaster> UpdatePaymentTypeAsync(int paymentTypeId, PaymentTypeMaster paymentTypeDetails)
            {
                var existingPaymentType = await _context.PaymentTypeMasters.FindAsync(paymentTypeId);
                if (existingPaymentType == null)
                    throw new KeyNotFoundException($"Payment type not found with id: {paymentTypeId}");

                existingPaymentType.PaymentTypeDesc = paymentTypeDetails.PaymentTypeDesc;
                existingPaymentType.UpdatedDate = DateTime.Now;

                await _context.SaveChangesAsync();
                return existingPaymentType;
            }

            public async Task<PaymentTypeMaster> UpdatePaymentTypeNameAsync(int paymentTypeId, string paymentTypeName)
            {
                var paymentType = await _context.PaymentTypeMasters.FindAsync(paymentTypeId);
                if (paymentType == null)
                    throw new KeyNotFoundException($"Payment type not found with id: {paymentTypeId}");

                paymentType.PaymentTypeDesc = paymentTypeName;
                paymentType.UpdatedDate = DateTime.Now;

                await _context.SaveChangesAsync();
                return paymentType;
            }

            public async Task<PaymentTypeMaster> UpdatePaymentTypeDescriptionAsync(int paymentTypeId, string description)
            {
                var paymentType = await _context.PaymentTypeMasters.FindAsync(paymentTypeId);
                if (paymentType == null)
                    throw new KeyNotFoundException($"Payment type not found with id: {paymentTypeId}");

                paymentType.PaymentTypeDesc = description;
                paymentType.UpdatedDate = DateTime.Now;

                await _context.SaveChangesAsync();
                return paymentType;
            }

            public async Task<PaymentTypeMaster> UpdatePaymentTypeStatusAsync(int paymentTypeId, string status)
            {
                var paymentType = await _context.PaymentTypeMasters.FindAsync(paymentTypeId);
                if (paymentType == null)
                    throw new KeyNotFoundException($"Payment type not found with id: {paymentTypeId}");

                // No status field, updating description for now
                paymentType.PaymentTypeDesc = status;
                paymentType.UpdatedDate = DateTime.Now;

                await _context.SaveChangesAsync();
                return paymentType;
            }

            // DELETE OPERATIONS
            public async Task DeletePaymentTypeAsync(int paymentTypeId)
            {
                var paymentType = await _context.PaymentTypeMasters.FindAsync(paymentTypeId);
                if (paymentType == null)
                    throw new KeyNotFoundException($"Payment type not found with id: {paymentTypeId}");

                _context.PaymentTypeMasters.Remove(paymentType);
                await _context.SaveChangesAsync();
            }

            public async Task DeletePaymentTypesByStatusAsync(string status)
            {
                // No status field, delete all
                _context.PaymentTypeMasters.RemoveRange(_context.PaymentTypeMasters);
                await _context.SaveChangesAsync();
            }

            public async Task DeleteInactivePaymentTypesAsync()
            {
                // No active field, delete all
                _context.PaymentTypeMasters.RemoveRange(_context.PaymentTypeMasters);
                await _context.SaveChangesAsync();
            }

            public async Task DeleteAllPaymentTypesAsync()
            {
                _context.PaymentTypeMasters.RemoveRange(_context.PaymentTypeMasters);
                await _context.SaveChangesAsync();
            }

            // BUSINESS LOGIC OPERATIONS
            public async Task<List<PaymentTypeMaster>> SearchPaymentTypesAsync(string searchTerm)
            {
                return await _context.PaymentTypeMasters
                    .Where(p => EF.Functions.Like(p.PaymentTypeDesc, $"%{searchTerm}%"))
                    .ToListAsync();
            }

            public async Task<List<PaymentTypeMaster>> GetPaymentTypesWithPaginationAsync(int page, int size)
            {
                return await _context.PaymentTypeMasters
                    .Skip(page * size)
                    .Take(size)
                    .ToListAsync();
            }

            public async Task<List<PaymentTypeMaster>> GetPaymentTypesSortedByNameAsync()
            {
                return await _context.PaymentTypeMasters
                    .OrderBy(p => p.PaymentTypeDesc)
                    .ToListAsync();
            }

            public async Task<List<PaymentTypeMaster>> GetPaymentTypesSortedByStatusAsync()
            {
                // No status field, sorting by description
                return await _context.PaymentTypeMasters
                    .OrderBy(p => p.PaymentTypeDesc)
                    .ToListAsync();
            }

            public async Task<List<PaymentTypeMaster>> GetPopularPaymentTypesAsync()
            {
                // For now, return all
                return await _context.PaymentTypeMasters.ToListAsync();
            }
        }
    }
