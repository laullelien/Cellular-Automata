import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;


class TestGirdSimulator {

    public static void main(String[] args) {
        GridSimulable gridSimulator = new GridSimulable(900, 25);
    }
}

public class GridSimulable implements Simulable {
    final private GUISimulator gui;
    final private int windowSize;
    final private int GridWidth;

    /*
    cellSize must be odd and must divide windowSize
     */
    public GridSimulable(int windowSize, int width) {
        this.windowSize = windowSize;
        this.GridWidth = width;
        this.gui = new GUISimulator(this.windowSize, this.windowSize, Color.white, this);
    }

    @Override
    public void next() {
        System.out.println("Not implemented yet!");
    }

    @Override
    public void restart() {
        int cellSize = windowSize / GridWidth;
        int halfCellSize = (cellSize + 1) / 2;
        for (int i = 0; i < windowSize / cellSize; i++) {
            for (int j = 0; j < windowSize / cellSize; j++) {
                this.gui.addGraphicalElement(new Rectangle(halfCellSize + i * cellSize, halfCellSize + j * cellSize, Color.black, Color.white, cellSize));
            }
        }
    }

    public void colorCell(Color color, int x, int y) {
        int cellSize = windowSize / GridWidth;
        this.gui.addGraphicalElement(new Rectangle((cellSize + 1) / 2 + x * cellSize, (cellSize + 1) / 2 + y * cellSize, Color.black, color, cellSize));
    }

    public void resetCell(int x, int y) {
        int cellSize = windowSize / GridWidth;
        this.gui.addGraphicalElement(new Rectangle((cellSize + 1) / 2 + x * cellSize, (cellSize + 1) / 2 + y * cellSize, Color.black, Color.lightGray, cellSize));
    }

    public int getGridWidth() {
        return GridWidth;
    }
}
