import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;
import java.util.ArrayList;

public class BoidsSimulable implements Simulable {
    final private GUISimulator gui;
    final private int boidSize = 20;
    ArrayList<Boid>[][] boidGrid;
    private int windowSize;
    private Color boidColor;
    private int neighbourInfluenceCircleRadius;
    private int neighbourInfluenceCircleDiametre;
    private int gridSize;

    BoidsSimulable(int windowSize, Color boidColor, Color backgroundColor, int neighbourInfluenceCircleRadius, int boidNumber) {
        this.windowSize = windowSize;
        this.boidColor = boidColor;
        this.neighbourInfluenceCircleRadius = neighbourInfluenceCircleRadius;
        this.neighbourInfluenceCircleDiametre = 2 * neighbourInfluenceCircleRadius;
        gridSize = windowSize / neighbourInfluenceCircleDiametre;
        boidGrid = new ArrayList[gridSize][gridSize];
        this.gui = new GUISimulator(this.windowSize, this.windowSize, backgroundColor, this);
    }

    public int getNeighbourInfluenceCircleRadius() {
        return neighbourInfluenceCircleRadius;
    }

    public int getNeighbourInfluenceCircleDiametre() {
        return neighbourInfluenceCircleDiametre;
    }

    private void drawBoid(Boid boid) {
        DVector orientation = boid.getOrientation();
        DVector topPoint = orientation.mult(boidSize / 2);
        DVector leftPoint = topPoint.rotate(boid.orientation, 90);
        DVector rightPoint = topPoint.rotate(boid.orientation, -90);
        gui.addGraphicalElement(new Triangle(topPoint.toPoint(), rightPoint.toPoint(), leftPoint.toPoint(), boidColor));
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
