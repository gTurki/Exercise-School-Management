# School Management System

A Spring Boot REST API for managing teachers, addresses, courses and students, built to practice JPA relationships (One-to-One, One-to-Many, Many-to-Many).

## Tech Stack

- Java + Spring Boot
- Spring Data JPA (Hibernate)
- MySQL
- Lombok
- Jakarta Validation

## Project Structure

```
com.example.exerciseschoolmanagement
├── Advice        → ControllerAdvice (global exception handling)
├── Api           → ApiException, ApiResponse
├── Controller    → Teacher, Address, Course, Student controllers
├── DTO           → AddressDTO
├── Entity        → Teacher, Address, Course, Student
├── Repository    → JPA repositories
└── Service       → Business logic
```

## Entities

| Entity  | Fields                                   |
|---------|------------------------------------------|
| Teacher | id, name, age, email, salary             |
| Address | id, area, street, buildingNumber         |
| Course  | id, name                                 |
| Student | id, name, age, major                     |

All fields have validation (not empty / not null, size, min/max, email format, positive numbers).

## Relationships

| Relation     | Entities          | Description                                          |
|--------------|-------------------|------------------------------------------------------|
| One-to-One   | Teacher – Address | Each teacher has one address (address uses `@MapsId`, so its id = teacher id) |
| One-to-Many  | Teacher – Course  | A teacher teaches many courses (`teacher_id` foreign key in course table) |
| Many-to-Many | Course – Student  | A course has many students and a student attends many courses (join table) |

---

## Exercise: JPA Relation II

Adds the **Course** entity and the **One-to-Many** relation between Teacher and Course.

### Teacher Endpoints — `/api/v1/teacher`

| Method | Endpoint          | Description                          |
|--------|-------------------|--------------------------------------|
| GET    | `/get`            | Get all teachers                     |
| POST   | `/add`            | Add new teacher                      |
| PUT    | `/update/{id}`    | Update teacher                       |
| DELETE | `/delete/{id}`    | Delete teacher                       |
| GET    | `/details/{id}`   | Get all teacher details (with address and courses) |

### Address Endpoints — `/api/v1/address`

| Method | Endpoint                | Description             |
|--------|-------------------------|-------------------------|
| POST   | `/add`                  | Add teacher address     |
| PUT    | `/update`               | Update teacher address  |
| DELETE | `/delete/{teacherId}`   | Delete teacher address  |

### Course Endpoints — `/api/v1/course`

| Method | Endpoint                    | Description                         |
|--------|-----------------------------|-------------------------------------|
| GET    | `/get`                      | Get all courses                     |
| POST   | `/add/{teacherId}`          | Add new course for a teacher        |
| PUT    | `/update/{id}`              | Update course                       |
| DELETE | `/delete/{id}`              | Delete course                       |
| GET    | `/teacher-name/{courseId}`  | Get the teacher name of a course    |

---

## Exercise: JPA Relation III

Adds the **Student** entity and the **Many-to-Many** relation between Course and Student.

### Student Endpoints — `/api/v1/student`

| Method | Endpoint                             | Description                                         |
|--------|--------------------------------------|-----------------------------------------------------|
| GET    | `/get`                               | Get all students                                    |
| POST   | `/add`                               | Add new student                                     |
| PUT    | `/update/{id}`                       | Update student                                      |
| DELETE | `/delete/{id}`                       | Delete student                                      |
| PUT    | `/change-major/{studentId}/{major}`  | Change student major (drops all the student's courses) |

### Extra Course Endpoints — `/api/v1/course`

| Method | Endpoint                          | Description                     |
|--------|-----------------------------------|---------------------------------|
| PUT    | `/{courseId}/assign/{studentId}`  | Assign a student to a course    |
| GET    | `/students/{courseId}`            | Get the student list of a course |

---

## Example Request Bodies

**Add teacher**
```json
{
  "name": "Mohammed",
  "age": 35,
  "email": "mohammed@school.com",
  "salary": 9000
}
```

**Add address**
```json
{
  "teacher_id": 1,
  "area": "Olaya",
  "street": "King Fahd",
  "buildingNumber": 12
}
```

**Add course**
```json
{
  "name": "Math"
}
```

**Add student**
```json
{
  "name": "Ahmed",
  "age": 20,
  "major": "CS"
}
```

## Suggested Testing Order

1. Add a teacher
2. Add an address for the teacher
3. Add a course for the teacher
4. Add a student
5. Assign the student to the course
6. Get the course's students
7. Change the student's major and check that the course list is now empty

## Error Handling

All errors are handled globally in `ControllerAdvice` and return a JSON message with status `400`, for example:

```json
{
  "message": "Teacher not found"
}
```
