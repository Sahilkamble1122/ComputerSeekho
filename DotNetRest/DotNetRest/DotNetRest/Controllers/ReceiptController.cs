using Microsoft.AspNetCore.Mvc;
using DotNetRest.Models;
using DotNetRest.Service;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace DotNetRest.Controllers
{
    [Route("api/receipts")]
    [ApiController]
    public class ReceiptController : ControllerBase
    {
        private readonly IReceiptService _receiptService;

        public ReceiptController(IReceiptService receiptService)
        {
            _receiptService = receiptService;
        }

        // ==============================
        // CREATE OPERATIONS
        // ==============================
        [HttpPost]
        public async Task<ActionResult<Receipt>> createReceipt([FromBody] Receipt receipt)
        {
            try
            {
                var savedReceipt = await _receiptService.SaveReceiptAsync(receipt);
                return CreatedAtAction(nameof(getReceiptById), new { id = savedReceipt.ReceiptId }, savedReceipt);
            }
            catch
            {
                return BadRequest();
            }
        }

        [HttpPost("add")]
        public async Task<ActionResult<Receipt>> addReceipt([FromBody] Receipt receipt)
        {
            try
            {
                var savedReceipt = await _receiptService.SaveReceiptAsync(receipt);
                return CreatedAtAction(nameof(getReceiptById), new { id = savedReceipt.ReceiptId }, savedReceipt);
            }
            catch
            {
                return BadRequest();
            }
        }

        [HttpPost("bulk")]
        public async Task<ActionResult<List<Receipt>>> createMultipleReceipts([FromBody] List<Receipt> receipts)
        {
            try
            {
                var savedReceipts = await _receiptService.SaveAllReceiptsAsync(receipts);
                return Created("", savedReceipts);
            }
            catch
            {
                return BadRequest();
            }
        }

        // ==============================
        // READ OPERATIONS
        // ==============================
        [HttpGet]
        public async Task<ActionResult<List<Receipt>>> getAllReceipts()
        {
            try
            {
                var receipts = await _receiptService.GetAllReceiptsAsync();
                return Ok(receipts);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("all")]
        public async Task<ActionResult<List<Receipt>>> getAllReceiptsAlternative()
        {
            try
            {
                var receipts = await _receiptService.GetAllReceiptsAsync();
                return Ok(receipts);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Receipt>> getReceiptById(int id)
        {
            try
            {
                var receipt = await _receiptService.GetReceiptByIdAsync(id);
                if (receipt == null) return NotFound();
                return Ok(receipt);
            }
            catch
            {
                return NotFound();
            }
        }

        // ==============================
        // UPDATE OPERATIONS
        // ==============================
        [HttpPut("{id}")]
        public async Task<ActionResult<Receipt>> updateReceipt(int id, [FromBody] Receipt receipt)
        {
            try
            {
                var updatedReceipt = await _receiptService.UpdateReceiptAsync(id, receipt);
                return Ok(updatedReceipt);
            }
            catch (Exception e) when (e.Message.Contains("not found"))
            {
                return NotFound();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpPut("update/{id}")]
        public async Task<ActionResult<Receipt>> updateReceiptAlternative(int id, [FromBody] Receipt receipt)
        {
            try
            {
                var updatedReceipt = await _receiptService.UpdateReceiptAsync(id, receipt);
                return Ok(updatedReceipt);
            }
            catch (Exception e) when (e.Message.Contains("not found"))
            {
                return NotFound();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        // ==============================
        // DELETE OPERATIONS
        // ==============================
        [HttpDelete("{id}")]
        public async Task<IActionResult> deleteReceipt(int id)
        {
            try
            {
                await _receiptService.DeleteReceiptAsync(id);
                return NoContent();
            }
            catch (Exception e) when (e.Message.Contains("not found"))
            {
                return NotFound();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpDelete("delete/{id}")]
        public async Task<IActionResult> deleteReceiptAlternative(int id)
        {
            try
            {
                await _receiptService.DeleteReceiptAsync(id);
                return NoContent();
            }
            catch (Exception e) when (e.Message.Contains("not found"))
            {
                return NotFound();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        // ==============================
        // BUSINESS LOGIC OPERATIONS
        // ==============================
        [HttpGet("payment/{paymentId}")]
        public async Task<ActionResult<List<Receipt>>> getReceiptsByPaymentId(int paymentId)
        {
            try
            {
                var receipts = await _receiptService.GetReceiptsByPaymentIdAsync(paymentId);
                return Ok(receipts);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("amount-range")]
        public async Task<ActionResult<List<Receipt>>> getReceiptsByAmountRange([FromQuery] double minAmount, [FromQuery] double maxAmount)
        {
            try
            {
                var receipts = await _receiptService.GetReceiptsByAmountRangeAsync(minAmount, maxAmount);
                return Ok(receipts);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("date-range")]
        public async Task<ActionResult<List<Receipt>>> getReceiptsByDateRange([FromQuery] DateTime startDate, [FromQuery] DateTime endDate)
        {
            try
            {
                var receipts = await _receiptService.GetReceiptsByDateRangeAsync(startDate, endDate);
                return Ok(receipts);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("total-amount/payment/{paymentId}")]
        public async Task<ActionResult<double>> getTotalAmountByPaymentId(int paymentId)
        {
            try
            {
                var totalAmount = await _receiptService.GetTotalAmountByPaymentIdAsync(paymentId);
                return Ok(totalAmount);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("count/payment/{paymentId}")]
        public async Task<ActionResult<long>> countReceiptsByPaymentId(int paymentId)
        {
            try
            {
                var count = await _receiptService.CountReceiptsByPaymentIdAsync(paymentId);
                return Ok(count);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("total-amount/date-range")]
        public async Task<ActionResult<double>> getTotalAmountByDateRange([FromQuery] DateTime startDate, [FromQuery] DateTime endDate)
        {
            try
            {
                var totalAmount = await _receiptService.GetTotalAmountByDateRangeAsync(startDate, endDate);
                return Ok(totalAmount);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpGet("count/date-range")]
        public async Task<ActionResult<long>> countReceiptsByDateRange([FromQuery] DateTime startDate, [FromQuery] DateTime endDate)
        {
            try
            {
                var count = await _receiptService.CountReceiptsByDateRangeAsync(startDate, endDate);
                return Ok(count);
            }
            catch
            {
                return StatusCode(500);
            }
        }

        // ==============================
        // PATCH OPERATIONS
        // ==============================
        [HttpPatch("{id}/amount")]
        public async Task<ActionResult<Receipt>> updateReceiptAmount(int id, [FromQuery] double amount)
        {
            try
            {
                var updatedReceipt = await _receiptService.UpdateReceiptAmountAsync(id, amount);
                return Ok(updatedReceipt);
            }
            catch (Exception e) when (e.Message.Contains("not found"))
            {
                return NotFound();
            }
            catch
            {
                return StatusCode(500);
            }
        }

        [HttpPatch("{id}/number")]
        public async Task<ActionResult<Receipt>> updateReceiptNumber(int id, [FromQuery] string receiptNumber)
        {
            try
            {
                var updatedReceipt = await _receiptService.UpdateReceiptNumberAsync(id, receiptNumber);
                return Ok(updatedReceipt);
            }
            catch (Exception e) when (e.Message.Contains("not found"))
            {
                return NotFound();
            }
            catch
            {
                return StatusCode(500);
            }
        }
    }
}


