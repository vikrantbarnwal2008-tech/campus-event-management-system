# Project Statement

## Project Title

**CampusConnect – College Event Management System**

## Problem Statement

College events involve multiple activities such as maintaining student records, creating events, registering participants, managing schedules and recording attendance. Handling these activities manually can make it difficult to keep information organized and consistent.

CampusConnect is a command-line Java application designed to provide a centralized way to manage college events and student participation.

## Scope of the Project

The project covers:

- Student registration and information management.
- Organizer registration.
- Event creation, update and deletion.
- Browsing available events.
- Student event registration and cancellation.
- Student schedule and participation history.
- Event participant viewing.
- Attendance generation.
- Basic reports and summaries.
- File-based data persistence.

The current implementation is a local console application and does not include a network server, online authentication or a database.

## Target Users

### Students
Students can create their records, browse events, register or cancel registrations, view their schedule and review participation history.

### Event Organizers
Organizers can create and manage their events, view participants and generate attendance.

### College Activity Coordinators
Reports provide summary information about events, registrations and attendance.

## High-Level Features

1. Student Portal
2. Organizer Portal
3. Event Management
4. Event Registration
5. Attendance Management
6. Reporting
7. Input Validation
8. File-Based Storage

## Functional Requirements

### FR1 – Student Registration
The system shall allow a student to create a student record using a unique student ID, name, email and course.

### FR2 – Event Browsing
The system shall display available events with date, time, venue and capacity.

### FR3 – Event Registration
The system shall allow a registered student to register for an available event.

### FR4 – Registration Cancellation
The system shall allow a student to cancel an existing event registration.

### FR5 – Schedule
The system shall display the events for which a student is registered.

### FR6 – Participation History
The system shall display a student's registered events and attendance status.

### FR7 – Event Creation
The system shall allow an organizer to create an event.

### FR8 – Event Modification
The system shall allow an organizer to update an event owned by that organizer.

### FR9 – Event Deletion
The system shall allow an organizer to delete an event and its related registration/attendance records.

### FR10 – Participant Viewing
The system shall allow an organizer to view registered participants for an event.

### FR11 – Attendance
The system shall allow an organizer to record attendance for registered participants.

### FR12 – Reporting
The system shall generate basic event, participation and attendance summaries.

## Non-Functional Requirements

### NFR1 – Usability
The interface should be understandable to a user with basic command-line knowledge.

### NFR2 – Reliability
The system should validate input and save application records to local files.

### NFR3 – Maintainability
The system should use separate classes for major entities and functions.

### NFR4 – Error Handling
Invalid input and unavailable records should produce clear messages instead of terminating normal program flow.

### NFR5 – Resource Efficiency
The application should operate using standard Java libraries without requiring an external server or database.

## Constraints

- Console-based interface.
- Local file storage.
- No external database in the current version.
- Single-machine execution.

## Future Scope

Database support, authentication, QR attendance, notifications, a GUI/web interface and richer analytics can be added in later versions.
