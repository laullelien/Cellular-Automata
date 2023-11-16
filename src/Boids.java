import java.awt.*;
import java.util.ArrayList;

public class Boids {
    final private int neighbourInfluenceCircleRadius;
    final private int neighbourInfluenceCircleDiametre;
    final private ArrayList<Boid> boidsList;
    final private int windowSize;
    final private int gridSize;
    private ArrayList<Boid>[][] boidGrid;
    private ArrayList<Boid>[][] newBoidGrid;

    public Boids(int windowSize, int neighbourInfluenceCircleRadius) {
        this.windowSize = windowSize;
        this.neighbourInfluenceCircleRadius = neighbourInfluenceCircleRadius;
        neighbourInfluenceCircleDiametre = 2 * neighbourInfluenceCircleRadius;
        gridSize = windowSize / neighbourInfluenceCircleDiametre + 1;

        boidsList = new ArrayList<>();
        newBoidGrid = new ArrayList[gridSize][gridSize];
        boidGrid = new ArrayList[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                boidGrid[i][j] = new ArrayList<>();
                newBoidGrid[i][j] = new ArrayList<>();
            }
        }
    }

    public void addBoidGroup(int boidNumber, BoidCaracteristics groupCaracteristics) {
        for (int i = 0; i < boidNumber; i++) {
            boidsList.add(new Boid(groupCaracteristics, windowSize));
        }
    }

    // Applies the 3 standard rules to the boids
    public void updateBoidState(Boid boid, DVector acceleration) {
        DVector newPosition = boid.getPosition().copy();
        DVector newVelocity = boid.getVelocity().copy();
        newVelocity.add(acceleration);
        newPosition.add(newVelocity);
        fixPosition(newPosition);
        Point coordinates = DVector.getCoordinates(newPosition, neighbourInfluenceCircleDiametre);
        newBoidGrid[coordinates.x][coordinates.y].add(new Boid(boid.getCaracteristics(), newPosition, newVelocity));
    }

    private void addStandardRules(Boid boid, Boid other, DVector acceleration) {
        DVector position = boid.getPosition();
        BoidCaracteristics caracteristics = boid.getCaracteristics();
        DVector separationVector = DVector.minus(position, other.getPosition());
        // Only apply Cohesion and Alignment to boids of the same family, i.e. the same characteristics
        if (boid.getCaracteristics() == other.getCaracteristics()) {
            // Cohesion
            acceleration.add(DVector.mult(separationVector, -caracteristics.getCohesionConstant()));
            // Alignment
            acceleration.add(DVector.mult(other.getVelocity(), caracteristics.getAlignmentConstant()));
        }
        // Separation
        acceleration.add(DVector.mult(separationVector, caracteristics.getSeparationConstant() / DVector.dotProduct(separationVector, separationVector)));
    }

    private void addWallRepulsion(Boid boid, DVector acceleration) {
        final double dWindowSize = windowSize;
        DVector position = boid.getPosition();
        DVector wallRepulsion = new DVector(1 / position.getX() - 1 / (dWindowSize - position.getX()), 1 / position.getY() - 1 / (dWindowSize - position.getY()));
        wallRepulsion.mult(boid.getCaracteristics().getWallRepulsionConstant());
        acceleration.add(wallRepulsion);
    }

    private void addFriction(Boid boid, DVector acceleration) {
        DVector velocity = boid.getVelocity();
        BoidCaracteristics caracteristics = boid.getCaracteristics();
        double velocityMagnitude = velocity.magnitude();
        if (velocityMagnitude > caracteristics.getVelocityMax())
            acceleration.add(DVector.mult(velocity, -caracteristics.getFrictionVelocityThreshold()));
    }

    // Computes the new position, velocity and acceleration of boids and stores is in the new grid
    // The grid is created in such a way that neighbours of a boid in a certain cell can only be found in the 8 neighbouring cells
    public void computeNewBoidState(int i, int j, Boid boid) {
        DVector acceleration = new DVector(0, 0);
        BoidCaracteristics caracteristics = boid.getCaracteristics();
        double neighbourNumber = 0;

        for (int k = Math.max(i - 1, 0); k <= Math.min(i + 1, gridSize - 1); k++) {
            for (int l = Math.max(j - 1, 0); l <= Math.min(j + 1, gridSize - 1); l++) {
                for (Boid other : boidGrid[k][l]) {
                    if (isNeighbour(boid, other) && boid.isVisible(other) && boid != other) {
                        neighbourNumber++;
                        addStandardRules(boid, other, acceleration);
                    }
                }
            }
        }
        // Take the average for each law
        if (neighbourNumber > 0)
            acceleration.mult(1 / neighbourNumber);
        // Take mass into account
        acceleration.mult(1 / caracteristics.getMass());

        addWallRepulsion(boid, acceleration);
        addFriction(boid, acceleration);

        updateBoidState(boid, acceleration);
    }

    private boolean isNeighbour(Boid boid, Boid other) {
        DVector position = boid.getPosition();
        DVector otherPosition = other.getPosition();
        return position.distanceTo(otherPosition) <= neighbourInfluenceCircleRadius;
    }

    private void fixPosition(DVector newPosition) {
        if (newPosition.getX() <= 0) newPosition.setX(1);
        else if (newPosition.getX() >= windowSize) newPosition.setX(windowSize - 1);
        if (newPosition.getY() <= 0) newPosition.setY(1);
        else if (newPosition.getY() >= windowSize) newPosition.setY(windowSize - 1);
    }

    public int getNeighbourInfluenceCircleDiametre() {
        return neighbourInfluenceCircleDiametre;
    }

    public ArrayList<Boid>[][] getBoidGrid() {
        return boidGrid;
    }

    public void setBoidGrid(ArrayList<Boid>[][] boidGrid) {
        this.boidGrid = boidGrid;
    }

    public ArrayList<Boid>[][] getNewBoidGrid() {
        return newBoidGrid;
    }

    public void setNewBoidGrid(ArrayList<Boid>[][] newBoidGrid) {
        this.newBoidGrid = newBoidGrid;
    }

    public ArrayList<Boid> getBoidsList() {
        return boidsList;
    }

    public int getWindowSize() {
        return windowSize;
    }

    public int getGridSize() {
        return gridSize;
    }

    public void copyBoid(int i, int j, Boid boid) {
        newBoidGrid[i][j].add(boid);
    }
}
