import java.awt.Color;

class Test1_StandardCells {
    public static void main(String[] args) {
        Color[] colors = {new Color(0, 128, 255), new Color(255, 255, 255)};
        GameOfLifeSimulator game = new GameOfLifeSimulator(400, 20, new int[]{2, 3}, new int[]{3}, colors[0], colors[1]);
        // Additional code specific to Test 1 if needed
    }
}

class Test2_StaticCells {
    public static void main(String[] args) {
        Color[] colors = {new Color(0, 128, 255), new Color(255, 255, 255)};
        GameOfLifeSimulator game = new GameOfLifeSimulator(600, 30, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, new int[]{}, colors[0], colors[1]);
        // Additional code specific to Test 2 if needed
    }
}

class Test3_TwoStatesCells {
    public static void main(String[] args) {
        Color[] colors = {new Color(0, 128, 255), new Color(255, 165, 0)};
        GameOfLifeSimulator game = new GameOfLifeSimulator(400, 20, new int[]{}, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, colors[0], colors[1]);
        // Additional code specific to Test 3 if needed
    }
}
class Test4_CellsCondemnedToConverge {
    public static void main(String[] args) {
        Color[] colors = {new Color(0, 128, 255), new Color(128, 0, 200)};
        GameOfLifeSimulator game = new GameOfLifeSimulator(800, 40, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, new int[]{0, 3, 4, 8}, colors[0], colors[1]);
        // Additional code specific to Test 4 if needed
    }
}


class Test5_CellsCondemnedToDie {
    public static void main(String[] args) {
        Color[] colors = {new Color(0, 255, 0), new Color(255, 69, 0)};
        GameOfLifeSimulator game = new GameOfLifeSimulator(400, 20, new int[]{}, new int[]{}, colors[0], colors[1]);
        // Additional code specific to Test 5 if needed
    }
}
