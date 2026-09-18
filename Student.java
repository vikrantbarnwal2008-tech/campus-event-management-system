import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    public static final ArrayList<Student> students = new ArrayList<>();

    private final int studentId;
    private String name;
    private String email;
    private String course;

    public Student(int studentId, String name, String email, String course) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.course = course;
    }

    public int getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCourse() { return course; }

    public static void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n=============== STUDENT PORTAL ===============");
            System.out.println("1. Register");
            System.out.println("2. Browse Events");
            System.out.println("3. Register for Event");
            System.out.println("4. Cancel Registration");
            System.out.println("5. View Schedule");
            System.out.println("6. View Participation History");
            System.out.println("7. Back");

            int choice = Validator.readInt(scanner, "Enter choice: ");

            switch (choice) {
                case 1 -> register(scanner);
                case 2 -> Event.displayEvents();
                case 3 -> registerForEvent(scanner);
                case 4 -> cancelRegistration(scanner);
                case 5 -> viewSchedule(scanner);
                case 6 -> viewParticipationHistory(scanner);
                case 7 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void register(Scanner scanner) {
        int id = Validator.readInt(scanner, "Enter student ID: ");
        if (findStudent(id) != null) {
            System.out.println("A student with this ID already exists.");
            return;
        }

        String name = Validator.readNonEmpty(scanner, "Enter full name: ");
        String email = Validator.readEmail(scanner, "Enter email: ");
        String course = Validator.readNonEmpty(scanner, "Enter course: ");

        students.add(new Student(id, name, email, course));
        FileManager.saveAllData();
        System.out.println("Student registered successfully.");
    }

    private static void registerForEvent(Scanner scanner) {
        int studentId = Validator.readInt(scanner, "Enter student ID: ");
        Student student = findStudent(studentId);

        if (student == null) {
            System.out.println("Student not found. Use Student Portal -> Register first.");
            return;
        }

        Event.displayEvents();
        int eventId = Validator.readInt(scanner, "Enter event ID: ");
        Event event = Event.findEvent(eventId);

        if (event == null) {
            System.out.println("Event not found.");
            return;
        }

        if (Registration.isRegistered(studentId, eventId)) {
            System.out.println("Student is already registered for this event.");
            return;
        }

        if (Registration.countForEvent(eventId) >= event.getCapacity()) {
            System.out.println("This event is full.");
            return;
        }

        int registrationId = Registration.nextId();
        Registration.registrations.add(
            new Registration(registrationId, studentId, eventId)
        );
        FileManager.saveAllData();

        System.out.println("Registration successful. Registration ID: " + registrationId);
    }

    private static void cancelRegistration(Scanner scanner) {
        int studentId = Validator.readInt(scanner, "Enter student ID: ");
        int eventId = Validator.readInt(scanner, "Enter event ID: ");

        Registration registration = Registration.findByStudentAndEvent(studentId, eventId);
        if (registration == null) {
            System.out.println("Registration not found.");
            return;
        }

        Registration.registrations.remove(registration);
        Attendance.remove(studentId, eventId);
        FileManager.saveAllData();
        System.out.println("Registration cancelled successfully.");
    }

    private static void viewSchedule(Scanner scanner) {
        int studentId = Validator.readInt(scanner, "Enter student ID: ");
        Student student = findStudent(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        boolean found = false;
        System.out.println("\n---------------- MY SCHEDULE ----------------");
        for (Registration r : Registration.registrations) {
            if (r.getStudentId() == studentId) {
                Event event = Event.findEvent(r.getEventId());
                if (event != null) {
                    System.out.println(event.summary());
                    found = true;
                }
            }
        }
        if (!found) System.out.println("No registered events.");
        System.out.println("----------------------------------------------");
    }

    private static void viewParticipationHistory(Scanner scanner) {
        int studentId = Validator.readInt(scanner, "Enter student ID: ");
        Student student = findStudent(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("\n--------- PARTICIPATION HISTORY ---------");
        boolean found = false;
        for (Registration r : Registration.registrations) {
            if (r.getStudentId() == studentId) {
                Event event = Event.findEvent(r.getEventId());
                if (event != null) {
                    Attendance a = Attendance.find(event.getEventId(), studentId);
                    String status = a == null ? "Attendance not marked"
                            : (a.isPresent() ? "Present" : "Absent");
                    System.out.println(event.getEventName() + " | " + event.getDate()
                            + " " + event.getTime() + " | " + status);
                    found = true;
                }
            }
        }
        if (!found) System.out.println("No participation history found.");
        System.out.println("------------------------------------------");
    }

    public static Student findStudent(int id) {
        for (Student s : students) {
            if (s.studentId == id) return s;
        }
        return null;
    }

    public void setDetails(String name, String email, String course) {
        this.name = name;
        this.email = email;
        this.course = course;
    }
}
