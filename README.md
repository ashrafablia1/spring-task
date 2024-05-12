**Backend Development:**

**Set Up the Project:**
- Spring Boot
- Java 17.
- spring web (mvc, restFul)
- spring Data JPA

**Database Setup:**
- Use PostgreSQL as the database.
- table: "users"
- Fields: id, first_name, last_name, password, and username.
- Ensure username uniqueness with a unique constraint.
- Create an index for faster username lookups.

- DBname: usertask
- username: postgres
- password: admin

```sql
create table users (
    id bigserial not null,
    first_name varchar(36) not null,
    last_name varchar(36) not null,
    password varchar(255) not null,
    username varchar(36) unique,
    primary key (id)
);
CREATE INDEX idx_username ON users (username);
```

**Design Architecture:**
- Follow the MVC pattern (Model-View-Controller).

**TDD Methodology:**
- Adopt Test-Driven Development (TDD) approach.
- Write unit tests for Repository and Service layers using JUnit.

**Implement RESTful Endpoints:**
- Endpoints for CRUD operations:
  - GET /api/all/users: Retrieves a list of all users.
  - POST /api/create/user: Creates a new user.
  - POST /api/update/{id}: Updates an existing user.
  - DELETE /api/delete/user/{id}: Deletes a user by ID.
  - GET /api/user/{id}: Retrieves a user by ID.
  - GET /api/usernameAvailable/{username}: Checks if a username is available.

**Controller Testing:**
- Test the controllers using Postman.

**Frontend Development:**

- Display a list of all available users.
- Implement add, update, and delete users.

**Form Validation:**
- First Name: Free text (alphabetic characters) with max length 36.
- Last Name: Free text (alphabetic characters) with max length 36.
- Username: Free text (alphanumeric characters) with max length 36 and uniqueness.
- Password: Free text (alphanumeric characters) with minimum 8 characters, including uppercase letters, lowercase letters, numbers, and special characters.
