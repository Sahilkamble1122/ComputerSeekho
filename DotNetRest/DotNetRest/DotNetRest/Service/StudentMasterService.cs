using DotNetRest.Data;
using DotNetRest.Models;
using DotNetRest.Service;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

public class StudentMasterService : IStudentMasterService
{
    private readonly ApplicationDbContext _context;

    public StudentMasterService(ApplicationDbContext context)
    {
        _context = context;
    }

    // CREATE
    public async Task<StudentMaster> SaveStudentAsync(StudentMaster student)
    {
        _context.StudentMasters.Add(student);
        await _context.SaveChangesAsync();
        return student;
    }

    public async Task<List<StudentMaster>> SaveAllStudentsAsync(List<StudentMaster> students)
    {
        _context.StudentMasters.AddRange(students);
        await _context.SaveChangesAsync();
        return students;
    }

    // READ
    public async Task<List<StudentMaster>> GetAllStudentsAsync()
    {
        return await _context.StudentMasters.ToListAsync();
    }

    public async Task<StudentMaster?> GetStudentByIdAsync(int studentId)
    {
        return await _context.StudentMasters.FindAsync(studentId);
    }

    public async Task<List<StudentMaster>> GetStudentsByNameAsync(string studentName)
    {
        return await _context.StudentMasters
            .Where(s => s.StudentName.Contains(studentName))
            .ToListAsync();
    }

    public async Task<List<StudentMaster>> GetStudentsByEmailAsync(string email)
    {
        return await _context.StudentMasters
            .Where(s => s.StudentEmail == email)
            .ToListAsync();
    }

    public async Task<List<StudentMaster>> GetStudentsByMobileAsync(long mobile)
    {
        return await _context.StudentMasters
            .Where(s => s.StudentMobile == mobile)
            .ToListAsync();
    }

    public async Task<List<StudentMaster>> GetStudentsByGenderAsync(string gender)
    {
        return await _context.StudentMasters
            .Where(s => s.StudentGender == gender)
            .ToListAsync();
    }

    public async Task<List<StudentMaster>> GetStudentsByBatchIdAsync(int batchId)
    {
        return await _context.StudentMasters
            .Where(s => s.BatchId == batchId)
            .ToListAsync();
    }

    public async Task<List<StudentMaster>> GetStudentsByCourseIdAsync(int courseId)
    {
        return await _context.StudentMasters
            .Where(s => s.CourseId == courseId)
            .ToListAsync();
    }

    public async Task<long> CountStudentsByBatchIdAsync(int batchId)
    {
        return await _context.StudentMasters
            .Where(s => s.BatchId == batchId)
            .LongCountAsync();
    }

    public async Task<long> CountStudentsByCourseIdAsync(int courseId)
    {
        return await _context.StudentMasters
            .Where(s => s.CourseId == courseId)
            .LongCountAsync();
    }

    // UPDATE
    public async Task<StudentMaster> UpdateStudentAsync(int studentId, StudentMaster studentDetails)
    {
        var student = await _context.StudentMasters.FindAsync(studentId);
        if (student == null)
            throw new KeyNotFoundException($"Student not found with id: {studentId}");

        // Update only the provided fields
        if (studentDetails.StudentName != null)
            student.StudentName = studentDetails.StudentName;
        if (studentDetails.StudentAddress != null)
            student.StudentAddress = studentDetails.StudentAddress;
        if (studentDetails.StudentGender != null)
            student.StudentGender = studentDetails.StudentGender;
        if (studentDetails.PhotoUrl != null)
            student.PhotoUrl = studentDetails.PhotoUrl;
        if (studentDetails.StudentDob.HasValue)
            student.StudentDob = studentDetails.StudentDob;
        if (studentDetails.StudentQualification != null)
            student.StudentQualification = studentDetails.StudentQualification;
        if (studentDetails.StudentMobile.HasValue)
            student.StudentMobile = studentDetails.StudentMobile;
        if (studentDetails.StudentEmail != null)
            student.StudentEmail = studentDetails.StudentEmail;
        if (studentDetails.BatchId.HasValue)
            student.BatchId = studentDetails.BatchId;
        if (studentDetails.CourseId.HasValue)
            student.CourseId = studentDetails.CourseId;
        if (studentDetails.StudentPassword != null)
            student.StudentPassword = studentDetails.StudentPassword;
        if (studentDetails.StudentUsername != null)
            student.StudentUsername = studentDetails.StudentUsername;
        if (studentDetails.IsPlaced.HasValue)
            student.IsPlaced = studentDetails.IsPlaced;
        if (studentDetails.PendingFees.HasValue)
            student.PendingFees = studentDetails.PendingFees;
        if (studentDetails.CourseFee.HasValue)
            student.CourseFee = studentDetails.CourseFee;

        student.UpdatedDate = DateTime.Now;

        await _context.SaveChangesAsync();
        return student;
    }

    public async Task<StudentMaster> UpdateStudentPendingFeesAsync(int studentId, double pendingFees)
    {
        var student = await _context.StudentMasters.FindAsync(studentId);
        if (student == null)
            throw new KeyNotFoundException($"Student not found with id: {studentId}");

        student.PendingFees = pendingFees;
        student.UpdatedDate = DateTime.Now;

        await _context.SaveChangesAsync();
        return student;
    }

