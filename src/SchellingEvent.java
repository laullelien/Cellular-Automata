public class SchellingEvent extends Event {
    private final EventManager manager;
    final boolean starting;
    SchellingSimulator game;

    public SchellingEvent(EventManager manager, boolean starting, SchellingSimulator game, long date) {
        super(date);
        this.manager = manager;
        this.starting = starting;
        this.game = game;
    }

    @Override
    public void execute() {
        if (starting) {
            game.clearAvailableHomes();
            game.clearHomelessFamilies();
            game.initialiseGrid();
            game.addAvailableHomes();
            game.drawGrid();
        } else {
            game.moveHomelessFamiliesToAvailableHomes();
            game.removeUnhappyFamilies();
        }
        manager.addEvent(new SchellingEvent(manager, false, game, super.getDate() + 1));
    }
}
