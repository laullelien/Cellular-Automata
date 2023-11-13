import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;
import java.util.ArrayList;

public class BoidsSimulator implements Simulable {
    final private Boids boids;
    final private GUISimulator gui;
    private final int windowSize;
    EventManager manager;
            
    public BoidsSimulator(int windowSize, Color backgroundColor, Boids boids) {
        this.windowSize = windowSize;
        this.boids = boids;
        this.gui = new GUISimulator(windowSize, windowSize, backgroundColor, this);
        manager = new EventManager();
        restart();
    }
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
        for (int i = 0; i < boids.getGridSize(); i++) {
            for (int j = 0; j < boids.getGridSize(); j++) {
                for (Boid boid : boids.getBoidGrid()[i][j]) {
                    drawBoid(boid);
                }
            }
        }
    }

    public void emptyGrid() {
        for (int i = 0; i < boids.getGridSize(); i++) {
            for (int j = 0; j < boids.getGridSize(); j++) {
                boids.getBoidGrid()[i][j].clear();
            }
        }
    }

    public void fillGrid() {
        gui.addGraphicalElement(new Rectangle((windowSize + 1) / 2, (windowSize + 1) / 2, Color.black, Color.white, windowSize));
        ArrayList<Boid> boidsList = boids.getBoidsList();
        for(Boid boid: boidsList) {
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
    @Override
    public final void next() {
        manager.next();
    }

    @Override
    public final void restart() {
        manager.restart();
        manager.addEvent(getStartingEvent());
        next();
    }

    public Event getStartingEvent() {
        return new BoidsEvent(manager, true, this, boids, 0);
    }
}
