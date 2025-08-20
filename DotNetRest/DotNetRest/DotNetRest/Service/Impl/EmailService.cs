using DotNetRest.Models;
using DotNetRest.Service;
using MailKit.Net.Smtp;
using MailKit.Security;
using Microsoft.Extensions.Options;
using MimeKit;

namespace DotNetRest.Service.Impl
{
    public class EmailService : IEmailService
    {
        private readonly EmailSettings _emailSettings;
        private readonly ILogger<EmailService> _logger;

        public EmailService(IOptions<EmailSettings> emailSettings, ILogger<EmailService> logger)
        {
            _emailSettings = emailSettings.Value;
            _logger = logger;
        }

        public async Task<bool> SendContactUsEmailAsync(ContactUs contactUs)
        {
            try
            {
                _logger.LogInformation($"Starting email send for contact from {contactUs.Name}");
                _logger.LogInformation($"Email settings - Server: {_emailSettings.SmtpServer}, Port: {_emailSettings.SmtpPort}, From: {_emailSettings.SenderEmail}, Admin: {_emailSettings.AdminEmail}");
                
                var subject = $"New Contact Us Message from {contactUs.Name}";
                var body = GenerateContactUsEmailBody(contactUs);

                // Send to admin
                _logger.LogInformation($"Sending admin email to: {_emailSettings.AdminEmail}");
                var adminResult = await SendEmailAsync(_emailSettings.AdminEmail, subject, body, true);
                _logger.LogInformation($"Admin email result: {adminResult}");

                // Send confirmation to user
                if (!string.IsNullOrEmpty(contactUs.Email))
                {
                    _logger.LogInformation($"Sending user confirmation email to: {contactUs.Email}");
                    var userSubject = "Thank you for contacting us";
                    var userBody = GenerateUserConfirmationEmailBody(contactUs);
                    var userResult = await SendEmailAsync(contactUs.Email, userSubject, userBody, true);
                    _logger.LogInformation($"User email result: {userResult}");
                }

                return true;
            }
            catch (Exception ex)
            {
                _logger.LogError(ex, "Error sending contact us email");
                return false;
            }
        }

        public async Task<bool> SendEmailAsync(string to, string subject, string body, bool isHtml = false)
        {
            return await SendEmailAsync(to, "", subject, body, isHtml);
        }

        public async Task<bool> SendEmailAsync(string to, string cc, string subject, string body, bool isHtml = false)
        {
            try
            {
                _logger.LogInformation($"Attempting to send email to: {to}");
                _logger.LogInformation($"SMTP Server: {_emailSettings.SmtpServer}:{_emailSettings.SmtpPort}");
                _logger.LogInformation($"From: {_emailSettings.SenderEmail}");
                
                var email = new MimeMessage();
                email.From.Add(new MailboxAddress(_emailSettings.SenderName, _emailSettings.SenderEmail));
                email.To.Add(MailboxAddress.Parse(to));

                if (!string.IsNullOrEmpty(cc))
                {
                    email.Cc.Add(MailboxAddress.Parse(cc));
                }

                email.Subject = subject;

                var builder = new BodyBuilder();
                if (isHtml)
                {
                    builder.HtmlBody = body;
                }
                else
                {
                    builder.TextBody = body;
                }

                email.Body = builder.ToMessageBody();

                _logger.LogInformation("Connecting to SMTP server...");
                using var smtp = new SmtpClient();
                
                // Determine SSL/TLS options
                SecureSocketOptions sslOption;
                if (_emailSettings.UseStartTls)
                {
                    sslOption = SecureSocketOptions.StartTls;
                }
                else if (_emailSettings.EnableSsl)
                {
                    sslOption = SecureSocketOptions.SslOnConnect;
                }
                else
                {
                    sslOption = SecureSocketOptions.None;
                }
                
                try
                {
                    await smtp.ConnectAsync(_emailSettings.SmtpServer, _emailSettings.SmtpPort, sslOption);
                    _logger.LogInformation("SMTP connection successful");
                }
                catch (Exception connectEx)
                {
                    _logger.LogError(connectEx, "SMTP connection failed");
                    throw;
                }
                
                // Check if authentication is required
                if (_emailSettings.RequireAuthentication && smtp.AuthenticationMechanisms.Count > 0)
                {
                    _logger.LogInformation("Authenticating with SMTP server...");
                    try
                    {
                        await smtp.AuthenticateAsync(_emailSettings.SenderEmail, _emailSettings.SenderPassword);
                        _logger.LogInformation("SMTP authentication successful");
                    }
                    catch (Exception authEx)
                    {
                        _logger.LogError(authEx, "SMTP authentication failed");
                        throw;
                    }
                }
                else
                {
                    _logger.LogInformation("No authentication required or no mechanisms available");
                }
                
                _logger.LogInformation("Sending email...");
                try
                {
                    await smtp.SendAsync(email);
                    _logger.LogInformation("Email sent successfully");
                }
                catch (Exception sendEx)
                {
                    _logger.LogError(sendEx, "Email sending failed");
                    throw;
                }
                
                _logger.LogInformation("Disconnecting from SMTP server...");
                await smtp.DisconnectAsync(true);
                _logger.LogInformation($"Email sent successfully to {to}");
                return true;
            }
            catch (Exception ex)
            {
                _logger.LogError(ex, $"Error sending email to {to}");
                _logger.LogError($"Exception details: {ex.Message}");
                if (ex.InnerException != null)
                {
                    _logger.LogError($"Inner exception: {ex.InnerException.Message}");
                }
                return false;
            }
        }

        private string GenerateContactUsEmailBody(ContactUs contactUs)
        {
            return $@"
                <html>
                <body>
                    <h2>New Contact Us Message</h2>
                    <p><strong>Name:</strong> {contactUs.Name}</p>
                    <p><strong>Email:</strong> {contactUs.Email}</p>
                    <p><strong>Phone:</strong> {contactUs.Number}</p>
                    <p><strong>Message:</strong></p>
                    <p>{contactUs.Message}</p>
                    <p><strong>Submitted on:</strong> {contactUs.CreatedDate:yyyy-MM-dd HH:mm:ss}</p>
                </body>
                </html>";
        }

        private string GenerateUserConfirmationEmailBody(ContactUs contactUs)
        {
            return $@"
                <html>
                <body>
                    <h2>Thank you for contacting us!</h2>
                    <p>Dear {contactUs.Name},</p>
                    <p>We have received your message and will get back to you as soon as possible.</p>
                    <p><strong>Your message:</strong></p>
                    <p>{contactUs.Message}</p>
                    <p>Best regards,<br/>Your Team</p>
                </body>
                </html>";
        }
    }
}
