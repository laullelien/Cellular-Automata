import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;

public class TestImmigration {
    public static void main(String[] args) {
        Color[] colorStates = {new Color(10, 10, 100),new Color(100, 0,200), new Color(255, 255, 255) };
        new Immigration(550, 50, 3, colorStates);
    }
}
class Immigration extends GridSimulable {
    private int nbStates;
    private Color[] colorStates;
    private final static int NB_STATES_MAX = 100;
    private final int[][] statesGrid;
    LinkedList<Integer> cellsToUpdate = new LinkedList<>();

    public Immigration(int windowSize, int gridWidth, int nbStates, Color[] colorStates) {
        super(windowSize, gridWidth);
        setNbStates(nbStates);
        setColorStates(nbStates, colorStates);
        this.statesGrid = new int[gridWidth][gridWidth];
        restart();
    }

    public int[][] getStatesGrid() {
        return statesGrid;
    }

    public LinkedList<Integer> getCellsToUpdate() {
        return cellsToUpdate;
    }

    public void setNbStates(int nbStates) {
        if (nbStates <= NB_STATES_MAX) {
            this.nbStates = nbStates;
        } else {
            throw new IllegalArgumentException("Le nombre d'états choisi est trop grand !");
        }
    }

    public void setColorStates(int nbStates, Color[] colorStates) {
        if (colorStates.length >= nbStates ) {
            this.colorStates = new Color[nbStates];
            System.arraycopy(colorStates, 0, this.colorStates, 0, nbStates);
        } else {
            throw new IllegalArgumentException("Le nombre de couleurs choisi est insuffisant par rapport au nombre d'états !");
        }

    }

    public void putColorState(int stateIndex, int x, int y) {
        super.colorCell(colorStates[stateIndex], x, y);
    }

    public int nextState(int stateIndex) {
        return (stateIndex + 1) % nbStates;
    }

    /*
    * Check if the adjacent cells of the (x,y) cell in state k can be updated into the next state k+1:
    * if it has 3 or more adjacent cells in the state k+1
    * */
    public int nbAjdPrepared(int x, int y, int gridWidth, int nextState) {
        int countAdjPrep = 0;
        int indLW = x-1;
        int indMW = x+1;
        int indLH = y-1;
        int indMH = y+1;

        if (indLW == -1) {indLW = gridWidth - 1;}
        if (indMW == gridWidth) {indMW = 0;}
        if (indLH == -1) {indLH = gridWidth - 1;}
        if (indMH == gridWidth) {indMH = 0;}
        
        if (statesGrid[indLW][ indLH] == nextState) {countAdjPrep += 1;}
        if (statesGrid[indLW][ y] == nextState) {countAdjPrep += 1;}
        if (statesGrid[indLW][ indMH] == nextState) {countAdjPrep += 1;}
        if (statesGrid[x][ indLH] == nextState) {countAdjPrep += 1;}
        if (statesGrid[x][ indMH] == nextState) {countAdjPrep += 1;}
        if (statesGrid[indMW][ indLH] == nextState) {countAdjPrep += 1;}
        if (statesGrid[indMW][ y] == nextState) {countAdjPrep += 1;}
        if (statesGrid[indMW][ indMH] == nextState) {countAdjPrep += 1;}

        return countAdjPrep;
    }

    public boolean isPrepared(int x, int y, int gridWidth, int stateIndex) {
        return nbAjdPrepared(x,y,gridWidth, nextState(stateIndex)) >= 3;
    }

    /*
    * Update the grid with the change from the last next()
    */
    public void statesUpdate(LinkedList<Integer> cellsToUpdate) {
        for (int i=0; i < cellsToUpdate.size(); i += 2) {
            int x = cellsToUpdate.get(i);
            int y = cellsToUpdate.get(i+1);

            statesGrid[x][y] = nextState(statesGrid[x][y]);
        }
    }

    @Override
    /*
    * Problem: the grid needs to be reset at each iteration
    * Keep the states of all the cells in a Matrix ? costs a lot of space
    */
    public void next() {
        statesUpdate(this.cellsToUpdate);
        this.cellsToUpdate.clear();

        for (int i = 0; i < this.getGridWidth(); i++) {
            for (int j = 0; j < this.getGridWidth(); j++) {
                int currentState = statesGrid[i][j];

                if (isPrepared(i, j, this.getGridWidth(), currentState)) {
                    putColorState(nextState(currentState), i, j);
                    cellsToUpdate.add(i);
                    cellsToUpdate.add(j);
                }
            }
        }
    }

    public void stateInit(int i, int j, int nbStates) {
        Random random = new Random();
        int stateIndex = random.nextInt(nbStates);

        putColorState(stateIndex, i, j);

        statesGrid[i][j] = stateIndex;
    }

    @Override
    public void restart() {
        for (int i=0; i<this.getGridWidth(); i++) {
            for (int j=0; j<this.getGridWidth(); j++) {
                stateInit(i,j,nbStates);
            }
        }
        this.cellsToUpdate.clear();
    }

}
