using DotNetRest.Models;

namespace DotNetRest.Service
{
    public interface IPaymentWithTypeService
    {
        // CREATE OPERATIONS
        Task<PaymentWithType> SavePaymentWithTypeAsync(PaymentWithType paymentWithType);
        Task<List<PaymentWithType>> SaveAllPaymentsWithTypeAsync(List<PaymentWithType> payments);

        // READ OPERATIONS
        Task<List<PaymentWithType>> GetAllPaymentsWithTypeAsync();
        Task<PaymentWithType?> GetPaymentWithTypeByIdAsync(int paymentId);
        Task<List<PaymentWithType>> GetPaymentsByStudentIdAsync(int studentId);
    }
}
