using DotNetRest.Data;
using DotNetRest.Models;
using Microsoft.EntityFrameworkCore;

namespace DotNetRest.Service
{
    public class PaymentWithTypeService : IPaymentWithTypeService
    {
        private readonly ApplicationDbContext _context;

        public PaymentWithTypeService(ApplicationDbContext context)
        {
            _context = context;
        }

        // CREATE OPERATIONS
        public async Task<PaymentWithType> SavePaymentWithTypeAsync(PaymentWithType paymentWithType)
        {
            paymentWithType.CreatedDate ??= DateTime.Now;
            paymentWithType.UpdatedDate ??= DateTime.Now;
            _context.PaymentWithTypes.Add(paymentWithType);
            await _context.SaveChangesAsync();
            return paymentWithType;
        }

        public async Task<List<PaymentWithType>> SaveAllPaymentsWithTypeAsync(List<PaymentWithType> payments)
        {
            foreach (var payment in payments)
            {
                payment.CreatedDate ??= DateTime.Now;
                payment.UpdatedDate ??= DateTime.Now;
            }
            _context.PaymentWithTypes.AddRange(payments);
            await _context.SaveChangesAsync();
            return payments;
        }

        // READ OPERATIONS
        public async Task<List<PaymentWithType>> GetAllPaymentsWithTypeAsync()
        {
            return await _context.PaymentWithTypes.ToListAsync();
        }

        public async Task<PaymentWithType?> GetPaymentWithTypeByIdAsync(int paymentId)
        {
            return await _context.PaymentWithTypes.FindAsync(paymentId);
        }

        public async Task<List<PaymentWithType>> GetPaymentsByStudentIdAsync(int studentId)
        {
            return await _context.PaymentWithTypes
                .Where(p => p.StudentId == studentId)
                .ToListAsync();
        }
    }
}