import java.util.ArrayList;
import java.util.Scanner;

public class Event {
    public static final ArrayList<Event> events = new ArrayList<>();

    private final int eventId;
    private String eventName;
    private String date;
    private String time;
    private String venue;
    private int capacity;
    private int organizerId;

    public Event(int eventId, String eventName, String date, String time,
                 String venue, int capacity, int organizerId) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.capacity = capacity;
        this.organizerId = organizerId;
    }

    public int getEventId() { return eventId; }
    public String getEventName() { return eventName; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getVenue() { return venue; }
    public int getCapacity() { return capacity; }
    public int getOrganizerId() { return organizerId; }

    public static void displayEvents() {
        System.out.println("\n================ AVAILABLE EVENTS ================");
        if (events.isEmpty()) {
            System.out.println("No events are available.");
        } else {
            for (Event e : events) {
                int registered = Registration.countForEvent(e.eventId);
                System.out.println(e.summary());
                System.out.println("Capacity: " + registered + "/" + e.capacity);
                System.out.println("Organizer ID: " + e.organizerId);
                System.out.println("---------------------------------------------------");
            }
        }
    }

    public String summary() {
        return "ID: " + eventId + " | " + eventName
                + " | " + date + " | " + time + " | " + venue;
    }

    public static Event findEvent(int id) {
        for (Event e : events) {
            if (e.eventId == id) return e;
        }
        return null;
    }

    public void update(String name, String date, String time,
                       String venue, int capacity) {
        this.eventName = name;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.capacity = capacity;
    }

    public static int nextId() {
        int max = 100;
        for (Event e : events) max = Math.max(max, e.eventId);
        return max + 1;
    }
}
