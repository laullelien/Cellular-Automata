import java.awt.Color;

public class TestGameOfLifeSimulator {
    public static void main(String[] args) {
        GameOfLifeSimulator game = new GameOfLifeSimulator(500, 50, new int[]{2, 3}, new int[]{3}, Color.white, Color.blue);
    }
}
