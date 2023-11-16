import java.awt.Color;
import java.util.LinkedList;

/*
* Stores a grid of the immigration game at a given time
* As well as the coordinates of the cells to be updated in the next frame
* */
public class Immigration {
    private int nbStates;
    private Color[] colorStates;
    private final static int NB_STATES_MAX = 100;
    private final int gridWidth;
    private final int[][] statesGrid;
    LinkedList<Integer> cellsToUpdate = new LinkedList<>();

    public Immigration(int gridWidth, int nbStates, Color[] colorStates) {
        setNbStates(nbStates);
        setColorStates(nbStates, colorStates);
        this.gridWidth = gridWidth;
        this.statesGrid = new int[gridWidth][gridWidth];
    }

    public int[][] getStatesGrid() {
        return statesGrid;
    }

    public LinkedList<Integer> getCellsToUpdate() {
        return cellsToUpdate;
    }

    public Color[] getColorStates() {
        return colorStates;
    }

    public int getNbStates() {
        return nbStates;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public void setNbStates(int nbStates) {
        if (nbStates <= NB_STATES_MAX) {
            this.nbStates = nbStates;
        } else {
            throw new IllegalArgumentException("Le nombre d'états " +
                    "choisi est trop grand !");
        }
    }

    public void setColorStates(int nbStates, Color[] colorStates) {
        if (colorStates.length >= nbStates) {
            this.colorStates = new Color[nbStates];
            System.arraycopy(colorStates, 0, this.colorStates, 0, nbStates);
        } else {
            throw new IllegalArgumentException("Le nombre de couleurs choisi est " +
                    "insuffisant par rapport au nombre d'états !");
        }

    }

    public int nextState(int stateIndex) {
        return (stateIndex + 1) % nbStates;
    }

    /*
     * Check if the adjacent cells of the (x,y) cell in state k can be
     * updated into the next state k+1:
     * if it has 3 or more adjacent cells in the state k+1
     * */
    public int nbAjdPrepared(int x, int y, int gridWidth, int nextState) {
        int countAdjPrep = 0;
        int indLW = x - 1;
        int indMW = x + 1;
        int indLH = y - 1;
        int indMH = y + 1;

        if (indLW == -1) {
            indLW = gridWidth - 1;
        }
        if (indMW == gridWidth) {
            indMW = 0;
        }
        if (indLH == -1) {
            indLH = gridWidth - 1;
        }
        if (indMH == gridWidth) {
            indMH = 0;
        }

        if (statesGrid[indLW][indLH] == nextState) {
            countAdjPrep += 1;
        }
        if (statesGrid[indLW][y] == nextState) {
            countAdjPrep += 1;
        }
        if (statesGrid[indLW][indMH] == nextState) {
            countAdjPrep += 1;
        }
        if (statesGrid[x][indLH] == nextState) {
            countAdjPrep += 1;
        }
        if (statesGrid[x][indMH] == nextState) {
            countAdjPrep += 1;
        }
        if (statesGrid[indMW][indLH] == nextState) {
            countAdjPrep += 1;
        }
        if (statesGrid[indMW][y] == nextState) {
            countAdjPrep += 1;
        }
        if (statesGrid[indMW][indMH] == nextState) {
            countAdjPrep += 1;
        }

        return countAdjPrep;
    }

    public boolean isPrepared(int x, int y, int gridWidth, int stateIndex) {
        return nbAjdPrepared(x, y, gridWidth, nextState(stateIndex)) >= 3;
    }

    /*
     * Update the grid with the change from the last next()
     */
    public void statesUpdate(LinkedList<Integer> cellsToUpdate) {
        for (int i = 0; i < cellsToUpdate.size(); i += 2) {
            int x = cellsToUpdate.get(i);
            int y = cellsToUpdate.get(i + 1);

            statesGrid[x][y] = nextState(statesGrid[x][y]);
        }
    }
}
