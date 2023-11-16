public class BoidsEvent extends Event {
    private final EventManager manager;
    final boolean starting;
    BoidsSimulator game;
    Boids boids;

    public BoidsEvent(EventManager manager, boolean starting, BoidsSimulator game, Boids boids, long date) {
        super(date);
        this.manager = manager;
        this.starting = starting;
        this.game = game;
        this.boids = boids;
    }

    @Override
    public void execute() {
        if (starting) {
            game.emptyGrid();
            game.fillGrid();
            game.draw();
        } else {
            game.reInitializeNewGrid();
            for (int i = 0; i < boids.getGridSize(); i++) {
                for (int j = 0; j < boids.getGridSize(); j++) {
                    for (Boid boid : boids.getBoidGrid()[i][j]) {
                        // Take into account the different boid update time
                        if (super.getDate() % boid.getCaracteristics().getUpdateTime() == 0) {
                            boids.computeNewBoidState(i, j, boid);
                        } else {
                            boids.copyBoid(i, j, boid);
                        }
                    }
                }
            }
            game.swapGrids();
            game.draw();
        }
        manager.addEvent(new BoidsEvent(manager, false, game, boids, super.getDate() + 1));
    }
}
