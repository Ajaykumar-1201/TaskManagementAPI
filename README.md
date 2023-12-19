# Task Management API

## Overview

The Task Management API is designed to help users manage tasks effectively. It provides endpoints for creating, updating, and deleting tasks, as well as user management features.

## Authentication

The API uses Basic Authentication. When making requests, include the `Authorization` header with the value `Basic <base64-encoded-credentials>`. The base64-encoded credentials should be the username and password separated by a colon (`:`).

## Endpoints

### Users

#### Create User

- **Endpoint:** `POST /users/create`
- **Description:** Create a new user.
- **Request:**
  - Method: `POST`
  - Path: `/users/create`
  - Body: JSON containing user details (username, password).
- **Response:**
  - Status: 201 Created
  - Body: JSON representing the created user.

#### Get User by ID

- **Endpoint:** `GET /users/{userId}`
- **Description:** Retrieve user details by ID.
- **Request:**
  - Method: `GET`
  - Path: `/users/{userId}`
- **Response:**
  - Status: 200 OK
  - Body: JSON representing the user.

#### Update User

- **Endpoint:** `PUT /users/{userId}`
- **Description:** Update user details by ID.
- **Request:**
  - Method: `PUT`
  - Path: `/users/{userId}`
  - Body: JSON containing updated user details.
- **Response:**
  - Status: 200 OK
  - Body: JSON representing the updated user.

### Tasks

#### Add Task

- **Endpoint:** `POST /tasks`
- **Description:** Add a new task.
- **Request:**
  - Method: `POST`
  - Path: `/tasks`
  - Body: JSON containing task details (title, description, dueDate).
- **Response:**
  - Status: 201 Created
  - Body: JSON representing the created task.

#### Get All Tasks

- **Endpoint:** `GET /tasks`
- **Description:** Retrieve all tasks for the authenticated user.
- **Request:**
  - Method: `GET`
  - Path: `/tasks`
- **Response:**
  - Status: 200 OK
  - Body: JSON array representing tasks.

... [Additional Endpoints]

## Models

### Task

Represents a task to be completed.

- `id`: Task ID (Long)
- `title`: Task title (String)
- `description`: Task description (String)
- `dueDate`: Due date of the task (LocalDate)
- `status`: Task status (Enum: PENDING, COMPLETED)
- `user`: User associated with the task (User)

### User

Represents a user of the system.

- `id`: User ID (Long)
- `username`: User's username (String)
- `password`: User's password (String)
- `isAdmin`: Indicates if the user is an administrator (boolean)
- `tasks`: List of tasks associated with the user (List<Task>)

## Dependencies

- Java
- Spring Boot
- Jakarta Persistence API (JPA)
- H2 Database (for demo purposes)

## Getting Started

1. Clone the repository.
2. Configure your database settings in `application.properties`.
3. Build and run the application.
4. Access the API at `http://localhost:8080`.


