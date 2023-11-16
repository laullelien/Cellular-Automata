import java.util.Comparator;
import java.util.PriorityQueue;
/**
* Executes (or not) subclass(es) of Event based on currentDate in chronological order
* */
public class EventManager {
    private long currentDate;
    final private PriorityQueue<Event> events;
    /**
    * Crée un EventManager avec une file d'évènements vide (basée sur un comparateur de dates)
    * et une date initialisée à 0
    * */
    public EventManager(){
        this.currentDate = 0;
        Comparator<Event> dateComparator = Comparator.comparing(Event::getDate);
        this.events = new PriorityQueue<>(dateComparator);
    }
    /**
    * Crée un EventManager avec une file d'évènements vide (basée sur un comparateur de dates)
    * @param currentDate date courante
    * */
    public EventManager(long currentDate){
        this.currentDate = currentDate;
        Comparator<Event> dateComparator = Comparator.comparing(Event::getDate);
        this.events = new PriorityQueue<>(dateComparator);
    }

    /**
    * Crée un EventManager comprenant une file d'évènements à gérer.
    * @param currentDate date courante
    * @param events file d'évènements, doit être créée à partir d'un comparateur de dates
    * */
    public EventManager(long currentDate, PriorityQueue<Event> events) {
        this.currentDate = currentDate;
        this.events = events;
    }

    public long getCurrentDate() { return currentDate; }

    public void addEvent(Event e) {
        this.events.add(e);
    }

    public void next() {
        Event e = events.poll();
        while (e != null && e.getDate() <= currentDate) {
            e.execute();
            e = events.poll();
        }
        if (e != null){
            events.add(e);
        }
        this.currentDate += 1;
    }
    /**
    * Retourne true si plus aucun évènement n'est en attente d'exécution
    * */
    public boolean isFinished() {
        return (events.isEmpty());
    }

    public void restart(){
        events.clear();
        this.currentDate = 0;
    }

}
