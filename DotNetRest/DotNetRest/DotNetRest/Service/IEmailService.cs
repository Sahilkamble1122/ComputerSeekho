using DotNetRest.Models;

namespace DotNetRest.Service
{
    public interface IEmailService
    {
        Task<bool> SendContactUsEmailAsync(ContactUs contactUs);
        Task<bool> SendEmailAsync(string to, string subject, string body, bool isHtml = false);
        Task<bool> SendEmailAsync(string to, string cc, string subject, string body, bool isHtml = false);
    }
}
