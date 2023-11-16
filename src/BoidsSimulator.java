import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;
import java.util.ArrayList;

public class BoidsSimulator extends EventSimulable {
    final private Boids boids;
    final private GUISimulator gui;
    private final int windowSize;

    public BoidsSimulator(int windowSize, Color backgroundColor, Boids boids) {
        super();
        this.windowSize = windowSize;
        this.boids = boids;
        this.gui = new GUISimulator(windowSize, windowSize, backgroundColor, this);
        super.restart();
    }

    /*
    Computes the points needed to draw the triangle representing the boid
    and draws it
     */
    private void drawBoid(Boid boid) {
        BoidCaracteristics caracteristics = boid.getCaracteristics();
        DVector position = boid.getPosition();
        DVector orientation = boid.getOrientation();
        orientation.mult(caracteristics.getBoidSize());
        DVector leftPoint = DVector.rotate(orientation, 150);
        DVector rightPoint = DVector.rotate(orientation, -150);
        orientation.add(position);
        leftPoint.add(position);
        rightPoint.add(position);
        gui.addGraphicalElement(new Triangle(DVector.toPoint(orientation), DVector.toPoint(rightPoint), DVector.toPoint(leftPoint), caracteristics.getColor()));
    }

    public void draw() {
        gui.reset();
        gui.addGraphicalElement(new Rectangle((windowSize + 1) / 2, (windowSize + 1) / 2, Color.black, Color.white, windowSize));
        int gridSize = boids.getGridSize();
        ArrayList<Boid>[][] grid = boids.getBoidGrid();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                for (Boid boid : grid[i][j]) {
                    drawBoid(boid);
                }
            }
        }
    }

    public void emptyGrid() {
        int gridSize = boids.getGridSize();
        ArrayList<Boid>[][] grid = boids.getBoidGrid();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j].clear();
            }
        }
    }

    /*
    Resets the boid grid with the same boids as before but
    with new random positions and velocities and draws them
     */
    public void fillGrid() {
        gui.addGraphicalElement(new Rectangle((windowSize + 1) / 2, (windowSize + 1) / 2, Color.black, Color.white, windowSize));
        ArrayList<Boid> boidsList = boids.getBoidsList();
        for (Boid boid : boidsList) {
            boid.randomize(windowSize);
            Point coordinates = DVector.getCoordinates(boid.getPosition(), boids.getNeighbourInfluenceCircleDiametre());
            boids.getBoidGrid()[coordinates.x][coordinates.y].add(boid);
            drawBoid(boid);
        }
    }

    public void reInitializeNewGrid() {
        for (int i = 0; i < boids.getGridSize(); i++) {
            for (int j = 0; j < boids.getGridSize(); j++) {
                boids.getNewBoidGrid()[i][j].clear();
            }
        }
    }


    public void swapGrids() {
        ArrayList<Boid>[][] temp = boids.getBoidGrid();
        boids.setBoidGrid(boids.getNewBoidGrid());
        boids.setNewBoidGrid(temp);
    }

    public Event getStartingEvent() {
        return new BoidsEvent(super.getManager(), true, this, boids, 0);
    }
}
