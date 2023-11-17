/**
 * Represents an event in the Game of Life simulation.
 */
public class GameOfLifeEvent extends Event {
    private GameOfLifeSimulator game;
    private EventManager manager;

    /**
     * Constructor for the GameOfLifeEvent class.
     *
     * @param date    The date of the event.
     * @param game    The GameOfLifeSimulator associated with the event.
     * @param manager The EventManager handling the events.
     */
    public GameOfLifeEvent(long date, GameOfLifeSimulator game, EventManager manager) {
        super(date);
        this.game = game;
        this.manager = manager;
    }

    /**
     * Executes the Game of Life event.
     */
    @Override
    public void execute() {
        // Initial state: Randomly initialize and draw the grid
        if (this.getDate() == 0) {
            game.initialiseGridRandomly();
            game.drawGrid();
        } else if (this.getDate() > 0) {
            // Subsequent states: Update the grid based on Game of Life rules
            int gridWidth = game.getGridWidth();
            for (int i = 0; i < gridWidth; i++) {
                for (int j = 0; j < gridWidth; j++) {
                    int[] neighbors = game.neigbhorCalculation(i, j);
                    // Check rules for a dead cell
                    if (game.getCurrentGrid()[i][j] == 0) {
                        int numberAliveCell = game.countCells(neighbors, 1);
                        if (game.isInTheTable(game.getNumberNeighborsToBorn(), numberAliveCell)) {
                            game.getNextGrid()[i][j] = 1;
                        } else {
                            game.getNextGrid()[i][j] = 0;
                        }
                    }
                    // Check rules for an alive cell
                    if (game.getCurrentGrid()[i][j] == 1) {
                        int numberAliveCell = game.countCells(neighbors, 1);
                        if (game.isInTheTable(game.getNumberNeighborsToLive(), numberAliveCell)) {
                            game.getNextGrid()[i][j] = 1;
                        } else {
                            game.getNextGrid()[i][j] = 0;
                        }
                    }
                }
            }
            // Change the state of the grid and redraw
            game.changeGridState();
            game.drawGrid();
        }

        // Schedule the next event
        GameOfLifeEvent newEvent = new GameOfLifeEvent(this.getDate() + 1,
                this.game, this.manager);
        this.manager.addEvent(newEvent);
    }
}
