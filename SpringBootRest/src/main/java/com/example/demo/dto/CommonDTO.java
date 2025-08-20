package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;

public class CommonDTO {
    
    // ===========================================
    // COMMON RESPONSE DTOs
    // ===========================================
    
    public static class ApiResponse<T> {
        private boolean success;
        private String message;
        private T data;
        private LocalDateTime timestamp;
        private Integer statusCode;
        
        // Constructors
        public ApiResponse() {
            this.timestamp = LocalDateTime.now();
        }
        
        public ApiResponse(boolean success, String message, T data) {
            this.success = success;
            this.message = message;
            this.data = data;
            this.timestamp = LocalDateTime.now();
        }
        
        public ApiResponse(boolean success, String message, T data, Integer statusCode) {
            this.success = success;
            this.message = message;
            this.data = data;
            this.statusCode = statusCode;
            this.timestamp = LocalDateTime.now();
        }
        
        // Static factory methods
        public static <T> ApiResponse<T> success(T data) {
            return new ApiResponse<>(true, "Success", data, 200);
        }
        
        public static <T> ApiResponse<T> success(String message, T data) {
            return new ApiResponse<>(true, message, data, 200);
        }
        
        public static <T> ApiResponse<T> error(String message) {
            return new ApiResponse<>(false, message, null, 400);
        }
        
        public static <T> ApiResponse<T> error(String message, Integer statusCode) {
            return new ApiResponse<>(false, message, null, statusCode);
        }
        
        public static <T> ApiResponse<T> notFound(String message) {
            return new ApiResponse<>(false, message, null, 404);
        }
        
        public static <T> ApiResponse<T> unauthorized(String message) {
            return new ApiResponse<>(false, message, null, 401);
        }
        
        public static <T> ApiResponse<T> forbidden(String message) {
            return new ApiResponse<>(false, message, null, 403);
        }
        
        public static <T> ApiResponse<T> serverError(String message) {
            return new ApiResponse<>(false, message, null, 500);
        }
        
        // Getters and Setters
        public boolean isSuccess() { return success; }
        public void setSuccess(boolean success) { this.success = success; }
        
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        
        public T getData() { return data; }
        public void setData(T data) { this.data = data; }
        
        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
        
        public Integer getStatusCode() { return statusCode; }
        public void setStatusCode(Integer statusCode) { this.statusCode = statusCode; }
    }
    
    public static class PaginatedResponse<T> {
        private List<T> data;
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
        private boolean hasNext;
        private boolean hasPrevious;
        
        // Constructors
        public PaginatedResponse() {}
        
        public PaginatedResponse(List<T> data, int page, int size, long totalElements, int totalPages) {
            this.data = data;
            this.page = page;
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.hasNext = page < totalPages - 1;
            this.hasPrevious = page > 0;
        }
        
        // Getters and Setters
        public List<T> getData() { return data; }
        public void setData(List<T> data) { this.data = data; }
        
        public int getPage() { return page; }
        public void setPage(int page) { this.page = page; }
        
        public int getSize() { return size; }
        public void setSize(int size) { this.size = size; }
        
        public long getTotalElements() { return totalElements; }
        public void setTotalElements(long totalElements) { this.totalElements = totalElements; }
        
        public int getTotalPages() { return totalPages; }
        public void setTotalPages(int totalPages) { this.totalPages = totalPages; }
        
        public boolean isHasNext() { return hasNext; }
        public void setHasNext(boolean hasNext) { this.hasNext = hasNext; }
        
        public boolean isHasPrevious() { return hasPrevious; }
        public void setHasPrevious(boolean hasPrevious) { this.hasPrevious = hasPrevious; }
    }
    
    // ===========================================
    // SEARCH AND FILTER DTOs
    // ===========================================
    
    public static class SearchRequest {
        private String searchTerm;
        private String sortBy;
        private String sortDirection;
        private Integer page;
        private Integer size;
        
        // Constructors
        public SearchRequest() {
            this.page = 0;
            this.size = 10;
            this.sortDirection = "ASC";
        }
        
        // Getters and Setters
        public String getSearchTerm() { return searchTerm; }
        public void setSearchTerm(String searchTerm) { this.searchTerm = searchTerm; }
        
        public String getSortBy() { return sortBy; }
        public void setSortBy(String sortBy) { this.sortBy = sortBy; }
        
        public String getSortDirection() { return sortDirection; }
        public void setSortDirection(String sortDirection) { this.sortDirection = sortDirection; }
        
        public Integer getPage() { return page; }
        public void setPage(Integer page) { this.page = page; }
        
        public Integer getSize() { return size; }
        public void setSize(Integer size) { this.size = size; }
    }
    
    public static class DateRangeRequest {
        @NotNull(message = "Start date is required")
        private String startDate;
        
        @NotNull(message = "End date is required")
        private String endDate;
        
        // Constructors
        public DateRangeRequest() {}
        
        public DateRangeRequest(String startDate, String endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }
        
        // Getters and Setters
        public String getStartDate() { return startDate; }
        public void setStartDate(String startDate) { this.startDate = startDate; }
        
        public String getEndDate() { return endDate; }
        public void setEndDate(String endDate) { this.endDate = endDate; }
    }
    
    // ===========================================
    // STATISTICS DTOs
    // ===========================================
    
    public static class StatisticsResponse {
        private Long totalCount;
        private Long activeCount;
        private Long inactiveCount;
        private String mostPopularItem;
        private Integer mostPopularItemId;
        
