#  Secure Task Management API

A production-ready RESTful API built with **Spring Boot 3.2**, featuring **JWT Authentication**, **Spring Security 6**, and **Role-Based Access Control (RBAC)**. This project demonstrates modern security practices and clean architecture principles.

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

---

##  Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Getting Started](#-getting-started)
- [API Documentation](#-api-documentation)
- [Security Implementation](#-security-implementation)
- [Project Structure](#-project-structure)
- [Database Schema](#-database-schema)
- [Configuration](#-configuration)
- [Testing](#-testing)
- [Contributing](#-contributing)
- [License](#-license)

---

##  Features

### Core Functionality
-  **JWT-based Authentication** - Stateless authentication with JSON Web Tokens
-  **Role-Based Access Control** - Three-tier authorization (USER, MANAGER, ADMIN)
-  **Task Management** - Complete CRUD operations for tasks
-  **Method-Level Security** - Fine-grained access control with `@PreAuthorize`
-  **Custom Security Filters** - JWT validation filter in the security chain
-  **Password Encryption** - BCrypt hashing for secure password storage
-  **Global Exception Handling** - Centralized error management
-  **Admin Dashboard** - User management and system statistics

### Security Features
- Stateless session management
- Token expiration and validation
- CORS configuration
- SQL injection prevention with JPA
- Input validation with Bean Validation
- Secure password encoding

---

## Tech Stack

Core Framework:
  • Spring Boot 3.2.0 - Application Framework
  • Spring Security 6.x - Security & Authentication
  • Spring Data JPA 3.2.0 - Data Access Layer

Security & Authentication:
  • JWT (jjwt) 0.12.3 - Token Generation & Validation

Database:
  • H2 Database 2.x - In-Memory Database (Development)
  • MySQL 8.x - Production Database

Development Tools:
  • Lombok 1.18.x - Boilerplate Code Reduction
  • Maven 3.6+ - Dependency Management
  • Java 17 - Programming Language

##  Architecture

### Layered Architecture
```
┌─────────────────────────────────────────┐
│          Controller Layer               │
│    (REST APIs, Request Handling)        │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│           Service Layer                 │
│    (Business Logic, Validation)         │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│         Repository Layer                │
│    (Data Access, JPA Queries)           │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│            Database                     │
│         (H2 / MySQL)                    │
└─────────────────────────────────────────┘
```

### Security Flow
```
HTTP Request
    ↓
JwtAuthenticationFilter (Extract & Validate Token)
    ↓
SecurityContext (Set Authentication)
    ↓
Controller (@PreAuthorize Check)
    ↓
Service Layer (Business Logic)
    ↓
Response
```

---

##  Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+ (optional, H2 included for development)
- Git

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/yourusername/secure-task-api.git
cd secure-task-api
```

2. **Configure database** (Optional - Uses H2 by default)

Edit `src/main/resources/application.properties`:
```properties
# For MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/taskdb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

3. **Build the project**
```bash
mvn clean install
```

4. **Run the application**
```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

### Quick Test
```bash
# Register a new user
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123",
    "role": "USER"
  }'
```

---

##  API Documentation

### Base URL
```
http://localhost:8080/api
```

### Authentication Endpoints

#### Register User
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "password123",
  "role": "USER"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "username": "john_doe",
  "role": "USER",
  "message": "Registration successful"
}
```

#### Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "john_doe",
  "password": "password123"
}
```

### Task Management Endpoints

#### Create Task
```http
POST /api/tasks
Authorization: Bearer {token}
Content-Type: application/json

{
  "title": "Complete Project",
  "description": "Finish the API implementation",
  "status": "TODO",
  "priority": "HIGH"
}
```

#### Get All Tasks
```http
GET /api/tasks
Authorization: Bearer {token}
```

#### Update Task
```http
PUT /api/tasks/{id}
Authorization: Bearer {token}
Content-Type: application/json

{
  "title": "Updated Title",
  "description": "Updated description",
  "status": "IN_PROGRESS",
  "priority": "URGENT"
}
```

#### Update Task Status
```http
PATCH /api/tasks/{id}/status?status=COMPLETED
Authorization: Bearer {token}
```

#### Delete Task (ADMIN only)
```http
DELETE /api/tasks/{id}
Authorization: Bearer {token}
```

### Admin Endpoints

#### Get All Users (ADMIN only)
```http
GET /api/admin/users
Authorization: Bearer {admin_token}
```

#### Update User Role (ADMIN only)
```http
PATCH /api/admin/users/{id}/role?role=MANAGER
Authorization: Bearer {admin_token}
```

#### Get System Statistics (ADMIN only)
```http
GET /api/admin/stats
Authorization: Bearer {admin_token}
```

### Role-Based Access Control

USER Role:
  ✅ Can create tasks (POST /api/tasks)
  ✅ Can view own tasks (GET /api/tasks)
  ✅ Can update own task status (PATCH /api/tasks/{id}/status)
  ❌ Cannot update complete task details (PUT /api/tasks/{id})
  ❌ Cannot delete tasks (DELETE /api/tasks/{id})
  ❌ Cannot access admin endpoints (GET /api/admin/**)

MANAGER Role:
  ✅ Can create tasks (POST /api/tasks)
  ✅ Can view all tasks (GET /api/tasks)
  ✅ Can update any task (PUT /api/tasks/{id})
  ✅ Can update any task status (PATCH /api/tasks/{id}/status)
  ✅ Can assign tasks to users
  ❌ Cannot delete tasks (DELETE /api/tasks/{id})
  ❌ Cannot access admin endpoints (GET /api/admin/**)

ADMIN Role:
  ✅ Can create tasks (POST /api/tasks)
  ✅ Can view all tasks (GET /api/tasks)
  ✅ Can update any task (PUT /api/tasks/{id})
  ✅ Can update any task status (PATCH /api/tasks/{id}/status)
  ✅ Can delete any task (DELETE /api/tasks/{id})
  ✅ Can access admin endpoints (GET /api/admin/**)
  ✅ Can manage users (view, update roles, delete)
  ✅ Can view system statistics

Legend:
  ✅ = Allowed
  ❌ = Forbidden (Returns 403 HTTP Status)

##  Security Implementation

### JWT Token Structure
```
Header:    {"alg": "HS256", "typ": "JWT"}
Payload:   {"sub": "username", "exp": 1234567890}
Signature: HMACSHA256(base64(header) + "." + base64(payload), secret)
```

### Security Filter Chain
1. **JwtAuthenticationFilter** - Validates JWT tokens
2. **UsernamePasswordAuthenticationFilter** - Standard Spring Security filter
3. **FilterSecurityInterceptor** - Enforces authorization rules

### Password Security
- Passwords are hashed using **BCrypt** with automatic salt generation
- Plain text passwords are never stored or logged
- Minimum password length: 6 characters

### Token Configuration
- **Expiration Time**: 24 hours (configurable in `application.properties`)
- **Secret Key**: 256-bit HMAC-SHA256 key
- **Algorithm**: HS256

---

##  Project Structure

```
src/main/java/com/taskmanager/
│
├── config/
│   ├── SecurityConfig.java              # Spring Security configuration
│   └── WebConfig.java                   # CORS and web configuration
│
├── controller/
│   ├── AuthController.java              # Authentication endpoints
│   ├── TaskController.java              # Task management endpoints
│   └── AdminController.java             # Admin operations
│
├── dto/
│   ├── AuthRequest.java                 # Login request DTO
│   ├── AuthResponse.java                # Authentication response DTO
│   ├── RegisterRequest.java             # Registration request DTO
│   ├── TaskRequest.java                 # Task creation/update DTO
│   ├── TaskResponse.java                # Task response DTO
│   ├── UserResponse.java                # User response DTO
│   ├── SystemStats.java                 # Statistics DTO
│   └── ErrorResponse.java               # Error response DTO
│
├── exception/
│   └── GlobalExceptionHandler.java      # Centralized exception handling
│
├── model/
│   ├── User.java                        # User entity (implements UserDetails)
│   ├── Task.java                        # Task entity
│   ├── Role.java                        # Role enum (USER, MANAGER, ADMIN)
│   ├── TaskStatus.java                  # Task status enum
│   └── Priority.java                    # Priority enum
│
├── repository/
│   ├── UserRepository.java              # User data access
│   └── TaskRepository.java              # Task data access
│
├── security/
│   ├── JwtService.java                  # JWT token operations
│   └── JwtAuthenticationFilter.java     # Custom JWT filter
│
├── service/
│   ├── AuthService.java                 # Authentication logic
│   ├── TaskService.java                 # Task business logic
│   ├── AdminService.java                # Admin operations
│   └── CustomUserDetailsService.java    # User details loading
│
└── TaskManagementApplication.java       # Main application class
```

---

##  Database Schema

### Users Table
```sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE
);
```

### Tasks Table
```sql
CREATE TABLE tasks (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(20) NOT NULL,
    priority VARCHAR(20) NOT NULL,
    assigned_to BIGINT,
    created_by BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    FOREIGN KEY (assigned_to) REFERENCES users(id),
    FOREIGN KEY (created_by) REFERENCES users(id)
);
```

### Entity Relationships
```
User (1) ──────< (N) Task (created_by)
User (1) ──────< (N) Task (assigned_to)
```

---

##  Configuration

### Application Properties
```properties
# Server Configuration
server.port=8080

# Database Configuration (H2)
spring.datasource.url=jdbc:h2:mem:taskdb
spring.datasource.username=admin
spring.datasource.password=admin123

# JPA Configuration
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# JWT Configuration
jwt.secret=your-256-bit-secret-key-here
jwt.expiration=86400000

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### Environment Variables (Production)
```bash
export JWT_SECRET=your-production-secret-key
export DB_URL=jdbc:mysql://localhost:3306/taskdb
export DB_USERNAME=your_db_user
export DB_PASSWORD=your_db_password
```

---

##  Testing

### Using cURL

**1. Register a User**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","email":"admin@test.com","password":"admin123","role":"ADMIN"}'
```

**2. Login**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'
```

**3. Create a Task**
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Authorization: Bearer YOUR_TOKEN_HERE" \
  -H "Content-Type: application/json" \
  -d '{"title":"Test Task","description":"Testing API","status":"TODO","priority":"HIGH"}'
```

### Using Postman

1. Import the API endpoints
2. Create an environment variable `token`
3. Use `{{token}}` in Authorization header
4. Test all endpoints with different roles

### H2 Console Access
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:taskdb`
- Username: `admin`
- Password: `admin123`

---

##  Key Concepts Demonstrated

### 1. JWT Authentication
- Token generation with user claims
- Token validation and extraction
- Stateless session management
- Token expiration handling

### 2. Spring Security
- Custom security filter chain
- Authentication provider configuration
- Password encoding with BCrypt
- CORS configuration

### 3. Role-Based Access Control
- Method-level security with `@PreAuthorize`
- Role hierarchy (USER → MANAGER → ADMIN)
- Resource-based authorization
- Custom access control logic

### 4. Clean Architecture
- Separation of concerns (Controller → Service → Repository)
- DTO pattern for data transfer
- Entity-DTO mapping
- Global exception handling

### 5. Best Practices
- Input validation with Bean Validation
- Proper HTTP status codes
- RESTful API design
- Secure coding practices

---

##  Built With

- **Spring Boot** - Application framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Data persistence
- **JWT (jjwt)** - Token-based authentication
- **Lombok** - Reducing boilerplate code
- **H2/MySQL** - Database
- **Maven** - Dependency management

---

##  License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

##  Author

**Your Name**
- GitHub: [@yourusername](https://github.com/yourusername)
- LinkedIn: [Your LinkedIn](https://linkedin.com/in/yourprofile)
- Email: your.email@example.com

---

##  Acknowledgments

- Spring Boot Team for the excellent framework
- Spring Security for robust security features
- JWT.io for token debugging tools
- The open-source community

---

##  Support

If you have any questions or issues, please open an issue on GitHub or contact me directly.

---

##  Future Enhancements

- [ ] Token refresh mechanism
- [ ] Email verification
- [ ] Password reset functionality
- [ ] Task comments and attachments
- [ ] Real-time notifications with WebSocket
- [ ] API rate limiting
- [ ] OAuth2 integration (Google, GitHub)
- [ ] Audit logging
- [ ] Task categories and tags
- [ ] Advanced search and filtering

---

**⭐ If you found this project helpful, please give it a star!**
