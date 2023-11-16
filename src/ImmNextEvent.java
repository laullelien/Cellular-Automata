/**
 * Event de pas de temps suivant pour le jeu de l'immigration.
 * Est exécuté lorsque les boutons 'Suivant' ou 'Début' de l'interface graphiques sont enfoncés.
 * */
public class ImmNextEvent extends Event {
    /**
     * Instance d'Immigration en cours de simulation*/
    Immigration immigration;
    /**
     * Simulation d'immigration.*/
    ImmigrationSimulator sim;
    /**
     * Gestionnaire d'événements associé à cette simulation*/
    EventManager manager;
    /**
     * Crée une instance de ImmNextEvent
     * @param date date de l'événement créé
     * @param i instance du jeu de l'immigration en cours
     * @param sim simulation de i
     * @param manager gestionnaire d'événements de sim
     * */
    public ImmNextEvent(long date, Immigration i, ImmigrationSimulator sim, EventManager manager) {
        super(date);
        this.immigration = i;
        this.sim = sim;
        this.manager = manager;
    }
    /**
     * Si la date courante de manager > zéro : Met à jour toutes les cellules qui doivent l'être.
     * Sinon : initialise les cellules à des états aléatoires.
     *
     * Dans tous les cas : crée le prochain évènement à exécuter, et l'ajoute dans manager.
     * */
    @Override
    public void execute() {
        if (this.getDate() == 0) {
            for (int i = 0; i < immigration.getGridWidth(); i++) {
                for (int j = 0; j < immigration.getGridWidth(); j++) {
                    sim.stateInit(i, j);
                }
            }
            immigration.getCellsToUpdate().clear();

        } else if (this.getDate() > 0) {
            immigration.statesUpdate(immigration.getCellsToUpdate());
            immigration.getCellsToUpdate().clear();

            for (int i = 0; i < immigration.getGridWidth(); i++) {
                for (int j = 0; j < immigration.getGridWidth(); j++) {
                    int currentState = immigration.getStatesGrid()[i][j];

                    if (immigration.isPrepared(i, j, immigration.getGridWidth(), currentState)) {
                        sim.putColorState(immigration.nextState(currentState), i, j);
                        immigration.getCellsToUpdate().add(i);
                        immigration.getCellsToUpdate().add(j);
                    }
                }
            }
        }
        ImmNextEvent newEvent = new ImmNextEvent(this.getDate() + 1,
                this.immigration, this.sim, this.manager);
        this.manager.addEvent(newEvent);
    }
}