# Finance Dashboard Backend

## Overview

This project is a backend system for a finance dashboard application. It provides APIs to manage users, financial records, and summary analytics with role-based access control.

The system is designed with a clean architecture, proper validation, and structured business logic.

---

## Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* MySQL
* Maven

---

## Features

### 1. User Management

* Create users (ADMIN only)
* View all users
* Each user has:

  * Name
  * Email (unique)
  * Role (ADMIN, ANALYST, VIEWER)
  * Status (ACTIVE / INACTIVE)

---

### 2. Financial Records Management

Supports full CRUD operations:

* Create record (ADMIN only)
* View records (ADMIN, ANALYST)
* Update record (ADMIN only)
* Delete record (ADMIN only)

Each record contains:

* Amount
* Type (INCOME / EXPENSE)
* Category
* Date
* Description

---

### 3. Filtering & Pagination

* Filter by:

  * Type
  * Category
* Supports combined filtering (type + category)
* Pagination:

  * page
  * size

---

### 4. Dashboard Summary APIs

* Total Income
* Total Expense
* Net Balance
* Category-wise totals
* Monthly trends
* Weekly trends
* Recent transactions

---

### 5. Access Control

| Role    | Permissions                      |
| ------- | -------------------------------- |
| VIEWER  | Cannot access records or summary |
| ANALYST | Can view records and summary     |
| ADMIN   | Full access                      |

---

### 6. Validation & Error Handling

* Input validation using annotations
* Proper HTTP status codes:

  * 400 → Validation errors
  * 403 → Unauthorized access
  * 404 → Resource not found
* Structured error responses

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

## Database

* MySQL database
* Auto schema update using Hibernate

---

## Assumptions

* Role is passed through request headers (mock authentication)
* No authentication system implemented (kept simple for assignment)

---

## How to Run

1. Clone the repository
2. Configure MySQL in `application.properties`
3. Run the Spring Boot application
4. Access APIs via Swagger or Postman

---

## Notes

This project focuses on backend design, API structure, and business logic implementation. It is not intended to be production-ready but demonstrates clean and maintainable backend development practices.
