import java.awt.*;

/*
Stores all the characteristics of a boid
 */
public class BoidCharacteristics {
    private final Color color;
    private final double mass;
    private final int boidSize;
    private final int frictionVelocityThreshold;
    private final double cohesionConstant;
    private final double alignmentConstant;
    private final double separationConstant;
    private final double wallRepulsionConstant;
    private final double frictionConstant;
    /**
    The number of frame taken to update its state
     */
    private final int updateTime;

    public BoidCharacteristics(Color color, double mass, int boidSize, int frictionVelocityThreshold, double cohesionConstant, double alignmentConstant, double separationConstant, double wallRepulsionConstant, double frictionConstant, int updateTime) {
        this.color = color;
        this.mass = mass;
        this.boidSize = boidSize;
        this.frictionVelocityThreshold = frictionVelocityThreshold;
        this.cohesionConstant = cohesionConstant;
        this.alignmentConstant = alignmentConstant;
        this.separationConstant = separationConstant;
        this.wallRepulsionConstant = wallRepulsionConstant;
        this.frictionConstant = frictionConstant;
        this.updateTime = updateTime;
    }

    public Color getColor() {
        return color;
    }

    public double getMass() {
        return mass;
    }

    public int getBoidSize() {
        return boidSize;
    }

    public int getFrictionVelocityThreshold() {
        return frictionVelocityThreshold;
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

    public double getWallRepulsionConstant() {
        return wallRepulsionConstant;
    }

    public double getFrictionConstant() {
        return frictionConstant;
    }

    public int getUpdateTime() {
        return updateTime;
    }
}
