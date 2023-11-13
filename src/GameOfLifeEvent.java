public class GameOfLifeEvent extends Event {
    private GameOfLifeSimulator game;
    private EventManager manager;

    public GameOfLifeEvent(long date, GameOfLifeSimulator game, EventManager manager) {
        super(date);
        this.game = game;
        this.manager = manager;
    }

    @Override
    public void execute() {
        if (this.getDate() == 0) {
            game.initialiseGridRandomly();
            game.drawGrid();
        } else if (this.getDate() > 0) {
            int gridWidth = game.getGridWidth();
            for (int i = 0; i < gridWidth; i++) {
                for (int j = 0; j < gridWidth; j++) {
                    int[] neighbors = game.neigbhorCalculation(i, j);
                    if (game.getCurrentGrid()[i][j] == 0) {    // Dead cell
                        int numberAliveCell = game.countCells(neighbors, 1);
                        if (game.isInTheTable(game.getNumberNeighborsToBorn(), numberAliveCell)) {
                            game.getNextGrid()[i][j] = 1;
                        } else {
                            game.getNextGrid()[i][j] = 0;
                        }
                    }
                    if (game.getCurrentGrid()[i][j] == 1) {    // Alive cell
                        int numberAliveCell = game.countCells(neighbors, 1);
                        if (game.isInTheTable(game.getNumberNeighborsToLive(), numberAliveCell)) {
                            game.getNextGrid()[i][j] = 1;
                        } else {
                            game.getNextGrid()[i][j] = 0;
                        }
                    }
                }
            }
            game.changeGridState();
            game.drawGrid();
        }
        GameOfLifeEvent newEvent = new GameOfLifeEvent(this.getDate() + 1,
                this.game, this.manager);
        this.manager.addEvent(newEvent);

    }
}
