import java.util.Comparator;
import java.util.PriorityQueue;

public class EventManager {
    private long currentDate;
    private PriorityQueue<Event> events;

    public EventManager() {
        this.currentDate = 0;
        Comparator<Event> dateComparator = Comparator.comparing(Event::getDate);
        this.events = new PriorityQueue<>(dateComparator);
    }

    public EventManager(long currentDate) {
        this.currentDate = currentDate;
        Comparator<Event> dateComparator = Comparator.comparing(Event::getDate);
        this.events = new PriorityQueue<>(dateComparator);
    }

    public EventManager(long currentDate, PriorityQueue<Event> events) {
        this.currentDate = currentDate;
        this.events = events;
    }

    public long getCurrentDate() {
        return currentDate;
    }

    public void addEvent(Event e) {
        this.events.add(e);
    }

    public void next() {
        Event e = events.poll();
        while (e != null && e.getDate() <= currentDate) {
            e.execute();
            e = events.poll();
        }
        if (e != null) {
            events.add(e);
        }
        this.currentDate += 1;
    }

    public boolean isFinished() {
        return (events.isEmpty());
    }

    public void restart() {
        events.clear();
        this.currentDate = 0;
    }

}
