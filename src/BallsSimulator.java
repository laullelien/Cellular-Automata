import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;

import java.awt.*;

class TestBallsSimulator {

    public static void main(String[] args) {
        BallsSimulator ballsSimulator = new BallsSimulator();
    }
}

public class BallsSimulator implements Simulable {
    final Balls balls;
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
        draw();
    }

    @Override

    public void next() {
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
        draw();
    }

    @Override

    public void restart() {
        balls.reInit();
        for (int i = 0; i < balls.getBallNumber(); i++) {
            velocity[i].set(10, 10);
        }
        draw();
    }

    private void draw() {
        final int radius = 10;
        gui.reset();
        for (int i = 0; i < balls.getBallNumber(); i++) {
            this.gui.addGraphicalElement(new Oval(balls.getBallPos()[i].x, balls.getBallPos()[i].y, Color.RED, Color.RED, radius));
        }
    }
}

