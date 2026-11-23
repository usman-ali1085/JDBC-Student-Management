# JDBC Student Management System

A console-based Student Management System built using Java, JDBC, Maven, and MariaDB. This project demonstrates database connectivity and CRUD operations using Java.

## Features

- Add new students
- View all students
- Search students by ID
- Update student details
- Delete student records
- Validate student input
- Use prepared statements for database operations
- Manage database connections using JDBC

## Technologies Used

- Java 25
- JDBC
- MariaDB
- Maven
- IntelliJ IDEA

## Project Structure

```text
JDBC_Student_Management/
├── database/
│   └── schema.sql
├── src/
│   └── main/
│       └── java/
│           └── org/
│               └── example/
│                   ├── Main.java
│                   ├── StudentManager.java
│                   └── DBConnection.java
├── .gitignore
├── pom.xml
└── README.md
```

## Prerequisites

Before running the project, make sure you have:

- JDK 25 or compatible Java environment
- MariaDB Server
- Maven
- An IDE such as IntelliJ IDEA (optional)

## Database Setup

1. Start your MariaDB server.
2. Execute the SQL script located at `database/schema.sql`.
3. This creates the `student_db` database and the `students` table.

## Database Configuration

The application uses environment variables to keep database credentials out of the source code.

Configure these three environment variables before running the application:

| Variable | Example |
|---|---|
| `DB_URL` | `jdbc:mariadb://localhost:3306/student_db` |
| `DB_USER` | Your MariaDB username |
| `DB_PASSWORD` | Your MariaDB password |

Never commit actual database credentials to the repository.

## Running the Project

1. Clone the repository:

```bash
git clone https://github.com/usman-ali1085/JDBC-Student-Management.git
```

2. Open the project in IntelliJ IDEA as a Maven project.

3. Configure the database environment variables in your run configuration.

4. Run `Main.java`.

The application will display a menu through which you can manage student records.

## Learning Outcomes

This project helped demonstrate practical understanding of:

- Java Database Connectivity (JDBC)
- SQL CRUD operations
- PreparedStatement and ResultSet
- Exception handling
- Maven dependency management
- Database connection management
- Git and GitHub project management

## Author

Created as part of my learning journey.