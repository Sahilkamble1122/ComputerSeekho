-- Drop existing tables if they exist
DROP TABLE IF EXISTS album_master;
DROP TABLE IF EXISTS student_master;
DROP TABLE IF EXISTS staff_master;
DROP TABLE IF EXISTS course_master;
DROP TABLE IF EXISTS batch_master;
DROP TABLE IF EXISTS payment_master;
DROP TABLE IF EXISTS enquiry_master;
DROP TABLE IF EXISTS followup_master;
DROP TABLE IF EXISTS placement_master;
DROP TABLE IF EXISTS receipt_master;
DROP TABLE IF EXISTS payment_type_master;
DROP TABLE IF EXISTS closure_reason_master;
DROP TABLE IF EXISTS image_master;
DROP TABLE IF EXISTS video_master;

-- Create album_master table
CREATE TABLE album_master (
    album_id INT AUTO_INCREMENT PRIMARY KEY,
    album_name VARCHAR(255) NOT NULL,
    album_description TEXT,
    start_date DATETIME,
    end_date DATETIME,
    album_is_active BOOLEAN DEFAULT TRUE,
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create student_master table
CREATE TABLE student_master (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(255) NOT NULL,
    student_address TEXT,
    student_gender VARCHAR(10),
    photo_url TEXT,
    student_dob DATE,
    student_qualification VARCHAR(255),
    student_mobile BIGINT,
    student_email VARCHAR(255),
    batch_id INT,
    course_id INT,
    student_password VARCHAR(255),
    student_username VARCHAR(255),
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create staff_master table
CREATE TABLE staff_master (
    staff_id INT AUTO_INCREMENT PRIMARY KEY,
    staff_name VARCHAR(255) NOT NULL,
    staff_address TEXT,
    staff_gender VARCHAR(10),
    staff_mobile BIGINT,
    staff_email VARCHAR(255),
    staff_password VARCHAR(255),
    staff_username VARCHAR(255),
    staff_role VARCHAR(50),
    staff_is_active BOOLEAN DEFAULT TRUE,
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create course_master table
CREATE TABLE course_master (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(255) NOT NULL,
    course_description TEXT,
    course_duration VARCHAR(100),
    course_fee DECIMAL(10,2),
    course_is_active BOOLEAN DEFAULT TRUE,
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create batch_master table
CREATE TABLE batch_master (
    batch_id INT AUTO_INCREMENT PRIMARY KEY,
    batch_name VARCHAR(255) NOT NULL,
    batch_description TEXT,
    course_id INT,
    start_date DATE,
    end_date DATE,
    batch_capacity INT,
    batch_is_active BOOLEAN DEFAULT TRUE,
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create payment_master table
CREATE TABLE payment_master (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    payment_type_id INT,
    payment_amount DECIMAL(10,2),
    installment_no INT,
    payment_date DATE,
    payment_status VARCHAR(50),
    payment_reference VARCHAR(255),
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create enquiry_master table
CREATE TABLE enquiry_master (
    enquiry_id INT AUTO_INCREMENT PRIMARY KEY,
    enquiry_name VARCHAR(255) NOT NULL,
    enquiry_mobile BIGINT,
    enquiry_email VARCHAR(255),
    enquiry_course VARCHAR(255),
    enquiry_status VARCHAR(50),
    enquiry_processed_flag BOOLEAN DEFAULT FALSE,
    enquiry_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create followup_master table
CREATE TABLE followup_master (
    followup_id INT AUTO_INCREMENT PRIMARY KEY,
    enquiry_id INT,
    followup_date DATE,
    followup_notes TEXT,
    followup_status VARCHAR(50),
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create placement_master table
CREATE TABLE placement_master (
    placement_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    company_name VARCHAR(255),
    position VARCHAR(255),
    salary DECIMAL(10,2),
    placement_date DATE,
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create receipt_master table
CREATE TABLE receipt_master (
    receipt_id INT AUTO_INCREMENT PRIMARY KEY,
    payment_id INT,
    student_id INT,
    receipt_number VARCHAR(255),
    receipt_date DATE,
    receipt_amount DECIMAL(10,2),
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create payment_type_master table
CREATE TABLE payment_type_master (
    payment_type_id INT AUTO_INCREMENT PRIMARY KEY,
    payment_type_name VARCHAR(255) NOT NULL,
    payment_type_description TEXT,
    payment_type_is_active BOOLEAN DEFAULT TRUE,
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create closure_reason_master table
CREATE TABLE closure_reason_master (
    closure_reason_id INT AUTO_INCREMENT PRIMARY KEY,
    closure_reason_name VARCHAR(255) NOT NULL,
    closure_reason_description TEXT,
    closure_reason_is_active BOOLEAN DEFAULT TRUE,
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create image_master table
CREATE TABLE image_master (
    image_id INT AUTO_INCREMENT PRIMARY KEY,
    image_name VARCHAR(255) NOT NULL,
    image_url TEXT,
    image_description TEXT,
    album_id INT,
    image_is_active BOOLEAN DEFAULT TRUE,
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create video_master table
CREATE TABLE video_master (
    video_id INT AUTO_INCREMENT PRIMARY KEY,
    video_name VARCHAR(255) NOT NULL,
    video_url TEXT,
    video_description TEXT,
    album_id INT,
    video_is_active BOOLEAN DEFAULT TRUE,
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert sample data for album_master
INSERT INTO album_master (album_name, album_description, start_date, end_date, album_is_active) VALUES
('Summer Collection 2024', 'Beautiful summer photos from our recent shoot', '2024-06-01 00:00:00', '2024-08-31 23:59:59', TRUE),
('Graduation Ceremony 2024', 'Photos from the graduation ceremony', '2024-05-15 00:00:00', '2024-05-15 23:59:59', TRUE),
('Student Activities', 'Various student activities and events', '2024-01-01 00:00:00', '2024-12-31 23:59:59', TRUE),
('Campus Tour', 'Virtual campus tour photos', '2024-03-01 00:00:00', '2024-03-31 23:59:59', TRUE),
('Faculty Events', 'Faculty and staff events', '2024-02-01 00:00:00', '2024-12-31 23:59:59', TRUE);

-- Insert sample data for student_master
INSERT INTO student_master (student_name, student_address, student_gender, photo_url, student_dob, student_qualification, student_mobile, student_email, batch_id, course_id, student_password, student_username) VALUES
('John Doe', '123 Main Street, City', 'Male', NULL, '1995-05-15', 'Bachelor of Science', 9876543210, 'john.doe@email.com', 1, 1, 'password123', 'johndoe'),
('Jane Smith', '789 Oak Avenue, Town', 'Female', NULL, '1990-08-22', 'Master of Arts', 8765432109, 'jane.smith@email.com', 1, 2, 'password123', 'janesmith'),
('Mike Johnson', '456 Pine Road, Village', 'Male', NULL, '1988-12-10', 'High School Diploma', 7654321098, 'mike.johnson@email.com', 2, 1, 'password123', 'mikejohnson'),
('Sarah Wilson', '321 Elm Street, Borough', 'Female', NULL, '1992-03-28', 'Bachelor of Engineering', 6543210987, 'sarah.wilson@email.com', 2, 2, 'password123', 'sarahwilson'),
('David Brown', '654 Maple Drive, County', 'Male', NULL, '1985-07-14', 'Master of Science', 5432109876, 'david.brown@email.com', 1, 1, 'password123', 'davidbrown');

-- Insert sample data for staff_master
INSERT INTO staff_master (staff_name, staff_address, staff_gender, staff_mobile, staff_email, staff_password, staff_username, staff_role, staff_is_active) VALUES
('Admin User', 'Admin Address', 'Male', 1234567890, 'admin@example.com', 'admin123', 'admin', 'ADMIN', TRUE),
('Teacher One', 'Teacher Address 1', 'Female', 2345678901, 'teacher1@example.com', 'teacher123', 'teacher1', 'TEACHER', TRUE),
('Teacher Two', 'Teacher Address 2', 'Male', 3456789012, 'teacher2@example.com', 'teacher123', 'teacher2', 'TEACHER', TRUE);

-- Insert sample data for course_master
INSERT INTO course_master (course_name, course_description, course_duration, course_fee, course_is_active) VALUES
('Java Programming', 'Complete Java programming course', '6 months', 5000.00, TRUE),
('Web Development', 'Full stack web development', '8 months', 7500.00, TRUE),
('Data Science', 'Data science and analytics', '10 months', 9000.00, TRUE),
('Python Programming', 'Python programming fundamentals', '4 months', 4000.00, TRUE);

-- Insert sample data for batch_master
INSERT INTO batch_master (batch_name, batch_description, course_id, start_date, end_date, batch_capacity, batch_is_active) VALUES
('Java Batch 2024-01', 'Java programming batch starting January 2024', 1, '2024-01-15', '2024-07-15', 30, TRUE),
('Web Dev Batch 2024-02', 'Web development batch starting February 2024', 2, '2024-02-01', '2024-10-01', 25, TRUE),
('Data Science Batch 2024-03', 'Data science batch starting March 2024', 3, '2024-03-01', '2024-12-31', 20, TRUE);

-- Insert sample data for payment_type_master
INSERT INTO payment_type_master (payment_type_name, payment_type_description, payment_type_is_active) VALUES
('Cash', 'Cash payment', TRUE),
('Cheque', 'Cheque payment', TRUE),
('Online Transfer', 'Online bank transfer', TRUE),
('Credit Card', 'Credit card payment', TRUE),
('Debit Card', 'Debit card payment', TRUE);

-- Insert sample data for closure_reason_master
INSERT INTO closure_reason_master (closure_reason_name, closure_reason_description, closure_reason_is_active) VALUES
('Course Completed', 'Student completed the course successfully', TRUE),
('Personal Reasons', 'Student left due to personal reasons', TRUE),
('Financial Issues', 'Student left due to financial constraints', TRUE),
('Health Issues', 'Student left due to health problems', TRUE),
('Job Opportunity', 'Student got a job opportunity', TRUE);

-- Create indexes for better performance
CREATE INDEX idx_album_name ON album_master(album_name);
CREATE INDEX idx_album_active ON album_master(album_is_active);
CREATE INDEX idx_student_name ON student_master(student_name);
CREATE INDEX idx_student_email ON student_master(student_email);
CREATE INDEX idx_student_mobile ON student_master(student_mobile);
CREATE INDEX idx_student_username ON student_master(student_username);
CREATE INDEX idx_batch_id ON student_master(batch_id);
CREATE INDEX idx_course_id ON student_master(course_id);
CREATE INDEX idx_staff_username ON staff_master(staff_username);
CREATE INDEX idx_course_name ON course_master(course_name);
CREATE INDEX idx_batch_name ON batch_master(batch_name);

