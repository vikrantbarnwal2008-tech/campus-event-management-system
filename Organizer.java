import java.util.ArrayList;
import java.util.Scanner;

public class Organizer {
    public static final ArrayList<Organizer> organizers = new ArrayList<>();

    private final int organizerId;
    private String name;
    private String email;
    private String department;

    public Organizer(int organizerId, String name, String email, String department) {
        this.organizerId = organizerId;
        this.name = name;
        this.email = email;
        this.department = department;
    }

    public int getOrganizerId() { return organizerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }

    public static void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n=============== ORGANIZER PORTAL ===============");
            System.out.println("1. Register Organizer");
            System.out.println("2. Create Event");
            System.out.println("3. Update Event");
            System.out.println("4. Delete Event");
            System.out.println("5. View Participants");
            System.out.println("6. Generate Attendance");
            System.out.println("7. Back");

            int choice = Validator.readInt(scanner, "Enter choice: ");

            switch (choice) {
                case 1 -> register(scanner);
                case 2 -> createEvent(scanner);
                case 3 -> updateEvent(scanner);
                case 4 -> deleteEvent(scanner);
                case 5 -> viewParticipants(scanner);
                case 6 -> generateAttendance(scanner);
                case 7 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void register(Scanner scanner) {
        int id = Validator.readInt(scanner, "Enter organizer ID: ");
        if (findOrganizer(id) != null) {
            System.out.println("Organizer ID already exists.");
            return;
        }

        String name = Validator.readNonEmpty(scanner, "Enter name: ");
        String email = Validator.readEmail(scanner, "Enter email: ");
        String department = Validator.readNonEmpty(scanner, "Enter department/club: ");

        organizers.add(new Organizer(id, name, email, department));
        FileManager.saveAllData();
        System.out.println("Organizer registered successfully.");
    }

    private static Organizer requireOrganizer(Scanner scanner) {
        int id = Validator.readInt(scanner, "Enter organizer ID: ");
        Organizer organizer = findOrganizer(id);
        if (organizer == null) {
            System.out.println("Organizer not found. Register an organizer first.");
        }
        return organizer;
    }

    private static void createEvent(Scanner scanner) {
        Organizer organizer = requireOrganizer(scanner);
        if (organizer == null) return;

        String name = Validator.readNonEmpty(scanner, "Event name: ");
        String date = Validator.readNonEmpty(scanner, "Date (DD-MM-YYYY): ");
        String time = Validator.readNonEmpty(scanner, "Time (e.g. 10:30 AM): ");
        String venue = Validator.readNonEmpty(scanner, "Venue: ");
        int capacity = Validator.readPositiveInt(scanner, "Capacity: ");

        int id = Event.nextId();
        Event.events.add(new Event(id, name, date, time, venue, capacity,
                organizer.organizerId));
        FileManager.saveAllData();
        System.out.println("Event created successfully. Event ID: " + id);
    }

    private static void updateEvent(Scanner scanner) {
        Organizer organizer = requireOrganizer(scanner);
        if (organizer == null) return;

        int eventId = Validator.readInt(scanner, "Event ID to update: ");
        Event event = Event.findEvent(eventId);

        if (event == null || event.getOrganizerId() != organizer.organizerId) {
            System.out.println("Event not found or it does not belong to this organizer.");
            return;
        }

        String name = Validator.readNonEmpty(scanner, "New event name: ");
        String date = Validator.readNonEmpty(scanner, "New date: ");
        String time = Validator.readNonEmpty(scanner, "New time: ");
        String venue = Validator.readNonEmpty(scanner, "New venue: ");
        int capacity = Validator.readPositiveInt(scanner, "New capacity: ");

        if (capacity < Registration.countForEvent(eventId)) {
            System.out.println("Capacity cannot be lower than current registrations.");
            return;
        }

        event.update(name, date, time, venue, capacity);
        FileManager.saveAllData();
        System.out.println("Event updated successfully.");
    }

    private static void deleteEvent(Scanner scanner) {
        Organizer organizer = requireOrganizer(scanner);
        if (organizer == null) return;

        int eventId = Validator.readInt(scanner, "Event ID to delete: ");
        Event event = Event.findEvent(eventId);

        if (event == null || event.getOrganizerId() != organizer.organizerId) {
            System.out.println("Event not found or it does not belong to this organizer.");
            return;
        }

        Event.events.remove(event);
        Registration.registrations.removeIf(r -> r.getEventId() == eventId);
        Attendance.attendanceList.removeIf(a -> a.getEventId() == eventId);
        FileManager.saveAllData();
        System.out.println("Event and related records deleted successfully.");
    }

    private static void viewParticipants(Scanner scanner) {
        Organizer organizer = requireOrganizer(scanner);
        if (organizer == null) return;

        int eventId = Validator.readInt(scanner, "Event ID: ");
        Event event = Event.findEvent(eventId);

        if (event == null || event.getOrganizerId() != organizer.organizerId) {
            System.out.println("Event not found or it does not belong to this organizer.");
            return;
        }

        System.out.println("\nParticipants for: " + event.getEventName());
        boolean found = false;

        for (Registration r : Registration.registrations) {
            if (r.getEventId() == eventId) {
                Student student = Student.findStudent(r.getStudentId());
                if (student != null) {
                    System.out.println("Registration ID: " + r.getRegistrationId()
                            + " | " + student.getName()
                            + " | " + student.getEmail()
                            + " | " + student.getCourse());
                    found = true;
                }
            }
        }

        if (!found) System.out.println("No participants registered.");
    }

    private static void generateAttendance(Scanner scanner) {
        Organizer organizer = requireOrganizer(scanner);
        if (organizer == null) return;

        int eventId = Validator.readInt(scanner, "Event ID: ");
        Event event = Event.findEvent(eventId);

        if (event == null || event.getOrganizerId() != organizer.organizerId) {
            System.out.println("Event not found or it does not belong to this organizer.");
            return;
        }

        Attendance.markForEvent(scanner, eventId);
    }

    public static Organizer findOrganizer(int id) {
        for (Organizer o : organizers) {
            if (o.organizerId == id) return o;
        }
        return null;
    }
}
