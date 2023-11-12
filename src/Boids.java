import java.awt.*;
import java.util.Random;

public class Boids {
    private Boid[] boids;
    private int nbBoids;
    static private Color color;

    static private int mass;

    static private double cohesionConstant;
    static private double alignmentConstant;
    static private double separationConstant;

    public Boids(int nbBoids, int windowSize, int boidSize) {
        Random r = new Random();
        int massMax = 10;

        Boids.mass = r.nextInt(10);
        Boids.color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        this.nbBoids = nbBoids;

        this.boids = new Boid[this.nbBoids];
        for (int i = 0; i < this.nbBoids; i++) {
            this.boids[i] = new Boid(windowSize, boidSize);
        }
    }

    public Boids(int nbBoids, int windowSize, int boidSize, int mass, Color color) {
        this(nbBoids, windowSize, boidSize);
        Boids.mass = mass;
        Boids.color = color;
    }

    public int getNbBoids() {
        return nbBoids;
    }

    public int getMass() {
        return mass;
    }

    public Color getColor() {
        return color;
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

    public void setCohesionConstant(int cohesionConstant) {
        Boids.cohesionConstant = cohesionConstant;
    }

    public void setAlignmentConstant(int alignmentConstant) {
        Boids.alignmentConstant = cohesionConstant;
    }

    public void setSeparationConstant(int separationConstant) {
        Boids.separationConstant = separationConstant;
    }

    public void setMass(int mass) {
        Boids.mass = mass;
    }

    public void setColor(Color color) {
        Boids.color = color;
    }

}
