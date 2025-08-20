# MySQL Database Setup Guide

This guide will help you set up the MySQL database for the RestApiDemo Spring Boot application.

## Prerequisites

1. **MySQL Server** (version 8.0 or higher recommended)
2. **MySQL Workbench** or any MySQL client
3. **Java 17** or higher
4. **Maven** 3.6 or higher

## Step 1: Install MySQL Server

### Windows
1. Download MySQL Installer from: https://dev.mysql.com/downloads/installer/
2. Run the installer and follow the setup wizard
3. Choose "Developer Default" or "Server only" installation
4. Set root password during installation
5. Complete the installation

### macOS
```bash
# Using Homebrew
brew install mysql
brew services start mysql

# Set root password
mysql_secure_installation
```

### Linux (Ubuntu/Debian)
```bash
sudo apt update
sudo apt install mysql-server
sudo mysql_secure_installation
```

## Step 2: Create Database

1. **Connect to MySQL:**
   ```bash
   mysql -u root -p
   ```

2. **Create the database:**
   ```sql
   CREATE DATABASE rest_api_demo;
   USE rest_api_demo;
   ```

3. **Verify database creation:**
   ```sql
   SHOW DATABASES;
   ```

## Step 3: Configure Application Properties

Update the `application.properties` file with your MySQL credentials:

```properties
# Database URL - Update with your MySQL host, port, and database name
spring.datasource.url=jdbc:mysql://localhost:3306/rest_api_demo?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=UTF-8

# Database credentials - Update with your MySQL username and password
spring.datasource.username=root
spring.datasource.password=your_password_here
```

## Step 4: Test Database Connection

1. **Start the application:**
   ```bash
   mvn spring-boot:run
   ```

2. **Check application logs** for database connection success:
   ```
   HikariPool-1 - Starting...
   HikariPool-1 - Start completed.
   ```

3. **Verify tables are created** (after first run):
   ```sql
   USE rest_api_demo;
   SHOW TABLES;
   ```

## Expected Tables

After running the application, you should see these tables created:

- `album_master`
- `batch_master`
- `closure_reason_master`
- `course_master`
- `enquiry`
- `followup`
- `image_master`
- `payment`
- `payment_type_master`
- `placement`
- `receipt`
- `staff_master`
- `student_master`
- `video_master`

## Step 5: Database Configuration Options

### DDL Auto Options

In `application.properties`, you can configure `spring.jpa.hibernate.ddl-auto`:

- **`create`**: Drops and recreates all tables on startup
- **`create-drop`**: Creates tables on startup, drops on shutdown
- **`update`**: Updates existing tables (recommended for development)
- **`validate`**: Validates schema without making changes
- **`none`**: No schema changes

### Recommended Settings

**For Development:**
```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**For Production:**
```properties
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
```

## Step 6: Connection Pool Configuration

The application uses HikariCP connection pool with these settings:

```properties
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.idle-timeout=300000
spring.datasource.hikari.connection-timeout=20000
spring.datasource.hikari.max-lifetime=1200000
```

## Troubleshooting

### Common Issues

1. **Connection Refused:**
   - Ensure MySQL service is running
   - Check if MySQL is listening on port 3306

2. **Access Denied:**
   - Verify username and password
   - Check if user has proper permissions

3. **SSL Connection Error:**
   - The configuration includes `useSSL=false`
   - If SSL is required, update the URL accordingly

4. **Timezone Issues:**
   - The configuration includes `serverTimezone=UTC`
   - Adjust if your server uses a different timezone

### Useful MySQL Commands

```sql
-- Check MySQL version
SELECT VERSION();

-- Check current user
SELECT USER();

-- Check current database
SELECT DATABASE();

-- List all databases
SHOW DATABASES;

-- List all tables in current database
SHOW TABLES;

-- Check table structure
DESCRIBE table_name;

-- Check table creation SQL
SHOW CREATE TABLE table_name;
```

## Security Considerations

1. **Use Strong Passwords** for MySQL root and application users
2. **Create Application-Specific User** instead of using root:
   ```sql
   CREATE USER 'restapi'@'localhost' IDENTIFIED BY 'strong_password';
   GRANT ALL PRIVILEGES ON rest_api_demo.* TO 'restapi'@'localhost';
   FLUSH PRIVILEGES;
   ```

3. **Enable SSL** for production environments
4. **Restrict Network Access** to MySQL server
5. **Regular Backups** of your database

## Performance Tuning

### MySQL Configuration

Add these to your MySQL configuration file (`my.cnf` or `my.ini`):

```ini
[mysqld]
# InnoDB settings
innodb_buffer_pool_size = 1G
innodb_log_file_size = 256M
innodb_flush_log_at_trx_commit = 2

# Connection settings
max_connections = 200
max_connect_errors = 1000000

# Query cache (MySQL 5.7 and below)
query_cache_type = 1
query_cache_size = 64M
```

### Application Configuration

For high-traffic applications, consider:

```properties
# Increase connection pool size
spring.datasource.hikari.maximum-pool-size=20

# Enable connection testing
spring.datasource.hikari.connection-test-query=SELECT 1

# Optimize JPA settings
spring.jpa.properties.hibernate.jdbc.batch_size=20
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true
```

## Next Steps

After successful database setup:

1. **Create Service Layer** - Business logic implementation
2. **Create Controllers** - REST API endpoints
3. **Add Data Validation** - Input validation
4. **Implement Security** - Authentication and authorization
5. **Add Testing** - Unit and integration tests 