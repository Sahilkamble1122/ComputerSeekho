# Email Troubleshooting Guide

## Current Configuration
Your email is configured to use Gmail SMTP with the following settings:
- **SMTP Server**: smtp.gmail.com
- **Port**: 587
- **Email**: gwaliorkt09@gmail.com
- **Password**: App password (mbgz fgot zpbk wban)
- **SSL**: STARTTLS enabled

## Common Issues and Solutions

### 1. Gmail App Password Issues
**Problem**: Gmail requires an "App Password" for less secure applications, not your regular password.

**Solution**: 
1. Go to your Google Account settings
2. Enable 2-Factor Authentication if not already enabled
3. Generate an App Password specifically for "Mail"
4. Use the 16-character app password (with spaces)

### 2. SSL/TLS Configuration Issues
**Problem**: Gmail requires proper SSL/TLS configuration.

**Solution**: 
- Port 587: Use STARTTLS (recommended)
- Port 465: Use SSL on connect
- Port 25: Not recommended (often blocked)

### 3. Gmail Security Settings
**Problem**: Gmail may block access from less secure apps.

**Solution**:
1. Check if "Less secure app access" is enabled (if using regular password)
2. Ensure 2FA is enabled and use App Password
3. Check Gmail's security alerts for blocked access

## Testing Your Email Configuration

### Step 1: Test Basic Connection
```bash
GET /api/email/test-connection
```
This tests if your application can connect to Gmail's SMTP server.

### Step 2: Test Step-by-Step Process
```bash
GET /api/email/test-step-by-step
```
This tests each part of the email process separately:
- SMTP connection
- Authentication mechanisms
- User authentication
- Email creation
- Email preparation

### Step 3: Test Gmail-Specific Settings
```bash
GET /api/email/test-gmail-specific
```
This validates your Gmail configuration and tests different SSL options.

### Step 4: Test Simple Email
```bash
GET /api/email/test-simple
```
This attempts to send a real test email.

## Debugging Steps

### 1. Check Console Output
Run your application and look for detailed console output that shows:
- Connection attempts
- Authentication steps
- Any error messages

### 2. Check Application Logs
Look for detailed logging information in your application logs.

### 3. Test with Different SSL Options
The application now automatically tests different SSL configurations to find the one that works.

## Common Error Messages and Solutions

### "Authentication failed"
- Verify your app password is correct
- Ensure 2FA is enabled on your Google account
- Check if the app password was generated for "Mail" application

### "Connection failed"
- Check if port 587 is open on your network
- Verify smtp.gmail.com is accessible
- Try different SSL options

### "Mailbox unavailable"
- Verify the recipient email address
- Check if the email domain is valid

## Alternative Solutions

### 1. Use Different Port
Try port 465 with SSL on connect:
```json
{
  "SmtpPort": 465,
  "UseStartTls": false,
  "EnableSsl": true
}
```

### 2. Use Different Email Provider
Consider using:
- Outlook/Hotmail
- Yahoo Mail
- Your own SMTP server

### 3. Check Network/Firewall
Ensure your server/network allows outbound connections to:
- smtp.gmail.com:587
- smtp.gmail.com:465

## Next Steps

1. **Run the step-by-step test** to identify exactly where the issue occurs
2. **Check your Gmail account settings** for app passwords and 2FA
3. **Verify your network connectivity** to Gmail's servers
4. **Check the console output** for detailed error messages

## Support

If you continue to have issues:
1. Run all the test endpoints
2. Copy the console output and error messages
3. Check your Gmail account security settings
4. Verify your app password is correctly generated and used
