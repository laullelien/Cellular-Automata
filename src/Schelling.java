import java.awt.*;
import java.util.LinkedList;
import java.util.Random;


public class Schelling extends GridSimulable {
    final LinkedList<Character> homelessFamilies;
    final LinkedList<Point> availableHomes;
    final private char[][] grid;
    final private Color[] colors;
    private final int K;
    private final char colorNumber;


    public Schelling(int windowSize, int gridWidth, int K, char colorNumber, Color[] colors) {
        super(windowSize, gridWidth);
        this.K = K;
        this.colorNumber = colorNumber;
        this.colors = colors;
        homelessFamilies = new LinkedList<>();
        availableHomes = new LinkedList<>();
        grid = new char[gridWidth][gridWidth];
        initialiseGrid();
        drawGrid();
    }

    private void moveHomelessFamiliesToAvailableHomes() {
        Random generator = new Random();
        while (availableHomes.size() != 0 && homelessFamilies.size() != 0) {
            char family = homelessFamilies.getFirst();
            homelessFamilies.removeFirst();
            int randInt = generator.nextInt(availableHomes.size());
            Point homeCoordinate = availableHomes.get(randInt);
            availableHomes.remove(randInt);
            grid[homeCoordinate.x][homeCoordinate.y] = family;
            super.colorCell(colors[family], homeCoordinate.x, homeCoordinate.y);
        }
    }

    /*
    Initializes the grid randomly
     */
    private void initialiseGrid() {
        int gridWidth = super.getGridWidth();
        Random generator = new Random();
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridWidth; j++) {
                grid[i][j] = (char) generator.nextInt(colorNumber + 1);
            }
        }
    }

    private void drawGrid() {
        int gridWidth = super.getGridWidth();
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridWidth; j++) {
                super.colorCell(colors[grid[i][j]], i, j);
            }
        }
    }

    private void addAvailableHomes() {
        int gridWidth = super.getGridWidth();
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridWidth; j++) {
                if (grid[i][j] == 0) {
                    availableHomes.add(new Point(i, j));
                }
            }
        }
    }

    /*
    A family is unhappy if there are more than K families of a different kind in the 8 families surrounding it
     */
    private void removeUnhappyFamilies() {
        int gridWidth = super.getGridWidth();
        // Remove families that have more that have more than K neighbours of a different color
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridWidth; j++) {

                if (grid[i][j] == 0) {
                    continue;
                }

                int neighbours = 0;

                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {

                        if (k >= 0 && k < gridWidth && l >= 0 && l < gridWidth && grid[k][l] != 0 && grid[i][j] != grid[k][l]) {
                            neighbours++;
                        }

                    }
                }

                if (neighbours >= K) {
                    homelessFamilies.add(grid[i][j]);
                    availableHomes.add(new Point(i, j));
                    grid[i][j] = 0;
                    super.colorCell(colors[0], i, j);
                }

            }
        }
    }

    @Override
    public void restart() {
        availableHomes.clear();
        homelessFamilies.clear();
        initialiseGrid();
        drawGrid();
        addAvailableHomes();
    }

    @Override
    public void next() {
        moveHomelessFamiliesToAvailableHomes();
        removeUnhappyFamilies();
    }

    @Override
    public Event getCurrentEvent() {
        return new MessageEvent(0, "Not implemented yet!");
    }

    @Override
    public Event getStartingEvent() {
        return new MessageEvent(0, "Not implemented yet!");
    }
}
