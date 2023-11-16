import java.awt.*;

public class BoidCaracteristics {
    private final Color color;
    private final double mass;
    private final int boidSize;
    private final int velocityMax;
    private final double cohesionConstant;
    private final double alignmentConstant;
    private final double separationConstant;
    private final double wallRepulsionConstant;
    private final double frictionVelocityThreshold;
    private final int updateTime;

    public BoidCaracteristics(Color color, double mass, int boidSize, int velocityMax, double cohesionConstant, double alignmentConstant, double separationConstant, double wallRepulsionConstant, double frictionVelocityThreshold, int updateTime) {
        this.color = color;
        this.mass = mass;
        this.boidSize = boidSize;
        this.velocityMax = velocityMax;
        this.cohesionConstant = cohesionConstant;
        this.alignmentConstant = alignmentConstant;
        this.separationConstant = separationConstant;
        this.wallRepulsionConstant = wallRepulsionConstant;
        this.frictionVelocityThreshold = frictionVelocityThreshold;
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

    public int getVelocityMax() {
        return velocityMax;
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

    public double getFrictionVelocityThreshold() {
        return frictionVelocityThreshold;
    }

    public int getUpdateTime() {
        return updateTime;
    }
}
