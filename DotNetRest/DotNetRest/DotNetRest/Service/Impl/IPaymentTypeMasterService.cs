using System.Collections.Generic;
using System.Threading.Tasks;
using DotNetRest.Models;

namespace DotNetRest.Service
    {
        public interface IPaymentTypeMasterService
        {
            // CREATE OPERATIONS
            Task<PaymentTypeMaster> SavePaymentTypeAsync(PaymentTypeMaster paymentType);
            Task<List<PaymentTypeMaster>> SaveAllPaymentTypesAsync(List<PaymentTypeMaster> paymentTypes);

            // READ OPERATIONS
            Task<List<PaymentTypeMaster>> GetAllPaymentTypesAsync();
            Task<PaymentTypeMaster?> GetPaymentTypeByIdAsync(int paymentTypeId);
            Task<List<PaymentTypeMaster>> GetPaymentTypesByNameAsync(string paymentTypeName);
            Task<List<PaymentTypeMaster>> GetPaymentTypesByDescriptionAsync(string description);
            Task<List<PaymentTypeMaster>> GetPaymentTypesByStatusAsync(string status);
            Task<List<PaymentTypeMaster>> GetPaymentTypesByNameContainingAsync(string paymentTypeName);
            Task<List<PaymentTypeMaster>> GetActivePaymentTypesAsync();
            Task<long> CountActivePaymentTypesAsync();

            // UPDATE OPERATIONS
            Task<PaymentTypeMaster> UpdatePaymentTypeAsync(int paymentTypeId, PaymentTypeMaster paymentTypeDetails);
            Task<PaymentTypeMaster> UpdatePaymentTypeNameAsync(int paymentTypeId, string paymentTypeName);
            Task<PaymentTypeMaster> UpdatePaymentTypeDescriptionAsync(int paymentTypeId, string description);
            Task<PaymentTypeMaster> UpdatePaymentTypeStatusAsync(int paymentTypeId, string status);

            // DELETE OPERATIONS
            Task DeletePaymentTypeAsync(int paymentTypeId);
            Task DeletePaymentTypesByStatusAsync(string status);
            Task DeleteInactivePaymentTypesAsync();
            Task DeleteAllPaymentTypesAsync();

            // BUSINESS LOGIC OPERATIONS
            Task<List<PaymentTypeMaster>> SearchPaymentTypesAsync(string searchTerm);
            Task<List<PaymentTypeMaster>> GetPaymentTypesWithPaginationAsync(int page, int size);
            Task<List<PaymentTypeMaster>> GetPaymentTypesSortedByNameAsync();
            Task<List<PaymentTypeMaster>> GetPaymentTypesSortedByStatusAsync();
            Task<List<PaymentTypeMaster>> GetPopularPaymentTypesAsync();
        }
    }

