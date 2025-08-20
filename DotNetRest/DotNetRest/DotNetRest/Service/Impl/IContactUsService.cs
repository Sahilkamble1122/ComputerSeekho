using DotNetRest.Models;

namespace DotNetRest.Service.Impl
{
    public interface IContactUsService
    {
        Task<ContactUs> SaveContactAsync(ContactUs contactUs);

        // READ OPERATIONS
        Task<IEnumerable<ContactUs>> GetAllContactsAsync();
        Task<ContactUs?> GetContactByIdAsync(int id);

        // UPDATE OPERATIONS
        Task<ContactUs> UpdateContactAsync(int id, ContactUs contactUs);

        // DELETE OPERATIONS
        Task DeleteContactAsync(int id);
    }
}
