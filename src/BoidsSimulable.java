/*
import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;
import java.util.ArrayList;

class TestBoidsSimulable {
    public static void main(String[] args) {
        new BoidsSimulable(800, Color.green, Color.white, 50, 20);
    }
}

public class BoidsSimulable implements Simulable {
    final private static int boidSize = 12;
    private final static double COHESION_CONSTANT = 0.03;
    private final static double ALIGNMENT_CONSTANT = 0.1;
    private final static double SEPARATION_CONSTANT = 100;
    private final static double WALL_REPULSION_CONSTANT = 100;
    private final static double FRICTION_CONSTANT = 0.2;
    private final static double MAX_VELOCITY = 15;
    final private GUISimulator gui;
    private final int windowSize;
    private final Color boidColor;
    private final int neighbourInfluenceCircleRadius;
    private final int neighbourInfluenceCircleDiametre;
    private final int gridSize;
    private final int boidNumber;
    ArrayList<Boid>[][] boidGrid;
    ArrayList<Boid>[][] newBoidGrid;

    BoidsSimulable(int windowSize, Color boidColor, Color backgroundColor, int neighbourInfluenceCircleRadius, int boidNumber) {
        this.windowSize = windowSize;
        this.boidColor = boidColor;
        this.neighbourInfluenceCircleRadius = neighbourInfluenceCircleRadius;
        this.neighbourInfluenceCircleDiametre = 2 * neighbourInfluenceCircleRadius;
        if (windowSize % neighbourInfluenceCircleDiametre == 0) {
            gridSize = windowSize / neighbourInfluenceCircleDiametre + 1;
        } else {
            gridSize = windowSize / neighbourInfluenceCircleDiametre;
        }
        this.boidNumber = boidNumber;
        newBoidGrid = new ArrayList[gridSize][gridSize];
        boidGrid = new ArrayList[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                boidGrid[i][j] = new ArrayList<Boid>();
                newBoidGrid[i][j] = new ArrayList<Boid>();
            }
        }
        this.gui = new GUISimulator(windowSize, windowSize, backgroundColor, this);
        fillGrid();
    }

    private void drawBoid(Boid boid) {
        DVector position = boid.getPosition();
        DVector orientation = boid.getOrientation();
        orientation.mult(boidSize);
        DVector leftPoint = DVector.rotate(orientation, 150);
        DVector rightPoint = DVector.rotate(orientation, -150);
        orientation.add(position);
        leftPoint.add(position);
        rightPoint.add(position);
        gui.addGraphicalElement(new Triangle(DVector.toPoint(orientation), DVector.toPoint(rightPoint), DVector.toPoint(leftPoint), boidColor));
    }

    private void draw() {
        gui.reset();
        gui.addGraphicalElement(new Rectangle((windowSize + 1) / 2, (windowSize + 1) / 2, Color.black, Color.white, windowSize));
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                for (Boid boid : boidGrid[i][j]) {
                    drawBoid(boid);
                }
            }
        }
    }

    private void emptyGrid() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                boidGrid[i][j].clear();
            }
        }
    }

    private void fillGrid() {
        gui.addGraphicalElement(new Rectangle((windowSize + 1) / 2, (windowSize + 1) / 2, Color.black, Color.white, windowSize));
        for (int i = 0; i < boidNumber; i++) {
            Boid boid = new Boid(windowSize, boidSize / 2);
            Point coordinates = DVector.getCoordinates(boid.getPosition(), neighbourInfluenceCircleDiametre);
            boidGrid[coordinates.x][coordinates.y].add(boid);
            drawBoid(boid);
        }
    }

    private void reInitializeNewGrid() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                newBoidGrid[i][j].clear();
            }
        }
    }

    private boolean isNeighbour(Boid boid, Boid other) {
        DVector position = boid.getPosition();
        DVector otherPosition = other.getPosition();
        return position.distanceTo(otherPosition) <= neighbourInfluenceCircleRadius;
    }

    // Computes the new position, velocity and acceleration of boids and stores is in the new grid
    // The grid is created in such a way that neighbours of a boid in a certain cell can only be found in the 8 neighbouring cells
    private void computeNewBoidState(int i, int j, Boid boid) {
        DVector position = boid.getPosition();
        DVector velocity = boid.getVelocity();
        DVector newPosition = boid.getPosition().copy();
        DVector newVelocity = boid.getVelocity().copy();
        DVector acceleration = new DVector(0, 0);
        double neighbourNumber = 0;
        for (int k = Math.max(i - 1, 0); k <= Math.min(i + 1, gridSize - 1); k++) {
            for (int l = Math.max(j - 1, 0); l <= Math.min(j + 1, gridSize - 1); l++) {
                for (Boid other : boidGrid[k][l]) {
                    if (isNeighbour(boid, other) && boid.isVisible(other) && boid != other) {
                        neighbourNumber++;
                        DVector separationVector = DVector.minus(position, other.getPosition());
                        // Cohesion
                        acceleration.add(DVector.mult(separationVector, -COHESION_CONSTANT));
                        // Alignment
                        acceleration.add(DVector.mult(other.getVelocity(), ALIGNMENT_CONSTANT));
                        // Separation
                        acceleration.add(DVector.mult(separationVector, SEPARATION_CONSTANT / DVector.dotProduct(separationVector, separationVector)));
                    }
                }
            }
        }
        // Take the average for each law
        if (neighbourNumber > 0)
            acceleration.mult(1 / neighbourNumber);
        // Add sides repulsion
        final double dWindowSize = (double) windowSize;
        DVector wallRepulsion = new DVector(1 / position.getX() - 1 / (dWindowSize - position.getX()), 1 / position.getY() - 1 / (dWindowSize - position.getY()));
        wallRepulsion.mult(WALL_REPULSION_CONSTANT);
        acceleration.add(wallRepulsion);
        // Add friction
        double velocityMagnitude = velocity.magnitude();
        if (velocityMagnitude > MAX_VELOCITY)
            acceleration.add(DVector.mult(velocity, -FRICTION_CONSTANT));
        // Update the new boid characteristics
        newVelocity.add(acceleration);
        newPosition.add(newVelocity);
        fixPosition(newPosition);
        //Insert the boid in new grid
        Point coordinates = DVector.getCoordinates(newPosition, neighbourInfluenceCircleDiametre);
        newBoidGrid[coordinates.x][coordinates.y].add(new Boid(newPosition, newVelocity));
    }

    private void fixPosition(DVector newPosition) {
        if (newPosition.getX() <= 0) newPosition.setX(1);
        else if (newPosition.getX() >= windowSize) newPosition.setX(windowSize - 1);
        if (newPosition.getY() <= 0) newPosition.setY(1);
        else if (newPosition.getY() >= windowSize) newPosition.setY(windowSize - 1);
    }

    private void swapGrids() {
        ArrayList<Boid>[][] temp = boidGrid;
        boidGrid = newBoidGrid;
        newBoidGrid = temp;
    }

    @Override
    public void next() {
        reInitializeNewGrid();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                for (Boid boid : boidGrid[i][j])
                    computeNewBoidState(i, j, boid);
            }
        }
        swapGrids();
        draw();
    }


    @Override
    public void restart() {
        emptyGrid();
        fillGrid();
        draw();
    }
}
*/
