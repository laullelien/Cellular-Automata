import java.awt.Color;
import java.util.ArrayList;

/**
 * Stocke une matrice représentant la grille d'un jeu de l'immigration à un instant donné
 * ainsi que les coordonnées des cellules à mettre à jour au pas de temps suivant.
 * */
public class Immigration {
    /**
     * Nombre d'états dans le cycle qui caractérise
     * l'instance du jeu de l'immigration en cours.
     * */
    private int nbStates;
    /**
     * Liste des couleurs associées à chaque état.
     * Si le nombre d'états est inférieur au nombre de couleurs,
     * IllegalArgumentException() est lancée.*/
    private Color[] colorStates;
    /**
     * Nombre maximal d'états (pour éviter les problèmes de mémoire). */
    private final static int NB_STATES_MAX = 100;
    /**
     * Taille de la grille (carrée).*/
    private final int gridWidth;
    /**
     * Matrice stockant les états de la grille
     * via les coordonnées des cellules (indices de la matrice).*/
    private final int[][] statesGrid;
    /**
     * ArrayList stockant les coordonnées des cellules à mettre à jour au pas de temps suivant. */
    ArrayList<Integer> cellsToUpdate = new ArrayList<>();
    /**
     * Crée une matrice de taille gridWidth et l'affecte à statesGrid.
     * Affecte une valeur à nbStates et une référence à colorStates.
     * */
    public Immigration(int gridWidth, int nbStates, Color[] colorStates) {
        setNbStates(nbStates);
        setColorStates(nbStates, colorStates);
        this.gridWidth =  gridWidth;
        this.statesGrid = new int[gridWidth][gridWidth];
    }
    public int[][] getStatesGrid() {
        return statesGrid;
    }

    public ArrayList<Integer> getCellsToUpdate() {
        return cellsToUpdate;
    }

    public Color[] getColorStates() {return colorStates; }

    public int getNbStates() {return nbStates; }

    public int getGridWidth() {return gridWidth; }

    public void setNbStates(int nbStates) {
        if (nbStates <= NB_STATES_MAX) {
            this.nbStates = nbStates;
        } else {
            throw new IllegalArgumentException("Le nombre d'états " +
                    "choisi est trop grand !");
        }
    }

    public void setColorStates(int nbStates, Color[] colorStates) {
        if (colorStates.length >= nbStates ) {
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

    /**
     * Commpte le nombre de cellules voisines de la cellule (x,y)
     * qui sont à l'état immédiatement supérieur au sien.
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
    /**
     * Retourne true si 3 ou plus des cellules adjacentes à la cellule (x,y)
     * sont dans l'état immédiatement supérieur au sien.
     * */
    public boolean isPrepared(int x, int y, int gridWidth, int stateIndex) {
        return nbAjdPrepared(x,y,gridWidth, nextState(stateIndex)) >= 3;
    }

    /**
     * Met à jour les cellules de la matrice statesGrid
     * dont les coordonnées sont stockées dans cellsToUpdate.
    */
    public void statesUpdate(ArrayList<Integer> cellsToUpdate) {
        for (int i=0; i < cellsToUpdate.size(); i += 2) {
            int x = cellsToUpdate.get(i);
            int y = cellsToUpdate.get(i+1);

            statesGrid[x][y] = nextState(statesGrid[x][y]);
        }
    }
}
