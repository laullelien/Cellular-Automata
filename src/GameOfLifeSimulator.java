import java.util.Random;
import java.awt.Color;

public class GameOfLifeSimulator extends GridSimulable {
    private final int[] numberNeighborsToLive;
    private final int[] numberNeighborsToBorn;
    private final Color[] colors;
    private int[][] currentGrid;
    private int[][] nextGrid;
    
    public GameOfLifeSimulator(int windowSize, int gridWidth, int[] numberNeighborsToLive, int[] numberNeighborsToBorn, Color deadCellColor, Color aliveCellColor){
        super(windowSize, gridWidth);
        this.numberNeighborsToLive = numberNeighborsToLive;
        this.numberNeighborsToBorn = numberNeighborsToBorn;

        colors = new Color[] {deadCellColor, aliveCellColor};
        currentGrid = new int[gridWidth][gridWidth];
        nextGrid = new int[gridWidth][gridWidth];
        initialiseGridRandomly();
        drawGrid();
    }

    private void initialiseGridRandomly(){
        int gridWidth = super.getGridWidth();
        Random generator = new Random();
        for (int i = 0; i < gridWidth; i++){
            for (int j = 0; j < gridWidth; j++){
                currentGrid[i][j] = generator.nextInt(2);
            }
        }
    }

    private void drawGrid() {
        int gridWidth = super.getGridWidth();
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridWidth; j++) {
                super.colorCell(colors[currentGrid[i][j]], i, j);
            }
        }
    }

    private int[] neigbhorCalculation(int i, int j){
        int gridWidth = super.getGridWidth();
        int[] neighbors = new int[8];
        
        // neighbors indices
        int i_top = (i - 1 + gridWidth) % gridWidth;
        int i_bellow = (i + 1) % gridWidth;
        int j_left = (j - 1 + gridWidth) % gridWidth;
        int j_right = (j + 1) % gridWidth;
    
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

    public int countCells(int[] neighbors, int cellState) {
        int countCell = 0;
    
        for (int i = 0; i < neighbors.length; i++) {
            if (neighbors[i] == cellState) {
                countCell ++;
            } 
        }
        
        return countCell;
    }

    private boolean isInTheTable(int[] array, int val){
        for (int element : array) {
            if (element == val){
                return true;
            }
        }
        return false;
    }
    

    private void changeGridState(){
        int[][] temp = currentGrid;
        currentGrid = nextGrid;
        nextGrid = temp;
    }
    
    @Override
    public void next() {
        int gridWidth = super.getGridWidth();
        for (int i = 0; i < gridWidth; i ++){
            for (int j = 0; j < gridWidth; j ++){
                int[] neighbors = neigbhorCalculation(i, j);
                if (currentGrid[i][j] == 0){    // Dead cell
                    int numberAliveCell = countCells(neighbors, 1);
                    if (isInTheTable(this.numberNeighborsToBorn, numberAliveCell)){
                        nextGrid[i][j] = 1;
                    }
                    else{
                        nextGrid[i][j] = 0;
                    }
                }
                if (currentGrid[i][j] == 1){    // Alive cell
                    int numberAliveCell = countCells(neighbors, 1);
                    if (isInTheTable(this.numberNeighborsToLive, numberAliveCell)){
                        nextGrid[i][j] = 1;
                    }
                    else{
                        nextGrid[i][j] = 0;
                    }
                }
            }
        }
        changeGridState();
        drawGrid();
    }

    @Override
    public void restart() {
        initialiseGridRandomly();
        drawGrid();
    }
}


