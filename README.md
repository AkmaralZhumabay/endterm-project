# Endterm Project — Student Course Registration API

This project is a Spring Boot REST API for managing students, courses, and enrollments using PostgreSQL.  
It demonstrates clean layered architecture, validation, exception handling, and RESTful design.

## Features
- CRUD operations for Students and Courses
- Student–Course enrollment(many-to-many)
- Address as a separate table linked to Student
- Validation using Jakarta Validation
- Global exception handling with meaningful HTTP responses
- JDBC with PostgreSQL
- REST API tested using Postman

## Project Structure
The project follows a layered architecture:
- Controller — handles HTTP requests
- Service — contains business logic and validation
- Repository — handles database access (JDBC)
- Model/DTO — domain and request objects
- Exception — centralized error handling

## Database
The database schema is defined in `src/main/resources/schema.sql` and is automatically initialized on application start.  
It includes tables:
- students
- courses
- enrollments
- addresses

## API Endpoints
- `POST /api/students` — create student
- `GET /api/students` — get all students
- `GET /api/students/{id}` — get student by id
- `PUT /api/students/{id}` — update student
- `DELETE /api/students/{id}` — delete student
- `GET /api/students/top-credits` — student with most credits

- `POST /api/courses` — create course
- `GET /api/courses` — get all courses
- `GET /api/courses/{id}` — get course by id
- `PUT /api/courses/{id}` — update course
- `DELETE /api/courses/{id}` — delete course

- `POST /api/enrollments` — enroll student in course
- `DELETE /api/enrollments` — remove enrollment

## Validation and Errors
- Invalid input returns HTTP 400
- Duplicate resources return HTTP 409
- Missing resources return HTTP 404
- Errors are returned in JSON format

## Screenshots
All Postman request and response screenshots are located in docs/screenshots
