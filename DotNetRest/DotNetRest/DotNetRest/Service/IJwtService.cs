using DotNetRest.Models;

namespace DotNetRest.Service
{
    public interface IJwtService
    {
        string GenerateToken(StaffMaster staff);
        string GenerateToken(StudentMaster student);
        string GenerateRefreshToken(StaffMaster staff);
        string GenerateRefreshToken(StudentMaster student);
        bool ValidateToken(string token);
        string GetUsernameFromToken(string token);
        string GetRoleFromToken(string token);
    }
}
