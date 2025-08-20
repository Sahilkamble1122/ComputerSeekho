using Microsoft.AspNetCore.Authorization;

namespace DotNetRest.Attributes
{
    [AttributeUsage(AttributeTargets.Class | AttributeTargets.Method)]
    public class JwtAuthorizeAttribute : AuthorizeAttribute
    {
        public JwtAuthorizeAttribute()
        {
            AuthenticationSchemes = "Bearer";
        }

        public JwtAuthorizeAttribute(string policy) : base(policy)
        {
            AuthenticationSchemes = "Bearer";
        }

        public JwtAuthorizeAttribute(params string[] roles) : base()
        {
            AuthenticationSchemes = "Bearer";
            Roles = string.Join(",", roles);
        }
    }
}
