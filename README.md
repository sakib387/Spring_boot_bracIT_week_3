# Dynamic Employee Management System (DEMS)

## Objective
Develop a robust Dynamic Employee Management System that showcases expertise in:
- Advanced PostgreSQL queries.
- Redis integration for caching.
- JDBC connection pooling.
- ACID-compliant database handling.

---

## Key Features

### **Authentication and Authorization**
- User login via JWT.
- Secure APIs with JWT validation.
- Role-based access control:
  - **Admin**: Full access to all APIs.
  - **User**: Limited access to view and search/filter employees.

### **Employee Management**
- Add, update, retrieve, and delete employee records.
- Store employee attributes such as:
  - Employee ID (UUID)
  - Name
  - Designation
  - Department
  - Date of Joining
  - Salary
  - Additional attributes in JSON (skills, certifications, etc.).

### **Search and Filtering**
- Search employees by name or department.
- Filter employees based on salary, joining date, or JSON attributes.
- Paginated API responses using `limit` and `offset`.

### **Caching**
- Frequently accessed employee profiles cached in Redis.
- Cache invalidation on updates or deletions.

### **Transactions**
- ACID-compliant batch operations (e.g., bulk employee imports).
- Rollback changes on failure during multi-step operations.

---

## API Endpoints

### **Authentication**
| Method | Endpoint           | Description                      | Access      |
|--------|---------------------|----------------------------------|-------------|
| POST   | `/auth/login`       | Authenticate user and return JWT | All users   |
| POST   | `/auth/register`    | Register a new user              | Admin only  |

### **Employee Management**
| Method | Endpoint              | Description                     | Access      |
|--------|------------------------|---------------------------------|-------------|
| POST   | `/employees`          | Add a new employee              | Admin only  |
| GET    | `/employees/{id}`     | Retrieve an employee by ID      | Admin, User |
| PUT    | `/employees/{id}`     | Update employee details         | Admin only  |
| DELETE | `/employees/{id}`     | Delete an employee              | Admin only  |

### **Search and Filtering**
| Method | Endpoint      | Description                                         | Access      |
|--------|---------------|-----------------------------------------------------|-------------|
| GET    | `/employees`  | Search, filter, and paginate employees              | Admin, User |
|        | Query params: | `name`, `department`, `minSalary`, `maxSalary`, etc.|             |

### **Bulk Operations**
| Method | Endpoint            | Description                           | Access     |
|--------|----------------------|---------------------------------------|------------|
| POST   | `/employees/bulk`   | Add or update multiple employees       | Admin only |

---

## Development Guidelines

### **PostgreSQL**
- Store additional attributes in `JSONB`.
- Index frequently queried fields (e.g., `name`, `department`, JSONB keys).
- Use `EXPLAIN ANALYZE` for query optimization.

### **Redis**
- Cache frequently accessed employee profiles with a TTL.
- Use Redis Hashes for structured data storage.
- Implement cache invalidation on updates or deletions.

### **Transactions**
- Ensure atomicity with Spring’s `@Transactional`.
- Rollback multi-step operations on failure.

### **Error Handling**
- Log errors for debugging.
- Return user-friendly error messages for API failures.

---

## Technologies Used
- **Backend Framework:** Spring Boot
- **Database:** PostgreSQL
- **Caching:** Redis
- **Authentication:** JWT
- **Connection Pooling:** HikariCP
- **Build Tool:** Maven

---

## Project Setup

### **Prerequisites**
- JDK 17 or higher
- PostgreSQL 14+
- Redis 6+
- Maven 3+

### **Steps to Run the Project**
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo-url/dynamic-employee-management-system.git
   cd dynamic-employee-management-system
