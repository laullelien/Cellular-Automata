import java.util.Random;

public class ImmigrationSimulator extends GridSimulable {
    final private Immigration immigration;
    
    public ImmigrationSimulator(int windowSize, Immigration immigration) {
        super(windowSize, immigration.getGridWidth());
        this.immigration = immigration;
        super.restart();
    }

    public void putColorState(int stateIndex, int x, int y) {
        super.colorCell(immigration.getColorStates()[stateIndex], x, y);
    }

    public void stateInit(int i, int j) {
        Random random = new Random();
        int stateIndex = random.nextInt(immigration.getNbStates());

        putColorState(stateIndex, i, j);

        immigration.getStatesGrid()[i][j] = stateIndex;
    }

    @Override
    public Event getStartingEvent() {
        return new ImmNextEvent(0,
                this.immigration, this, super.getManager());
    }

    /* @Override
    *//*
     *//*
    public void next() {
        immigration.statesUpdate(immigration.getCellsToUpdate());
        immigration.getCellsToUpdate().clear();

        for (int i = 0; i < immigration.getGridWidth(); i++) {
            for (int j = 0; j < immigration.getGridWidth(); j++) {
                int currentState = immigration.getStatesGrid()[i][j];

                if (immigration.isPrepared(i, j, immigration.getGridWidth(), currentState)) {
                    putColorState(immigration.nextState(currentState), i, j);
                    immigration.getCellsToUpdate().add(i);
                    immigration.getCellsToUpdate().add(j);
                }
            }
        }
    }*/

/*    @Override
    public void restart() {
        for (int i=0; i<immigration.getGridWidth(); i++) {
            for (int j=0; j<immigration.getGridWidth(); j++) {
                stateInit(i,j);
            }
        }
        immigration.getCellsToUpdate().clear();
    }*/

}
