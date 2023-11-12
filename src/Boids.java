import java.awt.*;
import java.util.ArrayList;

public class Boids {
    private int windowSize;
    private int gridSize;
    private int boidNumber;
    private Color boidColor;

    private int boidMass;
    private int boidSize;

    private int velocityMax;

    private double cohesionConstant;
    private double alignmentConstant;
    private double separationConstant;
    private double wallRepulsionConstant;
    private double frictionConstant;
    private int neighbourInfluenceCircleRadius;
    private int neighbourInfluenceCircleDiametre;
    ArrayList<Boid>[][] boidGrid;
    ArrayList<Boid>[][] newBoidGrid;

    public Boids(int windowSize, double cohesionConstant, double alignmentConstant, double separationConstant,
                 double wallRepulsionConstant, double frictionConstant, int velocityMax,
                 int neighbourInfluenceCircleRadius, int neighbourInfluenceCircleDiametre,
                 int boidMass, int boidSize, int boidNumber, Color boidColor) {
        this.windowSize = windowSize;
        this.cohesionConstant = cohesionConstant;
        this.alignmentConstant = alignmentConstant;
        this.separationConstant = separationConstant;
        this.wallRepulsionConstant = wallRepulsionConstant;
        this.frictionConstant = frictionConstant;
        this.velocityMax = velocityMax;
        this.neighbourInfluenceCircleRadius = neighbourInfluenceCircleRadius;
        this.neighbourInfluenceCircleDiametre = neighbourInfluenceCircleDiametre;
        this.boidMass = boidMass;
        this.boidSize = boidSize;
        this.boidNumber = boidNumber;
        this.boidColor = boidColor;

        if (windowSize % neighbourInfluenceCircleDiametre == 0) {
            gridSize = windowSize / neighbourInfluenceCircleDiametre + 1;
        } else {
            gridSize = windowSize / neighbourInfluenceCircleDiametre;
        }

        newBoidGrid = new ArrayList[gridSize][gridSize];
        boidGrid = new ArrayList[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                boidGrid[i][j] = new ArrayList<Boid>();
                newBoidGrid[i][j] = new ArrayList<Boid>();
            }
        }

        for (int i = 0; i < boidNumber; i++) {
            Boid boid = new Boid(windowSize, boidSize / 2);
            Point coordinates = DVector.getCoordinates(boid.getPosition(), neighbourInfluenceCircleDiametre);
            boidGrid[coordinates.x][coordinates.y].add(boid);
        }
    }


    // Computes the new position, velocity and acceleration of boids and stores is in the new grid
    // The grid is created in such a way that neighbours of a boid in a certain cell can only be found in the 8 neighbouring cells
    public void computeNewBoidState(int i, int j, Boid boid) {
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
                        acceleration.add(DVector.mult(separationVector, -cohesionConstant));
                        // Alignment
                        acceleration.add(DVector.mult(other.getVelocity(), alignmentConstant));
                        // Separation
                        acceleration.add(DVector.mult(separationVector, separationConstant / DVector.dotProduct(separationVector, separationVector)));
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
        wallRepulsion.mult(wallRepulsionConstant);
        acceleration.add(wallRepulsion);
        // Add friction
        double velocityMagnitude = velocity.magnitude();
        if (velocityMagnitude > velocityMax)
            acceleration.add(DVector.mult(velocity, -frictionConstant));
        // Update the new boid characteristics
        newVelocity.add(acceleration);
        newPosition.add(newVelocity);
        fixPosition(newPosition);
        //Insert the boid in new grid
        Point coordinates = DVector.getCoordinates(newPosition, neighbourInfluenceCircleDiametre);
        newBoidGrid[coordinates.x][coordinates.y].add(new Boid(newPosition, newVelocity));
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

    public int getBoidNumber() {
        return boidNumber;
    }

    public int getboidMass() {
        return boidMass;
    }

    public Color getBoidColor() {
        return boidColor;
    }

    public int getBoidSize() {
        return boidSize;
    }

    public double getCohesionConstant() {
        return cohesionConstant;
    }

    public double getAlignmentConstant() {
        return alignmentConstant;
    }

    public double getSeparationConstant() {
        return separationConstant;
    }

    public void setBoidGrid(ArrayList<Boid>[][] boidGridInit) {
        boidGrid = boidGridInit;
    }

    public void setNewBoidGrid(ArrayList<Boid>[][] newBoidGridInit) {
        newBoidGrid = newBoidGridInit;
    }

    public ArrayList<Boid>[][] getBoidGrid() {
        return boidGrid;
    }

    public ArrayList<Boid>[][] getNewBoidGrid() {
        return newBoidGrid;
    }

    public int getNeighbourInfluenceCircleDiametre() {
        return neighbourInfluenceCircleDiametre;
    }

    public int getNeighbourInfluenceCircleRadius() {
        return neighbourInfluenceCircleRadius;
    }

    public int getGridSize() {
        return gridSize;
    }

}
