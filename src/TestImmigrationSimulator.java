import java.awt.*;

class SmallTwoColors {
    public static void main(String[] args) {
        Color[] colors = {new Color(10, 10, 100), new Color(255, 255, 255) };
        Immigration i = new Immigration(11, 2, colors);
        new ImmigrationSimulator(303, i);
    }
}

class Small {
    public static void main(String[] args) {
        Color[] colors = {new Color(10, 10, 100),new Color(100, 0,200), new Color(190, 0, 200),
                new Color(255, 255, 255) };
        Immigration i = new Immigration(11, 4, colors);
        new ImmigrationSimulator(303, i);
    }
}

class MediumThreeColors {
    public static void main(String[] args) {
        Color[] colors = {new Color(10, 10, 100),new Color(100, 0,200), new Color(255, 255, 255) };
        Immigration i = new Immigration(50, 3, colors);
        new ImmigrationSimulator(500, i);
    }
}

class BigThreeColors {
    public static void main(String[] args) {
        Color[] colors = {new Color(10, 10, 100),new Color(100, 0,200), new Color(255, 255, 255) };
        Immigration i = new Immigration(10, 3, colors);
        new ImmigrationSimulator(1000, i);
    }
}