    public async Task<StudentMaster> UpdateStudentNameAsync(int studentId, string studentName)
    {
        var student = await _context.StudentMasters.FindAsync(studentId);
        if (student == null)
            throw new KeyNotFoundException($"Student not found with id: {studentId}");

        student.StudentName = studentName;
        student.UpdatedDate = DateTime.Now;

        await _context.SaveChangesAsync();
        return student;
    }

    public async Task<StudentMaster> UpdateStudentEmailAsync(int studentId, string email)
    {
        var student = await _context.StudentMasters.FindAsync(studentId);
        if (student == null)
            throw new KeyNotFoundException($"Student not found with id: {studentId}");

        student.StudentEmail = email;
        student.UpdatedDate = DateTime.Now;

        await _context.SaveChangesAsync();
        return student;
    }

    public async Task<StudentMaster> UpdateStudentMobileAsync(int studentId, long mobile)
    {
        var student = await _context.StudentMasters.FindAsync(studentId);
        if (student == null)
            throw new KeyNotFoundException($"Student not found with id: {studentId}");

        student.StudentMobile = mobile;
        student.UpdatedDate = DateTime.Now;

        await _context.SaveChangesAsync();
        return student;
    }

    public async Task<StudentMaster> UpdateStudentAddressAsync(int studentId, string address)
    {
        var student = await _context.StudentMasters.FindAsync(studentId);
        if (student == null)
            throw new KeyNotFoundException($"Student not found with id: {studentId}");

        student.StudentAddress = address;
        student.UpdatedDate = DateTime.Now;

        await _context.SaveChangesAsync();
        return student;
    }

    public async Task<StudentMaster> UpdateStudentQualificationAsync(int studentId, string qualification)
    {
        var student = await _context.StudentMasters.FindAsync(studentId);
        if (student == null)
            throw new KeyNotFoundException($"Student not found with id: {studentId}");

        student.StudentQualification = qualification;
        student.UpdatedDate = DateTime.Now;

        await _context.SaveChangesAsync();
        return student;
    }

    public async Task<StudentMaster> UpdateStudentPlacementStatusAsync(int studentId, bool isPlaced)
    {
        var student = await _context.StudentMasters.FindAsync(studentId);
        if (student == null)
            throw new KeyNotFoundException($"Student not found with id: {studentId}");

        student.IsPlaced = isPlaced;
        student.UpdatedDate = DateTime.Now;

        await _context.SaveChangesAsync();
        return student;
    }

    // DELETE
    public async Task DeleteStudentAsync(int studentId)
    {
        var student = await _context.StudentMasters.FindAsync(studentId);
        if (student == null) throw new Exception($"Student not found with id {studentId}");
        _context.StudentMasters.Remove(student);
        await _context.SaveChangesAsync();
    }

    public async Task DeleteStudentsByBatchIdAsync(int batchId)
    {
        var students = await _context.StudentMasters.Where(s => s.BatchId == batchId).ToListAsync();
        _context.StudentMasters.RemoveRange(students);
        await _context.SaveChangesAsync();
    }

    public async Task DeleteStudentsByCourseIdAsync(int courseId)
    {
        var students = await _context.StudentMasters.Where(s => s.CourseId == courseId).ToListAsync();
        _context.StudentMasters.RemoveRange(students);
        await _context.SaveChangesAsync();
    }

    public async Task DeleteAllStudentsAsync()
    {
        _context.StudentMasters.RemoveRange(_context.StudentMasters);
        await _context.SaveChangesAsync();
    }

    // SEARCH & SORT
    public async Task<List<StudentMaster>> SearchStudentsAsync(string searchTerm)
    {
        return await _context.StudentMasters
            .Where(s => s.StudentName.Contains(searchTerm) ||
                        s.StudentEmail.Contains(searchTerm) ||
                        s.StudentMobile.ToString().Contains(searchTerm))
            .ToListAsync();
    }

    public async Task<List<StudentMaster>> GetStudentsWithPaginationAsync(int page, int size)
    {
        return await _context.StudentMasters
            .Skip(page * size)
            .Take(size)
            .ToListAsync();
    }

    public async Task<List<StudentMaster>> GetStudentsSortedByNameAsync()
    {
        return await _context.StudentMasters.OrderBy(s => s.StudentName).ToListAsync();
    }

    public async Task<List<StudentMaster>> GetStudentsSortedByEmailAsync()
    {
        return await _context.StudentMasters.OrderBy(s => s.StudentEmail).ToListAsync();
    }

    public async Task<List<StudentMaster>> GetStudentsSortedByBatchIdAsync()
    {
        return await _context.StudentMasters.OrderBy(s => s.BatchId).ToListAsync();
    }

    // Authentication
    public async Task<StudentMaster?> AuthenticateStudentAsync(string username, string password)
    {
        return await _context.StudentMasters
            .FirstOrDefaultAsync(s => s.StudentUsername == username && s.StudentPassword == password);
    }

    // Placement status
    public async Task<List<StudentMaster>> GetStudentsByPlacementStatusAsync(bool isPlaced)
    {
        return await _context.StudentMasters.Where(s => s.IsPlaced == isPlaced).ToListAsync();
    }

    public async Task<long> CountPlacedStudentsAsync()
    {
        return await _context.StudentMasters.LongCountAsync(s => s.IsPlaced == true);
    }

    public async Task<long> CountUnplacedStudentsAsync()
    {
        return await _context.StudentMasters.LongCountAsync(s => s.IsPlaced == false);
    }
}
