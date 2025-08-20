using DotNetRest.Data;
using DotNetRest.Models;
using DotNetRest.Attributes;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace DotNetRest.Controllers
{
    [ApiController]
    [Route("api/staff")]
    //[JwtAuthorize] // Protect all endpoints in this controller
    public class StaffMasterController : ControllerBase
    {
        private readonly ApplicationDbContext _context;

        public StaffMasterController(ApplicationDbContext context)
        {
            _context = context;
        }

        // CREATE - Requires Admin role
        [HttpPost]
        //[JwtAuthorize("Admin")]
        public async Task<IActionResult> CreateStaff([FromBody] StaffMaster staff)
        {
            if (staff == null)
                return BadRequest("Staff data is null");

            await _context.StaffMasters.AddAsync(staff);
            await _context.SaveChangesAsync();

            return CreatedAtAction(nameof(GetStaffById), new { staffId = staff.StaffId }, staff);
        }

        // READ - All (requires any authenticated user)
        [HttpGet]
        public async Task<ActionResult<IEnumerable<StaffMaster>>> GetAllStaff()
        {
            var staffList = await _context.StaffMasters.ToListAsync();
            return Ok(staffList);
        }

        // READ - By ID (requires any authenticated user)
        [HttpGet("{staffId}")]
        public async Task<IActionResult> GetStaffById(int staffId)
        {
            var staff = await _context.StaffMasters.FindAsync(staffId);
            if (staff == null)
                return NotFound();

            return Ok(staff);
        }

        // UPDATE - Requires Admin role
        [HttpPut("{staffId}")]
        //[JwtAuthorize("Admin")]
        public async Task<IActionResult> UpdateStaff(int staffId, [FromBody] StaffMaster staffDetails)
        {
            var staff = await _context.StaffMasters.FindAsync(staffId);
            if (staff == null)
                return NotFound();

            // Update fields
            staff.StaffName = staffDetails.StaffName;
            staff.StaffGender = staffDetails.StaffGender;
            
            _context.Entry(staff).State = EntityState.Modified;
            await _context.SaveChangesAsync();

            return Ok(staff);
        }

        // DELETE - Requires Admin role
        [HttpDelete("{staffId}")]
        //[JwtAuthorize("Admin")]
        public async Task<IActionResult> DeleteStaff(int staffId)
        {
            var staff = await _context.StaffMasters.FindAsync(staffId);
            if (staff == null)
                return NotFound();

            _context.StaffMasters.Remove(staff);
            await _context.SaveChangesAsync();

            return NoContent();
        }
    }
}

