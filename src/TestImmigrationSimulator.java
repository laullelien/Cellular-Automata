import java.awt.*;

class TestImmigrationSimulatorSmall {
    public static void main(String[] args) {
        Color[] colors = {new Color(10, 10, 100),new Color(100, 0,200), new Color(255, 255, 255) };
        Immigration i = new Immigration(11, 3, colors);
        new ImmigrationSimulator(303, i);
    }
}

class TestImmigrationSimulator {
    public static void main(String[] args) {
        Color[] colors = {new Color(10, 10, 100),new Color(100, 0,200), new Color(255, 255, 255) };
        Immigration i = new Immigration(50, 3, colors);
        new ImmigrationSimulator(500, i);
    }
}