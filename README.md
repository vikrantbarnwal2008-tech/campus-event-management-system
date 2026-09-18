# CampusConnect – College Event Management System

## 1. Project Overview

CampusConnect is a command-line based Java application for managing college events and student participation.

The system separates the main workflows into two portals:

- **Student Portal** – student registration, event browsing, event registration, cancellation, schedule and participation history.
- **Organizer Portal** – organizer registration, event creation, update, deletion, participant viewing and attendance generation.
- **Reports** – dashboard and event/student/attendance summaries.

The application uses Java collections for in-memory processing and text files for persistent storage.

## 2. Problem Statement

College event information can become difficult to manage when student records, event details, registrations and attendance are maintained manually or in separate places. CampusConnect provides a single console application to organize these activities and make common event operations easier to perform.

## 3. Main Features

### Student features
1. Register a student.
2. Browse available events.
3. Register for an event.
4. Cancel an event registration.
5. View registered-event schedule.
6. View participation and attendance history.

### Organizer features
1. Register an organizer.
2. Create an event.
3. Update an event.
4. Delete an event.
5. View event participants.
6. Generate attendance.

### Reporting features
1. Dashboard summary.
2. Event registration report.
3. Student participation report.
4. Attendance summary.

### Validation and reliability
- Numeric input validation.
- Email validation.
- Empty-input validation.
- Duplicate student/organizer ID checks.
- Duplicate event-registration prevention.
- Event-capacity checking.
- Organizer ownership checking for event modification.
- File-based persistence.

## 4. Technologies Used

- Java
- Java Collections Framework (`ArrayList`)
- File I/O
- Exception handling
- Object-oriented programming
- Command-line interface
- Git/GitHub for version control

No external libraries are required.

## 5. Project Structure

```text
vityarthi project java/
│
├── Main.java
├── Student.java
├── Event.java
├── Registration.java
├── Attendance.java
├── Organizer.java
├── Report.java
├── Validator.java
├── FileManager.java
├── ValidationTest.java
│
├── README.md
├── statement.md
├── .gitignore
│
├── data/
│   ├── students.txt
│   ├── organizers.txt
│   ├── events.txt
│   ├── registrations.txt
│   └── attendance.txt
│
└── docs/
    ├── architecture.md
    ├── workflow.md
    ├── use-case.md
    ├── class-diagram.md
    └── sequence-diagram.md
```

## 6. Requirements

Install:

- JDK 17 or later
- Command Prompt / PowerShell / Terminal

Check Java:

```bash
java -version
javac -version
```

## 7. Compile the Project

Open a terminal in the project root:

```bash
javac *.java
```

If compilation finishes without an error, the project is ready to run.

## 8. Run the Application

```bash
java Main
```

Main menu:

```text
1. Student Portal
2. Organizer Portal
3. Reports
4. Save Data
5. Exit
```

## 9. Run Validation Tests

```bash
javac *.java
java ValidationTest
```

Expected output includes `PASS` for the validation, student, event, registration and attendance checks.

## 10. Data Storage

The application stores data in the `data/` directory:

- `students.txt`
- `organizers.txt`
- `events.txt`
- `registrations.txt`
- `attendance.txt`

The files use pipe-separated records. The application automatically creates the `data` directory if it does not exist.

## 11. Suggested Demonstration Flow

For a quick demonstration:

1. Open **Organizer Portal**.
2. Register an organizer, for example ID `1`.
3. Create an event and note its event ID.
4. Open **Student Portal**.
5. Register a student, for example ID `101`.
6. Browse events.
7. Register the student for the event.
8. Return to Organizer Portal.
9. View participants.
10. Generate attendance.
11. Open Student Portal and view participation history.
12. Open Reports and show the dashboard and attendance summary.

## 12. Functional Modules

The project contains more than the required three major functional modules:

- Student Management
- Event Management
- Registration Management
- Attendance Management
- Reporting

## 13. Non-Functional Requirements

- **Usability:** Menu-driven console interface with clear prompts.
- **Reliability:** Input validation and file persistence reduce invalid records and data loss during normal execution.
- **Maintainability:** Functionality is separated into focused Java classes.
- **Error handling:** Invalid numeric input, empty fields, invalid email addresses and invalid entity IDs are handled with user-facing messages.
- **Resource efficiency:** The application uses lightweight collections and text files and requires no external database.

## 14. Future Enhancements

- Database integration using JDBC/MySQL.
- Role-based login and authentication.
- QR-code attendance.
- Event notifications.
- Graphical or web interface.
- Search and filtering by date, venue or category.
- Export reports to CSV/PDF.

## 15. Author

Programming in Java – VITyarthi Project.

> Before submission, replace this section with your own name, registration details if required by your course, and any other information your instructor asks you to include.
