# Email Setup Guide for ContactUs Feature

This guide explains how to configure and use the email functionality in your .NET REST API project.

## Overview

The email functionality has been integrated into your ContactUs feature. When someone submits a contact form, the system will:

1. Save the contact information to the database
2. Send an email notification to the admin
3. Send a confirmation email to the user (if email is provided)

## Configuration

### 1. Update Email Settings in appsettings.json

Edit your `appsettings.json` file and update the `EmailSettings` section:

```json
{
  "EmailSettings": {
    "SmtpServer": "smtp.gmail.com",
    "SmtpPort": 587,
    "SenderEmail": "your-email@gmail.com",
    "SenderPassword": "your-app-password",
    "SenderName": "Your Company Name",
    "EnableSsl": true,
    "AdminEmail": "admin@yourcompany.com"
  }
}
```

### 2. Gmail Setup (Recommended)

If using Gmail, follow these steps:

1. **Enable 2-Factor Authentication** on your Gmail account
2. **Generate an App Password**:
   - Go to Google Account settings
   - Security → 2-Step Verification → App passwords
   - Generate a new app password for "Mail"
   - Use this password in your `SenderPassword` field

### 3. Other Email Providers

For other providers, use these common settings:

**Outlook/Hotmail:**
```json
{
  "SmtpServer": "smtp-mail.outlook.com",
  "SmtpPort": 587,
  "EnableSsl": true
}
```

**Yahoo:**
```json
{
  "SmtpServer": "smtp.mail.yahoo.com",
  "SmtpPort": 587,
  "EnableSsl": true
}
```

**Custom SMTP Server:**
```json
{
  "SmtpServer": "your-smtp-server.com",
  "SmtpPort": 587,
  "EnableSsl": true
}
```

## Usage

### ContactUs Email Flow

When a user submits a contact form via `POST /api/contactus`, the system automatically:

1. Saves the contact to the database
2. Sends a notification email to the admin with contact details
3. Sends a confirmation email to the user

### Testing Email Functionality

Use the test endpoint to verify your email configuration:

```http
POST /api/email/test
Content-Type: application/json

{
  "to": "test@example.com",
  "subject": "Test Email",
  "body": "This is a test email from your API",
  "isHtml": false
}
```

## API Endpoints

### ContactUs Endpoints

- `POST /api/contactus` - Submit contact form (triggers email)
- `GET /api/contactus` - Get all contacts
- `GET /api/contactus/{id}` - Get specific contact
- `PUT /api/contactus/{id}` - Update contact
- `DELETE /api/contactus/{id}` - Delete contact

### Email Test Endpoint

- `POST /api/email/test` - Test email functionality

## Email Templates

The system uses HTML email templates for better formatting:

### Admin Notification Email
- Subject: "New Contact Us Message from [Name]"
- Contains: Name, Email, Phone, Message, Submission Date

### User Confirmation Email
- Subject: "Thank you for contacting us"
- Contains: Personalized thank you message with their submitted message

## Troubleshooting

### Common Issues

1. **Authentication Failed**
   - Check your email and password
   - For Gmail, ensure you're using an App Password, not your regular password
   - Verify 2-Factor Authentication is enabled (for Gmail)

2. **Connection Timeout**
   - Check your internet connection
   - Verify SMTP server and port settings
   - Check firewall settings

3. **SSL/TLS Issues**
   - Try changing `EnableSsl` to `false` for testing
   - Verify your email provider supports the selected port

### Logging

Email operations are logged. Check your application logs for:
- Successful email sends
- Email failures with error details
- SMTP connection issues

## Security Considerations

1. **Never commit email passwords to source control**
   - Use environment variables or secure configuration management
   - Consider using Azure Key Vault or similar services for production

2. **Use App Passwords**
   - For Gmail, always use App Passwords instead of your main password
   - App Passwords are more secure and can be revoked individually

3. **Environment-Specific Configuration**
   - Use different email settings for development, staging, and production
   - Consider using `appsettings.Development.json` for local development

## Example Environment Variables

For production, consider using environment variables:

```bash
EmailSettings__SenderEmail=your-email@gmail.com
EmailSettings__SenderPassword=your-app-password
EmailSettings__AdminEmail=admin@yourcompany.com
```

## Next Steps

1. Update your `appsettings.json` with your email credentials
2. Test the email functionality using the test endpoint
3. Submit a contact form to verify the complete flow
4. Monitor logs for any issues
5. Customize email templates as needed for your business
