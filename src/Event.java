/**
 * Représente des événements exécutables définis par leur date
* */
public abstract class Event {
    final private long DATE;

    public Event(long DATE) {
        this.DATE = DATE;
    }

    public long getDate() {
        return DATE;
    }

    public abstract void execute();
}
