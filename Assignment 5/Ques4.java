import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class EventManager {

    private final String eventName;
    private final LocalDate eventDate;
    private final LocalTime startTime;
    private final Optional<String> venue;

    // 1. Accept event name, date, start time, and optional venue
    public EventManager(String eventName, LocalDate eventDate, LocalTime startTime, String venue) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.venue = Optional.ofNullable(venue);
    }

    public void displayEventDetails() {
        LocalDate today = LocalDate.now();

        // 2. Display basic details
        System.out.println("----- Event Details -----");
        System.out.println("Event Name: " + eventName);
        System.out.println("Event Date: " + eventDate);
        System.out.println("Start Time: " + startTime);

        // 5. Calculate end time (duration = 2 hours)
        LocalTime endTime = startTime.plusHours(2);
        System.out.println("End Time (2 hrs later): " + endTime);

        // 6. Handle optional venue name with fallback
        String venueDisplay = venue.orElse("Venue not specified");
        System.out.println("Venue: " + venueDisplay);

        // 3. Check whether the date is past, today, or future
        // 4. Calculate days remaining
        if (eventDate.isEqual(today)) {
            System.out.println("Timeline Status: The event is TODAY!");
        } else if (eventDate.isAfter(today)) {
            long daysRemaining = ChronoUnit.DAYS.between(today, eventDate);
            System.out.println("Timeline Status: Future event (" + daysRemaining + " day(s) remaining)");
        } else {
            long daysAgo = ChronoUnit.DAYS.between(eventDate, today);
            System.out.println("Timeline Status: Past event (held " + daysAgo + " day(s) ago)");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();

        // Future event with venue
        EventManager futureEvent = new EventManager(
                "Tech Symposium",
                today.plusDays(10),
                LocalTime.of(10, 0),
                "Auditorium 1"
        );
        futureEvent.displayEventDetails();

        // Today's event without venue
        EventManager todayEvent = new EventManager(
                "Coding Sprint",
                today,
                LocalTime.of(14, 30),
                null
        );
        todayEvent.displayEventDetails();
    }
}
