public class ValidationTest {
    public static void main(String[] args) {
        System.out.println("========== CAMPUSCONNECT VALIDATION TEST ==========");

        check("Valid email", Validator.isValidEmail("student@example.com"));
        check("Invalid email rejected", !Validator.isValidEmail("student@"));
        check("Valid name", Validator.isValidName("Aman Kumar"));
        check("Invalid name rejected", !Validator.isValidName("Aman123"));

        Student.students.clear();
        Student.students.add(new Student(101, "Test Student",
                "test@example.com", "B.Tech"));
        check("Student search", Student.findStudent(101) != null);

        Event.events.clear();
        Event.events.add(new Event(201, "Java Workshop", "18-09-2026",
                "10:00 AM", "Lab 1", 50, 1));
        check("Event search", Event.findEvent(201) != null);

        Registration.registrations.clear();
        Registration.registrations.add(new Registration(1001, 101, 201));
        check("Registration lookup", Registration.isRegistered(101, 201));
        check("Capacity counting", Registration.countForEvent(201) == 1);

        Attendance.attendanceList.clear();
        Attendance.attendanceList.add(new Attendance(201, 101, true));
        check("Attendance lookup", Attendance.find(201, 101) != null);
        check("Present status", Attendance.find(201, 101).isPresent());

        System.out.println("====================================================");
        System.out.println("Validation tests completed.");
    }

    private static void check(String testName, boolean passed) {
        System.out.println((passed ? "PASS" : "FAIL") + " : " + testName);
    }
}
