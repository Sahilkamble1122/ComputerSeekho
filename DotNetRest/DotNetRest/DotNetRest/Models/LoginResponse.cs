namespace DotNetRest.Models
{
    public class LoginResponse
    {
        public string Token { get; set; } = string.Empty;
        public string RefreshToken { get; set; } = string.Empty;
        public string TokenType { get; set; } = "Bearer";
        public string Username { get; set; } = string.Empty;
        public string Role { get; set; } = string.Empty;
        public long ExpiresIn { get; set; } = 86400000; // 24 hours in milliseconds
        public string Name { get; set; } = string.Empty;
        public string ImgPath { get; set; } = string.Empty;
    }
}
