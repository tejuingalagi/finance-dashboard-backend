# Finance Data Processing and Access Control Backend

## Overview

This project is a backend system built as part of a backend development assessment. It simulates a finance dashboard where users interact with financial records based on their roles.

The focus of this project is on:

* Clean API design
* Proper data modeling
* Role-based access control
* Business logic implementation
* Validation and error handling

---

## Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* MySQL
* Maven

---

## Architecture & Design

The application follows a layered architecture:

* **Controller Layer** → Handles API requests and responses
* **Service Layer** → Contains business logic
* **Repository Layer** → Handles database operations
* **Entity Layer** → Defines data models

This separation ensures clarity, maintainability, and scalability.

---

## Core Requirements Implementation

### 1. User & Role Management

* Users can be created and managed
* Each user has:

  * Name
  * Email (unique)
  * Role (ADMIN, ANALYST, VIEWER)
  * Status (ACTIVE / INACTIVE)

#### Role Behavior:

| Role    | Access                                               |
| ------- | ---------------------------------------------------- |
| VIEWER  | Restricted from records and summary                  |
| ANALYST | Can view records and summary                         |
| ADMIN   | Full access (create, update, delete users & records) |

---

### 2. Financial Records Management

Supports full CRUD operations:

* Create records (ADMIN only)
* View records (ADMIN, ANALYST)
* Update records (ADMIN only)
* Delete records (ADMIN only)

Each record includes:

* Amount
* Type (INCOME / EXPENSE)
* Category
* Date
* Description

---

### 3. Filtering & Pagination

* Filter records by:

  * Type
  * Category
* Supports combined filtering (type + category)
* Pagination implemented using:

  * page
  * size

---

### 4. Dashboard Summary APIs

Provides aggregated data for dashboard:

* Total Income
* Total Expense
* Net Balance
* Category-wise totals
* Monthly trends
* Weekly trends
* Recent transactions

---

### 5. Access Control Logic

Access control is implemented at the controller level using role checks from request headers.

Examples:

* VIEWER → Cannot access records or summary APIs
* ANALYST → Can access records and summary
* ADMIN → Full access to all APIs

---

### 6. Validation & Error Handling

* Input validation using annotations (`@Valid`, `@NotBlank`, etc.)
* Proper HTTP status codes:

  * 400 → Validation errors
  * 403 → Unauthorized access
  * 404 → Resource not found
* Centralized exception handling using `@RestControllerAdvice`

---

### 7. Data Persistence

* MySQL database used for persistence
* Spring Data JPA for ORM
* Hibernate auto schema update enabled

---

## API Examples

### Create Record

```
POST /records
Header: role = ADMIN
```

### Get Records

```
GET /records?type=INCOME&category=SALARY&page=0&size=5
Header: role = ANALYST
```

### Delete Record

```
DELETE /records/{id}
Header: role = ADMIN
```

---

## Assumptions

* Role is passed via request headers (mock authentication)
* No authentication mechanism implemented to keep focus on backend logic
* Case-insensitive role handling is supported

---

## How to Run

1. Clone the repository
2. Configure MySQL in `application.properties`
3. Run the Spring Boot application
4. Access APIs via Swagger UI or Postman

---

## Author

**Tejeshwini Ingalagi**


