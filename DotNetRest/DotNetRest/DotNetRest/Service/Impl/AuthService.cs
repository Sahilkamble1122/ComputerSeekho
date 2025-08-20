using System.Security.Cryptography;
using System.Text;
using DotNetRest.Data;
using DotNetRest.Models;
using Microsoft.EntityFrameworkCore;

namespace DotNetRest.Service.Impl
{
    public class AuthService : IAuthService
    {
        private readonly ApplicationDbContext _context;
        private readonly IJwtService _jwtService;

        public AuthService(ApplicationDbContext context, IJwtService jwtService)
        {
            _context = context;
            _jwtService = jwtService;
        }

        public async Task<LoginResponse> LoginAsync(LoginRequest request)
        {
            // First try to find as student
            var student = await _context.StudentMasters
                .FirstOrDefaultAsync(s => s.StudentUsername == request.Username);

            if (student != null)
            {
                if (await ValidateStudentCredentialsAsync(request.Username, request.Password))
                {
                    var token = _jwtService.GenerateToken(student);
                    var refreshToken = _jwtService.GenerateRefreshToken(student);

                    return new LoginResponse
                    {
                        Token = token,
                        RefreshToken = refreshToken,
                        Username = student.StudentUsername ?? string.Empty,
                        Role = "STUDENT",
                        ExpiresIn = 86400000, // 24 hours in milliseconds
                        Name = student.StudentName ?? string.Empty,
                        ImgPath = student.PhotoUrl ?? string.Empty
                    };
                }
            }

            // Then try to find as staff
            var staff = await _context.StaffMasters
                .FirstOrDefaultAsync(s => s.StaffUsername == request.Username);

            if (staff != null)
            {
                if (await ValidateStaffCredentialsAsync(request.Username, request.Password))
                {
                    var token = _jwtService.GenerateToken(staff);
                    var refreshToken = _jwtService.GenerateRefreshToken(staff);

                    return new LoginResponse
                    {
                        Token = token,
                        RefreshToken = refreshToken,
                        Username = staff.StaffUsername ?? string.Empty,
                        Role = "STAFF",
                        ExpiresIn = 86400000, // 24 hours in milliseconds
                        Name = staff.StaffName ?? string.Empty,
                        ImgPath = staff.PhotoUrl ?? string.Empty
                    };
                }
            }

            throw new InvalidOperationException("Invalid username or password");
        }

        public async Task<bool> ValidateCredentialsAsync(string username, string password)
        {
            // Try student first
            if (await ValidateStudentCredentialsAsync(username, password))
                return true;

            // Try staff
            if (await ValidateStaffCredentialsAsync(username, password))
                return true;

            return false;
        }

        private async Task<bool> ValidateStudentCredentialsAsync(string username, string password)
        {
            var student = await _context.StudentMasters
                .FirstOrDefaultAsync(s => s.StudentUsername == username);

            if (student == null)
                return false;

            // For now, we'll do a simple string comparison
            // In production, you should hash passwords
            return student.StudentPassword == password;
        }

        private async Task<bool> ValidateStaffCredentialsAsync(string username, string password)
        {
            var staff = await _context.StaffMasters
                .FirstOrDefaultAsync(s => s.StaffUsername == username);

            if (staff == null)
                return false;

            // For now, we'll do a simple string comparison
            // In production, you should hash passwords
            return staff.StaffPassword == password;
        }

        private string HashPassword(string password)
        {
            using (var sha256 = SHA256.Create())
            {
                var hashedBytes = sha256.ComputeHash(Encoding.UTF8.GetBytes(password));
                return Convert.ToBase64String(hashedBytes);
            }
        }
    }
}
