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

}