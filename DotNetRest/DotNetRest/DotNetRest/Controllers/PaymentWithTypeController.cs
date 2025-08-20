using Microsoft.AspNetCore.Mvc;
using DotNetRest.Models;
using DotNetRest.Service;

namespace DotNetRest.Controllers
{
    [Route("api/payment-with-type")]
    [ApiController]
    public class PaymentWithTypeController : ControllerBase
    {
        private readonly IPaymentWithTypeService _paymentWithTypeService;

        public PaymentWithTypeController(IPaymentWithTypeService paymentWithTypeService)
        {
            _paymentWithTypeService = paymentWithTypeService;
        }

        // CREATE OPERATIONS
        [HttpPost]
        public async Task<ActionResult<PaymentWithType>> createPaymentWithType([FromBody] PaymentWithType paymentWithType)
        {
            try
            {
                var savedPayment = await _paymentWithTypeService.SavePaymentWithTypeAsync(paymentWithType);
                return CreatedAtAction(nameof(getPaymentWithTypeById), new { paymentId = savedPayment.PaymentId }, savedPayment);
            }
            catch
            {
                return BadRequest();
            }
        }

        [HttpPost("bulk")]
        public async Task<ActionResult<List<PaymentWithType>>> createMultiplePaymentsWithType([FromBody] List<PaymentWithType> payments)
        {
            try
            {
                var savedPayments = await _paymentWithTypeService.SaveAllPaymentsWithTypeAsync(payments);
                return Created("", savedPayments);
            }
            catch
            {
                return BadRequest();
            }
        }

        // READ OPERATIONS
        [HttpGet]
        public async Task<ActionResult<List<PaymentWithType>>> getAllPaymentsWithType()
        {
            try
            {
                var payments = await _paymentWithTypeService.GetAllPaymentsWithTypeAsync();
                return Ok(payments);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("{paymentId}")]
        public async Task<ActionResult<PaymentWithType>> getPaymentWithTypeById(int paymentId)
        {
            try
            {
                var payment = await _paymentWithTypeService.GetPaymentWithTypeByIdAsync(paymentId);
                if (payment == null) return NotFound();
                return Ok(payment);
            }
            catch
            {
                return NotFound();
            }
        }

        [HttpGet("student/{studentId}")]
        public async Task<ActionResult<List<PaymentWithType>>> getPaymentsByStudentId(int studentId)
        {
            try
            {
                var payments = await _paymentWithTypeService.GetPaymentsByStudentIdAsync(studentId);
                return Ok(payments);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        // Additional endpoint for your frontend
        [HttpGet("history")]
        public async Task<ActionResult<List<PaymentWithType>>> GetPaymentHistory([FromQuery] int studentId)
        {
            try
            {
                var payments = await _paymentWithTypeService.GetPaymentsByStudentIdAsync(studentId);
                return Ok(payments);
            }
            catch
            {
                return StatusCode(500);
            }
        }
    }
}
