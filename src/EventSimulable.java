import gui.Simulable;

public abstract class EventSimulable implements Simulable {

    private final EventManager manager;

    public EventSimulable() {
        manager = new EventManager();
    }

    @Override
    public final void next() {
        manager.next();
    }

    @Override
    public final void restart() {
        manager.restart();
        manager.addEvent(getStartingEvent());
        next();
    }

    public abstract Event getStartingEvent();


    public EventManager getManager() {
        return manager;
    }

}
