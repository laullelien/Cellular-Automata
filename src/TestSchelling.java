import java.awt.*;

class TestShellingSmall2Colors {

    public static void main(String[] args) {
        final Color[] colors = {Color.white, Color.blue, Color.red};
        SchellingSimulator test = new SchellingSimulator(400, 4, 4, (char) 2, colors);
    }
}

class TestShellingSmall4Colors {

    public static void main(String[] args) {
        final Color[] colors = {Color.white, Color.blue, Color.red, Color.green, Color.magenta};
        SchellingSimulator test = new SchellingSimulator(400, 4, 3, (char) 4, colors);
    }
}

class TestShellingMedium3Colors {

    public static void main(String[] args) {
        final Color[] colors = {Color.white, Color.blue, Color.red, Color.green};
        SchellingSimulator test = new SchellingSimulator(800, 20, 2, (char) 3, colors);
    }
}
class TestShellingMedium6Colors {

    public static void main(String[] args) {
        final Color[] colors = {Color.white, Color.blue, Color.red, Color.green, Color.magenta, Color.orange, Color.pink};
        SchellingSimulator test = new SchellingSimulator(800, 20, 5, (char) 6, colors);
    }
}
class TestShellingBig3Colors {

    public static void main(String[] args) {
        final Color[] colors = {Color.white, Color.blue, Color.red, Color.green};
        SchellingSimulator test = new SchellingSimulator(800, 100, 3, (char) 3, colors);
    }
}

class TestShellingBig6Colors {

    public static void main(String[] args) {
        final Color[] colors = {Color.white, Color.blue, Color.red, Color.green, Color.magenta, Color.orange, Color.pink};
        SchellingSimulator test = new SchellingSimulator(800, 100, 5, (char) 6, colors);
    }
}
