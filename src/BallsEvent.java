public class BallsEvent extends Event{
    private final EventManager manager;
    final boolean starting;
    BallsSimulator game;
    final private Balls balls;

    public BallsEvent(EventManager manager, boolean starting, BallsSimulator game, Balls balls, long date) {
        super(date);
        this.manager = manager;
        this.starting = starting;
        this.game = game;
        this.balls = balls;
    }

    @Override
    public void execute() {
        Vector[] velocity = game.getVelocity();
        if(starting) {
            balls.reInit();
            for (int i = 0; i < balls.getBallNumber(); i++) {
                velocity[i].set(10, 10);
            }
            game.draw();
        }
        else {
            int windowSize = game.getWindowSize();
            // Mirrors the velocity if the ball goes outside the window in the next frame
            for (int i = 0; i < balls.getBallNumber(); i++) {
                if (balls.getBallPos()[i].x + velocity[i].getX() < 0 || balls.getBallPos()[i].x + velocity[i].getX() > windowSize) {
                    velocity[i].setX(-velocity[i].getX());
                } else {
                    balls.getBallPos()[i].x += velocity[i].getX();
                }
                if (balls.getBallPos()[i].y + velocity[i].getY() < 0 || balls.getBallPos()[i].y + velocity[i].getY() > windowSize) {
                    velocity[i].setY(-velocity[i].getY());
                } else {
                    balls.getBallPos()[i].y += velocity[i].getY();
                }
            }
            game.draw();
        }
        manager.addEvent(new BallsEvent(manager, false, game, balls, super.getDate() + 1));
    }
}
