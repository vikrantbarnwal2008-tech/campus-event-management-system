import java.util.ArrayList;

public class Registration {
    public static final ArrayList<Registration> registrations = new ArrayList<>();

    private final int registrationId;
    private final int studentId;
    private final int eventId;

    public Registration(int registrationId, int studentId, int eventId) {
        this.registrationId = registrationId;
        this.studentId = studentId;
        this.eventId = eventId;
    }

    public int getRegistrationId() { return registrationId; }
    public int getStudentId() { return studentId; }
    public int getEventId() { return eventId; }

    public static boolean isRegistered(int studentId, int eventId) {
        return findByStudentAndEvent(studentId, eventId) != null;
    }

    public static Registration findByStudentAndEvent(int studentId, int eventId) {
        for (Registration r : registrations) {
            if (r.studentId == studentId && r.eventId == eventId) return r;
        }
        return null;
    }

    public static int countForEvent(int eventId) {
        int count = 0;
        for (Registration r : registrations) {
            if (r.eventId == eventId) count++;
        }
        return count;
    }

    public static int nextId() {
        int max = 1000;
        for (Registration r : registrations) max = Math.max(max, r.registrationId);
        return max + 1;
    }
}
