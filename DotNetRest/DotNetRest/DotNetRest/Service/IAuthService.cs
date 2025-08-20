using DotNetRest.Models;

namespace DotNetRest.Service
{
    public interface IAuthService
    {
        Task<LoginResponse> LoginAsync(LoginRequest request);
        Task<bool> ValidateCredentialsAsync(string username, string password);
    }
}
