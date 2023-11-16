import java.awt.*;
import java.util.LinkedList;
import java.util.Random;


public class SchellingSimulator extends GridSimulable {
    /*
    Families are represented by an id of type char
     */
    /*
    Linkedlist of families that have moved from the grid
    because they had to many neighbours of a different kind
     */
    final LinkedList<Character> homelessFamilies;
    /*
    Linkedlist of cells that aren't occupied by any family
     */
    final LinkedList<Point> availableHomes;
    /*
    Grid that stores in each cell a positive family id
    or 0 if the cell isn't inhabited
     */
    final private char[][] grid;
    /*
    List of colors
    colors[i] is the color that will be used to represent
    the ith family if i is positive or not inhabited homes if i is 0
     */
    final private Color[] colors;
    private final int K;
    private final char colorNumber;


    public SchellingSimulator(int windowSize, int gridWidth, int K, char colorNumber, Color[] colors) {
        super(windowSize, gridWidth);
        this.K = K;
        this.colorNumber = colorNumber;
        this.colors = colors;
        homelessFamilies = new LinkedList<>();
        availableHomes = new LinkedList<>();
        grid = new char[gridWidth][gridWidth];
        super.restart();
    }

    public void moveHomelessFamiliesToAvailableHomes() {
        Random generator = new Random();
        while (!availableHomes.isEmpty() && !homelessFamilies.isEmpty()) {
            char family = homelessFamilies.poll();
            int randInt = generator.nextInt(availableHomes.size());
            // get a random and not inhabited home
            Point homeCoordinate = availableHomes.get(randInt);
            // move the family in this home
            availableHomes.remove(randInt);
            grid[homeCoordinate.x][homeCoordinate.y] = family;
            // show the change
            super.colorCell(colors[family], homeCoordinate.x, homeCoordinate.y);
        }
    }

    /*
    Initializes the grid randomly
     */
    public void initialiseGrid() {
        int gridWidth = super.getGridWidth();
        Random generator = new Random();
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridWidth; j++) {
                grid[i][j] = (char) generator.nextInt(colorNumber + 1);
            }
        }
    }

    public void drawGrid() {
        int gridWidth = super.getGridWidth();
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridWidth; j++) {
                super.colorCell(colors[grid[i][j]], i, j);
            }
        }
    }

    /*
    Goes through the entire grid and stores available homes
    i.e. cells that store 0 in the linkedlist availableHomes
     */
    public void addAvailableHomes() {
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
    public void removeUnhappyFamilies() {
        int gridWidth = super.getGridWidth();
        // Remove families that have more that have more than K neighbours of a different color
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridWidth; j++) {

                if (grid[i][j] == 0) {
                    continue;
                }

                int diffrentNeighbours = 0;

                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        // Check whether the cell is in the grid ,isn't empty or of the same family
                        if (k >= 0 && k < gridWidth && l >= 0 && l < gridWidth && grid[k][l] != 0 && grid[i][j] != grid[k][l]) {
                            diffrentNeighbours++;
                        }

                    }
                }

                if (diffrentNeighbours >= K) {
                    homelessFamilies.add(grid[i][j]);
                    availableHomes.add(new Point(i, j));
                    grid[i][j] = 0;
                    super.colorCell(colors[0], i, j);
                }

            }
        }
    }

    public void clearAvailableHomes() {
        availableHomes.clear();
    }

    public void clearHomelessFamilies() {
        homelessFamilies.clear();
    }

    @Override
    public Event getStartingEvent() {
        return new SchellingEvent(super.getManager(), true, this, 0);
    }
}
