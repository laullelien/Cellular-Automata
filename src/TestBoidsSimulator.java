import java.awt.*;

public class TestBoidsSimulator {
    public static void main(String[] args) {
        BoidCharacteristics blueCaracteristics = new BoidCharacteristics(Color.BLUE, 1, 12, 2, 0.03, 0.1, 100, 30, 0.2, 2);
        BoidCharacteristics redCaracteristics = new BoidCharacteristics(Color.RED, 10, 30, 1, 0.01, 0.05, 300, 20, 0.1, 4);
        BoidCharacteristics greenCaracteristics = new BoidCharacteristics(Color.GREEN, 0.1, 3, 4, 0.05, 0.02, 10, 10, 0.3, 1);
        Boids boids = new Boids(500, 20);
        boids.addBoidGroup(30, blueCaracteristics);
        boids.addBoidGroup(15, redCaracteristics);
        boids.addBoidGroup(50, greenCaracteristics);
        new BoidsSimulator(500, Color.WHITE, boids);
    }
}
