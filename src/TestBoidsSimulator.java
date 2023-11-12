import java.awt.*;

public class TestBoidsSimulator {
    public static void main(String[] args) {
        Boids b1 = new Boids(500, 0.03,0.1,100,
                100, 0.2, 15, 50, 100,
                1, 12, 20, Color.BLUE);
        BoidsSimulator sim = new BoidsSimulator(500, Color.WHITE, b1);
    }
}