        // Constructors
        public StatisticsResponse() {}
        
        public StatisticsResponse(Long totalCount, Long activeCount, Long inactiveCount, 
                               String mostPopularItem, Integer mostPopularItemId) {
            this.totalCount = totalCount;
            this.activeCount = activeCount;
            this.inactiveCount = inactiveCount;
            this.mostPopularItem = mostPopularItem;
            this.mostPopularItemId = mostPopularItemId;
        }
        
        // Getters and Setters
        public Long getTotalCount() { return totalCount; }
        public void setTotalCount(Long totalCount) { this.totalCount = totalCount; }
        
        public Long getActiveCount() { return activeCount; }
        public void setActiveCount(Long activeCount) { this.activeCount = activeCount; }
        
        public Long getInactiveCount() { return inactiveCount; }
        public void setInactiveCount(Long inactiveCount) { this.inactiveCount = inactiveCount; }
        
        public String getMostPopularItem() { return mostPopularItem; }
        public void setMostPopularItem(String mostPopularItem) { this.mostPopularItem = mostPopularItem; }
        
        public Integer getMostPopularItemId() { return mostPopularItemId; }
        public void setMostPopularItemId(Integer mostPopularItemId) { this.mostPopularItemId = mostPopularItemId; }
    }
    
    // ===========================================
    // BULK OPERATION DTOs
    // ===========================================
    
    public static class BulkOperationRequest<T> {
        private List<T> items;
        private String operation; // CREATE, UPDATE, DELETE
        
        // Constructors
        public BulkOperationRequest() {}
        
        public BulkOperationRequest(List<T> items, String operation) {
            this.items = items;
            this.operation = operation;
        }
        
        // Getters and Setters
        public List<T> getItems() { return items; }
        public void setItems(List<T> items) { this.items = items; }
        
        public String getOperation() { return operation; }
        public void setOperation(String operation) { this.operation = operation; }
    }
    
    public static class BulkOperationResponse {
        private int totalItems;
        private int successCount;
        private int failureCount;
        private List<String> errors;
        
        // Constructors
        public BulkOperationResponse() {}
        
        public BulkOperationResponse(int totalItems, int successCount, int failureCount, List<String> errors) {
            this.totalItems = totalItems;
            this.successCount = successCount;
            this.failureCount = failureCount;
            this.errors = errors;
        }
        
        // Getters and Setters
        public int getTotalItems() { return totalItems; }
        public void setTotalItems(int totalItems) { this.totalItems = totalItems; }
        
        public int getSuccessCount() { return successCount; }
        public void setSuccessCount(int successCount) { this.successCount = successCount; }
        
        public int getFailureCount() { return failureCount; }
        public void setFailureCount(int failureCount) { this.failureCount = failureCount; }
        
        public List<String> getErrors() { return errors; }
        public void setErrors(List<String> errors) { this.errors = errors; }
    }
    
    // ===========================================
    // FILE UPLOAD DTOs
    // ===========================================
    
    public static class FileUploadResponse {
        private String fileName;
        private String fileUrl;
        private String fileType;
        private Long fileSize;
        private LocalDateTime uploadDate;
        
        // Constructors
        public FileUploadResponse() {
            this.uploadDate = LocalDateTime.now();
        }
        
        public FileUploadResponse(String fileName, String fileUrl, String fileType, Long fileSize) {
            this.fileName = fileName;
            this.fileUrl = fileUrl;
            this.fileType = fileType;
            this.fileSize = fileSize;
            this.uploadDate = LocalDateTime.now();
        }
        
        // Getters and Setters
        public String getFileName() { return fileName; }
        public void setFileName(String fileName) { this.fileName = fileName; }
        
        public String getFileUrl() { return fileUrl; }
        public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
        
        public String getFileType() { return fileType; }
        public void setFileType(String fileType) { this.fileType = fileType; }
        
        public Long getFileSize() { return fileSize; }
        public void setFileSize(Long fileSize) { this.fileSize = fileSize; }
        
        public LocalDateTime getUploadDate() { return uploadDate; }
        public void setUploadDate(LocalDateTime uploadDate) { this.uploadDate = uploadDate; }
    }
    
    // ===========================================
    // VALIDATION DTOs
    // ===========================================
    
    public static class ValidationError {
        private String field;
        private String message;
        private String rejectedValue;
        
        // Constructors
        public ValidationError() {}
        
        public ValidationError(String field, String message, String rejectedValue) {
            this.field = field;
            this.message = message;
            this.rejectedValue = rejectedValue;
        }
        
        // Getters and Setters
        public String getField() { return field; }
        public void setField(String field) { this.field = field; }
        
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        
        public String getRejectedValue() { return rejectedValue; }
        public void setRejectedValue(String rejectedValue) { this.rejectedValue = rejectedValue; }
    }
    
    public static class ValidationResponse {
        private boolean valid;
        private List<ValidationError> errors;
        
        // Constructors
        public ValidationResponse() {}
        
        public ValidationResponse(boolean valid, List<ValidationError> errors) {
            this.valid = valid;
            this.errors = errors;
        }
        
        // Getters and Setters
        public boolean isValid() { return valid; }
        public void setValid(boolean valid) { this.valid = valid; }
        
        public List<ValidationError> getErrors() { return errors; }
        public void setErrors(List<ValidationError> errors) { this.errors = errors; }
    }
} 