# System Architecture

```mermaid
flowchart TD
    U[User] --> M[Main Console Menu]
    M --> S[Student Portal]
    M --> O[Organizer Portal]
    M --> R[Reports]

    S --> ST[Student]
    S --> E[Event]
    S --> RG[Registration]

    O --> OR[Organizer]
    O --> E
    O --> RG
    O --> A[Attendance]

    R --> ST
    R --> E
    R --> RG
    R --> A

    ST --> F[FileManager]
    OR --> F
    E --> F
    RG --> F
    A --> F

    F --> D[(Text Files in data/)]
```

## Architectural style

The application follows a simple layered/modular console architecture:

- **Presentation layer:** `Main`, `Student`, `Organizer`, and menu methods.
- **Domain/data layer:** `Student`, `Event`, `Registration`, `Attendance`, `Organizer`.
- **Utility layer:** `Validator` and `FileManager`.
- **Reporting layer:** `Report`.
