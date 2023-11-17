import java.awt.*;
import java.util.Random;

class TestBoid {
    public static void main(String[] args) {
        BoidCharacteristics caracteristics = new BoidCharacteristics(Color.BLUE, 1, 12, 15, 0.03, 0.1, 100, 100, 0.2, 1);
        Boid first = new Boid(caracteristics, new DVector(0, 0), new DVector(1, 0));
        Boid second = new Boid(caracteristics, new DVector(-1, 0), new DVector(-1, 0));
        System.out.println(first.getOrientation());
        System.out.println(second.getOrientation());
        System.out.println(first.isVisible(second) + " " + second.isVisible(first));
        second = new Boid(caracteristics, new DVector(1, 0), new DVector(0, 0));
        System.out.println(first.isVisible(second));
        second = new Boid(caracteristics, new DVector(0, 1), new DVector(0, 0));
        System.out.println(first.isVisible(second));
        second = new Boid(caracteristics, new DVector(-1, 1), new DVector(0, 0));
        System.out.println(first.isVisible(second));
        second = new Boid(caracteristics, new DVector(-2, 1), new DVector(0, 0));
        System.out.println(first.isVisible(second));
        second = new Boid(caracteristics, new DVector(-3, 1), new DVector(0, 0));
        System.out.println(first.isVisible(second));
    }
}

public class Boid {
    final static private double visibilityThreshold = 0.9;
    private final BoidCharacteristics caracteristics;
    final private DVector position;
    final private DVector velocity;

    public Boid(BoidCharacteristics caracteristics, DVector position, DVector velocity) {
        this.caracteristics = caracteristics;
        this.position = position;
        this.velocity = velocity;
    }

    /*
    Generates a boid with random positions (inside the window)
    and velocities (less than the boid's size)
     */
    public Boid(BoidCharacteristics caracteristics, int windowSize) {
        Random r = new Random();
        int boidSize = caracteristics.getBoidSize();

        this.position = new DVector(windowSize * r.nextDouble(), windowSize * r.nextDouble());
        this.velocity = new DVector(boidSize * r.nextDouble(), boidSize * r.nextDouble());
        velocity.add(-boidSize / 2);
        this.caracteristics = caracteristics;
    }

    //Determines if the other boid isn't too much behind
    // this boid to influence it
    public boolean isVisible(Boid other) {
        DVector vectorBoids = DVector.minus(other.position, this.position);

        DVector orientation = this.getOrientation();
        vectorBoids.selfNormalize();

        double dotProduct = DVector.dotProduct(orientation, vectorBoids);

        return dotProduct >= -visibilityThreshold;
    }

    public DVector getOrientation() {
        return velocity.normalize();
    }

    public DVector getPosition() {
        return this.position;
    }

    public DVector getVelocity() {
        return this.velocity;
    }

    public BoidCharacteristics getCaracteristics() {
        return caracteristics;
    }

    /*
    Recomputes random positions and velocities for this boid
     */
    public void randomize(int windowSize) {
        int boidSize = caracteristics.getBoidSize();
        position.randomize(windowSize);
        velocity.randomize(boidSize);
        velocity.add(-boidSize / 2);
    }

}
