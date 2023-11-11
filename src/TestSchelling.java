import java.awt.*;

class TestShellingSmall2Colors {

    public static void main(String[] args) {
        final Color[] colors = {Color.white, Color.blue, Color.red};
        Schelling test = new Schelling(400, 4, 4, (char)2, colors);
    }
}

class TestShellingSmall3Colors {

    public static void main(String[] args) {
        final Color[] colors = {Color.white, Color.blue, Color.red, Color.green};
        Schelling test = new Schelling(400, 4, 3, (char)3, colors);
    }
}
class TestShellingSmall4Colors {

    public static void main(String[] args) {
        final Color[] colors = {Color.white, Color.blue, Color.red, Color.green, Color.magenta};
        Schelling test = new Schelling(400, 4, 3, (char)4, colors);
    }
}

class TestShellingMedium4Colors {

    public static void main(String[] args) {
        final Color[] colors = {Color.white, Color.blue, Color.red, Color.green, Color.magenta, Color.orange, Color.pink};
        Schelling test = new Schelling(800, 20, 5, (char)6, colors);
    }
}
class TestShellingBig4Colors {

    public static void main(String[] args) {
        final Color[] colors = {Color.white, Color.blue, Color.red, Color.green, Color.magenta, Color.orange, Color.pink};
        Schelling test = new Schelling(800, 100, 2, (char)6, colors);
    }
}
