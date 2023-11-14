import java.awt.*;

public class TestBoidsSimulator {
    public static void main(String[] args) {
        BoidCaracteristics blueCaracteristics = new BoidCaracteristics(Color.BLUE, 1, 12, 2, 0.03, 0.1, 100, 100, 0.2, 2);
        BoidCaracteristics redCaracteristics = new BoidCaracteristics(Color.RED, 10, 30, 1, 0.01, 0.05, 300, 50, 0.1, 4);
        BoidCaracteristics greenCaracteristics = new BoidCaracteristics(Color.GREEN, 0.1, 3, 4, 0.05, 0.02, 10, 20, 0.3, 1);
        Boids boids = new Boids(500, 20);
        boids.addBoidGroup(30, blueCaracteristics);
        boids.addBoidGroup(15, redCaracteristics);
        boids.addBoidGroup(50, greenCaracteristics);
        new BoidsSimulator(500, Color.WHITE, boids);
    }
}
