import java.util.Random;
/**
* Simulates an instance of the Immigration class via a graphical interface
* */
public class ImmigrationSimulator extends GridSimulable {
    /**
     * Instance d'Immigration à simuler.*/
    final private Immigration immigration;
    /**
     * Crée une fenêtre graphique de taille windowSize
     * pour commencer la simulation de immigration*/
    public ImmigrationSimulator(int windowSize, Immigration immigration) {
        super(windowSize, immigration.getGridWidth());
        this.immigration = immigration;
        super.restart();
    }
    /**
     * Colorie la cellule (x,y) selon l'indice de son état (dans la liste des couleurs).*/
    public void putColorState(int stateIndex, int x, int y) {
        super.colorCell(immigration.getColorStates()[stateIndex], x, y);
    }
    /**
     * Initialise l'état de la cellule statesGrid[i,j]
     * à un numéro aléatoirement choisi parmi le nombre d'états.*/
    public void stateInit(int i, int j) {
        Random random = new Random();
        int stateIndex = random.nextInt(immigration.getNbStates());

        putColorState(stateIndex, i, j);

        immigration.getStatesGrid()[i][j] = stateIndex;
    }
    /**
     * Retourne un Event dont l'exécution correspond à restart()*/
    @Override
    public Event getStartingEvent() {
        return new ImmNextEvent(0,
                this.immigration, this, super.getManager());
    }

}