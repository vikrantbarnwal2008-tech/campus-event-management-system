import java.util.Scanner;

public class Report {
    public static void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n================ REPORTS ================");
            System.out.println("1. Dashboard Summary");
            System.out.println("2. Event Registration Report");
            System.out.println("3. Student Participation Report");
            System.out.println("4. Attendance Summary");
            System.out.println("5. Back");

            int choice = Validator.readInt(scanner, "Enter choice: ");

            switch (choice) {
                case 1 -> dashboard();
                case 2 -> eventRegistrationReport();
                case 3 -> studentParticipationReport();
                case 4 -> attendanceSummary();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void dashboard() {
        System.out.println("\n--------------- DASHBOARD ---------------");
        System.out.println("Students       : " + Student.students.size());
        System.out.println("Organizers     : " + Organizer.organizers.size());
        System.out.println("Events         : " + Event.events.size());
        System.out.println("Registrations  : " + Registration.registrations.size());
        System.out.println("Attendance Rows: " + Attendance.attendanceList.size());
        System.out.println("-----------------------------------------");
    }

    private static void eventRegistrationReport() {
        if (Event.events.isEmpty()) {
            System.out.println("No events available.");
            return;
        }

        System.out.println("\n--------- EVENT REGISTRATION REPORT ---------");
        for (Event e : Event.events) {
            int count = Registration.countForEvent(e.getEventId());
            System.out.println(e.getEventName() + " | " + count
                    + "/" + e.getCapacity() + " registered");
        }
    }

    private static void studentParticipationReport() {
        if (Student.students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\n------- STUDENT PARTICIPATION REPORT -------");
        for (Student s : Student.students) {
            int count = 0;
            for (Registration r : Registration.registrations) {
                if (r.getStudentId() == s.getStudentId()) count++;
            }
            System.out.println(s.getName() + " (" + s.getStudentId()
                    + ") -> " + count + " event(s)");
        }
    }

    private static void attendanceSummary() {
        int total = Attendance.attendanceList.size();
        int present = 0;

        for (Attendance a : Attendance.attendanceList) {
            if (a.isPresent()) present++;
        }

        System.out.println("\n------------- ATTENDANCE SUMMARY -------------");
        System.out.println("Total attendance records: " + total);
        System.out.println("Present: " + present);
        System.out.println("Absent : " + (total - present));

        if (total > 0) {
            double percentage = present * 100.0 / total;
            System.out.printf("Overall attendance: %.2f%%%n", percentage);
        } else {
            System.out.println("Overall attendance: 0.00%");
        }
    }
}
