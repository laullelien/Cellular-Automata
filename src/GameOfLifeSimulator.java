import java.util.Random;
import java.awt.Color;

/**
 * Implementation of Conway's Game of Life simulator.
 */
public class GameOfLifeSimulator extends GridSimulable {
    // Arrays to store the rules for survival and birth
    private final int[] numberNeighborsToLive;
    private final int[] numberNeighborsToBorn;
    // Colors for dead and alive cells
    private final Color[] colors;
    // Current and next grids
    private int[][] currentGrid;
    private int[][] nextGrid;

    /**
     * Constructor for the GameOfLifeSimulator class.
     *
     * @param windowSize            The size of the window.
     * @param gridWidth             The width of the grid.
     * @param numberNeighborsToLive An array specifying the number of neighbors required for a cell to survive.
     * @param numberNeighborsToBorn An array specifying the number of neighbors required for a cell to be born.
     * @param deadCellColor         The color representing dead cells.
     * @param aliveCellColor        The color representing alive cells.
     */
    public GameOfLifeSimulator(int windowSize, int gridWidth, int[] numberNeighborsToLive, int[] numberNeighborsToBorn, Color deadCellColor, Color aliveCellColor) {
        super(windowSize, gridWidth);
        this.numberNeighborsToLive = numberNeighborsToLive;
        this.numberNeighborsToBorn = numberNeighborsToBorn;

        // Assign colors
        colors = new Color[]{deadCellColor, aliveCellColor};
        // Initialize grids
        currentGrid = new int[gridWidth][gridWidth];
        nextGrid = new int[gridWidth][gridWidth];
        // Initialize the superclass and restart the simulation
        super.restart();
    }

    /**
     * Gets the array specifying the number of neighbors required for a cell to survive.
     *
     * @return The array specifying the number of neighbors to live.
     */
    public int[] getNumberNeighborsToLive() {
        return numberNeighborsToLive;
    }

    /**
     * Gets the array specifying the number of neighbors required for a cell to be born.
     *
     * @return The array specifying the number of neighbors to be born.
     */
    public int[] getNumberNeighborsToBorn() {
        return numberNeighborsToBorn;
    }

    /**
     * Gets the current state of the grid.
     *
     * @return The current state of the grid.
     */
    public int[][] getCurrentGrid() {
        return currentGrid;
    }

    /**
     * Gets the next state of the grid.
     *
     * @return The next state of the grid.
     */
    public int[][] getNextGrid() {
        return nextGrid;
    }

    /**
     * Initializes the grid randomly with dead or alive cells.
     */
    public void initialiseGridRandomly() {
        int gridWidth = super.getGridWidth();
        Random generator = new Random();
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridWidth; j++) {
                currentGrid[i][j] = generator.nextInt(2);
            }
        }
    }

    /**
     * Draws the current state of the grid using specified colors.
     */
    public void drawGrid() {
        int gridWidth = super.getGridWidth();
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridWidth; j++) {
                super.colorCell(colors[currentGrid[i][j]], i, j);
            }
        }
    }

    /**
     * Calculates the neighbors of a cell in the grid.
     *
     * @param i The row index of the cell.
     * @param j The column index of the cell.
     * @return An array representing the neighbors of the cell.
     */
    public int[] neigbhorCalculation(int i, int j) {
        int gridWidth = super.getGridWidth();
        int[] neighbors = new int[8];

        // Calculate indices of neighbors
        int i_top = (i - 1 + gridWidth) % gridWidth;
        int i_bellow = (i + 1) % gridWidth;
        int j_left = (j - 1 + gridWidth) % gridWidth;
        int j_right = (j + 1) % gridWidth;

        // Assign neighbor values
        neighbors[0] = currentGrid[i_top][j_left];
        neighbors[1] = currentGrid[i_top][j];
        neighbors[2] = currentGrid[i_top][j_right];
        neighbors[3] = currentGrid[i][j_left];
        neighbors[4] = currentGrid[i][j_right];
        neighbors[5] = currentGrid[i_bellow][j_left];
        neighbors[6] = currentGrid[i_bellow][j];
        neighbors[7] = currentGrid[i_bellow][j_right];

        return neighbors;
    }

    /**
     * Counts the number of cells in a given state among neighbors.
     *
     * @param neighbors  An array representing the neighbors of a cell.
     * @param cellState  The state of the cell to count.
     * @return The count of cells in the specified state.
     */
    public int countCells(int[] neighbors, int cellState) {
        int countCell = 0;
        for (int i = 0; i < neighbors.length; i++) {
            if (neighbors[i] == cellState) {
                countCell++;
            }
        }
        return countCell;
    }

    /**
     * Checks if a value is present in an array.
     *
     * @param array The array to check.
     * @param val   The value to check for.
     * @return True if the value is present, false otherwise.
     */
    public boolean isInTheTable(int[] array, int val) {
        for (int element : array) {
            if (element == val) {
                return true;
            }
        }
        return false;
    }

    /**
     * Swaps the current and next states of the grid.
     */
    public void changeGridState() {
        int[][] temp = currentGrid;
        currentGrid = nextGrid;
        nextGrid = temp;
    }

    /**
     * Overrides the superclass method to get the starting event for the simulation.
     *
     * @return The starting event for the Game of Life simulation.
     */
    @Override
    public Event getStartingEvent() {
        return new GameOfLifeEvent(0, this, super.getManager());
    }
}
