import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;

import java.awt.*;

class TestBallsSimulator {

    public static void main(String[] args) {
        BallsSimulator ballsSimulator = new BallsSimulator();
    }
}

public class BallsSimulator extends EventSimulable {
    final private Balls balls;
    final private GUISimulator gui;
    final private Vector[] velocity;
    final private int windowSize = 500;

    public BallsSimulator() {
        gui = new GUISimulator(windowSize, windowSize, Color.BLACK);
        gui.setSimulable(this);
        balls = new Balls();
        velocity = new Vector[balls.getBallNumber()];
        for (int i = 0; i < balls.getBallNumber(); i++) {
            velocity[i] = new Vector(10, 10);
        }
        restart();
    }

    @Override
    public Event getStartingEvent() {
        return new BallsEvent(super.getManager(), true, this, balls, 0);
    }

    public void draw() {
        final int radius = 10;
        gui.reset();
        for (int i = 0; i < balls.getBallNumber(); i++) {
            this.gui.addGraphicalElement(new Oval(balls.getBallPos()[i].x, balls.getBallPos()[i].y, Color.RED, Color.RED, radius));
        }
    }

    public Vector[] getVelocity() {
        return velocity;
    }

    public int getWindowSize() {
        return windowSize;
    }
}

