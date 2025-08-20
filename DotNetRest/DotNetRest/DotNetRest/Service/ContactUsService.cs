using DotNetRest.Data;
using DotNetRest.Models;
using DotNetRest.Service.Impl;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace DotNetRest.Service
{
    public class ContactUsService : IContactUsService
    {
        private readonly ApplicationDbContext _context;
        private readonly IEmailService _emailService;
        private readonly ILogger<ContactUsService> _logger;

        public ContactUsService(ApplicationDbContext context, IEmailService emailService, ILogger<ContactUsService> logger)
        {
            _context = context;
            _emailService = emailService;
            _logger = logger;
        }

        // CREATE
        public async Task<ContactUs> SaveContactAsync(ContactUs contactUs)
        {
            if (contactUs == null) throw new ArgumentNullException(nameof(contactUs));

            contactUs.CreatedDate ??= DateTime.Now;
            contactUs.UpdatedDate ??= DateTime.Now;

            await _context.ContactUs.AddAsync(contactUs);
            await _context.SaveChangesAsync();

            // Send email notification
            try
            {
                await _emailService.SendContactUsEmailAsync(contactUs);
                _logger.LogInformation($"Email sent successfully for contact from {contactUs.Name}");
            }
            catch (Exception ex)
            {
                _logger.LogError(ex, $"Failed to send email for contact from {contactUs.Name}");
                // Don't throw exception - email failure shouldn't prevent contact from being saved
            }

            return contactUs;
        }

        // READ
        public async Task<IEnumerable<ContactUs>> GetAllContactsAsync()
        {
            return await _context.ContactUs.ToListAsync();
        }

        public async Task<ContactUs?> GetContactByIdAsync(int id)
        {
            return await _context.ContactUs.FindAsync(id);
        }

        // UPDATE
        public async Task<ContactUs> UpdateContactAsync(int id, ContactUs contactUs)
        {
            var existingContact = await _context.ContactUs.FindAsync(id);
            if (existingContact == null)
                throw new InvalidOperationException($"Contact not found with id: {id}");

            existingContact.Name = contactUs.Name;
            existingContact.Email = contactUs.Email;
            existingContact.Number = contactUs.Number;
            existingContact.Message = contactUs.Message;
            existingContact.UpdatedDate = DateTime.Now;

            await _context.SaveChangesAsync();
            return existingContact;
        }

        // DELETE
        public async Task DeleteContactAsync(int id)
        {
            var contact = await _context.ContactUs.FindAsync(id);
            if (contact == null)
                throw new InvalidOperationException($"Contact not found with id: {id}");

            _context.ContactUs.Remove(contact);
            await _context.SaveChangesAsync();
        }
    }
}
