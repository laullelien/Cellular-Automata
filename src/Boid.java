import java.util.Random;

public class Boid {
    private DVector position;
    private DVector acceleration;
    private DVector velocity;
    
    final static private double visibilityThreshold = 0.8;

    public Boid(DVector position, DVector acceleration, DVector velocity) {
        this.position = position;
        this.acceleration = acceleration;
        this.velocity = velocity;
    }

    public Boid(int windowSize) {
        Random r = new Random();

        this.position = new DVector(r.nextInt(windowSize), r.nextInt(windowSize));
        this.velocity = new DVector(windowSize * r.nextDouble(), windowSize * r.nextDouble());
        this.acceleration = new DVector(windowSize * r.nextDouble(), windowSize * r.nextDouble());
    }
    
    public boolean isVisible(Boid b){
        DVector vectorBoids = DVector.minus(b.position, this.position);

        DVector orientation = this.getOrientation();
        vectorBoids.normalize();

        double dotProduct = DVector.dotProduct(orientation, vectorBoids);
        
        return dotProduct >= -visibilityThreshold;
    }

    public DVector getOrientation() {
        return velocity.normalize();
    }

}
