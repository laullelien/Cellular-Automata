import java.util.Random;
import java.awt.*;

public class Boid {
    final static private double VISIBILITY_THRESHOLD = 0.8;
    private DVector position;
    private DVector acceleration;
    private DVector velocity;

    public Boid(DVector position, DVector acceleration, DVector velocity) {
        this.position = position;
        this.acceleration = acceleration;
        this.velocity = velocity;
    }

    public Boid(int windowSize, int boidSize) {
        Random r = new Random();

        this.position = new DVector(windowSize * r.nextDouble(), windowSize * r.nextDouble());
        this.velocity = new DVector(boidSize * r.nextDouble(), boidSize * r.nextDouble());
        velocity.add(-boidSize / 2);
        this.acceleration = new DVector(0, 0);
    }

    public boolean isVisible(Boid b) {
        DVector vectorBoids = DVector.minus(b.position, this.position);

        DVector orientation = this.getOrientation();
        vectorBoids.selfNormalize();

        double dotProduct = DVector.dotProduct(orientation, vectorBoids);

        return dotProduct >= -VISIBILITY_THRESHOLD;
    }

    public DVector getOrientation() {
        return velocity.normalize();
    }

    public DVector getPosition() {
        return this.position;
    }

    public void randomize(int windowSize, int boidSize) {
        Random r = new Random();

        position.randomize(windowSize);
        velocity.randomize(boidSize);
        velocity.add(-boidSize / 2);
        acceleration.nullify();
    }

}
