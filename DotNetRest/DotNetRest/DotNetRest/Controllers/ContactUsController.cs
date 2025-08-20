using DotNetRest.Models;
using DotNetRest.Service;
using DotNetRest.Service.Impl;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace DotNetRest.Controllers
{
    [ApiController]
    [Route("api/contactus")]
    [Produces("application/json")]
    public class ContactUsController : ControllerBase
    {
        private readonly IContactUsService _contactUsService;

        public ContactUsController(IContactUsService contactUsService)
        {
            _contactUsService = contactUsService;
        }

        // GET: api/contactus
        [HttpGet]
        public async Task<ActionResult<IEnumerable<ContactUs>>> GetAllContacts()
        {
            var contacts = await _contactUsService.GetAllContactsAsync();
            return Ok(contacts);
        }

        // GET: api/contactus/{id}
        [HttpGet("{id}")]
        public async Task<ActionResult<ContactUs>> GetContactById(int id)
        {
            var contact = await _contactUsService.GetContactByIdAsync(id);
            if (contact == null)
                return NotFound();
            return Ok(contact);
        }

        // POST: api/contactus
        [HttpPost]
        public async Task<ActionResult<ContactUs>> AddContact([FromBody] ContactUs contactUs)
        {
            try
            {
                if (contactUs == null)
                {
                    return BadRequest(new { message = "Contact data is required", error = "Request body is null" });
                }

                // Validate required fields
                if (string.IsNullOrWhiteSpace(contactUs.Name))
                {
                    return BadRequest(new { message = "Name is required", error = "Name field cannot be empty" });
                }

                if (string.IsNullOrWhiteSpace(contactUs.Email))
                {
                    return BadRequest(new { message = "Email is required", error = "Email field cannot be empty" });
                }

                if (string.IsNullOrWhiteSpace(contactUs.Message))
                {
                    return BadRequest(new { message = "Message is required", error = "Message field cannot be empty" });
                }

                var savedContact = await _contactUsService.SaveContactAsync(contactUs);
                return CreatedAtAction(nameof(GetContactById), new { id = savedContact.ContactId }, savedContact);
            }
            catch (Exception ex)
            {
                return BadRequest(new { message = "Error processing contact request", error = ex.Message });
            }
        }

        // POST: api/contactus/add
        [HttpPost("add")]
        public async Task<ActionResult<ContactUs>> AddContactViaAdd([FromBody] ContactUs contactUs)
        {
            try
            {
                Console.WriteLine("=== CONTACT FORM SUBMITTED ===");
                Console.WriteLine($"Name: {contactUs?.Name}");
                Console.WriteLine($"Email: {contactUs?.Email}");
                Console.WriteLine($"Phone: {contactUs?.Number}");
                Console.WriteLine($"Message: {contactUs?.Message}");
                
                if (contactUs == null)
                {
                    Console.WriteLine("ERROR: Contact data is null");
                    return BadRequest(new { message = "Contact data is required", error = "Request body is null" });
                }

                // Validate required fields
                if (string.IsNullOrWhiteSpace(contactUs.Name))
                {
                    Console.WriteLine("ERROR: Name is empty");
                    return BadRequest(new { message = "Name is required", error = "Name field cannot be empty" });
                }

                if (string.IsNullOrWhiteSpace(contactUs.Email))
                {
                    Console.WriteLine("ERROR: Email is empty");
                    return BadRequest(new { message = "Email is required", error = "Email field cannot be empty" });
                }

                if (string.IsNullOrWhiteSpace(contactUs.Message))
                {
                    Console.WriteLine("ERROR: Message is empty");
                    return BadRequest(new { message = "Message is required", error = "Message field cannot be empty" });
                }

                Console.WriteLine("Saving contact to database...");
                var savedContact = await _contactUsService.SaveContactAsync(contactUs);
                Console.WriteLine($"Contact saved with ID: {savedContact.ContactId}");
                Console.WriteLine("=== CONTACT FORM PROCESSED SUCCESSFULLY ===");
                
                return CreatedAtAction(nameof(GetContactById), new { id = savedContact.ContactId }, savedContact);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"ERROR in contact form: {ex.Message}");
                return BadRequest(new { message = "Error processing contact request", error = ex.Message });
            }
        }

        // PUT: api/contactus/{id}
        [HttpPut("{id}")]
        public async Task<ActionResult<ContactUs>> UpdateContact(int id, [FromBody] ContactUs contactUs)
        {
            try
            {
                var updatedContact = await _contactUsService.UpdateContactAsync(id, contactUs);
                return Ok(updatedContact);
            }
            catch (InvalidOperationException)
            {
                return NotFound();
            }
        }

        // DELETE: api/contactus/{id}
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteContact(int id)
        {
            try
            {
                await _contactUsService.DeleteContactAsync(id);
                return NoContent();
            }
            catch (InvalidOperationException)
            {
                return NotFound();
            }
        }
    }
}

