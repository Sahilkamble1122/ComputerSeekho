namespace DotNetRest.Models
{
    public class EmailSettings
    {
        public string SmtpServer { get; set; } = string.Empty;
        public int SmtpPort { get; set; }
        public string SenderEmail { get; set; } = string.Empty;
        public string SenderPassword { get; set; } = string.Empty;
        public string SenderName { get; set; } = string.Empty;
        public bool EnableSsl { get; set; } = true;
        public string AdminEmail { get; set; } = string.Empty;
        public bool UseStartTls { get; set; } = true;
        public bool RequireAuthentication { get; set; } = true;
    }
}
