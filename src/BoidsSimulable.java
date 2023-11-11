import gui.GUISimulator;
import gui.Simulable;

import java.awt.*;
import java.util.ArrayList;

public class BoidsSimulable implements Simulable {
    final private GUISimulator gui;
    final private static int boidSize = 20;
    ArrayList<Boid>[][] boidGrid;
    private final Color boidColor;
    private final int neighbourInfluenceCircleRadius;
    private final int neighbourInfluenceCircleDiametre;
    private final int gridSize;

    BoidsSimulable(int windowSize, Color boidColor, Color backgroundColor, int neighbourInfluenceCircleRadius, int boidNumber) {
        this.boidColor = boidColor;
        this.neighbourInfluenceCircleRadius = neighbourInfluenceCircleRadius;
        this.neighbourInfluenceCircleDiametre = 2 * neighbourInfluenceCircleRadius;
        gridSize = windowSize / neighbourInfluenceCircleDiametre;
        boidGrid = new ArrayList[gridSize][gridSize];
        this.gui = new GUISimulator(windowSize, windowSize, backgroundColor, this);
    }

    public int getNeighbourInfluenceCircleRadius() {
        return neighbourInfluenceCircleRadius;
    }

    public int getNeighbourInfluenceCircleDiametre() {
        return neighbourInfluenceCircleDiametre;
    }

    private void drawBoid(Boid boid) {
        DVector position = boid.getPosition();
        DVector orientation = boid.getOrientation();
        DVector topPoint = DVector.mult(position, boidSize / 2);
        DVector leftPoint = DVector.rotate(position, boid.getOrientation(), 90);
        DVector rightPoint = DVector.rotate(position, boid.getOrientation(), -90);
        gui.addGraphicalElement(new Triangle(DVector.toPoint(topPoint), DVector.toPoint(rightPoint), DVector.toPoint(leftPoint), boidColor));
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

    @Override
    public void next() {

    }

    @Override
    public void restart() {

    }
}
