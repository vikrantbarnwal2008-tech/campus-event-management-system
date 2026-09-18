# CampusConnect – Project Report Draft

> **Important:** Complete the bracketed fields and replace/add your own screenshots and observations before submitting. The course PDF requires a detailed PDF report with the sections below.

## Cover Page

**Project Title:** CampusConnect – College Event Management System  
**Course:** Programming in Java  
**Student Name:** [Your Name]  
**Registration/Roll Number:** [Your Details]  
**Institution:** VIT Bhopal University  
**Academic Year:** 2026  

## 1. Introduction

CampusConnect is a command-line Java application for managing college events, students, organizers, registrations and attendance.

## 2. Problem Statement

College event information can be difficult to maintain when records are handled manually. The project provides a centralized console-based solution for common event-management activities.

## 3. Functional Requirements

- Student registration
- Event browsing
- Event registration and cancellation
- Schedule and participation history
- Organizer registration
- Event CRUD operations
- Participant viewing
- Attendance generation
- Reports

## 4. Non-functional Requirements

- Usability
- Reliability
- Maintainability
- Error handling
- Resource efficiency

## 5. System Architecture

Refer to `docs/architecture.md`.

## 6. Design Diagrams

Refer to:
- `docs/use-case.md`
- `docs/workflow.md`
- `docs/sequence-diagram.md`
- `docs/class-diagram.md`

## 7. Design Decisions and Rationale

### Java classes
Separate classes represent the main entities and responsibilities.

### ArrayList
`ArrayList` is used for simple in-memory collections during execution.

### File storage
Text files are used so that the application can run from a terminal without requiring a database server.

### Validation
A dedicated `Validator` class keeps common input checks separate from business logic.

## 8. Implementation Details

The program starts from `Main.java`. Student and organizer portals provide menu-driven operations. `Registration` connects students to events. `Attendance` records participation status. `Report` calculates summaries. `FileManager` saves and loads records.

## 9. Screenshots / Results

Add your own screenshots here:

1. Main menu
2. Student registration
3. Event creation
4. Event browsing
5. Event registration
6. Participant list
7. Attendance generation
8. Reports
9. Validation test output

## 10. Testing Approach

Run:

```bash
javac *.java
java ValidationTest
```

The validation test covers:
- Valid and invalid email formats
- Valid and invalid names
- Student lookup
- Event lookup
- Registration lookup
- Capacity counting
- Attendance lookup
- Attendance status

Also perform a manual end-to-end test using the demonstration flow in `README.md`.

## 11. Challenges Faced

[Write the actual challenges you experienced while building and testing the project.]

## 12. Learnings and Key Takeaways

[Write your own learning points. For example, explain what you learned about classes, collections, file handling, validation and modular design.]

## 13. Future Enhancements

- Database integration
- Authentication
- QR-code attendance
- Notifications
- GUI/web interface
- Advanced search and analytics

## 14. References

- VITyarthi Build Your Own Project instruction document.
- Java documentation/resources used during implementation: [add the resources you actually used].
