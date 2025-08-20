using Microsoft.AspNetCore.Mvc;
using DotNetRest.Models;
using DotNetRest.Service;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace DotNetRest.Controllers
    {
        [Route("api/payment-types")]
        [ApiController]
        public class PaymentTypeMasterController : ControllerBase
        {
            private readonly IPaymentTypeMasterService _paymentTypeService;

            public PaymentTypeMasterController(IPaymentTypeMasterService paymentTypeService)
            {
                _paymentTypeService = paymentTypeService;
            }

            // ===========================================
            // CREATE OPERATIONS
            // ===========================================
            [HttpPost]
            public async Task<ActionResult<PaymentTypeMaster>> CreatePaymentType([FromBody] PaymentTypeMaster paymentType)
            {
                try
                {
                    var savedPaymentType = await _paymentTypeService.SavePaymentTypeAsync(paymentType);
                    return CreatedAtAction(nameof(GetPaymentTypeById), new { paymentTypeId = savedPaymentType.PaymentTypeId }, savedPaymentType);
                }
                catch
                {
                    return BadRequest();
                }
            }

            [HttpPost("bulk")]
            public async Task<ActionResult<List<PaymentTypeMaster>>> CreateMultiplePaymentTypes([FromBody] List<PaymentTypeMaster> paymentTypes)
            {
                try
                {
                    var savedPaymentTypes = await _paymentTypeService.SaveAllPaymentTypesAsync(paymentTypes);
                    return Created("", savedPaymentTypes);
                }
                catch
                {
                    return BadRequest();
                }
            }

            // ===========================================
            // READ OPERATIONS
            // ===========================================
            [HttpGet]
            public async Task<ActionResult<List<PaymentTypeMaster>>> GetAllPaymentTypes()
            {
                var paymentTypes = await _paymentTypeService.GetAllPaymentTypesAsync();
                return Ok(paymentTypes);
            }

            [HttpGet("{paymentTypeId}")]
            public async Task<ActionResult<PaymentTypeMaster>> GetPaymentTypeById(int paymentTypeId)
            {
                var paymentType = await _paymentTypeService.GetPaymentTypeByIdAsync(paymentTypeId);
                if (paymentType == null) return NotFound();
                return Ok(paymentType);
            }

            [HttpGet("search/name/{paymentTypeName}")]
            public async Task<ActionResult<List<PaymentTypeMaster>>> GetPaymentTypesByName(string paymentTypeName)
            {
                var paymentTypes = await _paymentTypeService.GetPaymentTypesByNameAsync(paymentTypeName);
                return Ok(paymentTypes);
            }

            [HttpGet("search/description/{description}")]
            public async Task<ActionResult<List<PaymentTypeMaster>>> GetPaymentTypesByDescription(string description)
            {
                var paymentTypes = await _paymentTypeService.GetPaymentTypesByDescriptionAsync(description);
                return Ok(paymentTypes);
            }

            [HttpGet("search/status/{status}")]
            public async Task<ActionResult<List<PaymentTypeMaster>>> GetPaymentTypesByStatus(string status)
            {
                var paymentTypes = await _paymentTypeService.GetPaymentTypesByStatusAsync(status);
                return Ok(paymentTypes);
            }

            [HttpGet("search/name-contains/{paymentTypeName}")]
            public async Task<ActionResult<List<PaymentTypeMaster>>> GetPaymentTypesByNameContaining(string paymentTypeName)
            {
                var paymentTypes = await _paymentTypeService.GetPaymentTypesByNameContainingAsync(paymentTypeName);
                return Ok(paymentTypes);
            }

            [HttpGet("active")]
            public async Task<ActionResult<List<PaymentTypeMaster>>> GetActivePaymentTypes()
            {
                var paymentTypes = await _paymentTypeService.GetActivePaymentTypesAsync();
                return Ok(paymentTypes);
            }

            [HttpGet("count/active")]
            public async Task<ActionResult<long>> CountActivePaymentTypes()
            {
                var count = await _paymentTypeService.CountActivePaymentTypesAsync();
                return Ok(count);
            }

            // ===========================================
            // UPDATE OPERATIONS
            // ===========================================
            [HttpPut("{paymentTypeId}")]
            public async Task<ActionResult<PaymentTypeMaster>> UpdatePaymentType(int paymentTypeId, [FromBody] PaymentTypeMaster paymentTypeDetails)
            {
                try
                {
                    var updatedPaymentType = await _paymentTypeService.UpdatePaymentTypeAsync(paymentTypeId, paymentTypeDetails);
                    return Ok(updatedPaymentType);
                }
                catch (KeyNotFoundException)
                {
                    return NotFound();
                }
            }

            [HttpPatch("{paymentTypeId}/name")]
            public async Task<ActionResult<PaymentTypeMaster>> UpdatePaymentTypeName(int paymentTypeId, [FromQuery] string paymentTypeName)
            {
                try
                {
                    var updatedPaymentType = await _paymentTypeService.UpdatePaymentTypeNameAsync(paymentTypeId, paymentTypeName);
                    return Ok(updatedPaymentType);
                }
                catch (KeyNotFoundException)
                {
                    return NotFound();
                }
            }

            [HttpPatch("{paymentTypeId}/description")]
            public async Task<ActionResult<PaymentTypeMaster>> UpdatePaymentTypeDescription(int paymentTypeId, [FromQuery] string description)
            {
                try
                {
                    var updatedPaymentType = await _paymentTypeService.UpdatePaymentTypeDescriptionAsync(paymentTypeId, description);
                    return Ok(updatedPaymentType);
                }
                catch (KeyNotFoundException)
                {
                    return NotFound();
                }
            }

            [HttpPatch("{paymentTypeId}/status")]
            public async Task<ActionResult<PaymentTypeMaster>> UpdatePaymentTypeStatus(int paymentTypeId, [FromQuery] string status)
            {
                try
                {
                    var updatedPaymentType = await _paymentTypeService.UpdatePaymentTypeStatusAsync(paymentTypeId, status);
                    return Ok(updatedPaymentType);
                }
                catch (KeyNotFoundException)
                {
                    return NotFound();
                }
            }

            // ===========================================
            // DELETE OPERATIONS
            // ===========================================
            [HttpDelete("{paymentTypeId}")]
            public async Task<IActionResult> DeletePaymentType(int paymentTypeId)
            {
                try
                {
                    await _paymentTypeService.DeletePaymentTypeAsync(paymentTypeId);
                    return NoContent();
                }
                catch (KeyNotFoundException)
                {
                    return NotFound();
                }
            }

            [HttpDelete("status/{status}")]
            public async Task<IActionResult> DeletePaymentTypesByStatus(string status)
            {
                await _paymentTypeService.DeletePaymentTypesByStatusAsync(status);
                return NoContent();
            }

            [HttpDelete("inactive")]
            public async Task<IActionResult> DeleteInactivePaymentTypes()
            {
                await _paymentTypeService.DeleteInactivePaymentTypesAsync();
                return NoContent();
            }

            [HttpDelete("all")]
            public async Task<IActionResult> DeleteAllPaymentTypes()
            {
                await _paymentTypeService.DeleteAllPaymentTypesAsync();
                return NoContent();
            }

            // ===========================================
            // BUSINESS LOGIC OPERATIONS
            // ===========================================
            [HttpGet("search/global/{searchTerm}")]
            public async Task<ActionResult<List<PaymentTypeMaster>>> SearchPaymentTypes(string searchTerm)
            {
                var paymentTypes = await _paymentTypeService.SearchPaymentTypesAsync(searchTerm);
                return Ok(paymentTypes);
            }

            [HttpGet("pagination")]
            public async Task<ActionResult<List<PaymentTypeMaster>>> GetPaymentTypesWithPagination([FromQuery] int page = 0, [FromQuery] int size = 10)
            {
                var paymentTypes = await _paymentTypeService.GetPaymentTypesWithPaginationAsync(page, size);
                return Ok(paymentTypes);
            }

            [HttpGet("sorted/name")]
            public async Task<ActionResult<List<PaymentTypeMaster>>> GetPaymentTypesSortedByName()
            {
                var paymentTypes = await _paymentTypeService.GetPaymentTypesSortedByNameAsync();
                return Ok(paymentTypes);
            }

            [HttpGet("sorted/status")]
            public async Task<ActionResult<List<PaymentTypeMaster>>> GetPaymentTypesSortedByStatus()
            {
                var paymentTypes = await _paymentTypeService.GetPaymentTypesSortedByStatusAsync();
                return Ok(paymentTypes);
            }

            [HttpGet("popular")]
            public async Task<ActionResult<List<PaymentTypeMaster>>> GetPopularPaymentTypes()
            {
                var paymentTypes = await _paymentTypeService.GetPopularPaymentTypesAsync();
                return Ok(paymentTypes);
            }
        }
    }


