public class SchellingEvent extends Event{
    private final EventManager manager;
    final boolean starting;
    SchellingSimulator game;
    public SchellingEvent(EventManager manager, boolean starting) {
        super(manager.getCurrentDate());
        this.manager = manager;
        this.starting = starting;
    }
    @Override
    public void execute() {
        if(starting) {
            game.clearAvailableHomes();
            game.clearHomelessFamilies();
            game.initialiseGrid();
            game.drawGrid();
            game.addAvailableHomes();
        }
        else {
            game.moveHomelessFamiliesToAvailableHomes();
            game.removeUnhappyFamilies();
        }
        manager.addEvent(new SchellingEvent(manager, false));
    }
}
