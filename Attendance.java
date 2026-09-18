import java.util.ArrayList;
import java.util.Scanner;

public class Attendance {
    public static final ArrayList<Attendance> attendanceList = new ArrayList<>();

    private final int eventId;
    private final int studentId;
    private boolean present;

    public Attendance(int eventId, int studentId, boolean present) {
        this.eventId = eventId;
        this.studentId = studentId;
        this.present = present;
    }

    public int getEventId() { return eventId; }
    public int getStudentId() { return studentId; }
    public boolean isPresent() { return present; }

    public static void markForEvent(Scanner scanner, int eventId) {
        Event event = Event.findEvent(eventId);
        if (event == null) {
            System.out.println("Event not found.");
            return;
        }

        int registered = 0;
        for (Registration r : Registration.registrations) {
            if (r.getEventId() == eventId) {
                registered++;
                Student student = Student.findStudent(r.getStudentId());
                if (student == null) continue;

                System.out.print("Is " + student.getName() + " present? (Y/N): ");
                String answer = scanner.nextLine().trim();
                boolean present = answer.equalsIgnoreCase("Y");

                Attendance old = find(eventId, student.getStudentId());
                if (old == null) {
                    attendanceList.add(new Attendance(eventId, student.getStudentId(), present));
                } else {
                    old.present = present;
                }
            }
        }

        if (registered == 0) {
            System.out.println("No registered students for this event.");
        } else {
            FileManager.saveAllData();
            System.out.println("Attendance saved for " + registered + " registered student(s).");
        }
    }

    public static Attendance find(int eventId, int studentId) {
        for (Attendance a : attendanceList) {
            if (a.eventId == eventId && a.studentId == studentId) return a;
        }
        return null;
    }

    public static void remove(int studentId, int eventId) {
        attendanceList.removeIf(a ->
            a.studentId == studentId && a.eventId == eventId
        );
    }

    public static int presentCountForEvent(int eventId) {
        int count = 0;
        for (Attendance a : attendanceList) {
            if (a.eventId == eventId && a.present) count++;
        }
        return count;
    }
}
