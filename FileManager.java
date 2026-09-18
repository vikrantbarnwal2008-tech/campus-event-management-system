import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private static final String DATA_DIR = "data";
    private static final String STUDENTS = DATA_DIR + "/students.txt";
    private static final String ORGANIZERS = DATA_DIR + "/organizers.txt";
    private static final String EVENTS = DATA_DIR + "/events.txt";
    private static final String REGISTRATIONS = DATA_DIR + "/registrations.txt";
    private static final String ATTENDANCE = DATA_DIR + "/attendance.txt";

    public static void saveAllData() {
        createDataDirectory();
        saveStudents();
        saveOrganizers();
        saveEvents();
        saveRegistrations();
        saveAttendance();
    }

    public static void loadAllData() {
        createDataDirectory();
        loadStudents();
        loadOrganizers();
        loadEvents();
        loadRegistrations();
        loadAttendance();
    }

    private static void createDataDirectory() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) dir.mkdirs();
    }

    private static void write(String fileName, ArrayList<String> lines) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (String line : lines) out.println(line);
        }
    }

    private static void saveStudents() {
        try {
            ArrayList<String> lines = new ArrayList<>();
            for (Student s : Student.students) {
                lines.add(s.getStudentId() + "|" + clean(s.getName()) + "|"
                        + clean(s.getEmail()) + "|" + clean(s.getCourse()));
            }
            write(STUDENTS, lines);
        } catch (IOException e) {
            System.out.println("Could not save students: " + e.getMessage());
        }
    }

    private static void saveOrganizers() {
        try {
            ArrayList<String> lines = new ArrayList<>();
            for (Organizer o : Organizer.organizers) {
                lines.add(o.getOrganizerId() + "|" + clean(o.getName()) + "|"
                        + clean(o.getEmail()) + "|" + clean(o.getDepartment()));
            }
            write(ORGANIZERS, lines);
        } catch (IOException e) {
            System.out.println("Could not save organizers: " + e.getMessage());
        }
    }

    private static void saveEvents() {
        try {
            ArrayList<String> lines = new ArrayList<>();
            for (Event e : Event.events) {
                lines.add(e.getEventId() + "|" + clean(e.getEventName()) + "|"
                        + clean(e.getDate()) + "|" + clean(e.getTime()) + "|"
                        + clean(e.getVenue()) + "|" + e.getCapacity() + "|"
                        + e.getOrganizerId());
            }
            write(EVENTS, lines);
        } catch (IOException e) {
            System.out.println("Could not save events: " + e.getMessage());
        }
    }

    private static void saveRegistrations() {
        try {
            ArrayList<String> lines = new ArrayList<>();
            for (Registration r : Registration.registrations) {
                lines.add(r.getRegistrationId() + "|" + r.getStudentId()
                        + "|" + r.getEventId());
            }
            write(REGISTRATIONS, lines);
        } catch (IOException e) {
            System.out.println("Could not save registrations: " + e.getMessage());
        }
    }

    private static void saveAttendance() {
        try {
            ArrayList<String> lines = new ArrayList<>();
            for (Attendance a : Attendance.attendanceList) {
                lines.add(a.getEventId() + "|" + a.getStudentId()
                        + "|" + a.isPresent());
            }
            write(ATTENDANCE, lines);
        } catch (IOException e) {
            System.out.println("Could not save attendance: " + e.getMessage());
        }
    }

    private static void loadStudents() {
        File file = new File(STUDENTS);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|", -1);
                if (p.length == 4) {
                    try {
                        Student.students.add(new Student(Integer.parseInt(p[0]), p[1], p[2], p[3]));
                    } catch (NumberFormatException ignored) { }
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load students.");
        }
    }

    private static void loadOrganizers() {
        File file = new File(ORGANIZERS);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|", -1);
                if (p.length == 4) {
                    try {
                        Organizer.organizers.add(new Organizer(Integer.parseInt(p[0]), p[1], p[2], p[3]));
                    } catch (NumberFormatException ignored) { }
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load organizers.");
        }
    }

    private static void loadEvents() {
        File file = new File(EVENTS);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|", -1);
                if (p.length == 7) {
                    try {
                        Event.events.add(new Event(
                            Integer.parseInt(p[0]), p[1], p[2], p[3], p[4],
                            Integer.parseInt(p[5]), Integer.parseInt(p[6])
                        ));
                    } catch (NumberFormatException ignored) { }
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load events.");
        }
    }

    private static void loadRegistrations() {
        File file = new File(REGISTRATIONS);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|", -1);
                if (p.length == 3) {
                    try {
                        Registration.registrations.add(new Registration(
                            Integer.parseInt(p[0]), Integer.parseInt(p[1]), Integer.parseInt(p[2])
                        ));
                    } catch (NumberFormatException ignored) { }
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load registrations.");
        }
    }

    private static void loadAttendance() {
        File file = new File(ATTENDANCE);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|", -1);
                if (p.length == 3) {
                    try {
                        Attendance.attendanceList.add(new Attendance(
                            Integer.parseInt(p[0]), Integer.parseInt(p[1]),
                            Boolean.parseBoolean(p[2])
                        ));
                    } catch (NumberFormatException ignored) { }
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load attendance.");
        }
    }

    private static String clean(String value) {
        return value.replace("|", "/").replace("\n", " ").replace("\r", " ");
    }
}
