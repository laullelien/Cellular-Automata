import java.awt.Color;
class Test1_StaticCells {
    public static void main(String[] args) {
        Color[] colors = {new Color(10, 10, 100), new Color(255, 255, 255)};
        GameOfLifeSimulator game = new GameOfLifeSimulator(4, 2, new int[]{ /* Define numberNeighborsToLive */ }, new int[]{ /* Define numberNeighborsToBorn */ }, colors[0], colors[1]);
        // Additional code specific to Test 1 if needed
    }
}

class Test2_BlinkerCells {
    public static void main(String[] args) {
        Color[] colors = {new Color(10, 10, 100), new Color(255, 255, 255)};
        GameOfLifeSimulator game = new GameOfLifeSimulator(11, 2, new int[]{ /* Define numberNeighborsToLive */ }, new int[]{ /* Define numberNeighborsToBorn */ }, colors[0], colors[1]);
        // Additional code specific to Test 2 if needed
    }
}

class Test3_GliderCells {
    public static void main(String[] args) {
        Color[] colors = {new Color(10, 10, 100), new Color(100, 0, 200), new Color(190, 0, 200), new Color(255, 255, 255)};
        GameOfLifeSimulator game = new GameOfLifeSimulator(11, 4, new int[]{ /* Define numberNeighborsToLive */ }, new int[]{ /* Define numberNeighborsToBorn */ }, colors[0], colors[3]);
        // Additional code specific to Test 3 if needed
    }
}

class Test4_OscillatingCells {
    public static void main(String[] args) {
        Color[] colors = {new Color(10, 10, 100), new Color(100, 0, 200), new Color(255, 255, 255)};
        GameOfLifeSimulator game = new GameOfLifeSimulator(50, 3, new int[]{ /* Define numberNeighborsToLive */ }, new int[]{ /* Define numberNeighborsToBorn */ }, colors[0], colors[2]);
        // Additional code specific to Test 4 if needed
    }
}

class Test5_ExpandingGrid {
    public static void main(String[] args) {
        Color[] colors = {new Color(10, 10, 100), new Color(100, 0, 200), new Color(255, 255, 255)};
        GameOfLifeSimulator game = new GameOfLifeSimulator(10, 3, new int[]{ /* Define numberNeighborsToLive */ }, new int[]{ /* Define numberNeighborsToBorn */ }, colors[0], colors[2]);
        // Additional code specific to Test 5 if needed
    }
}
