# Student Management System

A beginner-friendly Spring Boot MVC CRUD project using Java 17, Maven, Spring Data JPA, Hibernate, MySQL, Thymeleaf and Bootstrap.

## Features

- List all students
- Add a student
- Edit a student
- Delete a student
- Basic validation for name, email and course
- MySQL persistence through Spring Data JPA

## Requirements

- JDK 17 or later
- Maven 3.8+
- MySQL Server
- IDE such as IntelliJ IDEA, Eclipse or Spring Tool Suite

## Setup

1. Extract the ZIP file and open the `student-management-system` folder in your IDE as a Maven project.
2. Start MySQL.
3. Open `src/main/resources/application.properties`.
4. Replace `YOUR_MYSQL_PASSWORD` with your local MySQL root password. If your MySQL username is not `root`, update that too.
5. The JDBC URL uses `createDatabaseIfNotExist=true`, so the `student_management` database is created automatically if the configured MySQL user has permission. Otherwise run:
   ```sql
   CREATE DATABASE student_management;
   ```
6. Wait for Maven to download dependencies.
7. Run `StudentManagementApplication.java` as a Java application, or from the project folder run:
   ```bash
   mvn spring-boot:run
   ```
8. Open http://localhost:8080/students in your browser.

## Main project structure

```text
src/main/java/com/example/studentmanagement/
├── StudentManagementApplication.java
├── controller/
│   └── StudentController.java
├── entity/
│   └── Student.java
├── repository/
│   └── StudentRepository.java
└── service/
    ├── StudentService.java
    └── StudentServiceImpl.java

src/main/resources/
├── application.properties
└── templates/
    ├── students.html
    └── create_student.html
```

## Important notes

- `spring.jpa.hibernate.ddl-auto=update` is convenient for learning; for production, use database migrations and managed schema changes.
- This sample uses standard Spring MVC form posts. Add authentication, authorization, CSRF-aware security configuration, and stronger error handling before using it in production.
- If the application fails to connect to MySQL, check that MySQL is running and that the username/password in `application.properties` are correct.
