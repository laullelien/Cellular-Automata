import gui.GUISimulator;
import gui.Simulable;

import java.awt.*;
import java.util.ArrayList;

class TestBoidsSimulable {
    public static void main(String[] args) {
        new BoidsSimulable(800, Color.green, Color.white, 10, 20);
    }
}

public class BoidsSimulable implements Simulable {
    final private static int boidSize = 12;
    final private GUISimulator gui;
    private final int windowSize;
    private final Color boidColor;
    private final int neighbourInfluenceCircleRadius;
    private final int neighbourInfluenceCircleDiametre;
    private final int gridSize;
    private final int boidNumber;
    ArrayList<Boid>[][] boidGrid;

    BoidsSimulable(int windowSize, Color boidColor, Color backgroundColor, int neighbourInfluenceCircleRadius, int boidNumber) {
        this.windowSize = windowSize;
        this.boidColor = boidColor;
        this.neighbourInfluenceCircleRadius = neighbourInfluenceCircleRadius;
        this.neighbourInfluenceCircleDiametre = 2 * neighbourInfluenceCircleRadius;
        gridSize = windowSize / neighbourInfluenceCircleDiametre;
        boidGrid = new ArrayList[gridSize][gridSize];
        this.boidNumber = boidNumber;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                boidGrid[i][j] = new ArrayList<Boid>();
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
        for (int i = 0; i < boidNumber; i++) {
            Boid boid = new Boid(windowSize, boidSize);
            Point coordinates = DVector.getCoordinates(boid.getPosition(), neighbourInfluenceCircleDiametre);
            boidGrid[coordinates.x][coordinates.y].add(boid);
            drawBoid(boid);
        }
    }

    @Override
    public void next() {

    }

    @Override
    public void restart() {
        emptyGrid();
        fillGrid();
        draw();
    }
}
