using DotNetRest.Data;
using DotNetRest.Service;
using DotNetRest.Service.Impl;
using DotNetRest.Models;
using Microsoft.AspNetCore.Diagnostics;
using Microsoft.EntityFrameworkCore;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.IdentityModel.Tokens;
using System.Text;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
//builder.Services.AddSwaggerGen();



// Add Entity Framework with MySQL
builder.Services.AddDbContext<ApplicationDbContext>(options =>
{
    var connStr = builder.Configuration.GetConnectionString("DefaultConnection");
        options.UseMySql(connStr, ServerVersion.AutoDetect(connStr))
           .EnableSensitiveDataLogging() // shows SQL parameter values
           .LogTo(Console.WriteLine);    // logs SQL queries
});

// Configure Email Settings
builder.Services.Configure<EmailSettings>(builder.Configuration.GetSection("EmailSettings"));

// JWT and Auth Services
builder.Services.AddScoped<IJwtService, JwtService>();
builder.Services.AddScoped<IAuthService, AuthService>();

// Email Service
builder.Services.AddScoped<IEmailService, EmailService>();

// Services
builder.Services.AddScoped<IAlbumMasterService, AlbumMasterService>();
builder.Services.AddScoped<IBatchMasterService, BatchMasterService>();
builder.Services.AddScoped<IClosureReasonMasterService, ClosureReasonMasterService>();

builder.Services.AddScoped<IContactUsService, ContactUsService>();
builder.Services.AddScoped<ICourseMasterService, CourseMasterService>();
builder.Services.AddScoped<IEnquiryService, EnquiryService>();
builder.Services.AddScoped<IFollowupService, FollowupService>();
builder.Services.AddScoped<IImageMasterService, ImageMasterService>();
builder.Services.AddScoped<IPaymentTypeMasterService, PaymentTypeMasterService>();
builder.Services.AddScoped<IPaymentWithTypeService, PaymentWithTypeService>();
builder.Services.AddScoped<IReceiptService, ReceiptService>();
builder.Services.AddScoped<IStaffMasterService, StaffMasterService>();
builder.Services.AddScoped<IStudentMasterService, StudentMasterService>();

//builder.Services.AddScoped<RestApiDemo.Api.Repositories.PaymentTypeMasterRepository>();
//builder.Services.AddScoped<RestApiDemo.Api.Repositories.PaymentWithTypeRepository>();
//builder.Services.AddScoped<RestApiDemo.Api.Repositories.PlacementRepository>();
//builder.Services.AddScoped<RestApiDemo.Api.Repositories.ReceiptRepository>();
//builder.Services.AddScoped<RestApiDemo.Api.Repositories.StaffMasterRepository>();
//builder.Services.AddScoped<RestApiDemo.Api.Repositories.StudentMasterRepository>();
//builder.Services.AddScoped<RestApiDemo.Api.Repositories.VideoMasterRepository>();

// Add CORS
builder.Services.AddCors(options =>
{
    options.AddPolicy("AllowReactApp", policy =>
    {
        policy.WithOrigins("http://localhost:3000", "https://localhost:3000") // React frontend
              .AllowAnyHeader()
              .AllowAnyMethod()
              .AllowCredentials()
              .WithExposedHeaders("Authorization");
    });
});
var jwtKey = builder.Configuration["Jwt:Key"];
var key = Encoding.ASCII.GetBytes(jwtKey);

// Add authentication with JWT Bearer token and logging
builder.Services.AddAuthentication(options =>
{
    options.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
    options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
}).AddJwtBearer(options =>
{
    options.TokenValidationParameters = new TokenValidationParameters
    {
        ValidateIssuer = false,
        ValidateAudience = false,
        ValidateIssuerSigningKey = true,
        IssuerSigningKey = new SymmetricSecurityKey(key),
        ValidateLifetime = true
    };

    // Add detailed debugging events
    options.Events = new JwtBearerEvents
    {
        OnAuthenticationFailed = context =>
        {
            Console.WriteLine("Authentication failed:");
            Console.WriteLine($"  Exception: {context.Exception}");
            return Task.CompletedTask;
        },
        OnTokenValidated = context =>
        {
            Console.WriteLine("Token validated successfully.");
            return Task.CompletedTask;
        },
        OnChallenge = context =>
        {
            Console.WriteLine("JWT Challenge triggered.");
            Console.WriteLine($"  Error: {context.Error}");
            Console.WriteLine($"  Description: {context.ErrorDescription}");
            return Task.CompletedTask;
        },
        OnMessageReceived = context =>
        {
            Console.WriteLine($"Received JWT token: {context.Token}");
            return Task.CompletedTask;
        }
    };
});

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseDeveloperExceptionPage();
}

app.UseExceptionHandler(errorApp =>
{
    errorApp.Run(async context =>
    {
        var exception = context.Features.Get<IExceptionHandlerFeature>()?.Error;
        if (exception != null)
        {
            Console.WriteLine(exception);
            context.Response.StatusCode = 500;
            await context.Response.WriteAsJsonAsync(new { message = exception.Message });
        }
    });
});

app.UseHttpsRedirection();

app.UseCors("AllowReactApp");

// Add Authentication and Authorization middleware
app.UseAuthentication();
app.UseAuthorization();

app.MapControllers();

app.Run();