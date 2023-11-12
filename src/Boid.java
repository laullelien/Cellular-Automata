import java.util.Random;

class TestBoid {
    public static void main(String[] args) {
        Boid first = new Boid(new DVector(0, 0), new DVector(1, 0));
        Boid second = new Boid(new DVector(-1, 0), new DVector(-1, 0));
        System.out.println(first.getOrientation());
        System.out.println(second.getOrientation());
        System.out.println(first.isVisible(second) + " " + second.isVisible(first));
        second = new Boid(new DVector(1, 0), new DVector(0, 0));
        System.out.println(first.isVisible(second));
        second = new Boid(new DVector(0, 1), new DVector(0, 0));
        System.out.println(first.isVisible(second));
        second = new Boid(new DVector(-1, 1), new DVector(0, 0));
        System.out.println(first.isVisible(second));
        second = new Boid(new DVector(-2, 1), new DVector(0, 0));
        System.out.println(first.isVisible(second));
        second = new Boid(new DVector(-3, 1), new DVector(0, 0));
        System.out.println(first.isVisible(second));
    }
}

public class Boid {
    final static private double visibilityThreshold = 0.9;
    private DVector position;
    private DVector velocity;

    public Boid(DVector position, DVector velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public Boid(int windowSize, int boidSize) {
        Random r = new Random();

        this.position = new DVector(windowSize * r.nextDouble(), windowSize * r.nextDouble());
        this.velocity = new DVector(boidSize * r.nextDouble(), boidSize * r.nextDouble());
        velocity.add(-boidSize / 2);
    }

    public boolean isVisible(Boid b) {
        DVector vectorBoids = DVector.minus(b.position, this.position);

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


    public void randomize(int windowSize, int boidSize) {
        Random r = new Random();

        position.randomize(windowSize);
        velocity.randomize(boidSize);
        velocity.add(-boidSize / 2);
    }

}
