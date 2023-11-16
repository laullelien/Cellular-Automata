public class ImmNextEvent extends Event {
    Immigration immigration;
    ImmigrationSimulator sim;
    EventManager manager;

    public ImmNextEvent(long date, Immigration i, ImmigrationSimulator sim, EventManager manager) {
        super(date);
        this.immigration = i;
        this.sim = sim;
        this.manager = manager;
    }

    @Override
    public void execute() {
        if (this.getDate() == 0) {
            for (int i = 0; i < immigration.getGridWidth(); i++) {
                for (int j = 0; j < immigration.getGridWidth(); j++) {
                    sim.stateInit(i, j);
                }
            }
            immigration.getCellsToUpdate().clear();

        } else if (this.getDate() > 0) {
            immigration.statesUpdate(immigration.getCellsToUpdate());
            immigration.getCellsToUpdate().clear();

            for (int i = 0; i < immigration.getGridWidth(); i++) {
                for (int j = 0; j < immigration.getGridWidth(); j++) {
                    int currentState = immigration.getStatesGrid()[i][j];

                    if (immigration.isPrepared(i, j, immigration.getGridWidth(), currentState)) {
                        sim.putColorState(immigration.nextState(currentState), i, j);
                        immigration.getCellsToUpdate().add(i);
                        immigration.getCellsToUpdate().add(j);
                    }
                }
            }
        }
        ImmNextEvent newEvent = new ImmNextEvent(this.getDate() + 1,
                this.immigration, this.sim, this.manager);
        this.manager.addEvent(newEvent);
    }
}