using DotNetRest.Models;
using DotNetRest.Service;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.DependencyInjection;

namespace DotNetRest.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class AuthController : ControllerBase
    {
        private readonly IAuthService _authService;

        public AuthController(IAuthService authService)
        {
            _authService = authService;
        }

        [HttpPost("login")]
        public async Task<ActionResult<LoginResponse>> Login([FromBody] LoginRequest request)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            try
            {
                var response = await _authService.LoginAsync(request);
                return Ok(response);
            }
            catch (InvalidOperationException ex)
            {
                var error = new { error = ex.Message };
                return BadRequest(error);
            }
            catch (Exception)
            {
                var error = new { error = "Authentication failed" };
                return BadRequest(error);
            }
        }

        [HttpPost("refresh")]
        public async Task<ActionResult<LoginResponse>> RefreshToken([FromHeader(Name = "Authorization")] string authorization)
        {
            if (string.IsNullOrEmpty(authorization) || !authorization.StartsWith("Bearer "))
            {
                return Unauthorized(new { message = "Invalid token format" });
            }

            var token = authorization.Substring("Bearer ".Length);
            var jwtService = HttpContext.RequestServices.GetService<IJwtService>();
            
            if (jwtService == null || !jwtService.ValidateToken(token))
            {
                return Unauthorized(new { message = "Invalid refresh token" });
            }

            var username = jwtService.GetUsernameFromToken(token);
            var role = jwtService.GetRoleFromToken(token);

            // For now, return a simple response. In a full implementation, you would:
            // 1. Check if the token is a refresh token
            // 2. Generate new access and refresh tokens
            // 3. Return the new tokens with user info
            return Ok(new { 
                message = "Token refresh endpoint - implementation needed",
                username = username,
                role = role
            });
        }

        [HttpPost("validate")]
        public async Task<ActionResult> ValidateToken([FromHeader(Name = "Authorization")] string authorization)
        {
            if (string.IsNullOrEmpty(authorization) || !authorization.StartsWith("Bearer "))
            {
                return Unauthorized(new { message = "Invalid token format" });
            }

            var token = authorization.Substring("Bearer ".Length);
            var jwtService = HttpContext.RequestServices.GetService<IJwtService>();
            
            if (jwtService == null || !jwtService.ValidateToken(token))
            {
                return Unauthorized(new { message = "Invalid token" });
            }

            var username = jwtService.GetUsernameFromToken(token);
            var role = jwtService.GetRoleFromToken(token);

            return Ok(new { 
                message = "Token is valid",
                username = username,
                role = role
            });
        }

        [HttpOptions("login")]
        public IActionResult LoginOptions()
        {
            return Ok();
        }

        [HttpOptions("validate")]
        public IActionResult ValidateOptions()
        {
            return Ok();
        }

        [HttpOptions("refresh")]
        public IActionResult RefreshOptions()
        {
            return Ok();
        }
    }
}
